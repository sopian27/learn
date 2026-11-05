# JVM Internals untuk Debugging Production
  # Memory model JVM: heap vs stack vs metaspace, object lifecycle, kapan eligible GC** ← lu di sini

  jangan di lakukan:
  1. mengganggap obj= null langsung membebaskan memori. yang diputus cuman referensi
  2. naikin xmx tanpa cek ada leak atau gak
  3. jangan optimasi memori sebelum ada bukti heap dump 
  
  best case :
  1. setiap kali muncul error OOM
  2. saat ada code yang pake static, cache buatan sendiri, threadlocal

  Aturan Emas:
  Memori tidak bocor karena GC lalai, tapi karena masih ada code yang menggenggam sesuatu yang sudah tidak butuhkan.

  Terapin:
  pas review PR yang nambah `static` collection atau cache, tanya satu hal: "Siapa yang menghapus isinya, dan kapan?"
  
  # Garbage Collector: GC generasional, G1, ZGC, trade-off throughput vs latency

  best base :
  1. saat latency naik tapi rata rata normal

  jangan di lakukan :
  1. jangan tuning flag gc detail tanpa bukti dari log
  2. jangan pake zgc karena alasan modern

  Aturan Emas:
  Jangan ganti obat sebelum tahu penyakitnya; ukur dulu, baru tuning.

  Terapin:
  pas ada keluhan "lambat", minta log GC atau grafik pause dulu, baru diskusi collector atau flag.
  
  3. Memory leak klasik di Spring Boot: static collection, listener, `ThreadLocal`
  
  4. Baca heap dump & thread dump (`jcmd`, VisualVM/JFR)
  
  5. JVM flags production-relevan secukupnya

