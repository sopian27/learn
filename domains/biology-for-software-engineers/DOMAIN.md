# Biologi untuk Software Engineer — Domain Pack

**Status: DRAFT — menunggu approval user, belum terdaftar di `ai-los/ACTIVE_DOMAIN.md` / `domains/README.md`.**

## Domain Name

Biologi untuk Software Engineer (Biology for Software Engineers) — biologi sel & molekuler, genetika, evolusi, sistem tubuh manusia, neuroscience, ekologi, dan bioinformatika/AI-in-biology, dijembatani eksplisit ke software engineering lewat analogi arsitektur DAN latihan bioinformatika ringan berbasis Python.

## Scope

Biologi sebagai alat berpikir DAN alat kerja seorang software engineer — "biologi YANG diterjemahkan jadi mental model arsitektur software, DAN biologi yang diproses lewat kode," bukan biologi untuk biologiwan/pre-med. Setiap modul harus terhubung eksplisit ke salah satu dari dua hal: (a) analogi konsep biologi ke desain/arsitektur software, atau (b) artefak kode bioinformatika yang benar-benar berjalan:

- **Biologi sel & molekuler dasar**: struktur sel (prokariotik vs eukariotik), organel & fungsinya, membran sel, metabolisme dasar (respirasi seluler, fotosintesis secukupnya) — fondasi sebelum masuk DNA/genetika, mengikuti struktur awal MIT OCW 7.01SC & Molecular Biology of the Cell.
- **DNA, RNA, dan Central Dogma**: struktur DNA/RNA, replikasi, transkripsi, translasi, kode genetik — central dogma sebagai "compile pipeline" biologis (DNA=source code, RNA=intermediate representation, protein=binary/executable).
- **Genetika & pewarisan sifat**: Hukum Mendel, dominan/resesif, alel, genotype vs phenotype, mutasi, rekombinasi genetik — dasar sebelum evolusi.
- **Evolusi & seleksi alam**: teori evolusi Darwin, seleksi alam, genetic drift, spesiasi — dijembatani eksplisit ke algoritma genetika (genetic algorithms) & evolutionary computing sebagai analogi/aplikasi komputasi.
- **Sistem tubuh manusia (overview)**: gambaran umum sistem sirkulasi, respirasi, pencernaan, imun, endokrin — cukup untuk literasi umum & fondasi modul neuroscience, TIDAK mendalam ke tiap sistem selevel kedokteran.
- **Neuroscience dasar**: struktur neuron, sinapsis, potensial aksi, plastisitas otak, gambaran umum area otak — dijembatani eksplisit ke artificial neural network sebagai analogi/kontras (bukan klaim neural network = otak sungguhan).
- **Ekologi & sistem kompleks**: rantai makanan, ekosistem, homeostasis, resiliensi ekosistem, keystone species — dijembatani eksplisit ke distributed systems/microservices resilience (redundancy, graceful degradation, self-healing systems, cascading failure).
- **Bioinformatika praktis dengan Python**: manipulasi sekuens DNA/protein, sequence alignment (pairwise/multiple), parsing format bioinformatika umum (FASTA, GenBank), query database publik (NCBI) — pakai Biopython sebagai tool utama, level "cukup untuk membaca & memproses data biologis," bukan riset genomik production-grade.
- **AI-in-biology**: gambaran konseptual protein folding (AlphaFold), drug discovery dengan ML, genomics + machine learning — level literasi arsitektur/use-case, bukan riset ML biologi mendalam.
- **Sintesis: prinsip biologi sebagai mental model desain software**: evolvability, redundancy, homeostasis sebagai kerangka eksplisit untuk self-healing systems, resilient architecture, dan evolutionary/incremental software design — modul penutup yang mengikat semua modul sebelumnya kembali ke software engineering.

Secara eksplisit domain ini TIDAK mencakup, dan mentor WAJIB menolak/mengarahkan ulang bila diminta:

- **Kedokteran klinis, diagnosis, atau saran pengobatan** — modul "Sistem Tubuh Manusia" murni literasi sains struktur/fungsi, TIDAK PERNAH dipakai untuk mendiagnosis, menyarankan pengobatan, atau menilai kondisi kesehatan learner/orang sungguhan. Bukan pengganti dokter/tenaga medis berlisensi (lihat Domain-Specific Standards).
- **Kurikulum pre-med/USMLE atau gelar biologi/kedokteran formal** — ini literasi terapan + artefak yang bisa dibangun (buildable artifacts), bukan pengganti gelar Biologi/Kedokteran terakreditasi atau ujian sertifikasi profesi medis.
- **Riset genomik/wet-lab production-grade** — modul bioinformatika mengajarkan penggunaan tool (Biopython, BLAST) untuk memproses & memahami data biologis, BUKAN protokol laboratorium basah (wet-lab), desain eksperimen genomik penelitian, atau pipeline bioinformatika skala production rumah sakit/perusahaan bioteknologi.
- **Riset ML/AI biologi mendalam (model architecture AlphaFold dari nol, dst)** — modul AI-in-biology bersifat literasi konseptual "apa itu & kenapa penting," bukan reproduksi arsitektur model riset dari paper.
- **Asal-usul kehidupan di semesta/astrobiologi/SETI** — domain terpisah [[domains/astronomy-and-life-in-the-universe/DOMAIN.md|astronomy-and-life-in-the-universe]] (Modul Astrobiologi) sudah memiliki scope itu (extremophile, habitable zone, Drake Equation); domain ini fokus biologi Bumi yang sudah established (sel, DNA, evolusi organisme yang ada), bukan spekulasi asal-usul kehidupan kosmik — bila pertanyaan menyentuh itu, diarahkan ke domain tersebut.

Domain ini beririsan erat dengan domain software-engineering di repo ini (secara sengaja) — dikonsumsi lewat dua jalur: analogi konseptual ke arsitektur software (mayoritas modul) DAN kode bioinformatika aktif (modul bioinformatika khusus), konsisten dengan pola domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]] tapi dengan bobot lebih besar ke analogi konseptual dibanding physics yang selalu berujung simulasi numerik.

## Trusted Sources / Research Priority

1. **Molecular Biology of the Cell, 4th ed.** (Alberts, Johnson, Lewis, Raff, Roberts, Walter — gratis via NCBI Bookshelf, https://www.ncbi.nlm.nih.gov/books/NBK21054/) — textbook definitif biologi sel & molekuler, tulang punggung modul sel, DNA/RNA, dan central dogma.
2. **MIT OpenCourseWare 7.01SC — Fundamentals of Biology** (https://ocw.mit.edu/courses/7-01sc-fundamentals-of-biology-fall-2011/, dikembangkan a.l. oleh Eric Lander, pemimpin Human Genome Project, bersama Robert Weinberg & Graham Walker) — materi kuliah resmi MIT, format OCW Scholar untuk independent learner, dipakai sebagai kerangka struktur modul sel/DNA/genetika. Lihat juga 7.012/7.013/7.014 (https://ocw.mit.edu/courses/7-012-introduction-to-biology-fall-2004/) untuk versi kuliah reguler bila butuh kedalaman tambahan.
3. **Khan Academy Biology** (High School Biology https://www.khanacademy.org/science/hs-bio, plus AP/College Biology) — sumber gratis, visual, terstruktur baik, dipakai untuk membangun intuisi awal tiap topik (genetika, evolusi, ekologi) sebelum masuk kedalaman MIT OCW/textbook, konsisten dengan permintaan riset user.
4. **NCBI (National Center for Biotechnology Information)** (https://www.ncbi.nlm.nih.gov/) — sumber otoritatif untuk GenBank (database sekuens), BLAST (alat pencarian kesamaan sekuens), dan NCBI Bookshelf — dipakai sebagai rujukan utama & tool praktik modul bioinformatika.
5. **Biopython — dokumentasi resmi** (https://biopython.org/, Tutorial & Cookbook https://biopython.org/docs/latest/Tutorial/) — library Python standar de-facto untuk bioinformatika terapan, dipakai sebagai tool utama seluruh latihan kode modul Bioinformatika Praktis.
6. **Eric R. Kandel et al., *Principles of Neural Science*** (McGraw-Hill, edisi terkini) — "gold standard" textbook neuroscience, ditulis peraih Nobel Fisiologi/Kedokteran 2000; dipakai sebagai rujukan definitif modul neuroscience dasar (struktur neuron, sinapsis, potensial aksi).
7. **AlphaFold Protein Structure Database** (https://alphafold.ebi.ac.uk/, hasil kolaborasi EMBL-EBI & Google DeepMind, https://deepmind.google/science/alphafold/) — rujukan utama modul AI-in-biology untuk protein folding, dipakai sebagai studi kasus "bagaimana AI mengubah biologi struktural" secara konseptual.
8. **Charles Darwin, *On the Origin of Species* (rangkuman konsep) + sumber modern evolusi** (mis. materi evolusi Khan Academy/MIT OCW di atas) — dipakai untuk fondasi konseptual modul evolusi, disilangkan ke penjelasan modern genetic drift/spesiasi (bukan hanya teks 1859 apa adanya).
9. **Sumber sekunder kredibel untuk ekologi & sistem kompleks** (mis. materi ekologi Khan Academy/OpenStax Biology) — dipakai secukupnya untuk modul ekologi, selalu proporsional dengan bobot analogi ke distributed systems yang jadi fokus utama domain ini.

Klaim biologis inti (mis. mekanisme central dogma, hukum Mendel, prinsip evolusi) selalu disilangkan minimal ke satu sumber textbook/akademik (1, 2, atau 6) DAN divalidasi konsisten dengan Khan Academy (3) untuk kejelasan penyampaian — tidak cukup hanya dari satu sumber atau dari ingatan/asumsi tanpa verifikasi, sama seperti disiplin sumber di domain [[domains/physics-for-software-engineers/DOMAIN.md|physics-for-software-engineers]].

## Applied Practice Definition

Applied practice di domain ini punya DUA bentuk yang sama sahnya (beda dari physics-for-software-engineers yang applied practice-nya selalu kode simulasi) — dipilih sesuai sifat modul:

- **Analogi konsep biologi ke arsitektur software** — untuk modul konseptual (sel, DNA/central dogma, genetika, evolusi, sistem tubuh, neuroscience, ekologi, sintesis), applied practice berarti menulis pemetaan eksplisit & terstruktur dari konsep biologi ke keputusan desain software nyata (mis. "central dogma → compile/build pipeline: bandingkan tahap replikasi/transkripsi/translasi dengan source control/CI/deployment", "homeostasis → circuit breaker & auto-scaling: jelaskan mekanisme feedback loop yang sama"), disertai contoh kode/arsitektur konkret (pseudocode, diagram, atau cuplikan kode nyata) yang menunjukkan analogi itu bekerja, bukan cuma perbandingan kalimat retoris.
- **Latihan bioinformatika ringan berbasis Python** — untuk modul bioinformatika & AI-in-biology, applied practice berarti kode Python yang benar-benar berjalan memakai Biopython (mis. parsing FASTA, menghitung GC content, pairwise sequence alignment, query NCBI via Biopython.Entrez) dengan output yang bisa diperiksa terhadap ekspektasi biologis yang diketahui (mis. hasil alignment dicek manual untuk sekuens pendek).

Setiap modul WAJIB berakhir di salah satu bentuk di atas sebelum dianggap selesai, sesuai `# Hands-on First` di `CLAUDE.md` root — modul konseptual tidak boleh berhenti di "sudah paham teorinya," harus ada artefak analogi tertulis + contoh kode/arsitektur konkret.

## Project Tiers

- **Mini Project** (per modul): satu artefak pemetaan analogi tertulis + contoh kode/arsitektur (untuk modul konseptual) ATAU satu skrip Python kecil dengan Biopython yang menyelesaikan satu tugas bioinformatika terfokus (untuk modul bioinformatika/AI).
- **Intermediate Project** (setelah beberapa modul terkait): artefak gabungan, mis. **"Central Dogma as Software Pipeline"** (dokumen + diagram yang memetakan DNA→RNA→protein secara detail ke source→build→deploy, dilengkapi contoh kode CI/CD nyata) setelah modul DNA/RNA & genetika, atau **"Sequence Analysis Toolkit"** (kumpulan skrip Biopython: parsing, alignment, GC content, query NCBI) setelah modul bioinformatika.
- **Capstone / Portfolio Project** (akhir course): **"Biological Design Patterns for Resilient Software"** — dokumen arsitektur portofolio yang mengintegrasikan minimal 3 prinsip biologi (mis. evolvability, redundancy, homeostasis) sebagai kerangka desain eksplisit untuk satu sistem software nyata/hipotetis (arsitektur microservices/distributed system), DILENGKAPI toolkit bioinformatika Python dari Intermediate Project sebagai bukti kemampuan applied coding — disimpan ke `portofolio/`, disertai penjelasan tertulis kenapa tiap analogi biologi dipilih dan batasannya (di mana analogi itu berhenti berlaku).

## Review Style

Di atas Universal Review Rubric (Strengths/Weaknesses/Actionable Improvements/Score 0-100), setiap artefak di domain ini juga dicek lewat lensa berikut:

- **Biological Accuracy** — apakah pemahaman konsep biologi yang dipakai sebagai dasar analogi benar secara sains (merujuk Trusted Sources), bukan penyederhanaan yang menyesatkan?
- **Analogy Fidelity** — apakah analogi ke software benar-benar menjelaskan mekanisme (bukan cuma kemiripan nama/permukaan) — mis. "homeostasis mirip circuit breaker" harus menjelaskan KENAPA mekanisme feedback-nya sama, bukan cuma "keduanya menjaga stabilitas."
- **Analogy Boundary Awareness** — apakah artefak juga menyatakan eksplisit di mana analogi BERHENTI berlaku (mis. neural network buatan TIDAK belajar seperti neuron biologis secara mekanistik) — konsisten dengan disiplin "no false equivalence" yang dipakai domain lain di repo ini.
- **Code Correctness (khusus modul bioinformatika)** — untuk artefak Python/Biopython, apakah kode benar secara sintaks & benar secara biologis (mis. hasil alignment/GC content sesuai perhitungan manual), memakai lensa dasar dari `standards/` repo (readability, correctness) yang berlaku lintas domain.

## Assessment Form

Concept-check singkat setelah tiap bagian teori (skenario "apa yang terjadi jika mekanisme X gagal" untuk menguji pemahaman mekanisme, bukan hafalan istilah), kuis genetika/evolusi bergaya Khan Academy dengan kunci jawaban untuk self-check, dan review artefak (baik dokumen analogi maupun kode bioinformatika — learner mempresentasikan analogi/kode-nya dan menjelaskan alasan desainnya) sebagai bentuk assessment applied yang khas domain ini.

## Practitioner Reference Frame

Bandingkan beberapa lensa berbeda untuk mendekati topik yang sama:

- **Computational biologist / bioinformatician** (gaya Eric Lander & Broad Institute of MIT and Harvard, https://www.broadinstitute.org/ — Lander adalah pemimpin utama Human Genome Project) — fokus pada memproses data biologis skala besar dengan komputasi, lensa dominan untuk modul bioinformatika & genetika.
- **Structural/AI biologist** (gaya tim AlphaFold di Google DeepMind & EMBL-EBI) — fokus pada bagaimana machine learning mempercepat pemahaman biologi struktural (protein folding), lensa dominan untuk modul AI-in-biology.
- **Neuroscientist akademik** (gaya Eric Kandel, peraih Nobel, Columbia University) — rigor mekanistik dari molekul (sinapsis) sampai perilaku, lensa dominan untuk modul neuroscience — dipakai juga untuk menjaga batas analogi neural network vs otak biologis tetap jujur.
- **Software architect yang memakai biologi sebagai mental model** (pola pikir yang dipopulerkan diskusi "antifragile systems", "biologically-inspired computing", self-healing infrastructure di industri seperti Netflix — Chaos Engineering terinspirasi resiliensi ekosistem) — lensa dominan untuk modul sintesis penutup & seluruh Applied Practice analogi.

Keempat lensa ini sering menekankan hal berbeda untuk topik yang sama (mis. evolusi: computational biologist melihatnya sebagai algoritma optimasi/genetic algorithm, software architect melihatnya sebagai justifikasi untuk incremental/evolutionary architecture) — domain ini melatih learner mengenali perbedaan penekanan tersebut secara sadar, bukan menganggap satu lensa sebagai satu-satunya jawaban benar.

## Domain-Specific Standards

`domains/biology-for-software-engineers/standards/` belum ada file terpisah, tapi domain ini punya house rule eksplisit:

- **Tidak ada analogi tanpa mekanisme.** Konsisten dengan Applied Practice Definition & Review Style "Analogy Fidelity" di atas — modul konseptual yang tidak berakhir dengan pemetaan mekanisme eksplisit (bukan cuma kemiripan permukaan) ke keputusan software nyata dianggap belum selesai.
- **Batas analogi dinyatakan eksplisit.** Setiap analogi biologi→software WAJIB menyatakan juga di mana analogi itu berhenti berlaku (lihat Review Style "Analogy Boundary Awareness") — terutama untuk neural network vs otak biologis, dan evolutionary computing vs evolusi biologis sungguhan, supaya learner tidak keliru mengira software architecture = biologi literal.
- **Literasi, bukan pengganti kredensial medis/biologi formal.** Setiap kali lesson menyentuh area yang butuh kredensial nyata (mis. "bagaimana mendiagnosis penyakit", "bagaimana sertifikasi dokter/ahli biologi profesional", "protokol wet-lab penelitian genomik"), mentor menyatakan eksplisit batas ini dan mengarahkan ke jalur pendidikan/sertifikasi/profesional resmi yang sesungguhnya — konsisten dengan house rule serupa di `domains/nuclear-technology/DOMAIN.md` dan `domains/physics-for-software-engineers/DOMAIN.md`.
- **Eskalasi untuk kekhawatiran kesehatan nyata.** Modul "Sistem Tubuh Manusia" dan topik yang bersinggungan dengan kesehatan TIDAK PERNAH dipakai untuk menilai/mendiagnosis kondisi kesehatan learner atau orang sungguhan — bila muncul kekhawatiran kesehatan nyata dalam percakapan, mentor mengarahkan eksplisit ke dokter/tenaga medis berlisensi, mirroring pola "consult a licensed professional" di `domains/psychology/DOMAIN.md` dan `domains/personal-finance/DOMAIN.md`.
- **Validasi biologis wajib untuk kode bioinformatika.** Setiap skrip Biopython yang dianggap "selesai" harus punya bukti eksplisit hasilnya benar secara biologis (mis. hasil alignment/GC content dicek manual untuk kasus kecil) — bukan cuma "kodenya jalan tanpa error", konsisten dengan disiplin "validasi numerik wajib" di `domains/physics-for-software-engineers/DOMAIN.md`.

## Domain Goal

Learner mampu (1) memakai konsep inti biologi (sel, DNA/central dogma, genetika, evolusi, sistem tubuh, neuroscience, ekologi) sebagai kerangka mental model eksplisit dan bermekanisme jelas untuk mendesain software yang lebih resilient, evolvable, dan self-healing, DAN (2) memproses data biologis dasar (sekuens DNA/protein) dengan Python/Biopython sebagai keterampilan bioinformatika terapan — dibuktikan lewat capstone project "Biological Design Patterns for Resilient Software" di portofolio yang menggabungkan analogi arsitektur yang jujur (dengan batasannya dinyatakan) DAN toolkit bioinformatika kode nyata. Ini eksplisit BUKAN kredensial biologi/kedokteran formal — murni literasi terapan yang menghubungkan latar belakang software engineering learner dengan biologi sebagai sumber mental model desain DAN sebagai domain data yang bisa diproses dengan kode.

## Registration History

Domain ini didaftarkan (registered) 2026-08-14 atas permintaan langsung user untuk membangun course "Biologi for Software Engineer" dari 0 sampai expert, mencakup sel, DNA, genetika, evolusi, sistem tubuh, neuroscience, ekologi, bioinformatika, dan AI-biologi, dengan penekanan eksplisit pada analogi konsep biologi ke arsitektur software. Domain pack ini di-scaffold meniru pola `domains/physics-for-software-engineers/DOMAIN.md` (domain saudara yang sudah ada di repo, sama-sama "sains untuk software engineer") untuk konsistensi struktural, dengan penyesuaian bobot Applied Practice: physics selalu berujung simulasi numerik yang divalidasi, domain ini menyeimbangkan analogi arsitektur (mayoritas modul) dengan bioinformatika Python (modul khusus) karena sifat materinya lebih konseptual/deskriptif dibanding physics yang selalu bisa disimulasikan numerik. Status masih DRAFT — menunggu approval user sebelum didaftarkan ke `ai-los/ACTIVE_DOMAIN.md`.
