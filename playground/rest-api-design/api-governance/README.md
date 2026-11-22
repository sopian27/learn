# API Governance Demo (Lesson 14.3)

Run: `java ApiGovernanceDemo.java`

Lihat:
- 3 spec dilint: hanya `/v1/orders` PASS. Breaking change tanpa approval ikut gagal.
- Lifecycle DESIGN -> REVIEW -> PUBLISHED -> DEPRECATED -> SUNSET. Lompat PUBLISHED -> SUNSET ditolak.

Simulasi data statis, bukan baca OpenAPI beneran. Versi nyata: Spectral di CI.
