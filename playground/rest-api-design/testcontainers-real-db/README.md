# Testcontainers: DB sungguhan vs H2

Run: `java TestcontainersRealDbDemo.java`

Lihat: DB tanpa partial unique index (gaya H2 yang schema-nya digenerate dari entity) membiarkan order PENDING ganda lolos. DB dengan index (gaya Postgres + Flyway) menolak.

Mapping ke Spring: `new Db(true)` = `PostgreSQLContainer` + Flyway migration yang sama dengan production. Demo ini plain Java, bukan Docker/Testcontainers asli.
