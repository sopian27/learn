-- Sample data Module 6.3 (JPA/Hibernate: N+1, JOIN FETCH, batch fetch, readOnly). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module6-jpa-sample-data.sql
-- Aman diulang: tabel di-drop dulu.
-- Ini SQL yang dikirim Hibernate di belakang layar. Tujuannya: lihat bentuk query-nya, bukan belajar Java.

DROP TABLE IF EXISTS jpa_order_items, jpa_orders, jpa_customers CASCADE;

CREATE TABLE jpa_customers (
    id   serial PRIMARY KEY,
    name text NOT NULL
);
CREATE TABLE jpa_orders (
    id          serial PRIMARY KEY,
    customer_id int NOT NULL REFERENCES jpa_customers(id),
    status      text NOT NULL DEFAULT 'NEW',
    created_at  timestamptz NOT NULL DEFAULT now()
);
CREATE TABLE jpa_order_items (
    id       serial PRIMARY KEY,
    order_id int NOT NULL REFERENCES jpa_orders(id),
    product  text NOT NULL,
    qty      int  NOT NULL CHECK (qty > 0)
);
CREATE INDEX idx_jpa_orders_customer ON jpa_orders (customer_id);
CREATE INDEX idx_jpa_items_order     ON jpa_order_items (order_id);

-- 20 customer, 100 order (5 per customer), 300 item (3 per order).
INSERT INTO jpa_customers (name) SELECT 'Customer ' || g FROM generate_series(1, 20) g;
INSERT INTO jpa_orders (customer_id) SELECT 1 + (g % 20) FROM generate_series(1, 100) g;
INSERT INTO jpa_order_items (order_id, product, qty)
SELECT o.id, 'Produk ' || p, p FROM jpa_orders o, generate_series(1, 3) p;

ANALYZE jpa_customers; ANALYZE jpa_orders; ANALYZE jpa_order_items;

-- Contoh query dari lesson (jalankan satu-satu, hitung jumlah statement-nya):
--
-- 1) N+1 (yang ditembak Hibernate untuk findAll() + o.getCustomer().getName()):
--    Query pertama sekali, lalu query kedua DIULANG per customer_id berbeda (di app: 1 + N kali).
-- SELECT id, customer_id, status, created_at FROM jpa_orders;
-- SELECT id, name FROM jpa_customers WHERE id = 1;
-- SELECT id, name FROM jpa_customers WHERE id = 2;
-- SELECT id, name FROM jpa_customers WHERE id = 3;   -- ...dan seterusnya
-- Hitung: jumlah customer_id berbeda di order = jumlah query tambahan.
-- SELECT count(DISTINCT customer_id) AS query_tambahan FROM jpa_orders;
--
query_tambahan|
--------------+
            20|
--
-- 2) Fix JOIN FETCH / @EntityGraph: satu query saja.
-- SELECT o.id, o.status, c.id, c.name
--   FROM jpa_orders o JOIN jpa_customers c ON c.id = o.customer_id;
--
id |status|id|name       |
---+------+--+-----------+
  1|NEW   | 2|Customer 2 |
  2|NEW   | 3|Customer 3 |
  3|NEW   | 4|Customer 4 |
  4|NEW   | 5|Customer 5 |
  5|NEW   | 6|Customer 6 |
--
-- 3) Fix @BatchSize: N query digabung jadi IN (...) per batch (mis. batch 10).
-- SELECT id, name FROM jpa_customers WHERE id IN (1,2,3,4,5,6,7,8,9,10);
-- SELECT id, name FROM jpa_customers WHERE id IN (11,12,13,14,15,16,17,18,19,20);
--
id|name       |
--+-----------+
 1|Customer 1 |
 2|Customer 2 |
 3|Customer 3 |
 4|Customer 4 |
 5|Customer 5 |
 6|Customer 6 |
 7|Customer 7 |
 8|Customer 8 |
 9|Customer 9 |
10|Customer 10|
--
-- 4) Fix DTO projection: ambil kolom yang dipakai saja, tanpa entity.
-- SELECT o.id AS order_id, c.name AS customer_name FROM jpa_orders o
--   JOIN jpa_customers c ON c.id = o.customer_id;
--
order_id|customer_name|
--------+-------------+
       1|Customer 2   |
       2|Customer 3   |
       3|Customer 4   |
       4|Customer 5   |
       5|Customer 6   |
       6|Customer 7   |
       7|Customer 8   |
       8|Customer 9   |
       9|Customer 10  |
      10|Customer 11  |
--
-- 5) Jebakan JOIN FETCH collection + pagination (HHH000104, paging di memory):
--    SQL ini mengembalikan 300 baris (satu per item), padahal "10 order" yang diminta.
--    Hibernate tidak bisa LIMIT di SQL karena baris duplikat per order, jadi ambil semua lalu potong di JVM.
-- SELECT o.id, i.id AS item_id FROM jpa_orders o JOIN jpa_order_items i ON i.order_id = o.id;
-- Solusi dua tahap: ambil ID dulu (LIMIT jalan benar), lalu fetch item untuk ID itu saja.
-- SELECT id FROM jpa_orders ORDER BY id LIMIT 10;
-- SELECT * FROM jpa_order_items WHERE order_id IN (1,2,3,4,5,6,7,8,9,10);
--
-- 6) Bandingkan biaya 1 query vs N query dengan EXPLAIN (query 2 vs query 1 + loop manual):
-- EXPLAIN (ANALYZE) SELECT o.id, c.name FROM jpa_orders o JOIN jpa_customers c ON c.id = o.customer_id;
-- Catatan: di DB lokal tiap query murah. Di aplikasi, yang mahal itu round trip jaringan x N, bukan eksekusi SQL-nya.
--
QUERY PLAN                                                                                                            |
----------------------------------------------------------------------------------------------------------------------+
Hash Join  (cost=1.45..3.77 rows=100 width=15) (actual time=0.037..0.065 rows=100 loops=1)                            |
  Hash Cond: (o.customer_id = c.id)                                                                                   |
  ->  Seq Scan on jpa_orders o  (cost=0.00..2.00 rows=100 width=8) (actual time=0.009..0.016 rows=100 loops=1)        |
  ->  Hash  (cost=1.20..1.20 rows=20 width=15) (actual time=0.014..0.014 rows=20 loops=1)                             |
        Buckets: 1024  Batches: 1  Memory Usage: 9kB                                                                  |
        ->  Seq Scan on jpa_customers c  (cost=0.00..1.20 rows=20 width=15) (actual time=0.004..0.007 rows=20 loops=1)|
Planning Time: 0.159 ms                                                                                               |
Execution Time: 0.088 ms                                                                                              |
--
-- 7) readOnly = true di level DB (Spring + PostgreSQL mengirim ini):
-- BEGIN READ ONLY;
-- SELECT count(*) FROM jpa_orders;                       -- boleh
-- UPDATE jpa_orders SET status = 'PAID' WHERE id = 1;    -- ERROR: cannot execute UPDATE in a read-only transaction
-- ROLLBACK;
--
SQL Error [25006]: ERROR: cannot execute UPDATE in a read-only transaction
--
-- 8) Dirty checking tanpa save(): ini UPDATE yang Hibernate generate saat flush
--    kalau entity berubah di dalam transaksi biasa (readOnly = false):
-- UPDATE jpa_orders SET status = 'PAID' WHERE id = 1;
-- Di transaksi readOnly = true, UPDATE ini tidak pernah dikirim karena flush mode MANUAL.
--
SQL Error [25P02]: ERROR: current transaction is aborted, commands ignored until end of transaction block

Error position:

Error position:
--