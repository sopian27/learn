# URI Naming Demo

Run: `java UriNamingDemo.java`

Yang diliat:

* Linter kecil menandai URI yang melanggar `standards/API_STANDARDS.md`: camelCase/PascalCase, underscore, verb, singular, trailing slash.
* `{id}` dilewatin: itu parameter, bukan nama resource.
* `GET /Orders` balas `404` sedangkan `GET /orders` balas `200`. Path itu case-sensitive, jadi huruf besar bikin resource beda, bukan alias.
* Linter cuma heuristik (cek plural = berakhiran `s`). Buat proyek beneran pakai linter OpenAPI.
