import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.util.*;

// Lesson 5.1: constraint dasar pada request DTO. Pakai Hibernate Validator asli, tanpa Spring.
public class BeanValidationDemo {

    record CreateOrderRequest(
            @NotNull(message = "customerId wajib diisi") Long customerId,
            @NotBlank(message = "shippingAddress tidak boleh kosong") @Size(max = 100, message = "shippingAddress maksimal 100 karakter") String shippingAddress,
            @Email(message = "contactEmail format tidak valid") String contactEmail,
            @Pattern(regexp = "^[+]?[0-9]{9,15}$", message = "contactPhone harus 9-15 digit, boleh diawali +") String contactPhone,
            @NotNull @Min(value = 1, message = "quantity minimal 1") Integer quantity) {}

    static void check(String label, Validator v, CreateOrderRequest req) {
        System.out.println("== " + label);
        Set<ConstraintViolation<CreateOrderRequest>> errs = v.validate(req);
        if (errs.isEmpty()) System.out.println("  lolos");
        errs.stream().map(e -> "  " + e.getPropertyPath() + ": " + e.getMessage()).sorted().forEach(System.out::println);
    }

    public static void main(String[] args) {
        try (ValidatorFactory f = Validation.buildDefaultValidatorFactory()) {
            Validator v = f.getValidator();
            // Bug lama: cuma @NotNull -> "" dan "   " lolos, alamat kosong masuk DB.
            check("valid", v, new CreateOrderRequest(7L, "Jl. Merdeka 1", "rani@mail.com", "+628123456789", 2));
            check("semua salah", v, new CreateOrderRequest(null, "   ", "bukan-email", "08-abc", 0));
            // null dianggap valid oleh @Email/@Pattern/@Size: field opsional lolos, mau wajib harus + @NotNull.
            check("opsional kosong (null)", v, new CreateOrderRequest(7L, "Jl. Merdeka 1", null, null, 1));
            check("string kosong beda dengan null", v, new CreateOrderRequest(7L, "Jl. Merdeka 1", "", "", 1));
        }
    }
}
