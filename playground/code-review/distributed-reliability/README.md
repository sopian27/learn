# Distributed Reliability (retry, idempotency, duplicate event)

Pendamping Library Lesson 5.4. Jaringan, database, dan Kafka ditiru di memori, bukan Spring/Kafka asli.

```
java ReliabilityDemo.java
```

Yang diliat:
- Baris 1 vs 2: retry yang sama, bedanya cuma `Idempotency-Key`. Tanpa key kepotong 200, dengan key 100.
- Baris 3 vs 4: event yang sama datang dua kali, dedup menahan poin dobel.

Batasnya: `HashSet` hilang saat restart dan tidak dibagi antar instance. Di sistem nyata pakai unique constraint di database, satu transaction dengan efeknya.
