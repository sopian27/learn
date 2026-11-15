# CPU Profiling Demo (Lesson 14.2)

Run: `java CpuProfilingDemo.java`

Yang diliat: sampler mini nunjukin frame teratas. Sebelum fix, waktu habis di internal `Pattern`
(compile ulang), bukan di logic validasi. Sesudah `static final Pattern`, total waktu turun jauh.
Versi nyata: `java -XX:StartFlightRecording=duration=30s,filename=rec.jfr ...` lalu `jfr print --events jdk.ExecutionSample rec.jfr`
Demo, bukan profiler produksi (getStackTrace kena safepoint bias).
