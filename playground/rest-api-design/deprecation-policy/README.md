# Deprecation Policy Demo (Lesson 8.3)

Run: `java DeprecationPolicyDemo.java`

Look for: tiga fase hidup v1. Sebelum deprecate tidak ada header. Di masa migrasi response tetap 200, tapi membawa `Deprecation`, `Sunset`, dan `Link` ke panduan migrasi. Sesudah tanggal sunset jadi 410 dengan petunjuk ke v2. Klien yang memantau header punya waktu enam bulan untuk pindah.
