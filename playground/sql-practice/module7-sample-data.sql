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

-- ===== Contoh query Lesson 7.1 (uncomment satu per satu) =====

-- Non-recursive: kategori dengan penjualan di atas rata-rata (harusnya Smartphone 3800, Laptop 3500)
WITH penjualan_kategori AS (
    SELECT category_id, SUM(total_sold) AS total FROM product_sales GROUP BY category_id
),
rata_rata AS (SELECT AVG(total) AS avg_total FROM penjualan_kategori)
SELECT c.name, p.total
FROM   penjualan_kategori p
JOIN   categories c ON c.id = p.category_id
CROSS  JOIN rata_rata r
WHERE  p.total > r.avg_total
ORDER  BY p.total DESC;

name      |total|
----------+-----+
Smartphone| 3800|
Laptop    | 3500|

-- Recursive: pohon kategori + level
WITH RECURSIVE pohon AS (
    SELECT id, name, parent_id, 0 AS level FROM categories WHERE parent_id IS NULL
    UNION ALL
    SELECT c.id, c.name, c.parent_id, p.level + 1
    FROM   categories c JOIN pohon p ON c.parent_id = p.id
    WHERE  p.level < 10
)
SELECT * FROM pohon ORDER BY level, name;

id|name         |parent_id|level|
--+-------------+---------+-----+
 1|Elektronik   |         |    0|
 2|Fashion      |         |    0|
 3|Rumah Tangga |         |    0|
 9|Dapur        |        3|    1|
 4|Laptop       |        1|    1|
 7|Pria         |        2|    1|
 5|Smartphone   |        1|    1|
 8|Wanita       |        2|    1|
 6|Gaming Laptop|        4|    2|

-- Recursive: total penjualan Elektronik + semua sub-kategori (harusnya 7300)
WITH RECURSIVE sub AS (
    SELECT id FROM categories WHERE id = 1
    UNION ALL
    SELECT c.id FROM categories c JOIN sub s ON c.parent_id = s.id
)
SELECT SUM(ps.total_sold) FROM product_sales ps JOIN sub ON ps.category_id = sub.id;

sum |
----+
7300|

-- Recursive: bawahan Sari (CTO) di org chart
WITH RECURSIVE bawahan AS (
    SELECT id, name, manager_id FROM employees WHERE id = 2
    UNION ALL
    SELECT e.id, e.name, e.manager_id FROM employees e JOIN bawahan b ON e.manager_id = b.id
)
SELECT * FROM bawahan;

id|name          |manager_id|
--+--------------+----------+
 2|Sari (CTO)    |         1|
 4|Dewi (Eng Mgr)|         2|
 5|Rudi (Backend)|         4|
 6|Lina (Backend)|         4|

-- ===== Contoh query Lesson 7.2 (uncomment satu per satu) =====

-- Top-2 per kategori pakai RANK: Laptop B dan C (sama-sama 800) harusnya sama-sama tampil
WITH ranked AS (
    SELECT category_id, name, total_sold,
           RANK() OVER (PARTITION BY category_id ORDER BY total_sold DESC) AS rnk
    FROM   product_sales
)
SELECT * FROM ranked WHERE rnk <= 2 ORDER BY category_id, rnk, name;

category_id|name    |total_sold|rnk|
-----------+--------+----------+---+
          4|Laptop A|       900|  1|
          4|Laptop B|       800|  2|
          4|Laptop C|       800|  2|
          5|HP A    |      1500|  1|
          5|HP B    |      1200|  2|
          7|Kemeja  |       640|  1|
          7|Celana  |       610|  2|
          8|Dress   |       720|  1|
          8|Rok     |       720|  1|
          9|Panci   |       300|  1|
          9|Wajan   |       250|  2|

-- Bandingkan ROW_NUMBER / RANK / DENSE_RANK di kategori Laptop (id 4)
SELECT name, total_sold,
       ROW_NUMBER() OVER (ORDER BY total_sold DESC) AS row_num,
       RANK()       OVER (ORDER BY total_sold DESC) AS rnk,
       DENSE_RANK() OVER (ORDER BY total_sold DESC) AS dense_rnk
FROM   product_sales WHERE category_id = 4;

name    |total_sold|row_num|rnk|dense_rnk|
--------+----------+-------+---+---------+
Laptop A|       900|      1|  1|        1|
Laptop B|       800|      2|  2|        2|
Laptop C|       800|      3|  2|        2|
Laptop D|       700|      4|  4|        3|
Laptop E|       300|      5|  5|        4|

-- LAG: omzet vs bulan lalu (Mei harusnya selisih negatif)
SELECT bulan, omzet,
       LAG(omzet) OVER (ORDER BY bulan)         AS omzet_bulan_lalu,
       omzet - LAG(omzet) OVER (ORDER BY bulan) AS selisih
FROM   omzet_bulanan;

bulan     |omzet       |omzet_bulan_lalu|selisih     |
----------+------------+----------------+------------+
2026-01-01|100000000.00|                |            |
2026-02-01|110000000.00|    100000000.00| 10000000.00|
2026-03-01|125000000.00|    110000000.00| 15000000.00|
2026-04-01|130000000.00|    125000000.00|  5000000.00|
2026-05-01| 95000000.00|    130000000.00|-35000000.00|
2026-06-01|120000000.00|     95000000.00| 25000000.00|
2026-07-01|140000000.00|    120000000.00| 20000000.00|
2026-08-01|150000000.00|    140000000.00| 10000000.00|
2026-09-01|148000000.00|    150000000.00| -2000000.00|
2026-10-01|170000000.00|    148000000.00| 22000000.00|

-- Running total + moving average 7 baris (9 dan 10 Okt bolong)
SELECT tanggal, penjualan,
       SUM(penjualan) OVER (ORDER BY tanggal) AS running_total,
       AVG(penjualan) OVER (ORDER BY tanggal ROWS BETWEEN 6 PRECEDING AND CURRENT ROW) AS rata_rata_7_baris
FROM   penjualan_harian;

tanggal   |penjualan |running_total|rata_rata_7_baris   |
----------+----------+-------------+--------------------+
2026-10-01| 937000.00|    937000.00| 937000.000000000000|
2026-10-02|1074000.00|   2011000.00|1005500.000000000000|
2026-10-03|1211000.00|   3222000.00|1074000.000000000000|
2026-10-04|1348000.00|   4570000.00|1142500.000000000000|
2026-10-05| 885000.00|   5455000.00|1091000.000000000000|
2026-10-06|1022000.00|   6477000.00|1079500.000000000000|
2026-10-07|1159000.00|   7636000.00|1090857.142857142857|
2026-10-08|1296000.00|   8932000.00|1142142.857142857143|
2026-10-11|1107000.00|  10039000.00|1146857.142857142857|
2026-10-12|1244000.00|  11283000.00|1151571.428571428571|
2026-10-13|1381000.00|  12664000.00|1156285.714285714286|
2026-10-14| 918000.00|  13582000.00|1161000.000000000000|
2026-10-15|1055000.00|  14637000.00|1165714.285714285714|
2026-10-16|1192000.00|  15829000.00|1170428.571428571429|
2026-10-17|1329000.00|  17158000.00|1175142.857142857143|
2026-10-18| 866000.00|  18024000.00|1140714.285714285714|
2026-10-19|1003000.00|  19027000.00|1106285.714285714286|
2026-10-20|1140000.00|  20167000.00|1071857.142857142857|

-- 3 transaksi terbaru per user
WITH t AS (
    SELECT o.*, ROW_NUMBER() OVER (PARTITION BY user_id ORDER BY created_at DESC, id DESC) AS rn
    FROM   orders o
)
SELECT * FROM t WHERE rn <= 3 ORDER BY user_id, rn;

id|user_id|total    |created_at             |rn|
--+-------+---------+-----------------------+--+
21|      1|132200.00|2026-09-27 00:00:00.000| 1|
17|      1|118500.00|2026-09-18 00:00:00.000| 2|
13|      1|104800.00|2026-09-09 00:00:00.000| 3|
22|      2|214400.00|2026-09-30 00:00:00.000| 1|
18|      2|187000.00|2026-09-21 00:00:00.000| 2|
14|      2|159600.00|2026-09-12 00:00:00.000| 3|
23|      3| 96600.00|2026-10-03 00:00:00.000| 1|
19|      3| 55500.00|2026-09-24 00:00:00.000| 2|
15|      3|214400.00|2026-09-15 00:00:00.000| 3|
24|      4|178800.00|2026-10-06 00:00:00.000| 1|
20|      4|124000.00|2026-09-27 00:00:00.000| 2|
16|      4| 69200.00|2026-09-18 00:00:00.000| 3|