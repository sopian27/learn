# Async Processing Demo (Lesson 13.3)

Run: `java AsyncProcessingDemo.java`

Lihat:
- At-least-once: pesan yang sama datang 2x. Worker naif menagih 2x, worker idempotent 1x.
- State machine job menolak transisi ilegal (DONE -> PROCESSING).
- Webhook: signature HMAC-SHA256 valid untuk body asli, ditolak kalau body diubah.

Simulasi satu thread tanpa broker asli. Produksi: queue (Kafka/RabbitMQ/SQS), update state atomik di DB, retry webhook dengan backoff.
