import java.io.*;
import java.util.*;

class Fish {
    int x, y, time;

    public Fish(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main
{
    static int N;
    static int[][] map;
    static boolean[][] isVisit;

    static Fish shark;		// 상어도 물고기! 상어의 위치를 기록
    static int size = 2;
    static int eaten = 0;

    static ArrayList<Fish> feedList = new ArrayList<>();
    static int answer;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);

        map = new int[N][N];
        isVisit = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);

                if(map[i][j] == 9) {
                    shark = new Fish(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }

        simulation();
        System.out.println(answer);
    }

    private static void simulation() {

        Queue<Fish> q = new LinkedList<>();
        q.add(shark);
        isVisit[shark.x][shark.y] = true;

        while(true) {
            while(!q.isEmpty()) {
                Fish now = q.poll();
                int time = now.time;


                for (int k = 0; k < 4; k++) {
                    int nx = now.x + dx[k];
                    int ny = now.y + dy[k];

                    // 범위 밖 -> 아웃
                    if(!(0 <= nx && nx < N && 0 <= ny && ny < N) || isVisit[nx][ny]) continue;

                    // [식사 가능] 먹이 리스트에 추가
                    if(map[nx][ny] < size && map[nx][ny] != 0) {
                        q.add(new Fish(nx, ny, time+1));
                        isVisit[nx][ny] = true;
                        feedList.add(new Fish(nx, ny, time+1));
                    }

                    // [이동만 가능] 사이즈랑 동일 OR 비어있음(0)
                    if(map[nx][ny] == size || map[nx][ny] == 0) {
                        q.add(new Fish(nx, ny, time+1));
                        isVisit[nx][ny] = true;
                    }
                }
            }

            if(!feedList.isEmpty()) {
                eat();
                // 식사가 끝났으면, 방문 배열 초기화

                q.clear();
                isVisit = new boolean[N][N];

                // 다시 이동하는 큐(q)에다가 현재 상어 추가
                q.add(shark);
                isVisit[shark.x][shark.y] = true;
            }else {
                return;
            }
        }

    }

    private static void eat() {

        // 정렬을 통해 우선순위별로 먹이 리스트 재정렬
        Collections.sort(feedList, new Comparator<Fish>() {

            @Override
            public int compare(Fish o1, Fish o2) {
                if(o1.time == o2.time) {
                    if(o1.x == o2.x) {
                        if(o1.y > o2.y) {
                            return 1;
                        }else {
                            return -1;
                        }
                    }else {
                        if(o1.x > o2.x) {
                            return 1;
                        }else {
                            return -1;
                        }
                    }
                }else if(o1.time > o2.time){
                    return 1;
                }else {
                    return -1;
                }
            };
        });

        Fish now = feedList.get(0);

        // 상어 위치 이동, 상어가 먹은 횟수 증가, 상어 걸린 시간 증가
        shark.x = now.x;
        shark.y = now.y;

        if(++eaten == size) {
            size++;
            eaten = 0;
        }

        answer += now.time;

        // 지도 위에 해당 위치 0 처리
        map[now.x][now.y] = 0;

        // 먹이 리스트 초기화
        feedList.clear();
    }
}
