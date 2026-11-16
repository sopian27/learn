# HTTP Status Code: Jujur vs "Selalu 200"

Companion Lesson 1.3 (REST API Design). Demo, bukan latihan.

## Run

```
java HttpStatusCodesDemo.java
```

## Yang diliat

* `/bad`: order tidak ada tapi status `200`. Client (dan monitoring) mengira sukses.
* `/good`: `201` + `Location`, `400` (format salah), `404` (tidak ada).
* `/good/report`: `503` dua kali lalu `200`. Retry hanya masuk akal untuk 5xx, bukan 4xx.
