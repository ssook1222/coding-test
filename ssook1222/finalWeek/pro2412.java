import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Location{
    int x, y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class Main
{
    static int n, T;
    static ArrayList<Integer>[] grv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        grv = new ArrayList[200001];
        for (int i = 0; i < 200001; i++) {
            grv[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            grv[y].add(x);
        }

        for (int i = 0; i < 200001; i++) {
            Collections.sort(grv[i]);
        }

        System.out.println(bfs());
        br.close();

    }

    public static int bfs() {

        Queue<Location> q = new LinkedList<>();
        q.offer(new Location(0, 0));

        int move=0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int s = 0; s < size; s++) {
                Location loc = q.poll(); //큐에 있는 정보 추출

                if (loc.y == T) //암벽 정상에 오르면
                {
                    return move; //이동 횟수 반환
                }

                for (int y=loc.y-2; y<=loc.y+2; y++) { //이동하고자 하는 높이가 2 범위 내에 있는 경우

                    if (y < 0 || y >= 200001) {
                        continue;
                    }

                    for (int j = 0; j < grv[y].size(); j++) {
                        int x = grv[y].get(j);

                        if (loc.x+2<x) {  //범위를 벗어나면 나가기
                            break;
                        }

                        else if (loc.x-2>x) { // 범위 내에 있다면 계속 진행
                            continue;
                        }
                        grv[y].remove(j); //이동시킨 후 큐에서 제거

                        q.add(new Location(x, y)); //큐에 지금 위치 추가

                        j--;
                    }
                }
            }
            move++;
        }

        return -1;
    }
}