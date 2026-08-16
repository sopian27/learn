# Course Roadmap

## Course Information

* Course Name: Statistika & Probabilitas — dari 0 sampai Expert
* Category: Mathematics Foundations (statistika & teori probabilitas terapan) — fondasi matematika bersama untuk `courses/ml-fundamentals` dan `courses/data-analysis-visualization`, bukan pengganti keduanya
* Difficulty: Level 0 (aljabar dasar saja, belum pernah belajar probabilitas/statistik formal) → Level 3 (paham derivasi & pembuktian intuitif di balik setiap rumus/uji statistik yang dipakai ML dan data analytics, mampu memilih uji yang tepat dan menjustifikasi asumsinya)
* Estimated Duration: 12 modul, ~3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Aljabar dasar (manipulasi persamaan, notasi sigma/summation, eksponen-logaritma). **Tidak** perlu kalkulus formal sebelumnya — turunan (derivative) dipakai secukupnya di Modul 7 & 10 (MLE, least squares) dan dijelaskan intuitif tepat saat dipakai, dengan pendekatan yang sama seperti `courses/ml-fundamentals` Module 3. **Tidak** perlu pengalaman statistik/probabilitas sebelumnya — course ini mulai dari nol.

---

# Catatan Scope (baca dulu sebelum approve)

Course ini diposisikan sebagai **lapisan fondasi matematika**, bukan course applied/tooling ketiga yang bersaing dengan dua course yang sudah ada. Batasannya eksplisit:

1. **Kenapa course ini perlu, padahal `ml-fundamentals` dan `data-analysis-visualization` sudah masing-masing punya modul statistik.** Kedua course itu sengaja **mengasumsikan** intuisi probabilitas/statistik sudah ada dan langsung lompat ke *penerapan*: `ml-fundamentals` Module 4 mengajarkan probabilitas "applied, tanpa pembuktian formal" untuk evaluasi model, `data-analysis-visualization` Module 4 mengajarkan statistik untuk *keputusan bisnis* (korelasi vs kausalitas, A/B test) tanpa menurunkan rumus dari nol. Keduanya secara sadar melewati pertanyaan "kenapa rumus variance dibagi n-1, bukan n?", "dari mana datangnya Central Limit Theorem?", "kenapa p-value < 0.05 bukan berarti 95% yakin hipotesis benar?" — course ini yang menjawab pertanyaan-pertanyaan itu.
2. **Perbedaan filosofi eksplisit: math-fundamentals-first vs applied-first.**
   * Course ini (Statistika & Probabilitas): **proof/derivasi/intuisi dulu, baru aplikasi**. Tiap rumus diturunkan dari definisi dasarnya (mis. Bayes' theorem diturunkan dari definisi conditional probability, least squares diturunkan dari minimisasi sum of squared residual pakai kalkulus, formula variance sampel dijelaskan kenapa butuh koreksi Bessel `n-1`). Tujuannya: begitu lulus, rumus di `scipy.stats`/`sklearn` tidak lagi terasa seperti "kotak hitam" yang harus dihafal cara pakainya.
   * `ml-fundamentals` Module 4 & `data-analysis-visualization` Module 4: **applied-first, tooling-first**. Rumus dipakai langsung dari library, fokus ke *kapan* dan *kenapa secara bisnis/model* suatu uji dipakai, bukan *dari mana* rumusnya berasal.
3. **Tidak ada duplikasi konten, tapi ada overlap topik yang disengaja (level berbeda).** Topik seperti "hypothesis testing" atau "korelasi" muncul di tiga course ini, tapi kedalamannya berbeda tingkat: course ini membuktikan kenapa t-distribution punya *fatter tails* dibanding normal (relevan untuk sample kecil), dua course lain langsung memakai `scipy.stats.ttest_ind()` dan fokus ke interpretasi hasil untuk konteks masing-masing (model ML vs keputusan bisnis).
4. **Urutan yang disarankan (tidak wajib secara teknis, tapi disarankan kuat)**: Course ini idealnya diselesaikan **sebelum atau paralel dengan** `ml-fundamentals` Module 4 dan `data-analysis-visualization` Module 4 — supaya modul-modul itu terasa seperti *penerapan* dari apa yang sudah dipahami di sini, bukan topik baru yang harus dihafal dari nol. Tidak ada blocking teknis karena kedua course lain sudah dirancang self-contained (applied, tanpa syarat course ini).
5. **Tidak dicakup di course ini** (ada di tempat lain, sengaja tidak diulang): Power BI/DAX/dashboard (`data-analysis-visualization` Module 6-7), business storytelling & komunikasi insight (`data-analysis-visualization` Module 8), algoritma ML (regresi/klasifikasi/neural network sebagai *model* — itu ada di `ml-fundamentals` Module 5-9), SQL analytics (`data-analysis-visualization` Module 2), matematika ML lain (aljabar linear, kalkulus optimisasi — `ml-fundamentals` Module 2-3, disinggung minimal di sini hanya saat dibutuhkan langsung untuk derivasi statistik).
6. **Bahasa exercise: Python** (bukan Java), meski stack utama Anda Java/Spring Boot. Alasannya: (a) course ini adalah fondasi matematika untuk dua course "hilir" (`ml-fundamentals`, `data-analysis-visualization`) yang **sudah** memakai Python/NumPy/Pandas — memakai bahasa yang sama menghindari context-switch tooling tiap pindah course dan memperkuat "otot" Python yang memang dibutuhkan untuk kerja ML-adjacent; (b) ekosistem verifikasi numerik (NumPy untuk simulasi Monte Carlo, SciPy `scipy.stats` untuk cross-check hasil derivasi manual, Matplotlib untuk visualisasi distribusi) jauh lebih matang dan ringkas di Python dibanding Java untuk kebutuhan eksploratif seperti ini. **Production Discussion** di tiap modul relevan tetap menyinggung bagaimana hasil statistik ini dikonsumsi dari backend Java/Spring Boot (mis. men-serve hasil A/B test atau confidence interval lewat REST endpoint), supaya konteks Java tidak hilang sepenuhnya — pola yang sama seperti `ml-fundamentals` Module 10.

Referensi riset yang dipakai menyusun roadmap ini: struktur & urutan topik [MIT OCW 18.05 — Introduction to Probability and Statistics](https://ocw.mit.edu/courses/18-05-introduction-to-probability-and-statistics-spring-2022/) (urutan counting → conditional probability/Bayes → discrete RV → continuous RV → limit theorems → estimasi/MLE → Bayesian inference → hypothesis testing → confidence interval → regresi, dipakai sebagai tulang punggung urutan modul), [MIT OCW 6.041 — Probabilistic Systems Analysis and Applied Probability](https://ocw.mit.edu/courses/6-041-probabilistic-systems-analysis-and-applied-probability-fall-2010/) (kedalaman treatment aksioma probabilitas, joint distribution, covariance), struktur bab *OpenIntro Statistics* (Diez, Çetinkaya-Rundel, Barr — data & sampling → probability → distributions → inference → regression, termasuk bab ANOVA sebagai perluasan uji banyak grup) dipakai untuk urutan sisi statistik-inferensial dan memastikan ANOVA tidak jadi topik yang berdiri sendiri tanpa konteks. Struktur *Think Stats* (Allen B. Downey) mengonfirmasi pola "hitung manual dulu, verifikasi dengan library" yang dipakai di tiap Mini Project course ini.

---

# Learning Objectives

* [ ] Menguasai statistika deskriptif secara mendalam: central tendency, dispersion, dan bentuk distribusi — termasuk **kenapa** rumus variance sampel dibagi `n-1` (koreksi Bessel), bukan cuma cara menghitungnya
* [ ] Memahami aksioma probabilitas (Kolmogorov), teori himpunan untuk probabilitas, counting (permutasi/kombinasi), dan bisa menurunkan Bayes' theorem dari definisi conditional probability
* [ ] Menguasai random variable (diskrit & kontinu): PMF/PDF/CDF, expectation, variance — dengan derivasi formal, bukan hafalan rumus
* [ ] Paham keluarga distribusi probabilitas utama (Bernoulli, Binomial, Poisson, Uniform, Exponential, Normal, Chi-square, Student's t, F) dan **dari mana asalnya** masing-masing (mis. Poisson sebagai limit Binomial, Chi-square sebagai jumlah kuadrat normal standar)
* [ ] Memahami joint/marginal/conditional distribution, covariance, dan korelasi — termasuk beda formal antara *independence* dan *uncorrelated*
* [ ] Menguasai Law of Large Numbers dan Central Limit Theorem — bukan sekadar hafal statement-nya, tapi bisa mensimulasikan dan menjelaskan kenapa keduanya jadi fondasi semua inferensi statistik
* [ ] Memahami metode sampling, teori estimasi (unbiasedness, consistency), dan bisa menurunkan Maximum Likelihood Estimator untuk distribusi sederhana
* [ ] Mampu menghitung dan **menginterpretasikan dengan benar** confidence interval (termasuk miskonsepsi umum yang salah)
* [ ] Menguasai logika hypothesis testing (Type I/II error, p-value, power) dan mampu memilih & menjalankan uji yang tepat (z-test, t-test, chi-square)
* [ ] Bisa menurunkan simple linear regression dari nol lewat least squares (kalkulus minimisasi), memahami asumsi regresi, dan menghitung R-squared
* [ ] Memahami ANOVA: kenapa tidak cukup pakai banyak t-test, cara kerja dekomposisi variance, dan interpretasi F-statistic
* [ ] Bisa menjelaskan secara eksplisit bagaimana tiap konsep di course ini menjadi fondasi Machine Learning (MLE → loss function, CLT → keandalan metric model, Bayes → Naive Bayes/Bayesian ML, regresi → linear/logistic regression)
* [ ] Menyelesaikan proyek portofolio: analisis statistik end-to-end pada dataset nyata dengan derivasi manual di titik-titik kunci (bukan hanya memanggil `scipy.stats` sebagai black box)

---

# Course Modules

## Module 1 — Statistika Deskriptif & Eksplorasi Data

Description: Titik awal setiap analisis data — meringkas dan memahami bentuk data sebelum masuk ke teori probabilitas. Modul ini membangun kebiasaan "lihat data dulu" sekaligus menurunkan rumus dasar yang akan dipakai berulang di seluruh course.

Lessons:

* [ ] 1.1 Populasi vs sampel, jenis data (nominal, ordinal, interval, ratio) — kenapa jenis data menentukan statistik apa yang valid dipakai
* [ ] 1.2 Central tendency: mean, median, mode — sifat robust vs sensitif terhadap outlier, kapan masing-masing menyesatkan
* [ ] 1.3 Dispersion: range, variance, standard deviation, IQR — derivasi rumus variance dari definisi "rata-rata jarak kuadrat ke mean", dan kenapa sampel butuh koreksi Bessel (`n-1`)
* [ ] 1.4 Bentuk distribusi: skewness, kurtosis, boxplot, histogram — membaca bentuk data sebelum memilih uji statistik nanti

Mini Project: Implementasi mean/variance/std dev/skewness dari nol dengan Python (tanpa `numpy.mean`/`numpy.std`), verifikasi hasil identik dengan NumPy, lalu jalankan pada dataset nyata dan tulis ringkasan eksploratif.

Status: Not Started

---

## Module 2 — Fondasi Probabilitas: Aksioma, Counting, Conditional Probability, Bayes

Description: Bahasa formal probabilitas — dari sini semua konsep "peluang" di ML dan statistik punya dasar matematis yang sama, bukan intuisi longgar.

Lessons:

* [ ] 2.1 Sample space, event, teori himpunan untuk probabilitas (union, intersection, complement)
* [ ] 2.2 Aksioma probabilitas Kolmogorov, aturan inclusion-exclusion — pembuktian singkat kenapa aturan ini konsisten
* [ ] 2.3 Counting: permutasi & kombinasi — derivasi rumus `n!/(n-k)!` dan `n!/(k!(n-k)!)`, dipakai untuk menghitung probabilitas ruang sampel diskrit
* [ ] 2.4 Conditional probability (`P(A|B) = P(A∩B)/P(B)`) dan independence — definisi formal, bukan cuma "kejadian tidak saling memengaruhi"
* [ ] 2.5 Bayes' theorem — derivasi lengkap dari conditional probability, base rate fallacy (kenapa tes medis akurat 99% bisa tetap sering salah pada penyakit langka)

Mini Project: Selesaikan 5 soal counting/probabilitas klasik (birthday problem, Monty Hall, dll) + 1 studi kasus Bayes lengkap (deteksi penyakit langka dengan false positive rate) — verifikasi hasil analitik dengan simulasi Monte Carlo di Python.

Status: Not Started

---

## Module 3 — Random Variable & Distribusi Diskrit

Description: Mengubah kejadian acak jadi objek matematis (angka) yang bisa dihitung ekspektasi dan variansnya — fondasi untuk semua model probabilistik setelahnya.

Lessons:

* [ ] 3.1 Random variable formal: PMF (probability mass function), CDF — sifat-sifatnya
* [ ] 3.2 Expectation `E[X]` — definisi formal, linearity of expectation (pembuktian singkat kenapa `E[X+Y] = E[X]+E[Y]` selalu benar, bahkan saat X dan Y tidak independen)
* [ ] 3.3 Variance random variable — derivasi `Var(X) = E[X²] - E[X]²`
* [ ] 3.4 Distribusi diskrit utama: Bernoulli, Binomial (diturunkan dari counting + trial independen), Geometric, Poisson (diturunkan sebagai limit Binomial saat `n→∞`, `p→0`)

Mini Project: Simulasikan Binomial, Geometric, dan Poisson dengan Monte Carlo (Python), bandingkan mean/variance empiris vs rumus teoretis, dan tunjukkan secara numerik bagaimana Binomial mendekati Poisson saat `n` besar & `p` kecil.

Status: Not Started

---

## Module 4 — Random Variable & Distribusi Kontinu

Description: Dari peluang diskrit ke peluang atas rentang bilangan real — termasuk distribusi Normal yang jadi jantung hampir semua inferensi statistik.

Lessons:

* [ ] 4.1 PDF vs PMF, CDF kontinu — kenapa `P(X = x) = 0` untuk random variable kontinu, dan kenapa itu tidak kontradiktif
* [ ] 4.2 Uniform & Exponential distribution — sifat *memoryless* Exponential (pembuktian singkat) dan hubungannya dengan Poisson process
* [ ] 4.3 Normal distribution: parameter, standar normal, z-score — preview kenapa distribusi ini "di mana-mana" (jawaban penuhnya di Modul 6)
* [ ] 4.4 Distribusi turunan untuk inferensi: Chi-square (jumlah kuadrat normal standar), Student's t (kenapa *fatter tails* dari Normal — relevan untuk sampel kecil), F-distribution (rasio dua Chi-square) — cukup dalam untuk paham kenapa masing-masing dipakai nanti di hypothesis testing (Modul 9) dan ANOVA (Modul 11)

Mini Project: Fit distribusi (Normal, Exponential) ke dataset nyata, buat QQ-plot untuk menguji kecocokan (goodness of fit) secara visual, dan jelaskan kapan asumsi distribusi tersebut valid/tidak valid.

Status: Not Started

---

## Module 5 — Joint Distribution, Covariance & Korelasi

Description: Data nyata jarang berupa satu variabel — modul ini membangun alat untuk memahami hubungan antar dua atau lebih random variable, fondasi matematis di balik "korelasi" yang sering disalahpahami.

Lessons:

* [ ] 5.1 Joint PMF/PDF, marginal distribution, conditional distribution
* [ ] 5.2 Independence formal vs *uncorrelated* — pembuktian bahwa independen selalu berarti uncorrelated, tapi tidak sebaliknya (contoh counter-example eksplisit)
* [ ] 5.3 Covariance — derivasi `Cov(X,Y) = E[XY] - E[X]E[Y]`, interpretasi tanda (+/-)
* [ ] 5.4 Korelasi Pearson — derivasi normalisasi covariance, kenapa nilainya selalu di rentang [-1, 1] (intuisi Cauchy-Schwarz)

Mini Project: Hitung covariance matrix secara manual dari dataset multi-variabel, visualisasikan dengan Anscombe's quartet untuk menunjukkan kenapa korelasi angka saja bisa menyesatkan tanpa visualisasi.

Status: Not Started

---

## Module 6 — Law of Large Numbers & Central Limit Theorem

Description: Dua teorema yang menjadi alasan **kenapa** inferensi statistik (Modul 7-9) bisa bekerja sama sekali — titik paling penting secara konseptual di seluruh course ini.

Lessons:

* [ ] 6.1 Law of Large Numbers (weak) — statement, intuisi kenapa rata-rata sampel konvergen ke rata-rata populasi
* [ ] 6.2 Central Limit Theorem — statement formal, kenapa distribusi sampling dari mean mendekati Normal *apa pun* bentuk distribusi asalnya
* [ ] 6.3 Sampling distribution — konsep kunci yang menjembatani probabilitas (Modul 2-5) ke statistik inferensial (Modul 7-9)

Mini Project: Simulasikan CLT secara eksplisit — ambil ribuan sampel mean dari distribusi non-Normal (Exponential, Uniform), plot histogram distribusi sampling, tunjukkan konvergensi ke bentuk Normal seiring ukuran sampel membesar.

Status: Not Started

---

## Module 7 — Sampling Methods & Teori Estimasi

Description: Dari populasi ke sampel yang representatif, dan dari sampel ke estimasi parameter populasi yang bisa dipertanggungjawabkan secara matematis.

Lessons:

* [ ] 7.1 Metode sampling: simple random, stratified, cluster, systematic — sumber bias (selection bias, non-response bias) dan dampaknya ke validitas kesimpulan
* [ ] 7.2 Sifat estimator: unbiasedness, consistency, efficiency — kenapa "estimator yang baik" bukan cuma soal "dekat dengan nilai asli"
* [ ] 7.3 Method of Moments vs Maximum Likelihood Estimation (MLE) — derivasi MLE untuk parameter Bernoulli dan Normal (turunan log-likelihood, set ke nol)
* [ ] 7.4 Standard error — beda dengan standard deviation, kenapa mengecil seiring `n` membesar (kaitan langsung ke CLT Modul 6)

Mini Project: Derivasi MLE untuk parameter distribusi (mis. rate Exponential atau mean Normal) di atas kertas, lalu implementasikan optimisasi log-likelihood dari nol di Python dan verifikasi hasilnya cocok dengan solusi analitik.

Status: Not Started

---

## Module 8 — Confidence Interval

Description: Mengukur ketidakpastian dari estimasi — salah satu konsep statistik yang paling sering **disalahpahami** bahkan oleh praktisi berpengalaman, sehingga interpretasi yang benar jadi fokus utama modul ini.

Lessons:

* [ ] 8.1 Logika interval estimation — interpretasi frequentist yang benar ("95% dari interval yang dibangun dengan prosedur ini akan memuat parameter sebenarnya", **bukan** "95% probabilitas parameter ada di interval ini")
* [ ] 8.2 CI untuk mean: kapan pakai z (variance populasi diketahui) vs t (variance diestimasi dari sampel, sampel kecil)
* [ ] 8.3 CI untuk proporsi, margin of error, dan penentuan ukuran sampel yang dibutuhkan
* [ ] 8.4 Bootstrap sebagai alternatif non-parametrik — konsep resampling, kapan dipakai saat asumsi distribusi tidak terpenuhi

Mini Project: Hitung & interpretasikan CI dari dataset nyata, lalu jalankan simulasi coverage probability (bangun 1000+ CI dari sampel berbeda-beda, verifikasi ~95% dari interval tersebut benar-benar memuat parameter populasi yang diketahui).

Status: Not Started

---

## Module 9 — Hypothesis Testing

Description: Kerangka formal untuk membuat keputusan berbasis data di bawah ketidakpastian — dan sumber miskonsepsi statistik paling umum (p-value, signifikansi) yang perlu diluruskan sejak awal.

Lessons:

* [ ] 9.1 Logika Null Hypothesis Significance Testing: `H0`/`H1`, Type I error (`α`) vs Type II error (`β`), statistical power
* [ ] 9.2 p-value — definisi formal yang benar, dan daftar miskonsepsi umum yang salah (p-value **bukan** probabilitas `H0` benar)
* [ ] 9.3 One-sample & two-sample test: z-test, t-test (paired vs unpaired) — kapan masing-masing valid dipakai
* [ ] 9.4 Chi-square test: goodness of fit dan test of independence
* [ ] 9.5 Multiple testing problem — kenapa menjalankan banyak test menaikkan false positive rate, pengantar koreksi Bonferroni

Mini Project: Jalankan rangkaian hypothesis test end-to-end pada dataset nyata (definisikan hipotesis, cek asumsi, pilih uji yang tepat, hitung & interpretasikan p-value dengan benar, tulis kesimpulan tanpa overclaim).

Status: Not Started

---

## Module 10 — Korelasi & Regresi Linear

Description: Dari "dua variabel berhubungan" (Modul 5) ke "memodelkan hubungan itu secara matematis" — derivasi least squares dari nol memakai kalkulus minimisasi.

Lessons:

* [ ] 10.1 Simple linear regression — derivasi least squares: minimisasi sum of squared residual lewat turunan parsial terhadap slope & intercept (normal equations)
* [ ] 10.2 Asumsi regresi linear: linearity, independence, homoscedasticity, normalitas residual — cara mengecek lewat diagnostic plot (residual plot, QQ-plot)
* [ ] 10.3 R-squared — derivasi & interpretasi (proporsi variance yang dijelaskan model), hubungannya dengan korelasi Pearson (`r² `)
* [ ] 10.4 Pengantar multiple regression — bentuk matriks, intuisi multicollinearity (tanpa mendalami aljabar linear penuh, cukup untuk paham kapan regresi berganda jadi tidak stabil)

Mini Project: Derivasi & implementasi simple linear regression dari nol (normal equations, tanpa `scikit-learn`) di Python, verifikasi hasil (slope, intercept, R²) identik dengan library, lalu cek asumsi model lewat diagnostic plot.

Status: Not Started

---

## Module 11 — ANOVA (Analysis of Variance)

Description: Memperluas hypothesis testing dari membandingkan 2 grup (Modul 9) ke membandingkan banyak grup sekaligus — dan kenapa "jalankan banyak t-test" adalah pendekatan yang secara statistik salah.

Lessons:

* [ ] 11.1 Kenapa banyak t-test berpasangan menaikkan family-wise error rate — alasan matematis butuh ANOVA
* [ ] 11.2 One-way ANOVA: dekomposisi total variance jadi between-group dan within-group variance, derivasi F-statistic sebagai rasio keduanya
* [ ] 11.3 Asumsi ANOVA (homogeneity of variance, normalitas per grup) dan post-hoc test (Tukey HSD) untuk tahu grup mana yang berbeda signifikan
* [ ] 11.4 Pengantar two-way ANOVA — konsep interaction effect (level konsep, tidak didalami penuh)

Mini Project: Jalankan one-way ANOVA lengkap pada dataset dengan >2 grup (mis. bandingkan rata-rata metrik antar 3+ kategori), interpretasikan F-statistic & p-value, lanjutkan dengan post-hoc test kalau signifikan.

Status: Not Started

---

## Module 12 — Capstone: Dari Statistik Klasik ke Fondasi Machine Learning

Description: Menyatukan seluruh course dan membuat jembatan eksplisit ke dua course "hilir" — supaya begitu masuk ke `ml-fundamentals` Module 4 atau `data-analysis-visualization` Module 4, materinya terasa seperti *penerapan* dari yang sudah dikuasai, bukan topik baru.

Lessons:

* [ ] 12.1 Peta konsep: MLE (Modul 7) → loss function ML; CLT (Modul 6) → keandalan metric evaluasi model; Bayes (Modul 2) → Naive Bayes classifier & Bayesian ML; regresi (Modul 10) → linear/logistic regression sebagai model ML
* [ ] 12.2 Bayesian vs Frequentist — overview perbandingan filosofis dua aliran statistik (course ini condong ke fondasi frequentist/axiomatic, tapi paham gambaran besar Bayesian penting karena banyak dipakai di ML modern)
* [ ] 12.3 Rekap eksplisit: bagian mana dari `ml-fundamentals` Module 4 dan `data-analysis-visualization` Module 4 yang sekarang seharusnya terasa seperti *aplikasi langsung*, bukan konsep baru
* [ ] 12.4 **Production Discussion**: pola umum men-serve hasil analisis statistik (mis. confidence interval, hasil A/B test) dari service Python sebagai internal API yang dikonsumsi backend Java/Spring Boot — pola yang sama seperti `ml-fundamentals` Module 10

Portfolio Project: Lihat "Capstone Project" di bawah.

Status: Not Started

---

# Capstone Project

Description: **Full Statistical Inference Pipeline** — pilih 1 dataset nyata dan jalankan pipeline analisis statistik lengkap dengan derivasi manual di titik-titik kunci (bukan hanya memanggil `scipy.stats` sebagai black box): (1) statistika deskriptif & eksplorasi (Modul 1), (2) fit & verifikasi model probabilistik yang sesuai bentuk data (Modul 3-4), (3) confidence interval untuk parameter kunci (Modul 8), (4) minimal 1 hypothesis test yang relevan dengan pertanyaan dataset (Modul 9), (5) regresi linear atau ANOVA (pilih sesuai jenis pertanyaan — hubungan dua variabel kontinu vs perbandingan banyak grup) (Modul 10-11), (6) laporan tertulis yang menyatakan asumsi, menunjukkan pekerjaan (*show work*, bukan hanya hasil akhir), dan tidak overclaim dari hasil statistik.

Acceptance Criteria:

* Minimal 2 hasil kunci (mis. estimator MLE, koefisien regresi, atau F-statistic ANOVA) dihitung manual dari rumus/derivasi lalu diverifikasi identik dengan library (`scipy.stats`/`statsmodels`)
* Semua uji statistik menyatakan asumsi yang diperlukan dan bukti bahwa asumsi tersebut dicek (bukan diasumsikan begitu saja)
* Interpretasi p-value dan confidence interval memakai bahasa yang secara teknis benar (tidak mengulang miskonsepsi umum yang sudah dibahas di Modul 8-9)
* Ada bagian eksplisit yang menghubungkan minimal 1 temuan ke konsep ML (mis. "estimator ini setara dengan cara kerja loss function di linear regression ML")
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: Module 1, Lesson 1.1 — Populasi vs sampel, jenis data (nominal, ordinal, interval, ratio)
