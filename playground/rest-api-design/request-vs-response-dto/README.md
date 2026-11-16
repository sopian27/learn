# Request DTO vs Response DTO Demo (Lesson 4.2)

Run: `java RequestVsResponseDemo.java`

Tanpa Spring/Jackson; binder mini berbasis reflection meniru Jackson (field yang dikenal tipe target akan terisi).

Yang diliat:

* Satu `UserDto` dua arah -> client bisa mengisi `role` dan `id` (mass assignment), dan `password` ikut balik di response.
* Dipisah `CreateUserRequest` / `UserResponse` -> field terlarang tidak punya tempat di tipe input, dan field rahasia tidak ada di tipe output.
