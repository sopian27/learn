# Validation & Error Response Demo (Lesson 5.3)

Jalankan: `java ValidationErrorDemo.java` (port 8091)

Yang diliat, 3 input ke dua endpoint:
- `/bad/orders`: validasi lempar exception, semua jadi 500 + stack trace mentah. Input salah klien kebaca "server rusak", cuma error pertama yang muncul.
- `/good/orders`: input salah jadi 400 dengan `code` stabil, `traceId`, dan `fieldErrors` lengkap (amount dan email sekaligus). Input valid sama-sama 201.

Validator ditulis manual supaya jalan tanpa Spring; di Spring Boot perannya diganti `@Valid` + `@RestControllerAdvice`.
