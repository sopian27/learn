# Database & Persistence Mendalam
  # JPA/Hibernate Internals: Lazy vs Eager, N+1, @Transactional(readOnly = true)
  persistance context :
  1. lazy : baru di query ketika object nya dipanggil 
  
  lawan dari "lazy" adalah "eager". Eager dipanggil bareng parent.
  
  default jpa
  lazy : onetomany, manytomany
  eager : manytoone,onetoone

  aturan praktis senior : semua relasi dibuat lazy (override default manytoone jadi fetch=lazy)

  cara deteksi:
  - spring.jpa.properties.hibernate.generate_statistics=true
  - Log org.hibernate.SQL di level DEBUG

  cara fixing:
  1. entitygraph
  2. join fetch di jpql : satu query, parent + child (case n+1)
  3. dto projection
  4. default batch size : where id in

  jangan di lakukan :
  1. return entity langsung dari controller
  2. join fetch collection + pagination
  3. readonly = true di method yang memang menulis

  # Migrasi Database dengan Flyway, Kenapa ddl-auto: update Anti-Pattern di Production

  aturan emas :
  1. jangan pernah edit migration yang udah di eksekusi 
  2. satu migration = satu perubahan
  3. nama yang jelas
  4. pisahkan schema migration dan data migration
  5. skema database harus punya history, review, dan urutan seperti kode

  jangan dilakukan :
  1. edit file yang udah jalan di staging
  2. ngandelin ddl-auto:update
  3. create index di table besar tanpa concurrently
  4. jangan pakai R_ repeatable migration buat schema yang tumbuh itu hanya buat, view, function dan store procedure


