# SonarQube Lokal (Docker)

SonarQube Community, self-hosted di Docker, untuk analisis code internal.

- URL: http://localhost:9500
- Port host **9500** (bukan 9000), karena Windows me-reserve range 8976-9075 (Hyper-V/WinNAT) sehingga port 9000 tidak bisa di-bind.
- Container: `sonarqube`, image `sonarqube:community`
- Data tersimpan di volume Docker: `sonarqube_data`, `sonarqube_extensions`, `sonarqube_logs`. Data tetap ada setelah restart/stop container.
- Database: H2 embedded (hanya untuk evaluasi/internal, bukan untuk data jangka panjang).

## Menyalakan setelah restart PC

1. Buka **Docker Desktop**, tunggu sampai status "Engine running" (ikon hijau). Kalau `docker ps` masih error `Internal Server Error`, engine belum siap, tunggu beberapa saat.
2. Start container:

   ```powershell
   docker start sonarqube
   ```

3. Tunggu 1-2 menit (SonarQube butuh waktu startup), lalu cek:

   ```powershell
   curl.exe http://localhost:9500/api/system/status
   ```

   Siap kalau responsnya `{"status":"UP",...}`. Status `STARTING` berarti tunggu lagi.
4. Buka http://localhost:9500.

Agar otomatis menyala bersama Docker Desktop (opsional, jalankan sekali):

```powershell
docker update --restart unless-stopped sonarqube
```

## Perintah sehari-hari

| Tujuan | Perintah |
| --- | --- |
| Cek status container | `docker ps -a --filter name=sonarqube` |
| Lihat log | `docker logs -f sonarqube` |
| Stop | `docker stop sonarqube` |
| Start | `docker start sonarqube` |
| Restart | `docker restart sonarqube` |

## Membuat container dari nol (kalau container terhapus)

Volume tetap menyimpan data selama tidak dihapus.

```powershell
docker run -d --name sonarqube `
  -p 9500:9000 `
  -v sonarqube_data:/opt/sonarqube/data `
  -v sonarqube_extensions:/opt/sonarqube/extensions `
  -v sonarqube_logs:/opt/sonarqube/logs `
  sonarqube:community
```

Login pertama: `admin` / `admin`, lalu wajib ganti password.

## Koneksi dari Claude Code (sonarqube-cli)

```powershell
sonar auth login -s http://localhost:9500
sonar auth status
sonar integrate claude --non-interactive
```

Setelah `integrate`, restart session Claude Code supaya tool `mcp__sonarqube__*` muncul. MCP server berjalan di container, jadi Docker harus sudah menyala.

## Troubleshooting

| Gejala | Penyebab / solusi |
| --- | --- |
| `docker` error `Internal Server Error for API route` | Docker Desktop belum selesai start. Tunggu, atau restart Docker Desktop. |
| `docker start sonarqube` gagal: port not available | Cek range reserved: `netsh int ipv4 show excludedportrange protocol=tcp`. Pastikan 9500 tidak masuk range. |
| Halaman tidak terbuka, status `STARTING` | Normal 1-2 menit setelah start. Pantau `docker logs -f sonarqube`. |
| Container exit sendiri, log menyebut `vm.max_map_count` | Biasanya tidak terjadi di Docker Desktop WSL2. Kalau terjadi: `wsl -d docker-desktop sysctl -w vm.max_map_count=524288`. |
| MCP tools tidak muncul di Claude | Docker belum jalan saat session dimulai, atau session belum direstart setelah `sonar integrate`. |
