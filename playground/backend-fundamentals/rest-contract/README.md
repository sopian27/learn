# REST Contract Demo (Lesson 5.1)

Dua gaya API di satu server kecil (JDK `HttpServer`, tanpa dependency): yang asal-asalan (`/createOrder`, selalu 200) vs yang konsisten (`/orders`, status code bermakna, pagination/filter/sort).

Run: `java RestContractDemo.java` (port 8089)

Yang diliat: baris pertama 200 padahal gagal; baris `/orders?sort=password` ditolak 400; `/orders/99` jadi 404; `DELETE` jadi 204; list punya `page`, `size`, `total`.
