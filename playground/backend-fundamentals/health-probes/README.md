# Health Probes Demo (Lesson 12.5)

Companion demo untuk lesson 12.5. Simulasi in-memory (tanpa Kubernetes): bedanya liveness dan readiness waktu DB mati dan waktu deploy.

## Run

```
java HealthProbesDemo.java
```

## Yang diperhatikan

1. Liveness yang ngecek DB: DB mati, semua pod di-restart (restart storm), traffic nol.
2. Liveness cuma "proses hidup" + readiness ngecek DB: pod tetap hidup, cuma berhenti dapat traffic, dan balik sendiri waktu DB pulih.
3. Pod baru yang belum warm-up nggak dapat traffic sampai readiness lolos.
