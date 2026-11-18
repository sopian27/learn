# Sorting Whitelist Demo (Lesson 7.4)

Run: `java SortingWhitelistDemo.java`

Look for: tanpa parameter sort, server pakai default `created` desc. Dua order dengan `total` sama tetap berurutan stabil berkat tie-breaker `id`. `customerNote` ditolak karena tidak ada di whitelist (di DB nyata, kolom itu tidak terindeks). Di Spring, pola yang sama dipakai untuk menyusun `Sort` dari parameter yang sudah divalidasi.
