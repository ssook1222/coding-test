import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main
{
    static int N;
    static int M;
    static int[][] map;
    static ArrayList<Home> chicken;
    static ArrayList<Home> person;
    static int[] output;
    static boolean[] visit;
    static int result;

    static class Home {
        int x;
        int y;

        public Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        result = Integer.MAX_VALUE;
        chicken = new ArrayList<Home>();
        person = new ArrayList<Home>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    //1일 때는 가정집 리스트에 추가
                    person.add(new Home(i, j));
                } else if (map[i][j] == 2) {
                    //2일 때는 치킨집 리스트에 추가
                    chicken.add(new Home(i, j));
                }
            }
        }

        visit = new boolean[chicken.size()];
        output = new int[chicken.size()];

        //치킨 집 선택
        for (int i = 0; i < chicken.size(); i++) {
            visit[i] = true;
            Select(i, 0);
            visit[i] = false;
        }
        System.out.println(result);
    }

    public static void Select(int start, int depth) {
        output[depth] = start + 1;

        for (int i = start; i < chicken.size(); i++) {
            if (visit[i])
                continue;
            visit[i] = true;
            Select(i, depth + 1);
            visit[i] = false;
        }

        if (depth == M-1) {
            int sum = 0;
            int current = 0;

            for (int i = 0; i < person.size(); i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < M; j++) {
                    current = Math.abs(person.get(i).x - chicken.get(output[j] - 1).x) + Math.abs(person.get(i).y - chicken.get(output[j] - 1).y);
                    min = Math.min(min, current);
                }
                sum = sum + min;
            }
            result = Math.min(result, sum);
        }
    }

}
