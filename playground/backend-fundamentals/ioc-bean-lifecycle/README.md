# IoC Bean Lifecycle Demo (Lesson 4.1)

Mini container (bukan Spring asli) yang mencetak urutan lifecycle bean: definition → instantiation → injection → post-processing → init → destroy.

## Jalankan

```
java IocBeanLifecycleDemo.java
```

## Yang diperhatikan

- Saat `refresh()` mulai, definisi sudah ada tapi objek 0. Definition adalah resep, bukan objek.
- `orderService` bergantung ke `dataSource`, jadi `dataSource` dibuat duluan walau didaftarkan belakangan.
- Constructor `OrderService` jalan saat `ds` masih null. Dependency baru ada setelah injection. Inilah kenapa logika yang butuh dependency tidak boleh di constructor field-injection.
- `postProcessAfterInitialization` adalah titik Spring mengganti bean asli dengan proxy.
- Destroy berjalan urutan terbalik: yang paling bergantung ditutup duluan.
- Ini demo, bukan implementasi Spring sebenarnya.
