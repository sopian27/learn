# Resource Modeling Demo (Order Management)

Run: `java ResourceModelingDemo.java`

Yang diliat:

* `POST /orders` dan `POST /orders/1/items`: `201` + header `Location`. Item nested karena hidupnya ikut order.
* `GET /orders/999/items`: `404`, parent nggak ada berarti child nggak ada.
* Item nyimpen `"product":"/products/10"` (referensi), bukan nested `/orders/1/items/1/products/10`. Product berdiri sendiri.
* `GET /orders?customerId=5`: relasi lintas resource lewat filter, bukan `/customers/5/orders/...` yang makin dalam.
* `POST /orders/1/payments`: aksi "bayar" jadi noun (resource baru), bukan `/payOrder`.
