import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main
{
    static int N; //팀의 인원수
    static int[][] ability; // 능력치 표
    static boolean[] visit; // 방문 여부

    static int min = 101; //100보다 큰 정수로 설정해서 점차 감소

    static void backtracking(int index, int count){ // 팀 조합이 완성될 경우
        if(count == N / 2) {
            // 방문한 곳과 방문하지 않은 곳을 팀으로 각각 나누어 각 팀의 점수를 구한 뒤 최솟값을 찾는다.
            different();
            return;
        }

        for(int i = index; i < N; i++) {
            // 방문하지 않은 곳인 경우
            if(!visit[i]) {
                visit[i] = true;	// 방문으로 변경
                backtracking(i + 1, count + 1);	// 재귀 호출
                visit[i] = false;	// 재귀가 끝나면 방문 안 한 곳으로 변경하여 다른 것과 조합할 수 있게끔 함
            }
        }
    }

    static void different() {
        int start = 0;
        int link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                // i 번째, j 번째 사람이 true라면 스타트 쪽으로 점수 합산
                if (visit[i] == true && visit[j] == true) {
                    start += ability[i][j];
                    start += ability[j][i];
                }
                // i 번째, j 번째 사람이 false라면 링크 쪽으로 점수 합산
                else if (visit[i] == false && visit[j] == false) {
                    link += ability[i][j];
                    link += ability[j][i];
                }
            }
        }
        // 두 팀의 점수 차이 (절댓값)
        int val = Math.abs(start-link);

        /*
         * 두 팀의 점수차가 0이라면 가장 낮은 최솟값이기 때문에
         * 더이상의 탐색 필요없이 0을 출력하고 종료하면 된다.
         */
        if (val == 0) {
            System.out.println(val);
            System.exit(0);
        }

        min = Math.min(val, min);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ability = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            //공백으로 토큰 분리

            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }//입력된 능력치 그리드에 세팅
        }

        backtracking(0, 0);
        System.out.println(min);

    }

}
