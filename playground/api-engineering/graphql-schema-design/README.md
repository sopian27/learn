# GraphQL Schema Design Demo (Lesson 3.1)

Run: `java SchemaDesignDemo.java`

Lihat:
- `REST`: server tentuin bentuk, client dapat semua field walau cuma butuh sebagian.
- `GraphQL`: client pilih field, dapat persis itu. Field yang nggak ada di schema ditolak dengan error jelas.
- `schema.graphqls`: contoh schema-first (Query, Mutation, Subscription, enum, input, payload) buat dibaca.

Simulasi in-memory tanpa library GraphQL. Ada self-check; kalau meleset program melempar AssertionError.
