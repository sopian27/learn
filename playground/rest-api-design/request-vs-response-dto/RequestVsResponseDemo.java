import java.lang.reflect.RecordComponent;
import java.util.*;

// Lesson 4.2: satu DTO untuk request + response (masalah) vs dipisah (aman).
// Binder mini meniru Jackson: isi field apa pun yang ada di JSON masuk.
public class RequestVsResponseDemo {
    // Satu DTO dipakai dua arah.
    record UserDto(Long id, String name, String email, String password, String role) {}

    // Dipisah: input hanya yang boleh diisi client, output hanya yang boleh dilihat client.
    record CreateUserRequest(String name, String email, String password) {}
    record UserResponse(long id, String name, String email) {}

    static <T extends Record> T bind(Map<String, String> json, Class<T> type) throws Exception {
        RecordComponent[] cs = type.getRecordComponents();
        Class<?>[] types = new Class<?>[cs.length];
        Object[] vals = new Object[cs.length];
        for (int i = 0; i < cs.length; i++) {
            types[i] = cs[i].getType();
            String v = json.get(cs[i].getName()); // field tak dikenal diabaikan; field dikenal selalu diisi
            vals[i] = v == null ? null : types[i] == long.class || types[i] == Long.class ? (Object) Long.valueOf(v) : v;
        }
        return type.getDeclaredConstructor(types).newInstance(vals);
    }

    public static void main(String[] args) throws Exception {
        // Client jahat menambah role dan id ke body POST /users.
        Map<String, String> body = Map.of("name", "Rani", "email", "rani@mail.com",
                "password", "rahasia123", "role", "ADMIN", "id", "1");

        System.out.println("== 1. Satu DTO untuk request dan response ==");
        UserDto in = bind(body, UserDto.class);
        System.out.println("diterima server : " + in);
        System.out.println("   -> role=ADMIN dan id=1 ikut terisi (mass assignment)");
        System.out.println("dikirim balik   : " + in);
        System.out.println("   -> password ikut dikembalikan di response\n");

        System.out.println("== 2. Dipisah ==");
        CreateUserRequest req = bind(body, CreateUserRequest.class);
        System.out.println("diterima server : " + req);
        System.out.println("   -> role dan id dari client tidak punya tempat, hilang");
        UserResponse res = new UserResponse(42, req.name(), req.email()); // id dibuat server
        System.out.println("dikirim balik   : " + res);
        System.out.println("   -> id dari server, password tidak ada di tipe response");
    }
}
