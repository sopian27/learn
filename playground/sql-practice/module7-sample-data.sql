-- Sample data Module 7 (CTE + Window Function). PostgreSQL.
-- Jalankan: psql -U postgres -d belajar -f module7-sample-data.sql
-- Aman diulang: semua tabel di-drop dulu.

DROP TABLE IF EXISTS orders, users, penjualan_harian, omzet_bulanan, product_sales, employees, categories CASCADE;

-- 7.1 recursive CTE: kategori bertingkat
CREATE TABLE categories (
    id        int PRIMARY KEY,
    name      text NOT NULL,
    parent_id int REFERENCES categories(id)
);
INSERT INTO categories (id, name, parent_id) VALUES
    (1, 'Elektronik',     NULL),
    (2, 'Fashion',        NULL),
    (3, 'Rumah Tangga',   NULL),
    (4, 'Laptop',         1),
    (5, 'Smartphone',     1),
    (6, 'Gaming Laptop',  4),
    (7, 'Pria',           2),
    (8, 'Wanita',         2),
    (9, 'Dapur',          3);

-- 7.1 recursive CTE: struktur organisasi
CREATE TABLE employees (
    id         int PRIMARY KEY,
    name       text NOT NULL,
    manager_id int REFERENCES employees(id)
);
INSERT INTO employees (id, name, manager_id) VALUES
    (1, 'Budi (CEO)',      NULL),
    (2, 'Sari (CTO)',      1),
    (3, 'Andi (CFO)',      1),
    (4, 'Dewi (Eng Mgr)',  2),
    (5, 'Rudi (Backend)',  4),
    (6, 'Lina (Backend)',  4),
    (7, 'Tono (Akuntan)',  3);

-- 7.2 ranking + top-N per grup (ada nilai kembar sengaja: 800 dan 500)
CREATE TABLE product_sales (
    id          serial PRIMARY KEY,
    category_id int  NOT NULL REFERENCES categories(id),
    name        text NOT NULL,
    total_sold  int  NOT NULL
);
INSERT INTO product_sales (category_id, name, total_sold) VALUES
    (4, 'Laptop A', 900), (4, 'Laptop B', 800), (4, 'Laptop C', 800),
    (4, 'Laptop D', 700), (4, 'Laptop E', 300),
    (5, 'HP A', 1500), (5, 'HP B', 1200), (5, 'HP C', 500),
    (5, 'HP D', 500),  (5, 'HP E', 100),
    (7, 'Kemeja', 640), (7, 'Celana', 610), (7, 'Jaket', 400), (7, 'Topi', 150),
    (8, 'Dress', 720),  (8, 'Rok', 720),    (8, 'Blouse', 480),
    (9, 'Panci', 300),  (9, 'Wajan', 250);

-- 7.2 LAG/LEAD: perbandingan periode (bulan Mei sengaja turun)
CREATE TABLE omzet_bulanan (
    bulan date PRIMARY KEY,
    omzet numeric(14,2) NOT NULL
);
INSERT INTO omzet_bulanan (bulan, omzet) VALUES
    ('2026-01-01', 100000000), ('2026-02-01', 110000000),
    ('2026-03-01', 125000000), ('2026-04-01', 130000000),
    ('2026-05-01',  95000000), ('2026-06-01', 120000000),
    ('2026-07-01', 140000000), ('2026-08-01', 150000000),
    ('2026-09-01', 148000000), ('2026-10-01', 170000000);

-- 7.2 running total + moving average. 9 dan 10 Okt sengaja bolong (tidak ada baris)
-- untuk lihat bedanya frame ROWS vs hari kalender.
CREATE TABLE penjualan_harian (
    tanggal   date PRIMARY KEY,
    penjualan numeric(14,2) NOT NULL
);
INSERT INTO penjualan_harian (tanggal, penjualan)
SELECT d::date, 800000 + (extract(day FROM d)::int * 137000 % 600000)
FROM   generate_series('2026-10-01'::date, '2026-10-20'::date, '1 day') AS d
WHERE  d::date NOT IN ('2026-10-09', '2026-10-10');

-- 7.2 "3 transaksi terbaru per user" dan deteksi duplikat
CREATE TABLE users (
    id   int PRIMARY KEY,
    name text NOT NULL
);
INSERT INTO users VALUES (1, 'Ani'), (2, 'Bayu'), (3, 'Citra'), (4, 'Dimas');

CREATE TABLE orders (
    id         serial PRIMARY KEY,
    user_id    int NOT NULL REFERENCES users(id),
    total      numeric(12,2) NOT NULL,
    created_at timestamp NOT NULL
);
INSERT INTO orders (user_id, total, created_at)
SELECT u.id,
       50000 + ((u.id * g * 13700) % 200000),
       timestamp '2026-08-01' + ((u.id * 3 + g * 9) || ' days')::interval
FROM   users u, generate_series(1, 6) AS g;

-- cek cepat
SELECT 'categories' AS tabel, count(*) FROM categories
UNION ALL SELECT 'employees',        count(*) FROM employees
UNION ALL SELECT 'product_sales',    count(*) FROM product_sales
UNION ALL SELECT 'omzet_bulanan',    count(*) FROM omzet_bulanan
UNION ALL SELECT 'penjualan_harian', count(*) FROM penjualan_harian
UNION ALL SELECT 'orders',           count(*) FROM orders;
