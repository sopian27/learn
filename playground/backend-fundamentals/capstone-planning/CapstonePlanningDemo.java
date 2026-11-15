import java.util.*;

// Jalankan: java CapstonePlanningDemo.java
// Demo milih domain capstone: tiap kandidat dicek, pilar mana yang MEMAKSA dipakai secara natural.
// Skor karangan buat contoh (bukan hasil riset): 1 = dipaksa natural, 0 = harus ditempel-tempel.
public class CapstonePlanningDemo {

    static final List<String> PILARS = List.of(
            "concurrency", "database+index", "reporting query", "locking",
            "caching", "security", "resiliency", "observability");

    public static void main(String[] args) {
        Map<String, int[]> kandidat = new LinkedHashMap<>();
        kandidat.put("Blog CRUD",              new int[]{0, 1, 0, 0, 0, 1, 0, 0});
        kandidat.put("Inventory",              new int[]{1, 1, 1, 1, 0, 1, 0, 1});
        kandidat.put("Booking Slot + Payment", new int[]{1, 1, 1, 1, 1, 1, 1, 1});

        System.out.println("Pilar yang dipaksa natural oleh domain:\n");
        for (var e : kandidat.entrySet()) {
            int total = Arrays.stream(e.getValue()).sum();
            System.out.printf("%-24s %d/%d%n", e.getKey(), total, PILARS.size());
            for (int i = 0; i < PILARS.size(); i++) {
                if (e.getValue()[i] == 0) System.out.println("    kosong -> " + PILARS.get(i) + " (bakal 'ditempel')");
            }
        }

        // Kenapa Booking Slot menang: tiap pilar punya alasan bisnis, bukan dekorasi.
        System.out.println("\nPeta pilar -> alasan di Booking Slot:");
        System.out.println("  locking/concurrency : dua orang rebutan slot yang sama (double booking)");
        System.out.println("  reporting query     : okupansi per hari/lapangan (window function)");
        System.out.println("  caching             : cek ketersediaan slot dibaca jauh lebih sering dari ditulis");
        System.out.println("  resiliency          : payment gateway eksternal bisa timeout/gagal");
        System.out.println("  security            : user cuma boleh batalin booking miliknya sendiri");
        System.out.println("  observability       : 'kenapa booking gue gagal?' harus bisa dijawab dari log/trace");
    }
}
