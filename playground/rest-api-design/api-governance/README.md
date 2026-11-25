# API Governance Demo (Lesson 14.3)

Run: `java ApiGovernanceDemo.java`

Lihat:
- 3 spec dilint: hanya `/v1/orders` PASS. Breaking change tanpa approval ikut gagal.
- Lifecycle DESIGN -> REVIEW -> PUBLISHED -> DEPRECATED -> SUNSET. Lompat PUBLISHED -> SUNSET ditolak.

Simulasi data statis, bukan baca OpenAPI beneran. Versi nyata: Spectral di CI.

## Spectral beneran

Ruleset: `.spectral.yaml` (extends `spectral:oas` + 4 rule custom: path-versioned, path-no-verb, owner-required, error-problem-json).

Run (butuh Node):
```
npx @stoplight/spectral-cli lint specs/good.yaml   # harus bersih dari error
npx @stoplight/spectral-cli lint specs/bad.yaml    # harus gagal
```
Di VS Code: install ext `stoplight.spectral`. Di CI: step lint di atas, exit code != 0 memblok merge.
