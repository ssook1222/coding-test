import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 인구이동_01 {
	public static int N,L,R;
	public static int[][] DP;
	public static int[][] ARR; // ARR 초기화
	public static int[] go_X = { -1, 1, 0, 0};
	public static int[] go_Y = { 0, 0, -1, 1};
	public static Queue<Point> queue = new LinkedList<Point>();
	public static ArrayList<ArrayList<Point>> region = new ArrayList<ArrayList<Point>>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); L = sc.nextInt(); R = sc.nextInt();
		DP = new int[N][N]; ARR = new int[N][N];
		for (int a=0; a<N; a++) {
			for (int b=0; b<N; b++) DP[a][b] = sc.nextInt();
		}
		// 인접한 것에 대한 차이를 계산해야함
		
		/* 1. 일단 영역을 나누기 -> // 일단 하나로 다 묽기 */ 
		int total =0;
		while(true) {
			regioncheck();
			if (region.size()==(N*N)) break;
			total++;
			valueinput();
			queue.clear();
			region.clear();
			clearARR();
			
		}
		System.out.println(total);
		
	}
	public static Point check() {
		for (int a=0; a<N; a++) {
			for (int b=0; b<N; b++) {
				if (ARR[a][b]!=1) {
					Point point = new Point(a,b);
					return point;
				}
			}
		}
		return null;
	}
	public static void regioncheck() {
		int count=0;
		while(count<(N*N)) {
			Point point = check();
			if (point!=null) {
				queue.add(point);
			}
			ArrayList<Point> arr = new ArrayList<Point>();
			while(!queue.isEmpty()) {
				point = queue.poll();
				if (ARR[point.x][point.y]!=0) continue;
				ARR[point.x][point.y] = 1;
				arr.add(point);
				count++;
				for (int i=0; i<4; i++) {
					int nx = point.x+go_X[i];
					int ny = point.y+go_Y[i];
					if (nx<0||nx>=N||ny<0||ny>=N) continue;
					if (ARR[nx][ny]!=0) continue;
					int subtrack = Math.abs(DP[nx][ny]-DP[point.x][point.y]);
					if (subtrack>=L&&subtrack<=R) {
						queue.add(new Point(nx, ny));
					}
				}
			}
			region.add(arr);
		}
		/*for (int i=0; i<region.size();i++) {
			System.out.println(region.get(i));
		}
		System.out.println("--------------------------");*/
	}
	public static void valueinput() {
		for (int a=0; a<region.size();a++) {
			int sum=0;
			for (int b=0; b < region.get(a).size();b++) {
				Point point = region.get(a).get(b);
				sum+=DP[point.x][point.y];
			}
			int value = sum / region.get(a).size();
			for (int b=0; b<region.get(a).size();b++) {
				Point point = region.get(a).get(b);
				DP[point.x][point.y] = value;
			}
			
		}
	}
	
	public static void clearARR() {
		for (int a=0; a<N; a++) {
			for(int b=0; b<N; b++) ARR[a][b]=0;
	}
	}
}
