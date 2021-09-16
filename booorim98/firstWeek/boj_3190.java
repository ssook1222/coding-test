import java.util.*;
import java.io.*;

public class boj_3190 {

    static int N;
    static int[][] arr;
    static Map<Integer, String> snake;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int dir = 1; //뱀의 최초 머리 방향 설정 (오른쪽)

    private static class Location {
        int x;
        int y;
        Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        snake = new HashMap<>();

        for (int i = 0; i < K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1; //사과 있는 곳
        }

        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            snake.put(time, direction);

        }
        find();
    }

    static void find() {
        Deque<Location> q = new ArrayDeque<>();
        q.add(new Location(1,1));
        arr[1][1] = -1; //뱀의 몸 있는 곳은 -1
        int time =0;

        while (true) {
            if (snake.containsKey(time)){
                dir = (snake.get(time).equals("D")) ? (dir + 1) % 4 : (dir + 3) % 4;
            }

            time++;
            Location head = q.getFirst();
            int nX = head.x + dx[dir];
            int nY = head.y + dy[dir];

            if (nX <= 0 || nX > N || nY <= 0 || nY > N) {
                break;
            }

            if (arr[nX][nY] == -1) {
                break;
            }

            if (arr[nX][nY] == 1) { //사과랑 만났을 때
                arr[nX][nY] = -1; //뱀 위치 추가
                q.offerFirst(new Location(nX, nY));
            }
            else {
                arr[nX][nY] = -1; //뱀 위치 추가
                q.offerFirst(new Location(nX, nY));
                Location tail = q.peekLast();
                arr[tail.x][tail.y] = 0;
            }

        }
        System.out.println(time);
    }
}