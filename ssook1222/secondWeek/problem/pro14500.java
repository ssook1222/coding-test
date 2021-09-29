import java.util.Scanner;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] check;
    static int result = 0;

    static int[] dx = {0, 0, -1, 1}; // 세로
    static int[] dy = {-1, 1, 0, 0}; // 가로


    static int[][] oh_x = {{0, 0, 0, 1}, {1, 1, 1, 0}, {0, 1, 2, 1}, {0, 1, 2, 1}};
    static int[][] oh_y = {{0, 1, 2, 1}, {0, 1, 2, 1}, {1, 1, 1, 0}, {0, 0, 0, 1}};

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();
        m = scan.nextInt();
        map = new int[n][m];
        check = new boolean[n][m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                map[i][j] = scan.nextInt();
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {

                // 4가지 모양
                check[i][j] = true;
                dfs(i, j, map[i][j], 1);
                check[i][j] = false;

                // ㅗ 모양
                oh(i, j);
            }
        }

        System.out.println(result);

    }

    //ㅗ말고 나머지 4가지
    static void dfs(int x, int y, int sum, int depth) {
        if(depth>3) {
            result = Math.max(result, sum);
            return;
        }
        else {
            int nx, ny;

            // 이동
            for(int i=0; i<4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];

                // 범위 확인
                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

                if(check[nx][ny]==false) {
                    check[nx][ny] = true;
                    dfs(nx, ny, (sum+map[nx][ny]), depth+1);
                    check[nx][ny] = false;
                }
            }
        }
    }

    // ㅗ 모양 검사
    static void oh(int x, int y) {
        int nx, ny, sum;
        boolean outCheck = false;

        for(int i=0; i<4; i++) {
            sum = 0;
            outCheck = false;
            for(int j=0; j<4; j++) {
                nx = x + oh_x[i][j];
                ny = y + oh_y[i][j];

                // 범위 체크
                if(nx<0 || nx>=n || ny<0 || ny>=m) {
                    outCheck = true;
                    continue;
                }

                sum += map[nx][ny];
            }

            // 범위 안 나갔다면
            if(outCheck==false)
                result = Math.max(sum, result);
        }
    }

}
// 실패코드 백업 (같은 줄만 연속으로 나올 경우 계속 1이 모자라게 나옴🥲)
/*
import java.util.Scanner;
public class Main
{
    static int n,m;
    static int[][] map;
    static boolean[][] cheak;
    static int result=0;

    static int[] dx={0,0,-1,1};
    static int[] dy={-1,1,0,0};

    //ㅗ모양인 경우(ㅜ,ㅗ,ㅓ,ㅏ)
    static int[][] oh_x = {{0, 0, 0, 1}, {1, 1, 1, 0}, {0, 1, 2, 1}, {0, 1, 2, 1}}; //세로
	static int[][] oh_y = {{0, 1, 2, 1}, {0, 1, 2, 1}, {1, 1, 1, 0}, {0, 0, 0, 1}}; //가로

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		n=scan.nextInt();
		m=scan.nextInt();

		map=new int[n][m];
		cheak=new boolean[n][m];

		for(int i=0;i<n;i++){
		    for(int j=0;j<m;j++){
		        map[i][j]=scan.nextInt();
		    }
		}


		//dfs를 이용할 모양 4개를 여기에 넣음
		for(int i=0;i<n;i++){
		    for(int j=0;j<m;j++){
		        cheak[i][j]=true;
		        dfs(i,j,map[i][j],1);
		        cheak[i][j]=false;

		        //oh모양
		        oh(i,j);
		    }
		}
		System.out.println(result);
	}

    public static void dfs(int y, int x, int sum, int depth){
        if(depth>3){
            result = Math.max(result,sum);
            return;
        }
        else{
            int nx,ny;
            for(int i=0;i<4;i++){
                nx=x+dx[i];
                ny=y+dy[i];

                // 범위 확인
                if(nx<0||nx>=n||ny<0||ny>=m){
                    continue;
                }

                if(!cheak[nx][ny]){
                    cheak[nx][ny]=true;
                    dfs(nx,ny,(sum+map[nx][ny]),depth+1);
                    cheak[nx][ny]=false;
                }
            }
        }
    }

    public static void oh(int x, int y){
        int nx,ny,sum;
        boolean outCheck=false;

        for(int i=0;i<4;i++){
            sum=0;
            outCheck=false;
            for(int j=0;j<4;j++){
                nx=x+oh_x[i][j];
                ny=y+oh_y[i][j];

                if(nx<0||nx>=n||ny<0||ny>=m){
                    outCheck=true;
                    continue;
                }
                sum+=map[nx][ny];
            }
            if(!outCheck){
                result=Math.max(sum,result);
            }
        }
    }

}
 */