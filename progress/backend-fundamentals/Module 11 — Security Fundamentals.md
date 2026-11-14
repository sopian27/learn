# Security Fundamentals
  
  # Authentication vs Authorization: Spring Security filter chain, kenapa ini juga Chain of Responsibility pattern sesuai standards/SECURITY_STANDARDS.md

  AuthN = identitas (gagal 401), AuthZ = izin (gagal 403); keduanya harus ada dan urutannya AuthN dulu.

  Aturan Emas:
  Login itu cuma jawaban "lu siapa"; "lu boleh apa" harus dijawab terpisah di setiap pintu.

  Terapin:
  Pas review endpoint baru, tanya "kalau user biasa yang manggil ini, apa yang terjadi?" sebelum approve.

  # JWT & OAuth2/OIDC: cara kerja, pitfall umum (JWT di localStorage, validasi expiry/signature)

  JWT = header.payload.signature; payload cuma Base64, keamanan ada di signature.

  Validasi wajib: signature, exp, iss, aud, dan algoritma dikunci; jangan simpan token berumur panjang di localStorage.

  OAuth2 = delegasi akses, OIDC = identitas di atasnya; pakai IdP dan library resmi, bukan bikin sendiri.

  Aturan Emas: 
  Token itu surat bertanda tangan, bukan kebenaran; selalu cek tanda tangannya sebelum percaya isinya.

  Terapin: 
  Pas review kode filter auth, cari baris yang cuma decode payload dan tanya "signature-nya dicek di mana?"

  # Password & secret handling: hashing (BCrypt/Argon2), kenapa secret nggak boleh hardcoded

  Secret nggak boleh hardcoded atau ke-commit; simpan di env atau secret manager, dan rotate kalau pernah bocor.
  
  SHA-256 / MD5
  Password yang sama → hash selalu sama.
  Proses sangat cepat.
  Mudah dicoba berulang kali oleh hacker (brute force).

  BCrypt / Argon2 / scrypt (Hash Password)
  Password yang sama → hash bisa berbeda karena salt otomatis.
  Proses sengaja dibuat lambat.
  Cost factor mengatur tingkat kesulitan komputasi.

  Salt: angka acak unik per user, supaya password sama menghasilkan hash beda

  Cost / work factor: bikin satu hash sengaja mahal. User cuma nunggu sepersekian detik saat login, attacker nunggu ribuan kali lebih lama.

  Constant-time compare: bandingin hash tanpa bocor lewat waktu respons.

  Secret = apa pun yang kalau bocor, orang lain bisa nyamar jadi lu: password DB, API key, JWT secret, private key. 
  
  Aturan standar: 
  jangan hardcode dan jangan commit. Simpan di environment variable, secret manager, atau Kubernetes Secret dengan kontrol akses yang benar.

  jangan di lakukan:
  1. Hash password pakai SHA-256/MD5, atau bikin "salt" sendiri yang sama untuk semua user.
  2. Hardcode secret di application.properties atau kode, lalu mengira "hapus di commit berikutnya" cukup. Riwayat git tetap nyimpen.
  3. Nge-log request body yang berisi password, atau mencetak secret pas debugging.
  4. Jangan bikin algoritma hash sendiri atau "enkripsi password biar bisa dibalikin".
  5. Jangan pakai secret yang sama antar environment (dev, staging, prod).
  6. Kalau memang bisa login lewat IdP, jangan sok nyimpen password sendiri.

  Aturan Emas: 
  Password dan secret itu bukan data biasa; hash yang lambat dan simpan di luar kode, karena yang cepat dan ke-commit nggak bisa ditarik balik.

  Terapin: 
  Pas review PR, cari kata password, secret, apikey di diff dan tanya "ini dihash pakai apa, dan nilainya datang dari mana?"

  # OWASP Top 10 di konteks Spring Boot: SQL injection, broken access control, mass assignment

  Injection dicegah dengan parameter binding; JPA aman karena binding-nya, bukan namanya.
  
  Broken access control: setelah login tetap cek kepemilikan di server tiap akses (IDOR).
  
  Mass assignment: pisahin DTO dari entity dan biarin server yang nentuin nilai sensitif.

  jangan di lakukan:
  1. Mengira "pakai JPA berarti kebal injection", lalu nyambung string di @Query atau createNativeQuery.
  2. Cuma cek "sudah login" tanpa cek "ini data siapa".
  3. Meneruskan entity langsung sebagai @RequestBody.
  4. Membalas 403 untuk id orang lain, jadi attacker tahu id itu ada.
  5. Jangan nulis filter input buatan sendiri buat "membersihkan" SQL. Pakai parameter binding.
  6. Jangan jadiin daftar Top 10 satu-satunya checklist; celah bisnis tetap perlu dipikirin.
  7. Jangan nunda ke "fase hardening". Cek ini di code review tiap PR.

  Aturan Emas: 
  Login cuma membuktikan siapa lu; tiap akses dan tiap input tetap harus diperiksa dari awal, karena server nggak boleh percaya apa pun dari luar.

  Terapin: 
  Pas review endpoint, tanya tiga hal: "id ini punya siapa?", "field apa yang bisa diisi client?", dan "nilai ini masuk query lewat parameter atau concat?"

  # Input validation & sanitization, CORS yang benar (bukan * di production)

  Validasi input: allowlist, bukan blocklist. Tentukan apa yang boleh (tipe, panjang, format, rentang), tolak sisanya. Blocklist ("larang kata DROP") selalu ketinggalan satu trik.

  Sanitization dan escaping itu beda tugas. Validasi memutuskan input boleh masuk atau nggak. Escape mengubah data supaya aman di tempat tujuannya (HTML, SQL, shell, log). Escape dilakukan saat output, sesuai konteks.

  CORS: aturan browser, bukan firewall. CORS menentukan origin mana yang boleh dibaca responsnya oleh JavaScript di browser. Dia nggak menghentikan curl atau server lain, jadi bukan pengganti autentikasi.

  jangan di lakukan:
  1. Validasi cuma di frontend.
  2. Blocklist kata berbahaya alih-alih allowlist format.
  3. Mengira CORS melindungi API dari semua klien.
  4. allowedOrigins("*") yang ikut ke production, atau memantulkan header Origin apa adanya.
  5. Mengganti parameter binding dengan "escape manual" untuk SQL.
  6. Jangan bikin fungsi sanitize buatan sendiri yang "membersihkan semuanya". Pakai fitur framework.
  7. jangan jadiin validasi satu-satunya pertahanan; dia lapisan, bersama binding, escape, dan kontrol akses.
  8. Jangan pakai CORS buat "mengamankan" API. Autentikasi tetap di server.

  Aturan Emas: 
  Jangan percaya input dari luar sebelum dicek bentuknya; terima yang jelas sah, tolak sisanya, dan perlakukan setiap tujuan dengan aturan amannya sendiri.

  Terapin: 
  Pas review DTO atau config baru, tanya: "bentuk sah field ini apa, ditampilkan di mana, dan origin mana yang boleh manggil?"

