## Claude / AI Usage Mastery — dari 0 sampai Expert — 2026-08-06

GOAL       : apa yang mau dicapai, konkret
CONTEXT    : info yang relevan saja (bukan seluruh riwayat proyek) / idealnya adalah latar belakang
CONSTRAINT : batasan (format, panjang, tech stack, yang TIDAK boleh)
FORMAT     : bentuk output yang diinginkan
TARGET     : tempat kejadian perkara

-SANDBOX : isolated environment untuk claude running script di terminal, kenapa harus di isolated untuk membatasi apa yang bisa dilakukan claude di komputer local.
Tanpa sandbox → Anda memberikan kunci seluruh kantor.
Dengan sandbox → Anda hanya memberikan akses ke satu ruangan kerja dan satu lemari arsip. //kalau butuh ruangan lain, bilang aja (ask)

DENY
  ↓
  true ─────→ ❌ BLOCK
  │
 false
  ↓
ALLOW
  ↓
 true ─────→ ✅ ALLOW
 false ────→ ❓ ASK/BLOCK

-----

                    Command
                       │
          ┌────────────┴────────────┐
          │                         │
      Jelas aman?               Jelas berbahaya?
          │                         │
         YES                       YES
          │                         │
        ALLOW                     DENY
                                   
                    Tidak jelas
                         │
                        ASK