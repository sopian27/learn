# Failure Modes Demo (Lesson 12.1)

Run: `java FailureModesDemo.java` (Java 25).

Yang dilihat: pool 4 thread, 12 request nyangkut di dependency yang hang 2 detik. Request `/health` yang sehat harus antre di belakangnya (~6000 ms). Dengan timeout 200 ms, thread cepat bebas dan `/health` kebalas (~600 ms). Angka pastinya bisa beda sedikit, polanya yang penting.

Ini simulasi kecil: pool = thread Tomcat, sleep = HTTP call yang hang.
