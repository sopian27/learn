# Course Roadmap

## Course Information

* Course Name: Design Patterns untuk Java Backend Engineer — dari "Tahu Singleton & DAO" sampai Expert
* Category: Software Design & Architecture (pelengkap fondasi untuk Java/Spring Boot backend)
* Difficulty: Level 1 (sudah bisa coding Java backend, tahu implementasi Singleton & DAO tapi belum paham *kapan* dan *kenapa*) → Level 4 (bisa memilih & menolak pattern secara sadar, baca source code Spring dan mengenali pattern di dalamnya, memimpin code review soal desain)
* Estimated Duration: 13 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Java OOP solid (class, interface, inheritance, polymorphism), sudah pernah bikin REST API dengan Spring Boot, familiar konsep DAO & Singleton meski belum mendalam.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban Anda:

1. **Cakupan**: Anda memilih **GoF + Enterprise + Architectural patterns**. Karena itu course ini tidak berhenti di 23 pola Gang of Four — ada Module 9 (DAO vs Repository, Unit of Work, DTO, Service Layer, Dependency Injection) dan Module 10 (Layered, Hexagonal/Clean Architecture, MVC, CQRS, Event-Driven) yang langsung menjawab pertanyaan "kapan pakai apa" di kode backend Spring Boot sehari-hari — bukan cuma pola dari buku 1994.
2. **Integrasi Spring**: Anda memilih **sertakan Production Discussion: Spring Boot** di tiap modul pattern. Setiap pattern akan ditunjukkan implementasi aslinya di source code Spring Framework (mis. `BeanFactory` = Factory + Singleton scope, `ProxyFactory`/AOP = Proxy & Decorator, `JdbcTemplate`/`RestTemplate` = Template Method, `ApplicationListener` = Observer, `HandlerInterceptorChain` = Chain of Responsibility). Ini penting supaya pattern tidak terasa akademis — Anda akan sadar Anda **sudah memakai pattern ini setiap hari** tanpa sadar lewat Spring.
3. **Gaya Latihan**: Anda memilih **refactor dari kode jelek**, bukan bangun dari nol. Karena itu hampir setiap lesson dimulai dari cuplikan kode Java yang punya code smell nyata (rigid, melanggar SOLID, sulit di-test) — Anda diminta mendiagnosis dulu *masalah desainnya apa* sebelum tahu pattern mana yang jadi solusinya. Ini yang langsung melatih kelemahan yang Anda sebutkan: tahu cara implementasi Singleton/DAO, tapi belum punya insting "kapan pattern ini relevan."
4. **Kenapa Module 1 wajib ada sebelum masuk pattern**: Tanpa paham SOLID & code smell, pattern akan terasa seperti resep hafalan. Module 1 membangun *kosakata* untuk mendiagnosis masalah desain — tiap pattern setelahnya adalah jawaban atas satu jenis masalah spesifik dari kosakata itu.
5. **Kenapa ada Module 12 (Anti-Pattern)**: Ini modul yang paling sering dilewatkan course lain, padahal paling penting untuk level Expert — bukan cuma tahu cara pakai pattern, tapi tahu kapan **menolak** memakainya. Overengineering dengan pattern adalah kesalahan senior-yang-baru-belajar-pattern yang sangat umum.

Referensi riset yang dipakai menyusun roadmap ini: struktur kategori pattern (Creational/Structural/Behavioral) mengikuti katalog resmi [refactoring.guru/design-patterns/catalog](https://refactoring.guru/design-patterns/catalog) dan buku asli *Design Patterns: Elements of Reusable Object-Oriented Software* (Gang of Four), topik "Design Principles → Design Patterns → Architectural Patterns → Enterprise Patterns" mengikuti struktur [roadmap.sh — Software Design & Architecture](https://roadmap.sh/software-design-architecture), pola static factory/builder mengikuti *Effective Java* (Joshua Bloch, Item 1-3), dan pola arsitektur (Layered/Hexagonal/CQRS) mengikuti *Patterns of Enterprise Application Architecture* (Martin Fowler) serta dokumentasi resmi [Spring Framework](https://docs.spring.io/spring-framework/reference/) untuk bagian Production Discussion.

---

# Learning Objectives

* [ ] Paham SOLID principles bukan sebagai hafalan akronim, tapi sebagai alat diagnosis: bisa menunjuk baris kode dan bilang "ini melanggar OCP karena..."
* [ ] Menguasai seluruh 23 pola GoF (Creational, Structural, Behavioral): tahu struktur, intent, trade-off, dan kapan pola itu justru pilihan yang salah
* [ ] Benar-benar paham Singleton: thread-safety, kenapa dianggap anti-pattern di banyak konteks, bedanya dengan Spring singleton bean scope
* [ ] Bisa membedakan DAO vs Repository vs Unit of Work vs Service Layer secara presisi, dan tahu mana yang cocok untuk arsitektur proyek tertentu
* [ ] Paham pola arsitektur backend (Layered, Hexagonal/Clean Architecture, CQRS, Event-Driven) dan trade-off masing-masing untuk skala proyek berbeda
* [ ] Bisa membaca source code Spring Framework dan mengenali pattern GoF di dalamnya (BeanFactory, AOP Proxy, JdbcTemplate, ApplicationEvent, dll)
* [ ] Mampu mengenali code smell dan memilih pattern yang tepat untuk memperbaikinya — bukan menghafal "masalah X = pattern Y" tapi memahami intent di baliknya
* [ ] Mengenali anti-pattern dan overengineering: tahu kapan **tidak** memakai pattern adalah keputusan yang lebih baik
* [ ] Siap interview teknis level Senior/Staff untuk pertanyaan design pattern & system/object design
* [ ] Menyelesaikan proyek portofolio: refactor backend service Java yang buruk desainnya menjadi production-grade, dengan dokumentasi keputusan desain (ADR-style)

---

# Course Modules

## Module 1 — Fondasi: Kenapa Design Pattern Ada

Description: Sebelum belajar satu pattern pun, bangun dulu kosakata untuk mendiagnosis masalah desain. Modul ini adalah alasan kenapa pattern nanti terasa seperti solusi logis, bukan resep hafalan.

Lessons:

* [ ] OOP recap yang relevan untuk pattern: encapsulation sebagai information hiding (bukan cuma private field), polymorphism sebagai mekanisme "satu interface, banyak perilaku"
* [ ] Composition over Inheritance — kenapa prinsip ini jadi fondasi hampir semua GoF pattern
* [ ] SOLID principles satu per satu dengan contoh kode Java yang melanggar tiap prinsip: SRP, OCP, LSP, ISP, DIP
* [ ] Code smell yang jadi sinyal butuh pattern: Rigidity, Fragility, Immobility, tight coupling, shotgun surgery
* [ ] Membaca UML dasar untuk pattern: class diagram (asosiasi, agregasi, komposisi, inheritance) dan sequence diagram secukupnya untuk mengikuti course ini

Mini Project: Diberi 1 file Java backend (service class) yang "berfungsi" tapi jelek — Anda diminta membuat daftar pelanggaran SOLID dan code smell yang ditemukan, tanpa memperbaikinya dulu (latihan diagnosis, bukan solusi).

Status: Not Started

---

## Module 2 — Creational Patterns Part 1: Singleton & Factory Method

Description: Mulai dari pattern yang sudah Anda kenal (Singleton) tapi kali ini sampai akar-akarnya — thread-safety, kenapa dianggap anti-pattern, dan bedanya dengan Spring bean scope. Lalu Factory Method sebagai pattern creational paling dasar.

Lessons:

* [ ] Singleton mendalam: lazy vs eager initialization, double-checked locking, enum singleton (kenapa ini cara paling aman di Java), masalah testability & global state
* [ ] Singleton vs Spring `@Component` default scope — kenapa Spring container yang mengelola singleton lebih baik daripada `getInstance()` manual
* [ ] Static Factory Method (Effective Java Item 1): kapan lebih baik daripada constructor publik
* [ ] Factory Method pattern penuh: struktur, intent, refactor kode yang punya `if-else`/`switch` bertingkat untuk membuat objek
* [ ] **Production Discussion: Spring Boot** — `BeanFactory` & `ApplicationContext` sebagai Factory + Singleton scope management, bean lifecycle

Mini Project: Refactor kode jelek yang punya `switch` besar untuk membuat berbagai jenis `NotificationSender` (Email/SMS/Push) menjadi Factory Method yang extensible.

Status: Not Started

---

## Module 3 — Creational Patterns Part 2: Abstract Factory, Builder, Prototype

Description: Melanjutkan creational patterns untuk kasus yang lebih kompleks — membuat keluarga objek terkait, objek dengan banyak parameter opsional, dan menduplikasi objek mahal.

Lessons:

* [ ] Abstract Factory: membuat keluarga objek yang saling terkait (mis. berbagai provider payment gateway dengan varian request/response berbeda)
* [ ] Builder pattern: masalah "telescoping constructor", fluent builder manual di Java
* [ ] Builder di dunia nyata: Lombok `@Builder`, `StringBuilder`, `UriComponentsBuilder` Spring — kapan builder manual vs generated
* [ ] Prototype pattern: shallow vs deep copy, `Cloneable` di Java dan kenapa banyak yang menghindarinya, alternatif copy constructor
* [ ] **Production Discussion: Spring Boot** — pola builder pada `RestClient`/`WebClient`, `ResponseEntity.BodyBuilder`

Mini Project: Refactor konstruktor `Order` dengan 8 parameter (beberapa opsional) menjadi Builder yang readable dan aman dari salah urutan argumen.

Status: Not Started

---

## Module 4 — Structural Patterns Part 1: Adapter, Facade, Decorator

Description: Pola structural untuk menyusun objek/class menjadi struktur yang lebih besar tanpa membuatnya rapuh. Dimulai dari tiga pattern yang paling sering muncul di integrasi backend.

Lessons:

* [ ] Adapter pattern: mengintegrasikan library/API eksternal dengan interface yang tidak cocok dengan kode Anda, tanpa mengubah kode eksisting
* [ ] Facade pattern: menyederhanakan subsystem kompleks (mis. proses checkout yang melibatkan banyak service) di balik satu interface sederhana
* [ ] Decorator pattern: menambah perilaku ke objek secara dinamis tanpa subclassing — contoh klasik Java I/O Streams
* [ ] Decorator vs Inheritance: kapan extend class lebih tepat, kapan decorator lebih tepat
* [ ] **Production Discussion: Spring Boot** — `HandlerInterceptor` & filter chain sebagai Decorator/Chain, `BufferedReader(new InputStreamReader(...))` sebagai Decorator asli Java

Mini Project: Refactor kode yang melakukan logging & caching dengan cara "copy-paste kode ke setiap method" menjadi Decorator yang reusable.

Status: Not Started

---

## Module 5 — Structural Patterns Part 2: Proxy, Composite, Bridge, Flyweight

Description: Structural pattern yang lebih jarang dipakai eksplisit tapi krusial untuk paham cara kerja framework (Proxy) dan kasus khusus (tree structure, memory optimization).

Lessons:

* [ ] Proxy pattern: virtual proxy (lazy loading), protection proxy (access control), remote proxy — bedanya dengan Decorator (intent, bukan struktur)
* [ ] Composite pattern: struktur tree yang diperlakukan seragam (mis. permission/menu hierarchy)
* [ ] Bridge pattern: memisahkan abstraction dari implementation supaya keduanya bisa berubah independen (kasus lebih jarang, tapi penting untuk paham *variasi 2 dimensi*)
* [ ] Flyweight pattern: berbagi objek untuk hemat memori (caching object yang immutable dan sering dipakai ulang)
* [ ] **Production Discussion: Spring Boot** — AOP proxy (`@Transactional`, `@Cacheable` bekerja lewat dynamic proxy/CGLIB), `Integer.valueOf` caching sebagai Flyweight di JDK

Mini Project: Jelaskan (dengan diagram + kode kecil) kenapa `@Transactional` di Spring butuh Proxy — dan apa yang terjadi kalau Anda memanggil method `@Transactional` dari method lain di class yang sama (self-invocation problem).

Status: Not Started

---

## Module 6 — Behavioral Patterns Part 1: Strategy, Template Method, Command

Description: Tiga pattern behavioral yang paling sering dipakai di backend production — strategy untuk variasi algoritma, template method untuk skeleton proses, command untuk membungkus request sebagai objek.

Lessons:

* [ ] Strategy pattern: mengganti `if-else`/`switch` berdasarkan tipe menjadi keluarga algoritma yang saling ditukar (contoh: berbagai aturan diskon, validasi, payment method)
* [ ] Strategy + Dependency Injection: kenapa kombinasi ini natural di Spring (inject `List<PaymentStrategy>`, pilih berdasarkan key)
* [ ] Template Method: mendefinisikan skeleton algoritma di superclass, langkah spesifik di subclass
* [ ] Command pattern: membungkus request sebagai objek — dasar untuk undo/redo, job queue, retry
* [ ] **Production Discussion: Spring Boot** — `JdbcTemplate`/`RestTemplate` sebagai Template Method asli, `Runnable`/`Callable` yang dikirim ke `TaskExecutor` sebagai Command

Mini Project: Refactor kode diskon checkout yang punya `if (customerType.equals("GOLD")) ... else if (...)` bertingkat menjadi Strategy pattern yang di-inject Spring.

Status: Not Started

---

## Module 7 — Behavioral Patterns Part 2: Observer, State, Chain of Responsibility

Description: Pattern untuk komunikasi antar objek tanpa coupling erat (Observer), mengelola perilaku berbasis status (State), dan memproses request lewat rangkaian handler (Chain of Responsibility).

Lessons:

* [ ] Observer pattern: subject-observer, push vs pull model, masalah memory leak kalau observer tidak di-unsubscribe
* [ ] State pattern: menggantikan flag/enum + `if-else` besar untuk mengelola state machine (contoh: status Order — CREATED, PAID, SHIPPED, CANCELLED)
* [ ] Chain of Responsibility: request diproses berantai lewat serangkaian handler, tiap handler bisa menangani atau meneruskan
* [ ] **Production Discussion: Spring Boot** — `ApplicationEvent`/`ApplicationListener`/`@EventListener` sebagai Observer, `FilterChain` Servlet & Spring Security filter chain sebagai Chain of Responsibility

Mini Project: Refactor state management pesanan (`Order`) yang pakai `String status` + banyak `if` tersebar di berbagai service menjadi State pattern yang eksplisit.

Status: Not Started

---

## Module 8 — Behavioral Patterns Part 3: Iterator, Mediator, Memento, Visitor

Description: Empat pattern behavioral yang lebih jarang muncul eksplisit di kode backend sehari-hari, tapi penting untuk kelengkapan GoF, membaca library, dan interview.

Lessons:

* [ ] Iterator pattern: kenapa `for-each` Java sebenarnya memakai pattern ini (`Iterable`/`Iterator`), kapan buat custom iterator
* [ ] Mediator pattern: memusatkan komunikasi antar banyak objek supaya tidak saling kenal langsung (contoh: koordinasi antar komponen UI atau workflow step)
* [ ] Memento pattern: menyimpan & mengembalikan state objek tanpa melanggar encapsulation (dasar konsep undo, audit snapshot)
* [ ] Visitor pattern: menambah operasi baru ke struktur objek tanpa mengubah class-nya (double dispatch) — trade-off dengan tambah method langsung
* [ ] Kapan 4 pattern ini benar-benar terpakai di backend vs kapan cuma jadi pengetahuan interview

Mini Project: Implementasikan Visitor kecil untuk menghitung total harga dari struktur `Composite` (dari Module 5) yang berisi campuran `Product` dan `Bundle`.

Status: Not Started

---

## Module 9 — Enterprise & Data Access Patterns

Description: Ini modul yang langsung membedah kelemahan spesifik Anda — DAO. Setelah modul ini Anda akan tahu presisi bedanya DAO, Repository, Unit of Work, DTO, dan Service Layer, bukan cuma "pokoknya class buat akses data".

Lessons:

* [ ] DAO pattern murni: intent aslinya (abstraksi akses data generik), bentuk klasik sebelum ada Spring Data
* [ ] Repository pattern (Domain-Driven Design): bedanya dengan DAO — Repository bicara bahasa domain/collection-like, DAO bicara bahasa storage/CRUD
* [ ] `Spring Data JPA` Repository: apa yang Spring buatkan otomatis, dan apakah itu murni Repository pattern atau hybrid
* [ ] Unit of Work pattern: mengelola transaksi & perubahan sebagai satu batch — hubungannya dengan `@Transactional`
* [ ] DTO vs Entity vs Domain Model: kenapa tidak boleh expose Entity JPA langsung ke API, mapping strategy (manual vs MapStruct)
* [ ] Service Layer pattern & Dependency Injection sebagai pattern arsitektural (bukan cuma fitur framework)

Mini Project: Refactor sebuah `UserDao` yang mengembalikan Entity JPA langsung ke Controller (tanpa DTO, tanpa Service layer) menjadi struktur Repository + Service + DTO yang benar berlapis.

Status: Not Started

---

## Module 10 — Architectural Patterns untuk Backend

Description: Naik satu level dari pattern per-class ke pattern yang menentukan struktur seluruh aplikasi. Modul ini menjawab pertanyaan "kenapa proyek Spring Boot biasa punya folder controller/service/repository" dan kapan itu tidak cukup.

Lessons:

* [ ] Layered (N-tier) Architecture: struktur controller-service-repository standar, kelebihan & keterbatasannya (anemic domain model risk)
* [ ] Hexagonal Architecture (Ports & Adapters) / Clean Architecture: memisahkan domain dari infrastruktur, kapan ini sepadan dengan kompleksitas tambahannya
* [ ] MVC dalam konteks Spring MVC: apa sebenarnya "Model" di sana (sering disalahpahami)
* [ ] CQRS (Command Query Responsibility Segregation): memisahkan jalur read & write, kapan berguna (bukan default untuk semua proyek)
* [ ] Event-Driven Architecture overview: domain event, eventual consistency — persiapan konsep untuk Kafka (di luar scope course ini)

Mini Project: Bandingkan (dokumen singkat + diagram) struktur Layered vs Hexagonal untuk 1 use case yang sama (mis. modul pembayaran) — kapan Anda akan memilih yang mana untuk proyek nyata.

Status: Not Started

---

## Module 11 — Design Patterns di Dalam Spring Framework (Deep Dive)

Description: Mengumpulkan semua "Production Discussion" dari modul sebelumnya jadi satu latihan besar: membaca source code Spring Framework langsung dan mengenali pattern GoF & Enterprise di dalamnya. Ini yang membedakan level "tahu pattern" dengan "paham framework yang dipakai tiap hari".

Lessons:

* [ ] Peta lengkap: pattern apa dipakai di komponen Spring apa (BeanFactory, ApplicationContext, ProxyFactory, JdbcTemplate, RestTemplate/WebClient, ApplicationEventPublisher, HandlerInterceptor, FilterChain)
* [ ] Membaca source code `AbstractApplicationContext` / `DefaultListableBeanFactory` (ringkas, bagian relevan saja) untuk melihat Factory + Singleton scope nyata
* [ ] Membaca source code AOP proxy creation untuk melihat Proxy/Decorator nyata di balik `@Transactional`/`@Cacheable`
* [ ] Diskusi: kenapa memahami pattern di balik framework membuat Anda bisa debug masalah yang "aneh" (mis. self-invocation `@Transactional`, circular dependency saat bean creation)

Mini Project: Tulis catatan (untuk `progress/cheatsheet.md`) berupa tabel "Pattern → Komponen Spring → Kenapa dipakai di situ" hasil eksplorasi Anda sendiri.

Status: Not Started

---

## Module 12 — Anti-Pattern, Pattern Abuse, dan Kapan TIDAK Pakai Pattern

Description: Modul level Expert yang paling sering dilewatkan course lain. Tujuan sebenarnya bukan menambah pattern baru ke tool belt Anda, tapi melatih penilaian (judgment) — kapan pattern adalah solusi, dan kapan pattern itu sendiri jadi masalah.

Lessons:

* [ ] Overengineering / "pattern-itis": ciri-ciri kode yang pakai pattern padahal masalahnya sederhana
* [ ] Anti-pattern umum: God Object, Anemic Domain Model, Service Locator, Singleton sebagai global mutable state, Golden Hammer
* [ ] Refactoring TO patterns (menambah pattern saat kompleksitas nyata muncul) vs refactoring AWAY FROM patterns (menyederhanakan saat pattern ternyata berlebihan)
* [ ] Heuristik pengambilan keputusan: pertanyaan yang harus dijawab sebelum menambah pattern (Apakah variasi ini benar-benar akan bertambah? Apakah tim paham pattern ini? Apakah biaya indirection sepadan?)
* [ ] Studi kasus dari code review nyata: kapan reviewer senior akan bilang "ini terlalu rumit untuk masalah sekecil ini"

Mini Project: Diberi 2 potongan kode — satu underengineered (butuh pattern tapi tidak pakai), satu overengineered (pakai 3 pattern untuk masalah sepele). Anda menulis code review untuk keduanya.

Status: Not Started

---

## Module 13 — Capstone: Refactor Legacy Backend Service

Description: Simulasi kerja nyata sebagai Senior Engineer — diberi 1 service Java/Spring Boot yang berfungsi tapi desainnya buruk (melanggar SOLID, hardcoded, sulit di-test), lalu me-refactor-nya secara bertahap dengan pattern yang tepat dari seluruh course, didampingi code review penuh.

Lessons:

* [ ] Fase diagnosis: audit menyeluruh code smell & pelanggaran SOLID di service yang diberikan (pakai kosakata dari Module 1)
* [ ] Fase perencanaan: menentukan pattern mana yang relevan untuk tiap masalah — dan pattern mana yang sengaja TIDAK dipakai (pakai judgment dari Module 12)
* [ ] Fase eksekusi: refactor bertahap, tiap langkah dengan test yang tetap hijau
* [ ] Fase dokumentasi: menulis ADR (Architecture Decision Record) singkat untuk 3 keputusan desain terbesar
* [ ] Code review penuh (REVIEW_MODE) atas hasil akhir

Portfolio Project: Lihat "Enterprise Project" di bawah.

Status: Not Started

---

# Enterprise Project

Description: **Legacy Service Refactor Simulation** — diberikan 1 service backend Java/Spring Boot (order processing atau payment processing) yang sengaja ditulis dengan banyak code smell dan pelanggaran SOLID, sepenuhnya berfungsi tapi rapuh. Anda mendiagnosis, merencanakan, dan me-refactor-nya menjadi kode production-grade dengan menerapkan minimal 6 pattern dari course ini (kombinasi Creational/Structural/Behavioral/Enterprise) secara tepat guna — tidak berlebihan, tidak kurang.

Acceptance Criteria:

* Ada dokumen diagnosis awal: daftar code smell & pelanggaran SOLID yang ditemukan, dengan referensi baris kode
* Minimal 6 pattern diterapkan, masing-masing dengan justifikasi tertulis "kenapa pattern ini, kenapa bukan yang lain"
* Minimal 1 keputusan eksplisit untuk **tidak** memakai pattern di tempat yang tergoda untuk dipakai, dengan alasan (bukti pemahaman Module 12)
* Test suite (JUnit) tetap hijau di setiap tahap refactor — tidak ada refactor yang memecah behavior
* 3 ADR (Architecture Decision Record) singkat untuk keputusan desain terbesar
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, siap dibahas saat interview

Status: Not Started

---

# Overall Progress

Module Completion: 0/13

Overall Completion: 0%

Current Module: Belum dimulai — menunggu approval roadmap

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — OOP recap yang relevan untuk pattern (encapsulation & polymorphism)
