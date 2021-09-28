//최종...시간 초과...
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedWriter;


public class Main
{
    static int N, K; // N은 칸 수, K는 내구도가 0인 칸의 개수
    static int durability[];//칸 별 내구도
    static int belt[][]; //컨베이어 벨트 위치
    static boolean robot[][]; //로봇 위치

    public static int result(){
        int check=0;

        while(checkIt()==true){

            moveBelt();
            moveRobot();
            //로봇 적재
            if(durability[belt[0][1]]>0){ //내구도가 0보다 크면 로봇 적재
                robot[0][1]=true;
                durability[belt[0][1]]--;
                check++;
            }

        }
        return check;
    }

    public static boolean checkIt(){
        int count = 0;
        for(int i=1;i<=2*N;i++){
            if(durability[i]==0){ //내구도가 0이면 카운트하기
                count++;
            }
        }
        return count<K; //조건보다 카운트 수가 적다면 계속 진행
    }

    public static void moveBelt(){
        int tmp = belt[1][1]; // 시작점 중간 세이브

        //컨베이어 이동
        for (int i=1; i<N; i++){
            belt[1][i]=belt[1][i+1];
        }

        belt[1][N]=belt[0][N];

        for(int i=N;i>=2;i--){
            belt[0][i]=belt[0][i-1];
            robot[0][i]=robot[0][i-1];
        }

        belt[0][1] = tmp;
        robot[0][1]=false;
        robot[0][N]=false; // 첫 벨트의 시작점과 끝점을 false로
    }

    public static void moveRobot(){
        //로봇 이동
        for(int i=N-1;i>=1;i--){
            if (robot[0][i]==true && robot[0][i+1]==false && durability[belt[0][i+1]]>0) {
                robot[0][i]=false;
                robot[0][i+1] = true;
                durability[belt[0][i+1]]--;
                // 벨트 내구도 감소
            }
        }
        robot[0][N]=false; // 첫 줄 벨트 끝점 false로
    }


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt=new int[2][N+1]; //2줄씩 나뉘어 있으므로 [2][N+1]로 표기
        robot=new boolean[2][N+1];

        durability=new int[2*N+1];

        st=new StringTokenizer(br.readLine()," ");

        for(int i=1;i<2*N+1;i++){
            durability[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=1,n=1; i<N+1;i++){
            belt[0][i]=n++;
        }

        for(int i=N,n=1;i>0;i--){
            belt[1][i]=n++;
        }

        bw.write(result() + "\n");
        bw.flush();
    }
}


//시간초과 코드 2
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.IOException;
//import java.util.StringTokenizer;
//
//
//public class Main
//{
//    static int N, K; // N은 칸 수, K는 내구도가 0인 칸의 개수
//    static int durability[];//칸 별 내구도
//    static int belt[][]; //컨베이어 벨트 위치
//    static boolean robot[][]; //로봇 위치
//
//    public static int result(){
//        int check=0;
//
//        while(checkIt()==true){
//
//            int tmp = belt[1][1]; // 시작점 중간 세이브
//
//            //컨베이어 이동
//            for (int i=1; i<N; i++){
//                belt[1][i]=belt[1][i+1];
//            }
//
//            belt[1][N]=belt[0][N];
//
//            for(int i=N;i>=2;i--){
//                belt[0][i]=belt[0][i-1];
//                robot[0][i]=robot[0][i-1];
//            }
//
//            belt[0][1] = tmp;
//            robot[0][1]=false;
//            robot[0][N]=false; // 첫 벨트의 시작점과 끝점을 false로
//
//            //로봇 이동
//            for(int i=N-1;i>=1;i--){
//                if (robot[0][i]==true && robot[0][i+1]==false && durability[belt[0][i+1]]>0) {
//                    robot[0][i]=false;
//                    robot[0][i+1] = true;
//                    durability[belt[0][i+1]]--;
//                    // 벨트 내구도 감소
//                }
//            }
//
//            robot[0][N]=false; // 첫 줄 벨트 끝점 false로
//
//            //로봇 적재
//            if(durability[belt[0][1]]>0){ //내구도가 0보다 크면 로봇 적재
//                robot[0][1]=true;
//                durability[belt[0][1]]--;
//                check++;
//            }
//
//        }
//        return check;
//    }
//
//    public static boolean checkIt(){
//        int count = 0;
//        for(int i=1;i<=2*N;i++){
//            if(durability[i]==0){ //내구도가 0이면 카운트하기
//                count++;
//            }
//        }
//        return count<K; //조건보다 카운트 수가 적다면 계속 진행
//    }
//
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine()," ");
//        N = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//
//        belt=new int[2][N+1]; //2줄씩 나뉘어 있으므로 [2][N+1]로 표기
//        robot=new boolean[2][N+1];
//
//        durability=new int[2*N+1];
//
//        st=new StringTokenizer(br.readLine()," ");
//
//        for(int i=1;i<2*N+1;i++){
//            durability[i]=Integer.parseInt(st.nextToken());
//        }
//
//        for(int i=1,n=1; i<N+1;i++){
//            belt[0][i]=n++;
//        }
//
//        for(int i=N,n=1;i>0;i--){
//            belt[1][i]=n++;
//        }
//
//        System.out.println(result()+"\n");
//    }
//}


//시간초과 코드 1
//import java.util.Scanner;
//
//
//public class pro20055
//{
//
//    static int N, K; // N은 칸 수, K는 내구도가 0인 칸의 개수
//    static int durability[];//칸 별 내구도
//    static int belt[][]; //컨베이어 벨트 위치
//    static boolean robot[][]; //로봇 위치
//
//    public static int result(){
//        int check=0;
//
//        while(checkIt()==true){
//
//            int tmp = belt[1][1]; // 시작점 중간 세이브
//
//            //컨베이어 이동
//            for (int i=1; i<N; i++){
//                belt[1][i]=belt[1][i+1];
//            }
//
//            belt[1][N]=belt[0][N];
//
//            for(int i=N;i>=2;i--){
//                belt[0][i]=belt[0][i-1];
//                robot[0][i]=robot[0][i-1];
//            }
//
//            belt[0][1] = tmp;
//            robot[0][1]=false;
//            robot[0][N]=false; // 첫 벨트의 시작점과 끝점을 false로
//
//            //로봇 이동
//            for(int i=N-1;i>=1;i--){
//                if (robot[0][i]==true && robot[0][i+1]==false && durability[belt[0][i+1]]>0) {
//                    robot[0][i]=false;
//                    robot[0][i+1] = true;
//                    durability[belt[0][i+1]]--;
//                    // 벨트 내구도 감소
//                }
//            }
//
//            robot[0][N]=false; // 첫 줄 벨트 끝점 false로
//
//            //로봇 적재
//            if(durability[belt[0][1]]>0){ //내구도가 0보다 크면 로봇 적재
//                robot[0][1]=true;
//                durability[belt[0][1]]--;
//                check++;
//            }
//
//        }
//        return check;
//    }
//
//    public static boolean checkIt(){
//        int count = 0;
//        for(int i=1;i<=2*N;i++){
//            if(durability[i]==0){ //내구도가 0이면 카운트하기
//                count++;
//            }
//        }
//        return count<K; //조건보다 카운트 수가 적다면 계속 진행
//    }
//
//
//    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        N=scan.nextInt();
//        K=scan.nextInt();
//
//        belt=new int[2][N+1]; //2줄씩 나뉘어 있으므로 [2][N+1]로 표기
//        robot=new boolean[2][N+1];
//
//        durability=new int[2*N+1];
//
//        for(int i=1;i<2*N+1;i++){
//            durability[i]=scan.nextInt();
//        }
//
//        for(int i=1,n=1; i<N+1;i++){
//            belt[0][i]=n++;
//        }
//
//        for(int i=N,n=1;i>0;i--){
//            belt[1][i]=n++;
//        }
//
//        System.out.println(result()+"\n");
//    }
//}
