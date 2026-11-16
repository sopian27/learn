import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * Lesson 1.1 companion: HTTP itu teks. REST cuma "kesepakatan" di atas teks ini.
 * Run: java HttpAnatomyDemo.java
 */
public class HttpAnatomyDemo {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(0), 0);
        server.createContext("/orders/42", ex -> {
            byte[] body = "{\"id\":42,\"status\":\"PAID\"}".getBytes(StandardCharsets.UTF_8);
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            ex.getResponseBody().write(body);
            ex.close();
        });
        server.start();
        int port = server.getAddress().getPort();

        // Request ditulis manual lewat socket: tanpa library HTTP sama sekali.
        String request = "GET /orders/42 HTTP/1.1\r\n"      // start line: method, target, version
                + "Host: localhost:" + port + "\r\n"         // header
                + "Accept: application/json\r\n"             // header
                + "Connection: close\r\n"
                + "\r\n";                                    // baris kosong = akhir header, GET tanpa body

        try (Socket s = new Socket("localhost", port)) {
            s.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8));
            System.out.println("=== REQUEST (yang gua kirim) ===");
            System.out.println(request.replace("\r\n", "\r\n\n"));
            System.out.println("=== RESPONSE (mentah) ===");
            System.out.println(new String(s.getInputStream().readAllBytes(), StandardCharsets.UTF_8)
                    .replace("\r\n", "\r\n\n"));
        }
        server.stop(0);
    }
}
