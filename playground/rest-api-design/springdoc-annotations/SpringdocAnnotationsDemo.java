import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Lesson 10.2 demo: mini-springdoc tanpa dependency.
 * Anotasi mirip @Operation/@ApiResponse; reflection membaca controller lalu mencetak "spec".
 * Intinya: dokumentasi = hasil baca kode, jadi apa yang lupa dianotasi hilang dari spec.
 */
public class SpringdocAnnotationsDemo {

    @Retention(RetentionPolicy.RUNTIME) @interface Operation { String summary(); }
    @Retention(RetentionPolicy.RUNTIME) @Repeatable(ApiResponses.class) @interface ApiResponse { int code(); String description(); }
    @Retention(RetentionPolicy.RUNTIME) @interface ApiResponses { ApiResponse[] value(); }
    @Retention(RetentionPolicy.RUNTIME) @interface Route { String method(); String path(); }

    static class OrderController {
        @Route(method = "GET", path = "/v1/orders/{id}")
        @Operation(summary = "Get order by id")
        @ApiResponse(code = 200, description = "Order ditemukan")
        @ApiResponse(code = 404, description = "Order tidak ada (application/problem+json)")
        public void getOrder() {}

        @Route(method = "POST", path = "/v1/orders")
        @Operation(summary = "Create order")
        @ApiResponse(code = 201, description = "Order dibuat")
        // 422 sengaja TIDAK dianotasi: controller aslinya bisa balikin 422, tapi spec diam.
        public void createOrder() {}
    }

    public static void main(String[] args) {
        // Controller mana yang punya error 422 di runtime (kenyataan), dibanding yang terdokumentasi.
        Map<String, Set<Integer>> actual = Map.of("POST /v1/orders", Set.of(201, 422));

        for (Method m : OrderController.class.getDeclaredMethods()) {
            Route r = m.getAnnotation(Route.class);
            if (r == null) continue;
            String key = r.method() + " " + r.path();
            Set<Integer> documented = new TreeSet<>();
            System.out.println(key + "  -> " + m.getAnnotation(Operation.class).summary());
            for (ApiResponse a : m.getAnnotationsByType(ApiResponse.class)) {
                documented.add(a.code());
                System.out.println("   " + a.code() + " " + a.description());
            }
            Set<Integer> missing = new TreeSet<>(actual.getOrDefault(key, Set.of()));
            missing.removeAll(documented);
            if (!missing.isEmpty()) System.out.println("   !! response nyata tapi TIDAK terdokumentasi: " + missing);
        }
    }
}
