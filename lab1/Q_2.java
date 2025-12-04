import java.util.Scanner;

class Q_2 {

    public static char[] sumBits(char a, char b, char c) {
        int x = a - '0';
        int y = b - '0';
        int z = c - '0';
        int t = x + y + z;
        char sum = (char)((t % 2) + '0');
        char carry = (char)((t / 2) + '0');
        return new char[]{sum, carry};
    }

    public static String onesc(String s) {
        String t = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') t += "0";
            else t += "1";
        }
        return t;
    }

    public static String addWords(String[] w, int word) {
        String res = "";
        char c = '0';
        for (int i = word - 1; i >= 0; i--) {
            char bit = '0';
            for (int j = 0; j < w.length; j++) {
                char[] r = sumBits(bit, w[j].charAt(i), '0');
                bit = r[0];
            }
            char[] f = sumBits(bit, c, '0');
            res = f[0] + res;
            c = f[1];
        }
        if (c == '1') {
            String s = "0".repeat(word);
            String[] wrap = {res, s};
            res = addWords(wrap, word);
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter word size (bits): ");
        int word = sc.nextInt();
        System.out.print("Enter number of words: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] data = new String[n];
        System.out.println("Enter the data words:");
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextLine().trim();
        }

        String sum = addWords(data, word);
        String checksum = onesc(sum);

        System.out.println();
        System.out.println("--- Transmission Side ---");
        System.out.println("Checksum: " + checksum);
        System.out.println("Transmitted words:");
        for (int i = 0; i < n; i++) System.out.println(data[i]);
        System.out.println(checksum);

        System.out.println();
        System.out.println("--- Receiver Side ---");
        System.out.println("Enter received words:");
        String[] rec = new String[n + 1];
        for (int i = 0; i < n + 1; i++) rec[i] = sc.nextLine().trim();

        String recvSum = addWords(rec, word);
        String recvComp = onesc(recvSum);

        System.out.println();
        System.out.println("--- Receiver Calculation ---");
        System.out.println("Final Sum (after adding checksum): " + recvSum);
        System.out.println("1's Complement of result: " + recvComp);

        if (recvComp.equals("0".repeat(word))) {
            System.out.println("No Error: Data received correctly");
        } else {
            System.out.println("Error Detected!");
        }
    }
}

