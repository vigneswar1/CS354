import java.util.Scanner;

class Q_1 {

    String bitStuffing(String st) {
        String newst = "";
        for (int i = 0; i < st.length(); i++) {
            if (i + 5 < st.length()
                    && st.charAt(i) == '0'
                    && st.charAt(i + 1) == '1'
                    && st.charAt(i + 2) == '1'
                    && st.charAt(i + 3) == '1'
                    && st.charAt(i + 4) == '1'
                    && st.charAt(i + 5) == '1') {
                newst += "0111110";
                i = i + 5;
            } else {
                newst += st.charAt(i);
            }
        }
        return newst;
    }
    String unStuffing(String st) {
        String newst = "";
        for (int i = 0; i < st.length(); i++) {
            if (i + 5 < st.length()
                    && st.charAt(i) == '0'
                    && st.charAt(i + 1) == '1'
                    && st.charAt(i + 2) == '1'
                    && st.charAt(i + 3) == '1'
                    && st.charAt(i + 4) == '1'
                    && st.charAt(i + 5) == '1') {
                newst += "011111";
                i = i + 5;
            } else {
                newst += st.charAt(i);
            }
        }
        return newst;
    }


    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter original data: ");
        String ordata = s.nextLine();

        Q_1 obj = new Q_1();
        String newst = obj.bitStuffing(ordata);

        System.out.println("Bit stuffed data: " + newst);
        
        String newst1 = obj.unStuffing(ordata);

        System.out.println("Bit unstuffed data: " + newst1);
        s.close();
    }
}
