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

public class �ֻ���������_01 {
	public static int[] dice = new int[10];
	private static Node[] horse; // �� 4��
	private static int totalCount = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i=0; i<10; i++) dice[i] = sc.nextInt();
		
		/* �U���� �� ����� */
		Node start = new Node(0);
		Node node = start;
		for (int i=2; i<=40; i+=2) node = node.nextNode(i);
		
		Node end = node.nextNode(0); // ������ ��������
		end.next1 = null; // �������� �ƹ��͵� ����Ű�� �ʴ°�
		
		Node central = new Node(25);

	}

}
