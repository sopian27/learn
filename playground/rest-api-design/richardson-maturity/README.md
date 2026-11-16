# Richardson Maturity Model Demo

Run: `java RichardsonDemo.java`

Aksi yang sama (batalin order) dipanggil di 4 level. Yang diliat:

* L0: order 99 nggak ada, tapi status tetap `200`. Errornya ngumpet di body.
* L1: sudah ada URI per order, tapi masih `POST` dan masih `200`.
* L2: `DELETE` + `204` / `404`. Client, cache, dan monitoring bisa baca status tanpa parsing body.
* L3: response bawa `_links` (mis. `refund`), client nggak hardcode URL.
