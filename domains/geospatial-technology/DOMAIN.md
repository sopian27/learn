# Teknologi Geospasial — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Teknologi Geospasial (Geospatial Technology) — geodesi, data & database spasial, analisis spasial, remote sensing/Earth observation tingkat literasi-terapan, dan web mapping, sebagai bidang software-engineering-adjacent.

## Scope

Memahami bagaimana data lokasi/geografis dimodelkan, disimpan, dianalisis, dan divisualisasikan dalam sistem perangkat lunak nyata:

- **Geodesi & coordinate reference system (CRS)**: bentuk bumi (ellipsoid vs geoid), datum (mis. WGS84), sistem proyeksi (UTM dan proyeksi umum lain), kenapa "lokasi" tidak pernah sesederhana satu pasang angka lat/lon tanpa CRS yang jelas, transformasi antar CRS, sumber error umum (mis. salah asumsi CRS, presisi vs akurasi koordinat).
- **Model data spasial**: vector (point/line/polygon, topology dasar) vs raster (grid, resolusi, band), kapan pakai yang mana, format umum (Shapefile, GeoJSON — RFC 7946, GeoPackage, GeoTIFF).
- **Database spasial**: PostgreSQL + PostGIS (tipe geometry/geography, spatial SQL — ST_* functions, spatial join), spatial indexing (R-tree dan variannya, geohash, Uber H3, Google S2) — trade-off tiap skema indexing untuk use-case berbeda (mis. H3 untuk ride-hailing/analytics heksagonal, geohash untuk sistem legacy/sederhana, R-tree sebagai default index PostGIS).
- **Analisis & algoritma spasial**: buffering, overlay (intersect/union/difference), nearest-neighbor search, routing & network analysis dasar (shortest path di graph jalan), spatial join, aggregation spasial.
- **Remote sensing & Earth observation tingkat literasi-terapan**: citra satelit (Landsat, Sentinel), band spektral, produk turunan seperti NDVI secukupnya untuk paham cara kerja & use-case (mis. deteksi vegetasi/perubahan lahan) — bukan sains sinyal remote-sensing tingkat riset.
- **GPS/GNSS fundamental**: cara kerja penentuan posisi (trilaterasi, satelit, segmen kontrol), sumber error (multipath, atmospheric delay), perbedaan GPS vs GNSS secara umum (GPS AS, GLONASS, Galileo, BeiDou) secukupnya untuk konteks aplikasi, bukan rekayasa penerima sinyal RF.
- **Web mapping & visualisasi**: Leaflet, Mapbox GL JS, OpenLayers, GeoJSON sebagai format tukar-data, vector tiles (kenapa dan bagaimana peta modern di-tile), styling & interaksi peta dasar.
- **Merangkai semuanya jadi software nyata**: API geospasial (mis. endpoint pencarian "titik terdekat" atau "poligon mana yang mengandung titik ini"), pipeline data geospasial sederhana (ingest → transform CRS → simpan ke PostGIS → serve lewat API/tile), aplikasi peta end-to-end.

**Kenapa ini domain terpisah, bukan bagian dari `software-engineering`**: domain ini memang tumpang tindih rasa (flavor) dengan `software-engineering` — Applied Practice-nya sungguh-sungguh menulis kode (query SQL/PostGIS, aplikasi peta, pipeline data). Tapi ia dibuat sebagai domain spesialis terpisah (paralel dengan cara `nuclear-technology` dan `relativity` jadi domain spesialis) karena butuh **trusted-source list sendiri** (OGC, PostGIS, QGIS, textbook GIScience akademik — bukan roadmap.sh generik), **practitioner reference frame sendiri** (insinyur GIS, remote-sensing scientist, geodesist — bukan backend engineer generik), dan **project tiers sendiri** yang berpusat pada artefak geospasial (peta, layer analisis, pipeline citra satelit) yang tidak otomatis muncul dari kurikulum software-engineering umum. Applied Practice domain ini secara eksplisit **bersandar pada** kemampuan software-engineering yang sudah dipunya learner (lihat bagian Applied Practice Definition di bawah) — sama seperti `relativity` menyandarkan komponen simulasinya ke `[[domains/software-engineering/DOMAIN.md|software-engineering]]`.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Remote-sensing science / fotogrametri tingkat riset pascasarjana** — pemrosesan sinyal citra satelit tingkat lanjut (mis. koreksi atmosferik penuh, algoritma klasifikasi citra berbasis riset, kalibrasi sensor, structure-from-motion fotogrametri mendalam) di luar scope; domain ini hanya sampai level "paham cara kerja & bisa memakai produk turunan seperti NDVI," bukan mengembangkan algoritma remote-sensing baru.
- **Sertifikasi profesional GIS (mis. GISP — GIS Professional certification)** — domain ini bukan pengganti jalur sertifikasi resmi GISCI/organisasi setara; tidak ada klaim "lulus modul ini = setara GISP."
- **Teori desain kartografi** (color theory peta, tipografi peta, prinsip desain visual peta tingkat lanjut) — domain ini mengajarkan cukup styling/visualisasi untuk membuat peta yang berfungsi dan cukup enak dibaca, bukan disiplin desain kartografi tersendiri (itu domain desain, bukan domain ini).
- **Rekayasa penerima sinyal GNSS/RF tingkat hardware** — cara kerja chip GPS, desain antena, digital signal processing sinyal satelit di luar scope; GPS/GNSS di sini dibahas dari sisi konsumen data posisi (aplikasi), bukan perancang perangkat keras penerima.

## Trusted Sources / Research Priority

1. **OGC (Open Geospatial Consortium)** (https://www.ogc.org/, standar: https://www.ogc.org/standards/) — badan standar internasional untuk interoperabilitas data geospasial (mis. WMS, WFS, GeoPackage). Rujukan tertinggi untuk definisi standar format & layanan geospasial yang dipakai industri.
2. **PostGIS Official Manual** (https://postgis.net/documentation/, https://postgis.net/documentation/getting_started/) — dokumentasi resmi ekstensi spasial PostgreSQL; rujukan utama untuk spatial SQL, tipe geometry/geography, spatial indexing.
3. **Regina Obe & Leo Hsu — *PostGIS in Action*, 3rd Edition** (Manning, https://www.manning.com/books/postgis-in-action-third-edition) — buku standar industri untuk belajar PostGIS end-to-end, ditulis salah satunya oleh anggota PostGIS core development team & Project Steering Committee (Regina Obe). Tulang punggung modul database spasial.
4. **Paul Longley, Michael Goodchild, David Maguire, David Rhind — *Geographic Information Science and Systems*, 4th Edition** (Wiley, https://www.wiley.com/en-us/Geographic+Information+Science+and+Systems,+4th+Edition-p-9781119128458) — textbook akademik standar GIScience, dipakai sebagai kerangka struktur untuk fondasi teori (geodesi, model data, analisis spasial) — bukan untuk kedalaman riset per topik.
5. **QGIS Documentation** (https://docs.qgis.org/) — dokumentasi resmi software GIS open-source paling luas dipakai; dipakai untuk latihan visual/eksplorasi data spasial secara hands-on sebelum masuk ke coding murni.
6. **USGS (U.S. Geological Survey) — Landsat Missions** (https://www.usgs.gov/landsat-missions) dan **NASA Landsat** (https://landsat.gsfc.nasa.gov/) — sumber resmi untuk data & literasi citra satelit Landsat, band spektral, dan sejarah program.
7. **ESA (European Space Agency) — Copernicus Sentinel missions** (https://www.esa.int/Applications/Observing_the_Earth/Copernicus/The_Sentinel_missions) — sumber resmi untuk program Sentinel (radar Sentinel-1, multispectral Sentinel-2, dst.), dipakai sebagai pelengkap Landsat untuk literasi Earth observation modern.
8. **Uber Engineering — H3 documentation** (https://h3geo.org/, blog pengumuman: https://www.uber.com/us/en/blog/h3/) — dokumentasi resmi sistem spatial indexing heksagonal H3, dipakai untuk modul spatial indexing modern & studi kasus penggunaan riil di industri (ride-hailing/analytics).
9. **Google — S2 Geometry Library** (https://s2geometry.io/) — dokumentasi resmi sistem indexing spasial berbasis sel spherical, dipakai sebagai pembanding H3 untuk memahami trade-off desain spatial index yang berbeda.
10. **Mapbox GL JS Documentation** (https://docs.mapbox.com/mapbox-gl-js/guides/), **Leaflet** (https://leafletjs.com/, referensi: https://leafletjs.com/reference.html), **OpenLayers** (https://openlayers.org/doc/) — dokumentasi resmi tiga library web mapping utama; dipakai untuk modul web mapping & visualisasi, dibandingkan satu sama lain untuk trade-off (ringan/simpel vs fitur vektor tile penuh vs fleksibilitas open-source).
11. **GeoJSON — RFC 7946** (https://www.rfc-editor.org/rfc/rfc7946.html, ringkasan: https://geojson.org/) — spesifikasi resmi IETF untuk format tukar-data geospasial paling umum di web mapping.
12. **GPS.gov** (https://www.gps.gov/) — sumber resmi pemerintah AS (dikoordinasikan oleh National Coordination Office for Space-Based PNT) untuk literasi cara kerja GPS/GNSS, dipakai untuk modul GPS/GNSS fundamental.

Setiap klaim teknis kuantitatif (mis. formula proyeksi, definisi index spasial, spesifikasi format) wajib bisa ditelusuri ke sumber resmi (1-9, 11-12) — tidak cukup dari tutorial blog pihak ketiga tanpa disilangkan ke dokumentasi resmi.

## Applied Practice Definition

Domain ini bersandar langsung pada kemampuan coding yang sudah dipunya learner di domain [[domains/software-engineering/DOMAIN.md|software-engineering]] — "praktik nyata" di sini SELALU berbentuk kode/query yang berjalan, bukan cuma teori:

- **Spatial SQL lab**: menulis & menjalankan query PostGIS nyata (ST_Distance, ST_Intersects, ST_Buffer, spatial join, dst.) terhadap dataset spasial riil, memverifikasi hasilnya secara visual (mis. lewat QGIS atau plot sederhana).
- **Mini web map**: membangun peta interaktif kecil (Leaflet/Mapbox GL/OpenLayers) yang me-render data GeoJSON/vector tile nyata, dengan minimal satu interaksi (klik, filter, popup info).
- **Spatial indexing exercise**: mengimplementasikan/membandingkan strategi indexing (mis. hitung H3 cell dari koordinat, bandingkan hasil query nearest-neighbor pakai R-tree PostGIS vs pendekatan geohash/H3) untuk memahami trade-off performa & granularitas secara empiris, bukan cuma baca definisinya.
- **Pipeline data geospasial kecil**: skrip end-to-end (bahasa pilihan learner) yang mengambil data mentah (mis. koordinat GPS/citra satelit metadata), melakukan transformasi CRS bila perlu, menyimpan ke PostGIS, lalu menyajikannya lewat query/API/tile sederhana.

## Project Tiers

- **Mini Project** (per modul): satu artefak tunggal — mis. satu set query PostGIS yang menyelesaikan satu masalah spasial konkret (cari semua toko dalam radius X km), atau satu peta Leaflet/Mapbox statis yang menampilkan satu dataset GeoJSON.
- **Intermediate Project** (setelah beberapa modul terkait): artefak gabungan yang menyatukan >1 konsep — mis. "Nearest-Facility Finder": API kecil (backend + PostGIS) yang menerima koordinat pengguna dan mengembalikan fasilitas terdekat pakai spatial index yang tepat, plus peta web sederhana untuk menampilkannya; atau "NDVI Change Viewer" sederhana dari dua citra Sentinel/Landsat pada waktu berbeda.
- **Capstone / Portfolio Project** (akhir course): **aplikasi geospasial end-to-end**, mis. sistem pencarian lokasi/routing sederhana (data → PostGIS dengan spatial index yang dipilih sadar trade-off-nya → API → web map interaktif dengan vector tiles), atau dashboard analisis spasial (mis. visualisasi perubahan tutupan lahan dari citra satelit multi-waktu) — disimpan ke `portofolio/`. Harus menunjukkan pemahaman penuh loop: CRS yang benar → model data yang tepat → index yang sesuai use-case → analisis → visualisasi, bukan cuma satu potongan tanpa yang lain.

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak geospasial juga dicek lewat lensa berikut:

- **CRS Correctness** — apakah coordinate reference system dinyatakan eksplisit dan konsisten di seluruh pipeline (tidak mencampur derajat WGS84 dengan unit proyeksi UTM tanpa transformasi sadar)? Ini kesalahan pemula paling umum dan paling berbahaya secara diam-diam (silent bug — hasil "kelihatan jalan" tapi salah jarak/lokasi).
- **Right Data Model & Index for the Job** — apakah pilihan vector vs raster, dan pilihan spatial index (R-tree/geohash/H3/S2), dijustifikasi berdasarkan use-case (skala data, jenis query), bukan asal pilih yang paling familiar?
- **Query/Pipeline Correctness under Edge Cases** — apakah spatial query menangani kasus tepi wajar (titik persis di boundary, polygon dengan hole, antimeridian/dateline untuk data global) — bukan cuma benar di happy path?
- **Production-Readiness lensed through Software Engineering** — karena Applied Practice domain ini adalah kode nyata, review juga memakai standar review kode umum dari `standards/UNIVERSAL_STANDARDS.md`/`domains/software-engineering/DOMAIN.md` (index yang dipakai benar-benar dipakai query planner, tidak N+1 query spasial, dsb.) — geospasial tidak mengecualikan artefaknya dari disiplin engineering biasa.

## Assessment Form

Concept-check singkat setelah bagian teori (mis. "apa bedanya geodetic vs projected CRS," "kapan pakai H3 vs geohash vs R-tree"), soal debugging spasial terarah (diberi query/peta yang salah karena bug CRS/index/geometry, learner diminta mengidentifikasi & memperbaiki root cause), dan latihan coding kecil dengan dataset nyata untuk tiap topik hands-on (PostGIS, web mapping, spatial indexing).

## Practitioner Reference Frame

Bandingkan beberapa lensa untuk topik yang sama:

- **GIS engineer / geospatial software engineer** (gaya kontributor PostGIS/QGIS, penulis *PostGIS in Action*) — framing "bagaimana ini diimplementasikan dengan benar & efisien di sistem produksi," rigor terhadap CRS dan index.
- **GIScience akademisi** (gaya penulis Longley et al.) — framing konseptual/ilmiah: model data, teori analisis spasial, epistemologi "apa itu ruang/lokasi" dalam sistem informasi.
- **Remote sensing / Earth observation scientist** (gaya tim USGS/NASA Landsat, ESA Sentinel) — framing dari sisi data mentah citra satelit: kalibrasi, band spektral, interpretasi fisik sinyal.
- **Web mapping / frontend geospatial engineer** (gaya tim Mapbox/Leaflet/OpenLayers) — framing performa render, UX interaksi peta, vector tiles sebagai masalah rekayasa web modern.
- **Insinyur infrastruktur skala industri** (gaya Uber H3, Google S2) — framing "bagaimana meng-index & mengagregasi milyaran titik/query lokasi secara efisien" — titik temu langsung dengan latar belakang software engineering learner.

Kelima lensa ini sering menekankan hal berbeda untuk masalah yang sama (mis. "pilih spatial index apa": engineer produksi fokus ke throughput query nyata, akademisi fokus ke properti matematis index, insinyur H3/S2 fokus ke kasus penggunaan spesifik skala besar) — domain ini melatih learner mengenali perbedaan penekanan tersebut, bukan menganggap satu lensa sebagai satu-satunya kebenaran.

## Domain-Specific Standards

`domains/geospatial-technology/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **CRS selalu dinyatakan eksplisit.** Setiap artefak (query, kode, dataset) yang menyentuh koordinat wajib menyatakan CRS yang dipakai (mis. EPSG:4326 untuk WGS84, EPSG:3857 untuk Web Mercator) — tidak pernah ada koordinat "telanjang" tanpa CRS yang jelas, karena ini sumber bug paling umum dan paling diam-diam di seluruh domain ini.
- **Kode geospasial tunduk pada standar software engineering biasa.** Karena Applied Practice domain ini adalah kode nyata (SQL, JavaScript/Python/dll.), semua artefak kode tetap tunduk penuh pada `standards/UNIVERSAL_STANDARDS.md` dan konvensi `domains/software-engineering/DOMAIN.md` (testing, readability, security) — domain ini menambah lensa spasial di atas itu, bukan menggantikannya.
- **Data lokasi nyata = data sensitif.** Setiap contoh/latihan yang memakai data lokasi individu nyata (bukan dataset publik/sintetis) wajib memakai data anonim/dummy — mentor tidak pernah meminta atau memproses data lokasi pribadi learner atau pihak ketiga sungguhan sebagai bagian latihan.

## Domain Goal

Learner mampu merancang dan membangun sistem geospasial nyata dari ujung ke ujung — memahami geodesi & CRS secukupnya untuk tidak membuat bug lokasi yang diam-diam salah, memilih model data & strategi spatial indexing yang tepat untuk suatu use-case, menulis spatial SQL/analisis spasial yang benar, memahami literasi dasar remote sensing/GNSS untuk mengonsumsi data Earth observation, dan menyajikan semuanya lewat web map yang berfungsi — dibuktikan lewat aplikasi geospasial end-to-end di portofolio yang menunjukkan seluruh loop (data → CRS benar → index tepat → analisis → visualisasi), bukan potongan-potongan terisolasi. Domain ini eksplisit BUKAN kredensial GISP atau gelar geomatika/GIScience formal, dan eksplisit BUKAN pengganti keahlian remote-sensing-science tingkat riset — murni kemampuan applied engineer yang bisa membangun produk geospasial produksi dengan pijakan software-engineering yang sudah dipunya learner.

## Registration History

Registered 2026-08-09, domain pack approved via the user's direct request to build the course roadmap right away. Registered paused per the 2026-08-07 token-saving policy, but the course roadmap was built immediately at the user's request rather than deferred.
