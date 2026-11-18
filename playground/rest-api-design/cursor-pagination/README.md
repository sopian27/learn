# Cursor Pagination Demo (Lesson 7.2)

Run: `java CursorPaginationDemo.java`

Look for: order id 11 masuk di antara page 0 dan page 1, tapi page 1 tetap `[7, 6, 5]` tanpa duplikat (bandingkan dengan `../offset-pagination`). Perhatikan juga cursor opaque (Base64) dan trik ambil `size+1` baris untuk tahu masih ada halaman berikutnya tanpa `COUNT(*)`.
