import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] move;
    static boolean[][] check;

    static int dr[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int dc[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
    static ArrayList<Pos> storm;
    static class Pos {
        int r, c;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        move = new int[M][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            move[i][0] = Integer.parseInt(st.nextToken()); // 방향 d
            move[i][1] = Integer.parseInt(st.nextToken()); // 거리 s
        }

        storm = new ArrayList<Pos>();

        storm.add(new Pos(N - 1, 0));
        storm.add(new Pos(N - 1, 1));

        storm.add(new Pos(N - 2, 0));
        storm.add(new Pos(N - 2, 1));


        for (int i = 0; i < M; i++) {

            check = new boolean[N][N];
            int d = move[i][0];
            int s = move[i][1];
            move(d, s);
            add();
            remove();
        }
        System.out.println(count());

    }

    private static int count() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += map[i][j];
            }
        }
        return cnt;
    }

    private static void remove() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] >= 2 && !check[i][j]) {
                    map[i][j] -= 2;
                    storm.add(new Pos(i, j));
                }
            }
        }

    }

    private static void add() {
        for (Pos pos : storm) {
            int cnt = 0;
            int nr = -1, nc = -1;
            for (int i = 2; i <= 8; i += 2) {
                nr = pos.r + dr[i];
                nc = pos.c + dc[i];
                if (canMove(nr, nc) && map[nr][nc] > 0) {
                    cnt++;
                }
            }
            map[pos.r][pos.c] += cnt;
        }
        storm.clear();
    }

    private static boolean canMove(int r, int c) {
        if(r >= 0 && c >= 0 && r < N && c < N){
            return true;
        }
        else{
            return false;
        }
    }

    private static void move(int d, int s) {

        for (Pos pos : storm) {
            int nr = (pos.r + N + dr[d] * s % N) % N;
            int nc = (pos.c + N + dc[d] * s % N) % N;

            check[nr][nc] = true;
            map[nr][nc] += 1;
            pos.r = nr;
            pos.c = nc;
        }
    }
}