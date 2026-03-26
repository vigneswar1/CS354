import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverAddress = "127.0.0.1";
        int port = 5500;

        try (DatagramSocket socket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            System.out.println("UDP Client started. Send 4-bit data (type 'exit' to quit).");

            while (true) {
                System.out.print("Enter 4-bit data: ");
                String data = sc.nextLine().trim();

                if (data.equalsIgnoreCase("exit")) break;
                if (!data.matches("[01]{4}")) {
                    System.out.println("Invalid input! Must be 4 bits (0 or 1).");
                    continue;
                }

                String hammingCode = HammingUtils.generateHammingCode(data);
                System.out.println("Sending Hamming code: " + hammingCode);

                byte[] sendData = hammingCode.getBytes();
                InetAddress serverIP = InetAddress.getByName(serverAddress);

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, port);
                socket.send(sendPacket);

                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server response: " + response);
            }

            System.out.println("Client exiting.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
