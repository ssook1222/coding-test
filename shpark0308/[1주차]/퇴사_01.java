import java.util.Scanner;

public class 퇴사_01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] T = new int[N+5];
		int[] P = new int[N+5];
		int[] DP = new int[N+5];
		for (int i=0; i<N; i++) {
			T[i] = sc.nextInt();
			P[i] = sc.nextInt();
		}
		int max = 0; // 최대 수익금
		for (int i=0; i<=N; i++) {
			DP[i] = Math.max(DP[i], max);
			DP[i+T[i]] = Math.max(DP[i+T[i]], DP[i]+P[i]);
			max = Math.max(max, DP[i]); // 현재까지 중에 가장 큰 애를 고르는 것
		}
		System.out.println(DP[N]);

	}

}
