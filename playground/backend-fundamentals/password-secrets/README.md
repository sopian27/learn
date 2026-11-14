# Password & Secret Demo (Lesson 11.3)

Bandingin SHA-256 polos (tanpa salt) dengan hash lambat + salt acak, plus cek secret dari environment variable.

## Jalanin

```bash
java PasswordSecretsDemo.java
JWT_SECRET=0123456789abcdef0123456789abcdef java PasswordSecretsDemo.java
```

## Output asli (angka ms beda tiap mesin)

```text
--- 1. SHA-256 polos: dua user, password sama ---
budi : ef92b778bafe771e89245b89...
yuni : ef92b778bafe771e89245b89...  sama? true
--- 2. Salted + lambat: password sama, hash beda ---
budi : 14b886aa3ca617b40e9c3ceb...
yuni : 94db7d456155fc6fe98993dc...  sama? false
login benar : true
login salah : false
--- 3. Biaya nebak (attacker punya dump DB) ---
SHA-256   : 1000 tebakan = 11 ms
slow hash : 5 tebakan    = 249 ms  (per tebakan ~49 ms)
--- 4. Secret dari env, bukan hardcoded ---
JWT_SECRET belum di-set / terlalu pendek -> app MENOLAK start (fail fast)
```

## Yang diliat

- SHA-256 polos: password sama = hash sama. Satu password ketebak, semua user yang pakai password itu ikut kebuka.
- Salt acak bikin hash beda walau password sama. Iterasi banyak bikin tiap tebakan mahal (puluhan ms vs mikrodetik).
- Perbandingan hash pakai `MessageDigest.isEqual` (constant-time), bukan `equals`.
- Secret dibaca dari env, dicek panjangnya, dan nilainya nggak pernah dicetak.

## Catatan

Simulasi: demo pakai PBKDF2 karena ada di JDK. Produksi pakai BCrypt/Argon2 lewat `PasswordEncoder` Spring Security (`BCryptPasswordEncoder`, `Argon2PasswordEncoder`), yang ngurus salt dan format hash otomatis. Bagian 4 cuma mencetak pesan, app sungguhan harus melempar exception saat startup.
