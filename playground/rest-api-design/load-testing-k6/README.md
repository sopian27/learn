# Load testing dasar (Lesson 12.5)

## Demo Java (sudah dijalankan)
`java LoadTestDemo.java` — simulasi 50 virtual user x 20 request ke DB palsu (pool 10 koneksi, 2 ms/query).
Bandingkan N+1 (21 query/request) vs batch (2 query/request): lihat avg, p95, p99, throughput.

Yang dilihat: p95/p99 jauh di atas avg; throughput naik setelah query dikurangi.
Catatan: ini simulasi thread + sleep, bukan server HTTP asli. Angka bergantung mesin dan scheduler OS.

## Skrip k6 (belum dijalankan di sini)
`k6 run orders.js` — butuh k6 terpasang dan `GET /orders` hidup di localhost:8080.
Ramp up 30s ke 50 VU, tahan 1 menit, turun 10s. Threshold: p95 < 500 ms, error < 1%.
