# Testing Pyramid yang Proporsional
  
  # Testing pyramid: proporsi unit vs integration vs end-to-end yang sehat, kenapa ice-cream cone anti-pattern

  Testing Pyramid adalah strategi pengujian software yang membagi testing menjadi tiga layer berdasarkan cakupan pengujian.

  Prinsipnya: semakin ke bawah, jumlah test semakin banyak, cepat, dan murah. Semakin ke atas, jumlah test semakin sedikit, tetapi cakupan pengujiannya semakin luas.

  ibarat piramid, makin ke atas makin mengerucut.

  1. Unit Test
  Menguji logic sebuah class atau method secara terisolasi tanpa bergantung pada dependency eksternal.
  Fokus: Business logic.
  Dependency: Biasanya menggunakan mock.
  Kecepatan: Milidetik.
  Jumlah: Paling banyak.
  
  2. Integration Test
  Menguji apakah beberapa komponen dapat bekerja sama dengan benar, termasuk integrasi dengan dependency eksternal.
  Fokus: Interaksi antar-komponen.
  Dependency: Menggunakan dependency asli atau environment pengujian yang representatif.
  Kecepatan: Detik.
  Jumlah: Sedang.
  
  3. E2E Test
  Menguji keseluruhan sistem melalui alur yang menyerupai tindakan pengguna sebenarnya.
  Fokus: User journey.
  Dependency: Seluruh komponen sistem berjalan.
  Kecepatan: Puluhan detik atau lebih.
  Jumlah: Paling sedikit.

  jangan di lakukan:
  1. Ngukur kesehatan test dari persentase coverage, bukan dari "apakah gagal saat bug beneran muncul".
  2. Nambah E2E tiap ada bug, padahal bug-nya bisa ditangkep dengan satu unit test.
  3. Membiasakan "re-run aja" untuk test flaky. Itu ngajarin tim buat nge-ignore merah.
  4. Jangan refactor seluruh suite sekaligus; pindahin pelan-pelan tiap nambah fitur.

  Aturan Emas: 
  Tangkep bug di layer termurah yang masih bisa nangkepnya.

  Terapin: 
  Pas nulis test buat bug baru, tanya dulu "bisa nggak ini dites di unit?" sebelum bikin E2E.
  
  # Unit testing yang baik: JUnit 5, Mockito, mock vs stub vs spy, over-mocking
  
  # Integration testing dengan Testcontainers: database/Redis/Kafka asli, bukan H2
  
  # Contract testing dasar
 
  # Test sebagai dokumentasi hidup: penamaan, Given-When-Then, pesan gagal yang berguna

