# Course Roadmap

## Course Information

* Course Name: VPS (Virtual Private Server) — dari 0 sampai Expert
* Category: Linux System Administration / Server Operations
* Difficulty: Level 1 (belum pernah pegang server, hanya tahu istilah "VPS") → Level 4 (Expert, mampu provision, harden, deploy, dan mengoperasikan server production sendirian tanpa panduan)
* Estimated Duration: 9 modul, 3-4 lesson/modul, ~30-40 menit/lesson
* Prerequisites: Bisa pakai terminal dasar (`cd`, `ls`, edit file) dan familiar Java/Spring Boot (dari repo ini, dipakai sebagai aplikasi yang di-deploy di Modul 6). Tidak perlu menyelesaikan `courses/cloud-devops-foundations` atau `courses/kubernetes` lebih dulu — course ini adalah lapisan **paling dasar dan paling konkret**: satu server Linux sungguhan, disentuh langsung lewat SSH.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini murni operasional single-server, sengaja tidak tumpang tindih dengan course lain yang sudah ada di repo:

1. **Beda dari `courses/cloud-devops-foundations`.** Course itu adalah lapisan *konsep* provider-agnostic (IaaS/PaaS/SaaS, Terraform, SRE/SLO) — tidak pernah benar-benar login ke satu mesin dan mengetik perintah. Course ini kebalikannya: 100% hands-on di satu VPS nyata (DigitalOcean/Linode/Vultr/Hetzner, atau lab lokal via VirtualBox/Vagrant kalau tidak mau bayar), dari `ssh root@ip` pertama kali sampai aplikasi live. Modul 9.1 di course ini secara eksplisit menunjuk balik ke Terraform (`courses/cloud-devops-foundations` Modul 3-4) sebagai langkah *setelah* mahir manual, bukan mengulanginya.
2. **Beda dari `courses/kubernetes`.** Course itu mengajarkan orkestrasi container (Docker → K8s → OpenShift) untuk banyak server/pod. Course ini mengajarkan cara sebuah aplikasi hidup di **satu mesin Linux telanjang** — fondasi yang tetap relevan bahkan kalau nanti pakai K8s, karena node K8s pun adalah VPS yang di-harden dengan cara yang sama.
3. **Beda dari `courses/cybersecurity`.** Course itu membahas AppSec mendalam (threat modeling STRIDE, OWASP Top 10, security code review). Course ini hanya membahas hardening di **level OS/infrastruktur** (SSH, firewall, patching) secukupnya untuk server production yang aman — Modul 7.4 secara eksplisit merujuk balik ke `courses/cybersecurity` untuk keamanan aplikasi, tidak mengulang.
4. **Beda dari `courses/observability`.** Course itu (draft, belum dimulai) membahas stack tool-specific (Prometheus/Grafana/ELK) mendalam. Modul 8 course ini hanya monitoring/logging *level single-box* yang cukup untuk operasional harian (journalctl, htop, logrotate) — bukan pengganti observability stack penuh.
5. **Beda dari `courses/ci-cd`.** Course itu membahas pipeline (GitLab CI) yang men-*trigger* deployment. Course ini membahas apa yang terjadi *di dalam server* saat deployment itu mendarat (systemd service, zero-downtime restart) — keduanya saling melengkapi, tidak duduk di lapisan yang sama.
6. **Track paralel/independen dari Master Goal**, sama seperti course lain yang bukan bagian inti "World-Class Backend Developer" (Backend Fundamentals, System Design) — tapi sangat applied dan langsung dipakai untuk deploy portofolio dari course lain (mis. hasil `courses/backend-fundamentals` atau `courses/system-design`) ke server nyata yang bisa ditunjukkan ke klien freelance.

Referensi riset yang dipakai menyusun roadmap ini: [roadmap.sh — Linux Roadmap](https://roadmap.sh/linux) dan [roadmap.sh — Linux Server Setup project](https://roadmap.sh/projects/linux-server-setup) (kerangka topik & project-based learning), [DigitalOcean — Initial Server Setup with Ubuntu](https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu) (standar industri de-facto untuk hardening awal: non-root sudo user, SSH key auth, firewall — dipakai sebagai kerangka Modul 3-4), [Certbot / EFF official documentation](https://certbot.eff.org/instructions?ws=nginx&os=snap) (workflow Let's Encrypt + auto-renewal via systemd timer, dipakai sebagai kerangka Modul 7), dokumentasi resmi nginx, systemd (freedesktop.org), OpenSSH, dan PostgreSQL/Redis (sudah menjadi trusted source standar repo ini).

---

# Learning Objectives

* [ ] Memahami apa itu VPS (virtualisasi KVM/Xen) dan bisa memilih provider secara sadar trade-off (harga, lokasi, dukungan)
* [ ] Mahir navigasi Linux sebagai server admin: filesystem hierarchy, permission, package management, process management
* [ ] Mampu men-*setup* akses server yang aman: non-root sudo user, SSH key-only auth, fail2ban
* [ ] Mampu mengkonfigurasi firewall (ufw) dan DNS dasar agar server hanya expose apa yang perlu
* [ ] Mahir nginx sebagai web server & reverse proxy, termasuk load balancing dasar
* [ ] Mampu men-deploy aplikasi Spring Boot production sebagai systemd service dengan database (PostgreSQL/Redis) di server yang sama
* [ ] Mampu mengamankan aplikasi dengan HTTPS (Let's Encrypt/certbot, auto-renewal) dan hardening OS lanjutan
* [ ] Mampu menjalankan operasional harian: logging, monitoring dasar, backup terjadwal, dan disaster recovery
* [ ] Memahami kapan 1 VPS tidak lagi cukup dan langkah selanjutnya (automasi provisioning, scaling, kapan pindah ke K8s)
* [ ] Menyelesaikan proyek portofolio: satu aplikasi production live di VPS nyata, dari server kosong sampai HTTPS + monitoring + backup

---

# Course Modules

## Module 1 — Fondasi VPS & Provisioning

Description: Sebelum menyentuh command apa pun, pahami dulu apa itu VPS dan bagaimana memilih server pertama secara sadar, bukan asal klik.

Lessons:

* [ ] 1.1 Apa itu VPS: virtualisasi (KVM/Xen), VPS vs shared hosting vs dedicated server vs cloud instance (EC2/Droplet)
* [ ] 1.2 Memilih provider: DigitalOcean, Linode/Akamai, Vultr, Hetzner — trade-off harga, lokasi datacenter, dukungan, dan kapan pakai lab lokal (VirtualBox/Vagrant) untuk latihan gratis
* [ ] 1.3 Provisioning VPS pertama: memilih OS (Ubuntu LTS), sizing (vCPU/RAM/disk), region
* [ ] 1.4 Koneksi pertama: SSH dasar (`ssh root@ip`), perintah eksplorasi awal server baru

Mini Project: Provision 1 VPS nyata (atau VM lab lokal), SSH masuk pertama kali, dan tulis catatan spesifikasi + alasan pemilihan provider/sizing.

Status: Not Started

---

## Module 2 — Linux Fundamentals untuk Server Admin

Description: Kemampuan navigasi dan diagnosa dasar yang dipakai di hampir setiap modul selanjutnya.

Lessons:

* [ ] 2.1 Filesystem Hierarchy Standard (`/etc`, `/var`, `/opt`, `/usr`, `/home`), navigasi, permission (`chmod`/`chown`/`umask`)
* [ ] 2.2 Package management dengan `apt` (install/update/upgrade/remove), dasar `dpkg`
* [ ] 2.3 Process & service management dasar: `ps`, `top`/`htop`, `kill`, melihat apa yang sedang berjalan
* [ ] 2.4 Text processing esensial untuk sysadmin: `grep`, `less`, `tail -f`, cukup `sed`/`awk` untuk baca log

Mini Project: Audit sebuah server (user apa saja ada, proses apa berjalan, disk usage per direktori) dan tulis laporan singkat temuan.

Status: Not Started

---

## Module 3 — User, Access & SSH Hardening

Description: Langkah pertama yang wajib dilakukan di server production mana pun — mengikuti standar industri DigitalOcean Initial Server Setup.

Lessons:

* [ ] 3.1 User & group management, `sudo` dan principle of least privilege (jangan kerja sebagai root)
* [ ] 3.2 SSH key-based authentication: generate key pair, copy public key, disable password authentication
* [ ] 3.3 SSH hardening lanjut: disable root login, pertimbangan ganti port default, `fail2ban` dasar untuk brute-force protection
* [ ] 3.4 Workflow multi-server: `~/.ssh/config` untuk mengelola banyak server dengan rapi

Mini Project: Setup server dengan non-root sudo user, SSH key-only (password auth mati), root login dimatikan, fail2ban aktif.

Status: Not Started

---

## Module 4 — Firewall & Jaringan Dasar

Description: Server yang aman adalah server yang hanya membuka apa yang benar-benar perlu dibuka.

Lessons:

* [ ] 4.1 Konsep jaringan dasar untuk sysadmin: port, protokol, `netstat`/`ss` untuk melihat apa yang listening
* [ ] 4.2 `ufw` (Uncomplicated Firewall): default deny, allow spesifik service, cek status
* [ ] 4.3 Sekilas `iptables` — memahami apa yang sebenarnya dikerjakan `ufw` di baliknya
* [ ] 4.4 DNS dasar: record A/AAAA/CNAME, propagasi, mengarahkan domain pribadi ke IP VPS

Mini Project: Firewall server hanya expose port yang perlu (SSH, 80, 443), dan domain pribadi berhasil mengarah ke VPS.

Status: Not Started

---

## Module 5 — Web Server & Reverse Proxy (Nginx)

Description: Nginx sebagai pintu masuk tunggal ke server — dasar untuk semua deployment aplikasi di modul berikutnya.

Lessons:

* [ ] 5.1 Instalasi nginx, konsep web server vs reverse proxy
* [ ] 5.2 Server block / virtual host, serving static site
* [ ] 5.3 Reverse proxy ke aplikasi backend yang berjalan di port lokal (mis. Spring Boot di `:8080`)
* [ ] 5.4 Load balancing dasar dengan nginx (`upstream` block) untuk lebih dari satu instance aplikasi

Mini Project: Deploy static landing page via nginx, plus reverse-proxy ke satu aplikasi Spring Boot sederhana yang jalan di port lokal.

Status: Not Started

---

## Module 6 — Deploy Aplikasi Production (Spring Boot di VPS)

Description: Menyatukan Modul 1-5 untuk men-deploy aplikasi nyata dengan cara yang production-grade, bukan sekadar `java -jar` di terminal yang mati saat SSH terputus.

Lessons:

* [ ] 6.1 Menjalankan JAR sebagai systemd service: unit file, auto-restart, start saat boot
* [ ] 6.2 Environment variable & konfigurasi production yang aman (profile `application-prod.yml`, memisahkan secret dari kode)
* [ ] 6.3 Deploy script sederhana dengan minimal-downtime: build → upload → `systemctl restart` → health check
* [ ] 6.4 Setup PostgreSQL & Redis di VPS: instalasi, securing akses (bind address, user/password), koneksi dari aplikasi

Mini Project: Deploy end-to-end aplikasi Spring Boot + PostgreSQL yang bisa diakses via domain, otomatis hidup lagi setelah server reboot.

Status: Not Started

---

## Module 7 — SSL/TLS & Keamanan Lanjut

Description: Aplikasi production tanpa HTTPS bukan aplikasi production.

Lessons:

* [ ] 7.1 HTTPS secukupnya: apa itu TLS handshake dan kenapa ini wajib, bukan opsional
* [ ] 7.2 Let's Encrypt + certbot: mendapatkan sertifikat, konfigurasi nginx, auto-renewal via systemd timer
* [ ] 7.3 Hardening lanjutan: `unattended-upgrades`, mematikan service yang tidak dipakai, security header dasar di nginx
* [ ] 7.4 Batas scope: kapan harus lanjut ke `courses/cybersecurity` untuk threat modeling & keamanan aplikasi mendalam (tidak diulang di sini)

Mini Project: Aplikasi dari Modul 6 accessible via HTTPS dengan sertifikat valid dan auto-renewal terbukti jalan (`certbot renew --dry-run`).

Status: Not Started

---

## Module 8 — Monitoring, Logging & Backup

Description: Server yang jalan hari ini harus tetap bisa dipercaya minggu depan — operasional harian, bukan cuma setup sekali jadi.

Lessons:

* [ ] 8.1 Logging dasar: `journalctl`, `logrotate`, mengarahkan log aplikasi ke file yang rapi
* [ ] 8.2 Monitoring resource level single-box (`htop`, `df`, `uptime`); rujuk `courses/observability` untuk stack tool-specific (Prometheus/Grafana) yang lebih dalam
* [ ] 8.3 Backup strategy: dump database terjadwal (`cron`), backup file, menyimpan di luar server (S3/rsync ke mesin lain)
* [ ] 8.4 Disaster recovery: praktik restore dari backup, menulis runbook sederhana

Mini Project: Cron job backup database harian ke storage eksternal, plus dokumen runbook restore yang sudah diuji coba benar-benar bisa memulihkan data.

Status: Not Started

---

## Module 9 — Operasional & Scaling dari 1 VPS

Description: Modul penutup — kapan cara manual di course ini mulai tidak cukup, dan ke mana melangkah selanjutnya.

Lessons:

* [ ] 9.1 Automasi provisioning dasar: shell script setup server ("poor man's IaC"), lalu kenapa pada akhirnya butuh Terraform (rujuk `courses/cloud-devops-foundations` Modul 3-4)
* [ ] 9.2 Kapan 1 VPS tidak lagi cukup: vertical vs horizontal scaling, kapan waktunya pindah ke container orchestration (rujuk `courses/kubernetes`)
* [ ] 9.3 Maintenance jangka panjang: patching rutin, kernel update, kebijakan reboot, biaya operasional
* [ ] 9.4 Troubleshooting checklist production: server down, load tinggi, disk penuh, koneksi database gagal

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full VPS Production Deployment** — pilih 1 aplikasi Spring Boot nyata/realistis (boleh hasil dari course lain, mis. `courses/backend-fundamentals` atau `courses/system-design`) dan operasikan penuh di VPS nyata: (1) provisioning dari server kosong sampai akses SSH aman (Modul 1-3), (2) firewall + domain mengarah ke server (Modul 4), (3) nginx reverse proxy (Modul 5), (4) aplikasi + PostgreSQL berjalan sebagai systemd service, restart otomatis saat reboot (Modul 6), (5) HTTPS via Let's Encrypt dengan auto-renewal terbukti (Modul 7), (6) monitoring dasar + backup terjadwal + runbook restore (Modul 8).

Acceptance Criteria:

* Server hanya bisa diakses via SSH key (password auth mati), non-root sudo user, firewall default-deny kecuali port yang perlu
* Aplikasi live dan bisa diakses lewat domain sungguhan dengan HTTPS valid (bukan `http://ip:8080`)
* Aplikasi otomatis hidup kembali setelah `reboot` server tanpa intervensi manual
* Backup database terjadwal terbukti bisa di-restore (dites, bukan diasumsikan jalan)
* Runbook operasional (deploy ulang, restore backup, troubleshooting dasar) ditulis jelas — orang lain (atau diri sendiri 6 bulan lagi) bisa mengikutinya tanpa bertanya
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact, termasuk dokumentasi arsitektur singkat

Status: Not Started

---

# Overall Progress

Module Completion: 0/9

Overall Completion: 0%

Current Module: Roadmap disetujui 2026-08-08, sengaja belum dimulai (paused, kebijakan hemat-token 2 course aktif) — lanjut kapan saja atas permintaan "lanjut course VPS"

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Apa itu VPS: virtualisasi (KVM/Xen), VPS vs shared hosting vs dedicated server vs cloud instance
