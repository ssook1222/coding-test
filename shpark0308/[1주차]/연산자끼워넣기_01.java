import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 연산자끼워넣기_01 {
	public static int total=0;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] str = br.readLine().split(" ");
		int[] number = new int[N];
		for (int i=0; i<N; i++) number[i] = Integer.parseInt(str[i]);
		
		str = br.readLine().split(" ");
		String[] operation = new String[N-1];
		int count=0;
		for (int i=0; i<Integer.parseInt(str[0]);i++, count++) operation[count] = "+";
		for (int i=0; i<Integer.parseInt(str[1]);i++, count++) operation[count] = "-";
		for (int i=0; i<Integer.parseInt(str[2]);i++, count++) operation[count] = "*";
		for (int i=0; i<Integer.parseInt(str[3]);i++, count++) operation[count] = "+";
		
		System.out.println(Arrays.toString(operation));
		
		permutation(N-1, 0, operation);
		System.out.println("count = "+total);

	}
	public static void permutation(int n, int depth, String[] arr) {
		//System.out.println("depth = "+depth+"    arr = "+Arrays.toString(arr));
		if (depth == n) {
			total++;
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i=depth; i<n; i++) {
			swap(arr, depth,i );
			permutation(n, depth+1, arr);
			swap(arr, i, depth);
		}
	}
	
	public static void swap(String[] arr, int s, int e) {
		String temp = arr[s];
		arr[s] = arr[e];
		arr[e] = temp;
	}

}
