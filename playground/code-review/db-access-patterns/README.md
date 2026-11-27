# Lesson 4.1: N+1 dan database access patterns

```bash
java DbAccessDemo.java
```

Yang dilihat: jumlah query versi `before` naik seiring jumlah row (N+1), versi `after` tetap 2. Dengan 3 row bedanya hampir gak kerasa, makanya lolos di test.

DB-nya palsu (cuma ngitung query), dan angka ms cuma asumsi 2 ms per round trip.

Mau lihat sisi index dan `EXPLAIN` di PostgreSQL asli: `playground/sql-practice/module6-indexing-sample-data.sql`.
