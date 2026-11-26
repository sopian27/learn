# Security-Focused Review

   # Injection, broken auth, sensitive data exposure dalam diff

   SQL Injection
      Kode bermasalah:
      String sql = "SELECT * FROM users WHERE name = '" + name + "'";

      Perbaikan:
      String sql = "SELECT * FROM users WHERE name = ?";
      jdbcTemplate.query(sql, rowMapper, name);

      Review: Input user jangan digabung langsung ke SQL.

   ORDER BY dari Request
      Kode bermasalah:
      String sql = """
         SELECT * FROM users ORDER BY %s
         """.formatted(sortBy);

      Perbaikan:
      Set<String> allowed = Set.of("name", "created_at");

      if (!allowed.contains(sortBy)) {
         throw new IllegalArgumentException("Invalid sort");
      }

      Review: Nama kolom harus menggunakan whitelist.

   IDOR / BOLA
      Kode bermasalah:
      return orderRepository.findById(id);

      Perbaikan:
      return orderRepository.findByIdAndUserId(
         id, currentUser.getId()
      );

      Review: Pastikan user hanya mengakses resource yang menjadi haknya.
   
   permitAll()
      Kode bermasalah:
      .requestMatchers("/api/admin/**").permitAll()

      Perbaikan:
      .requestMatchers("/api/admin/**").hasRole("ADMIN")

      Review: Jangan membuka endpoint sensitif untuk semua orang.

   Matcher Security Baru
      Kode bermasalah:
      .requestMatchers("/api/**").permitAll()

      Perbaikan:
      .requestMatchers("/api/public/**").permitAll()
      .requestMatchers("/api/admin/**").hasRole("ADMIN")
      .anyRequest().authenticated()

      Review: Perhatikan urutan matcher dan cakupan aksesnya.
   
   CSRF Dimatikan
      Kode berisiko:
      http.csrf(csrf -> csrf.disable());

      Review: Apakah aplikasi menggunakan session/cookie authentication atau bearer token?

      Jangan mematikan CSRF tanpa memahami mekanisme autentikasinya. CSRF terutama relevan ketika browser otomatis mengirim credential seperti session cookie.

   JWT Hanya di-Decode
      Kode bermasalah:
      String[] parts = token.split("\\.");
      String payload = new String(
         Base64.getUrlDecoder().decode(parts[1])
      );

      Perbaikan:
      Jwts.parser()
         .verifyWith(secretKey)
         .build()
         .parseSignedClaims(token);

      Review: Pastikan signature, expiration, issuer, dan audience divalidasi sesuai kebutuhan.

   Log Object Utuh
      Kode bermasalah:
      log.info("Login request: {}", request);

      Perbaikan:
      log.info("Login attempt: {}", request.getUsername());

      Review: Jangan sampai password, token, atau informasi sensitif masuk log.

   Entity Langsung Menjadi Response
      Kode bermasalah:
      @GetMapping("/{id}")
      public User getUser(@PathVariable Long id) {
         return userRepository.findById(id).orElseThrow();
      }

      Perbaikan:
      public record UserResponse(
         Long id,
         String username,
         String email
      ) {}

      Review: Gunakan DTO untuk mengontrol field yang boleh keluar.
   
   File Path dari Request
      Kode bermasalah:
      File file = new File("/uploads/" + filename);
      return Files.readAllBytes(file.toPath());

      Perbaikan:
      Path base = Paths.get("/uploads").toRealPath();
      Path target = base.resolve(filename).normalize();

      if (!target.startsWith(base)) {
         throw new SecurityException("Invalid path");
      }

      Review: Pastikan user tidak bisa mengakses file di luar direktori yang diizinkan.

   Command Injection
      Kode bermasalah:
      Runtime.getRuntime().exec(
         "ping " + hostname
      );

      Perbaikan:
      new ProcessBuilder(
         "ping", "-c", "1", validatedHostname
      ).start();

      Review: Hindari shell command dengan string concatenation. Validasi input sesuai format yang diizinkan.

   XSS (Cross-Site Scripting)
      Kode bermasalah:
      return "<h1>Hello " + username + "</h1>";

      Perbaikan:

      Gunakan template engine dengan automatic HTML escaping atau lakukan contextual output encoding.

      Review: Jangan memasukkan input user langsung ke HTML tanpa encoding yang sesuai konteks.
   
   Exception Information Disclosure
      Kode bermasalah:
      catch (Exception e) {
         return ResponseEntity.internalServerError()
            .body(e.getMessage());
      }

      Perbaikan:
      catch (Exception e) {
         log.error("Failed to process request", e);

         return ResponseEntity.internalServerError()
            .body("Internal server error");
      }

      Review: Jangan bocorkan SQL, database hostname, internal path, atau stack trace ke client.

   Endpoint Baru Tanpa Authorization
      Kode bermasalah:
      @GetMapping("/admin/reports")
      public List<Report> getReports() {
         return reportService.findAll();
      }

      Perbaikan:
      @PreAuthorize("hasRole('ADMIN')")
      @GetMapping("/admin/reports")
      public List<Report> getReports() {
         return reportService.findAll();
      }

      Review: Pastikan method security aktif dan aturan authorization benar-benar diterapkan.

   jangan di lakukan:
   1. "Kan internal service", jadi cek otorisasi dilewat
   2. Nge-escape atau blacklist karakter manual, padahal bisa parameter binding.
   3. Komentar "ini gak aman" tanpa nunjukin skenario serangan dan usul fix.
   4. Temuan celah di sistem yang udah live jangan dibahas di channel rame; lapor lewat jalur security tim lu.

   Tes Feynman: jangan biarin tulisan orang jadi perintah, jangan kasih data tanpa cek dia berhak, jangan tulis rahasia di tempat umum.

   Aturan Emas: Udah dikenal belum tentu berhak.

   Terapin: tiap liat endpoint nerima id, tanya "kalau id-nya gue ganti punya orang lain, apa yang nahan?"

   # Input validation & sanitization checklist
   
   validation : tolak yang gak sesuai aturan
   sanitazion : ubah input biar aman
   output encoding : amanin saat dipakai di tujuan

   Checklist per field di diff:

      Wajib : null, kosong, spasi doang?
      Batas : Min, max, panjang, jumlah elemen list?
      Format : Enum atau allowlist, bukan blacklist?
      Nested : Object di dalam list ikut @Valid?
      Antar field : startDate sebelum endDate?
      Bisnis : Produknya ada, punya user ini, statusnya boleh?
      Field liar : Client bisa ngisi price, role, status?

   Untuk path file, normalize dulu baru cek:

      Path file = baseDir.resolve(name).normalize();
      if (!file.startsWith(baseDir)) throw new IllegalArgumentException("invalid file");
   
   baseDir harus absolute dan udah di-normalize. Kalau foldernya bisa berisi symlink, cek pakai toRealPath().

   jangan di lakukan:
   1. Ngandelin validasi di frontend. Siapa aja bisa manggil API langsung.
   2. Blacklist karakter "berbahaya" lalu ngerasa aman.
   3. Pesan error atau log yang mantulin input mentah.
   4. jangan nuntut validasi ulang di tiap method internal yang inputnya udah dijamin di pintu masuk.

   Tes Feynman: bentuknya bener belum tentu isinya masuk akal, jadi tentuin batasnya dan tolak yang di luar.
      
   Aturan Emas: Tulis apa yang boleh, jangan nebak semua yang gak boleh.

   Terapin: tiap liat field baru di PR, tanya "nilai terkecil dan terbesar yang sah berapa, dan di mana ditegakkannya?"

   # Secrets & credential handling

   secret sebaiknya beda per environment dan hak aksesnya sesempit mungkin. Key dev yang bocor jangan sampai bisa nyentuh production.

   real world:
   GitHub sekarang punya secret scanning dan push protection buat nahan token sebelum masuk. Startup bisa mulai dari scanner di pre-commit dan CI; perusahaan besar biasanya nambah credential berumur pendek dan rotasi otomatis.

   jangan di lakukan:
   1. Ngerasa aman karena repo-nya private.
   2. Hapus di commit baru tanpa rotate.
   3. Naruh default asli di config: ${DB_PASSWORD:rahasia123}.
   4. jangan bahas nilai atau detail secret yang bocor di thread terbuka. Lapor lewat jalur security tim.

   Tes Feynman: rahasia yang pernah kelihatan harus dianggap sudah diketahui orang lain.

   Aturan Emas: Sekali bocor, ganti kuncinya, jangan cuma hapus jejaknya.

   Terapin: nemu credential di PR, minta rotate dulu sebelum ngomongin cara ngerapiin commit.

   # Dependency/supply-chain awareness saat review

   Kode dependency jalan dengan hak akses yang sama kayak kode tim lu, jadi nambah dependency itu sama dengan nerima kode orang lain tanpa baca.

   | Pemeriksaan | Intinya |
   |---|---|
   | Perlu | Benar-benar dibutuhkan atau bisa pakai fitur bawaan? |
   | Asal | Library berasal dari publisher yang benar? |
   | Kesehatan | Masih aktif dirawat? |
   | Versi | Versi dipin dan tidak menggunakan SNAPSHOT sembarangan? |
   | Transitive | Dependency lain apa saja yang ikut terbawa? |
   | Kerentanan | Ada CVE yang diketahui? |
   | Lisensi | Boleh digunakan secara legal di produk kita? |
   | Scope | Dependency test tidak ikut masuk runtime? |

   jangan di lakukan:
   1. Cuma review kode Java, pom.xml dilewat.
   2. Ngerasa aman karena library-nya populer atau scanner-nya ijo.
   3. Approve PR bot tanpa baca changelog versi major.

   Tes Feynman: kode orang yang lu ambil jadi tanggung jawab lu, termasuk yang ikut di belakangnya.

   Aturan Emas: Cek apa yang ikut masuk, bukan cuma apa yang lu undang.

   Terapin: tiap ada dependency baru di PR, minta diff dependency:tree dan satu kalimat alasan kenapa perlu.

