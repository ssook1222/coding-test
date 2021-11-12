import java.util.Scanner;

class Node{
	int score;
	boolean isEmpty;
	Node next1, next2;
	
	public Node(int score) {
		this.score = score;
		this.isEmpty = true;
	}
	
	public Node nextNode(int score) {
		Node nextNode = new Node(score);
		this.next1 = nextNode;
		return next1;
	}
	
}

public class 주사위윷놀이_01 {
	public static int[] dice = new int[10];
	private static Node[] horse; // 말 4개
	private static int totalCount = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<10; i++) dice[i] = sc.nextInt();
		
		/* 웇놀이 판 만들기 */
		Node start = new Node(0);
		Node node = start;
		for (int i=2; i<=40; i+=2) node = node.nextNode(i);
		
		Node end = node.nextNode(0); // 마지막 도착지점
		end.next1 = null; // 마지막은 아무것도 가리키지 않는것
		
		Node central = new Node(25);

	}

}
