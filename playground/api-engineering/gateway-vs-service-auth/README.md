# Gateway vs service: auth & rate limit

Run: `java GatewayVsServiceAuthDemo.java`

Lihat:
- [1] token valid lolos gateway, tapi Rani membaca order Akbar. Gateway tidak tahu siapa pemilik order, itu data domain.
- [2] service cek kepemilikan: 403 untuk order orang lain, 200 untuk order sendiri.
- [3] rate limit per client di gateway: request ke-4 dapat 429.

Batas: token cuma peta statis, limiter fixed-window in-memory satu instance (gateway multi-instance butuh store bersama seperti Redis, lihat lesson rate limiting). Tidak ada JWT, scope, atau role.
