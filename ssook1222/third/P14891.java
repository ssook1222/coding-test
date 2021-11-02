import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14891
{

    static int[][] gear = new int[4][8]; //전체 바퀴 저장, 4개의 바퀴, 8개의 방향
    static int[] dir; //회전하는 방향, 0이면 정지, 1이면 시계, -1이면 반시계

    static void ans(int idx, int dir) {
        left(idx-1, -dir);
        right(idx+1, -dir);
        rotate(idx, dir);
    }

    static void left(int idx, int dir) {
        if (idx<0) return;

        if (gear[idx][2]!=gear[idx+1][6]) { //2번과 6번 방향 비교, 다르면 왼쪽으로 회전
            left(idx-1, -dir);
            rotate(idx, dir);
        }
    }

    static void right(int idx, int dir) {
        if (idx > 3) return;

        if (gear[idx][6] != gear[idx-1][2]) {  //2번과 6번 방향 비교, 다르면 오른쪽으로 회전
            right(idx+1, -dir);
            rotate(idx, dir);
        }
    }

    static void rotate(int idx, int dir) {
        if (dir==1) { //시계방향으로 회전
            int temp = gear[idx][7];

            for (int i = 7; i > 0; i--) {
                gear[idx][i] = gear[idx][i - 1];
            }

            gear[idx][0]=temp;

        }

        else { //반시계방향으로 회전
            int temp=gear[idx][0];

            for (int i = 0; i < 7; i++) {
                gear[idx][i]=gear[idx][i + 1];
            }

            gear[idx][7]=temp;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //방향 받아오기
        for (int i = 0; i < 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = s.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        //톱니바퀴 계산
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            // 톱니바퀴의 번호는 0 ~ 3 이 아니라 1 ~ 4 다. 따라서 배열 인덱스 0~3에 맞춰줘야 한다.
            ans(idx - 1, dir);
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            int num = gear[i][0];
            if (num == 1) {
                result += Math.pow(2, i);
            }
        }

        System.out.println(result);
    }
}
