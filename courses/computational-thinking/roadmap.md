# Course Roadmap

## Course Information

* Course Name: Computational Thinking & Algoritma — dari 0 sampai Expert (dengan *The Art of Computer Programming* sebagai Referensi Mendalam)
* Category: Computer Science Fundamentals (berpikir komputasional, struktur data, analisis algoritma) — melengkapi fondasi backend, bukan pengganti `courses/backend-fundamentals` maupun `courses/design-patterns`
* Difficulty: Level 1 (bisa coding tapi belum punya kerangka berpikir algoritmik terstruktur, belum insting kompleksitas Big-O, belum pernah implementasi struktur data dari nol) → Level 4 (bisa mendesain & menganalisis algoritma sendiri, mengenali struktur data yang tepat untuk masalah produksi nyata, siap technical interview Senior/Staff)
* Estimated Duration: 14 modul, 3-5 lesson per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Bisa coding Java dasar (variable, loop, function, OOP dasar). Tidak perlu pernah belajar struktur data/algoritma formal sebelumnya — course ini mulai dari 0 untuk topik itu.

---

# Catatan Scope (baca dulu sebelum approve)

Berdasarkan jawaban kalibrasi Anda:

1. **Posisi TAOCP**: Anda memilih TAOCP sebagai **referensi opsional**, bukan expert layer wajib maupun jalur serius penuh. Karena itu, tiap modul yang punya bab relevan di *The Art of Computer Programming* akan diberi kotak "**TAOCP Deep Reference**" — pointer ke volume/bab spesifik Knuth untuk siapa yang mau baca lebih dalam — tapi tidak ada soal dari TAOCP yang wajib dikerjakan, dan notasi MIX/MMIX tidak diajarkan. Fondasi utama course tetap CLRS-style (*Introduction to Algorithms* — Cormen/Leiserson/Rivest/Stein) dan Sedgewick (*Algorithms*), yang jauh lebih pedagogis untuk belajar dari 0.
2. **Bahasa**: **Java saja**, konsisten dengan stack profesional Anda. Semua struktur data diimplementasi dari nol di Java (bukan langsung pakai `java.util`), baru dibandingkan dengan implementasi built-in Java setelah paham cara kerja internalnya.
3. **Tujuan**: **Fondasi CS + interview readiness**, condong ke praktis. Course ini secara eksplisit mengisi gap "fondasi terasa rapuh" dari `progress/roadmap.md` Gap Analysis — bukan proof-heavy/teoretis penuh. Bukti matematis dijelaskan secukupnya untuk paham *kenapa*, bukan untuk melatih kemampuan pembuktian formal.
4. **Kenapa Modul 1 (Computational Thinking) wajib sebelum struktur data**: Tanpa kerangka dekomposisi/abstraksi/pattern recognition, algoritma dan struktur data akan terasa seperti daftar resep hafalan yang harus dicocokkan ke soal. Modul 1 melatih *cara mengenali* masalah seperti apa yang butuh solusi seperti apa — kerangka mental yang dipakai di semua modul setelahnya.
5. **Kenapa ada Modul 14 (Capstone ke sistem nyata) alih-alih cuma problem set**: Sesuai `ai-los/CORE_LOS.md` Project Tiering, course harus berakhir di artefak produksi nyata. Modul 13 melatih pattern interview (LeetCode-style), tapi Modul 14 memaksa Anda menerapkan struktur data/algoritma yang tepat ke *fitur nyata dari pekerjaan Anda* — supaya ilmu ini tidak berhenti di "bisa jawab interview" tapi juga "bisa dipakai di kode produksi".
6. **Irisan dengan course lain**: Course ini **tidak** mengulang arsitektur/design pattern (`courses/design-patterns`) atau production concern Spring Boot/database mendalam (`courses/backend-fundamentals`) — course ini murni lapisan CS fundamentals di bawah keduanya. Modul 7 (Trees) akan menyinggung B-Tree sebagai jembatan konseptual ke database indexing, tapi indexing production-grade tetap domain `backend-fundamentals`.

Referensi riset yang dipakai menyusun roadmap ini: definisi & 4 elemen computational thinking mengikuti Jeannette Wing, ["Computational Thinking"](https://www.cs.cmu.edu/afs/cs/usr/wing/www/Computational_Thinking.pdf) (CMU, 2006) dan ["Computational Thinking: What and Why?"](https://www.cs.cmu.edu/~CompThink/resources/TheLinkWing.pdf) (2010); struktur modul algoritma & struktur data mengikuti urutan kanonik *Introduction to Algorithms* (CLRS) dan *Algorithms* (Sedgewick & Wayne); status volume *The Art of Computer Programming* per riset terbaru — [Volume 4B terbit akhir 2022](https://www-cs-faculty.stanford.edu/~knuth/taocp.html), Volume 4C baru terbit sebagian (~275 halaman pertama), Volume 5 belum terbit — dipakai untuk memastikan pointer "TAOCP Deep Reference" di tiap modul merujuk ke volume yang benar-benar sudah ada.

---

# Learning Objectives

* [ ] Menguasai 4 elemen computational thinking (decomposition, pattern recognition, abstraction, algorithm design) dan bisa menerapkannya ke masalah backend nyata sebelum menulis kode
* [ ] Paham notasi asymptotic (Big-O/Ω/Θ) bukan sebagai hafalan, tapi sebagai alat untuk menghitung kompleksitas kode sendiri
* [ ] Bisa mengimplementasikan dari nol (bukan cuma memakai): linked list, stack, queue, hash table, binary search tree, heap, graph
* [ ] Menguasai algoritma sorting & searching utama (merge sort, quicksort, heapsort, binary search, hashing) beserta trade-off dan kompleksitasnya
* [ ] Paham algorithmic paradigms: divide and conquer, greedy, dynamic programming, backtracking — dan tahu kapan tiap paradigma tepat/tidak tepat dipakai
* [ ] Paham dasar graph algorithms (BFS/DFS, Dijkstra, MST) dan kaitannya dengan masalah nyata (routing, dependency resolution, scheduling)
* [ ] Punya intuisi praktis soal P vs NP dan kenapa sebagian masalah backend didekati dengan heuristik, bukan solusi optimal
* [ ] Mengenali pattern soal interview umum (two pointers, sliding window, fast & slow pointers) dan bisa memetakannya ke struktur data/algoritma yang tepat
* [ ] Siap technical interview level Senior/Staff untuk pertanyaan algoritma & struktur data
* [ ] Tahu kapan dan di mana mencari kedalaman lebih lanjut di *The Art of Computer Programming* untuk topik spesifik, tanpa harus membacanya cover-to-cover
* [ ] Menyelesaikan proyek portofolio: identifikasi & refactor satu fitur nyata dari pekerjaan Anda memakai struktur data/algoritma yang lebih tepat, dengan benchmark before/after dan dokumentasi keputusan (ADR-style)

---

# Course Modules

## Module 1 — Computational Thinking: Kerangka Berpikir Sebelum Coding

Description: Sebelum satu baris kode struktur data pun ditulis, bangun dulu kerangka mental untuk mendekati masalah apa pun secara sistematis. Modul ini adalah alasan kenapa modul-modul algoritma setelahnya akan terasa seperti jawaban logis, bukan resep hafalan.

Lessons:

* [ ] Apa itu computational thinking dan kenapa Jeannette Wing menyebutnya sepenting membaca-menulis-berhitung
* [ ] Decomposition — memecah masalah besar jadi sub-masalah yang bisa ditangani terpisah
* [ ] Pattern Recognition — mengenali kemiripan dengan masalah yang sudah pernah dipecahkan
* [ ] Abstraction — membuang detail tidak relevan, fokus ke struktur inti masalah
* [ ] Algorithm Design — menyusun langkah solusi yang presisi & bisa dieksekusi, dari bahasa natural ke pseudocode

Mini Project: Ambil 3 masalah nyata dari pekerjaan Anda (bug rumit, fitur baru, atau proses manual) — dekomposisi dan tulis pseudocode-nya tanpa menulis kode Java sama sekali dulu (latihan berpikir, bukan implementasi).

Status: Not Started

---

## Module 2 — Fondasi Matematika untuk Analisis Algoritma

Description: Alat ukur untuk membandingkan algoritma secara objektif — tanpa ini, "algoritma A lebih cepat dari B" cuma perasaan, bukan analisis.

Lessons:

* [ ] Notasi asymptotic: Big-O, Big-Omega (Ω), Big-Theta (Θ) — beda makna best/worst/average case
* [ ] Menghitung kompleksitas waktu dari kode Java langsung (loop, nested loop, rekursi sederhana)
* [ ] Kompleksitas ruang (space complexity) dan trade-off time vs space
* [ ] Dasar matematika pendukung: summation, logaritma dalam konteks algoritma, induksi matematika secukupnya untuk memahami bukti korektnes sederhana
* [ ] **TAOCP Deep Reference**: Knuth *TAOCP Vol. 1, Ch. 1* — definisi formal notasi O yang jadi standar industri hari ini

Mini Project: Ambil 5 potongan kode Java (loop bersarang, rekursi, pencarian linear) dan tentukan kompleksitas Big-O masing-masing dengan justifikasi tertulis.

Status: Not Started

---

## Module 3 — Struktur Data Dasar: Array, Linked List, Stack, Queue

Description: Struktur data paling fundamental, diimplementasikan dari nol supaya paham *cara kerja* internalnya, bukan cuma cara memakai `java.util`.

Lessons:

* [ ] Array vs dynamic array (`ArrayList`): kenapa resize butuh alokasi ulang, amortized complexity
* [ ] Singly & doubly linked list — implementasi dari nol di Java
* [ ] Stack: implementasi dari nol (pakai array & linked list), use case nyata (undo history, expression evaluation, call stack)
* [ ] Queue & deque: implementasi dari nol, use case nyata (task queue, BFS nanti di Modul 9)
* [ ] Production Discussion: kapan `ArrayList` vs `LinkedList` vs `ArrayDeque` di Java, dan kenapa `LinkedList` jarang jadi pilihan tepat di praktik

Mini Project: Implementasi custom `MyLinkedList`, `MyStack`, `MyQueue` dari nol (tanpa `java.util`), lengkap dengan unit test JUnit.

Status: Not Started

---

## Module 4 — Rekursi & Divide and Conquer

Description: Cara berpikir rekursif — salah satu blok bangunan paling penting untuk algoritma lanjutan (sorting, tree, graph, DP semuanya bertumpu di sini).

Lessons:

* [ ] Anatomi rekursi: base case, recursive case, call stack
* [ ] Rekursi vs iterasi: kapan rekursi lebih jernih, kapan berbahaya (stack overflow)
* [ ] Divide and Conquer sebagai paradigma: pecah — selesaikan — gabung
* [ ] Studi kasus: naive recursive Fibonacci (redundant calls) → memoization sebagai jembatan ke Modul 10 (DP)
* [ ] Common Mistakes: infinite recursion, missing base case, rekursi yang seharusnya iteratif (tail call di Java tidak dioptimasi otomatis)

Mini Project: Implementasi rekursif untuk 3 masalah klasik (factorial, power, Tower of Hanoi) + identifikasi mana yang perlu dioptimasi dan kenapa.

Status: Not Started

---

## Module 5 — Sorting Algorithms

Description: Algoritma sorting sebagai studi kasus pertama untuk membandingkan trade-off kompleksitas secara konkret.

Lessons:

* [ ] Sorting fondasi: bubble sort, selection sort, insertion sort — kompleksitas dan kapan (jarang) tetap relevan
* [ ] Merge sort — penerapan langsung divide and conquer dari Modul 4
* [ ] Quicksort — pivot selection, worst-case vs average-case, kenapa praktik sering lebih cepat dari merge sort meski worst-case lebih buruk
* [ ] Heapsort — pendahuluan konsep heap (didalami penuh di Modul 8)
* [ ] **TAOCP Deep Reference**: Knuth *TAOCP Vol. 3, "Sorting and Searching"* — analisis matematis mendalam berbagai algoritma sorting
* [ ] Production Discussion: bagaimana `Arrays.sort()` Java bekerja (dual-pivot quicksort untuk primitif, Timsort untuk objek) dan kenapa pilihan itu diambil

Mini Project: Implementasi merge sort & quicksort dari nol, benchmark keduanya dengan dataset besar (10k, 100k, 1M elemen) dan bandingkan hasil aktual vs prediksi Big-O.

Status: Not Started

---

## Module 6 — Searching & Hashing

Description: Dari pencarian linear ke hash table — struktur data yang paling sering dipakai tanpa disadari di balik `HashMap` dan database index.

Lessons:

* [ ] Linear search vs binary search — prasyarat data terurut, kompleksitas
* [ ] Hash function: sifat hash function yang baik, kolisi tidak terhindarkan
* [ ] Hash table dari nol: collision handling — chaining vs open addressing (linear/quadratic probing)
* [ ] Load factor & resizing hash table
* [ ] **TAOCP Deep Reference**: Knuth *TAOCP Vol. 3, Ch. 6.4* — hashing
* [ ] Production Discussion: cara kerja internal `HashMap` Java (bucket, treeification sejak Java 8), kaitan konseptual ke database indexing (didalami penuh di `courses/backend-fundamentals`)

Mini Project: Implementasi `MyHashMap` dari nol dengan chaining, uji dengan skenario high-collision, ukur degradasi performa.

Status: Not Started

---

## Module 7 — Trees & Balanced Trees

Description: Struktur data hierarkis — dari binary tree sederhana sampai jembatan konseptual ke B-Tree yang jadi tulang punggung database index.

Lessons:

* [ ] Binary tree & Binary Search Tree (BST): insert, delete, search, traversal (in-order, pre-order, post-order)
* [ ] Tree traversal dengan BFS vs DFS (level-order vs depth-first)
* [ ] Kenapa BST bisa terdegradasi jadi linked list (unbalanced) — pengantar konsep balancing
* [ ] AVL Tree & Red-Black Tree — konsep rotasi dan self-balancing (level konsep, tidak perlu implementasi rotasi penuh)
* [ ] B-Tree sebagai jembatan konseptual: kenapa database pakai B-Tree bukan BST biner untuk index (pointer ke pendalaman penuh di `courses/backend-fundamentals`)

Mini Project: Implementasi BST dari nol (insert, search, delete, 3 jenis traversal), plus tulis analisis singkat kenapa BST tanpa balancing berbahaya untuk data yang sudah terurut.

Status: Not Started

---

## Module 8 — Heaps & Priority Queues

Description: Struktur data untuk selalu tahu elemen "terpenting" secara efisien — dipakai di scheduling, event processing, dan jadi fondasi algoritma graph di modul berikutnya.

Lessons:

* [ ] Binary heap: min-heap vs max-heap, representasi pakai array
* [ ] Operasi heap: insert (sift-up), extract-min/max (sift-down), build-heap
* [ ] Heapsort revisited — sekarang dengan pemahaman penuh mekanisme heap
* [ ] Priority Queue: kapan dipakai di sistem nyata (job scheduling, event-driven simulation, Dijkstra di Modul 9)
* [ ] Production Discussion: `PriorityQueue` Java, use case di message processing/task scheduling

Mini Project: Implementasi min-heap dari nol, gunakan untuk membangun "Top-K Elements" solver dari dataset besar.

Status: Not Started

---

## Module 9 — Graphs & Graph Algorithms

Description: Struktur data untuk memodelkan relasi antar entitas — salah satu topik paling relevan untuk masalah backend nyata (dependency resolution, routing, social network).

Lessons:

* [ ] Representasi graph: adjacency list vs adjacency matrix, trade-off
* [ ] BFS & DFS pada graph (bukan cuma tree) — deteksi cycle, connected components
* [ ] Shortest path: Dijkstra's algorithm (pakai priority queue dari Modul 8), kenapa Bellman-Ford dibutuhkan untuk edge weight negatif
* [ ] Minimum Spanning Tree: Kruskal's & Prim's algorithm
* [ ] Real-world: dependency resolution (build tools, package manager), routing (maps), rekomendasi sosial (LinkedIn/Facebook graph)

Mini Project: Modelkan dependency graph antar service/module dari proyek nyata Anda (atau contoh yang diberikan), deteksi circular dependency pakai DFS, cari shortest path pakai Dijkstra.

Status: Not Started

---

## Module 10 — Algorithmic Paradigms: Greedy, Dynamic Programming, Backtracking

Description: Tiga cara berpikir untuk membangun algoritma dari nol ketika tidak ada algoritma standar yang langsung cocok — paradigma paling sering muncul di interview level Senior.

Lessons:

* [ ] Greedy algorithm: kapan pilihan lokal optimal menghasilkan solusi global optimal (dan kapan tidak — contoh kasus greedy gagal)
* [ ] Dynamic Programming dari nol: memoization (top-down) vs tabulation (bottom-up), identifikasi overlapping subproblems & optimal substructure
* [ ] DP klasik: knapsack problem, Longest Common Subsequence (LCS)
* [ ] Backtracking: N-Queens, Sudoku solver — eksplorasi solution space dengan pruning
* [ ] **TAOCP Deep Reference**: Knuth *TAOCP Vol. 4A, "Combinatorial Algorithms"* — pendalaman backtracking & combinatorial search

Mini Project: Selesaikan 3 masalah DP klasik (knapsack, LCS, coin change) dengan pendekatan top-down dan bottom-up, bandingkan trade-off keduanya.

Status: Not Started

---

## Module 11 — Complexity Theory & NP-Completeness (Pengantar)

Description: Kesadaran praktis kenapa sebagian masalah backend "sengaja" didekati dengan heuristik/approximation, bukan solusi optimal — level intuisi, bukan proof-heavy.

Lessons:

* [ ] P vs NP: definisi intuitif, kenapa ini pertanyaan terbuka paling terkenal di CS
* [ ] NP-Complete & NP-Hard: konsep reduction secukupnya untuk mengenali masalah NP-Complete di dunia nyata (TSP, knapsack 0/1, graph coloring)
* [ ] Kenapa kesadaran ini penting di backend: mengenali kapan solusi "optimal" tidak realistis, dan heuristik/approximation adalah keputusan sadar, bukan menyerah

Mini Project: Identifikasi 1 masalah dari pekerjaan Anda yang sebenarnya NP-Hard (scheduling, resource allocation, dll), jelaskan kenapa, dan usulkan pendekatan heuristik yang masuk akal.

Status: Not Started

---

## Module 12 — Randomized Algorithms & Random Numbers

Description: Peran randomisasi dalam algoritma — dari quickselect sampai hash table security — dengan pointer opsional ke sisi paling matematis dari TAOCP.

Lessons:

* [ ] Random number generation: pseudo-random vs true random, kenapa ini penting untuk keamanan (kaitan ke `standards/SECURITY_STANDARDS.md`)
* [ ] Randomized quickselect: mencari elemen ke-k tanpa sorting penuh, ekspektasi kompleksitas
* [ ] Randomization dalam hashing: mencegah hash flooding attack
* [ ] **TAOCP Deep Reference**: Knuth *TAOCP Vol. 2, "Seminumerical Algorithms"* — treatment matematis penuh soal random number generation

Mini Project: Implementasi randomized quickselect, bandingkan performa dengan sort-then-index untuk mencari median dari dataset besar.

Status: Not Started

---

## Module 13 — Interview Readiness: Pattern-Based Problem Solving

Description: Menghubungkan semua struktur data & algoritma yang sudah dipelajari ke pattern soal interview yang berulang — supaya soal baru terasa seperti variasi pattern yang sudah dikenal, bukan tantangan asing.

Lessons:

* [ ] Two pointers pattern & sliding window pattern
* [ ] Fast & slow pointers (cycle detection)
* [ ] Top-K / heap pattern, merge intervals pattern
* [ ] Strategi menghadapi soal baru: memetakan soal ke pattern + paradigma yang sudah dikuasai (Modul 1 computational thinking dipakai kembali di sini)
* [ ] Mock interview session: 2-3 soal live, dinilai dari proses berpikir bukan cuma solusi akhir

Mini Project: Selesaikan problem set campuran (8-10 soal, mencakup semua pattern di atas) dengan time-boxing ala interview asli.

Status: Not Started

---

## Module 14 — Capstone: Terapkan ke Sistem Backend Nyata

Description: Modul penutup yang memaksa semua yang dipelajari mendarat di kode produksi nyata, bukan berhenti di problem set.

Lessons:

* [ ] Identifikasi 1 fitur nyata dari pekerjaan Anda yang punya masalah performa atau desain data yang kurang tepat
* [ ] Analisis Big-O dari implementasi saat ini, diagnosis struktur data/algoritma yang lebih tepat
* [ ] Refactor dengan struktur data/algoritma yang tepat, tulis benchmark before/after
* [ ] Dokumentasi keputusan desain ala ADR (Architecture Decision Record): kenapa struktur data X dipilih, trade-off apa yang diterima

Portfolio Project: Refactor 1 fitur backend nyata dengan struktur data/algoritma yang lebih tepat, benchmark terukur, dan dokumentasi ADR — disimpan ke `portofolio/`.

Status: Not Started

---

# Enterprise Project

Description: (Diisi setelah Modul 14 selesai — akan disusun bersama saat mendekati akhir course, berdasarkan area kerja nyata Anda saat itu.)

Acceptance Criteria:

Status: Not Started

---

# Overall Progress

Module Completion: 0/14

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: 1.1 Apa itu computational thinking dan kenapa Jeannette Wing menyebutnya sepenting membaca-menulis-berhitung
