import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main
{

    public static int MAX = Integer.MIN_VALUE;	// 최댓값
    public static int MIN = Integer.MAX_VALUE;	// 최소값
    public static int[] operator = new int[4];	// 연산자 개수
    public static int[] number;					// 숫자
    public static int N;						// 입력 값 : 숫자 개수


    public static void dfs(int num, int index) {
        if (index == N) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for (int i = 0; i < 4; i++) { // 연산자 개수가 1개 이상
            if (operator[i] > 0) { // 해당 연산자를 1 감소
                operator[i]--;

                if(i==0){
                    dfs(num+number[index],index+1);
                }

                else if(i==1){
                    dfs(num-number[index],index+1);
                }

                else if(i==2){
                    dfs(num*number[index],index+1);
                }

                else if(i==3){
                    dfs(num/number[index],index+1);
                }

                operator[i]++; //연산자 개수 4개로 복구
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        number = new int[N];

        // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(number[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }
}
