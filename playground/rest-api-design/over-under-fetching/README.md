# Over-fetching vs Under-fetching Demo (Lesson 4.4)

Run: `java OverUnderFetchingDemo.java`

Demo tanpa Spring/DB; ukuran response = panjang string JSON, round trip dihitung manual.

Yang diliat:

* DTO salinan tabel -> response gemuk (over-fetching: notes dan itemsJson nggak dipakai layar) dan nama customer nggak ada, jadi butuh request tambahan (under-fetching).
* DTO dirancang dari layar daftar -> 1 round trip, karakter jauh lebih sedikit.
* Layar detail tetap butuh endpoint sendiri: satu DTO per kebutuhan.
