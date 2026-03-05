package network;

import java.util.*;

public class lab41 {

    public static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for (int i = 0; i < 4; i++) {
            result = result * 256 + Integer.parseInt(parts[i]);
        }
        return result;
    }

    public static String longToIp(long ip) {
        return ((ip >> 24) & 255) + "." +
               ((ip >> 16) & 255) + "." +
               ((ip >> 8) & 255) + "." +
               (ip & 255);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Network Address: ");
        String networkIP = sc.nextLine();

        System.out.print("Enter Prefix Length (e.g. 24): ");
        int prefix = sc.nextInt();

        System.out.print("Enter Number of Subnet Bits to Borrow: ");
        int subnetBits = sc.nextInt();

        int newPrefix = prefix + subnetBits;

        int numberOfSubnets = (int) Math.pow(2, subnetBits);
        int hostBits = 32 - newPrefix;
        int hostsPerSubnet = (int) Math.pow(2, hostBits) - 2;

        long baseIP = ipToLong(networkIP);

        long subnetSize = (long) Math.pow(2, hostBits);

        System.out.println("\nNumber of Subnets: " + numberOfSubnets);
        System.out.println("Hosts per Subnet: " + hostsPerSubnet);
        System.out.println("New Prefix: /" + newPrefix);

        long mask = 0xffffffffL << (32 - newPrefix);
        System.out.println("Subnet Mask: " + longToIp(mask));

        System.out.println("\nForwarding Table:\n");

        System.out.printf("%-15s %-15s %-15s %-15s %-15s\n",
                "Subnet Address", "First Host", "Last Host", "Broadcast", "Hosts");

        for (int i = 0; i < numberOfSubnets; i++) {

            long subnetAddress = baseIP + (i * subnetSize);
            long firstHost = subnetAddress + 1;
            long broadcast = subnetAddress + subnetSize - 1;
            long lastHost = broadcast - 1;

            System.out.printf("%-15s %-15s %-15s %-15s %-15d\n",
                    longToIp(subnetAddress),
                    longToIp(firstHost),
                    longToIp(lastHost),
                    longToIp(broadcast),
                    hostsPerSubnet);
        }

        sc.close();
    }
}
