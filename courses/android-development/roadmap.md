# Course Roadmap

## Course Information

* Course Name: Android Development — dari 0 sampai Expert
* Category: Mobile Development (Native Android)
* Difficulty: Level 1 (belum pernah bikin aplikasi Android sama sekali) → Level 4 (Expert, mampu mendesain arsitektur multi-module untuk aplikasi berskala production, profiling performa, dan merilis ke Play Store secara mandiri)
* Estimated Duration: 12 modul, 4-5 lesson/modul, ~30-45 menit/lesson
* Prerequisites: Familiar dengan Java (dari root `CLAUDE.md`, stack utama repo ini) dan OOP dasar — Modul 1 secara eksplisit dipakai sebagai jembatan Java → Kotlin, bukan pengantar pemrograman dari nol. Terminal dasar dan Git dasar dianggap sudah dikuasai (dipakai untuk Android Studio, Gradle CLI, dan version control sepanjang course). Tidak perlu menyelesaikan course lain di repo ini lebih dulu — course ini berdiri sendiri sebagai track mobile development pertama di repo.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini adalah **course mobile development pertama di repo**, jadi tidak ada course lain yang tumpang tindih untuk dibandingkan — tapi beberapa keputusan scope penting perlu ditulis eksplisit:

1. **Kotlin-first, bukan Java-for-Android.** Meski stack utama repo ini (root `CLAUDE.md`) adalah Java/Spring Boot, Android modern secara resmi merekomendasikan **Kotlin** sebagai bahasa utama — per dokumentasi Android Developers, lebih dari 95% dari 1.000 aplikasi Android teratas memakai Kotlin. Course ini mengajarkan Kotlin dari Modul 1, dengan Java sebagai jembatan konseptual (keduanya JVM-based, OOP, collection framework yang familiar) untuk mempercepat onboarding — bukan mengajarkan Android dengan Java+XML Views seperti materi lama.
2. **Jetpack Compose-first, bukan XML/View system.** Sejak Google resmi menjadikan Compose sebagai toolkit UI yang direkomendasikan untuk aplikasi baru, course ini mengajarkan UI deklaratif (Compose) sejak Modul 3, **bukan** `XML layout` + `findViewById`/View Binding + Fragment-heavy UI lama. Ini keputusan sadar mengikuti arah resmi platform saat ini (2026), bukan course kompatibilitas legacy. Sistem View lama disebut sekilas di Modul 2 (karena Activity/Intent/lifecycle tetap dasar dari sistem View) tapi tidak dijadikan cara membangun UI.
3. **Level "Expert" secara eksplisit menyasar Modul 10-12**: arsitektur skala besar (multi-module Gradle), performance profiling & optimization (startup time, memory leak, baseline profile), serta CI/CD + security + publishing production-grade — bukan sekadar "bisa bikin 1 layar aplikasi", tapi mampu mengoperasikan codebase Android yang dipakai tim & dirilis ke pengguna nyata.
4. **Tie-in ke course backend lain di repo**: Modul 7 (Networking) sengaja dirancang agar aplikasi Android yang dibangun bisa mengonsumsi REST API dari `courses/backend-fundamentals` atau `courses/api-engineering` (Spring Boot) — dua sisi client-server dari stack yang sama, bukan API dummy publik selamanya.

Referensi riset yang dipakai menyusun roadmap ini:

* [Android Developers — Get started / Build your first app](https://developer.android.com/get-started/overview) dan [Create your first Android app (Compose codelab)](https://developer.android.com/codelabs/basic-android-kotlin-compose-first-app) — struktur onboarding resmi Google, dipakai sebagai kerangka Modul 1-3.
* [Kotlin Documentation — Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html), [Flows](https://kotlinlang.org/docs/coroutines-flow.html), dan [Kotlin flows on Android](https://developer.android.com/kotlin/flow) (dokumentasi resmi JetBrains + Android Developers) — dipakai sebagai kerangka Modul 4.
* [State and Jetpack Compose](https://developer.android.com/develop/ui/compose/state) dan [Where to hoist state](https://developer.android.com/develop/ui/compose/state-hoisting) (dokumentasi resmi Compose) — dipakai sebagai kerangka Modul 3.
* [Guide to app architecture](https://developer.android.com/topic/architecture) dan [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android) (dokumentasi resmi) — dipakai sebagai kerangka Modul 5 dan Modul 8.
* [Guide to Android app modularization](https://developer.android.com/topic/modularization) dan [Common modularization patterns](https://developer.android.com/topic/modularization/patterns) (dokumentasi resmi, dirilis Google merespons permintaan komunitas) — dipakai sebagai kerangka Modul 10 (Expert).
* [Improve performance overview](https://developer.android.com/topic/performance/improving-overview), [The Android Profiler](https://developer.android.com/studio/profile/android-profiler), [Baseline Profiles overview](https://developer.android.com/topic/performance/baselineprofiles/overview), dan [Enable app optimization with R8](https://developer.android.com/topic/performance/app-optimization/enable-app-optimization) — dipakai sebagai kerangka Modul 11 dan bagian R8/security di Modul 12.
* [roadmap.sh — Android Developer Roadmap](https://roadmap.sh/android) — dipakai untuk cross-check urutan topik & memastikan tidak ada gap besar dibanding konsensus komunitas.
* Course `courses/ci-cd` (sudah ada di repo, berbasis GitLab CI generik) dipakai sebagai rujukan mental model CI/CD umum; Modul 12 course ini **tidak mengulang** konsep pipeline generik, hanya bagian yang spesifik-Android (Gradle build variants, signing config, Play Console release tracks).

---

# Learning Objectives

* [ ] Menguasai Kotlin sebagai bahasa utama Android (syntax, null safety, data class, coroutine-ready) dengan memanfaatkan latar belakang Java/JVM sebagai percepatan
* [ ] Memahami model aplikasi Android: Activity/Fragment lifecycle, Intent, Application class, dan bagaimana OS mengelola proses aplikasi
* [ ] Mahir membangun UI deklaratif dengan Jetpack Compose: composable function, layout, Modifier, state hoisting, dan recomposition
* [ ] Menguasai asynchronous programming di Android dengan Kotlin Coroutines & Flow (structured concurrency, lifecycle-aware collection)
* [ ] Mampu menerapkan arsitektur MVVM dengan Unidirectional Data Flow memakai Jetpack ViewModel
* [ ] Mampu mengimplementasikan local persistence dengan Room database (schema, DAO, migration)
* [ ] Mampu mengonsumsi REST API dari aplikasi Android (Retrofit/Ktor client) termasuk terhadap backend Spring Boot buatan sendiri
* [ ] Menguasai Dependency Injection dengan Hilt untuk aplikasi berskala menengah-besar
* [ ] Mampu menulis unit test, Compose UI test, dan instrumented test yang reliable
* [ ] **(Expert)** Mampu merancang arsitektur multi-module (modularization) untuk codebase besar yang dikerjakan banyak developer
* [ ] **(Expert)** Mampu melakukan performance profiling (startup time, memory leak, jank) dan optimasi memakai Android Studio Profiler & Baseline Profiles
* [ ] **(Expert)** Mampu membangun pipeline CI/CD Android, hardening dengan R8/ProGuard, dan merilis aplikasi bertanda tangan (signed) ke Google Play Store
* [ ] Menyelesaikan proyek portofolio: aplikasi Android modular, teruji, dengan networking ke backend nyata, pipeline CI, dan build release siap submit ke Play Store

---

# Course Modules

## Module 1 — Kotlin Fundamentals untuk Android (Jembatan dari Java)

Description: Sebelum menyentuh Android sama sekali, kuasai Kotlin sebagai bahasa utama platform. Karena Kotlin dan Java sama-sama JVM-based, modul ini eksplisit memetakan konsep yang sudah dikuasai dari Java (OOP, collection, generic) ke sintaks dan idiom Kotlin, bukan mengajarkan dari nol seolah belum pernah pemrograman.

Lessons:

* [ ] 1.1 Kenapa Kotlin: sejarah singkat, null safety sebagai pembeda utama dari Java, interoperabilitas 100% dengan kode Java
* [ ] 1.2 Syntax dasar: `val`/`var`, type inference, function, string template — dipetakan langsung ke padanan Java
* [ ] 1.3 Null safety mendalam: nullable type (`?`), safe call (`?.`), elvis operator (`?:`), `!!` dan kenapa harus dihindari
* [ ] 1.4 OOP di Kotlin: class, `data class` (vs POJO/record Java), `object`/singleton, sealed class, extension function
* [ ] 1.5 Collection & functional style: `List`/`Map`/`Set` immutable vs mutable, lambda, higher-order function, `map`/`filter`/`fold`

Mini Project: Migrasi sebuah class Java sederhana (mis. domain model dengan beberapa field dan business logic) menjadi Kotlin idiomatik, mendokumentasikan setiap perubahan idiom dan alasannya.

Status: Not Started

---

## Module 2 — Fondasi Aplikasi Android

Description: Memahami model aplikasi Android itu sendiri — bagaimana OS menjalankan, menghentikan, dan mengelola siklus hidup komponen aplikasi. Ini fondasi yang tetap relevan terlepas dari UI toolkit apa pun yang dipakai di atasnya.

Lessons:

* [ ] 2.1 Anatomi proyek Android Studio: `AndroidManifest.xml`, struktur modul `app`, Gradle sebagai build system
* [ ] 2.2 Activity lifecycle: `onCreate`/`onStart`/`onResume`/`onPause`/`onStop`/`onDestroy`, kenapa lifecycle ini ada (memori terbatas, rotasi layar, interupsi sistem)
* [ ] 2.3 Intent: explicit vs implicit intent, berpindah antar-Activity, mengirim/menerima data
* [ ] 2.4 Fragment & sekilas sistem View lama (`XML layout`, `findViewById`) secukupnya untuk paham konteks historis — bukan cara membangun UI di course ini
* [ ] 2.5 Application class, proses & task, serta app permission model dasar

Mini Project: Aplikasi 2 layar sederhana (tanpa Compose dulu) yang berpindah via Intent, mengirim data antar-Activity, dan mencatat lifecycle callback yang terpanggil ke Logcat untuk diobservasi.

Status: Not Started

---

## Module 3 — Jetpack Compose: UI Fundamentals & State Management

Description: Inti dari Android modern. Compose adalah cara resmi Google membangun UI Android saat ini — deklaratif, bukan imperatif seperti sistem View lama.

Lessons:

* [ ] 3.1 Paradigma deklaratif vs imperatif: kenapa Compose menggantikan XML View, mental model "UI = f(state)"
* [ ] 3.2 Composable function dasar: `@Composable`, layout built-in (`Column`, `Row`, `Box`), `Modifier` chain
* [ ] 3.3 Komponen Material 3: `Text`, `Button`, `TextField`, `LazyColumn` untuk list, theming dasar
* [ ] 3.4 State di Compose: `remember`, `mutableStateOf`, `rememberSaveable`, dan bagaimana recomposition bekerja di baliknya
* [ ] 3.5 State hoisting: memisahkan stateless vs stateful composable, kenapa ini membuat komponen reusable & testable
* [ ] 3.6 Side effect API secukupnya untuk pemula: `LaunchedEffect`, `DisposableEffect` — kapan dipakai vs kapan berlebihan

Mini Project: Aplikasi to-do list satu layar penuh dengan Compose: tambah/hapus/centang item, state di-hoist dengan benar ke composable induk, list ditampilkan dengan `LazyColumn`.

Status: Not Started

---

## Module 4 — Kotlin Coroutines & Flow: Asynchronous Programming

Description: Android penuh operasi asynchronous (network call, database, sensor) yang tidak boleh memblokir main thread. Coroutines adalah cara resmi Kotlin/Android menangani ini.

Lessons:

* [ ] 4.1 Kenapa asynchronous di Android: main thread/UI thread, ANR (Application Not Responding), masalah callback hell sebelum coroutine
* [ ] 4.2 Coroutine dasar: `suspend fun`, `CoroutineScope`, `launch` vs `async`, structured concurrency
* [ ] 4.3 Dispatcher: `Main`/`IO`/`Default`, kapan memakai yang mana, `withContext`
* [ ] 4.4 Flow: cold stream, `flow {}` builder, operator umum (`map`, `filter`, `collect`), perbedaan dengan suspend function biasa
* [ ] 4.5 Flow lifecycle-aware di Android: `StateFlow` vs `SharedFlow`, `collectAsStateWithLifecycle` di Compose, menghindari leak saat koleksi

Mini Project: Refactor mini project Modul 3 agar operasi "simulasi loading data" berjalan lewat coroutine dengan `StateFlow`, ditampilkan sebagai loading state di UI Compose tanpa memblokir main thread.

Status: Not Started

---

## Module 5 — Arsitektur Aplikasi: MVVM & Unidirectional Data Flow

Description: Menyatukan UI (Compose) dan state (Coroutines/Flow) dengan pola arsitektur resmi yang direkomendasikan Android Developers — bukan sekadar taruh semua logic di composable.

Lessons:

* [ ] 5.1 Kenapa perlu arsitektur: separation of concern, testability, apa yang salah kalau semua logic ada di composable/Activity
* [ ] 5.2 Jetpack `ViewModel`: lifecycle-aware, survive configuration change, `viewModelScope`
* [ ] 5.3 Pola MVVM di Android: View (Compose) → ViewModel → Model/Repository, aliran data satu arah
* [ ] 5.4 Unidirectional Data Flow (UDF) mendalam: UI State sebagai single source of truth, event naik dari UI ke ViewModel, state turun dari ViewModel ke UI
* [ ] 5.5 Repository pattern: memisahkan ViewModel dari sumber data konkret (persiapan untuk Room & Retrofit di modul berikutnya)

Mini Project: Refactor to-do list app menjadi MVVM penuh: `TodoViewModel` dengan `UiState` data class, Composable hanya membaca state & mengirim event, Repository in-memory sebagai placeholder sumber data.

Status: Not Started

---

## Module 6 — Local Persistence dengan Room

Description: Aplikasi nyata butuh data yang bertahan setelah aplikasi ditutup. Room adalah lapisan abstraksi resmi Jetpack di atas SQLite.

Lessons:

* [ ] 6.1 Kenapa Room, bukan raw SQLite: type safety, compile-time query verification
* [ ] 6.2 Entity, DAO (Data Access Object), Database class — anatomi Room
* [ ] 6.3 Query: `@Insert`/`@Update`/`@Delete`/`@Query`, return type `Flow<List<T>>` untuk observasi reaktif
* [ ] 6.4 Migration: menangani perubahan schema tanpa kehilangan data pengguna
* [ ] 6.5 Menghubungkan Room ke Repository & ViewModel dari Modul 5 (mengganti in-memory placeholder)

Mini Project: To-do list app sekarang persist ke Room database — data tetap ada setelah aplikasi ditutup dan dibuka lagi, list ter-update reaktif lewat `Flow`.

Status: Not Started

---

## Module 7 — Networking: Retrofit & Konsumsi REST API

Description: Menghubungkan aplikasi Android ke dunia luar — termasuk, sebagai latihan yang relevan dengan repo ini, ke backend Spring Boot buatan sendiri dari course lain.

Lessons:

* [ ] 7.1 HTTP client di Android: Retrofit + OkHttp sebagai standar de-facto, sekilas Ktor Client sebagai alternatif Kotlin-native
* [ ] 7.2 Definisi API interface, serialisasi JSON (`kotlinx.serialization` atau Moshi/Gson), mapping response ke model Kotlin
* [ ] 7.3 Error handling network: timeout, retry, representasi hasil sukses/gagal (`sealed class Result`), status code
* [ ] 7.4 Menghubungkan ke backend nyata: mengonsumsi REST API dari `courses/backend-fundamentals`/`courses/api-engineering` (Spring Boot), autentikasi dasar (Bearer token)
* [ ] 7.5 Strategi offline-first sederhana: menggabungkan data dari Room (cache) dan Retrofit (remote) di Repository — "single source of truth" pattern

Mini Project: Ganti/lengkapi to-do list app agar sinkron dengan REST API backend (boleh backend sendiri dari course lain, atau REST API publik sederhana sebagai sementara), dengan Room sebagai cache offline.

Status: Not Started

---

## Module 8 — Dependency Injection dengan Hilt

Description: Seiring aplikasi tumbuh (ViewModel butuh Repository, Repository butuh Retrofit service & Room DAO), manual dependency wiring jadi tidak terkelola. Hilt adalah DI framework resmi Jetpack untuk Android.

Lessons:

* [ ] 8.1 Kenapa DI: masalah manual instantiation & tight coupling, keuntungan untuk testability
* [ ] 8.2 Hilt dasar: `@HiltAndroidApp`, `@AndroidEntryPoint`, constructor injection
* [ ] 8.3 Module & Provides: menyediakan instance untuk class eksternal (Retrofit, OkHttpClient, Room database) yang tidak bisa di-constructor-inject langsung
* [ ] 8.4 Scoping: `@Singleton`, `@ViewModelScoped`, memahami component hierarchy Hilt
* [ ] 8.5 Hilt + Jetpack ViewModel (`hiltViewModel()` di Compose) dan Hilt + testing (`@HiltAndroidTest` — pratinjau untuk Modul 9)

Mini Project: Refactor seluruh dependency chain to-do app (ViewModel → Repository → Retrofit service + Room DAO) memakai Hilt, hapus semua manual wiring/factory manual.

Status: Not Started

---

## Module 9 — Testing: Unit, Compose UI, & Instrumented Test

Description: Kode Android production-grade harus teruji di tiga level berbeda — modul ini menyatukan semua yang dibangun di Modul 1-8 lewat lensa testability.

Lessons:

* [ ] 9.1 Piramida testing di Android: unit test (JVM lokal) vs instrumented test (device/emulator), kapan pakai yang mana
* [ ] 9.2 Unit test ViewModel & Repository dengan JUnit + coroutine test (`runTest`, `TestDispatcher`), fake/mock dengan MockK atau Mockito
* [ ] 9.3 Compose UI test: `ComposeTestRule`, semantic tree, `onNodeWithText`/`performClick`, testing state & interaksi tanpa device fisik
* [ ] 9.4 Instrumented test: Room DAO test di in-memory database, Hilt testing module untuk mengganti dependency production dengan fake
* [ ] 9.5 Test doubles yang tepat: fake vs mock vs stub, menghindari over-mocking yang membuat test rapuh

Mini Project: Test suite untuk to-do app: unit test ViewModel (state transition benar), Compose UI test (klik tombol tambah item memunculkan item baru), instrumented test Room DAO.

Status: Not Started

---

## Module 10 — [Expert] Arsitektur Skala Besar & Modularization

Description: Modul level expert pertama. Aplikasi single-module cukup untuk belajar, tapi tidak untuk tim besar atau aplikasi kompleks — modul ini mengikuti panduan modularization resmi yang dirilis Google.

Lessons:

* [ ] 10.1 Kenapa modularization: build time, reusability, separation of concern, enforced boundary antar-fitur
* [ ] 10.2 Jenis modul: `app` module, `feature` module, `core`/`data` module, `common` module — granularity trade-off (terlalu halus vs terlalu kasar)
* [ ] 10.3 Common modularization patterns: dependency direction (API vs implementation di Gradle), menghindari circular dependency antar-module
* [ ] 10.4 Navigasi antar-module (Navigation Compose lintas feature module) tanpa membuat module saling bergantung langsung
* [ ] 10.5 Build performance: Gradle build cache, parallel execution, dampak modularization terhadap incremental build time

Mini Project: Pecah to-do app (yang sejauh ini single-module) menjadi minimal 3 module (`app`, `feature-todo`, `core-data`), buktikan build incremental lebih cepat setelah perubahan kecil di satu module.

Status: Not Started

---

## Module 11 — [Expert] Performance Profiling & Optimization

Description: Modul level expert kedua. Aplikasi yang benar secara fungsional tapi lambat/boros memori tetap dianggap gagal di production — modul ini mengajarkan cara mengukur, bukan menebak.

Lessons:

* [ ] 11.1 Android Studio Profiler: CPU, memori, network, energy — cara membaca timeline dan mengidentifikasi bottleneck
* [ ] 11.2 Startup time: cold/warm/hot start, mengukur dan mengoptimasi waktu startup aplikasi
* [ ] 11.3 Baseline Profiles: apa itu, kenapa mempercepat startup & mengurangi jank, cara generate dan verifikasi dampaknya
* [ ] 11.4 Memory leak: penyebab umum di Android (context leak, listener yang tidak di-unregister, coroutine scope yang salah), cara mendeteksi dengan Memory Profiler/LeakCanary
* [ ] 11.5 Jank & smooth UI: frame timing, kenapa recomposition berlebihan di Compose bikin lag, teknik `derivedStateOf`/`key` untuk mengurangi recomposition tidak perlu

Mini Project: Profiling to-do app hasil Modul 10, temukan minimal satu bottleneck nyata (startup time atau recomposition berlebihan), perbaiki, dan dokumentasikan before/after dengan angka dari Profiler.

Status: Not Started

---

## Module 12 — [Expert] CI/CD, Security Hardening & Publishing ke Play Store

Description: Modul penutup level expert — menyatukan semua modul sebelumnya menjadi aplikasi yang benar-benar bisa dirilis ke pengguna nyata, otomatis, dan aman. Fokus khusus-Android; konsep CI/CD generik (stage/pipeline) sudah dibahas di `courses/ci-cd` dan tidak diulang di sini.

Lessons:

* [ ] 12.1 Gradle build variants: build type (`debug`/`release`), product flavor, kapan dipakai untuk multi-environment (staging vs production)
* [ ] 12.2 CI untuk Android: pipeline GitHub Actions yang menjalankan lint, unit test, Compose UI test/instrumented test on emulator, build APK/AAB
* [ ] 12.3 R8 & code shrinking: `minifyEnabled`, ProGuard/R8 keep rules, kenapa app crash setelah release build (obfuscation issue) dan cara debug
* [ ] 12.4 App signing: keystore, signing config, App Bundle (`.aab`) vs APK, Play App Signing
* [ ] 12.5 Publikasi ke Google Play Console: release track (internal testing → closed → open → production), staged rollout, versioning (`versionCode`/`versionName`)

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Aplikasi Android Modular Production-Ready** — satukan seluruh course menjadi satu aplikasi nyata (boleh melanjutkan to-do app dari mini project, atau domain lain yang lebih personal, mis. aplikasi pelacak kebiasaan atau expense tracker) yang: (1) ditulis penuh dalam Kotlin + Jetpack Compose dengan state hoisting & UDF yang benar (Modul 1-5), (2) persist data lokal dengan Room dan sinkron ke REST API nyata — idealnya backend Spring Boot dari course lain di repo ini (Modul 6-7), (3) dependency-nya dikelola lewat Hilt (Modul 8), (4) punya test suite di tiga level: unit, Compose UI, instrumented (Modul 9), (5) dipecah minimal 3 module dengan boundary yang jelas (Modul 10), (6) sudah di-profile dan minimal satu optimasi performa terbukti dengan angka (Modul 11), (7) punya pipeline CI (GitHub Actions) dan build release yang di-sign, siap upload ke Play Console minimal sampai tahap internal testing track (Modul 12).

Acceptance Criteria:

* Aplikasi berjalan penuh di atas Kotlin + Jetpack Compose (tidak ada XML layout untuk UI baru), dengan arsitektur MVVM + UDF yang konsisten di semua fitur
* Codebase terbagi ke minimal 3 Gradle module dengan dependency direction yang benar (tidak ada circular dependency), dibuktikan lewat `./gradlew :app:dependencies` atau setara
* Data persist secara lokal (Room) dan sinkron dengan REST API nyata yang bisa didemokan (bukan mock statis)
* Test suite mencakup ketiga level (unit, Compose UI, instrumented) dan dijalankan otomatis lewat CI pipeline (GitHub Actions) pada setiap push
* Minimal satu hasil profiling nyata didokumentasikan (startup time atau memory/recomposition) lengkap dengan angka before/after dari Android Studio Profiler
* Build release (`.aab`) ter-sign dengan keystore sendiri, R8/minify aktif tanpa crash, dan siap di-upload ke Google Play Console (dibuktikan sampai minimal internal testing track — tidak wajib publish publik)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur (diagram module & data flow) dan catatan hasil profiling

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Kenapa Kotlin: sejarah singkat, null safety sebagai pembeda utama dari Java, interoperabilitas 100% dengan kode Java
