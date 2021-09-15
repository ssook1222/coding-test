import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

class Node_02{
	int x, y;
	public Node_02(int x, int y) {
		this.x = x; this.y = y;
	}
}

public class 연구소_01 {
	
	public static int[] go_X = { 1, -1, 0, 0 };
	public static int[] go_Y = { 0, 0, 1, -1 };
	
	public static int N, M;
	public static int[][] DP, wallDP;
	public static ArrayList<Node_02> virus = new ArrayList<Node_02>();
	public static int max = Integer.MIN_VALUE;
	public static int count=0;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]); M = Integer.parseInt(str[1]);
		DP = new int[N][M]; wallDP = new int[N][M];
		for (int a=0; a<N; a++) {
			str = br.readLine().split(" ");
			for (int b=0; b<M; b++) {
				DP[a][b] = Integer.parseInt(str[b]);
				wallDP[a][b] = DP[a][b];
				if (DP[a][b]==2) {
					virus.add(new Node_02(a, b));
				}
			}
		}
		findwall(0);
		System.out.println(max);

	}
	public static void findwall(int depth) {
		if (depth==3) {
			count++;
			System.out.println("COUNT :: "+count);
			int[][] copyarr = new int[N][M];
			/* 배열 복사하기 */
			for (int a=0; a<N; a++) {
				for (int b=0; b<M; b++) {
					copyarr[a][b] = wallDP[a][b];
				}
			}
			countvirus(copyarr);
			return;
		}
		for (int a=0; a<N; a++) {
			for (int b=0; b<M;b++) {
				if (wallDP[a][b]==0) {
					wallDP[a][b]=1;
					findwall(depth+1);
					wallDP[a][b]=0;
				}
			}
		}
		
	}
	public static void countvirus(int[][] arr) {
		int[][] visited = new int[N][M];
		Queue<Node_02> queue = new LinkedList<Node_02>(virus);
		System.out.println(queue);
		Node_02 node;
		int nx, ny;
		while(!queue.isEmpty()) {
			node = queue.poll();
			for (int i=0; i<4; i++) {
				nx = node.x+go_X[i];
				ny = node.y+go_Y[i];
				if (nx<0||nx>=N||ny<0||ny>=M) continue;
				if (visited[nx][ny]!=1&&arr[nx][ny]==0) {
					visited[nx][ny]=0;
					arr[nx][ny]=2;
					queue.add(new Node_02(nx, ny));
				}
			}
		}
		/*for (int a=0; a<N; a++) {
			System.out.println(Arrays.toString(arr[a]));
		}
		System.out.println("=====================");*/
		counting(arr);
	}
	
	public static void counting(int[][] arr) {
		int sum=0;
		for (int a=0; a<N; a++) {
			for (int b=0; b<M; b++) {
				if (arr[a][b]==0) sum++;
			}
		}
		max = Math.max(max, sum);
	}

}
