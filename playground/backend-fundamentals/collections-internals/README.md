# Collections internals demo (Lesson 1.2)

Run: `java CollectionsInternalsDemo.java`

Lihat:
- `get setelah mutasi = null` tapi `size = 1`: key diubah setelah masuk `HashMap`, entry nyangkut di bucket lama.
- `broken contains = false`: `equals` tanpa `hashCode`. Versi `record` jalan benar.
- `HashSet size = 2` vs `TreeSet size = 1`: `BigDecimal("2.0")` vs `"2.00"`, beda `equals` tapi sama `compareTo`.
- Waktu `LinkedList.get(i)` jauh di atas `ArrayList` (angka bervariasi per mesin).
