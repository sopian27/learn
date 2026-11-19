# Versioning Strategies Demo (Lesson 8.2)

Run: `java VersioningStrategiesDemo.java`

Look for: satu handler yang sama melayani v1 dan v2, tapi versinya dibaca dari tiga tempat berbeda (URI, header, `Accept`). Perhatikan baris "tanpa header": klien yang tidak menyebut versi tetap dapat v1, bukan versi terbaru. Itu yang menjaga klien lama tidak pecah.
