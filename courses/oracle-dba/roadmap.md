# Course Roadmap

## Course Information

* Course Name: Oracle Database Administrator — dari 0 sampai Expert
* Category: Database Administration / Relational Database Systems
* Difficulty: Level 1 (belum pernah install atau login ke Oracle Database sama sekali) → Level 4 (Expert, mampu mengoperasikan instance Oracle production sendirian — install, secure, backup/recovery, tuning, troubleshooting — tanpa panduan, dan siap mengambil sertifikasi OCP)
* Estimated Duration: 11 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Sudah bisa SQL (SELECT/JOIN/DML/DDL dasar) dan familiar konsep database relasional dari pengalaman PostgreSQL/Redis di repo ini. **Tidak perlu** pengalaman Oracle sebelumnya — arsitektur, tooling, dan istilah Oracle-spesifik (instance, SGA, tablespace, RMAN, dst.) diajarkan dari nol di Modul 1.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Course Oracle pertama di repo ini.** Semua course database yang sudah ada (disinggung di `CLAUDE.md` dan dipakai di `courses/backend-fundamentals`/`courses/system-design`) berpusat pada PostgreSQL dan Redis. Course ini murni menambah kompetensi baru — Oracle DBA — bukan menggantikan stack utama. Di beberapa lesson (storage, backup/recovery, PL/SQL) sengaja disisipkan catatan perbandingan singkat ke PostgreSQL supaya konsep yang sudah dikuasai bisa jadi jembatan, bukan diulang dari nol.
2. **Mengikuti jalur sertifikasi OCA → OCP secara longgar, bukan silabus ujian yang kaku.** Struktur modul 1-6 kurang lebih mengikuti cakupan **Oracle Database Administration I (OCA, exam 1Z0-082)** — arsitektur, instalasi, storage, security, backup dasar — dan modul 6-11 mengarah ke cakupan **Oracle Database Administration II (OCP, exam 1Z0-083)** — RMAN mendalam, performance tuning, Data Guard, patching. Course ini **bukan** bootcamp ujian sertifikasi (tidak ada dump soal), tapi kalau nanti mau ambil OCA/OCP, roadmap ini sudah searah dengan exam topics resmi.
3. **Scope: DBA operations, bukan pengembangan aplikasi PL/SQL.** PL/SQL diajarkan di Modul 5 secukupnya untuk kebutuhan administratif (scripting maintenance, scheduled jobs via `DBMS_SCHEDULER`, membaca/menulis stored procedure sederhana untuk automation) — bukan sebagai jalur "PL/SQL Developer" penuh (arsitektur aplikasi, package kompleks, tuning PL/SQL lanjut ada di luar scope course ini).
4. **RAC (Real Application Clusters) sengaja tidak dibahas mendalam** — disinggung sekilas di Modul 9 sebagai "lapisan HA di atas Data Guard" tapi tidak dipraktikkan (butuh multi-node cluster, di luar jangkauan lab single-machine). Fokus HA course ini adalah Data Guard, yang lebih realistis untuk dipelajari dan dipraktikkan sendiri.
5. **Environment lab: Oracle Database XE (Express Edition) atau image container resmi Oracle**, gratis dan cukup untuk mempraktikkan hampir seluruh modul (kecuali beberapa fitur Enterprise Edition seperti Data Guard penuh dan RAC yang hanya bisa disimulasikan konseptual). Ini dipilih dengan alasan yang sama seperti `courses/vps` memilih VPS nyata/lab lokal: hands-on di instance sungguhan, bukan slide.

Referensi riset yang dipakai menyusun roadmap ini:

* [Oracle Database Administration I — Exam 1Z0-082 (education.oracle.com)](https://education.oracle.com/oracle-database-administration-i/pexam_1Z0-082) dan [Oracle Database Administration II — Exam 1Z0-083 (education.oracle.com)](https://education.oracle.com/oracle-database-administration-ii/pexam_1Z0-083) — dipakai sebagai kerangka topik OCA/OCP (arsitektur, instance, storage, security, backup, performance tuning) yang jadi tulang punggung urutan Modul 1-8.
* [Oracle Database 23ai Administration Certified Professional & Associate paths (education.oracle.com)](https://education.oracle.com/oracle-database-23ai-administration-certified-professional/trackp_ODB23AIOCP) — konfirmasi jalur sertifikasi OCA → OCP terkini dan exam number yang relevan (1Z0-182 untuk Associate versi 23ai).
* [Oracle Database Backup and Recovery User's Guide — Getting Started with RMAN (docs.oracle.com, 19c)](https://docs.oracle.com/en/database/oracle/oracle-database/19/bradv/getting-started-rman.html) — dokumentasi resmi dipakai sebagai kerangka utama Modul 6 (RMAN): komponen (target database, RMAN repository di control file, recovery catalog opsional, fast recovery area), tipe backup (backup set vs image copy, full vs incremental level 0/1 differential/cumulative), dan workflow restore/recover termasuk Flashback Database dan Data Recovery Advisor.
* [ORACLE-BASE.com](https://oracle-base.com/) — referensi independen paling banyak dirujuk komunitas DBA Oracle (setara "MDN untuk Oracle"), dipakai untuk memverifikasi cakupan topik praktis lintas versi: RMAN incremental/duplicate database, Data Pump, Data Guard, patching, tuning berbasis AWR/ADDM, dan artikel diagnostik (ADRCI, Autonomous Health Framework).
* Dokumentasi resmi Oracle Database Concepts (docs.oracle.com/database) untuk arsitektur instance vs database, SGA/PGA, dan background processes yang jadi fondasi Modul 1.

---

# Learning Objectives

* [ ] Memahami arsitektur Oracle Database secara mendalam: instance vs database, SGA (Shared Pool, Database Buffer Cache, Redo Log Buffer, Large Pool, Java Pool), PGA, dan background processes utama (PMON, SMON, DBWn, LGWR, CKPT, ARCn, MMON)
* [ ] Mampu instalasi Oracle Database dan membuat database baru dari nol (DBCA), termasuk konfigurasi listener untuk koneksi jaringan
* [ ] Menguasai struktur storage: tablespace, datafile, control file, redo log (online & archived), undo tablespace, dan arsitektur multitenant (CDB/PDB)
* [ ] Mahir SQL*Plus/SQLcl serta mengelola user, role, system/object privilege, dan profile (password policy, resource limit) sesuai prinsip least privilege
* [ ] Mampu menulis dan membaca PL/SQL esensial untuk kebutuhan administratif: block, procedure, package sederhana, trigger, dan scheduled job via `DBMS_SCHEDULER`
* [ ] Mahir backup & recovery dengan RMAN: full backup, incremental backup (level 0/1), configure retention policy, dan yang terpenting — **melakukan point-in-time recovery yang benar-benar berhasil**, bukan cuma teori
* [ ] Mampu memakai Data Pump (`expdp`/`impdp`) untuk backup logis, migrasi schema, dan transportable tablespace, serta memahami kapan pakai Data Pump vs RMAN
* [ ] Mampu melakukan performance tuning fundamental: baca execution plan, gunakan AWR/ADDM untuk mendiagnosis bottleneck, dan menentukan strategi indexing yang tepat
* [ ] Memahami konsep high availability & replication Oracle (Data Guard: physical/logical standby, protection mode, switchover/failover) di level konseptual dan praktik dasar
* [ ] Mampu merencanakan dan mengeksekusi patching/upgrade Oracle Database dengan aman (OPatch, Release Update, AutoUpgrade)
* [ ] Mahir troubleshooting operasional: membaca alert log, memakai ADRCI, dan mendiagnosis error Oracle umum (ORA-xxxxx) secara sistematis
* [ ] Menyelesaikan proyek portofolio: satu instance Oracle Database yang diadministrasi penuh — dari instalasi sampai backup/recovery teruji dan studi kasus tuning nyata

---

# Course Modules

## Module 1 — Fondasi Arsitektur Oracle Database

Description: Modul paling penting di seluruh course — semua modul berikutnya (storage, backup/recovery, tuning) hanya masuk akal kalau arsitektur instance vs database sudah dipahami betul. Jangan buru-buru lewat modul ini.

Lessons:

* [ ] 1.1 Instance vs Database: kenapa Oracle memisahkan keduanya (instance = memory + process yang bisa mati/hidup; database = file fisik yang persisten), dan kenapa satu database bisa dilayani lebih dari satu instance (RAC, disinggung sekilas)
* [ ] 1.2 System Global Area (SGA): Shared Pool (library cache, data dictionary cache), Database Buffer Cache, Redo Log Buffer, Large Pool, Java Pool — apa fungsi masing-masing dan kenapa ukurannya berpengaruh ke performance
* [ ] 1.3 Program Global Area (PGA) dan background processes utama: PMON (process monitor), SMON (system monitor/recovery), DBWn (database writer), LGWR (log writer), CKPT (checkpoint), ARCn (archiver), MMON (manageability monitor)
* [ ] 1.4 Multitenant architecture sekilas: Container Database (CDB) vs Pluggable Database (PDB) — kenapa ini jadi default sejak Oracle 12c dan wajib dipahami sebelum instalasi di Modul 2

Mini Project: Diagram arsitektur Oracle Database (instance-database, komponen SGA/PGA, alur background process saat transaksi commit) digambar dan dijelaskan dengan kata-kata sendiri — validasi paham, bukan hafal istilah.

Status: Not Started

---

## Module 2 — Instalasi & Pembuatan Database

Description: Dari nol sampai punya instance Oracle yang hidup dan bisa diakses dari luar mesin — praktik langsung, bukan cuma baca.

Lessons:

* [ ] 2.1 Memilih environment lab: Oracle Database XE (Express Edition, gratis) vs image container resmi Oracle (`container-registry.oracle.com`) vs Enterprise Edition trial — trade-off kemudahan setup vs kelengkapan fitur (XE tidak punya Data Guard/RAC penuh)
* [ ] 2.2 Instalasi Oracle software: konsep Oracle Home, Oracle Base, OFA (Optimal Flexible Architecture), environment variable (`ORACLE_HOME`, `ORACLE_SID`)
* [ ] 2.3 Membuat database dengan DBCA (Database Configuration Assistant): memilih template, karakter set, ukuran memory, CDB/PDB, review generated script
* [ ] 2.4 Konfigurasi Oracle Net Listener (`listener.ora`, `tnsnames.ora`): supaya database bisa diakses dari client/aplikasi lewat jaringan, verifikasi dengan `tnsping` dan `lsnrctl status`

Mini Project: Instalasi Oracle Database (XE atau container) dari nol, buat 1 CDB dengan minimal 1 PDB via DBCA, dan buktikan bisa connect dari client eksternal (SQL*Plus dari mesin lain atau tool seperti DBeaver) lewat listener yang sudah dikonfigurasi.

Status: Not Started

---

## Module 3 — Struktur Storage

Description: Bagaimana Oracle benar-benar menyimpan data di disk — fondasi untuk memahami backup/recovery (Modul 6) dan tuning I/O (Modul 8).

Lessons:

* [ ] 3.1 Tablespace & datafile: SYSTEM, SYSAUX, USERS, TEMP, UNDO — fungsi masing-masing tablespace default dan kapan membuat tablespace custom
* [ ] 3.2 Control file: apa isinya (metadata struktur fisik database, current SCN, informasi RMAN), kenapa harus dimultiplex, dan apa yang terjadi kalau hilang
* [ ] 3.3 Redo log: online redo log group/member, log switch, ARCHIVELOG vs NOARCHIVELOG mode — keputusan paling krusial sebelum bisa melakukan recovery serius (jembatan langsung ke Modul 6)
* [ ] 3.4 Undo tablespace (read consistency, rollback) dan sekilas Automatic Storage Management (ASM) sebagai alternatif filesystem-managed storage di lingkungan enterprise

  > **Bridging ke PostgreSQL**: tablespace Oracle mirip konsep tablespace PostgreSQL tapi jauh lebih granular (SYSTEM/SYSAUX/UNDO/TEMP terpisah eksplisit); ARCHIVELOG mode Oracle setara `wal_level` + `archive_mode` PostgreSQL; undo tablespace Oracle setara mekanisme MVCC PostgreSQL tapi dengan penyimpanan before-image eksplisit, bukan versi tuple in-place.

Mini Project: Audit storage struktur database lab: list semua tablespace beserta ukuran dan persentase pemakaian, aktifkan ARCHIVELOG mode, dan multiplex control file ke lokasi kedua.

Status: Not Started

---

## Module 4 — SQL*Plus/SQLcl & Schema, User, Security Management

Description: Tooling harian seorang DBA dan bagaimana mengelola akses secara aman — prinsip least privilege ala Oracle.

Lessons:

* [ ] 4.1 SQL*Plus dan SQLcl: perbedaan, koneksi (`sqlplus`, `CONNECT`, `AS SYSDBA`), perintah admin esensial (`STARTUP`/`SHUTDOWN` dengan mode NORMAL/IMMEDIATE/ABORT, `SPOOL`, `SET` formatting)
* [ ] 4.2 User & schema management: `CREATE USER`, default tablespace & quota, `ALTER USER`, kapan schema = user (khas Oracle, beda dari PostgreSQL yang memisahkan keduanya)
* [ ] 4.3 Roles & privileges: system privilege vs object privilege, `GRANT`/`REVOKE`, role predefined (`DBA`, `CONNECT`, `RESOURCE`) vs custom role, prinsip least privilege
* [ ] 4.4 Profile & auditing: password policy (`PASSWORD_LIFE_TIME`, `FAILED_LOGIN_ATTEMPTS`), resource limit, dan unified auditing dasar untuk mencatat aktivitas sensitif

  > **Bridging ke PostgreSQL**: `GRANT`/`REVOKE` konsepnya identik; bedanya Oracle punya profile sebagai objek terpisah untuk password policy (PostgreSQL biasanya diserahkan ke `pg_hba.conf`/extension), dan di Oracle *user = schema* sehingga tidak ada pemisahan eksplisit seperti `CREATE SCHEMA` di PostgreSQL.

Mini Project: Buat model security untuk aplikasi contoh: 1 schema owner (privilege penuh di schema-nya), 1 role read-only untuk reporting, 1 user aplikasi dengan privilege minimal sesuai kebutuhan CRUD saja, plus profile dengan password policy yang wajar untuk production.

Status: Not Started

---

## Module 5 — PL/SQL Essentials untuk DBA

Description: PL/SQL secukupnya untuk automation dan maintenance operasional — bukan jalur PL/SQL developer penuh (lihat Catatan Scope).

Lessons:

* [ ] 5.1 PL/SQL block dasar: `DECLARE`/`BEGIN`/`EXCEPTION`/`END`, variabel, tipe data, kontrol alur (`IF`, `LOOP`, `FOR`)
* [ ] 5.2 Cursor, exception handling (`WHEN OTHERS`, custom exception), dan kenapa penanganan error yang benar penting untuk script maintenance DBA
* [ ] 5.3 Stored procedure, function, dan package sederhana — cukup untuk membungkus rutinitas administratif (mis. cleanup job, validasi sebelum maintenance window)
* [ ] 5.4 `DBMS_SCHEDULER` untuk menjadwalkan job maintenance (mis. gather statistics rutin, purge log table) — pengganti modern dari `DBMS_JOB`

  > **Bridging ke PostgreSQL**: PL/SQL vs PL/pgSQL sangat mirip secara sintaks (`BEGIN`/`END`, exception handling), tapi `DBMS_SCHEDULER` Oracle jauh lebih kaya fitur (resource plan, window, chain job) dibanding `pg_cron` yang lebih minimalis.

Mini Project: Tulis 1 stored procedure untuk maintenance rutin (mis. purge baris log lebih dari 30 hari dengan exception handling yang layak), lalu jadwalkan otomatis harian via `DBMS_SCHEDULER` dan buktikan job history-nya jalan.

Status: Not Started

---

## Module 6 — Backup & Recovery dengan RMAN

Description: **Modul dengan bobot paling besar di course ini.** Kompetensi backup/recovery yang benar-benar teruji adalah pembeda utama antara DBA junior dan DBA yang bisa dipercaya production. Target modul ini bukan "paham konsep RMAN", tapi "pernah benar-benar merusak database lab dan berhasil memulihkannya".

Lessons:

* [ ] 6.1 Arsitektur RMAN: target database, RMAN repository (tersimpan di control file), recovery catalog opsional (kapan perlu), fast recovery area, koneksi (`rman TARGET /`)
* [ ] 6.2 Konfigurasi persistent settings (`CONFIGURE RETENTION POLICY`, `CONFIGURE DEFAULT DEVICE TYPE`, `CONFIGURE CONTROLFILE AUTOBACKUP`) dan tipe backup: backup set vs image copy
* [ ] 6.3 Full backup dan incremental backup: level 0 (basis), level 1 differential (sejak backup terakhir apa pun) vs level 1 cumulative (sejak level 0 terakhir) — trade-off waktu backup vs waktu recovery
* [ ] 6.4 Restore & recover — **complete recovery**: skenario datafile hilang/corrupt, `RESTORE DATABASE` + `RECOVER DATABASE`, verifikasi hasil
* [ ] 6.5 Restore & recover — **incomplete recovery (point-in-time recovery)**: recovery ke SCN/timestamp/restore point tertentu (mis. sebelum `DROP TABLE` tanpa flashback), termasuk `FLASHBACK DATABASE` sebagai alternatif yang lebih cepat
* [ ] 6.6 RMAN reporting & maintenance: `LIST BACKUP`, `REPORT OBSOLETE`, `CROSSCHECK`, `DELETE OBSOLETE`, Data Recovery Advisor (`LIST FAILURE`, `ADVISE FAILURE`) untuk diagnosis semi-otomatis

  > **Bridging ke PostgreSQL**: RMAN kira-kira menggabungkan peran `pg_basebackup` + WAL archiving + `pg_dump` fisik dalam satu tool terintegrasi dengan katalog metadata sendiri — sesuatu yang di ekosistem PostgreSQL biasanya dirakit manual dari beberapa tool (`pgBackRest`/`barman` mendekati filosofi RMAN). Incomplete recovery Oracle setara PITR PostgreSQL via `restore_command` + `recovery_target_time`.

Mini Project (wajib, bukan opsional): Di database lab, buat data penting (tabel dengan beberapa baris "berharga"), ambil full backup + incremental, lalu **sengaja rusak** database (hapus datafile, atau `DROP TABLE` tanpa flashback diaktifkan), lakukan restore & recovery sampai data kembali utuh, dan catat langkah-langkah beserta output RMAN sebagai bukti (dipakai lagi di Capstone).

Status: Not Started

---

## Module 7 — Data Pump & Migrasi Logis

Description: Backup fisik (RMAN) dan backup logis (Data Pump) menyelesaikan masalah yang berbeda — modul ini memastikan tahu kapan pakai yang mana.

Lessons:

* [ ] 7.1 Data Pump architecture: `expdp`/`impdp`, directory object (`CREATE DIRECTORY`), perbedaan mendasar dari `exp`/`imp` lama
* [ ] 7.2 Export & import: full database, per-schema, per-table, dengan filter (`INCLUDE`/`EXCLUDE`), parallel untuk database besar
* [ ] 7.3 Transportable tablespace untuk migrasi data volume besar antar database secara cepat (copy datafile + metadata, bukan row-by-row)
* [ ] 7.4 Kapan pakai Data Pump vs RMAN: migrasi/upgrade versi vs disaster recovery, restore sebagian schema vs restore seluruh database, kecepatan vs kelengkapan

  > **Bridging ke PostgreSQL**: Data Pump kira-kira setara `pg_dump`/`pg_restore` (backup logis, portable antar versi/platform) sementara RMAN setara `pg_basebackup`+WAL (backup fisik, cepat, tapi versi/platform-dependent) — trade-off yang sama persis ada di kedua ekosistem.

Mini Project: Export 1 schema penuh dengan `expdp`, import ke PDB/database lain (simulasikan "migrasi ke server baru"), dan verifikasi data + object (index, constraint) konsisten setelah import.

Status: Not Started

---

## Module 8 — Performance Tuning Fundamental

Description: Dari "kenapa query ini lambat?" sampai bisa menjawabnya dengan data, bukan tebakan.

Lessons:

* [ ] 8.1 Optimizer statistics: `DBMS_STATS`, kenapa statistics yang stale membuat optimizer memilih execution plan yang buruk
* [ ] 8.2 Membaca execution plan: `EXPLAIN PLAN`, `DBMS_XPLAN.DISPLAY`, autotrace — mengenali full table scan yang tidak perlu, join method yang mahal
* [ ] 8.3 AWR (Automatic Workload Repository) & ADDM (Automatic Database Diagnostic Monitor): snapshot, report, mengidentifikasi top wait event dan SQL paling mahal
* [ ] 8.4 Strategi indexing: B-tree vs bitmap index (kapan masing-masing tepat), function-based index, kapan index justru memperlambat (write-heavy table)

  > **Bridging ke PostgreSQL**: `DBMS_XPLAN.DISPLAY` setara `EXPLAIN (ANALYZE, BUFFERS)` PostgreSQL; AWR/ADDM setara kombinasi `pg_stat_statements` + `pg_stat_activity` tapi jauh lebih terintegrasi (snapshot historis otomatis, built-in advisor) — salah satu keunggulan operasional Oracle Enterprise Edition dibanding stack open-source.

Mini Project: Ambil 1 query lambat di database lab (buat sengaja lewat query tanpa index yang tepat di tabel besar), diagnosis pakai execution plan + AWR report, terapkan fix (index atau rewrite query), dan buktikan dengan angka (waktu eksekusi sebelum vs sesudah).

Status: Not Started

---

## Module 9 — High Availability & Data Guard

Description: Level konseptual sampai praktik dasar — memahami bagaimana Oracle menjaga database tetap hidup saat server utama gagal.

Lessons:

* [ ] 9.1 Konsep Data Guard: physical standby (byte-for-byte, redo apply) vs logical standby (SQL apply, bisa dipakai baca-tulis terbatas)
* [ ] 9.2 Redo transport: ARCH vs LGWR transport, synchronous vs asynchronous — trade-off zero data loss vs latency
* [ ] 9.3 Protection mode: Maximum Protection, Maximum Availability, Maximum Performance — kapan pilih yang mana sesuai kebutuhan bisnis (RPO/RTO)
* [ ] 9.4 Switchover (terencana) vs failover (darurat), Data Guard Broker (`DGMGRL`) untuk manajemen yang lebih mudah, dan sekilas RAC sebagai lapisan HA yang berbeda (di luar praktik course ini)

  > **Bridging ke PostgreSQL**: physical standby Data Guard setara streaming replication PostgreSQL (WAL shipping); protection mode Maximum Protection setara `synchronous_commit = remote_apply`, sementara Maximum Performance setara replication asynchronous default PostgreSQL.

Mini Project: Setup physical standby database sederhana antara dua instance lab (bisa dua container/VM), verifikasi redo apply berjalan (data baru di primary muncul di standby), dan lakukan 1 switchover terencana yang berhasil.

Status: Not Started

---

## Module 10 — Patching & Upgrade

Description: Menjaga database tetap up-to-date dan aman tanpa mematikan production tanpa rencana.

Lessons:

* [ ] 10.1 Konsep patching Oracle: Release Update (RU), Release Update Revision (RUR), one-off patch, dan `OPatch` sebagai tool aplikasinya
* [ ] 10.2 Proses apply patch: pre-check, apply ke Oracle Home, `datapatch` untuk update SQL-level di database, verifikasi
* [ ] 10.3 Upgrade versi database: `AutoUpgrade` tool, pre-upgrade checks, kapan upgrade in-place vs kapan migrasi via Data Pump/RMAN duplicate
* [ ] 10.4 Merencanakan maintenance window: komunikasi downtime, rollback plan kalau patching gagal, testing di environment non-production dulu

Mini Project: Simulasikan siklus patching di database lab: cek versi & patch level saat ini, apply satu Release Update (atau simulasikan langkahnya kalau image lab sudah versi terbaru), dan tulis maintenance runbook singkat.

Status: Not Started

---

## Module 11 — Monitoring & Troubleshooting Operasional

Description: Modul penutup — kemampuan bertahan hidup sehari-hari sebagai DBA: database yang jalan hari ini harus tetap bisa dipercaya besok.

Lessons:

* [ ] 11.1 Alert log: lokasi, cara membaca, error yang wajib diperhatikan (ORA-600, ORA-7445, tablespace full, archiver stuck)
* [ ] 11.2 Automatic Diagnostic Repository (ADR) & `ADRCI`: struktur direktori diagnostik, mencari incident, packaging diagnostic untuk Oracle Support
* [ ] 11.3 Enterprise Manager Database Express (EM Express) atau Cloud Control sekilas: monitoring visual sebagai pelengkap (bukan pengganti) command-line skill
* [ ] 11.4 Troubleshooting checklist production: database hang, session yang blocking, tablespace mendekati penuh, listener tidak menerima koneksi — diagnosis sistematis, bukan panik

Mini Project: Audit kesehatan database lab: cek alert log untuk error 7 hari terakhir, identifikasi session blocking (kalau ada, simulasikan satu), dan tulis checklist troubleshooting pribadi yang bisa dipakai lagi di masa depan.

Status: Not Started

---

# Capstone Project

Description: **Full Oracle Database Administration** — satu instance Oracle Database (Oracle XE atau container resmi Oracle) yang diadministrasi penuh dari nol: (1) instalasi dan pembuatan database dengan arsitektur CDB/PDB yang dipahami, bukan sekadar mengikuti wizard (Modul 1-2), (2) storage terkonfigurasi dengan benar termasuk ARCHIVELOG mode aktif (Modul 3), (3) model security terdokumentasi: user, role, privilege, profile sesuai least privilege (Modul 4), (4) minimal satu job maintenance otomatis via PL/SQL + `DBMS_SCHEDULER` (Modul 5), (5) **RMAN backup/recovery drill yang benar-benar diuji** — bukan diasumsikan jalan (Modul 6), dan (6) studi kasus performance tuning tertulis pada satu query lambat nyata, dengan bukti before/after (Modul 8).

Acceptance Criteria:

* Database instance hidup dan bisa diakses via listener dari client eksternal (bukan hanya `sqlplus / as sysdba` lokal di server)
* ARCHIVELOG mode aktif dan control file dimultiplex ke minimal 2 lokasi
* Model user/role/privilege/profile terdokumentasi jelas: siapa punya akses apa dan kenapa (prinsip least privilege terlihat, bukan semua user diberi `DBA` role)
* RMAN backup/recovery drill terbukti berhasil: ada bukti (log/output RMAN) dari skenario "database dirusak sengaja → di-restore & recover sampai data kembali utuh", termasuk minimal satu skenario point-in-time recovery
* Studi kasus tuning tertulis: satu query lambat, diagnosis pakai execution plan/AWR, fix diterapkan, dan perbaikan dibuktikan dengan angka (waktu eksekusi sebelum vs sesudah)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur singkat (diagram instance/storage) dan runbook operasional (cara restore, cara jalankan job maintenance)

Status: Not Started

---

# Overall Progress

Module Completion: 0/11

Overall Completion: 0%

Current Module: Roadmap draft — menunggu approval user, belum dimulai (mengikuti kebijakan hemat-token repo, tidak otomatis jadi course aktif)

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Instance vs Database: kenapa Oracle memisahkan keduanya, dan kenapa satu database bisa dilayani lebih dari satu instance
