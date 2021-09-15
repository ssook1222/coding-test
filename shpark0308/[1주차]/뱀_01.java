import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Node_01{
	int x, y;
	public Node_01(int x, int y) {
		this.x = x; this.y = y;
	}
}
class Direct_01{
	int t;
	String s;
	public Direct_01(int t, String s) {
		this.t = t; this.s = s;
	}
}
public class 뱀_01 {
	public static int[] go_X = { 0, 1,0,-1,0};
	public static int[] go_Y = { 0, 0,-1,0,1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] DP = new int[N][N];
		for (int i=0; i<K;i++) DP[sc.nextInt()-1][sc.nextInt()-1]=1;
		int DR = sc.nextInt();
		ArrayList<Direct_01> treemap = new ArrayList<Direct_01>();
		for (int i=0; i<DR;i++) treemap.add(new Direct_01(sc.nextInt(), sc.next()));
		
		for (int a=0; a<DP[0].length;a++) {
			System.out.println(Arrays.toString(DP[a]));
		}
		
		System.out.println("==========================");
		
		System.out.println(treemap);
		
		ArrayList<Node_01> queue = new ArrayList<Node_01>();
		
		queue.add(new Node_01(0, 0));
		int time=0, dir=4, nx, ny;
		Node_01 node;
		boolean flag = true;
		
		while(true) {
			time++;
			node = queue.get(0);
			nx = node.x+go_X[dir];
			ny = node.y+go_Y[dir];
			if (nx<0||nx>=N||ny<0||ny>=N) break;
			if (queue.size()>1) {
				for (int a=0; a<queue.size();a++) {
					if (queue.get(a).x==nx&&queue.get(a).y==ny) {
						System.out.println("나 여기야"+nx+"  "+ny);
						flag = false;
						break;
					}
					
				}
				if (flag==false) break;
			}
			queue.add(0,new Node_01(nx, ny));			
			if (DP[nx][ny]==1) DP[nx][ny]=0;
			else {
				if (queue.size()>1) queue.remove(queue.size()-1);
			}
			if (treemap.size()>0&&time==treemap.get(0).t) {
				if (treemap.get(0).s.equals("D")) {
					if (dir==4) dir=1;
					else dir++;
				}
				else if (treemap.get(0).s.equals("L")) {
					if (dir==1) dir=4;
					else dir--;
				}
				treemap.remove(0);
			}
			
			/* 여기서는 출력하는 부분 */
			System.out.println("TIME :: "+time);
			for (int a=0; a<queue.size();a++) {
				System.out.print("["+queue.get(a).x+", "+queue.get(a).y+"]");
			}
			System.out.println();
			
		}
		System.out.println(time);

	}

}
