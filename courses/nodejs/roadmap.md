# Course Roadmap

## Course Information

* Course Name: Node.js — dari 0 sampai Expert
* Category: Backend Engineering / JavaScript Runtime (stack kedua, di luar Java/Spring Boot)
* Difficulty: Level 0 (belum pernah menulis JavaScript sama sekali, tapi sudah mahir Java/Spring Boot production) → Level 4 (Expert, mampu merancang, membangun, men-debug, dan mengoperasikan REST API Node.js production tanpa panduan)
* Estimated Duration: 10 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Tidak perlu JavaScript sebelumnya — course ini mengajarkan JavaScript dari nol di Modul 1, tapi **bukan** dengan asumsi "belum pernah programming". Karena background Java/Spring Boot sudah kuat, Modul 1 sengaja tidak mengulang konsep pemrograman generik (variable, function, loop, OOP dasar) yang sudah dikuasai — fokusnya adalah "apa yang beda dari Java": closures & scoping tanpa block-scoping strict seperti Java, `this` yang dinamis, prototype-based inheritance vs class-based Java, dan yang paling penting: async/await serta single-threaded event loop menggantikan mental model thread-per-request JVM. Familiar dengan PostgreSQL dan Redis (dari repo ini) dipakai langsung di Modul 5, tidak diajarkan dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini adalah **course JavaScript/Node.js runtime pertama** di repo ini — tidak ada course lain yang tumpang tindih dari sisi bahasa/runtime, tapi tetap ada beberapa batas scope yang perlu jelas sejak awal:

1. **Bukan course frontend.** `courses/react/roadmap.md` sudah ada dan mengajarkan React + TypeScript sebagai fullstack track terpisah. Course ini murni **backend Node.js** (server, API, database) — tidak menyentuh DOM, komponen UI, atau bundler frontend (Vite/Webpack) sama sekali. Kalau nanti butuh Node.js sebagai *tooling* di balik React (build script, SSR), itu domain `courses/react`, bukan di sini.
2. **Beda dari `courses/backend-fundamentals` dan `courses/api-engineering`.** Kedua course itu adalah jalur inti Master Goal "World-Class Backend Developer" dan **berbasis Java/Spring Boot** (REST production-grade, gRPC, GraphQL, API gateway, dsb). Course ini **tidak mengulang teori desain API** yang sudah dalam di sana (idempotency, versioning strategy, rate-limiting theory, kontrak API) — Modul 4 course ini fokus ke *implementasi* REST API dengan Express di runtime yang berbeda (Node vs JVM), bukan mengajarkan ulang kapan pakai REST vs gRPC. Anggap ini sebagai "port" kemampuan backend yang sudah dimiliki ke stack JavaScript, bukan kursus desain API dari nol.
3. **Beda dari `courses/cybersecurity`.** Modul 6 course ini hanya membahas security *level aplikasi Node* yang wajib (JWT, session, bcrypt, OWASP basics secukupnya untuk implementasi) — untuk threat modeling STRIDE dan security code review mendalam, Modul 6.4 merujuk balik ke `courses/cybersecurity`, tidak mengulang.
4. **Beda dari `courses/vps`.** Modul 10 course ini membahas *cara mengemas* aplikasi Node untuk production (containerization, env config, health check, structured logging) tapi **mekanika deploy ke server sungguhan** (SSH, nginx reverse proxy, systemd, HTTPS/certbot) sepenuhnya merujuk ke `courses/vps` — tidak diduplikasi. PM2/systemd sebagai process manager Node dibahas di sini karena spesifik ke runtime Node, bukan generik server ops.
5. **PostgreSQL & Redis tidak diajarkan dari nol.** Repo ini sudah punya familiarity kuat dengan keduanya dari sisi Java/Spring Boot (`courses/backend-fundamentals` Modul 6 & 9). Modul 5 course ini hanya mengajarkan *cara mengaksesnya dari Node* (driver `pg`, ORM Prisma, client `ioredis`, connection pooling ala Node) — bukan konsep database itu sendiri.
6. **Track paralel/independen dari Master Goal**, sama seperti `courses/vps` — sangat applied, langsung dipakai untuk membangun portofolio berbasis Node.js atau membuktikan kemampuan "polyglot backend engineer" ke klien freelance yang butuh stack JavaScript, bukan Java.

Referensi riset yang dipakai menyusun roadmap ini:

* [roadmap.sh — Node.js Developer Roadmap](https://roadmap.sh/nodejs) — kerangka topik resmi (JavaScript prerequisite, module system CJS/ESM, npm, async programming, Express/Fastify/NestJS, testing dengan Jest, database, threading/cluster) dipakai sebagai kerangka urutan modul.
* [Node.js Official Docs — Event Loop, Timers, and process.nextTick()](https://nodejs.org/en/learn/asynchronous-work/event-loop-timers-and-nexttick) dan [libuv documentation](https://docs.libuv.org/) — dipakai sebagai kerangka Modul 2 (internals event loop, non-blocking I/O), sumber resmi paling akurat untuk perbedaan dari model thread-per-request JVM.
* [Express.js official documentation](https://expressjs.com) — khususnya Guide: [Routing](https://expressjs.com/en/guide/routing.html), [Writing Middleware](https://expressjs.com/en/guide/writing-middleware.html), dan [Error Handling](https://expressjs.com/en/guide/error-handling.html) — dipakai sebagai kerangka Modul 4.
* [Jest — Getting Started](https://jestjs.io/docs/getting-started) dan dokumentasi [supertest](https://github.com/ladjs/supertest) — dipakai sebagai kerangka Modul 7 (unit test + integration test HTTP layer).
* Dokumentasi resmi [node-postgres (`pg`)](https://node-postgres.com/), [Prisma](https://www.prisma.io/docs), dan [ioredis](https://github.com/redis/ioredis) — dipakai sebagai kerangka Modul 5.

---

# Learning Objectives

* [ ] Memahami JavaScript untuk backend secara solid: closures, scope, `this`, async/await, Promise — dari sudut pandang "apa yang beda dari Java", bukan dari nol total
* [ ] Memahami internals runtime Node.js: event loop, libuv, non-blocking I/O, dan kapan single-threaded event loop unggul/kalah dibanding model thread-per-request JVM
* [ ] Mahir mengelola project Node dengan npm: `package.json`, semver, scripts, workspaces, dan struktur project yang idiomatis
* [ ] Mampu membangun REST API production-grade dengan Express: routing, middleware, validation, error handling terpusat
* [ ] Mampu mengakses PostgreSQL (via `pg`/Prisma) dan Redis (via `ioredis`) dari Node dengan connection pooling yang benar
* [ ] Mampu mengimplementasikan autentikasi (JWT, session) dan praktik keamanan dasar OWASP di aplikasi Node
* [ ] Mahir menulis test (unit dengan Jest/Vitest, integration API dengan supertest) untuk aplikasi Node
* [ ] Memahami pola async lanjut (event emitter, stream, worker threads, cluster) dan error handling yang tidak silent-fail di production
* [ ] Mampu menyiapkan aplikasi Node untuk production: clustering, process manager (PM2/systemd), structured logging, profiling dasar
* [ ] Menyelesaikan proyek portofolio: REST API Node.js production-grade dengan auth, database, test, dan dokumentasi perbandingan arsitektur ke Spring Boot

---

# Course Modules

## Module 1 — JavaScript Essentials untuk Backend Developer (dari Java ke JavaScript)

Description: Bukan "belajar programming dari nol" — ini adalah jembatan cepat dari mental model Java ke JavaScript, fokus ke bagian yang benar-benar beda dan sering jadi sumber bug bagi developer Java yang pindah ke Node.

Lessons:

* [ ] 1.1 Sintaks & tipe data JavaScript secukupnya (var/let/const, tipe primitif, truthy/falsy) — dengan highlight eksplisit tempat JavaScript berbeda dari Java (no static typing, coercion, `==` vs `===`)
* [ ] 1.2 Function, closures, dan scope: kenapa closures terasa asing datang dari Java (tidak ada `final` implisit, lexical scoping), `this` yang dinamis vs `this` Java yang selalu jelas
* [ ] 1.3 Prototype-based inheritance vs class-based Java: `class` di JavaScript hanyalah syntactic sugar di atas prototype chain — apa artinya untuk desain kode
* [ ] 1.4 Asynchronous programming: callback → Promise → async/await, event loop sebagai pengganti thread pool JVM, kenapa `await` yang lupa ditulis adalah bug diam-diam
* [ ] 1.5 Module system: CommonJS (`require`) vs ES Modules (`import`) — sejarah, perbedaan, dan cara memilih untuk project baru

Mini Project: Port sebuah utility class kecil dari Java (mis. validator/formatter sederhana) ke JavaScript murni (Node, tanpa framework), lengkap dengan async version-nya, dan tulis catatan poin-poin yang terasa paling berbeda dari Java.

Status: Not Started

---

## Module 2 — Node.js Runtime & Event Loop Internals

Description: Memahami *kenapa* Node.js berperforma seperti itu — bukan menghafal istilah "non-blocking I/O", tapi mengerti mekanismenya cukup dalam untuk men-debug masalah performa nanti.

Lessons:

* [ ] 2.1 Apa itu Node.js: V8 engine, libuv, arsitektur single-threaded event loop untuk JavaScript tapi multi-threaded di bawahnya (thread pool libuv)
* [ ] 2.2 Fase-fase event loop (timers, pending callbacks, poll, check, close callbacks) dan urutan eksekusi `setTimeout` vs `setImmediate` vs `process.nextTick`
* [ ] 2.3 Microtask queue vs macrotask queue: kenapa `Promise.then` selalu jalan sebelum `setTimeout(fn, 0)`
* [ ] 2.4 Blocking vs non-blocking: operasi apa yang benar-benar async (I/O) vs yang tetap blocking (CPU-bound sync code), dan kenapa satu `while(true)` bisa membekukan seluruh server
* [ ] 2.5 Perbandingan eksplisit dengan model Spring Boot/Tomcat: thread-per-request (satu thread per koneksi, blocking I/O aman karena ada banyak thread) vs event loop (satu thread, non-blocking wajib) — kapan masing-masing model unggul

Mini Project: Tulis skrip kecil yang sengaja mem-blok event loop (loop sinkron berat) dan bandingkan latensi request lain saat itu terjadi, lalu perbaiki dengan memecah kerja secara async — dokumentasikan hasil sebelum/sesudah.

Status: Not Started

---

## Module 3 — npm, Project Structure & Tooling

Description: Ekosistem package management dan tooling Node — analog `npm`/`package.json` dengan Maven/Gradle yang sudah dikenal dari Java, plus konvensi struktur project yang idiomatis.

Lessons:

* [ ] 3.1 `npm` dasar: `package.json`, dependencies vs devDependencies, semantic versioning (`^`/`~`), `package-lock.json` — analog `pom.xml`/`build.gradle` dan lockfile Maven/Gradle
* [ ] 3.2 Scripts (`npm run`), `npx`, global vs local install, dan npm workspaces untuk monorepo kecil
* [ ] 3.3 Struktur project Node idiomatis (layered: routes/controllers/services/repositories) — dipetakan ke struktur package Spring Boot (`controller`/`service`/`repository`) yang sudah familiar
* [ ] 3.4 Tooling wajib: ESLint, Prettier, `.env`/`dotenv` untuk konfigurasi, `nodemon` untuk dev loop cepat

Mini Project: Inisialisasi project Node baru dari nol dengan struktur folder layered, ESLint+Prettier terkonfigurasi, dan script `npm run dev`/`npm test` siap pakai — jadi template dasar untuk modul-modul berikutnya.

Status: Not Started

---

## Module 4 — Membangun REST API dengan Express

Description: Mengimplementasikan REST API production-grade dengan Express — kemampuan desain REST API-nya sudah dimiliki dari Spring Boot, jadi modul ini fokus ke *bagaimana Express mengekspresikan pola yang sama*.

Lessons:

* [ ] 4.1 Routing Express: `app.get/post/put/delete`, route parameter, `express.Router()` untuk modularisasi — analog `@GetMapping`/`@RestController` dan `@RequestMapping` di Spring
* [ ] 4.2 Middleware: konsep chain-of-responsibility Express (`req, res, next`) — analog interceptor/filter Spring, urutan eksekusi, middleware bawaan (`express.json()`) vs custom
* [ ] 4.3 Request validation (mis. `zod` atau `joi`) dan pemisahan controller/service/repository yang konsisten dengan struktur Modul 3.3
* [ ] 4.4 Error handling terpusat: error-handling middleware Express (4 parameter), membedakan operational error vs programmer error, mapping ke HTTP status code yang konsisten
* [ ] 4.5 Sekilas Fastify sebagai alternatif Express (performa, schema-based validation bawaan) — kapan pertimbangkan pindah

Mini Project: REST API CRUD sederhana (mis. "notes" atau "tasks") dengan Express, lengkap dengan validation, error handling terpusat, dan struktur layered dari Modul 3.

Status: Not Started

---

## Module 5 — Database dari Node.js: PostgreSQL & Redis

Description: Mengakses stack database yang sama seperti di Spring Boot (PostgreSQL, Redis), tapi lewat driver dan idiom Node — bukan mengajarkan konsep database dari nol.

Lessons:

* [ ] 5.1 Koneksi PostgreSQL dengan `pg` (node-postgres): connection pool, parameterized query, mencegah SQL injection
* [ ] 5.2 ORM dengan Prisma: schema, migration, query builder type-safe — dibandingkan dengan pengalaman JPA/Hibernate di Spring Boot (trade-off ORM vs raw SQL, sama-sama berlaku di kedua stack)
* [ ] 5.3 Redis dari Node dengan `ioredis`: caching pattern (cache-aside), TTL, dan perbedaan penanganan koneksi async dibanding `RedisTemplate`/Lettuce di Spring
* [ ] 5.4 Transaksi database di Node: `BEGIN/COMMIT/ROLLBACK` manual dengan `pg`, transaksi Prisma (`$transaction`) — hati-hati konkurensi karena tidak ada thread-confinement seperti `@Transactional` per-thread di Spring

Mini Project: Tambahkan persistence PostgreSQL (via Prisma) ke API dari Modul 4, plus caching Redis untuk salah satu endpoint yang sering dibaca — buktikan cache hit mengurangi query ke database.

Status: Not Started

---

## Module 6 — Authentication & Security Dasar

Description: Kemampuan security level aplikasi yang wajib ada di API manapun — secukupnya untuk implementasi aman, bukan pengganti pendalaman security course yang sudah ada.

Lessons:

* [ ] 6.1 Password hashing dengan `bcrypt`, kenapa tidak boleh menyimpan plaintext atau hash tanpa salt (sama prinsipnya dengan `BCryptPasswordEncoder` Spring Security)
* [ ] 6.2 Autentikasi berbasis JWT: signing, verifikasi, refresh token pattern, middleware Express untuk proteksi route
* [ ] 6.3 Autentikasi berbasis session (`express-session` + Redis sebagai session store) dan kapan pilih session vs JWT (stateful vs stateless, trade-off yang sama seperti di Spring Security)
* [ ] 6.4 OWASP basics untuk aplikasi Node (helmet.js untuk security header, rate limiting dasar, input sanitization) — batas scope: untuk threat modeling dan security code review mendalam, lanjut ke `courses/cybersecurity`, tidak diulang di sini

Mini Project: Tambahkan autentikasi JWT ke API dari Modul 4-5 (register, login, route terproteksi), plus `helmet` dan rate limiting dasar aktif.

Status: Not Started

---

## Module 7 — Testing Node.js Applications

Description: Test adalah bagian dari "definition of done", sama seperti JUnit di dunia Java — modul ini membangun kebiasaan test-first yang sudah dimiliki, dipindahkan ke tooling Node.

Lessons:

* [ ] 7.1 Unit testing dengan Jest atau Vitest: struktur test, assertion, `describe/it`, mocking dependency — analog JUnit + Mockito
* [ ] 7.2 Integration testing HTTP layer dengan `supertest`: test endpoint Express end-to-end tanpa perlu server benar-benar listen
* [ ] 7.3 Test database: strategi test dengan database nyata (test container/test database terpisah) vs mocking layer repository — trade-off yang sama seperti Testcontainers di Spring Boot
* [ ] 7.4 Test coverage, CI-friendly test script (`npm test` yang exit code benar), dan test pyramid yang proporsional untuk API Node

Mini Project: Tulis test suite untuk API dari Modul 4-6: unit test untuk service/util, integration test endpoint dengan `supertest`, coverage report dihasilkan.

Status: Not Started

---

## Module 8 — Async Patterns, Streams & Error Handling Skala Besar

Description: Pola async lanjut yang dibutuhkan begitu aplikasi tidak lagi sekadar CRUD sederhana — termasuk cara Node menangani beban CPU-bound dan paralelisme.

Lessons:

* [ ] 8.1 EventEmitter pattern: kapan pakai event-driven architecture internal (bukan Kafka/message broker, tapi dalam satu proses)
* [ ] 8.2 Streams (Readable/Writable/Transform): memproses file/data besar tanpa memuat semuanya ke memory — analog `InputStream`/`OutputStream` Java tapi dengan backpressure otomatis
* [ ] 8.3 Worker threads dan `cluster` module: cara Node memanfaatkan multi-core meski single-threaded per proses — dibandingkan thread pool JVM yang built-in
* [ ] 8.4 Error handling yang tidak silent-fail: unhandled promise rejection, uncaught exception, graceful shutdown (`SIGTERM`), dan kenapa proses Node yang crash diam-diam lebih berbahaya daripada exception yang terlihat

Mini Project: Tambahkan satu endpoint yang memproses file besar via stream (bukan load penuh ke memory), dan implementasikan graceful shutdown yang benar untuk API dari modul sebelumnya.

Status: Not Started

---

## Module 9 — Performance & Operasional Production

Description: Aplikasi yang jalan di laptop belum tentu siap production — modul ini menutup gap antara "jalan" dan "siap dioperasikan".

Lessons:

* [ ] 9.1 Clustering Node untuk memanfaatkan multi-core di satu mesin (built-in `cluster` module vs PM2 cluster mode)
* [ ] 9.2 Process manager: PM2 (auto-restart, zero-downtime reload) vs systemd service (konsisten dengan pendekatan `courses/vps` Modul 6 untuk Spring Boot) — kapan pakai yang mana
* [ ] 9.3 Structured logging (pino/winston) dan korelasi request ID — analog SLF4J/Logback terstruktur di Spring Boot
* [ ] 9.4 Profiling dan memory leak dasar: `--inspect`, Chrome DevTools untuk Node, gejala memory leak umum (closure yang menahan referensi, event listener yang tidak dilepas)

Mini Project: Jalankan API dengan PM2 dalam cluster mode, tambahkan structured logging dengan request ID, dan lakukan satu sesi profiling sederhana untuk endpoint yang paling lambat.

Status: Not Started

---

## Module 10 — Deployment & Kesiapan Capstone

Description: Modul penutup — menyiapkan aplikasi Node untuk benar-benar hidup di server production, dengan mekanika deploy server yang merujuk penuh ke `courses/vps`.

Lessons:

* [ ] 10.1 Containerization aplikasi Node dengan Docker: `Dockerfile` multi-stage build, `.dockerignore`, image yang ramping untuk production
* [ ] 10.2 Environment configuration yang aman: `.env` untuk lokal vs environment variable production, memisahkan secret dari kode (konsisten dengan prinsip Modul 6.2 `courses/vps`)
* [ ] 10.3 Health check endpoint dan readiness/liveness probe — kebutuhan minimal sebelum aplikasi dianggap "production-ready" oleh load balancer/orchestrator manapun
* [ ] 10.4 Mekanika deploy ke server sungguhan (systemd/PM2, nginx reverse proxy, HTTPS): rujuk penuh ke `courses/vps` Modul 5-7, tidak diulang — course ini hanya memastikan aplikasi Node sudah dalam bentuk yang siap di-deploy dengan cara yang sama

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **REST API Node.js Production-Grade** — bangun satu REST API nyata/realistis (mis. task manager, blog API, atau domain lain yang relevan untuk portofolio freelance) yang menyatukan seluruh modul: (1) struktur project idiomatis dengan Express (Modul 3-4), (2) persistence PostgreSQL via Prisma + caching Redis (Modul 5), (3) autentikasi JWT dan security basics (Modul 6), (4) test suite unit + integration dengan coverage yang jelas (Modul 7), (5) error handling & graceful shutdown yang benar (Modul 8), (6) siap dioperasikan: clustering, structured logging, containerized (Modul 9-10). Hasil disimpan di `portofolio/`.

Acceptance Criteria:

* API berjalan dengan struktur layered yang jelas (routes/controllers/services/repositories), bukan semua logic di satu file
* Autentikasi JWT berfungsi penuh (register, login, refresh, route terproteksi), password di-hash dengan bcrypt, tidak ada secret hardcoded
* Data persist di PostgreSQL (via Prisma), minimal satu endpoint memanfaatkan caching Redis dengan invalidation yang benar
* Test suite mencakup unit test (service/util) dan integration test (`supertest` terhadap endpoint), dapat dijalankan via `npm test` dengan exit code yang benar untuk CI
* Aplikasi dikemas dengan `Dockerfile` production-ready, punya health check endpoint, dan graceful shutdown terbukti (tidak drop request saat `SIGTERM`)
* Ditulis **dokumen arsitektur singkat** yang membandingkan desain API ini dengan bagaimana service yang sama akan dibangun di Spring Boot (mis. middleware vs interceptor, Prisma vs JPA, event loop vs thread pool, PM2/cluster vs JVM thread management) — bukti pemahaman lintas-stack, bukan sekadar tahu syntax Node
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Sintaks & tipe data JavaScript secukupnya (var/let/const, tipe primitif, truthy/falsy) dengan highlight perbedaan dari Java
