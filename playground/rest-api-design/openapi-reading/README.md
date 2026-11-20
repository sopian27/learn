# OpenAPI Reading Demo (Lesson 10.1)

Run: `java OpenApiReadingDemo.java`

Look for: urutan baca spec = `paths` (endpoint apa saja) lalu ikuti `$ref` ke `components/schemas` (bentuk datanya). `GET /v1/orders/{id}` punya response 404 yang `$ref`-nya putus (`ProblemDetail` tidak ada, yang ada `Problem`). Itu tipe kesalahan yang bikin Swagger UI atau generator client error.
