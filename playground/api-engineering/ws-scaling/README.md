# WebSocket scaling demo

Run: `java WsScalingDemo.java`

Lihat:
- [1] publish di instance B tidak sampai ke klien di instance A, tanpa error.
- [2] dengan bus pub/sub (tiruan Redis), kedua klien dapat pesan.
- [3] sticky session: klien yang sama selalu ke instance yang sama.

Batas: bus tiruan di memori, tanpa socket, tanpa reconnect, tanpa autentikasi. Redis pub/sub asli juga tidak menyimpan pesan (pesan hilang kalau instance sedang putus), dan hashing di [3] hanya ilustrasi, bukan cara load balancer sungguhan.
