| Aspek                  | Vibes-only                                                  | Disciplined                                                                  |
| ---------------------- | ----------------------------------------------------------- | ---------------------------------------------------------------------------- |
| **Intent**             | Natural-language vague; definisi selesai tidak eksplisit    | Definition of Done jelas: fetch → extract → validate → CSV → truthful status |
| **Implementation**     | 1-shot, tanpa klarifikasi                                   | Implementasi mengikuti spec + constraints                                    |
| **Failure**            | TLS gagal; setelah diperbaiki, CSV hanya header             | Failure mode dibuat eksplisit dan `0 products` diperlakukan sebagai failure  |
| **Root cause**         | Tidak diketahui sampai dilakukan observasi                  | Struktur HTML divalidasi sebelum extraction                                  |
| **Verification**       | Program bisa run → mudah dianggap berhasil                  | 3 gerbang: diff review, test coverage, security review                       |
| **Security**           | Belum dipikirkan secara sistematis                          | Secrets, injection, SSRF boundary, dependency, logging direview              |
| **Output correctness** | Tidak terbukti; CSV header-only sempat dianggap hasil       | Ada constraint bahwa CSV harus memiliki data row sebelum dianggap sukses     |
| **Confidence**         | Rendah karena tidak tahu apa yang sebenarnya dilakukan tool | Lebih tinggi karena ada evidence dari review + test + security gate          |

### Refleksi

Vibes-only : unggul di kecepatan dan friction yang sangat rendah. Saya bisa mendapatkan implementasi dalam satu shot, tetapi kegagalan menunjukkan kelemahan utamanya: kode yang berhasil dieksekusi belum berarti requirement terpenuhi. Bahkan setelah TLS diperbaiki, scraper tetap menghasilkan CSV kosong karena selector tidak tervalidasi.

Disciplined approach :  lebih lambat karena ambiguity harus diselesaikan sebelum implementation. Namun kualitasnya meningkat karena failure condition, edge case, security boundary, dan Definition of Done dibuat eksplisit. Yang paling penting, keberhasilan tidak lagi didefinisikan sebagai *"program tidak error"*, tetapi sebagai **evidence bahwa output memenuhi spec**.

Vibes-only: sekitar 32 seconds sampai berhenti/refleksi.
Disciplined: sekitar 57 seconds sampai tiga gerbang selesai.

Setelah vibes-only, saya **tidak cukup percaya** untuk membawa hasil langsung ke production karena saya belum tahu apakah implementasinya benar. Setelah disciplined process, confidence saya **lebih tinggi**, bukan karena agent tiba-tiba lebih pintar, tetapi karena hasilnya melewati beberapa mekanisme verifikasi independen. Saya tetap tidak menganggapnya “pasti aman”; saya menganggapnya **lebih terbukti dan lebih layak dipercaya**.

### Inti pembelajaran mini-project:

Vibe coding mengoptimalkan speed-to-code. Disciplined AI-assisted engineering mengoptimalkan speed-to-verified-result.
