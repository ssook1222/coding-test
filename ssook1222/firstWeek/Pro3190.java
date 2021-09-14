import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Pro3190
{
    static int dx[] = {-1 ,0, 1, 0}; //X축 방향 이동
    static int dy[] = {0, 1, 0, -1}; //Y축 방향 이동

    static class Pos{
        int x;
        int y;
        public Pos(int x, int y){this.x = x; this.y = y;} //좌표 받아오기
    }

    private static int cd(int cd, char inst){
        int nd;
        if(inst == 'D')
            nd = (cd==3) ? 0 : ++cd;
        else
            nd = (cd==0) ? 3 : --cd;
        return nd;
    }

    private static Pos nextPos(Pos pos, int dir){
        Pos np = new Pos(pos.x, pos.y);
        switch (dir){
            case 0: --np.x; break;
            case 1: ++np.y; break;
            case 2: ++np.x; break;
            case 3: --np.y; break;
        }
        return np;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int L;
        int time=0;
        int dir=1;
        StringTokenizer st;

        Queue<Pos> snake = new LinkedList<>();   // 뱀 좌표를 넣을 큐, 링크리스트로 구현
        int map[][] = new int[N+2][N+2];        // n+2 by n+2 그리드로
        //사과가 없으면 0, 있으면 1, 그냥 벽은 -1
        char inst[] = new char[100001];     // 명령어를 넣어두는 배열

        // 그리드에 벽 생성
        for(int i=0; i<N+2; i++)
            for(int j=0; j<N+2; j++)
                if(i==0 || j==0 || i==N+1 || j==N+1)
                    map[i][j] = -1;

        // 그리드에 사과 놓기
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        L=Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        //명령어 넣을 때 문제가 생긴 듯...
        for(int i=0; i<L; i++){
            if(!st.hasMoreTokens()){
                inst[Integer.parseInt(st.nextToken())]=st.nextToken().charAt(0);}
        }

        Pos cur_pos = new Pos(1, 1);
        snake.offer(cur_pos);
        map[cur_pos.x][cur_pos.y] = -1;

        while(true){
            ++time;

            // 뱀의 머리 위치 이동
            cur_pos.x += dx[dir];
            cur_pos.y += dy[dir];

            //cur_pos = getNextPos(cur_pos, dir);

            // 벽이나 뱀의 몸을 만났을 경우 게임 종료
            if(map[cur_pos.x][cur_pos.y] == -1 ){
                break;
            }
            // 빈칸인 경우 마지막 칸 꼬리를 비워줌
            if(map[cur_pos.x][cur_pos.y] == 0){
                Pos tail = snake.poll();
                map[tail.x][tail.y] = 0;
            }

            // 머리를 큐에 넣고 맵의 변수 변경
            snake.offer(cur_pos);
            map[cur_pos.x][cur_pos.y] = -1;

            // 방향전환
            if(inst[time] == 'D' || inst[time] == 'L')
                dir = cd(dir, inst[time]);
        }
        System.out.println(time);
    }
}

