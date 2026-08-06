Module 1 — Lesson 1: Tujuan Sebenarnya dari Code Review
1.Sebuah PR memperbaiki typo di log message yang tidak pernah dilihat user. Pilar mana yang paling relevan, dan kenapa?
pilar 2: knowledge transfer, karena untuk maintenance code di waktu yang akan datang, dan memudahkan transfer knowledge ke developer lain

2.Kenapa "LGTM" tanpa komentar apa pun bisa dianggap gagal di keempat pilar sekaligus?
karena review nya bisa bias dan subjective 

3.Kapan seorang expert reviewer sengaja menurunkan prioritas Pilar 4 (mentoring)?
ketika case itu berhubungan dengan masalah style code bukan arsitektural 

Module 1 — Lesson 2: Anatomy of a Bad Review vs a Great Review
1.Kenapa "silent approval" (approve padahal ragu) lebih berbahaya daripada memberi komentar yang salah?
karena bisa mendatangkan konflik kepada team nya. padahal itu bisa di hindari jika komunikasi lancar

2.Apa beda antara "Nit: ..." dan komentar tanpa label severity, dari sisi dampak ke kecepatan PR merge?
nit lebih cepat karena hanya style code, tapi kalau komentar tanpa label, harus di cek dulu, reviewer harus mengklasifikan label tersebut, jadi butuh waktu lebih lama. 