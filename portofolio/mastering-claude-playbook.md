# Mastering Claude — Playbook Pribadi

Enterprise Project (Capstone) dari course `courses/mastering-claude/roadmap.md`. Dokumen keputusan pribadi hasil 8 module: kapan pakai mekanisme mana, kapan vibe coding vs spec-driven, dan bukti ROI dari kasus nyata (bukan klaim tanpa data).

Acceptance Criteria dipenuhi: AC1 (dokumen ini), AC2 (automation nyata, §3), AC3 (ROI case study, §4), AC4 (review, §6), AC5 (lokasi `portofolio/`).

---

## 1. Decision Framework — CLAUDE.md vs Skill vs Plugin vs Hook

Empat mekanisme, empat scope beda. Salah pilih = context boros atau disiplin gak ke-enforce.

| Mekanisme | Kapan pakai | Kenapa bukan yang lain |
|---|---|---|
| **CLAUDE.md** | Instruksi yang selalu relevan di project ini, tiap sesi, <200 baris (Module 2.2) | Skill: kalau taruh instruksi task-spesifik di CLAUDE.md, dia numpuk di context tiap sesi walau gak relevan — boros token |
| **Skill** | Instruksi task-spesifik yang cuma perlu ke-load pas task itu relevan (progressive disclosure, Module 4.1) | Plugin: kalau cuma 1 skill dan gak perlu didistribusikan/dibundle, bikin plugin itu overhead struktural tanpa manfaat |
| **Plugin** | Membundel skill+hook+MCP jadi 1 unit yang bisa didistribusikan/di-install ulang (Module 4.3) — dipakai kalau ada ≥2 komponen yang harus jalan bareng (skill + hook, misalnya) | CLAUDE.md: gak bisa bundle hook/MCP, gak portable antar-project |
| **Hook** | Enforcement otomatis, event-driven, TIDAK bergantung ke keputusan agent bahwa sesuatu "relevan" (Module 3.2, dan §3 di bawah) | Skill: skill cuma instruksi — agent BISA lupa/skip. Hook jalan tiap event match, gak bisa di-skip agent |

**Prinsip inti:** Skill = *reminder yang bisa diabaikan*. Hook = *gate yang gak bisa diabaikan*. Pilih hook kalau stakes cukup tinggi sampai "agent lupa ikutin instruksi" gak bisa ditoleransi.

---

## 2. Decision Matrix — Vibe Coding vs Spec-Driven

Dari Module 7.2 & 7-mini-project/8-mini-project (bukti nyata, bukan teori doang).

| Stakes \ Reversibility | Reversible (gampang di-undo) | Irreversible (production, data, credential) |
|---|---|---|
| **Rendah** (throwaway script, prototype, tool pribadi) | **Vibe coding.** Speed-to-code menang, verifikasi mahal relatif ke value. Contoh: 7-mini-project scraper awal. | Jarang terjadi kombinasi ini — kalau irreversible, stakes biasanya ikut naik. |
| **Tinggi** (production code, dipakai orang lain, ada data/security boundary) | Spec-driven, tapi verifikasi boleh proporsional (gak perlu semua 3 gerbang tiap commit kecil). | **Spec-driven penuh + 3 gerbang wajib** (diff review, test coverage, security review — Module 8.3). Contoh: 8-mini-project. |

**Sinyal beralih dari vibe ke spec-driven di tengah sesi:** begitu output mulai "terlihat jalan tapi gak jelas apakah benar" (program run tanpa error ≠ program benar — pelajaran inti 7-mini-project), itu sinyal untuk berhenti, define Definition of Done eksplisit, baru lanjut.

---

## 3. Automation Piece (AC2) — TDD Gate di `tdd-toolkit`

Skill `tdd-workflow` (Module 4.2) cuma instruksi — agent BISA melewatinya kalau lupa/terburu-buru. Untuk Enterprise Project, `tdd-toolkit` (`playground/mastering-claude/plugins/tdd-toolkit/`, v1.0.0 → **v1.1.0**) di-extend dengan 2 hook nyata supaya disiplin RED-GREEN jadi *enforced*, bukan cuma *diminta*.

### Mekanisme

* **`PostToolUse` matcher `Bash`** (`hooks/record-test-result.py`) — tiap command yang match pola test-runner (`mvn ... test`, `pytest`, `npm test`, `gradle ... test`, `go test`, `jest`) di-parse output-nya (summary `mvn` `Tests run: X, Failures: Y, Errors: Z`, `pytest` `N failed`/`N passed`, fallback keyword `BUILD FAILURE`/`BUILD SUCCESS`). Hasil ditulis ke `.claude/tdd-state.json`: `{"status": "red"|"green", ...}`. Command yang gak match test-runner atau output ambigu **tidak** menimpa state lama (menghindari state salah karena tebakan).
* **`PreToolUse` matcher `Edit|Write`** (`hooks/enforce-red-first.py`) — kalau file target berekstensi kode (`.java/.py/.js/.ts/.jsx/.tsx/.go/.rb`) dan BUKAN file test (nama mengandung `test`/`spec`), dan `.claude/tdd-state.json` belum ada sama sekali → **block** (`exit(2)`, stderr menjelaskan alasan). Edit ke file test selalu diizinkan (justru itu yang harus terjadi duluan di RED phase).

### Known Limitation (jujur, bukan overclaim)

Ini gate MVP: "minimal satu test run harus terobservasi sebelum implementation edit pertama" — **bukan** full state machine RED→GREEN→REFACTOR yang mem-block tiap edit implementasi setelah GREEN (itu akan menghalangi refactor step yang sah). Scope sengaja dibatasi supaya hook ini tetap benar untuk kasus yang jelas (vibe-coding tanpa test sama sekali) tanpa berpura-pura menegakkan disiplin yang lebih halus (RED-tepat-sebelum-tiap-edit) yang belum bisa dibedakan reliable dari sinyal command+output saja.

**Celah konkret:** state tidak terikat ke file yang di-edit. Satu `mvn test` yang lolos (GREEN) di awal sesi — bahkan untuk modul yang sama sekali tidak berhubungan — sudah cukup membuat `.claude/tdd-state.json` ada, sehingga gate lolos untuk SEMUA edit implementasi berikutnya di sesi itu, termasuk ke file yang belum pernah punya test sama sekali. Gate ini menjegal kasus "vibe-coding tanpa test observation sama sekali", bukan kasus "test observation ada tapi tidak relevan ke file yang diedit" — itu di luar scope v1.1.0.

### Verifikasi (dijalankan nyata, 2026-08-16)

| Skenario | Input | Hasil |
|---|---|---|
| Edit file kode, belum ada state | `enforce-red-first.py` < `{"tool_input":{"file_path":"src/Foo.java"}}` | `exit=2`, stderr: "no test run observed yet..." |
| Edit file test | `{"tool_input":{"file_path":"src/FooTest.java"}}` | `exit=0` (diizinkan) |
| Edit file non-kode | `{"tool_input":{"file_path":"README.md"}}` | `exit=0` (diizinkan, di luar scope guard) |
| `mvn test` gagal (`Failures: 2`) | `record-test-result.py` | `.claude/tdd-state.json` → `status: "red"` |
| `mvn test` sukses (`Failures: 0`) | idem | state → `status: "green"` (overwrite) |
| `pytest` gagal (`2 failed, 3 passed`) | idem | state → `status: "red"` |
| `git status` (bukan test command) | idem | state **tidak berubah** (masih hasil pytest sebelumnya) |
| Edit file kode setelah state ada | `enforce-red-first.py` | `exit=0` (gate lolos begitu ada observasi test apapun) |

Semua 7 skenario dijalankan via `echo <json> \| python <script>` dan exit code/isi `.claude/tdd-state.json` dicek langsung, bukan diasumsikan.

---

## 4. ROI Case Study (AC3) — Scraper: Vibes-only vs Disciplined

Sumber: `playground/mastering-claude/7-mini-project/` (vibes-only, Module 7) vs `playground/mastering-claude/8-mini-project/` (disciplined, Module 8) — task identik (CLI scraper produk → CSV), dikerjakan 2 cara berbeda secara nyata, bukan simulasi.

| Metrik | Vibes-only | Disciplined |
|---|---|---|
| **Test ditulis** | 0 | 14 test |
| **Test coverage** | Tidak diukur (tidak ada test) | 99% line coverage (melebihi target 92% dari 8.1/8.3) |
| **Defect ditemukan sebelum "selesai"** | 0 (subjektif — dianggap selesai begitu run tanpa error) | 1 real bug (mojibake "Â£" — `requests.text` fallback ISO-8859-1 padahal body UTF-8), ketemu lewat diff review |
| **Defect ditemukan setelah "selesai"** | 1 kritis: CSV 0 produk karena selector ditebak salah (baru ketahuan pas refleksi, bukan real-time) | 0 (selector divalidasi ke struktur HTML nyata sebelum implementasi) |
| **Security review** | Tidak dilakukan | Dilakukan (skill `security-review`), 0 finding HIGH/MEDIUM |
| **Confidence subjektif (self-report)** | Rendah — "tidak cukup percaya membawa ke production" | Lebih tinggi — bukan karena agent lebih pintar, tapi karena ada evidence dari 3 mekanisme verifikasi independen |
| **Token/biaya per sesi** | **Tidak diukur** (gap, lihat Known Limitation) | **Tidak diukur** (gap, lihat Known Limitation) |

**Catatan waktu (di luar tabel, sengaja):** log asli mencatat "~32 detik" (vibes-only) vs "~57 detik" (disciplined) sampai titik berhenti masing-masing. Angka ini **tidak dimasukkan ke tabel** karena self-reported, tidak diukur pakai timer konsisten, dan kemungkinan mengukur hal berbeda di tiap sesi (durasi ketik refleksi ikut campur) — memformat angka sementah ini sebagai baris tabel akan menyiratkan presisi yang tidak ada. Diperlakukan sebagai sinyal kasar saja: disciplined terasa lebih lama, bukan bukti rasio waktu yang bisa dipakai buat estimasi proyek nyata.

### Interpretasi

Vibe coding menang di *speed-to-code*: satu prompt, satu implementasi, friction rendah. Tapi "program jalan tanpa error" bukan bukti "program benar" — kegagalan kritis (0 produk) baru ketahuan lewat refleksi manual, bukan lewat sinyal apapun dari sistem. Disciplined process lebih lambat di titik ini (~2x self-reported), tapi defect yang sama kelasnya (selector gak valid) tertangkap SEBELUM diklaim selesai, karena Definition of Done eksplisit mewajibkan validasi struktur HTML nyata.

**Kesimpulan yang sudah dicatat di Module 8** dan dikonfirmasi ulang di sini: *vibe coding mengoptimalkan speed-to-code, disciplined AI-assisted engineering mengoptimalkan speed-to-verified-result.* Trade-off ini konsisten dengan Decision Matrix §2 — task ini masuk kuadran "stakes rendah, reversible" di awal (cocok vibe coding untuk draft pertama), tapi begitu ada niat mempercayai hasilnya (bawa ke pemakaian nyata), itu bergeser ke kuadran yang butuh spec-driven.

### Known Limitation — Token/Cost Belum Diukur

Module 2.2 sudah mengajarkan audit `/usage` per sesi, tapi sesi Module 7 & 8 tidak dijalankan dengan boundary sesi yang dipisah khusus untuk membandingkan token cost vibes-only vs disciplined — jadi baris "token/biaya" di tabel di atas jujur kosong, bukan diisi angka karangan. **Rekomendasi untuk case study ROI berikutnya:** jalankan `/usage` sebelum & sesudah tiap fase (vibes-only, disciplined) di sesi terpisah, supaya token cost bisa dibandingkan dengan bukti, bukan estimasi.

---

## 5. Ringkasan Playbook — Aturan Praktis

1. Instruksi selalu-relevan → CLAUDE.md (jaga <200 baris). Instruksi task-spesifik → Skill.
2. ≥2 komponen (skill+hook, skill+MCP) yang perlu jalan bareng & portable → bundle jadi Plugin.
3. Disiplin yang gak boleh bisa "kelewat" oleh agent yang lupa → Hook, bukan Skill.
4. Stakes rendah + reversible → vibe coding boleh, buat draft cepat.
5. Stakes tinggi ATAU irreversible → spec-driven wajib, minimal Definition of Done eksplisit; kalau production-grade, 3 gerbang penuh (diff, test, security).
6. Sinyal untuk berhenti vibe coding di tengah jalan: "kelihatan jalan tapi gak yakin benar" — itu tandanya define Definition of Done, baru lanjut.
7. Klaim ROI/waktu-hemat harus dibuktikan dengan data terukur (waktu, coverage, defect count) — kalau belum terukur (token cost), akui sebagai gap, jangan dikarang.

---

## 6. Review (AC4)

Review mentor, Universal Review Rubric (`ai-los/CORE_LOS.md`), 2026-08-16.

**Strengths:**
* Tiap klaim di §1-§4 dijangkarkan ke bukti nyata (log module, hasil test hook, file `8-mini-project.review.md`) — bukan generalisasi abstrak.
* AC2 diverifikasi sungguhan (7 skenario, exit code + isi state file dicek), bukan diasumsikan jalan.
* Known Limitation ditulis eksplisit di 2 tempat (gate hook, token cost belum terukur) — tidak overclaim kekuatan automation atau kelengkapan data ROI.

**Weaknesses:**
* Gate hook (§3) tetap punya celah trivial: satu test lolos yang gak berhubungan bikin gate lolos untuk semua file — sudah didokumentasikan (revisi), tapi ini artinya AC2 "enforcement" masih lemah untuk multi-file session yang realistis.
* ROI case study (§4) cuma 1 task (scraper) — n=1, belum tentu generalize ke task lain yang lebih besar/kecil stakes-nya.
* Token/cost ROI belum ada data sama sekali (diakui, tapi tetap gap terhadap deskripsi awal Enterprise Project "ukur ROI waktu vs biaya token vs kualitas").

**Actionable Improvements:**
* Kalau dipakai serius ke depan: ikat state ke path file yang diedit (bukan global session state) supaya celah di atas tertutup — di luar scope v1.1.0 tapi jadi kandidat v1.2.0 jelas.
* Jalankan minimal 1 case study ROI lagi dengan `/usage` dibatasi per fase, supaya baris token/cost di §4 punya angka nyata, bukan kosong.

**Score: 90/100.** Playbook ini kuat di kejujuran evidence & verifikasi nyata (persis disiplin yang diajarkan Module 8), turun poin karena cakupan ROI case study sempit (n=1, tanpa data biaya) dan automation piece masih MVP dengan celah yang terdokumentasi tapi belum ditutup.
