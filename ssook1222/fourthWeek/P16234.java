import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

class dot {
    int x;
    int y;

    public dot(int x,int y) {
        this.x= x;
        this.y= y;
    }
}

public class P16234
{
    static int N,L,R,result=0;
    static int[][] map,open;
    static boolean[][] visited;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};
    static boolean check = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        L = Integer.valueOf(st.nextToken());
        R = Integer.valueOf(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        do{
            check = false;
            visited = new boolean[N][N];
            open = new int[N][N];

            //국경을 열 수 있는지 보기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    castleOpen(new dot(i,j));
                }
            }

            if(check==false) { //없는 경우 나와!
                //System.out.println(check);
                check=true;
            }
            else {
                result++;
            }

            for(int i=0; i<N; i++) { //연결된 경우 인구 분배
                for(int j=0; j<N; j++) {
                    if(open[i][j] == 1 && !visited[i][j]) {
                        personDistribute(new dot(i,j));
                    }
                }
            }
        } while (check != false);

        System.out.println(result);
    }

    public static void castleOpen(dot d) {
        int x = d.x;
        int y = d.y;

        for(int i=0; i<4; i++) {
            int x1 = d.x + dx[i];
            int y1 = d.y + dy[i];

            if(x1>=0 && x1<N && y1>=0 && y1<N) { //L 이상 R이하의 범위이면 두 나라를 연결시켜준다.
                if(Math.abs(map[x][y] - map[x1][y1])>=L && Math.abs(map[x][y] - map[x1][y1])<=R) {
                    open[x][y] = 1;
                    open[x1][y1] = 1;
                    check=true;
                    System.out.println(check);
                }
            }
        }
    }

    public static void personDistribute(dot d) {
        Queue<dot> q = new LinkedList<dot>();
        ArrayList<dot> save = new ArrayList<dot>();
        int tmp_count = 0;
        int sum = 0;
        visited[d.x][d.y] = true;
        q.add(d); //상하좌우가 1이라면 q에 더해준다.

        while(!q.isEmpty()) {
            dot t = q.poll();
            save.add(t);
            int x = t.x;
            int y = t.y;
            sum += map[x][y];
            tmp_count++;

            for(int i=0; i<4; i++) {
                int x1 = x + dx[i];
                int y1 = y + dy[i];

                if(x1>=0 && x1<N && y1>=0 && y1<N && Math.abs(map[x][y] - map[x1][y1])>=L && Math.abs(map[x][y] - map[x1][y1])<=R && !visited[x1][y1]) {
                    q.add(new dot(x1, y1));
                    visited[x1][y1] = true;
                }
            }
        }
        //나눠줄 인구를 구한 후 큐를 돌며 좌표를 구해 인구를 나누어준다.
        int divide = sum/tmp_count;

        for(int i=0; i<save.size(); i++) {
            dot t = save.get(i);
            map[t.x][t.y] = divide;
        }
    }
}

