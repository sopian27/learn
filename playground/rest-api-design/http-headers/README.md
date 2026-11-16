# Header Penting API

Companion Lesson 1.4 (REST API Design). Demo, bukan latihan.

## Run

```
java HttpHeadersDemo.java
```

## Yang diliat

* `POST` tanpa `Content-Type`: `415`. Dengan `application/json`: `201` + `Location`.
* `GET` dengan `Accept: application/xml`: `406`.
* `GET` pertama: `200` + `ETag` + `Cache-Control`. Ulang dengan `If-None-Match`: `304`, body 0 byte.
