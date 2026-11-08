-- Sample data Module 8.1 (ACID). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module8-acid-sample-data.sql
-- Aman diulang: tabel di-drop dulu.
-- Demo, bukan latihan: buka 2 sesi psql untuk bagian Isolation.

DROP TABLE IF EXISTS acid_accounts CASCADE;

CREATE TABLE acid_accounts (
    id      serial PRIMARY KEY,
    owner   text   NOT NULL,
    balance bigint NOT NULL,
    -- Consistency: DB sendiri yang menolak saldo negatif.
    CONSTRAINT balance_not_negative CHECK (balance >= 0)
);

INSERT INTO acid_accounts (owner, balance) VALUES
    ('Isryad', 1000000),
    ('Arif',    500000);

-- ============================================================
-- Query demo (jalankan manual, satu blok per kali)
-- ============================================================

-- 1) ATOMICITY: debit jalan, lalu "crash" sebelum kredit -> ROLLBACK.
--    Setelah ROLLBACK, saldo Isryad kembali 1.000.000 (debit ikut batal).
BEGIN;
UPDATE acid_accounts SET balance = balance - 300000 WHERE owner = 'Isryad';
-- anggap server mati di sini, atau error:
ROLLBACK;
SELECT owner, balance FROM acid_accounts ORDER BY id;

owner |balance|
------+-------+
Isryad|1000000|
Arif  | 500000|

-- 2) CONSISTENCY: transfer melebihi saldo ditolak constraint, bukan cuma if di Java.
BEGIN;
UPDATE acid_accounts SET balance = balance - 2000000 WHERE owner = 'Arif';
-- ERROR: violates check constraint "balance_not_negative"
ROLLBACK;

SQL Error [23514]: ERROR: new row for relation "acid_accounts" violates check constraint "balance_not_negative"
  Detail: Failing row contains (2, Arif, -1500000).

-- 3) ISOLATION: sesi A belum COMMIT, sesi B tidak boleh melihat perubahannya.
   Sesi A: BEGIN; UPDATE acid_accounts SET balance = 0 WHERE owner = 'Isryad';
   Sesi B: SELECT balance FROM acid_accounts WHERE owner = 'Isryad';  -- masih 1000000
   Sesi A: ROLLBACK;

-- 4) DURABILITY: setelah COMMIT, data bertahan walau server restart (WAL di-flush).
SHOW synchronous_commit;   -- 'on' = COMMIT baru balik setelah WAL ditulis ke disk
SHOW fsync;

fsync|
-----+
on   |