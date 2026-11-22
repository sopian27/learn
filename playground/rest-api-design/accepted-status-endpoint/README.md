# 202 Accepted + Status Endpoint Demo (Lesson 13.2)

Run: `java AcceptedStatusDemo.java`

Lihat:
- Sync: balasan baru keluar setelah ~2000 ms.
- Async: `202` + `Location` + `Retry-After` keluar dalam ~0 ms.
- Polling: PENDING/PROCESSING (200 + Retry-After), lalu DONE (303 ke hasil).
- Job tidak ada: 404.

Simulasi in-memory + satu thread worker. Produksi: job disimpan di DB/queue (lihat 13.3).
