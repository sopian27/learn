-- Sample data Module 6.1 (Indexing: B-tree, composite, biaya write, EXPLAIN). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module6-indexing-sample-data.sql
-- Aman diulang: tabel di-drop dulu. Generate 200.000 order, butuh beberapa detik.

DROP TABLE IF EXISTS idx_orders CASCADE;

CREATE TABLE idx_orders (
    id          bigserial PRIMARY KEY,           -- PK otomatis dapat B-tree index
    customer_id int         NOT NULL,
    status      text        NOT NULL,
    total       numeric(12,2) NOT NULL,
    created_at  timestamptz NOT NULL
);

INSERT INTO idx_orders (customer_id, status, total, created_at)
SELECT (random() * 5000)::int + 1,
       (ARRAY['NEW','PAID','SHIPPED','DONE','CANCELLED'])[1 + (random() * 4)::int],
       (random() * 1000000)::numeric(12,2),
       now() - (random() * interval '365 days')
FROM generate_series(1, 200000);

ANALYZE idx_orders;  -- statistik segar, supaya planner ambil keputusan masuk akal

-- Contoh query dari lesson (jalankan satu-satu, bandingkan sebelum/sesudah index):
--
-- 1) Tanpa index (harusnya Seq Scan):
-- EXPLAIN (ANALYZE, BUFFERS) SELECT * FROM idx_orders WHERE customer_id = 42;
--
QUERY PLAN                                                                                               |
---------------------------------------------------------------------------------------------------------+
Seq Scan on idx_orders  (cost=0.00..4167.00 rows=40 width=34) (actual time=0.334..21.184 rows=51 loops=1)|
  Filter: (customer_id = 42)                                                                             |
  Rows Removed by Filter: 199949                                                                         |
  Buffers: shared hit=1667                                                                               |
Planning:                                                                                                |
  Buffers: shared hit=27                                                                                 |
Planning Time: 0.325 ms                                                                                  |
Execution Time: 21.224 ms                                                                                |
--
-- 2) Index single-column, lalu ulangi query 1 (harusnya Index/Bitmap Scan):
-- CREATE INDEX idx_orders_customer ON idx_orders (customer_id);
-- ANALYZE idx_orders;
--
Updated Rows	0
Execute time	0.06s
Start time	Sat Nov 07 23:06:27 WIB 2026
Finish time	Sat Nov 07 23:06:27 WIB 2026
Query	ANALYZE idx_orders
--
-- 3) Query yang filter customer + sort tanggal:
-- EXPLAIN (ANALYZE, BUFFERS)
-- SELECT * FROM idx_orders WHERE customer_id = 42 ORDER BY created_at DESC LIMIT 10;
--
QUERY PLAN                                                                                                                              |
----------------------------------------------------------------------------------------------------------------------------------------+
Limit  (cost=147.38..147.41 rows=10 width=34) (actual time=0.115..0.117 rows=10 loops=1)                                                |
  Buffers: shared hit=49 read=2                                                                                                         |
  ->  Sort  (cost=147.38..147.48 rows=40 width=34) (actual time=0.114..0.115 rows=10 loops=1)                                           |
        Sort Key: created_at DESC                                                                                                       |
        Sort Method: top-N heapsort  Memory: 26kB                                                                                       |
        Buffers: shared hit=49 read=2                                                                                                   |
        ->  Bitmap Heap Scan on idx_orders  (cost=4.60..146.52 rows=40 width=34) (actual time=0.044..0.103 rows=51 loops=1)             |
              Recheck Cond: (customer_id = 42)                                                                                          |
              Heap Blocks: exact=49                                                                                                     |
              Buffers: shared hit=49 read=2                                                                                             |
              ->  Bitmap Index Scan on idx_orders_customer  (cost=0.00..4.59 rows=40 width=0) (actual time=0.024..0.025 rows=51 loops=1)|
                    Index Cond: (customer_id = 42)                                                                                      |
                    Buffers: shared read=2                                                                                              |
Planning:                                                                                                                               |
  Buffers: shared hit=34 read=1                                                                                                         |
Planning Time: 0.310 ms                                                                                                                 |
Execution Time: 0.167 ms                                                                                                                |
--
-- 4) Composite index (urutan kolom penting: equality dulu, baru sort/range):
-- CREATE INDEX idx_orders_cust_created ON idx_orders (customer_id, created_at DESC);
-- ANALYZE idx_orders;
-- -> ulangi query 3, lihat langkah Sort hilang.
--
QUERY PLAN                                                                                                                                   |
---------------------------------------------------------------------------------------------------------------------------------------------+
Limit  (cost=0.42..41.59 rows=10 width=34) (actual time=0.097..0.111 rows=10 loops=1)                                                        |
  Buffers: shared hit=10 read=3                                                                                                              |
  ->  Index Scan using idx_orders_cust_created on idx_orders  (cost=0.42..165.10 rows=40 width=34) (actual time=0.095..0.108 rows=10 loops=1)|
        Index Cond: (customer_id = 42)                                                                                                       |
        Buffers: shared hit=10 read=3                                                                                                        |
Planning:                                                                                                                                    |
  Buffers: shared hit=17 read=1                                                                                                              |
Planning Time: 0.513 ms                                                                                                                      |
Execution Time: 0.140 ms                                                                                                                     |
--
-- 5) Urutan kolom salah: filter hanya created_at, index (customer_id, created_at) nggak efektif:
-- EXPLAIN (ANALYZE) SELECT * FROM idx_orders WHERE created_at > now() - interval '1 day';
--
QUERY PLAN                                                                                                                |
--------------------------------------------------------------------------------------------------------------------------+
Gather  (cost=1000.00..4779.22 rows=534 width=34) (actual time=0.390..18.283 rows=541 loops=1)                            |
  Workers Planned: 1                                                                                                      |
  Workers Launched: 1                                                                                                     |
  ->  Parallel Seq Scan on idx_orders  (cost=0.00..3725.82 rows=314 width=34) (actual time=0.043..14.118 rows=270 loops=2)|
        Filter: (created_at > (now() - '1 day'::interval))                                                                |
        Rows Removed by Filter: 99730                                                                                     |
Planning Time: 0.105 ms                                                                                                   |
Execution Time: 18.331 ms                                                                                                 |
--
-- 6) Biaya write: bandingkan waktu insert massal dengan 0 vs 3 index tambahan:
-- \timing on
-- INSERT INTO idx_orders (customer_id, status, total, created_at)
-- SELECT 1, 'NEW', 1, now() FROM generate_series(1, 100000);
-- (buat index status & total & created_at, ulangi insert, bandingkan durasi)
--
Updated Rows	100000
Execute time	0.461s
Start time	Sat Nov 07 23:14:28 WIB 2026
Finish time	Sat Nov 07 23:14:29 WIB 2026
Query	INSERT INTO idx_orders (customer_id, status, total, created_at)
	 SELECT 1, 'NEW', 1, now() FROM generate_series(1, 100000)
--
-- 7) Cek index yang nggak pernah dipakai:
-- SELECT indexrelname, idx_scan FROM pg_stat_user_indexes WHERE relname = 'idx_orders';
indexrelname           |idx_scan|
-----------------------+--------+
idx_orders_pkey        |       0|
idx_orders_customer    |       1|
idx_orders_cust_created|       1|
