-- Companion Library Lesson 5.2 (code-review): review migrasi + locking.
-- Run: psql -U postgres -d postgres -f code-review-m5-migration-locking-sample-data.sql
-- Pakai database latihan: script ini men-DROP tabel cr_orders.
\set ON_ERROR_STOP on

DROP TABLE IF EXISTS cr_orders;
CREATE TABLE cr_orders (
    id          bigint PRIMARY KEY,
    customer_id bigint NOT NULL,
    amount      numeric(12,2) NOT NULL
);
INSERT INTO cr_orders
SELECT g, g % 1000, (g % 500) + 1 FROM generate_series(1, 200000) g;

-- 1. Default konstan vs volatile: relfilenode berubah = seluruh tabel ditulis ulang.
SELECT pg_relation_filenode('cr_orders') AS before_node \gset
ALTER TABLE cr_orders ADD COLUMN channel text DEFAULT 'web';
SELECT pg_relation_filenode('cr_orders') AS const_node \gset
ALTER TABLE cr_orders ADD COLUMN trace_id uuid DEFAULT gen_random_uuid();
SELECT pg_relation_filenode('cr_orders') AS volatile_node \gset

SELECT :before_node = :const_node     AS constant_default_no_rewrite,
       :const_node <> :volatile_node  AS volatile_default_rewrites;

-- 2. NOT NULL bertahap (expand dulu, kencangkan belakangan).
ALTER TABLE cr_orders ADD COLUMN currency text;
UPDATE cr_orders SET currency = 'IDR';  -- di production: backfill per batch, di luar Flyway
ALTER TABLE cr_orders
    ADD CONSTRAINT cr_currency_nn CHECK (currency IS NOT NULL) NOT VALID;  -- lock singkat
ALTER TABLE cr_orders VALIDATE CONSTRAINT cr_currency_nn;  -- scan, tapi write tetap jalan
ALTER TABLE cr_orders ALTER COLUMN currency SET NOT NULL;  -- pakai CHECK, tanpa scan ulang
ALTER TABLE cr_orders DROP CONSTRAINT cr_currency_nn;

-- 3. Index tanpa memblok write. Tidak boleh di dalam transaction block.
CREATE INDEX CONCURRENTLY cr_orders_customer_idx ON cr_orders (customer_id);
SELECT indisvalid AS index_valid FROM pg_index
WHERE indexrelid = 'cr_orders_customer_idx'::regclass;

-- 4. Antrean lock (manual, 3 terminal psql, jalankan berurutan):
--   T1: BEGIN; SELECT count(*) FROM cr_orders;          -- jangan di-COMMIT
--   T2: ALTER TABLE cr_orders ADD COLUMN note text;     -- menunggu T1
--   T3: SELECT * FROM cr_orders WHERE id = 1;           -- SELECT biasa ikut macet
--   Ulangi T2 dengan: SET lock_timeout = '2s';  lalu ALTER -> gagal cepat, T3 jalan lagi.

-- 5. Lost update vs atomic update:
--   BUG : SELECT amount ... ; hitung di aplikasi ; UPDATE cr_orders SET amount = <hasil>
--   FIX : UPDATE cr_orders SET amount = amount - 10 WHERE id = 1 AND amount >= 10;
--   FIX : SELECT amount FROM cr_orders WHERE id = 1 FOR UPDATE;  -- di dalam transaction
