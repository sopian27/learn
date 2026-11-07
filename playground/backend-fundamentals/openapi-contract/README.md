# OpenAPI Contract Demo (Lesson 5.4)

Jalankan: `java OpenApiContractDemo.java` (port 8092)

Yang diliat: kontrak `POST /orders` (201, `id` number, `currency` string) dipakai sebagai patokan.
- `/drifted/orders`: implementasi yang berubah tanpa spec ikut berubah (200, `id` jadi string, `currency` hilang). Checker nandain 3 pelanggaran.
- `/conforming/orders`: implementasi yang ngikutin kontrak, lolos.

Spec di sini ditulis ringkas ala OpenAPI dan cek-nya manual; di proyek asli pakai file `openapi.yaml` + contract test / validator (mis. Spring Cloud Contract, Schemathesis, atau springdoc untuk bandingin).
