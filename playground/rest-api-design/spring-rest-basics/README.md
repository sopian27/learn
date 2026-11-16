# Spring REST Basics Demo (Lesson 3.1)

Run: `java SpringRestBasicsDemo.java`

Demo ini pakai JDK HttpServer (tanpa Spring) untuk meniru perilaku `@PathVariable` vs `@RequestParam`.

Yang diliat:

* `GET /orders/1` -> `200`. `GET /orders/99` -> `404`. Path variable = identitas, nggak ketemu berarti resource nggak ada.
* `GET /orders?status=CANCELLED` -> `200 []`. Query param = filter, hasil kosong itu valid, bukan error.
* `GET /orders` tanpa filter -> semua order. Query param itu opsional, path variable wajib.

---

# Lesson 3.2: `@RequestBody` dan `ResponseEntity`

Run: `java RequestBodyResponseEntityDemo.java`

Yang diliat:

* `POST /naive` -> `200`, tanpa `Location`. Ini hasil kalau method cuma `return order;`.
* `POST /orders` -> `201` + `Location: /orders/N`. Ini hasil `ResponseEntity.created(...)`.
* Body rusak atau kosong -> `400`. Meniru kegagalan binding `@RequestBody`.

## Lesson 3.3

Run: `java ContentNegotiationDemo.java`

Lihat: `Accept: application/xml` -> 200 XML; `Accept: text/csv` -> 406; `Content-Type: text/plain` pada POST -> 415; tanpa Accept -> JSON default.

## Lesson 3.4

Run: `java LayeredArchitectureDemo.java`

Lihat: controller gemuk -> job import menyalin aturan (qty=10 total 100000, qty=-3 lolos); controller tipis + service -> total sama 90000 dan qty=-3 ditolak.
