# Course Roadmap

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Course Information

* Course Name: Teknologi Geospasial — dari 0 sampai Aplikasi End-to-End
* Category: Geospatial Technology (Domain: geospatial-technology — lihat `domains/geospatial-technology/DOMAIN.md`)
* Difficulty: Level 0-1 (belum paham CRS/model data spasial, belum pernah pakai PostGIS) → Level 3 (mampu merancang & membangun aplikasi geospasial end-to-end: CRS benar → model data & index tepat → spatial SQL/analisis benar → tersaji lewat web map) — **bukan** kredensial GISP atau gelar geomatika/GIScience formal, dan bukan pengganti remote-sensing science tingkat riset.
* Estimated Duration: 10 modul, 4 lesson/modul, pace disesuaikan karena Applied Practice domain ini selalu berbentuk kode/query yang benar-benar dijalankan (bukan cuma baca teori)
* Prerequisites: Domain ini bersandar langsung pada kemampuan software-engineering yang sudah dipunya learner (lihat `domains/geospatial-technology/DOMAIN.md` bagian Applied Practice Definition) — perlu SQL dasar, minimal satu bahasa pemrograman umum (mis. Python/JavaScript), dan familiar command line/Git. Tidak perlu pengalaman GIS/geodesi sebelumnya — Modul 1-2 dibangun dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Referensi riset yang dipakai menyusun roadmap ini** (mengikuti daftar Trusted Sources di `domains/geospatial-technology/DOMAIN.md`): struktur bab [Regina Obe & Leo Hsu — *PostGIS in Action*, 3rd Edition](https://postgis.us/chapters) (spatial types → SRS considerations → spatial SQL/functions → proximity analysis → performance tuning → web applications) untuk urutan Modul 3; struktur bab [Longley et al. — *Geographic Information Science and Systems*, 4th Edition](https://www.wiley.com/en-us/Geographic+Information+Science+and+Systems,+4th+Edition-p-9781119128458) (Georeferencing, Nature of Geographic Data, Representing Geography, Spatial Data Analysis, GeoWeb) untuk kerangka Modul 1-2 dan 5; [OGC Standards](https://www.ogc.org/standards/) (WMS/WFS/GeoPackage) dan [GeoJSON RFC 7946](https://www.rfc-editor.org/rfc/rfc7946.html) untuk Modul 2; perbandingan trade-off [Uber H3](https://h3geo.org/) vs [Google S2](https://s2geometry.io/) vs geohash untuk Modul 4; [Mapbox GL JS Guides](https://docs.mapbox.com/mapbox-gl-js/guides/) dan dokumentasi [Leaflet](https://leafletjs.com/)/[OpenLayers](https://openlayers.org/doc/) untuk Modul 8; [GPS.gov](https://www.gps.gov/) (trilaterasi, control segment, multipath) untuk Modul 7; USGS Landsat dan ESA Copernicus Sentinel untuk Modul 6.
2. **Eksplisit di luar scope** (konsisten dengan `domains/geospatial-technology/DOMAIN.md`): remote-sensing science/fotogrametri tingkat riset pascasarjana, sertifikasi GISP, teori desain kartografi tingkat lanjut, dan rekayasa penerima sinyal GNSS/RF tingkat hardware. Modul 6-7 murni literasi terapan (paham cara kerja & bisa memakai produk turunannya), bukan pengembangan algoritma remote-sensing/GNSS baru.
3. **Data lokasi**: seluruh dataset di latihan/mini project memakai data publik/sintetis/dummy — tidak pernah data lokasi pribadi nyata milik learner atau pihak ketiga, sesuai Domain-Specific Standards.

---

# Learning Objectives

* [ ] Memahami geodesi & CRS secukupnya untuk tidak membuat bug lokasi yang diam-diam salah — datum, ellipsoid vs geoid, geodetic vs projected CRS, transformasi antar CRS
* [ ] Memilih model data spasial (vector vs raster) dan format standar (GeoJSON, GeoPackage, GeoTIFF) yang tepat untuk suatu use-case
* [ ] Menulis spatial SQL/PostGIS yang benar (ST_Distance, ST_Intersects, ST_Buffer, spatial join) terhadap dataset spasial riil
* [ ] Memahami dan membandingkan strategi spatial indexing (R-tree, geohash, H3, S2) serta memilih yang tepat berdasarkan use-case, bukan asal familiar
* [ ] Menerapkan analisis spasial (buffering, overlay, nearest-neighbor, routing dasar) termasuk menangani edge case (boundary, hole, antimeridian)
* [ ] Memahami literasi remote sensing/Earth observation (Landsat/Sentinel, band spektral, NDVI) tanpa masuk ke sains riset
* [ ] Memahami fundamental GPS/GNSS (trilaterasi, segmen sistem, sumber error) dari sisi konsumen data posisi
* [ ] Membangun web map interaktif (Leaflet/Mapbox GL JS/OpenLayers) yang menyajikan data GeoJSON/vector tile nyata
* [ ] Merangkai seluruh kemampuan di atas jadi aplikasi geospasial end-to-end (data → CRS benar → index tepat → analisis → API/visualisasi) sebagai Capstone Project

---

# Course Modules

## Module 1 — Fondasi Geodesi & Coordinate Reference System (CRS)

Description: Titik paling kritis dan paling sering jadi silent bug di seluruh domain ini — sebelum menyentuh data spasial apa pun, learner harus paham kenapa "lokasi" tidak pernah sesederhana satu pasang angka lat/lon tanpa CRS yang jelas.

Lessons:

* [ ] 1.1 Bentuk bumi: ellipsoid vs geoid, kenapa lokasi butuh model matematis bumi sebelum jadi koordinat
* [ ] 1.2 Datum (mis. WGS84): kenapa titik fisik yang sama bisa punya koordinat berbeda tergantung datum
* [ ] 1.3 Geodetic vs projected CRS: UTM, Web Mercator (EPSG:3857), dan distorsi (jarak/luas/sudut) yang selalu ada di proyeksi
* [ ] 1.4 Transformasi antar CRS & EPSG code — kenapa koordinat "telanjang" tanpa CRS eksplisit adalah bug tersembunyi paling umum

Mini Project: Skrip transformasi koordinat (pyproj/GDAL/ST_Transform) antara EPSG:4326 dan EPSG:3857/UTM zone yang sesuai untuk satu dataset nyata, plus satu kasus debugging: diberi dataset "kelihatan salah lokasi" karena CRS mismatch, identifikasi dan perbaiki root cause-nya.

Status: Not started

---

## Module 2 — Model Data Spasial: Vector, Raster & Format Standar

Description: Setelah CRS solid, masuk ke bagaimana data spasial itu sendiri direpresentasikan — vector vs raster, dan format tukar-data standar industri yang jadi dasar semua modul berikutnya.

Lessons:

* [ ] 2.1 Vector data model: point/line/polygon dan topology dasar (adjacency, containment)
* [ ] 2.2 Raster data model: grid, resolusi, band — kapan pakai raster vs vector untuk suatu masalah
* [ ] 2.3 Format vector standar: Shapefile (legacy & limitasinya), GeoJSON (RFC 7946), GeoPackage (standar OGC modern)
* [ ] 2.4 Format raster standar (GeoTIFF) dan eksplorasi data lewat QGIS sebelum masuk coding murni

Mini Project: Eksplorasi satu dataset vector dan satu dataset raster nyata di QGIS, lalu konversi dataset vector dari Shapefile ke GeoPackage/GeoJSON lewat script (GDAL/ogr2ogr atau library pilihan), diverifikasi visual bahwa hasil konversi tidak mengubah geometri/CRS.

Status: Not started

---

## Module 3 — Database Spasial: PostgreSQL + PostGIS & Spatial SQL

Description: Tulang punggung praktik domain ini — menulis query spasial nyata terhadap database, bukan cuma teori format data.

Lessons:

* [ ] 3.1 Setup PostGIS, tipe geometry vs geography — kapan pakai yang mana
* [ ] 3.2 Spatial SQL fundamental: ST_Distance, ST_Contains, ST_Intersects, ST_Within
* [ ] 3.3 Operasi geometris lewat SQL: ST_Buffer, ST_Union, ST_Intersection
* [ ] 3.4 Spatial join: menggabungkan dua tabel berdasarkan hubungan spasial, bukan foreign key biasa

Mini Project: Spatial SQL lab — satu set query PostGIS nyata yang menyelesaikan masalah spasial konkret (mis. cari semua toko dalam radius X km dari titik tertentu) terhadap dataset spasial riil, hasil diverifikasi visual lewat QGIS.

Status: Not started

---

## Module 4 — Spatial Indexing: R-tree, Geohash, H3, S2

Description: Query spasial yang benar tapi lambat tetap gagal di skala produksi — modul ini membangun pemahaman empiris (bukan cuma definisi) soal trade-off strategi spatial indexing.

Lessons:

* [ ] 4.1 Kenapa index spasial berbeda dari B-tree biasa — R-tree/GiST sebagai default index PostGIS
* [ ] 4.2 Geohash: struktur hierarchical prefix dan limitasinya (neighbor cell tidak berdekatan secara prefix)
* [ ] 4.3 Uber H3: grid heksagonal, kenapa lebih uniform untuk distance, use-case ride-hailing/analytics
* [ ] 4.4 Google S2: spherical cell, level presisi, dibanding H3 & geohash — kapan pilih index yang mana

Mini Project: Exercise perbandingan indexing — hitung H3 cell dari koordinat nyata, bandingkan hasil query nearest-neighbor pakai R-tree PostGIS vs pendekatan geohash/H3 untuk dataset yang sama, tulis kesimpulan trade-off performa & granularitas berdasarkan hasil empiris.

Status: Not started

---

## Module 5 — Analisis Spasial: Buffer, Overlay, Nearest-Neighbor, Routing

Description: Menggabungkan spatial SQL dan indexing jadi analisis yang menjawab pertanyaan nyata, termasuk menangani edge case yang sering dilewatkan pemula.

Lessons:

* [ ] 5.1 Buffering & overlay operations (intersect/union/difference) sebagai alat analisis, bukan cuma operasi geometri
* [ ] 5.2 Nearest-neighbor search & spatial aggregation
* [ ] 5.3 Routing & network analysis dasar: graph jalan, shortest path secukupnya untuk konteks spasial
* [ ] 5.4 Edge case spasial: titik persis di boundary, polygon dengan hole, antimeridian/dateline untuk data global

Mini Project: Analisis spasial gabungan pada dataset nyata (mis. cari fasilitas terdekat dari beberapa titik query pakai buffer + nearest-neighbor), termasuk minimal satu uji edge case boundary/hole secara eksplisit.

Status: Not started

---

## Module 6 — Remote Sensing & Earth Observation: Literasi Terapan

Description: Sisi "data mentah dari langit" dalam sistem geospasial — cukup dalam untuk paham cara kerja & memakai produk turunannya, secara eksplisit bukan riset remote-sensing science.

Lessons:

* [ ] 6.1 Citra satelit: program Landsat (USGS) dan Sentinel/Copernicus (ESA) — cakupan & band spektral
* [ ] 6.2 Kombinasi band: true color vs false color, kenapa band dipilih berbeda untuk tujuan berbeda
* [ ] 6.3 NDVI: formula, cara kerja, use-case deteksi vegetasi/perubahan lahan
* [ ] 6.4 Batas literasi domain ini: apa yang eksplisit di luar scope (koreksi atmosferik penuh, klasifikasi citra tingkat riset)

Mini Project: Skrip kecil menghitung NDVI dari band Red & NIR sample data Landsat/Sentinel (mis. numpy/rasterio atau library pilihan), visualisasikan hasilnya, dan interpretasikan area vegetasi vs non-vegetasi.

Status: Not started

---

## Module 7 — GPS & GNSS Fundamental

Description: Sisi "bagaimana titik koordinat itu sendiri didapat" — dari perspektif konsumen data posisi (aplikasi), bukan perancang hardware penerima sinyal.

Lessons:

* [ ] 7.1 Trilaterasi: cara kerja penentuan posisi dari jarak ke beberapa satelit
* [ ] 7.2 Tiga segmen GPS: space segment, control segment, user segment
* [ ] 7.3 Sumber error: multipath, atmospheric delay — kenapa akurasi GPS selalu punya batas
* [ ] 7.4 GPS vs GNSS: GLONASS, Galileo, BeiDou secukupnya untuk konteks aplikasi

Mini Project: Analisis kecil terhadap data GPS nyata (mis. parsing file GPX/NMEA publik), hitung estimasi sebaran error dari titik-titik berulang di lokasi yang sama, dan jelaskan sumber error yang teramati.

Status: Not started

---

## Module 8 — Web Mapping & Visualisasi Peta Interaktif

Description: Bagaimana seluruh data dan analisis di atas akhirnya dilihat manusia — tiga library web mapping utama dibandingkan dari trade-off nyata, bukan preferensi sepihak.

Lessons:

* [ ] 8.1 Leaflet: ringan, simpel, ekosistem plugin luas
* [ ] 8.2 Mapbox GL JS: vector tiles, WebGL rendering, styling lewat Mapbox Style Specification
* [ ] 8.3 OpenLayers: fleksibilitas & fitur open-source penuh, dibanding dua library lain
* [ ] 8.4 GeoJSON sebagai format tukar-data & vector tiles — kenapa dan bagaimana peta modern di-tile

Mini Project: Mini web map interaktif (pilih Leaflet/Mapbox GL/OpenLayers) yang me-render data GeoJSON/vector tile nyata, dengan minimal satu interaksi (klik, filter, atau popup info).

Status: Not started

---

## Module 9 — Merangkai Jadi Software: Geospatial API & Data Pipeline

Description: Modul integrasi sebelum Capstone — menyatukan database spasial, indexing, dan web map jadi satu alur software nyata, dinilai penuh lewat lensa software engineering biasa.

Lessons:

* [ ] 9.1 Desain API geospasial: endpoint "titik terdekat" / "poligon mana yang mengandung titik ini"
* [ ] 9.2 Pipeline data geospasial: ingest → transform CRS → simpan ke PostGIS → serve lewat API/tile
* [ ] 9.3 Menyatukan spatial index yang tepat (Module 4) ke API — memastikan index benar-benar dipakai query planner, bukan cuma dibuat lalu diabaikan
* [ ] 9.4 Review lensa software engineering untuk kode geospasial: N+1 query spasial, testing, keamanan input koordinat

Mini Project (setara Intermediate Project per `domains/geospatial-technology/DOMAIN.md` bagian Project Tiers): "Nearest-Facility Finder" — API kecil (backend + PostGIS) yang menerima koordinat pengguna dan mengembalikan fasilitas terdekat memakai spatial index yang tepat, plus peta web sederhana (dari Module 8) untuk menampilkan hasilnya.

Status: Not started

---

## Module 10 — Capstone: Aplikasi Geospasial End-to-End

Description: Mengintegrasikan seluruh artefak Module 1-9 jadi satu aplikasi geospasial koheren, mengikuti `domains/geospatial-technology/DOMAIN.md` bagian "Project Tiers" untuk definisi Capstone di domain ini.

Lessons:

* [ ] 10.1 Menyusun ulang seluruh artefak Module 1-9 (CRS, model data, PostGIS, index, analisis, remote sensing/GNSS bila relevan, web map, API/pipeline) jadi satu aplikasi/dashboard geospasial koheren
* [ ] 10.2 Uji sistem dengan dataset nyata: verifikasi CRS konsisten, index sesuai use-case, query benar di edge case
* [ ] 10.3 Self-review lewat empat lensa Review Style domain: CRS Correctness, Right Data Model & Index for the Job, Query/Pipeline Correctness under Edge Cases, Production-Readiness
* [ ] 10.4 Finalisasi dan simpan ke `portofolio/`

Capstone Project: Aplikasi geospasial end-to-end — pilih salah satu: (a) sistem pencarian lokasi/routing sederhana (data → PostGIS dengan spatial index yang dipilih sadar trade-off → API → web map interaktif dengan vector tiles), atau (b) dashboard analisis spasial (mis. visualisasi perubahan tutupan lahan dari citra satelit multi-waktu). Harus menunjukkan pemahaman penuh loop: CRS yang benar → model data yang tepat → index yang sesuai use-case → analisis → visualisasi, bukan cuma satu potongan tanpa yang lain.

Status: Not started

---

# Capstone Project

Description: Lihat Module 10 di atas untuk rincian lengkap. Capstone course ini SATU dengan Capstone Project Module 10 — tidak ada proyek terpisah tambahan di luar modul, konsisten dengan `domains/geospatial-technology/DOMAIN.md` bagian "Project Tiers".

Acceptance Criteria: Memenuhi empat lensa Review Style domain (CRS Correctness, Right Data Model & Index for the Job, Query/Pipeline Correctness under Edge Cases, Production-Readiness lensed through Software Engineering) plus Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score) dari `ai-los/CORE_LOS.md`, menunjukkan loop penuh data → CRS benar → model data & index tepat → analisis → visualisasi dengan bukti artefak kode/query nyata (bukan cuma dideskripsikan), dan disimpan ke `portofolio/`.

Status: Not started

---

# Overall Progress

Module Completion: 0/10

Overall Completion: 0%

Current Module: Module 1 — Fondasi Geodesi & Coordinate Reference System (CRS) (menunggu approval roadmap)

Current Lesson: 1.1 Bentuk bumi: ellipsoid vs geoid, kenapa lokasi butuh model matematis bumi sebelum jadi koordinat

Next Lesson: 1.1 Bentuk bumi: ellipsoid vs geoid, kenapa lokasi butuh model matematis bumi sebelum jadi koordinat
