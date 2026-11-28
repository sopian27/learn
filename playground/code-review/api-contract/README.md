# API Contract (backward compatibility)

Pendamping Library Lesson 5.3. Response JSON ditiru pakai `Map`, bukan Spring/Jackson asli.

```
java ApiContractDemo.java
```

Yang diliat:
- Baris 2-4: perubahan yang di server kelihatan kecil, tapi client lama rusak.
- Baris 5: field baru ditambah, field lama dipertahankan, client lama tetap jalan. Client ini cuma baca key yang dia kenal; client yang menolak field asing tetap bisa rusak.
