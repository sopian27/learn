# Over-Mocking vs Behavior Test (Lesson 10.2)

Run: `java OverMockingDemo.java`

Yang diliat:

- Logic checkout direfactor (memoize harga), perilaku luar sama.
- Test yang ngecek interaksi ("repo harus dipanggil 3 kali") jadi merah, padahal nggak ada bug.
- Test yang ngecek hasil dengan stub tetap hijau.

Demo ini pakai class tangan, bukan Mockito, biar jalan tanpa dependency. Konsepnya sama persis dengan `verify(repo, times(3))` di Mockito.
