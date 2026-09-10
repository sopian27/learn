### Konteks

`SessionAnalyticsService` adalah bagian dari sebuah aplikasi Spring Boot yang mencatat aktivitas user untuk keperluan analytics ringan. Setelah berjalan beberapa jam di staging, tim ops melaporkan memory usage container terus naik tanpa pernah turun walau traffic relatif stabil (tidak ada lonjakan user) — persis pola "baseline naik terus" yang dibahas sebagai sinyal leak di Lesson 3.2.

```java
@Service
public class SessionAnalyticsService {

    // Menyimpan snapshot aktivitas tiap session untuk dianalisis batch tiap malam
    private static final Map<String, List<UserActivity>> sessionActivityLog = new ConcurrentHashMap<>();

    public void recordActivity(String sessionId, UserActivity activity) {
        sessionActivityLog
            .computeIfAbsent(sessionId, k -> new ArrayList<>())
            .add(activity);
    }

    public List<UserActivity> getActivityForSession(String sessionId) {
        return sessionActivityLog.getOrDefault(sessionId, List.of());
    }

    // Dipanggil oleh scheduled job jam 2 pagi untuk kirim data ke data warehouse
    public void flushDailyAnalytics() {
        // TODO: kirim sessionActivityLog ke data warehouse
        // (asumsikan bagian ini sudah benar dan tidak perlu diubah)
    }
}

// record UserActivity(String action, String page, Instant timestamp) -- anggap sudah ada
```

Aplikasi ini dipakai oleh e-commerce dengan traffic sekitar 5.000 session unik per hari, dan session ID **tidak pernah di-reuse** (UUID baru tiap kali user membuka aplikasi, termasuk guest/anonymous user yang tidak pernah checkout).

### Deliverable

1. **Diagnosis gejala** — jelaskan, berdasarkan pola yang dipelajari di Lesson 3.3, kenapa kode di atas hampir pasti bocor, dan pola mana (dari 3 pola Lesson 3.3) yang paling cocok menjelaskannya. Sebelum melihat kodenya lebih jauh, tuliskan dulu prediksi Anda hanya dari deskripsi gejala (baseline memory naik terus, traffic stabil).

    Static collection yang tumbuh tanpa batas.

    Kode:

    private static final Map<String, List<UserActivity>> sessionActivityLog = new ConcurrentHashMap<>();

    Masalahnya bukan karena HashMap atau ArrayList otomatis menyebabkan leak. Masalahnya adalah data terus ditambahkan, tetapi tidak ada kebijakan penghapusan atau pembatasan ukuran.

    Setiap kali event baru datang:

    sessionActivityLog
            .computeIfAbsent(sessionId, k -> new ArrayList<>())
            .add(activity);

    Jika sessionId baru terus berdatangan, map akan terus memiliki entry baru. Jika sessionId yang sama menerima banyak event, list-nya juga terus bertambah.

    Mekanisme reachability

    Jalur reference-nya:

    GC Root
    ↓
    Class object SessionAnalyticsService
    ↓
    static field sessionActivityLog
    ↓
    HashMap
    ↓
    List<UserActivity>
    ↓
    userActivity entries

    sessionActivityLog adalah static, sehingga map tersebut tetap reachable selama class yang memilikinya masih loaded.

    Akibatnya:

    GC terjadi
        ↓
    GC memeriksa sessionActivityLog
        ↓
    Entry lama masih reachable
        ↓
    Entry tidak dikumpulkan
        ↓
    Entry baru terus ditambahkan
        ↓
    Retained heap terus bertambah

    Jadi, GC tetap berjalan, tetapi baseline heap dapat terus naik karena objek lama masih reachable dari GC Root.
    
2. **Ambil bukti** — jalankan aplikasi ini (boleh disederhanakan jadi program `main()` biasa yang mensimulasikan ribuan `recordActivity()` dengan `sessionId` unik berbeda-beda tiap panggilan, tidak perlu Spring Boot penuh berjalan kalau lebih praktis untuk latihan ini), ambil heap dump dengan `jcmd` seperti Lesson 3.4, lalu buka di VisualVM/MAT. Tunjukkan (screenshot atau deskripsi tertulis rinci) dominator tree yang mengarah ke `sessionActivityLog` sebagai akar retensi.

    output:
            2668:
            2026-09-10 16:22:17
            Full thread dump Java HotSpot(TM) 64-Bit Server VM (17.0.11+7-LTS-207 mixed mode, sharing):

            Threads class SMR info:
            _java_thread_list=0x0000026528b30c20, length=12, elements={
            0x00000265057128e0, 0x00000265286d7ed0, 0x00000265286d8a90, 0x00000265288004a0,
            0x0000026528802d80, 0x00000265288052c0, 0x0000026528805c80, 0x0000026528815ff0,
            0x000002652881fa10, 0x0000026528834130, 0x000002652895d1d0, 0x0000026528988ce0
            }

            "main" #1 prio=5 os_prio=0 cpu=437.50ms elapsed=129.92s tid=0x00000265057128e0 nid=0x1a7c waiting on condition  [0x0000009bab7ff000]
            java.lang.Thread.State: TIMED_WAITING (sleeping)
                    at java.lang.Thread.sleep(java.base@17.0.11/Native Method)
                    at SessionAnalyticsDemo.main(SessionAnalyticsDemo.java:35)

            "Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=129.90s tid=0x00000265286d7ed0 nid=0x5010 waiting on condition  [0x0000009babeff000]
            java.lang.Thread.State: RUNNABLE
                    at java.lang.ref.Reference.waitForReferencePendingList(java.base@17.0.11/Native Method)
                    at java.lang.ref.Reference.processPendingReferences(java.base@17.0.11/Reference.java:253)
                    at java.lang.ref.Reference$ReferenceHandler.run(java.base@17.0.11/Reference.java:215)

            "Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=129.90s tid=0x00000265286d8a90 nid=0x49f0 in Object.wait()  [0x0000009babfff000]
            java.lang.Thread.State: WAITING (on object monitor)
                    at java.lang.Object.wait(java.base@17.0.11/Native Method)
                    - waiting on <0x0000000706178628> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:155)
                    - locked <0x0000000706178628> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:176)
                    at java.lang.ref.Finalizer$FinalizerThread.run(java.base@17.0.11/Finalizer.java:172)

            "Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x00000265288004a0 nid=0x4448 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Attach Listener" #5 daemon prio=5 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x0000026528802d80 nid=0x775c waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Service Thread" #6 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.89s tid=0x00000265288052c0 nid=0x53a4 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Monitor Deflation Thread" #7 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.89s tid=0x0000026528805c80 nid=0x2054 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "C2 CompilerThread0" #8 daemon prio=9 os_prio=2 cpu=78.12ms elapsed=129.89s tid=0x0000026528815ff0 nid=0x4034 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE
            No compile task

            "C1 CompilerThread0" #11 daemon prio=9 os_prio=2 cpu=62.50ms elapsed=129.89s tid=0x000002652881fa10 nid=0x1414 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE
            No compile task

            "Sweeper thread" #12 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x0000026528834130 nid=0xb50 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Notification Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.88s tid=0x000002652895d1d0 nid=0x7040 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Common-Cleaner" #14 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=129.88s tid=0x0000026528988ce0 nid=0x464c in Object.wait()  [0x0000009bac9fe000]
            java.lang.Thread.State: TIMED_WAITING (on object monitor)
                    at java.lang.Object.wait(java.base@17.0.11/Native Method)
                    - waiting on <0x00000007060fd680> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:155)
                    - locked <0x00000007060fd680> (a java.lang.ref.ReferenceQueue$Lock)
                    at jdk.internal.ref.CleanerImpl.run(java.base@17.0.11/CleanerImpl.java:140)
                    at java.lang.Thread.run(java.base@17.0.11/Thread.java:842)
                    at jdk.internal.misc.InnocuousThread.run(java.base@17.0.11/InnocuousThread.java:162)

            "VM Thread" os_prio=2 cpu=15.62ms elapsed=129.90s tid=0x00000265286d28b0 nid=0x10f4 runnable  

            "GC Thread#0" os_prio=2 cpu=62.50ms elapsed=129.92s tid=0x0000026505781610 nid=0x4248 runnable  

            "GC Thread#1" os_prio=2 cpu=78.12ms elapsed=129.80s tid=0x0000026528a76d30 nid=0x4090 runnable  

            "GC Thread#2" os_prio=2 cpu=78.12ms elapsed=129.80s tid=0x0000026528a0e450 nid=0x3fa4 runnable  

            "GC Thread#3" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x0000026528a0e710 nid=0x30b4 runnable  

            "GC Thread#4" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x000002656e304c00 nid=0x5960 runnable  

            "GC Thread#5" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x0000026528a4a5a0 nid=0x32c4 runnable  

            "GC Thread#6" os_prio=2 cpu=78.12ms elapsed=129.65s tid=0x000002656e40c7a0 nid=0x68bc runnable  

            "GC Thread#7" os_prio=2 cpu=78.12ms elapsed=129.65s tid=0x000002656e5acb50 nid=0x23c8 runnable  

            "GC Thread#8" os_prio=2 cpu=62.50ms elapsed=129.65s tid=0x000002656e41b620 nid=0x60d0 runnable  

            "GC Thread#9" os_prio=2 cpu=62.50ms elapsed=129.65s tid=0x000002656e41b8e0 nid=0xfa4 runnable  

            "G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=129.92s tid=0x0000026505792030 nid=0x6d04 runnable  

            "G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=129.92s tid=0x0000026505793710 nid=0x190 runnable  

            "G1 Refine#0" os_prio=2 cpu=187.50ms elapsed=129.91s tid=0x00000265057ddbd0 nid=0x53b8 runnable  

            "G1 Refine#1" os_prio=2 cpu=109.38ms elapsed=129.79s tid=0x000002656e1f6f90 nid=0x428c runnable  

            "G1 Refine#2" os_prio=2 cpu=46.88ms elapsed=129.79s tid=0x000002656e152a60 nid=0x1424 runnable  

            "G1 Refine#3" os_prio=2 cpu=31.25ms elapsed=129.79s tid=0x000002656e152d50 nid=0x5598 runnable  

            "G1 Refine#4" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x0000026528bad330 nid=0x7344 runnable  

            "G1 Refine#5" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e0e5660 nid=0x3874 runnable  

            "G1 Refine#6" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e132290 nid=0x2708 runnable  

            "G1 Refine#7" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e132580 nid=0x629c runnable  

            "G1 Refine#8" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e048a60 nid=0x884 runnable  

            "G1 Refine#9" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e049570 nid=0x65ec runnable  

            "G1 Service" os_prio=2 cpu=15.62ms elapsed=129.91s tid=0x00000265057de600 nid=0x5bc0 runnable  

            "VM Periodic Task Thread" os_prio=2 cpu=31.25ms elapsed=129.88s tid=0x000002650579d120 nid=0x6b2c waiting on condition  

            JNI global refs: 5, weak refs: 0

3. **Perbaiki** — tulis ulang `SessionAnalyticsService` supaya tidak lagi bocor, dengan minimal 2 pendekatan berbeda yang Anda pertimbangkan (misalnya: eviction manual setelah `flushDailyAnalytics()` memproses data suatu session vs TTL-based cache seperti `Caffeine`/Guava `Cache` dengan `expireAfterWrite`), pilih satu, dan jelaskan kenapa itu lebih tepat untuk kasus ini dibanding alternatif satunya.

    Pendekatan yang dipertimbangkan Manual eviction setelah flushDailyAnalytics() Setelah data berhasil dikirim ke data warehouse, hapus session dari Map.
    Cocok karena proses flush memang menjadi batas akhir lifecycle data. TTL-based cache dengan Caffeine/Guava Entry otomatis dihapus setelah waktu tertentu, misalnya expireAfterWrite(24h).
    
    Lebih cocok jika kita tidak tahu kapan data sudah tidak diperlukan, atau session bisa berhenti tanpa pernah masuk proses flush.
    Saya juga memilih manual eviction

    Alasannya bukan sekadar "setelah flush tidak dipakai lagi", tetapi karena flush adalah business event yang menentukan kapan data sudah tidak dibutuhkan.

    Dengan TTL, kita hanya menebak:

    "Data ini mungkin sudah tidak diperlukan setelah 24 jam."

    Sedangkan dengan manual eviction:

    Data dikirim
        ↓
    Berhasil diproses
        ↓
    Tidak diperlukan lagi
        ↓
    Hapus dari memory

    Jadi lebih deterministic.

    fixing : D:\learn\playground\backend-fundamentals\SessionAnalyticsService.java

4. **Buktikan dengan angka** — jalankan ulang simulasi dengan kode yang sudah diperbaiki, ambil heap dump/`jcmd GC.class_histogram` sebelum dan sesudah simulasi jalan lama, dan tunjukkan bahwa memory usage sekarang **stabil** (tidak terus naik), bukan cuma "kelihatannya lebih baik".

    before :
                        2668:
            2026-09-10 16:22:17
            Full thread dump Java HotSpot(TM) 64-Bit Server VM (17.0.11+7-LTS-207 mixed mode, sharing):

            Threads class SMR info:
            _java_thread_list=0x0000026528b30c20, length=12, elements={
            0x00000265057128e0, 0x00000265286d7ed0, 0x00000265286d8a90, 0x00000265288004a0,
            0x0000026528802d80, 0x00000265288052c0, 0x0000026528805c80, 0x0000026528815ff0,
            0x000002652881fa10, 0x0000026528834130, 0x000002652895d1d0, 0x0000026528988ce0
            }

            "main" #1 prio=5 os_prio=0 cpu=437.50ms elapsed=129.92s tid=0x00000265057128e0 nid=0x1a7c waiting on condition  [0x0000009bab7ff000]
            java.lang.Thread.State: TIMED_WAITING (sleeping)
                    at java.lang.Thread.sleep(java.base@17.0.11/Native Method)
                    at SessionAnalyticsDemo.main(SessionAnalyticsDemo.java:35)

            "Reference Handler" #2 daemon prio=10 os_prio=2 cpu=0.00ms elapsed=129.90s tid=0x00000265286d7ed0 nid=0x5010 waiting on condition  [0x0000009babeff000]
            java.lang.Thread.State: RUNNABLE
                    at java.lang.ref.Reference.waitForReferencePendingList(java.base@17.0.11/Native Method)
                    at java.lang.ref.Reference.processPendingReferences(java.base@17.0.11/Reference.java:253)
                    at java.lang.ref.Reference$ReferenceHandler.run(java.base@17.0.11/Reference.java:215)

            "Finalizer" #3 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=129.90s tid=0x00000265286d8a90 nid=0x49f0 in Object.wait()  [0x0000009babfff000]
            java.lang.Thread.State: WAITING (on object monitor)
                    at java.lang.Object.wait(java.base@17.0.11/Native Method)
                    - waiting on <0x0000000706178628> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:155)
                    - locked <0x0000000706178628> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:176)
                    at java.lang.ref.Finalizer$FinalizerThread.run(java.base@17.0.11/Finalizer.java:172)

            "Signal Dispatcher" #4 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x00000265288004a0 nid=0x4448 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Attach Listener" #5 daemon prio=5 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x0000026528802d80 nid=0x775c waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Service Thread" #6 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.89s tid=0x00000265288052c0 nid=0x53a4 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Monitor Deflation Thread" #7 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.89s tid=0x0000026528805c80 nid=0x2054 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "C2 CompilerThread0" #8 daemon prio=9 os_prio=2 cpu=78.12ms elapsed=129.89s tid=0x0000026528815ff0 nid=0x4034 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE
            No compile task

            "C1 CompilerThread0" #11 daemon prio=9 os_prio=2 cpu=62.50ms elapsed=129.89s tid=0x000002652881fa10 nid=0x1414 waiting on condition  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE
            No compile task

            "Sweeper thread" #12 daemon prio=9 os_prio=2 cpu=0.00ms elapsed=129.89s tid=0x0000026528834130 nid=0xb50 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Notification Thread" #13 daemon prio=9 os_prio=0 cpu=0.00ms elapsed=129.88s tid=0x000002652895d1d0 nid=0x7040 runnable  [0x0000000000000000]
            java.lang.Thread.State: RUNNABLE

            "Common-Cleaner" #14 daemon prio=8 os_prio=1 cpu=0.00ms elapsed=129.88s tid=0x0000026528988ce0 nid=0x464c in Object.wait()  [0x0000009bac9fe000]
            java.lang.Thread.State: TIMED_WAITING (on object monitor)
                    at java.lang.Object.wait(java.base@17.0.11/Native Method)
                    - waiting on <0x00000007060fd680> (a java.lang.ref.ReferenceQueue$Lock)
                    at java.lang.ref.ReferenceQueue.remove(java.base@17.0.11/ReferenceQueue.java:155)
                    - locked <0x00000007060fd680> (a java.lang.ref.ReferenceQueue$Lock)
                    at jdk.internal.ref.CleanerImpl.run(java.base@17.0.11/CleanerImpl.java:140)
                    at java.lang.Thread.run(java.base@17.0.11/Thread.java:842)
                    at jdk.internal.misc.InnocuousThread.run(java.base@17.0.11/InnocuousThread.java:162)

            "VM Thread" os_prio=2 cpu=15.62ms elapsed=129.90s tid=0x00000265286d28b0 nid=0x10f4 runnable  

            "GC Thread#0" os_prio=2 cpu=62.50ms elapsed=129.92s tid=0x0000026505781610 nid=0x4248 runnable  

            "GC Thread#1" os_prio=2 cpu=78.12ms elapsed=129.80s tid=0x0000026528a76d30 nid=0x4090 runnable  

            "GC Thread#2" os_prio=2 cpu=78.12ms elapsed=129.80s tid=0x0000026528a0e450 nid=0x3fa4 runnable  

            "GC Thread#3" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x0000026528a0e710 nid=0x30b4 runnable  

            "GC Thread#4" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x000002656e304c00 nid=0x5960 runnable  

            "GC Thread#5" os_prio=2 cpu=62.50ms elapsed=129.80s tid=0x0000026528a4a5a0 nid=0x32c4 runnable  

            "GC Thread#6" os_prio=2 cpu=78.12ms elapsed=129.65s tid=0x000002656e40c7a0 nid=0x68bc runnable  

            "GC Thread#7" os_prio=2 cpu=78.12ms elapsed=129.65s tid=0x000002656e5acb50 nid=0x23c8 runnable  

            "GC Thread#8" os_prio=2 cpu=62.50ms elapsed=129.65s tid=0x000002656e41b620 nid=0x60d0 runnable  

            "GC Thread#9" os_prio=2 cpu=62.50ms elapsed=129.65s tid=0x000002656e41b8e0 nid=0xfa4 runnable  

            "G1 Main Marker" os_prio=2 cpu=0.00ms elapsed=129.92s tid=0x0000026505792030 nid=0x6d04 runnable  

            "G1 Conc#0" os_prio=2 cpu=0.00ms elapsed=129.92s tid=0x0000026505793710 nid=0x190 runnable  

            "G1 Refine#0" os_prio=2 cpu=187.50ms elapsed=129.91s tid=0x00000265057ddbd0 nid=0x53b8 runnable  

            "G1 Refine#1" os_prio=2 cpu=109.38ms elapsed=129.79s tid=0x000002656e1f6f90 nid=0x428c runnable  

            "G1 Refine#2" os_prio=2 cpu=46.88ms elapsed=129.79s tid=0x000002656e152a60 nid=0x1424 runnable  

            "G1 Refine#3" os_prio=2 cpu=31.25ms elapsed=129.79s tid=0x000002656e152d50 nid=0x5598 runnable  

            "G1 Refine#4" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x0000026528bad330 nid=0x7344 runnable  

            "G1 Refine#5" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e0e5660 nid=0x3874 runnable  

            "G1 Refine#6" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e132290 nid=0x2708 runnable  

            "G1 Refine#7" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e132580 nid=0x629c runnable  

            "G1 Refine#8" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e048a60 nid=0x884 runnable  

            "G1 Refine#9" os_prio=2 cpu=15.62ms elapsed=129.79s tid=0x000002656e049570 nid=0x65ec runnable  

            "G1 Service" os_prio=2 cpu=15.62ms elapsed=129.91s tid=0x00000265057de600 nid=0x5bc0 runnable  

            "VM Periodic Task Thread" os_prio=2 cpu=31.25ms elapsed=129.88s tid=0x000002650579d120 nid=0x6b2c waiting on condition  

            JNI global refs: 5, weak refs: 0
    after :
 num     #instances         #bytes  class name (module)
-------------------------------------------------------
   1:            25        8404304  [Ljava.util.concurrent.ConcurrentHashMap$Node; (java.base@17.0.11)
   2:          2603         130384  [B (java.base@17.0.11)
   3:           676          84144  java.lang.Class (java.base@17.0.11)
   4:          1069          67808  [Ljava.lang.Object; (java.base@17.0.11)
   5:          2514          60336  java.lang.String (java.base@17.0.11)
   6:            13          41832  [C (java.base@17.0.11)
   7:          1061          33952  java.util.concurrent.ConcurrentHashMap$Node (java.base@17.0.11)
   8:          1010          32320  java.util.HashMap$Node (java.base@17.0.11)
   9:           259          24176  [Ljava.util.HashMap$Node; (java.base@17.0.11)
  10:           261          12528  java.util.HashMap (java.base@17.0.11)
  11:           362           8688  java.lang.module.ModuleDescriptor$Exports (java.base@17.0.11)
  12:           114           8664  [Ljava.lang.ref.SoftReference; (java.base@17.0.11)
  13:           141           6768  java.lang.invoke.MemberName (java.base@17.0.11)
  14:           208           6656  java.lang.invoke.LambdaForm$Name (java.base@17.0.11)
  15:           256           6144  java.lang.Long (java.base@17.0.11)
  16:           253           6072  java.util.ImmutableCollections$Set12 (java.base@17.0.11)
  17:           131           5400  [I (java.base@17.0.11)
  18:           126           5040  java.lang.invoke.MethodType (java.base@17.0.11)
  19:            63           5040  java.net.URI (java.base@17.0.11)
  20:            24           4624  [Ljava.lang.invoke.MethodHandle; (java.base@17.0.11)
  21:           133           4256  java.lang.module.ModuleDescriptor$Requires (java.base@17.0.11)
  22:           262           4192  java.lang.Integer (java.base@17.0.11)
  23:           129           4128  java.lang.invoke.MethodType$ConcurrentWeakInternSet$WeakEntry (java.base@17.0.11)
  24:           130           4064  [Ljava.lang.Class; (java.base@17.0.11)
  25:            11           4048  java.lang.Thread (java.base@17.0.11)
  26:            63           4032  java.lang.module.ModuleDescriptor (java.base@17.0.11)
  27:            69           3864  java.lang.Module (java.base@17.0.11)
  28:            63           3528  jdk.internal.module.ModuleReferenceImpl (java.base@17.0.11)
  29:           140           3360  java.util.ImmutableCollections$SetN (java.base@17.0.11)
  30:           198           3168  java.util.HashSet (java.base@17.0.11)
  31:           117           2808  java.lang.invoke.ResolvedMethodName (java.base@17.0.11)
  32:            42           2688  java.util.concurrent.ConcurrentHashMap (java.base@17.0.11)
  33:            55           2560  [Ljava.lang.invoke.LambdaForm$Name; (java.base@17.0.11)
  34:            50           2400  java.lang.invoke.LambdaForm (java.base@17.0.11)
  35:            68           2176  java.lang.invoke.LambdaForm$Kind (java.base@17.0.11)
  36:            62           1984  jdk.internal.loader.BuiltinClassLoader$LoadedModule (java.base@17.0.11)
  37:            82           1968  java.util.ImmutableCollections$List12 (java.base@17.0.11)
  38:            49           1960  java.lang.ref.SoftReference (java.base@17.0.11)
  39:            57           1824  java.lang.invoke.MethodTypeForm (java.base@17.0.11)
  40:            75           1800  java.lang.invoke.LambdaForm$NamedFunction (java.base@17.0.11)
  41:            64           1536  jdk.internal.module.ServicesCatalog$ServiceProvider (java.base@17.0.11)
  42:            63           1512  java.lang.module.ResolvedModule (java.base@17.0.11)
  43:            63           1512  jdk.internal.module.SystemModuleFinders$2 (java.base@17.0.11)
  44:            62           1488  java.lang.module.ModuleDescriptor$Provides (java.base@17.0.11)
  45:            82           1312  java.lang.Object (java.base@17.0.11)
  46:             5           1104  [J (java.base@17.0.11)
  47:             1           1040  [Ljava.lang.Integer; (java.base@17.0.11)
  48:             1           1040  [Ljava.lang.Long; (java.base@17.0.11)
  49:            31            992  java.lang.invoke.VarHandle$AccessMode (java.base@17.0.11)
  50:            61            976  jdk.internal.module.SystemModuleFinders$3 (java.base@17.0.11)
  51:            23            920  java.lang.invoke.DirectMethodHandle$Accessor (java.base@17.0.11)
  52:            21            840  java.io.ObjectStreamField (java.base@17.0.11)
  53:            26            832  java.lang.invoke.DirectMethodHandle (java.base@17.0.11)
  54:            20            776  [Ljava.lang.String; (java.base@17.0.11)
  55:            31            744  java.util.concurrent.CopyOnWriteArrayList (java.base@17.0.11)
  56:            30            720  java.util.ImmutableCollections$ListN (java.base@17.0.11)
  57:            20            640  java.util.Locale (java.base@17.0.11)
  58:            20            640  sun.util.locale.BaseLocale (java.base@17.0.11)
  59:             3            624  [Ljava.lang.invoke.LambdaForm; (java.base@17.0.11)
  60:            13            624  java.lang.invoke.LambdaFormEditor$Transform (java.base@17.0.11)
  61:            14            560  java.security.AccessControlContext (java.base@17.0.11)
  62:            10            560  sun.invoke.util.Wrapper (java.base@17.0.11)
  63:             7            496  [Lsun.invoke.util.Wrapper; (java.base@17.0.11)
  64:            12            480  java.lang.OutOfMemoryError (java.base@17.0.11)
  65:             8            384  java.lang.invoke.BoundMethodHandle$SpeciesData (java.base@17.0.11)
  66:             1            376  java.lang.ref.Finalizer$FinalizerThread (java.base@17.0.11)
  67:             1            376  jdk.internal.misc.InnocuousThread (java.base@17.0.11)
  68:             1            368  java.lang.ref.Reference$ReferenceHandler (java.base@17.0.11)
  69:             5            360  java.lang.reflect.Constructor (java.base@17.0.11)
  70:             5            336  [S (java.base@17.0.11)
  71:             8            320  [Ljava.lang.invoke.BoundMethodHandle$SpeciesData; (java.base@17.0.11)
  72:             1            288  [Ljava.lang.invoke.LambdaForm$Kind; (java.base@17.0.11)
  73:             9            288  jdk.internal.org.objectweb.asm.Type (java.base@17.0.11)
  74:            11            264  java.lang.invoke.Invokers (java.base@17.0.11)
  75:            11            264  java.lang.invoke.MethodHandleImpl$Intrinsic (java.base@17.0.11)
  76:             4            256  java.lang.Class$ReflectionData (java.base@17.0.11)
  77:             5            240  [Ljava.lang.invoke.LambdaForm$NamedFunction; (java.base@17.0.11)
  78:             6            240  java.lang.invoke.BoundMethodHandle$Species_LL (java.base@17.0.11)
  79:            10            240  java.nio.charset.CoderResult (java.base@17.0.11)
  80:             8            224  [Ljava.io.ObjectStreamField; (java.base@17.0.11)
  81:             7            224  java.lang.ref.ReferenceQueue (java.base@17.0.11)
  82:             5            200  java.lang.invoke.BoundMethodHandle$Species_LJ (java.base@17.0.11)
  83:             5            200  java.security.ProtectionDomain (java.base@17.0.11)
  84:             5            200  java.util.WeakHashMap$Entry (java.base@17.0.11)
  85:             4            192  java.lang.invoke.BoundMethodHandle$Species_LLLL (java.base@17.0.11)
  86:             6            192  java.lang.invoke.LambdaForm$BasicType (java.base@17.0.11)
  87:             8            192  java.util.ArrayList (java.base@17.0.11)
  88:             5            160  java.lang.invoke.VarHandle$AccessType (java.base@17.0.11)
  89:             4            160  java.security.CodeSource (java.base@17.0.11)
  90:             5            160  java.util.Hashtable$Entry (java.base@17.0.11)
  91:             1            144  [Ljava.lang.invoke.VarHandle$AccessMode; (java.base@17.0.11)
  92:             3            144  java.lang.ThreadGroup (java.base@17.0.11)
  93:             9            144  java.lang.ref.ReferenceQueue$Lock (java.base@17.0.11)
  94:             3            144  java.util.Hashtable (java.base@17.0.11)
  95:             3            144  jdk.internal.ref.CleanerImpl$PhantomCleanableRef (java.base@17.0.11)
  96:             3            128  [Ljava.util.Hashtable$Entry; (java.base@17.0.11)
  97:             4            128  java.lang.invoke.BoundMethodHandle$Species_L (java.base@17.0.11)
  98:             4            128  java.lang.invoke.MethodHandles$Lookup (java.base@17.0.11)
  99:             4            128  java.util.ImmutableCollections$MapN (java.base@17.0.11)
 100:             4            128  jdk.internal.loader.NativeLibraries (java.base@17.0.11)
 101:             2            128  sun.nio.cs.SingleByte$Encoder (java.base@17.0.11)
 102:             3            120  [Ljava.lang.invoke.LambdaForm$BasicType; (java.base@17.0.11)
 103:             3            120  java.io.FileDescriptor (java.base@17.0.11)
 104:             3            120  java.lang.invoke.MethodHandleImpl$IntrinsicMethodHandle (java.base@17.0.11)
 105:             5            120  java.lang.module.ModuleDescriptor$Opens (java.base@17.0.11)
 106:             3            112  [Ljava.lang.Thread; (java.base@17.0.11)
 107:             2            112  [Ljava.lang.invoke.MethodType; (java.base@17.0.11)
 108:             2            112  java.nio.HeapByteBuffer (java.base@17.0.11)
 109:             1            104  jdk.internal.loader.ClassLoaders$AppClassLoader (java.base@17.0.11)
 110:             1            104  jdk.internal.loader.ClassLoaders$BootClassLoader (java.base@17.0.11)
 111:             1            104  jdk.internal.loader.ClassLoaders$PlatformClassLoader (java.base@17.0.11)
 112:             1             96  [Lsun.util.locale.BaseLocale; (java.base@17.0.11)
 113:             3             96  java.lang.invoke.SimpleMethodHandle (java.base@17.0.11)
 114:             4             96  java.lang.module.ModuleDescriptor$Modifier (java.base@17.0.11)
 115:             4             96  java.lang.module.ModuleDescriptor$Requires$Modifier (java.base@17.0.11)
 116:             3             96  jdk.internal.reflect.NativeConstructorAccessorImpl (java.base@17.0.11)
 117:             2             96  sun.nio.cs.StreamEncoder (java.base@17.0.11)
 118:             1             88  java.lang.reflect.Method (java.base@17.0.11)
 119:             2             80  [Ljava.lang.OutOfMemoryError; (java.base@17.0.11)
 120:             5             80  [Ljava.security.Principal; (java.base@17.0.11)
 121:             1             80  [Ljava.util.WeakHashMap$Entry; (java.base@17.0.11)
 122:             2             80  java.io.BufferedWriter (java.base@17.0.11)
 123:             2             80  java.io.PrintStream (java.base@17.0.11)
 124:             2             80  java.lang.ModuleLayer (java.base@17.0.11)
 125:             2             80  java.lang.module.Configuration (java.base@17.0.11)
 126:             5             80  java.security.ProtectionDomain$Key (java.base@17.0.11)
 127:             2             80  sun.util.locale.LocaleObjectCache$CacheEntry (java.base@17.0.11)
 128:             3             72  [Ljava.lang.reflect.Constructor; (java.base@17.0.11)
 129:             1             64  [Ljava.lang.invoke.MethodHandleImpl$Intrinsic; (java.base@17.0.11)
 130:             2             64  [Ljava.nio.charset.CoderResult; (java.base@17.0.11)
 131:             2             64  java.io.BufferedOutputStream (java.base@17.0.11)
 132:             2             64  java.io.FileOutputStream (java.base@17.0.11)
 133:             2             64  java.lang.invoke.MethodHandleNatives$CallSiteContext (java.base@17.0.11)
 134:             2             64  java.lang.ref.ReferenceQueue$Null (java.base@17.0.11)
 135:             2             64  java.lang.ref.WeakReference (java.base@17.0.11)
 136:             1             64  java.net.URL (java.base@17.0.11)
 137:             2             64  java.util.HexFormat (java.base@17.0.11)
 138:             1             56  [[I (java.base@17.0.11)
 139:             1             56  java.lang.invoke.BoundMethodHandle$Specializer (java.base@17.0.11)
 140:             1             56  java.lang.invoke.BoundMethodHandle$Species_LLLLLL (java.base@17.0.11)
 141:             1             56  java.util.Properties (java.base@17.0.11)
 142:             1             56  jdk.internal.org.objectweb.asm.Label (java.base@17.0.11)
 143:             2             48  [Ljava.security.ProtectionDomain; (java.base@17.0.11)
 144:             2             48  java.io.File$PathStatus (java.base@17.0.11)
 145:             2             48  java.io.OutputStreamWriter (java.base@17.0.11)
 146:             1             48  java.lang.NullPointerException (java.base@17.0.11)
 147:             1             48  java.lang.invoke.BoundMethodHandle$Specializer$Factory (java.base@17.0.11)
 148:             2             48  java.lang.invoke.MethodHandles$Lookup$ClassOption (java.base@17.0.11)
 149:             3             48  java.nio.charset.CodingErrorAction (java.base@17.0.11)
 150:             2             48  java.util.ArrayDeque (java.base@17.0.11)
 151:             1             48  java.util.WeakHashMap (java.base@17.0.11)
 152:             3             48  java.util.concurrent.atomic.AtomicInteger (java.base@17.0.11)
 153:             2             48  jdk.internal.misc.Signal (java.base@17.0.11)
 154:             2             48  jdk.internal.misc.Signal$NativeHandler (java.base@17.0.11)
 155:             3             48  jdk.internal.module.ServicesCatalog (java.base@17.0.11)
 156:             3             48  jdk.internal.reflect.DelegatingConstructorAccessorImpl (java.base@17.0.11)
 157:             1             40  [Ljava.lang.invoke.VarHandle$AccessType; (java.base@17.0.11)
 158:             1             40  [[Ljava.lang.invoke.LambdaForm$Name; (java.base@17.0.11)
 159:             1             40  java.io.BufferedInputStream (java.base@17.0.11)
 160:             1             40  java.lang.ArithmeticException (java.base@17.0.11)
 161:             1             40  java.lang.VirtualMachineError (java.base@17.0.11)
 162:             1             40  java.lang.invoke.MethodHandleImpl$AsVarargsCollector (java.base@17.0.11)
 163:             1             40  jdk.internal.loader.URLClassPath (java.base@17.0.11)
 164:             1             40  jdk.internal.ref.CleanerImpl$CleanerCleanable (java.base@17.0.11)
 165:             1             40  sun.nio.cs.StandardCharsets$Aliases (java.base@17.0.11)
 166:             1             40  sun.nio.cs.StandardCharsets$Cache (java.base@17.0.11)
 167:             1             40  sun.nio.cs.StandardCharsets$Classes (java.base@17.0.11)
 168:             2             32  [Ljava.lang.StackTraceElement; (java.base@17.0.11)
 169:             1             32  [Ljava.lang.ThreadGroup; (java.base@17.0.11)
 170:             1             32  [Ljava.lang.invoke.LambdaFormEditor$Transform; (java.base@17.0.11)
 171:             1             32  [Ljava.lang.module.ModuleDescriptor$Modifier; (java.base@17.0.11)
 172:             1             32  [Ljava.lang.module.ModuleDescriptor$Requires$Modifier; (java.base@17.0.11)
 173:             1             32  [Ljdk.internal.module.ServicesCatalog; (java.base@17.0.11)
 174:             1             32  java.io.File (java.base@17.0.11)
 175:             1             32  java.io.FileInputStream (java.base@17.0.11)
 176:             1             32  java.io.WinNTFileSystem (java.base@17.0.11)
 177:             2             32  java.lang.Boolean (java.base@17.0.11)
 178:             1             32  java.lang.Module$ArchivedData (java.base@17.0.11)
 179:             2             32  java.lang.ThreadLocal (java.base@17.0.11)
 180:             1             32  java.lang.module.ModuleDescriptor$Version (java.base@17.0.11)
 181:             2             32  java.nio.ByteOrder (java.base@17.0.11)
 182:             1             32  java.util.Collections$UnmodifiableMap (java.base@17.0.11)
 183:             1             32  jdk.internal.loader.ArchivedClassLoaders (java.base@17.0.11)
 184:             2             32  jdk.internal.loader.ClassLoaderValue (java.base@17.0.11)
 185:             1             32  jdk.internal.module.ArchivedModuleGraph (java.base@17.0.11)
 186:             1             32  jdk.internal.org.objectweb.asm.ConstantDynamic (java.base@17.0.11)
 187:             1             32  jdk.internal.org.objectweb.asm.Handle (java.base@17.0.11)
 188:             1             32  sun.util.locale.BaseLocale$Key (java.base@17.0.11)
 189:             1             24  [Ljava.io.File$PathStatus; (java.base@17.0.11)
 190:             1             24  [Ljava.lang.invoke.MethodHandles$Lookup$ClassOption; (java.base@17.0.11)
 191:             1             24  [Ljava.lang.reflect.Method; (java.base@17.0.11)
 192:             1             24  [Ljava.net.URL; (java.base@17.0.11)
 193:             1             24  [Ljava.util.jar.Manifest; (java.base@17.0.11)
 194:             1             24  java.lang.Double (java.base@17.0.11)
 195:             1             24  java.lang.NamedPackage (java.base@17.0.11)
 196:             1             24  java.lang.RuntimePermission (java.base@17.0.11)
 197:             1             24  java.lang.StringBuilder (java.base@17.0.11)
 198:             1             24  java.lang.invoke.MethodType$ConcurrentWeakInternSet (java.base@17.0.11)
 199:             1             24  java.lang.reflect.ReflectPermission (java.base@17.0.11)
 200:             1             24  java.security.BasicPermissionCollection (java.base@17.0.11)
 201:             1             24  java.security.Permissions (java.base@17.0.11)
 202:             1             24  java.util.Collections$EmptyMap (java.base@17.0.11)
 203:             1             24  java.util.Collections$SetFromMap (java.base@17.0.11)
 204:             1             24  java.util.Locale$Cache (java.base@17.0.11)
 205:             1             24  jdk.internal.loader.URLClassPath$FileLoader (java.base@17.0.11)
 206:             1             24  jdk.internal.module.ModuleHashes (java.base@17.0.11)
 207:             1             24  jdk.internal.module.SystemModuleFinders$SystemModuleFinder (java.base@17.0.11)
 208:             1             24  jdk.internal.ref.CleanerImpl (java.base@17.0.11)
 209:             1             24  sun.nio.cs.IBM437 (java.base@17.0.11)
 210:             1             24  sun.nio.cs.ISO_8859_1 (java.base@17.0.11)
 211:             1             24  sun.nio.cs.MS1252 (java.base@17.0.11)
 212:             1             24  sun.nio.cs.StandardCharsets (java.base@17.0.11)
 213:             1             24  sun.nio.cs.US_ASCII (java.base@17.0.11)
 214:             1             24  sun.nio.cs.UTF_16 (java.base@17.0.11)
 215:             1             24  sun.nio.cs.UTF_16BE (java.base@17.0.11)
 216:             1             24  sun.nio.cs.UTF_16LE (java.base@17.0.11)
 217:             1             24  sun.nio.cs.UTF_8 (java.base@17.0.11)
 218:             1             24  sun.security.util.LazyCodeSourcePermissionCollection (java.base@17.0.11)
 219:             1             24  sun.util.locale.BaseLocale$Cache (java.base@17.0.11)
 220:             1             16  [D (java.base@17.0.11)
 221:             1             16  [F (java.base@17.0.11)
 222:             1             16  [Ljava.lang.Throwable; (java.base@17.0.11)
 223:             1             16  [Ljava.security.cert.Certificate; (java.base@17.0.11)
 224:             1             16  [Z (java.base@17.0.11)
 225:             1             16  SessionAnalyticsDemoV2$$Lambda$1/0x0000016c56000c08
 226:             1             16  java.io.FileDescriptor$1 (java.base@17.0.11)
 227:             1             16  java.lang.CharacterDataLatin1 (java.base@17.0.11)
 228:             1             16  java.lang.Float (java.base@17.0.11)
 229:             1             16  java.lang.Runtime (java.base@17.0.11)
 230:             1             16  java.lang.String$CaseInsensitiveComparator (java.base@17.0.11)
 231:             1             16  java.lang.System$2 (java.base@17.0.11)
 232:             1             16  java.lang.Terminator$1 (java.base@17.0.11)
 233:             1             16  java.lang.invoke.ClassSpecializer$1 (java.base@17.0.11)
 234:             1             16  java.lang.invoke.MemberName$Factory (java.base@17.0.11)
 235:             1             16  java.lang.invoke.MethodHandleImpl$1 (java.base@17.0.11)
 236:             1             16  java.lang.invoke.StringConcatFactory$1 (java.base@17.0.11)
 237:             1             16  java.lang.invoke.StringConcatFactory$2 (java.base@17.0.11)
 238:             1             16  java.lang.invoke.StringConcatFactory$3 (java.base@17.0.11)
 239:             1             16  java.lang.module.ModuleDescriptor$1 (java.base@17.0.11)
 240:             1             16  java.lang.ref.Cleaner (java.base@17.0.11)
 241:             1             16  java.lang.ref.Cleaner$1 (java.base@17.0.11)
 242:             1             16  java.lang.ref.Reference$1 (java.base@17.0.11)
 243:             1             16  java.lang.reflect.ReflectAccess (java.base@17.0.11)
 244:             1             16  java.net.URI$1 (java.base@17.0.11)
 245:             1             16  java.net.URL$3 (java.base@17.0.11)
 246:             1             16  java.net.URL$DefaultFactory (java.base@17.0.11)
 247:             1             16  java.nio.Buffer$1 (java.base@17.0.11)
 248:             1             16  java.security.ProtectionDomain$JavaSecurityAccessImpl (java.base@17.0.11)
 249:             1             16  java.security.SecureClassLoader$CodeSourceKey (java.base@17.0.11)
 250:             1             16  java.util.Collections$EmptyList (java.base@17.0.11)
 251:             1             16  java.util.Collections$EmptySet (java.base@17.0.11)
 252:             1             16  java.util.HashMap$EntrySet (java.base@17.0.11)
 253:             1             16  java.util.Optional (java.base@17.0.11)
 254:             1             16  java.util.WeakHashMap$KeySet (java.base@17.0.11)
 255:             1             16  java.util.concurrent.ConcurrentHashMap$EntrySetView (java.base@17.0.11)
 256:             1             16  jdk.internal.misc.ScopedMemoryAccess (java.base@17.0.11)
 257:             1             16  jdk.internal.misc.Unsafe (java.base@17.0.11)
 258:             1             16  jdk.internal.module.ArchivedBootLayer (java.base@17.0.11)
 259:             1             16  jdk.internal.module.ModuleLoaderMap$Mapper (java.base@17.0.11)
 260:             1             16  jdk.internal.module.ModulePatcher (java.base@17.0.11)
 261:             1             16  jdk.internal.module.ModuleTarget (java.base@17.0.11)
 262:             1             16  jdk.internal.reflect.ReflectionFactory (java.base@17.0.11)
 263:             1             16  sun.net.www.protocol.file.Handler (java.base@17.0.11)
 264:             1             16  sun.net.www.protocol.jar.Handler (java.base@17.0.11)
Total         14126        9052536

kesimpulan :
            Berdasarkan GC.class_histogram, setelah data di-flush dan GC dijalankan, UserActivity tidak lagi muncul sebagai live object dalam histogram. Total live object hanya sekitar 14.126 dengan retained shallow bytes sekitar 9 MB. Ini menunjukkan bahwa object activity yang sebelumnya ditampung oleh sessionActivityLog sudah tidak lagi reachable setelah entry dihapus.

            ConcurrentHashMap masih memiliki internal table berukuran sekitar 8.4 MB, tetapi hal tersebut bukan bukti memory leak karena table capacity dapat tetap besar walaupun entry sudah dihapus. Untuk membuktikan memory stabil secara lebih kuat, simulasi perlu dijalankan dalam beberapa batch dan histogram/heap usage dibandingkan setelah setiap batch + GC. Jika jumlah live object dan heap setelah GC kembali ke kisaran yang sama, maka tidak terjadi pertumbuhan memory yang tidak terbatas.


### Production Discussion (tuliskan sebagai bagian penutup jawaban Anda)

Kenapa `ConcurrentHashMap` di kode ini **tidak menyelesaikan** masalah leak sama sekali walau namanya kedengaran "aman" — jelaskan kaitannya dengan pemahaman Module 2 (thread-safety) vs Module 3 (lifecycle/reachability): `ConcurrentHashMap` menjamin akses **konkuren yang aman**, tapi tidak ada hubungannya dengan **kapan sebuah entry dianggap tidak dibutuhkan lagi**. Ini poin penting yang membedakan "kode ini aman dari race condition" (benar) dari "kode ini aman dari memory leak" (salah, dua masalah yang sama sekali berbeda dan sering tertukar oleh yang belum memisahkan keduanya secara sadar).
    
    ConcurrentHashMap tidak menyelesaikan memory leak karena thread-safety dan lifecycle adalah dua masalah yang berbeda.

    Thread-safety: ConcurrentHashMap memastikan banyak thread bisa membaca/menulis map secara aman tanpa race condition tertentu.
    Lifecycle/Reachability: JVM melihat apakah object masih reachable dari GC Root. Selama sessionActivityLog masih menyimpan entry, UserActivity tetap reachable dan tidak bisa di-GC.

    Jadi kalau map terus diisi:

    sessionActivityLog.put(sessionId, activities);

    tanpa pernah menghapus entry, maka:

    ConcurrentHashMap
        ↓
    session entry
        ↓
    List<UserActivity>
        ↓
    UserActivity

    Semua object tersebut tetap reachable, sehingga memory terus bertambah.

    Jadi ConcurrentHashMap yang tidak punya eviction bisa tetap memory leak, sama seperti HashMap. Bedanya hanya ConcurrentHashMap lebih aman untuk akses konkuren—bukan otomatis aman dari memory leak.