# Contract testing (Pact) - simulasi

Jalankan: `java PactContractDemo.java`

Yang dilihat:
- Consumer (`mobile-app`) menulis kontrak: field `id`, `status`, `total`.
- Provider v1 lolos. Provider v2 rename `total` jadi `amount`, verifikasi gagal sebelum deploy.
- Field ekstra (`internalNote`) tidak bikin gagal: kontrak cuma cek apa yang dipakai consumer.

Catatan: simulasi in-memory, bukan library Pact asli dan tidak ada Pact Broker.
