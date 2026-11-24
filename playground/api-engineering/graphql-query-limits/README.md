# GraphQL Query Limits (Lesson 3.4)

Jalankan: `java QueryLimitsDemo.java` (Java 17+, tanpa library).

Yang diliat:
- Query normal (`orders(first:20) { items(first:10) }`) lolos: depth 3, cost 440.
- Query dangkal tapi lebar (`first:100` x `first:100`) cuma depth 3, tapi kena batas complexity. Depth limit saja nggak cukup.
- Query dalam (siklus `orders -> customer -> orders`) kena batas depth.
- Ubah `MAX_DEPTH` / `MAX_COMPLEXITY` dan lihat query mana yang ikut ditolak.
