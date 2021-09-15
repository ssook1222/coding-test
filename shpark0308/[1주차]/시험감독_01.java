import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 시험감독_01 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] A = br.readLine().split(" ");
		String[] str = br.readLine().split(" ");
		int B = Integer.parseInt(str[0]); int C = Integer.parseInt(str[1]);
		
		long count = 0; 
		int num=0;
		for (int i=0; i<A.length;i++) {
			num = Integer.parseInt(A[i]);
			num = num-B >=0 ? num-B : 0;
			num = num%C!=0 ? num/C+1 : num/C;
			count = count+1+num;
		}
		System.out.println(count);
	}

}
