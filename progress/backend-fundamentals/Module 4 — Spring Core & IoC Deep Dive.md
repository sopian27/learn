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


  3. Bean scope (singleton, prototype, request, session)
  4. AOP & Proxy: JDK dynamic proxy vs CGLIB, pitfall self-invocation
  5. Circular dependency dan cara Spring (gagal) mengatasinya

