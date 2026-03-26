class HammingUtils {

    public static String generateHammingCode(String data) {
        if (data.length() != 4) throw new IllegalArgumentException("Data must be 4 bits");

        char[] hamming = new char[7];
        hamming[2] = data.charAt(0);
        hamming[4] = data.charAt(1);
        hamming[5] = data.charAt(2);
        hamming[6] = data.charAt(3);

        hamming[0] = parity(hamming[2], hamming[4], hamming[6]);
        hamming[1] = parity(hamming[2], hamming[5], hamming[6]);
        hamming[3] = parity(hamming[4], hamming[5], hamming[6]);

        return new String(hamming);
    }

    public static boolean checkHammingCode(String code) {
        if (code.length() != 7) throw new IllegalArgumentException("Code must be 7 bits");
        char[] c = code.toCharArray();

        int p1 = xor(c[0], c[2], c[4], c[6]);
        int p2 = xor(c[1], c[2], c[5], c[6]);
        int p3 = xor(c[3], c[4], c[5], c[6]);

        int errorPosition = p1 * 1 + p2 * 2 + p3 * 4;
        return errorPosition == 0;
    }

    private static char parity(char a, char b, char c) {
        return ((a - '0') ^ (b - '0') ^ (c - '0')) == 0 ? '0' : '1';
    }

    private static int xor(char... bits) {
        int result = 0;
        for (char b : bits) {
            result ^= (b - '0');
        }
        return result;
    }
}
