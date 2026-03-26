import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        int port = 5500;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("UDP Server started on port " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String code = new String(packet.getData(), 0, packet.getLength()).trim();
                
                if (code.length() == 7 && code.matches("[01]+")) {
                    String response = HammingUtils.checkHammingCode(code) ? "GOOD DATA" : "BAD DATA";
                    byte[] responseBytes = response.getBytes();

                    DatagramPacket responsePacket = new DatagramPacket(
                            responseBytes, responseBytes.length,
                            packet.getAddress(), packet.getPort()
                    );
                    socket.send(responsePacket);
                    System.out.println("Processed code: " + code + " -> " + response);
                } else {
                    System.out.println("Ignored invalid packet: " + code);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
