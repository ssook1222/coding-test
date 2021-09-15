import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 연산자끼워넣기_02 {
	public static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	public static int N;
	public static int[] number, operation;
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");
		number = new int[N];
		for (int i=0; i<N; i++) number[i] = Integer.parseInt(str[i]);
		
		str = br.readLine().split(" ");
		operation = new int[4];
		for (int i=0; i<4; i++) operation[i] = Integer.parseInt(str[i]);
		
		calculate(number[0], 1);
		System.out.println(max);
		System.out.println(min);
		

	}
	public static void calculate(int sum, int curr) {
		if (curr == N) {
			if (sum<min) min = sum;
			if (sum>max) max = sum;
			return;
		}
		for (int i=0; i<4; i++) {
			if (operation[i]>0) {
				operation[i]--;
				switch(i) {
				case 0: calculate(sum+number[curr], curr+1); break;
				case 1: calculate(sum-number[curr], curr+1); break;
				case 2: calculate(sum*number[curr], curr+1); break;
				case 3: calculate(sum/number[curr], curr+1); break;
				}
				operation[i]++;
			}
		}
	}

}
