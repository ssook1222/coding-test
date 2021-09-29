import java.util.*;
import java.io.*;
public class Main {
    static int N, classroom[][], freindInfo[][], like[][];

    static int dx[] = {1,-1,0,0};
    static int dy[] = {0,0,1,-1};

    static int findIndex(int number) { // 친구 정보와 관련된 인덱스 찾아주는 함수
        int index = 0;
        for(int i=0; i<N*N; i++)
            if(freindInfo[i][0]==number)
                index = i;
        return index;
    }

    static int[] getPos() {
        int max = 0;
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                max = Math.max(max, like[i][j]);

        ArrayList<int[]> list = new ArrayList<int[]>();
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                if(like[i][j]==max && classroom[i][j]==0)
                    list.add(new int[] {i, j, getZero(i, j)});

        Collections.sort(list, new Comparator<int[]>() { // 정렬합니다.
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[2]==o2[2]) {
                    if(o1[0]==o2[0])
                        return Integer.compare(o1[1], o2[1]);
                    return Integer.compare(o1[0], o2[0]);
                }
                return Integer.compare(o2[2], o1[2]);
            }
        });
        return new int[] {list.get(0)[0], list.get(0)[1]};
    }

    static int getZero(int y, int x) { // 주위 빈칸의 수를 찾아주는 함수
        int count = 0;
        for(int d=0; d<4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(!isRange(ny, nx) || classroom[ny][nx]!=0)
                continue;
            count++;
        }
        return count;
    }

    static void findFriend(int number) { // 주위에 친구가 앉아있는지 찾아주는 함수
        for(int y=0; y<N; y++) {
            for(int x=0; x<N; x++) {
                if(classroom[y][x]==number) {
                    for(int d=0; d<4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if(!isRange(ny, nx) || classroom[ny][nx]!=0)
                            continue;
                        like[ny][nx]++;
                    }
                }
            }
        }
    }

    static boolean isRange(int y, int x) {
        if (x<0 || y<0 || y>=N || x>=N)
            return false;
        return true;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        classroom = new int[N][N];
        freindInfo = new int[N*N][5];
        int result;

        for(int i=0; i<N*N; i++) {
            for(int j=0; j<5; j++)
                freindInfo[i][j] = scan.nextInt();
        }
        //자리배치
        classroom[1][1] = freindInfo[0][0];
        for(int i=1; i<N*N; i++) {
            like = new int[N][N];
            for(int j=1; j<5; j++)
                findFriend(freindInfo[i][j]);
            int[] pos = getPos();
            classroom[pos[0]][pos[1]] = freindInfo[i][0];
        }

        result = getSum();
        System.out.println(result);
    }

    static int getSum() {
        int sum = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int count = 0;
                int curIndex = findIndex(classroom[i][j]);
                for(int d=0; d<4; d++) {
                    int ny = i + dy[d];
                    int nx = j + dx[d];
                    if(isRange(ny, nx)) {
                        for(int k=1; k<5; k++)
                            if(classroom[ny][nx]==freindInfo[curIndex][k])
                                count++;
                    }
                }
                if(count==1) 		sum += 1;
                else if(count==2)	sum += 10;
                else if(count==3)	sum += 100;
                else if(count==4)	sum += 1000;
            }
        }
        return sum;
    }

}

// 실패 코드 백업
//public class Main
//{
//    static int N;
//    static int[] dx={-1,1,0,0};
//    static int[] dy={0,0,-1,1};
//    static int classroom[][],friendInfo[][],like[][];
//
//    public static void friend(int num){
//        for(int r=0; r<N; r++){
//            for(int c=0; c<N;c++){
//                if(classroom[r][c]==num){
//                    for(int d=0; d<4; d++){ //상하좌우 탐색
//                        int nr=r+dy[d];
//                        int nc=c+dx[d];
//                        if(r<0||c<0||r>=N||c>=N){ //범위가 아니면
//                            continue; //패스
//                        }
//                        if(classroom[nr][nc]!=0){ //빈칸이 아니면
//                            continue; //패스
//                        }
//                        like[nc][nr]++;
//                    }
//                }
//            }
//        }
//    }
//
//    public static int[] getPos(){
//        int max = 0; // preference 배열에서 가장 큰 숫자(즉, 여러 친구가 함께있을 때 높아졌을 것임)
//        for(int i=0; i<N; i++)
//            for(int j=0; j<N; j++)
//                max = Math.max(max, like[i][j]);
//
//        ArrayList<int[]> list = new ArrayList<int[]>(); // max가 여러개일수도있기에
//        for(int i=0; i<N; i++)
//            for(int j=0; j<N; j++)
//                if(like[i][j]==max && classroom[i][j]==0)
//                    list.add(new int[] {i, j, getZero(i, j)}); // y, x , 빈칸의 수
//
//        Collections.sort(list, new Comparator<int[]>() { // 정렬합니다.
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if(o1[2]==o2[2]) { // 빈칸 수가 같다면
//                    if(o1[0]==o2[0]) // y 좌표 같다면
//                        return Integer.compare(o1[1], o2[1]); //x가 작은 순
//                    return Integer.compare(o1[0], o2[0]); // y가 작은 순
//                }
//                return Integer.compare(o2[2], o1[2]); //빈칸 내림차순을 통한 큰 값 획득
//            }
//        });
//        return new int[] {list.get(0)[0], list.get(0)[1]};
//
//    }
//
//    static int getZero(int r, int c) { // 주위 빈칸의 수를 찾아주는 함수
//        int count = 0;
//        for(int d=0; d<4; d++) {
//
//            int nr = r + dy[d];
//            int nc = c + dx[d];
//            if(r<0||c<0||r>=N||c>=N){
//                continue;
//            }
//            if(classroom[nc][nr]!=0){
//                continue;
//            }
//            count++;
//        }
//        return count;
//    }
//
//    static int findIndex(int number) { // 친구 정보와 관련된 인덱스 찾아주는 함수
//        int index = 0;
//        for(int i=0; i<N*N; i++)
//            if(friendInfo[i][0]==number)
//                index = i;
//        return index;
//    }
//
//    static int getSum() { //가중치 관련
//        int sum = 0;
//        for(int i=0; i<N; i++) {
//            for(int j=0; j<N; j++) {
//                int count = 0;
//                int curIndex = findIndex(classroom[i][j]); // 정보가 들어있는 인덱스 찾기
//                for(int d=0; d<4; d++) {
//                    int ny = i + dy[d];
//                    int nx = j + dx[d];
//                    if(ny<0||nx<0||ny>=N||nx>=N) {
//                        for(int k=1; k<5; k++)
//                            if(classroom[ny][nx]==friendInfo[curIndex][k])
//                                count++;
//                    }
//                }
//                if(count==1) 		sum += 1;
//                else if(count==2)	sum += 10;
//                else if(count==3)	sum += 100;
//                else if(count==4)	sum += 1000;
//            }
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//        int result;
//        Scanner scan = new Scanner(System.in);
//        N = scan.nextInt();
//        classroom = new int [N][N];
//        friendInfo = new int [N*N][5];
//        like = new int [N][N];
//
//        for(int i=0; i<N*N; i++){
//            for(int j=0;j<5;j++){
//                friendInfo[i][j]=scan.nextInt();
//            }
//        }
//
//        //자리 배치하기
//        classroom[1][1]=friendInfo[0][0]; // 첫 시작은 1,1로
//        for(int i=0;i<N*N;i++){
//            for(int j=1; j<5; j++){
//                friend(friendInfo[i][j]);//주위 친구에 따라 선호도 정보 갱신
//            }
//            int pos[] = getPos();// r,c 좌표 가져오기
//            classroom[pos[0]][pos[1]] = friendInfo[i][0];
//            //선호하는 친구 정보 좌표 찍기
//        }
//        result=getSum();//만족도 구하기
//        System.out.println(result);
//    }
//
//}
