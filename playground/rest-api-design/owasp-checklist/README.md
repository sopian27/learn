# OWASP API Top 10 checklist demo

Companion Lesson 9.5 (rest-api-design). Tiga cek otomatis: shadow endpoint (API9), field sensitif bocor (API3), SSRF allowlist (API7).

Jalankan: `java OwaspChecklistDemo.java`

Yang dicari: `GET /v1/users` ketahuan sebagai endpoint tak terdokumentasi, `passwordHash` ketahuan bocor, URL metadata cloud (`169.254.169.254`) ditolak, diakhiri `OK`.
