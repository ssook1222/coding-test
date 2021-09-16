import java.util.*;
import java.io.*;

public class boj_13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] exam = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            exam[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken()); //총감독관 감시 가능 학생 수
        int C = Integer.parseInt(st.nextToken()); //부감독관 감시 가능 학생 수

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int students = exam[i];
            students -= B;
            answer++;

            if (students > 0) {
                if (students % C == 0) {
                    answer += students / C;
                }
                else {
                    answer += students / C + 1;
                }
            }
        }

        System.out.println(answer);
    }
}