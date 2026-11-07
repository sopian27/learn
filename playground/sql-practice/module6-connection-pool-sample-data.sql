-- Sample data Module 6.5 (Connection pooling / HikariCP). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module6-connection-pool-sample-data.sql
-- Aman diulang: tabel di-drop dulu.
-- Ini bukan demo Hikari-nya; ini lihat sisi database: berapa koneksi, siapa yang menahan.

DROP TABLE IF EXISTS pool_payments CASCADE;

CREATE TABLE pool_payments (
    id         serial PRIMARY KEY,
    order_id   int  NOT NULL,
    status     text NOT NULL DEFAULT 'PENDING'
);
INSERT INTO pool_payments (order_id) SELECT g FROM generate_series(1, 50) g;

-- Contoh query dari lesson (jalankan satu-satu):
--
-- 1) Batas koneksi server dan berapa yang terpakai sekarang.
--    Total koneksi semua pod + tooling harus di bawah angka ini.
-- SHOW max_connections;
-- SELECT count(*) AS terpakai FROM pg_stat_activity;
--
max_connections|
---------------+
100            |

terpakai|
--------+
       8|
--
-- 2) Kelompokkan koneksi per state. "idle in transaction" yang banyak = tanda koneksi ditahan.
-- SELECT state, count(*) FROM pg_stat_activity GROUP BY state ORDER BY count(*) DESC;
--
state |count|
------+-----+
      |    5|
idle  |    2|
active|    1|
--
-- 3) Simulasi cerita Akbar: transaksi yang menahan koneksi sambil "menunggu payment gateway".
--    Di sesi psql A, jalankan baris berikut dan JANGAN commit dulu:
-- BEGIN;
-- UPDATE pool_payments SET status = 'PROCESSING' WHERE id = 1;
-- -- (di sini aplikasi memanggil HTTP ke gateway selama beberapa detik)
--    Di sesi psql B, lihat koneksi A berstatus idle in transaction:
-- SELECT pid, state, now() - xact_start AS lama_transaksi, left(query, 60) AS query_terakhir
--   FROM pg_stat_activity
--  WHERE state = 'idle in transaction'
--  ORDER BY xact_start;
--    Sesi A lalu: COMMIT;  (koneksi kembali bebas)
--
-- 4) Transaksi tahan lock: sesi lain yang update baris yang sama akan menunggu.
--    Sesi B (saat A masih BEGIN): UPDATE pool_payments SET status = 'PAID' WHERE id = 1;  -- menggantung
--    Sesi C, lihat siapa menunggu siapa:
-- SELECT pid, pg_blocking_pids(pid) AS diblokir_oleh, state, left(query, 60) AS query
--   FROM pg_stat_activity
--  WHERE cardinality(pg_blocking_pids(pid)) > 0;
--
-- 5) Pengaman di sisi DB (opsional): batasi transaksi yang diam terlalu lama.
-- SET idle_in_transaction_session_timeout = '5s';   -- sesi A akan diputus jika diam > 5 detik
