import java.util.Scanner;


class Q_1{
	public static void main(String[] args){
		Scanner scanner=new Scanner(System.in);
		System.out.print("Enter original data : ");
		String ordata = scanner.nextLine();
		int c=0;
		for(int i=0;i<ordata.length();i++){
			if(ordata.charAt(i) == '1'){
				c=c+1;
			}
		}
		if(c%2==0){
			ordata = ordata+"0";
		}else{
			ordata = ordata+"1";
		}
		
		System.out.println("Generated data with parity :"+ordata);
		
		System.out.print("Enter received data : ");
		String Rdata = scanner.nextLine();
		
		int j=0;
		for(int i=0;i<Rdata.length();i++){
			if(Rdata.charAt(i) == '1'){
				j=j+1;
			}
		}
		String result=(j%2==0) ? "Error detected : NO" : "Error detected : YES";
		System.out.print(result);
	}
}
