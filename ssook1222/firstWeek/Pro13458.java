import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pro13458
{

    static int N,B,C;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st =new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken()); // 총 감독관 관리 가능 수
        C = Integer.parseInt(st.nextToken()); // 부 감독관 관리 가능 수

        long count = 0;

        for(int i=0;i<arr.length;i++) {
            long result = 0;

            if(arr[i]<=B) {
                count++;
                continue;
            }

            else {
                count++;
                arr[i]-=B;

                if(arr[i]%C==0) count+=arr[i]/C;
                else if(arr[i]%C!=0) count+= arr[i]/C + 1;
            }
        }
        System.out.println(count);
    }
}
