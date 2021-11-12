import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Map{
    int red_next,blue_next,now;

    Map(int red_next, int blue_next, int now){
        this.red_next = red_next;
        this.blue_next = blue_next;
        this.now = now;
    }
}

class Horse{
    int idx;
    boolean flag;
    Horse(int idx, boolean flag){
        this.idx= idx;
        this.flag= flag;
    }
}

public class Main {
    static int dfs_horse[] = new int[11];
    static Map map[];
    static Horse horse[];
    static int cmd[] = new int[11]; //입력되는 명령어 개수
    static int result = 0;
    static boolean visited[] = new boolean [32];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new Map[32];
        setting();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=11; i++) {
            cmd[i]= Integer.parseInt(st.nextToken());
        }

        dfs(1);

        System.out.println(result);
    }
    public static boolean go(int cmd, int horse_num) {

        Horse a = horse[horse_num];
        int tmp=0;
        int now_pos = a.idx;
        boolean flag = a.flag;

        if(now_pos==100) {
            return true;
        }


        for(int i=0; i<cmd; i++) { //10회 동안 반복

            if(now_pos ==100) {
                horse[horse_num] = new Horse(now_pos,flag);
                visited[a.idx] = false;
                return true;
            }

            if(now_pos ==5 || now_pos ==10 || now_pos ==15) {
                if(flag) {
                    now_pos = map[now_pos].blue_next;
                }
                else {
                    now_pos = map[now_pos].red_next;
                }
            }
            else {
                now_pos = map[now_pos].red_next;
            }
        }

        if(now_pos ==100) {
            horse[horse_num] = new Horse(now_pos,flag);
            visited[a.idx] = false;
            return true;
        }

        if(now_pos ==5 || now_pos ==10 || now_pos ==15) {
            flag = true;
        }

        if(visited[now_pos]) {
            return false;
        }

        tmp+=map[now_pos].now;
        horse[horse_num] = new Horse(now_pos,flag);
        visited[a.idx] = false;
        visited[now_pos] = true;

        return true;
    }
    public static void dfs(int level) {
        int tmp=0;
        if(level==11) {
            for(int i=1; i<=10; i++) {
                if(go(cmd[i],dfs_horse[i])==false) {
                    clear();
                    return ;
                }
            }
            result = Math.max(result, tmp);
            clear();
            return ;
        }

        for(int i=1; i<=4; i++) {
            dfs_horse[level] = i;
            dfs(level+1);
        }
    }

    public static void clear() {

        for(int i=1; i<=4; i++) { // 말의 위치정보 초기화
            horse[i] = new Horse(0,false);
        }

        visited = new boolean [32]; // 중복을 위한 방문정보 초기화
    }

    public static void setting() {

        map[0] = new Map(1,0,0);

        int map_num = 2;

        for(int i=1; i<=19; i++) {
            map[i] = new Map(i+1,0,map_num);
            map_num+=2;
        }

        map[20] = new Map(100,0,40);


        map[5].blue_next = 21;
        map[10].blue_next = 29;
        map[15].blue_next = 27;

        map[21] = new Map(22,0,13);
        map[22] = new Map(23,0,16);
        map[23] = new Map(24,0,19);
        map[24] = new Map(30,0,25);
        map[25] = new Map(24,0,26);
        map[26] = new Map(25,0,27);
        map[27] = new Map(26,0,28);
        map[28] = new Map(24,0,24);
        map[29] = new Map(28,0,22);
        map[30] = new Map(31,0,30);
        map[31] = new Map(20,0,35);

        horse = new Horse[5];

        for(int i=1; i<=4; i++) {
            horse[i] = new Horse(0,false);
        }
    }
}
