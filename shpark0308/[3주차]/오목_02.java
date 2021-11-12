import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 오목_02 {
	public static int[][] DP = new int[19][19];
	public static int[] dir = {1, 1, 1, 1};
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		for (int a=0; a<19; a++) {
			str = br.readLine().split(" ");
			for (int b=0; b<19; b++) DP[a][b] = Integer.parseInt(str[b]);
		}
		
		for (int a=0; a<19; a++) {
			for (int b=0; b<19; b++) {
				if (DP[a][b]==1) searching(a,b,1);
				else if (DP[a][b]==2) searching(a,b,2);
			}
		}
		System.out.println(0);
	}
	public static void searching(int x, int y, int num) {
		// 5번씩 확인하기
		for (int i=1; i<5; i++) { // right
			int nx = x;
			int ny = y+i;
			if (nx<0||nx>=19||ny<0||ny>=19) continue;
			dir[0]++;
		}
		for (int i=1; i<5; i++) { // down
			int nx = x+i;
			int ny = y;
			if (nx<0||nx>=19||ny<0||ny>=19) continue;
			dir[1]++;
		}
		for (int i=1; i<5; i++) { // right_down
			int nx = x+i;
			int ny = y+i;
			if (nx<0||nx>=19||ny<0||ny>=19) continue;
			dir[2]++;
		}
		for (int i=1; i<5; i++) { // right_up
			int nx = x-i;
			int ny = y+i;
			if (nx<0||nx>=19||ny<0||ny>=19) continue;
			dir[3]++;
		}
		
		if (dir[0]==5) {
			int nx = x; int ny = y-1;
			if (nx>=0&&nx<19&&ny>=0&&ny<19&&DP[nx][ny]!=num) {
				// 반대편 탐색
				ny = y+5;
				if (nx>=0&&nx<19&&ny>=0&&ny<19) {
					if (DP[nx][ny]!=num) {
						print(x,y,num);
					}
				}
				else print(x,y,num);
			}
		}
		if (dir[1]==5) {
			int nx = x-1; int ny = y;
			if (nx>=0&&nx<19&&ny>=0&&ny<19&&DP[nx][ny]!=num) {
				// 반대편 탐색
				nx = x+5;
				if (nx>=0&&nx<19&&ny>=0&&ny<19) {
					if (DP[nx][ny]!=num) {
						print(x,y,num);
					}
				}
				else print(x,y,num);
			}
		}
		if (dir[2]==5) {
			int nx = x-1; int ny = y-1;
			if (nx>=0&&nx<19&&ny>=0&&ny<19&&DP[nx][ny]!=num) {
				// 반대편 탐색
				nx = x+5; ny = y+5;
				if (nx>=0&&nx<19&&ny>=0&&ny<19) {
					if (DP[nx][ny]!=num) {
						print(x,y,num);
					}
				}
				else print(x,y,num);
			}
		}
		if (dir[3]==5) {
			int nx = x+1; int ny = y-1;
			if (nx>=0&&nx<19&&ny>=0&&ny<19&&DP[nx][ny]!=num) {
				// 반대편 탐색
				nx = x-5; ny = y+5;
				if (nx>=0&&nx<19&&ny>=0&&ny<19) {
					if (DP[nx][ny]!=num) {
						print(x,y,num);
					}
				}
				else print(x,y,num);
			}
		}
		dir[0]=1;dir[1]=1;dir[1]=1;dir[3]=1;
	}
	public static void print(int x, int y, int num) {
		System.out.println(num);
		System.out.println((x+1)+" "+(y+1));
		System.exit(0);
	}
	
}
