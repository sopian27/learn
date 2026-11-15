# Incident Debugging Demo (Lesson 13.5)

Companion demo untuk lesson 13.5. Data buatan: tiga sumber sinyal (metric, trace, log) untuk satu insiden checkout, dibaca berurutan seperti on-call.

## Run

```
java IncidentDebugDemo.java
```

## Yang diperhatikan

1. Metric mempersempit: cuma `/checkout` yang rusak, endpoint lain sehat.
2. Trace menunjuk service: `payment-service` terlama di trace yang error, bukan gateway atau order.
3. Log (difilter pakai trace ID) memberi penyebab: connection pool habis.
4. Urutannya penting: tiap langkah mempersempit area cari langkah berikutnya.
