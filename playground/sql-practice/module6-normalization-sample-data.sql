-- Sample data Module 6.2 (Normalisasi vs denormalisasi). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module6-normalization-sample-data.sql
-- Aman diulang: semua tabel di-drop dulu.

DROP TABLE IF EXISTS norm_order_items, norm_orders, norm_products, norm_customers, flat_orders CASCADE;

-- A) Versi "satu tabel gemuk": customer & produk ke-copy di setiap baris.
CREATE TABLE flat_orders (
    id               serial PRIMARY KEY,
    customer_name    text NOT NULL,
    customer_city    text NOT NULL,
    product_name     text NOT NULL,
    unit_price       numeric(12,2) NOT NULL,
    qty              int  NOT NULL
);
INSERT INTO flat_orders (customer_name, customer_city, product_name, unit_price, qty) VALUES
    ('Saski', 'Bandung', 'Keyboard', 450000, 1),
    ('Saski', 'Bandung', 'Mouse',    150000, 2),
    ('Saski', 'Bandung', 'Monitor', 2000000, 1),
    ('Septi', 'Jakarta', 'Mouse',    150000, 1);

-- B) Versi normal: tiap fakta disimpan di satu tempat.
CREATE TABLE norm_customers (
    id   serial PRIMARY KEY,
    name text NOT NULL,
    city text NOT NULL
);
CREATE TABLE norm_products (
    id    serial PRIMARY KEY,
    name  text NOT NULL UNIQUE,
    price numeric(12,2) NOT NULL CHECK (price >= 0)   -- harga SEKARANG
);
CREATE TABLE norm_orders (
    id          serial PRIMARY KEY,
    customer_id int NOT NULL REFERENCES norm_customers(id),
    created_at  timestamptz NOT NULL DEFAULT now()
);
CREATE TABLE norm_order_items (
    order_id   int NOT NULL REFERENCES norm_orders(id),
    product_id int NOT NULL REFERENCES norm_products(id),
    qty        int NOT NULL CHECK (qty > 0),
    unit_price numeric(12,2) NOT NULL,   -- SNAPSHOT harga saat beli (fakta historis, bukan duplikasi)
    PRIMARY KEY (order_id, product_id)
);
CREATE INDEX idx_norm_items_product ON norm_order_items (product_id);  -- FK tidak otomatis ter-index

INSERT INTO norm_customers (name, city) VALUES ('Saski', 'Bandung'), ('Septi', 'Jakarta');
INSERT INTO norm_products (name, price) VALUES ('Keyboard', 450000), ('Mouse', 150000), ('Monitor', 2000000);
INSERT INTO norm_orders (customer_id) VALUES (1), (2);
INSERT INTO norm_order_items (order_id, product_id, qty, unit_price) VALUES
    (1, 1, 1, 450000), (1, 2, 2, 150000), (1, 3, 1, 2000000), (2, 2, 1, 150000);

-- Contoh query dari lesson (jalankan satu-satu):
--
-- 1) Update anomaly: Saski pindah ke Surabaya. Di tabel gemuk harus update banyak baris;
--    lupa satu baris = data kontradiktif.
-- UPDATE flat_orders SET customer_city = 'Surabaya' WHERE id = 1;   -- sengaja cuma 1 baris
-- SELECT DISTINCT customer_name, customer_city FROM flat_orders WHERE customer_name = 'Saski';
--
customer_name|customer_city|
-------------+-------------+
Saski        |Bandung      |
Saski        |Surabaya     |
--
-- 2) Versi normal: satu UPDATE, satu baris, selalu konsisten.
-- UPDATE norm_customers SET city = 'Surabaya' WHERE name = 'Saski';
--
Updated Rows	1
Execute time	0.007s
Start time	Sat Nov 07 23:35:02 WIB 2026
Finish time	Sat Nov 07 23:35:02 WIB 2026
Query	UPDATE norm_customers SET city = 'Surabaya' WHERE name = 'Saski'
--
-- 3) Harga produk naik. Order lama TIDAK boleh ikut berubah (itu gunanya snapshot unit_price):
-- UPDATE norm_products SET price = 175000 WHERE name = 'Mouse';
-- SELECT o.id, sum(i.qty * i.unit_price) AS total FROM norm_orders o
--   JOIN norm_order_items i ON i.order_id = o.id GROUP BY o.id;
--
id|total     |
--+----------+
 2| 150000.00|
 1|2750000.00|
--
--
-- 4) Harga pembayaran lewat JOIN (biaya normalisasi: join; wajar di skala normal, cek dengan EXPLAIN):
-- EXPLAIN (ANALYZE) SELECT c.name, sum(i.qty * i.unit_price)
--   FROM norm_customers c JOIN norm_orders o ON o.customer_id = c.id
--   JOIN norm_order_items i ON i.order_id = o.id GROUP BY c.name;
--
QUERY PLAN                                                                                                                       |
---------------------------------------------------------------------------------------------------------------------------------+
HashAggregate  (cost=127.40..129.90 rows=200 width=64) (actual time=0.037..0.040 rows=2 loops=1)                                 |
  Group Key: c.name                                                                                                              |
  Batches: 1  Memory Usage: 40kB                                                                                                 |
  ->  Hash Join  (cost=80.75..112.90 rows=1450 width=52) (actual time=0.026..0.029 rows=4 loops=1)                               |
        Hash Cond: (o.customer_id = c.id)                                                                                        |
        ->  Hash Join  (cost=51.62..79.94 rows=1450 width=24) (actual time=0.010..0.012 rows=4 loops=1)                          |
              Hash Cond: (i.order_id = o.id)                                                                                     |
              ->  Seq Scan on norm_order_items i  (cost=0.00..24.50 rows=1450 width=24) (actual time=0.002..0.003 rows=4 loops=1)|
              ->  Hash  (cost=28.50..28.50 rows=1850 width=8) (actual time=0.003..0.004 rows=2 loops=1)                          |
                    Buckets: 2048  Batches: 1  Memory Usage: 17kB                                                                |
                    ->  Seq Scan on norm_orders o  (cost=0.00..28.50 rows=1850 width=8) (actual time=0.002..0.002 rows=2 loops=1)|
        ->  Hash  (cost=18.50..18.50 rows=850 width=36) (actual time=0.013..0.013 rows=2 loops=1)                                |
              Buckets: 1024  Batches: 1  Memory Usage: 9kB                                                                       |
              ->  Seq Scan on norm_customers c  (cost=0.00..18.50 rows=850 width=36) (actual time=0.010..0.010 rows=2 loops=1)   |
Planning Time: 0.166 ms                                                                                                          |
Execution Time: 0.067 ms                                                                                                         |
--
-- 5) Denormalisasi terukur: simpan total di norm_orders, TAPI ada satu jalur yang menghitungnya:
-- ALTER TABLE norm_orders ADD COLUMN total numeric(14,2);
-- UPDATE norm_orders o SET total = (SELECT sum(qty * unit_price) FROM norm_order_items WHERE order_id = o.id);
-- -- bisa dibangun ulang kapan saja dari norm_order_items = aman. Kalau tidak bisa dibangun ulang, jangan.
