import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 다른 곳에 나와있는 테스트 케이스는 전부 맞는데 30프로에서 계속 틀림 */
public class 오목_01 {
	public static int[][] DP = new int[19][19];
	public static int[] go_X = { 0, 1, 1, 1, 0, -1, -1, -1, -1 }; // 0. right 1. right_down 2. down 3. left_down
	public static int[] go_Y = { 1, 1, 0, -1, -1, -1, 0, 1};

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		// 굳이 더 많은 자료구조를 사용해서 메모리를 차지 하지 말기
		for (int a=0; a<19; a++) {
			str = br.readLine().split(" ");
			for (int b=0; b<19; b++) DP[a][b] = Integer.parseInt(str[b]);
		}
		boolean flag = false;
		for (int a=0; a<19&&!flag; a++) {
			for (int b=0; b<19 && !flag; b++) {
				if (DP[a][b]==1) {
					flag = searching(a , b, 1); // 일단 해보기
				}
				else if (DP[a][b]==2) {
					flag = searching(a , b, 1);
				}
			}
		}
		if (!flag) System.out.println(0);
		

	}
	public static boolean searching(int x, int y, int num) {
		int nx, ny, count;
		for (int i=0; i<4; i++) { // i<4로 고치기 /* 시작점이 아니라 가장 왼쪽에 있는 위치
			for (nx = x, ny = y, count=0; nx>=0&&nx<19&&ny>=0&&ny<19&&DP[nx][ny]==num ; count++) {
				nx+=go_X[i];
				ny+=go_Y[i];
			}
			//System.out.println("count = "+count);
			if (count==5) { // count가 5일 경우에서만 
				/* 양 끝을 확인해야하는 경우 */ // 각각 반대를 확인해줘야함
				System.out.println("X = "+x+" Y = "+y+" I = "+i);
				boolean flag2 = true;
				nx = x + go_X[i]*5;
				ny = y + go_Y[i]*5;
				System.out.println("1. nx = "+nx+" ny = "+ny);
				if (nx>=0&&nx<19&&ny>=0&&ny<19&& DP[nx][ny]==num) {
					System.out.println("nx = "+nx+" ny = "+ny);
					flag2 = false;
				}
				nx = x + go_X[i+4]*1;
				ny = y + go_Y[i+4]*1;
				System.out.println("2. nx = "+nx+" ny = "+ny);
				if (nx>=0&&nx<19&&ny>=0&&ny<19&& DP[nx][ny]==num) {
					System.out.println("nx = "+nx+" ny = "+ny);
					flag2 = false;
				}
				if (flag2) {
					if (i==3) {
						x = x + go_X[i]*4;
						y = y + go_Y[i]*4;
					}
					System.out.println(num);
					System.out.println((x+1)+" "+(y+1));
					return true;
				}
			}
		}
		return false;		
	}

}
