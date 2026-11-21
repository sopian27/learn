# Service Unit Test Demo (Lesson 11.1)

Run: `java ServiceUnitTestDemo.java`

Look for: di versi BUG, test "[jelek] cancel memanggil save()" tetap PASS padahal ada bug, sedangkan "[bagus] SHIPPED ditolak" FAIL. Di versi FIXED semua PASS. Di Spring, `FakeRepo` diganti `@Mock OrderRepository` (Mockito) dan `saved` jadi `verify(repo, never()).save(any())`.
