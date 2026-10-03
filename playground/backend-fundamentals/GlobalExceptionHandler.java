import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // =========================================================
    // 400 — Bean Validation gagal pada @RequestBody
    // =========================================================

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        List<FieldErrorDetail> fieldErrors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fe -> new FieldErrorDetail(
                                fe.getField(),
                                fe.getDefaultMessage(),
                                null
                        ))
                        .toList();

        ApiError body = ApiError.of(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_FAILED",
                "Request tidak valid, cek field errors",
                req.getRequestURI(),
                traceId,
                fieldErrors
        );

        log.warn(
                "Validation failed traceId={} path={} errors={}",
                traceId,
                req.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity.badRequest().body(body);
    }

    // =========================================================
    // 400 — @Validated pada @RequestParam / @PathVariable
    // =========================================================

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        List<FieldErrorDetail> fieldErrors =
                ex.getConstraintViolations()
                        .stream()
                        .map(v -> new FieldErrorDetail(
                                v.getPropertyPath().toString(),
                                v.getMessage(),
                                null
                        ))
                        .toList();

        ApiError body = ApiError.of(
                HttpStatus.BAD_REQUEST,
                "VALIDATION_FAILED",
                "Request tidak valid, cek field errors",
                req.getRequestURI(),
                traceId,
                fieldErrors
        );

        log.warn(
                "Constraint violation traceId={} path={} errors={}",
                traceId,
                req.getRequestURI(),
                fieldErrors
        );

        return ResponseEntity.badRequest().body(body);
    }

    // =========================================================
    // 400 — PathVariable / RequestParam gagal dikonversi
    // Contoh: /products/abc padahal productId = Long
    // =========================================================

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        ApiError body = ApiError.of(
                HttpStatus.BAD_REQUEST,
                "INVALID_PARAMETER",
                "Parameter request tidak valid",
                req.getRequestURI(),
                traceId,
                List.of()
        );

        log.warn(
                "Invalid parameter traceId={} path={} parameter={}",
                traceId,
                req.getRequestURI(),
                ex.getName()
        );

        return ResponseEntity.badRequest().body(body);
    }

    // =========================================================
    // 400 — JSON request body tidak valid / malformed
    // =========================================================

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadableMessage(
            HttpMessageNotReadableException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        ApiError body = ApiError.of(
                HttpStatus.BAD_REQUEST,
                "MALFORMED_REQUEST",
                "Request body tidak valid",
                req.getRequestURI(),
                traceId,
                List.of()
        );

        log.warn(
                "Malformed request traceId={} path={}",
                traceId,
                req.getRequestURI()
        );

        return ResponseEntity.badRequest().body(body);
    }

    // =========================================================
    // 400 — Idempotency-Key wajib
    // =========================================================

    @ExceptionHandler(MissingIdempotencyKeyException.class)
    public ResponseEntity<ApiError> handleMissingIdempotencyKey(
            MissingIdempotencyKeyException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.badRequest()
                .body(ApiError.of(
                        HttpStatus.BAD_REQUEST,
                        "IDEMPOTENCY_KEY_REQUIRED",
                        "Header Idempotency-Key wajib disertakan",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 404 — Product tidak ditemukan
    // =========================================================

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(
            ProductNotFoundException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(
                        HttpStatus.NOT_FOUND,
                        "PRODUCT_NOT_FOUND",
                        ex.getMessage(),
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 404 — Category tidak ditemukan
    // =========================================================

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(
            CategoryNotFoundException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(
                        HttpStatus.NOT_FOUND,
                        "CATEGORY_NOT_FOUND",
                        ex.getMessage(),
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 404 — Stock tidak ditemukan
    // =========================================================

    @ExceptionHandler(StockNotFoundException.class)
    public ResponseEntity<ApiError> handleStockNotFound(
            StockNotFoundException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(
                        HttpStatus.NOT_FOUND,
                        "STOCK_NOT_FOUND",
                        ex.getMessage(),
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 404 — Generic resource tidak ditemukan
    // =========================================================

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(
                        HttpStatus.NOT_FOUND,
                        "RESOURCE_NOT_FOUND",
                        ex.getMessage(),
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 404 — Endpoint/resource path tidak ditemukan
    // Spring 6
    // =========================================================

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFound(
            NoResourceFoundException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        log.warn(
                "Resource not found traceId={} path={}",
                traceId,
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiError.of(
                        HttpStatus.NOT_FOUND,
                        "RESOURCE_NOT_FOUND",
                        "Resource tidak ditemukan",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 405 — HTTP method tidak didukung
    // Contoh: PATCH endpoint yang hanya menyediakan PUT
    // =========================================================

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        log.warn(
                "Method not supported traceId={} path={} method={}",
                traceId,
                req.getRequestURI(),
                ex.getMethod()
        );

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiError.of(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        "METHOD_NOT_ALLOWED",
                        "HTTP method tidak didukung untuk resource ini",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 409 — Business/resource conflict
    // =========================================================

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiError> handleConflict(
            ResourceConflictException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.of(
                        HttpStatus.CONFLICT,
                        "RESOURCE_CONFLICT",
                        ex.getMessage(),
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 409 — Database constraint conflict
    //
    // Missing category seharusnya dicek di service terlebih dahulu
    // dan menghasilkan CategoryNotFoundException (404).
    //
    // Handler ini adalah fallback jika database constraint tetap gagal.
    // =========================================================

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(
            DataIntegrityViolationException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        log.warn(
                "Data integrity violation traceId={} path={}",
                traceId,
                req.getRequestURI(),
                ex
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.of(
                        HttpStatus.CONFLICT,
                        "DATA_INTEGRITY_CONFLICT",
                        "Request tidak dapat diproses karena melanggar constraint data",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 409 — Optimistic locking race
    // =========================================================

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiError> handleOptimisticLock(
            ObjectOptimisticLockingFailureException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        log.warn(
                "Optimistic locking conflict traceId={} path={}",
                traceId,
                req.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiError.of(
                        HttpStatus.CONFLICT,
                        "OPTIMISTIC_LOCK_CONFLICT",
                        "Resource berubah oleh request lain. Silakan ambil data terbaru dan coba lagi.",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 412 — If-Match tidak sesuai ETag terbaru
    // =========================================================

    @ExceptionHandler(PreconditionFailedException.class)
    public ResponseEntity<ApiError> handlePreconditionFailed(
            PreconditionFailedException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body(ApiError.of(
                        HttpStatus.PRECONDITION_FAILED,
                        "PRECONDITION_FAILED",
                        "Resource sudah berubah. Ambil representasi terbaru sebelum melakukan update.",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 415 — Content-Type tidak didukung
    // =========================================================

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ApiError> handleMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        log.warn(
                "Unsupported media type traceId={} path={} contentType={}",
                traceId,
                req.getRequestURI(),
                ex.getContentType()
        );

        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(ApiError.of(
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                        "UNSUPPORTED_MEDIA_TYPE",
                        "Content-Type request tidak didukung",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 428 — If-Match wajib
    // =========================================================

    @ExceptionHandler(PreconditionRequiredException.class)
    public ResponseEntity<ApiError> handlePreconditionRequired(
            PreconditionRequiredException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED)
                .body(ApiError.of(
                        HttpStatus.PRECONDITION_REQUIRED,
                        "PRECONDITION_REQUIRED",
                        "Header If-Match wajib disertakan",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 422 — Idempotency-Key dipakai ulang untuk request berbeda
    // =========================================================

    @ExceptionHandler(IdempotencyKeyReuseException.class)
    public ResponseEntity<ApiError> handleIdempotencyKeyReuse(
            IdempotencyKeyReuseException ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ApiError.of(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "IDEMPOTENCY_KEY_REUSED",
                        "Idempotency-Key sudah digunakan untuk request yang berbeda",
                        req.getRequestURI(),
                        traceId,
                        List.of()
                ));
    }

    // =========================================================
    // 500 — Unexpected exception
    // =========================================================

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleUnexpected(
            Exception ex,
            HttpServletRequest req) {

        String traceId = UUID.randomUUID().toString();

        // Detail exception hanya masuk log.
        // Jangan expose ex.getMessage() kepada client.
        log.error(
                "Unexpected error traceId={} path={}",
                traceId,
                req.getRequestURI(),
                ex
        );

        ApiError body = ApiError.of(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "INTERNAL_ERROR",
                "Terjadi kesalahan pada server, tim kami sudah diberi tahu",
                req.getRequestURI(),
                traceId,
                List.of()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }
}