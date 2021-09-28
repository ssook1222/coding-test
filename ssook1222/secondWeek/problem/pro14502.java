import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Virus {
    int x,y; //바이러스 퍼뜨릴 좌표

    public Virus(int x, int y){
        this.x=x;
        this.y=y;
    }
}


public class Main
{
    static int space=0;
    static int wall=1;
    static int virus=2;

    static int x,y;
    static int res=Integer.MIN_VALUE;

    static int[][] map, map_copy; //탐색할 공간인 map과 바이러스를 퍼뜨릴 copy 생성

    //바이러스 움직이는 방향 변경
    static int dx[]={0,0,1,-1};
    static int dy[]={1,-1,0,0};

    static Queue<Virus> queue;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        x=scan.nextInt();
        y=scan.nextInt();

        map=new int[x][y];
        map_copy=new int[x][y];

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                map[i][j] = scan.nextInt();
            }
        }

        for(int i = 0; i < x; i++) {
            for(int j = 0 ; j < y; j++) {
                if(map[i][j] == space) {
                    map[i][j] = wall;
                    wall_make(1);
                    map[i][j] = space;
                }
            }
        }

        System.out.println(res);
    }

    //벽을 세우는 메소드
    public static void wall_make(int depth){
        if (depth==3){
            spread();
            return;
        }

        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                if(map[i][j]==space) //space인 상태
                {
                    map[i][j]=wall; //벽이 생겼읍니다
                    wall_make(depth+1);
                    map[i][j]=space; // 다음 껄 체크해야 되므로 다시 복구
                }
            }
        }
    }

    //벽 세운 후 virus 뿌리기
    public static void spread(){
        for(int i=0;i<x;i++){
            for(int j=0; j<y;j++){
                map_copy[i][j]=map[i][j];
            }
        }

        queue=new LinkedList<Virus>(); //큐 생성

        for(int i=0; i<x; i++){
            for(int j=0;j<y;j++){
                if(map_copy[i][j]==virus){
                    queue.add(new Virus(i,j));
                }
            }
        }

        while(!queue.isEmpty()){
            Virus v = queue.remove();
            int a = v.x;
            int b= v.y;

            for(int i=0; i<4 ; i++){
                int nx = a + dx[i];
                int ny = b + dy[i];

                if(nx>=0 && ny>=0 && nx<x && ny<y) {
                    if(map_copy[nx][ny]==space){
                        map_copy[nx][ny]=virus;
                        queue.add(new Virus(nx,ny));
                    }
                }
            }
        }

        int count = 0;
        for (int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                if(map_copy[i][j]==space)
                {++count;}
            }
        }
        res = Math.max(count,res);
    }
}
