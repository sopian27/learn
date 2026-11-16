# Enam Constraint REST dalam Satu Demo

Companion Lesson 2.1 (REST API Design). Demo, bukan latihan.

## Run

```
java RestConstraintsDemo.java
```

## Yang diliat

* Request tanpa `Authorization` dapat `401`: server nggak ingat siapa-siapa (stateless).
* `GET /orders/42` dapat `200` + `ETag` + `_links`; kirim ulang dengan `If-None-Match` dapat `304` tanpa body (cacheable).
* Header `Via: 1.1 gateway` muncul: client cuma kenal gateway, backend tersembunyi (layered system).
* `/widget.js` = server ngirim kode (code-on-demand), satu-satunya constraint yang opsional.
