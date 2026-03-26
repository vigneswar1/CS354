import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TCP Server started. Waiting for client...");

            Socket client = serverSocket.accept();
            System.out.println("Client connected: " + client.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            String code;
            while ((code = in.readLine()) != null) {
                System.out.println("Received: " + code);

                if (HammingUtils.checkHammingCode(code)) {
                    out.println("GOOD DATA");
                } else {
                    out.println("BAD DATA");
                }
            }

            client.close();
            System.out.println("Client disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
