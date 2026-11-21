# Failure Scenarios Demo (Lesson 11.4)

Plain Java, tanpa Spring. Mensimulasikan endpoint `GET /orders/{id}` dan `GET /orders?page&size` dengan dua versi: versi awal (cuma happy path yang dipikirkan) dan versi fix.

## Jalankan

```
java FailureScenariosDemo.java
```

## Yang perlu dilihat

* Versi awal: "tanpa token" dan "order milik orang lain" balik `200` (bocor), `size=0` balik `500`. Tiga FAIL, tapi test happy path tetap PASS semua.
* Versi fix: semua PASS. Order orang lain dibalas `404`, bukan `403`, supaya id tidak bisa ditebak.
* Halaman terakhir dan halaman melewati data sengaja PASS di kedua versi: edge case yang sudah benar tetap perlu dikunci supaya refactor tidak merusaknya.

## Mapping ke dunia nyata

Tiap baris `check(...)` = satu test method. `getOrder(...)` = endpoint di `@WebMvcTest` (11.2) atau `@SpringBootTest` (11.3). Ini simulasi dalam memori, bukan test Spring sungguhan.
