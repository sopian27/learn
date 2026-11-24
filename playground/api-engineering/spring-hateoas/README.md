# Spring HATEOAS Demo (Lesson 1.2)

Run: `mvn spring-boot:run`

Lalu: `curl localhost:8080/orders/1` sampai `/orders/4` (PENDING, PAID, SHIPPED, CANCELLED).

Lihat:
- Link `cancel` hanya muncul di PENDING, `refund` hanya di PAID.
- Format HAL (`_links`) dipilih otomatis lewat `Accept: application/hal+json` (default Spring Boot).
- Status dummy dari id, tanpa DB. Demo, bukan production.
