# springdoc Annotations Demo (Lesson 10.2)

Run: `java SpringdocAnnotationsDemo.java`

Look for: spec dibangun dengan membaca anotasi di kode (cara kerja springdoc, versi mini tanpa dependency). `POST /v1/orders` bisa balikin 422 di runtime, tapi tidak ada `@ApiResponse` untuk itu, jadi muncul peringatan "tidak terdokumentasi". Itu drift yang sama yang bikin client kaget di production.
