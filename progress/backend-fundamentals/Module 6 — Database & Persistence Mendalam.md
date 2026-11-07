# Database & Persistence Mendalam

  # Indexing mendalam
  index default postgree adalah btree. Karena terurut btree bagus untuk equality (=), range, order by, min/max, like 'abc%'

  best case :
  equality dulu baru column sort

  seq scan : planner membaca semua

  index memperlambat write : tiap insert/update/delete harus update semua index, akibatnya write jadi lebih lama.

  untuk data besar sebaiknya update index dengan CONCURRENTLY 

  jangan di lakukan :
  1. bikin index tanpa menggunakan explain, lalu mengganggap issue udah selesai karena ada index
  2. urutan column composite terbalik 
  3. menambah index di column yang sering di write tanpa ngitung dampak ke insert nya
  4. bikin index buat jaga-jaga aja

  Aturan Emas: 
  Jangan nambah index karena yakin, tambah karena plan-nya bilang begitu.

  Terapin: 
  sebelum PR yang isinya CREATE INDEX, tempel EXPLAIN sebelum dan sesudah di deskripsi PR.

  # Normalisasi vs denormalisasi yang sadar trade-off

  jangan di lakukan :
  1. Menganggap "normalisasi = lambat" tanpa pernah ukur join-nya; padahal join di kolom ter-index biasanya murah.
  2. Denormalisasi duluan karena takut, lalu nggak ada yang tahu tabel mana sumber kebenaran.
  3. Menyamakan snapshot historis dengan duplikasi, lalu "membersihkannya" dan merusak data lama.

  Aturan Emas: 
  Normalisasi dulu sebagai default, denormalisasi cuma kalau ada angka yang ngebuktiin dan ada jalan buat bangun ulang.

  Terapin: 
  sebelum nambah kolom salinan di PR, tulis di deskripsi: sumber kebenarannya di mana, siapa yang nulis, dan query apa yang membangunnya ulang.
  
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


