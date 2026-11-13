# Mock Bohong vs Constraint Asli (Lesson 10.3)

Run: `java MockLiesDemo.java`

Yang diliat:

- Service daftar user nggak nangani email duplikat.
- Test dengan mock yang selalu sukses: hijau. Bug lolos ke production.
- Test dengan repo yang menegakkan unique constraint: merah, bug ketahuan.

Aturan DB di demo ini disimulasikan. Di proyek nyata, aturan itu datang dari Postgres asli lewat Testcontainers (butuh Docker dan dependency `org.testcontainers:postgresql`, nggak dipasang di demo ini).
