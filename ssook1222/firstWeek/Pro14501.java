import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro14501
{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        //N이 15이하이므로 넉넉하게 2배로 잡아 둠
        int[] T = new int[N+15];
        int[] P = new int[N+15];
        int[] dp = new int[N+15];
        int max=0;

        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i > 0; i--) {
            int day=i+T[i];

            if (day>N + 1) {//날짜를 넘어가는 경우
                dp[i]=dp[i + 1];
            }
            else{
                dp[i] = Math.max(P[i]+dp[day],dp[i + 1]);
            }
        }
        System.out.println(dp[1]);
    }
}
