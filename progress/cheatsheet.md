# Cheat Sheet

Referensi cepat per topik. Update seiring topik baru dipelajari.

---

## Git — Squash Merge Antar Remote (Internal ↔ External GitLab)

```bash
git remote -v                                       # cek remote yang terhubung
git fetch <remote>                                   # ambil update tanpa merge
git merge <branch>                                   # merge biasa (ada merge commit, history lengkap)
git merge --squash <branch>                          # gabungkan isi branch, tanpa merge commit
git commit -m "Release: ringkasan"                   # wajib manual setelah --squash
git rebase -i HEAD~n                                 # squash manual n commit terakhir
git push <remote> <branch> --force-with-lease         # push aman setelah history ditulis ulang
git branch -D <branch> && git checkout -b <branch>    # recreate branch bersih (hindari conflict berulang)
```

Aturan singkat:
* `--force-with-lease` > `--force` polos — selalu, kecuali yakin sendirian yang pegang branch tsb.
* Setelah squash-merge ke `master` dan push ke eksternal, recreate `pre-master` dari `main` untuk siklus berikutnya — jangan biarkan `pre-master` lama hidup terus, sumber conflict berulang.

---

## Claude Code — Token Efficiency Cheat Sheet

```
/context           # lihat breakdown pemakaian context window saat ini
/usage              # lihat token & biaya sesi (cache hit vs miss)
/compact [fokus]    # ringkas history manual, opsional instruksi fokus
/clear              # mulai sesi baru, CLAUDE.md & auto-memory tetap terbawa
/model              # ganti model (tiap model = cache terpisah)
/memory             # edit CLAUDE.md langsung
```

Aturan singkat:
* Cache hidup 1 jam (subscription) dan biaya cache-hit ~10% dari normal — hindari ganti model/effort/MCP di tengah sesi tanpa perlu, itu bikin cache hangus.
* `/compact` sendiri murah (cache hit ke rangkuman); giliran BERIKUTNYA yang mahal (cache dibangun ulang dari rangkuman pendek).
* Hasil `Read` file besar ikut numpuk di context selamanya sampai `/clear`/compact — pakai `Grep`/pencarian dulu, baru baca bagian spesifik.
* Delegasikan riset verbose (banyak file, log panjang) ke subagent — hasil ringkas yang balik ke context utama, bukan raw dump.
* CLAUDE.md jaga <200 baris; instruksi spesifik-per-tugas taruh di Skill (on-demand), bukan di CLAUDE.md (selalu dimuat).
* Prompt spesifik (Goal/Context/Constraint/Format) di awal > prompt umum + banyak iterasi klarifikasi.

---

## Claude Code — Anatomi Skill (Progressive Disclosure)

```text
skill-name/
  SKILL.md          # wajib — frontmatter (name, description) + body
  references/*.md    # opsional, detail besar/jarang dipakai
  scripts/*           # opsional, executable
```

```text
Level 1: name + description   -> SELALU di context (semua skill terdaftar)
Level 2: isi SKILL.md body    -> load sekali pas description match & di-invoke
Level 3: references/*, scripts/* -> load hanya kalau body eksplisit merujuknya
```

Aturan singkat:

* `description` = satu-satunya sinyal Claude putuskan "pakai skill ini atau tidak" SEBELUM baca body — gagal di sini bersifat silent (skill gak pernah dipanggil, gak ada error).
* Pola description kuat: "Use when [trigger kondisi konkret]" + kalau perlu scope boundary eksplisit ("skip when...") biar gak collide sama skill mirip topik.
* Isi bulk/jarang dipakai taruh di `references/`, bukan numpuk di SKILL.md body — Level 2 harus tetap murah tiap kali trigger.
* Skill vs subagent vs slash command: skill = load ke context main thread (probabilistik, dipilih dari description); subagent = context terisolasi (proteksi context utama, laporan akhir aja yang balik); slash command = trigger deterministik (user panggil nama langsung, gak lewat keputusan model).
