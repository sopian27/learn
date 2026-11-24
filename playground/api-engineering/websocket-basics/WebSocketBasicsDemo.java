import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

// Demo Lesson 4.1: handshake HTTP Upgrade -> 101, lalu frame dua arah di satu koneksi TCP.
// Tanpa library: server dan client ditulis manual supaya bytes-nya kelihatan.
public class WebSocketBasicsDemo {
    static final String GUID = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"; // konstanta dari RFC 6455

    static String accept(String key) throws Exception {
        byte[] sha1 = MessageDigest.getInstance("SHA-1").digest((key + GUID).getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(sha1);
    }

    static String readLine(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = in.read()) != '\n') { if (c == -1) break; if (c != '\r') sb.append((char) c); }
        return sb.toString();
    }

    // Kirim frame teks. Server -> client TIDAK di-mask, client -> server WAJIB di-mask.
    static void send(OutputStream out, String text, boolean mask) throws IOException {
        byte[] p = text.getBytes(StandardCharsets.UTF_8);
        out.write(0x81);                                  // FIN=1, opcode=1 (text)
        out.write((mask ? 0x80 : 0) | p.length);          // bit MASK + panjang (demo ini < 126 byte)
        if (mask) {
            byte[] key = {1, 2, 3, 4};
            out.write(key);
            for (int i = 0; i < p.length; i++) p[i] ^= key[i % 4];
        }
        out.write(p);
        out.flush();
    }

    static String receive(InputStream in, String who) throws IOException {
        int b0 = in.read(), b1 = in.read();
        boolean masked = (b1 & 0x80) != 0;
        int len = b1 & 0x7F;
        byte[] key = masked ? in.readNBytes(4) : null;
        byte[] p = in.readNBytes(len);
        if (masked) for (int i = 0; i < len; i++) p[i] ^= key[i % 4];
        System.out.printf("[%s] frame: FIN=%d opcode=%d masked=%b len=%d%n", who, b0 >> 7, b0 & 0x0F, masked, len);
        return new String(p, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        try (ServerSocket ss = new ServerSocket(0)) {
            Thread server = new Thread(() -> {
                try (Socket s = ss.accept()) {
                    InputStream in = s.getInputStream();
                    OutputStream out = s.getOutputStream();
                    String line, key = null;
                    while (!(line = readLine(in)).isEmpty())
                        if (line.startsWith("Sec-WebSocket-Key:")) key = line.substring(18).trim();
                    out.write(("HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
                            + "Sec-WebSocket-Accept: " + accept(key) + "\r\n\r\n").getBytes());
                    send(out, "ORD-1 status=PAID", false);                 // server PUSH tanpa diminta client
                    System.out.println("[server] terima: " + receive(in, "server"));
                } catch (Exception e) { e.printStackTrace(); }
            });
            server.start();

            try (Socket c = new Socket("localhost", ss.getLocalPort())) {
                String key = Base64.getEncoder().encodeToString("demo-key-16bytes!".getBytes());
                OutputStream out = c.getOutputStream();
                InputStream in = c.getInputStream();
                out.write(("GET /ws/orders HTTP/1.1\r\nHost: localhost\r\nUpgrade: websocket\r\nConnection: Upgrade\r\n"
                        + "Sec-WebSocket-Key: " + key + "\r\nSec-WebSocket-Version: 13\r\n\r\n").getBytes());
                System.out.println("[client] status  : " + readLine(in));
                String line, got = null;
                while (!(line = readLine(in)).isEmpty())
                    if (line.startsWith("Sec-WebSocket-Accept:")) got = line.substring(21).trim();
                System.out.println("[client] accept valid: " + got.equals(accept(key)));
                System.out.println("[client] terima: " + receive(in, "client"));
                send(out, "ack ORD-1", true);                              // client kirim di koneksi yang SAMA
            }
            server.join();
        }
        System.out.println("Polling 1 detik selama 1 menit = 60 request HTTP. WebSocket = 1 handshake + frame seperlunya.");
    }
}
