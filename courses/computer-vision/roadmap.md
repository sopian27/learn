# Course Roadmap

## Course Information

* Course Name: Computer Vision — dari 0 sampai Expert
* Category: Computer Vision / Applied Deep Learning
* Difficulty: Level 0 (belum pernah image processing/computer vision sama sekali) → Level 4 (Expert — mampu merancang & men-deploy sistem computer vision production end-to-end, dari classical filtering sampai vision-language model modern)
* Estimated Duration: 12 modules, ~4-5 lessons per modul (20-45 menit/lesson, sesuai `ai-los/AI-LOS.md`)
* Prerequisites: Programming solid (Java/Spring Boot dari root `CLAUDE.md`, tapi course ini praktik dengan Python — lihat alasan di Catatan Scope poin 1). **Direkomendasikan** (bukan wajib) sudah menyelesaikan `courses/ml-fundamentals/roadmap.md` Module 1 (Python untuk data science: NumPy/Pandas) dan Module 8-9 (neural network dasar, PyTorch dasar) sebagai pemanasan — kalau belum, Module 3 course ini menyediakan refresher singkat sebelum masuk CNN. **Tidak** perlu pengalaman computer vision sebelumnya.

---

# Catatan Scope (baca dulu sebelum approve)

1. **Kenapa Python, bukan Java/Spring Boot — meski stack utama repo ini Java.** Seluruh ekosistem tooling computer vision praktis (OpenCV, PyTorch/`torchvision`, Ultralytics YOLO, Hugging Face `transformers` untuk ViT/VLM, Albumentations) native Python — ini bukan preferensi, tapi kondisi industri: riset dan production CV di Google, Meta, Tesla, dan lab-lab computer vision lain berjalan di atas ekosistem Python yang sama. Course ini **tidak meninggalkan** stack utama repo — mengikuti pola yang sudah dipakai `courses/ml-fundamentals` dan `courses/llm`, Module 12 (Deployment) secara eksplisit membahas **"Production Discussion: Spring Boot Integration"**: model CV di-serve sebagai Python microservice (FastAPI) yang dipanggil dari backend Spring Boot, bukan CV logic ditulis ulang di Java.

2. **Hubungan dengan `courses/ml-fundamentals/roadmap.md`** (dicek penuh sebelum course ini dibuat, tidak ada duplikasi module): Module 9 course itu ("Deep Learning dengan PyTorch") menyentuh CNN untuk gambar hanya sebagai **satu bagian kecil** dari peta besar deep learning — satu mini project CNN classifier CIFAR-10, tanpa classical CV (edge detection/SIFT/HOG), tanpa object detection, segmentation, tracking, OCR, Vision Transformer, atau multimodal VLM. Course CV ini adalah **spesialisasi vertikal penuh** dari computer vision sebagai bidang tersendiri, dibangun **di atas** fondasi CNN yang sudah disentuh `ml-fundamentals` Module 8-9 — bukan mengulang, tapi memperluas horizontal (classical CV → deep CV → modern CV/multimodal) jauh melampaui cakupan satu modul di course itu. `ml-fundamentals` tetap prasyarat opsional yang direkomendasikan untuk fondasi neural network/PyTorch, bukan wajib (Module 3 course ini punya refresher singkat).

3. **Hubungan dengan `courses/neural-network/roadmap.md`**: course itu Module 8 menurunkan CNN **secara matematis penuh dari nol** (derivasi backpropagation convolution, implementasi NumPy tanpa autograd). Kedalaman derivasi matematis itu **sengaja tidak diulang** di sini — course CV ini applied-first: langsung pakai PyTorch/OpenCV/Ultralytics, fokus ke breadth topik CV modern dan pipeline lengkap (data → model → deploy), bukan pembuktian matematis. Kalau sudah/akan mengambil `neural-network`, Module 3-4 course ini akan terasa familiar dari sisi konsep tapi baru dari sisi tooling & breadth aplikasi — keduanya saling melengkapi (satu vertikal-matematis, satu horizontal-applied), tidak ada modul yang duplikat.

4. **Dicek juga `courses/android-development/roadmap.md`**: course itu murni Kotlin/Jetpack Compose/arsitektur/networking — tidak menyentuh computer vision atau ML on-device sama sekali. Tidak ada overlap, tidak perlu cross-reference lebih lanjut. Kalau di masa depan ada kebutuhan CV on-device (mis. kamera real-time di aplikasi Android), itu akan jadi extension module terpisah yang eksplisit merujuk balik ke Module 12 course ini (model export/optimization) — bukan bagian dari scope course ini sekarang.

5. **Urutan classical CV sebelum deep learning bukan pilihan sembarang.** Mengikuti pedagogi Stanford CS231n dan `PyImageSearch` (referensi #2-3 di bawah), Module 1-2 course ini sengaja dimulai dari image processing & feature engineering manual (filtering, edge detection, SIFT/HOG) **sebelum** CNN. Ini bukan sekadar sejarah — teknik ini masih dipakai production untuk kasus real-time/low-resource, dan membangun intuisi kenapa CNN "belajar" filter serupa secara otomatis lewat gradient descent, alih-alih terasa seperti sihir.

Referensi riset yang dipakai menyusun roadmap ini: [Stanford CS231n — Convolutional Neural Networks for Visual Recognition](https://cs231n.github.io/) (struktur progresif image classification → CNN → deteksi/segmentasi → self-supervised/CLIP, dipakai sebagai kerangka urutan Module 3-10), [CS231n course site](http://vision.stanford.edu/cs231n/) (silabus & jadwal terbaru), [PyImageSearch — Start Here / PyImageSearch University](https://pyimagesearch.com/start-here/) (pola pengajaran applied: OpenCV dasar → deep learning object detection → YOLO family, dipakai sebagai kerangka Module 1-2 dan 5), [OpenCV Official Documentation](https://docs.opencv.org/) (referensi API image processing & classical CV), [Meta AI — Segment Anything Model (SAM)](https://ai.meta.com/blog/segment-anything-model-3/) (foundation model segmentasi zero-shot, dipakai di Module 6), dan survei [Vision-Language Models: A Survey (arXiv 2510.09586)](https://arxiv.org/html/2510.09586v1) (evolusi arsitektur VLM 2023-2026, dipakai untuk kerangka Module 10).

---

# Learning Objectives

* [ ] Menguasai fondasi image processing: representasi digital gambar, filtering/konvolusi, color space, thresholding — dengan OpenCV sebagai tool utama
* [ ] Mahir classical computer vision: edge/corner detection, feature descriptor (SIFT/HOG/ORB), feature matching — termasuk kapan teknik ini masih relevan dibanding deep learning
* [ ] Memahami cara kerja CNN untuk visual data (convolution, pooling, receptive field) dan mampu membangun/melatih image classifier dengan PyTorch
* [ ] Mahir transfer learning & arsitektur CNN modern (ResNet, EfficientNet) untuk task classification dunia nyata
* [ ] Mampu membangun sistem object detection (YOLO) lengkap: labeling, training, evaluasi mAP
* [ ] Mampu membangun sistem image segmentation (U-Net, Mask R-CNN, SAM) untuk semantic & instance segmentation
* [ ] Mampu membangun pipeline object tracking video (SORT/DeepSORT/ByteTrack) di atas object detector
* [ ] Mampu membangun sistem OCR/document extraction (Tesseract/EasyOCR/PaddleOCR) untuk mengubah gambar jadi data terstruktur
* [ ] Memahami Vision Transformer (ViT) dan mampu membandingkannya dengan CNN pada task nyata
* [ ] Memahami & mampu memakai model multimodal vision-language (CLIP, VLM) untuk visual search dan image understanding
* [ ] Mampu membangun pipeline data & training CV production-grade: labeling terstruktur, augmentation aware bounding-box/mask, experiment tracking
* [ ] Mampu men-deploy model CV sebagai production system: export/optimization model, serving API, pertimbangan latency & monitoring khusus CV
* [ ] Menyelesaikan Capstone Project: sistem CV end-to-end nyata yang menggabungkan minimal 2 kapabilitas CV berbeda, terdeploy, dan terdokumentasi

---

# Course Modules

## Module 1 — Fondasi Image Processing & OpenCV

Description: Sebelum memakai OpenCV sebagai black box, pahami dulu bagaimana gambar direpresentasikan secara digital dan operasi dasar image processing yang mendasari hampir semua algoritma CV berikutnya — classical maupun deep learning.

Lessons:

* [ ] Setup environment CV Python (OpenCV, NumPy, Matplotlib, Jupyter) — kenapa Python jadi bahasa praktik CV (lihat Catatan Scope poin 1)
* [ ] Representasi gambar digital: pixel, channel (RGB/BGR/grayscale), color space (HSV, LAB) dan kapan masing-masing dipakai
* [ ] Operasi pixel-level: brightness/contrast, histogram & histogram equalization
* [ ] Konvolusi & filtering: kernel, Gaussian blur, median blur, sharpening — fondasi matematis yang sama dipakai CNN nanti
* [ ] Thresholding & morphological operations (erosion, dilation, opening/closing) untuk segmentasi sederhana

Mini Project: Image preprocessing pipeline dari nol dengan OpenCV (load → color space convert → denoise → threshold → morphological cleanup) diterapkan ke dataset gambar nyata (mis. dokumen kotor atau foto low-light), dibandingkan before/after.

Status: Not Started

---

## Module 2 — Classical Computer Vision: Edge, Corner & Feature Descriptors

Description: Sebelum deep learning mendominasi (pasca-2012), computer vision mengandalkan feature engineering manual yang dirancang tangan. Modul ini bukan sekadar sejarah — teknik ini masih dipakai production untuk kasus real-time/low-resource, dan jadi fondasi intuisi kenapa CNN "belajar" filter serupa secara otomatis.

Lessons:

* [ ] Edge detection: gradient image, operator Sobel/Prewitt, Canny edge detector — derivasi intuitif & implementasi OpenCV
* [ ] Corner & interest point detection: Harris corner detector, kenapa "sudut" adalah fitur yang stabil terhadap transformasi
* [ ] Feature descriptor klasik: SIFT (Scale-Invariant Feature Transform) — scale-space, keypoint, descriptor; HOG (Histogram of Oriented Gradients) untuk pedestrian detection
* [ ] Feature matching: brute-force matcher, FLANN, aplikasi ke image stitching/panorama
* [ ] ORB sebagai alternatif SIFT yang bebas paten & cepat untuk real-time (mis. robotics/AR)

Mini Project: Panorama stitching dari beberapa foto (feature detection SIFT/ORB → matching → homography → warping) dibangun dari komponen OpenCV, plus pedestrian detector sederhana dengan `HOGDescriptor` + Linear SVM bawaan OpenCV.

Status: Not Started

---

## Module 3 — Neural Network & CNN Refresher untuk Vision

Description: Titik transisi dari classical CV ke deep learning. Modul ini **bukan** pengantar neural network dari nol (itu domain `courses/ml-fundamentals` Module 8 atau `courses/neural-network` untuk derivasi penuh) — ini refresher applied yang menyambungkan konsep neural network ke representasi gambar, plus kenapa CNN mengungguli fully-connected network untuk data visual.

Lessons:

* [ ] Refresher singkat: neuron, layer, forward pass, training loop (pointer eksplisit ke `ml-fundamentals` Module 8 / `neural-network` Module 1-5 untuk yang butuh fondasi lebih dalam)
* [ ] Convolution & pooling di PyTorch: `nn.Conv2d`, `nn.MaxPool2d` — menyambungkan ke intuisi kernel/filter dari Module 1
* [ ] Receptive field & parameter sharing — kenapa CNN jauh lebih efisien dari MLP untuk gambar
* [ ] PyTorch data pipeline untuk vision: `Dataset`, `DataLoader`, `torchvision.transforms`
* [ ] Training loop image classifier end-to-end pertama dengan PyTorch (dataset kecil)

Mini Project: Image classifier CNN kecil (arsitektur `nn.Module` sendiri, bukan pretrained) untuk dataset sederhana (mis. CIFAR-10 subset atau custom 5-kelas), dibandingkan akurasinya dengan MLP biasa untuk membuktikan keunggulan CNN pada data visual.

Status: Not Started

---

## Module 4 — CNN Architectures & Transfer Learning

Description: Arsitektur CNN modern jarang dilatih dari nol di production — modul ini mempelajari evolusi arsitektur penting dan menguasai transfer learning, teknik paling praktis dan paling sering dipakai di industri.

Lessons:

* [ ] Evolusi arsitektur: LeNet → AlexNet → VGG → ResNet (residual connection, kenapa penting untuk network sangat dalam) → EfficientNet
* [ ] Batch normalization & dropout dalam konteks CNN modern (revisit singkat, fokus aplikasi praktis)
* [ ] Transfer learning: feature extraction vs fine-tuning, kapan memakai yang mana
* [ ] Model pretrained dari `torchvision.models`/`timm` — cara memilih backbone sesuai use case (akurasi vs kecepatan vs ukuran model)
* [ ] Data augmentation untuk image classification: flip, rotate, crop, color jitter, `Mixup`/`CutMix`

Mini Project: Fine-tuning model pretrained (ResNet/EfficientNet) untuk custom image classification task (dataset domain spesifik, mis. klasifikasi jenis sampah/tanaman/produk) — bandingkan hasil training dari nol vs transfer learning dari sisi akurasi & waktu training.

Status: Not Started

---

## Module 5 — Object Detection

Description: Dari "gambar ini kelas apa" (classification) ke "di mana dan apa saja objek di gambar ini" — salah satu task CV paling banyak dipakai di industri (retail, otomotif, security, robotics).

Lessons:

* [ ] Formulasi masalah: bounding box, anchor box, IoU (Intersection over Union), Non-Max Suppression (NMS)
* [ ] Two-stage detector: R-CNN → Fast R-CNN → Faster R-CNN — region proposal, kenapa akurat tapi lambat
* [ ] One-stage detector: YOLO (You Only Look Once) & SSD — trade-off kecepatan vs akurasi, kenapa mendominasi real-time detection
* [ ] Evaluation metric: mAP (mean Average Precision), precision-recall curve untuk object detection
* [ ] Praktik dengan Ultralytics YOLO (versi terbaru) — training custom object detector dengan dataset sendiri

Mini Project: Custom object detector dengan Ultralytics YOLO pada dataset yang di-label sendiri (mis. deteksi jenis kendaraan atau produk di rak toko) — dari labeling, training, sampai evaluasi mAP.

Status: Not Started

---

## Module 6 — Image Segmentation

Description: Lebih presisi dari bounding box — menentukan batas objek di level piksel. Dipakai luas di medical imaging, autonomous driving, dan editing foto otomatis.

Lessons:

* [ ] Semantic segmentation: FCN (Fully Convolutional Network), arsitektur encoder-decoder U-Net — kenapa cocok untuk medical imaging & segmentasi presisi tinggi
* [ ] Instance segmentation: Mask R-CNN — menggabungkan object detection + segmentation per instance
* [ ] Loss function khusus segmentasi: Dice loss, IoU loss — kenapa cross-entropy biasa kurang cocok untuk class imbalance di level piksel
* [ ] Segment Anything Model (SAM) — foundation model segmentasi zero-shot (Meta AI), prompt-based segmentation (point/box/text)
* [ ] Evaluation: pixel accuracy, mean IoU (mIoU)

Mini Project: Training U-Net untuk semantic segmentation pada dataset domain spesifik (mis. segmentasi jalan/bangunan dari citra satelit, atau organ dari citra medis publik), lalu bandingkan hasilnya dengan zero-shot SAM tanpa training sama sekali.

Status: Not Started

---

## Module 7 — Object Tracking

Description: Detection per-frame saja tidak cukup untuk video — modul ini mengajarkan cara mempertahankan identitas objek yang sama across frame, fondasi untuk video analytics & surveillance.

Lessons:

* [ ] Masalah tracking: kenapa deteksi ulang tiap frame tidak cukup (identity switch, occlusion)
* [ ] Tracking-by-detection: kombinasi detector (Module 5) + algoritma asosiasi antar-frame
* [ ] SORT (Simple Online and Realtime Tracking) — Kalman filter untuk prediksi posisi, Hungarian algorithm untuk asosiasi
* [ ] DeepSORT & ByteTrack — menambahkan appearance feature (re-identification) untuk tracking lebih robust saat occlusion
* [ ] Multi-object tracking metric: MOTA, ID switches

Mini Project: Pipeline video tracking end-to-end (YOLO dari Module 5 + ByteTrack/DeepSORT) pada video nyata (mis. tracking kendaraan di CCTV traffic atau orang di area publik), hitung jumlah objek unik yang melintas.

Status: Not Started

---

## Module 8 — Optical Character Recognition (OCR) & Document AI

Description: Task CV terapan yang sangat umum di industri (fintech, logistik, legal-tech): mengekstrak teks dari gambar/dokumen — dari OCR klasik sampai document understanding modern.

Lessons:

* [ ] Pipeline OCR: text detection vs text recognition sebagai dua sub-task terpisah
* [ ] Tesseract — engine OCR open-source tertua, kekuatan & batasannya (dokumen bersih, layout stabil)
* [ ] EasyOCR & PaddleOCR — OCR berbasis deep learning, multi-bahasa, lebih robust untuk scene text (foto jalan, produk) dibanding dokumen scan
* [ ] Preprocessing khusus OCR: deskewing, binarization, noise removal — menyambung ke teknik Module 1
* [ ] Document AI modern: layout-aware model (konsep, tanpa deep-dive) untuk structured extraction dari invoice/KTP/formulir

Mini Project: Sistem ekstraksi data dari dokumen nyata (mis. struk belanja, KTP, atau invoice) — preprocessing gambar, OCR dengan EasyOCR/PaddleOCR, lalu parsing hasil teks jadi structured data (JSON) dengan aturan/regex sederhana.

Status: Not Started

---

## Module 9 — Vision Transformers & Self-Supervised Learning

Description: Sejak 2020, arsitektur Transformer (yang mendominasi NLP) terbukti bekerja untuk gambar juga — modul ini mempelajari pergeseran paradigma dari CNN ke attention-based vision model.

Lessons:

* [ ] Vision Transformer (ViT): gambar sebagai sequence of patches, patch embedding, positional encoding untuk gambar — perbandingan langsung dengan self-attention di `courses/llm`/`courses/neural-network` Module 10
* [ ] ViT vs CNN: trade-off data efficiency & inductive bias — kapan ViT unggul (dataset besar) vs kapan CNN masih lebih baik (dataset kecil)
* [ ] Arsitektur turunan: Swin Transformer (hierarchical, windowed attention) untuk task dense prediction (detection/segmentation)
* [ ] DETR (Detection Transformer) — object detection tanpa NMS/anchor box, end-to-end dengan Transformer
* [ ] Self-supervised learning untuk vision: DINO — belajar representasi tanpa label, kenapa penting untuk domain dengan data terlabel terbatas

Mini Project: Fine-tuning ViT pretrained (Hugging Face `transformers`) untuk image classification task yang sama seperti Module 4, bandingkan akurasi/kecepatan training/jumlah data yang dibutuhkan vs CNN (ResNet/EfficientNet).

Status: Not Started

---

## Module 10 — Multimodal AI: Vision-Language Models

Description: Batas antara "computer vision" dan "NLP/LLM" semakin kabur — modul ini masuk ke model yang memahami gambar DAN teks sekaligus, arah riset & industri paling aktif saat ini (2025-2026).

Lessons:

* [ ] CLIP (Contrastive Language-Image Pretraining) — cara kerja contrastive learning menyatukan ruang embedding gambar & teks, zero-shot image classification
* [ ] Image captioning: dari CNN+RNN klasik sampai Transformer-based captioning
* [ ] Visual Question Answering (VQA) — konsep & use case
* [ ] Vision-Language Model (VLM) modern: arsitektur LLaVA-style (vision encoder + projector + LLM), overview model seperti GPT-4V/Claude vision/Qwen-VL — bagaimana vision encoder (ViT dari Module 9) "disambungkan" ke LLM (pointer eksplisit ke `courses/llm/roadmap.md`)
* [ ] Praktik: memakai VLM API (mis. Claude/GPT vision) untuk task applied (image understanding, document QA) vs kapan perlu fine-tune model open-source sendiri

Mini Project: Aplikasi kecil "visual search" — pakai CLIP untuk semantic image search (cari gambar dari query teks) dalam koleksi foto, plus eksperimen VQA sederhana dengan VLM API pada gambar domain spesifik.

Status: Not Started

---

## Module 11 — Dataset Handling & Training Pipeline Production-Grade

Description: Model CV sebaik data yang melatihnya — modul ini menutup gap antara "notebook yang jalan" dan pipeline data/training yang reliable & reproducible, sesuatu yang jarang dibahas tuntas di tutorial CV pemula.

Lessons:

* [ ] Sumber & strategi data collection untuk CV: dataset publik (COCO, ImageNet, Open Images) vs data custom, isu lisensi & bias dataset
* [ ] Data labeling: tools (CVAT, Roboflow, LabelImg), format anotasi (COCO JSON, YOLO format, Pascal VOC) dan konversi antar-format
* [ ] Data augmentation lanjutan dengan Albumentations — augmentation yang aware terhadap bounding box/mask (bukan cuma gambar mentah)
* [ ] Menangani class imbalance & data quality issue (mislabeled data, duplikat, distribusi tidak seimbang)
* [ ] Experiment tracking untuk CV (Weights & Biases/MLflow — pointer ke `courses/ml-fundamentals` Module 11 untuk MLOps mendalam, tidak diulang di sini), reproducibility (seed, dataset versioning)

Mini Project: Bangun pipeline data lengkap untuk salah satu proyek Module 5-8 (mis. object detector) — dari raw image, labeling terstruktur, split train/val/test yang benar (tanpa data leakage), augmentation, sampai eksperimen tercatat rapi di tracking tool.

Status: Not Started

---

## Module 12 — Deployment & Production Computer Vision Systems

Description: Modul penutup — membawa model CV dari notebook ke sistem yang benar-benar dipakai pengguna nyata, termasuk pertimbangan khusus CV (ukuran model, latency real-time, device constraint) yang tidak ada padanannya di deployment model teks biasa.

Lessons:

* [ ] Model export & optimization: ONNX, TensorRT, quantization — kenapa model CV sering perlu dikompres untuk inference cepat
* [ ] Serving model CV sebagai API dengan FastAPI, containerization dengan Docker
* [ ] Edge deployment overview: constraint device mobile/embedded (kamera CCTV, robot, drone) vs server-side inference
* [ ] Latency & throughput: batching strategy, GPU vs CPU inference trade-off, real-time video processing constraint (frame rate budget)
* [ ] Monitoring model CV di production: data drift visual (distribusi gambar berubah — pencahayaan, sudut kamera, musim), kapan perlu retraining

**Production Discussion: Spring Boot Integration** — pola serving model CV Python (FastAPI) sebagai internal microservice yang dipanggil dari backend Spring Boot (REST client, timeout budget untuk inference yang lebih berat dari teks, penanganan file upload gambar/video besar) — mengikuti pola yang sudah dibahas `courses/llm/roadmap.md` Module 4 & 9 dan `courses/ml-fundamentals/roadmap.md` Module 10, tidak diulang detail di sini.

Mini Project: Deploy salah satu model dari course ini (object detector/segmentation/OCR) sebagai REST API dengan FastAPI + Docker, ukur latency inference, lakukan optimasi (ONNX export atau quantization), dokumentasikan before/after.

Status: Not Started

---

# Capstone Project

Description: **Sistem Computer Vision End-to-End Nyata** — pilih 1 masalah applied nyata dan selesaikan penuh dari data mentah sampai sistem yang bisa diakses lewat API, menggabungkan minimal 2 kapabilitas CV berbeda dari course ini. Contoh pilihan (atau domain lain yang lebih personal):

1. **Retail analytics**: deteksi & tracking pelanggan di CCTV toko (Module 5 detection + Module 7 tracking) + OCR struk (Module 8) untuk dashboard analytics sederhana.
2. **Document intelligence**: OCR + segmentation layout dokumen (invoice/KTP/formulir) → structured data extraction, dengan VLM (Module 10) sebagai fallback untuk kasus ambigu.
3. **Visual search engine**: CLIP-based semantic image search (Module 10) atas koleksi gambar besar, dengan classification/tagging otomatis (Module 4) sebagai metadata tambahan.

Bangun end-to-end: (1) data collection & labeling terstruktur (Module 11), (2) minimal 2 model CV berbeda yang saling melengkapi (bukan cuma 1 model tunggal), (3) evaluasi rigorous dengan metric yang tepat per task (mAP/mIoU/akurasi sesuai jenis model), (4) deployment sebagai API (FastAPI + Docker, Module 12), (5) dokumentasi "Model Card" yang menjelaskan limitasi, bias dataset, dan skenario kegagalan.

Acceptance Criteria:

* Minimal 2 kapabilitas CV berbeda digabung dalam satu sistem (bukan single-model demo), dengan alasan arsitektur yang jelas kenapa digabung
* Setiap model dievaluasi dengan metric yang sesuai jenisnya (bukan hanya "terlihat bagus secara visual") dan hasil dibandingkan terhadap baseline sederhana
* Dataset (custom atau kombinasi publik+custom) dilabeli/disiapkan secara terstruktur dengan train/val/test split yang benar, tanpa data leakage
* Sistem berjalan sebagai API via Docker dan bisa menerima input gambar/video nyata, bukan hanya jalan di notebook
* Ada dokumentasi limitasi eksplisit: kondisi apa yang membuat sistem gagal (pencahayaan, sudut, oklusi, domain shift)
* Hasil disimpan di `portofolio/` sebagai portfolio-ready artifact

Status: Not Started

---

# Overall Progress

Module Completion: 0/12

Overall Completion: 0%

Current Module: menunggu approval

Current Lesson: -

Next Lesson: Module 1, Lesson 1 — Setup environment CV Python (OpenCV, NumPy, Matplotlib, Jupyter)
