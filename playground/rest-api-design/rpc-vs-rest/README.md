# RPC-style vs REST-style Demo

Run: `java RpcVsRestDemo.java`

Yang diliat:

* `PATCH /orders/3 {"status":"DELIVERED"}`: diterima `200`, padahal order baru `NEW`. PATCH naif nggak punya aturan transisi.
* `PATCH /orders/2 {"status":"PAID"}`: order `SHIPPED` bisa mundur. Client memegang kendali state.
* `POST /orders/1/cancel`: `200`. Diulang: `200` lagi (idempotent).
* `POST /orders/2/cancel` pada order `SHIPPED`: `409`. Aturan bisnis hidup di server, bukan di client.
* PATCH naif ini sengaja disederhanakan. PATCH bisa divalidasi juga, tapi aturannya harus ditulis sendiri.
