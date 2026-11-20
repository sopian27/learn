# Contract-First Demo (Lesson 10.3)

Run: `java ContractFirstDemo.java`

Look for: kontrak disetujui dulu, kode diverifikasi terhadap kontrak. `GET /v1/orders/{id}` gagal karena backend ganti nama `total` jadi `totalAmount` tanpa mengubah kontrak. Di code-first, perubahan ini lolos diam-diam dan merusak consumer. Di contract-first, build gagal dan perubahan harus lewat persetujuan consumer.
