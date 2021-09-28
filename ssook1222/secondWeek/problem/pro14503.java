import java.util.Scanner;

public class pro14503
{
    static int n,m,result=0;
    static int map[][];

    static int dr[]={-1,0,1,0};
    static int dc[]={0,1,0,-1};

    public static void main(String[] args) {

        int r,c,dir;

        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        m = scan.nextInt();

        r = scan.nextInt();
        c = scan.nextInt();
        dir = scan.nextInt();

        map = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                map[i][j]=scan.nextInt();
            }
        }

        move(r,c,dir);
        System.out.println(result);
    }

    public static void move(int r, int c, int dir){

        if(map[r][c]==1){
            return;
        }
        else if(map[r][c]==0){
            map[r][c]=-1;
            result++;
        } //청소안 한 곳을 청소한 곳으로

        int nd = dir;
        for(int i=0; i<4; i++){
            nd=(nd+3)%4;
            int nr=r+dr[nd];
            int nc=c+dc[nd];

            if(map[nr][nc]==0){
                move(nr,nc,nd);
                return;
            }
        }
        move(r-dr[dir],c-dc[dir],dir);
        return;
    }
}

