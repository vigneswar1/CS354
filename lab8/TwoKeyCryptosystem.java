import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class TwoKeyCryptosystem {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            // Step 1: Generate Bob's Key Pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair bobKeyPair = keyGen.generateKeyPair();
            PublicKey bobPublicKey = bobKeyPair.getPublic();
            PrivateKey bobPrivateKey = bobKeyPair.getPrivate();

            System.out.println("Bob's Public Key (Base64): " + Base64.getEncoder().encodeToString(bobPublicKey.getEncoded()));
            // Step 2: Take Alice's Message from User
            System.out.print("\nEnter the message Alice wants to send to Bob: ");
            String message = scanner.nextLine();
            // Encrypt the message using Bob's public key
            Cipher encryptCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, bobPublicKey);
            byte[] encryptedMessage = encryptCipher.doFinal(message.getBytes());

            System.out.println("\nEncrypted Message (Base64): " + Base64.getEncoder().encodeToString(encryptedMessage));
            // Step 3: Bob Decrypts the Message
            Cipher decryptCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, bobPrivateKey);
            byte[] decryptedMessage = decryptCipher.doFinal(encryptedMessage);

            System.out.println("\nDecrypted Message at Bob's Side: " + new String(decryptedMessage));

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
