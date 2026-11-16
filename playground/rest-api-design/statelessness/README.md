# Statelessness: Session di Memori vs Token Mandiri

Companion Lesson 1.5 (REST API Design). Demo, bukan latihan.

## Run

```
java StatelessnessDemo.java
```

## Yang diliat

* `/stateful`: login di instance A, request ke instance B dapat `401`. Session cuma ada di memori A.
* `/stateless`: token yang sama diterima A dan B (`200`), karena tiap instance bisa verifikasi sendiri. Token dirusak dapat `401`.
* Token di demo ini versi minimal (HMAC). JWT beneran dibahas di modul security.
