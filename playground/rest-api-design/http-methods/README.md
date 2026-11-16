# HTTP Methods: Safe dan Idempotent

Companion Lesson 1.2 (REST API Design). Demo, bukan latihan.

## Run

```
java HttpMethodsDemo.java
```

## Yang diliat

Tiap method dikirim 2x (simulasi retry setelah timeout):

* `GET` x2: state tidak berubah (safe).
* `PUT` x2: state akhir sama (idempotent).
* `POST` x2: dua order terbentuk (tidak idempotent, duplikat).
* `DELETE` x2: order hilang, state akhir sama (idempotent).
