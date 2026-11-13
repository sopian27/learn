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

  Stub
  Memberikan jawaban tetap
  Kita menentukan sendiri hasil yang dikembalikan dependency.

  @Test
  void getUser_shouldReturnUser() {
      // Stub
      when(userRepository.findById(1L))
          .thenReturn(Optional.of(new User(1L, "Budi")));

      User result = userService.getUser(1L);

      assertEquals("Budi", result.getName());
  }

  Mock
  Memeriksa interaksi
  Kita ingin memastikan dependency dipanggil sesuai harapan.

  @Test
  void deleteUser_shouldCallRepository() {
      userService.deleteUser(1L);

      // Mock verification
      verify(userRepository).deleteById(1L);
  }

  Spy
  Memantau objek asli
  Spy menggunakan objek asli, tetapi kita bisa memantau atau mengganti perilaku method tertentu.

  @Test
  void spyExample() {
      List<String> users = new ArrayList<>();

      List<String> spyList = spy(users);

      spyList.add("Budi");

      // Memeriksa hasil objek asli
      assertEquals(1, spyList.size());

      // Memeriksa interaksi
      verify(spyList).add("Budi");
  }

  fake
  Implementasi sederhana
  Kita membuat implementasi dependency sendiri tanpa database sungguhan.

  class FakeUserRepository implements UserRepository {

      private final Map<Long, User> database = new HashMap<>();

      @Override
      public Optional<User> findById(Long id) {
          return Optional.ofNullable(database.get(id));
      }

      @Override
      public void save(User user) {
          database.put(user.getId(), user);
      }
  }

  jangan di lakukan:
  1. Nulis verify(...) untuk semua dependency, sampai test merah tiap ada refactor tanpa perubahan perilaku.
  2. Test tanpa assertion yang berarti (cuma mastiin "nggak error"), lalu dibanggain karena coverage tinggi.
  3. Mock class yang lu miliki sendiri dan sederhana, padahal objek aslinya bisa dipakai langsung.
  4. Jangan mock untuk ngetes query SQL atau mapping. Itu tugas integration test.
  5. Jangan verify urutan atau jumlah panggilan internal kalau bukan kontrak.

  Aturan Emas: 
  Tes apa yang dihasilkan kode, bukan gimana kode itu bekerja.

  Terapin: 
  Pas review test, tanya "kalau implementasinya diganti tapi hasilnya sama, test ini masih hijau nggak?"
  
  # Integration testing dengan Testcontainers: database/Redis/Kafka asli, bukan H2

  Testcontainers nyalain Postgres/Redis/Kafka asli di Docker, dengan schema dari migration yang sama.

  Testcontainers: perilaku sama dengan production, bug constraint/locking/query ketangkep.

  H2 / in-memory: cepat dan nggak butuh apa-apa, tapi nyembunyiin bug yang spesifik ke database asli.

  Mock repository: paling cepat, tapi nggak ngetes SQL, mapping, dan constraint sama sekali.

  Jangan ngetes semuanya lewat container. Kalau piramida test berarti di tengah, di integration test.

  jangan di lakukan:
  1. Pakai H2 "mode PostgreSQL" lalu percaya hasilnya sama. Mode itu cuma meniru sebagian.
  2. Ngandelin ddl-auto=create di test, jadi schema test beda dari hasil migration production.
  3. Nyalain container baru di tiap test method, bikin suite lambat, lalu tim mematikan integration test.
  4. Test saling bergantung ke data sisa test lain karena database nggak dibersihkan antar test.
  5. Logic bisnis murni tanpa I/O: cukup unit test

  best case:
  1. Ngetes repository, query custom, migration, constraint, dan transaksi.
  2. Ngetes integrasi nyata: cache Redis (TTL, serialisasi), consumer/producer Kafka.
  3. Reproduksi bug production yang hanya muncul di database asli.

  Aturan Emas: 
  Test yang cuma ngetes tiruan cuma membuktikan tiruannya baik-baik saja.

  Terapin: 
  Sebelum percaya test hijau, tanya "ini jalan melawan sistem yang sama dengan production, atau tiruannya?"
  
  # Contract testing dasar
 
  # Test sebagai dokumentasi hidup: penamaan, Given-When-Then, pesan gagal yang berguna

