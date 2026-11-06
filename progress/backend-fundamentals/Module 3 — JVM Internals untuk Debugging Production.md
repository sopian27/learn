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
  
  # Memory leak klasik di Spring Boot: static collection, listener, `ThreadLocal`

  GC root yang paling sering:
  1. static collection : batasi ukuran + ttl atau gunakan caffeine
  2. listener tidak di unreg : pasangkan register dengan unregister , atau pakai weakReference
  3. ThreadLocal lupa di bersihkan

  jangan di lakukan :
  1. nge restart tanpa nyari tahu leak nya darimana
  2. ngira kalau static itu bagus untuk cache karena cuman satu instance, padahal kalau satu instance gak di bersihin juga bakal jadi leak nanti nya
  3. pake thread local lupa nge remove atau ada remove tapi cuman di block try kalau kena catch gak di handle

  aturan emas:
  tiap kali nyimpen sesatu , tentuin kapan mau dibuang

  terapin:
  pas nulis put, add, subscribe langsung nulis pasangannya
  
  # Baca heap dump & thread dump (`jcmd`, VisualVM/JFR)

  jangan di lakukan :
  1. restart dulu , bari dicari dump nya, bukti nya udah keburu ilang
  2. baca satu thread dump aja, lalu langsung nyimpulin, keputusan terlalu dini
  3. ambil heap dump ketika traffic lagi gede, padahal ada biayanya 
  4. jangan share .hprof selain ke internal

  best case:
  1. ketika apps hang
  2. OOM

  aturan emas:
  amankan buktinya dulu, baru perbaiki

  terapin:
  sebelum restart ambil dump dulu satu menit lebih baik daripada berjam jam nebak
  
  # JVM flags production-relevan secukupnya

