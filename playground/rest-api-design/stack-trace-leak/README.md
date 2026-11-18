# Stack Trace Leak Demo (Lesson 6.1)

Run (Java 17+, tanpa dependency):

```bash
java StackTraceLeakDemo.java
```

Yang diliat:

* Versi bocor: body response memuat host DB `10.0.3.7`, nama database, user, dan nama class/baris dari stack trace.
* Versi aman: klien cuma dapat pesan generik + `errorId`; detail lengkap masuk log server dengan `errorId` yang sama, jadi tim bisa cari lognya.
