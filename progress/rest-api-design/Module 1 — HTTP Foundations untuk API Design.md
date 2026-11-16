# HTTP Foundations untuk API Design

   # Anatomy HTTP request/response

   HTTP = teks tiga bagian: start line, header, body (dipisah baris kosong).

   REST adalah gaya arsitektur yang memakai semantik HTTP, bukan protokol terpisah.

   jangan di lakukan:
   1. Selalu return 200 dan taruh status asli di body (success: false).
   2. Nganggap REST = "pakai JSON dan URL", padahal intinya manfaatin semantik HTTP.

   Tes Feynman: REST bukan protokol baru, tapi cara memakai aturan HTTP yang sudah ada secara benar supaya semua tool di jalan ngerti API lu.

   Aturan Emas: Jangan bikin aturan sendiri kalau protokolnya sudah punya kata untuk itu.

   Terapin: sebelum nambah field success: false atau header custom, cek dulu apakah status code/header HTTP standar sudah bisa bilang hal yang sama.

   # HTTP methods & safety/idempotency semantics (RFC 9110)

   Method HTTP adalah janji: GET safe, PUT/DELETE idempotent, POST bukan keduanya.

   Idempotent artinya state akhir sama kalau dikirim ulang, jadi aman di-retry; ini alasan retry otomatis bisa jalan.

   Tes Feynman: method HTTP adalah janji tentang akibat sebuah request kalau dikirim ulang, dan tool di sepanjang jalan bergantung pada janji itu.

   Aturan Emas: Sebelum milih method, tanya: "kalau request ini terkirim dua kali, apa yang rusak?"

   Terapin: pas nulis endpoint pembayaran atau order baru, jawab pertanyaan itu dulu; kalau jawabannya "ke-charge dobel", pasang Idempotency-Key sebelum merge.

   # HTTP status code by category (1xx-5xx)

   | **Kategori** | **1xx** |
   |---|---|
   | **Artinya** | Informasional, jarang disentuh API biasa |
   | **Contoh umum** | 100 Continue |

   | **Kategori** | **2xx** |
   |---|---|
   | **Artinya** | Berhasil |
   | **Contoh umum** | 200 OK, 201 Created, 202 Accepted, 204 No Content |

   | **Kategori** | **3xx** |
   |---|---|
   | **Artinya** | Pindah / pakai cache |
   | **Contoh umum** | 301, 304 Not Modified |

   | **Kategori** | **4xx** |
   |---|---|
   | **Artinya** | Client salah, jangan retry sebelum diperbaiki |
   | **Contoh umum** | 400, 401, 403, 404, 409, 422, 429 |

   | **Kategori** | **5xx** |
   |---|---|
   | **Artinya** | Server salah, retry mungkin berhasil |
   | **Contoh umum** | 500, 502, 503, 504 |

   Tes Feynman: status code adalah sinyal yang dibaca mesin untuk memutuskan langkah berikutnya, jadi harus jujur soal siapa yang salah.

   Aturan Emas: Taruh hal terpenting di tempat yang dibaca semua orang, bukan di tempat yang cuma lu yang baca.

   Terapin: sebelum return dari handler, tanya "client harus ngapain kalau dapat ini?"; kalau jawabannya beda dari apa yang disiratkan status code lu, ganti status code-nya.

   # Header penting untuk API

   Header adalah kanal metadata: Content-Type/Accept untuk format, Location untuk resource baru, Authorization untuk kredensial.

   | **Header** | **Content-Type** |
   |---|---|
   | **Arah** | Request & Response |
   | **Fungsi** | Format body yang dikirim (`application/json`) |

   | **Header** | **Accept** |
   |---|---|
   | **Arah** | Request |
   | **Fungsi** | Format response yang diminta client |

   | **Header** | **Authorization** |
   |---|---|
   | **Arah** | Request |
   | **Fungsi** | Kredensial (`Bearer <token>`) |

   | **Header** | **Location** |
   |---|---|
   | **Arah** | Response |
   | **Fungsi** | URL resource baru, dipakai bareng 201 atau redirect 3xx |

   | **Header** | **ETag, If-None-Match** |
   |---|---|
   | **Arah** | Response, Request |
   | **Fungsi** | Versi resource. Jika cocok, server mengembalikan `304 Not Modified` tanpa body |

   | **Header** | **Cache-Control** |
   |---|---|
   | **Arah** | Response (dan Request) |
   | **Fungsi** | Menentukan berapa lama resource boleh di-cache dan oleh siapa |

   Tes Feynman: header adalah metadata yang dibaca mesin untuk memutuskan cara memperlakukan body, sehingga data yang sama nggak perlu dikirim dua kali.

   Aturan Emas: Sebelum ngirim sesuatu yang besar, tanya dulu "orang di seberang sudah punya ini belum?"

   Terapin: tiap bikin GET yang datanya stabil, tambahkan ETag dan Cache-Control sebelum bilang endpoint selesai.

   # Statelessness

   Stateless berarti server nggak menyimpan state sesi klien di antara request. Setiap request berdiri sendiri: identitas, parameter, dan konteks dibawa client lewat header, URL, atau body. Ini salah satu constraint utama REST.

   jangan di lakukan:
   1. Nyimpen sesi login di memori proses (HashMap, HttpSession default) lalu nambah instance.
   2. Mengandalkan sticky session sebagai solusi permanen.
   3. Mengira stateless = tanpa database. Yang dilarang cuma state sesi klien di memori server.
   4. Nyimpen data sensitif di token tanpa enkripsi. Signed itu bukan secret: siapa pun bisa baca isinya, cuma nggak bisa mengubahnya.
   
   Tes Feynman: server stateless nggak mengingat percakapan, karena tiap request membawa semua yang dibutuhkan untuk dipahami, jadi server mana pun bisa melayaninya.

   Aturan Emas: Jangan simpan ingatan penting di satu tempat yang bisa hilang; bawa atau taruh di tempat bersama.

   Terapin: sebelum nambah instance, cabut satu instance di staging. Kalau ada user yang terdampak, berarti ada state tersembunyi yang perlu dipindah.


