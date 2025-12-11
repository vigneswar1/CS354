import java.util.Scanner;

class Q_2{
	char xor(char i,char j){
		return (i == j) ? '0' : '1';
	}
	public static void main(String[] args){
		Scanner s=new Scanner(System.in);
		System.out.print("Enter number of bits in input data : ");
		int m = Integer.parseInt(s.nextLine());
		System.out.print("Enter original data : ");
		String ordata = s.nextLine();
		int i=0;
		int x;
		while (true){
		 	if((1 << i) >= m + i + 1){
				x = i;
				break;
		 	}
            i++;
		}
		String tdata="";
		int k=0;
		int dataIndex = ordata.length() - 1;

		for(int j=0;j<x+m;j++){
			if((j == (1 << k)) && ((1 << k) <= x + m)){
				tdata = tdata + "_";
				k++;
			}else{
				if (dataIndex >= 0) {
					tdata = tdata + ordata.charAt(dataIndex);
					dataIndex--;
				} else {
				}
			}
		}
		System.out.println(tdata);
		s.close();
	}
}
