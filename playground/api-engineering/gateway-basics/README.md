# API Gateway basics demo

Run: `java GatewayBasicsDemo.java`

Lihat:
- [1] service dipanggil langsung: siapa saja bisa isi `X-User-Id`, tidak ada yang menahan.
- [2] gateway: tanpa token 401, token valid di-route ke service yang benar, path asing 404.
- [3] header `X-User-Id` palsu dari client dibuang, diganti identitas hasil verifikasi token; field internal `internalCost` hilang dari response.

Batas: semua di memori, token cuma peta statis (bukan JWT), tanpa TLS, tanpa timeout/retry. Gateway sungguhan (Kong, Spring Cloud Gateway) juga wajib dikunci supaya service tidak bisa diakses langsung di jaringan, kalau tidak [1] tetap terjadi.
