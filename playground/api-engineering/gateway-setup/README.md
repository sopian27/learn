# Gateway setup demo

Run: `java GatewaySetupDemo.java`

Lihat:
- [1] route table: path `/api/orders/42` cocok route `orders`, prefix `/api` dibuang (StripPrefix), service dapat `/orders/42`. Path tak dikenal 404.
- [2] request ID disuntik gateway, beda tiap request.
- [3] route `reports` lambat (500ms) kena timeout 200ms jadi 504; route `orders` tetap 200.

Batas: timeout di sini cuma dicek setelah service selesai (service tetap jalan sampai habis). Gateway sungguhan memutus koneksi saat timeout lewat. Tanpa load balancing, TLS, retry.
