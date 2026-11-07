# Spring Core & IoC Deep Dive

  # IoC Container & `ApplicationContext`: bean definition, bean lifecycle

  lifecycle bean:
  BeanDefinition terdaftar (resep, belum ada objek)
  1. Instantiation          -> constructor jalan, field masih null
  2. Dependency Injection   -> setter / field disuntik
  3. Aware callbacks        -> BeanNameAware, ApplicationContextAware
  4. BeanPostProcessor.before
  5. Init                   -> @PostConstruct, afterPropertiesSet, initMethod
  6. BeanPostProcessor.after -> di sinilah proxy dibuat (@Transactional, @Cacheable)
  ... bean siap dipakai ...
  7. Destroy                -> @PreDestroy, DisposableBean (urutan terbalik)

  real world:
  @PostConstruct : bisa digunakan untuk warming cache atau validasi konfigurasi saat startup, jadinya kalau service gagal waktu deploy bisa langsung ketahuan.

  jangan di lakukan :
  1. calling dependency di constructor , pasti null karena field injection belum terjadi
  2. taruh logika berat di @PostConstruct sampai startup lambat dan pod gagal
  3. logika bisnis di constructor

  aturan emas:
  jangan pakai sesuatu sebelum yakin urutan pembuatannya sudah selesai

  terapin:
  sebelum menulis logika di constructor atau init, udah yakin belum kalau dependency nya udah ke inject
  
  # Dependency Injection: constructor vs setter vs field injection
  
  keuntungan constructor injection :
  1. immutability : field final tidak bisa diubah setelah dibuat
  2. testability : unit test lebih mudah
  3. fail-fast : dependency kurang = gagal, langsung ketahuan
  4. desain jujur : constructor panjang langsung keliatan

  jangan di lakukan :
  1. field injection itu mengorbankan testability dan kejujuran design
  2. lupa nge set field injection padahal wajib
  3. constructor dengan lebih dari +6 field dibiarkan begitu saja

  aturan emas:
  dependency yang harusnya wajib harus mustahil terlupa, bukan cuma "biasanya ingat".

  terapin:
  saat review code, kalau liat autowired di field tanya kenapa gak via constructor

  # Bean scope (singleton, prototype, request, session)
  scope
  1. singleton : hidup seumur aplikasi (1 per container) cocok untuk service, repo, client
  2. prototype : container cuman buat, gak ngurusin destroy nya (1 per request)
  3. request : 1 per HTTP (1 request)
  4. session : 1 per HTTP session (1 session user)

  singleton harus thread safe (jangan bocor), caranya :
  1. simpan di param
  2. variable lokal
  3. atau thread safe

  jangan di lakukan :
  1. nyimpen data per-request di bean singleton (akibatnya jadi race condition)
  2. ngira kalau prototype akan dibuat terus ketika request baru (padahal prototype juga di inject ke singleton)
  
  best case:
  1. singleton (default) untuk service, repository, dan client yang stateless.
  2. Prototype untuk objek stateful ringan yang tiap pemakai harus punya sendiri (builder, collector), diminta lewat `ObjectProvider`.

  aturan emas:
  Kalau banyak client berbagi satu objek, objek itu tidak boleh mengingat siapa pun.

  Terapin: 
  saat review `@Service`, scan field-nya; tiap field yang diubah setelah startup wajib dijawab, "siapa saja yang berbagi instance ini?"

  # AOP & Proxy: JDK dynamic proxy vs CGLIB, pitfall self-invocation
  aop (aspect oriented programming) : menaruh urusan lintas feature (trx, logging, security, cache, retry) di satu tempat, bukan di salin ke tiap method.

  JDK-DYNAMIC PROXY : punya java bawaan
  CGLIB : punya spring

  jangan di lakukan :
  1. nganggep `@Transactional`/`@Async`/`@Cacheable` bisa pake `this.method()` di class yang sama.
  2. pake anotasi `@Transactional`/`@Async`/`@Cacheable` padahal method `private` atau class `final` (CGLIB gagal)

  Aturan Emas:
  Kalau sebuah anotasi tidak bekerja, curigai jalur panggilannya dulu, baru logikanya.

  Terapin:
  saat "@Transactional kok nggak rollback", pertama-tama tanya "siapa yang memanggil method ini, proxy atau `this`?"

  5. Circular dependency dan cara Spring (gagal) mengatasinya
  cara memutus siklus:
  1. ekstrak bean ketiga yang memegang logika bersama
  2. setter injection + allow circular references

  jangan di lakukan :
  1. nyalain allow circular references agar supaya apps bisa jalan
  2. pindah ke field atau setter injection supaya error ilang
  3. pakai @lazy dimana mana sebagai solusi default
  4. dua service domain yang saling memanggil , lebih baik lewat bean ketiga atau event

  aturan emas:
  kalau dua hal saling butuh, jangan cari cara menyambungnya, cari hal ketiga yang bisa nyambungin tanpa mereka harus kenal

  terapin:
  saat review pr ada dua service yang saling inject

