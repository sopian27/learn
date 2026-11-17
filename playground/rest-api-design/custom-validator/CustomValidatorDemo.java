import jakarta.validation.*;
import jakarta.validation.constraints.*;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.util.*;

// Lesson 5.2: custom constraint untuk rule yang tidak bisa dijawab anotasi bawaan.
public class CustomValidatorDemo {

    // 1) Field-level: format SKU + check digit (huruf 3 + angka 4, angka terakhir = jumlah 3 angka awal mod 10).
    @Target({ElementType.FIELD, ElementType.METHOD}) @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = SkuValidator.class)
    @interface ValidSku {
        String message() default "sku tidak valid (format ABC1234, digit terakhir = check digit)";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class SkuValidator implements ConstraintValidator<ValidSku, String> {
        public boolean isValid(String v, ConstraintValidatorContext c) {
            if (v == null) return true; // null urusan @NotNull, sama seperti constraint bawaan
            if (!v.matches("[A-Z]{3}[0-9]{4}")) return false;
            int sum = v.charAt(3) + v.charAt(4) + v.charAt(5) - 3 * '0';
            return sum % 10 == v.charAt(6) - '0';
        }
    }

    // 2) Class-level: aturan lintas field (from < to). Error ditempel ke field "deliveryDate".
    @Target(ElementType.TYPE) @Retention(RetentionPolicy.RUNTIME)
    @Constraint(validatedBy = WindowValidator.class)
    @interface ValidDeliveryWindow {
        String message() default "deliveryDate harus setelah orderDate";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

    public static class WindowValidator implements ConstraintValidator<ValidDeliveryWindow, OrderRequest> {
        public boolean isValid(OrderRequest r, ConstraintValidatorContext c) {
            if (r.orderDate() == null || r.deliveryDate() == null) return true;
            if (r.deliveryDate().isAfter(r.orderDate())) return true;
            c.disableDefaultConstraintViolation();
            c.buildConstraintViolationWithTemplate(c.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("deliveryDate").addConstraintViolation();
            return false;
        }
    }

    @ValidDeliveryWindow
    record OrderRequest(@NotNull @ValidSku String sku, @NotNull LocalDate orderDate, @NotNull LocalDate deliveryDate) {}

    static void check(String label, Validator v, OrderRequest req) {
        System.out.println("== " + label);
        Set<ConstraintViolation<OrderRequest>> errs = v.validate(req);
        if (errs.isEmpty()) System.out.println("  lolos");
        errs.stream().map(e -> "  [" + e.getPropertyPath() + "] " + e.getMessage()).sorted().forEach(System.out::println);
    }

    public static void main(String[] args) {
        try (ValidatorFactory f = Validation.buildDefaultValidatorFactory()) {
            Validator v = f.getValidator();
            LocalDate d = LocalDate.of(2026, 11, 17);
            check("valid (ABC1236: 1+2+3=6)", v, new OrderRequest("ABC1236", d, d.plusDays(2)));
            check("check digit salah", v, new OrderRequest("ABC1239", d, d.plusDays(2)));
            check("kirim sebelum order", v, new OrderRequest("ABC1236", d, d.minusDays(1)));
            check("dua-duanya salah", v, new OrderRequest("abc", d, d));
            // Stok cukup? Butuh DB: bukan tugas constraint. Itu 409/422 dari service.
        }
    }
}
