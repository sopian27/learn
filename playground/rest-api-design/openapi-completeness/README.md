# OpenAPI Completeness Demo (Lesson 10.4)

Run: `java OpenApiCompletenessDemo.java`

Look for: `GET /v1/orders/{id}` lolos secara teknis (ada path, ada 200) tapi gagal lint kelengkapan: tanpa error, contoh, auth, dan rate limit. `POST /v1/orders` lolos. Di dunia nyata ini dikerjakan Spectral/Redocly di CI terhadap `openapi.yaml`.
