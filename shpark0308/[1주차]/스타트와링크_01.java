import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* 일단 해보기 */
public class 스타트와링크_01 {
	public static HashMap<ArrayList<Integer>, Integer > hashmap = new HashMap<ArrayList<Integer>, Integer>();
	public static int[][] DP;
	public static int min = Integer.MAX_VALUE;
	public static int total = 0;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		DP = new int[N][N];
		String[] str;
		for (int a=0; a<N; a++) {
			str = br.readLine().split(" ");
			for (int b=0; b<N; b++) DP[a][b] = Integer.parseInt(str[b]);
		}
		combination(N, 1 ,new ArrayList<Integer>());
		//System.out.println(hashmap);
		//System.out.println(hashmap.size());
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(hashmap.keySet());
		ArrayList<Integer> all = new ArrayList<Integer>();
		for (int i=1; i<=N;i++) all.add(i);
		ArrayList<Integer> temp1 = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		int sum = 0;
		////System.out.println(hashmap);
		////System.out.println();
		////System.out.println();
		while(!list.isEmpty()) {
			temp1 = list.get(0);
			temp2.addAll(all);
			temp2.removeAll(temp1);
			/////System.out.println("temp1 = "+temp1+" temp2"+temp2);
			//sum = hashmap.get(temp1)+hashmap.get(temp2);
			sum = Math.abs(hashmap.get(temp1)-hashmap.get(temp2));
			list.remove(temp1);
			list.remove(temp2);
			min = Math.min(min, sum);
			System.out.println(temp1 + " // "+temp2+"   sum = "+sum);
			temp2.clear();
		}
		System.out.println(min);

	}

	public static void combination(int N, int index, ArrayList<Integer> arr) {
		if (arr.size()==(N/2)) {
			System.out.println(arr);
			total = 0;
			combination2(0, new ArrayList<Integer>(), arr,0); // -> 여기서 절반만 계산
			hashmap.put(arr, total);
			System.out.println(total);
			return;
		}
		if (index==(N+1)) return;
		ArrayList<Integer> ar1 = new ArrayList<Integer>();
		ArrayList<Integer> ar2 = new ArrayList<Integer>();
		ar1.addAll(arr); ar2.addAll(arr);
		
		combination(N, index+1, ar1);
		ar2.add(index);
		combination(N, index+1, ar2);

	}
	
	public static void combination2(int index, ArrayList<Integer> arr, ArrayList<Integer> standard, int sum) {
		if (arr.size()==2) {
			sum = sum + DP[arr.get(0)-1][arr.get(1)-1] +  DP[arr.get(1)-1][arr.get(0)-1];
			//////System.out.println(arr+"    "+sum);
			total+=sum;
			return;
		}
		if (index==standard.size()) return;
		ArrayList<Integer> ar1 = new ArrayList<Integer>();
		ArrayList<Integer> ar2 = new ArrayList<Integer>();
		ar1.addAll(arr); ar2.addAll(arr);
		
		combination2(index+1, ar1,standard, sum);
		ar2.add(standard.get(index));
		combination2(index+1, ar2, standard, sum);
	}
}
