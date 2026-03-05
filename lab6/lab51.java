package network;

import java.util.*;

public class lab51 {

    static Scanner sc = new Scanner(System.in);


    public static void goBackN() {

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter window size: ");
        int window = sc.nextInt();

        int i = 0;

        while (i < frames) {

            System.out.println("\nSending Frames:");

            for (int j = i; j < i + window && j < frames; j++) {
                System.out.println("Frame " + j + " sent");
            }

            System.out.print("Enter last acknowledged frame: ");
            int ack = sc.nextInt();

            if (ack >= i) {
                i = ack + 1;
            } else {
                System.out.println("Error! Resending window...");
            }
        }

        System.out.println("\nAll frames transmitted successfully using Go-Back-N.");
    }

 
    public static void selectiveRepeat() {

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter window size: ");
        int window = sc.nextInt();

        boolean[] received = new boolean[frames];

        int base = 0;

        while (base < frames) {

            System.out.println("\nSending Frames in Window:");

            for (int i = base; i < base + window && i < frames; i++) {
                if (!received[i]) {
                    System.out.println("Frame " + i + " sent");
                }
            }

            System.out.print("Enter frame number received: ");
            int ack = sc.nextInt();

            if (ack < frames) {
                received[ack] = true;
                System.out.println("Frame " + ack + " acknowledged");
            }

            while (base < frames && received[base]) {
                base++;
            }
        }

        System.out.println("\nAll frames transmitted successfully using Selective Repeat.");
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n---- Sliding Window Protocol ----");
            System.out.println("1. Go-Back-N");
            System.out.println("2. Selective Repeat");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    goBackN();
                    break;

                case 2:
                    selectiveRepeat();
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }
}
