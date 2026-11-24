# Quota per Client & Tier Demo (Lesson 2.3)

Run: `java QuotaTierDemo.java`

Lihat:
- `global`: satu counter untuk semua. Client free yang rakus menghabiskan jatah, client paid dapat 0.
- `per client`: counter per apiKey, limit dari tier (free 5, paid 20). Free dibatasi 5, paid lolos semua 10.

Simulasi in-memory satu window, bukan Redis/gateway asli. Di kode ada self-check; kalau hasil meleset program melempar AssertionError.
