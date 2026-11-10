# Transactional Propagation Demo (Lesson 8.3)

Simulasi mini (tanpa Spring/DB) buat lihat beda `REQUIRED`, `REQUIRES_NEW`, `NESTED`.

Jalankan: `java PropagationDemo.java`

Yang dilihat:
1. Audit di `REQUIRED` ikut hilang waktu order di-rollback.
2. Audit di `REQUIRES_NEW` tetap tersimpan walau order gagal.
3. Inner `REQUIRED` gagal lalu exception ditelan: outer tetap gagal commit (rollback-only).
4. Inner `NESTED` gagal lalu ditelan: hanya bagian inner yang batal, order commit.

Ini model sederhana, bukan implementasi asli Spring.
