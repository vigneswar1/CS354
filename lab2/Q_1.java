import java.util.Scanner;

class Q_1{
	char xor(char i,char j){
		return (i == j) ? '0' : '1';
	}
	String moddiv(String key,String div){
		String data="";
		for(int i=0;i<key.length();i++){
			char x=xor(key.charAt(i),div.charAt(i));
			if(x=='0' && data.length()==0){
			} else{
				data=data+x;
			}
		}
		String remainderPart = "";
        if (div.length() > key.length()) {
            remainderPart = div.substring(key.length());
        }
        String nextDiv = (data.length() == 0) ? remainderPart : data + remainderPart;
        if (nextDiv.length() < key.length()) return nextDiv;
        return moddiv(key, nextDiv);
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		System.out.print("Enter original data : ");
		String ordata = s.nextLine();
		System.out.print("Enter Key : ");
		String key = s.nextLine();
		Q_1 obj = new Q_1();
		String tdata = ordata + obj.moddiv(key, ordata);
		System.out.print("Encoded Data: : "+tdata);
		System.out.print("Enter Received data : ");
		String Rdata = s.nextLine();
		String rdata = obj.moddiv(key, Rdata);
		for (char i : rdata.toCharArray()) {
    		if (i == '1') {
        		System.out.println("Error detected : YES");
        		return;
    		} else {
        		System.out.println("Error detected : NO");
    		}
		}
		s.close();
	}
}

