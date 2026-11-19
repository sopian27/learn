# Object-Level Authorization (BOLA/IDOR) Demo

Companion Lesson 9.2.

Run: `java ObjectLevelAuthorizationDemo.java` (Java 17+)

Yang dilihat:
- `vulnerable`: Budi (customer) ganti id di URL dan dapat order Wahyu (200). Login valid, tapi akses object tidak dicek.
- `secure`: order orang lain dibalas 404; order sendiri dan akses ADMIN tetap 200.
