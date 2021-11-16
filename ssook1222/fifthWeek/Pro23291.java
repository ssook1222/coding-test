import java.util.*;
import java.io.*;

public class Pro23291
{
    static int N,K;
    static final int[] dr = { -1, 0, 1, 0 };
    static final int[] dc = { 0, 1, 0, -1 };
    static int[][] fish_map;
    static int[] row_size;
    static int row_start = 100;
    static int row_end = 101;
    static int col_start = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.valueOf(st.nextToken());
        K = Integer.valueOf(st.nextToken());

        row_size[row_start] = N;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fish_map[row_start][i] = Integer.valueOf(st.nextToken());
        }

        int min_fish_num = Integer.MIN_VALUE;
        int max_fish_num = Integer.MAX_VALUE;
        int iter = 0;

        while (true){
            //바닥에서 물고기가 적은 어항을 찾는 중
            for (int i = 0; i < row_size[row_start]; i++) {
                max_fish_num = Math.max(fish_map[row_start][i], max_fish_num);
                min_fish_num = Math.min(fish_map[row_start][i], min_fish_num);
            }

            if (max_fish_num - min_fish_num <= K){ //어항 차가 K이하면 break
                break;
            }

            // 가장 물고기 개수가 적은 어항에 물고기를 1개 추가
            for (int i = 0; i < row_size[row_start]; i++) {
                if (fish_map[row_start][i] == min_fish_num)
                    fish_map[row_start][i]++;
            }

            int nrow = 1, ncol = 1;
            int nrow_start;
            int grow, gcol;

            while(true){
                int bcol = ncol;
                if (nrow == ncol) {
                    nrow++;
                    nrow_start = row_start - 1;
                }

                else {
                    ncol = nrow;
                    nrow_start = row_start;
                }

                if (nrow * ncol > N){
                    break;
                }


                Deque<Integer> q = new ArrayDeque<>();
                for (int i = col_start; i < col_start + bcol; i++)
                    for (int j = row_start; j < row_end; j++){
                        q.addLast(fish_map[j][i]);}

                row_start = nrow_start;
                col_start += bcol;


                for(int i = row_end-2; i >= row_start; i--) {
                    row_size[i] = col_start + ncol;
                    for (int j = col_start; j < col_start + ncol; j++) {
                        int num = q.getLast();
                        q.removeLast();
                        fish_map[i][j] = num;
                    }
                }

                grow = nrow;
                gcol = ncol;

            }

            //공중 부양 작업 이후, 물고기 수 조절
            int diff_map[][]=new int[101][101];
            for (int i = 0; i < 101; i++)
                for (int j = 0; j < 101; j++){
                    diff_map[i][j] = 0;
                }

            for (int i = row_start; i < row_end; i++) {
                for (int j = col_start; j < row_size[i]; j++) {
                    for (int k = 0; k < 4; k++) {
                        nrow = i + dr[k];
                        ncol = j + dc[k];
                        if (nrow < row_start || nrow > row_end - 1)
                            continue;
                        if (ncol < col_start || ncol > row_size[i] - 1)
                            continue;
                        if (ncol > row_size[nrow] - 1)
                            continue;
                        if (fish_map[i][j] <= fish_map[nrow][ncol])
                            continue;
                        int diff = 0;
                        diff = (int)(fish_map[i][j] - fish_map[nrow][ncol]) / 5;
                        diff_map[i][j] -= diff;
                        diff_map[nrow][ncol] += diff;
                    }
                }
            }

            for (int i = row_start; i < row_end; i++)
                for (int j = col_start; j < row_size[i]; j++){
                    fish_map[i][j] += diff_map[i][j];
                }

            Stack<Integer> s = new Stack<>();
            gcol=ncol;
            for (int i = col_start; i < col_start + gcol; i++){
                for (int j = row_end - 1; j >= row_start; j--){
                    s.push(fish_map[j][i]);
                }
            }

            for (int i = col_start + gcol; i < row_size[row_end - 1]; i++){
                s.push(fish_map[row_end - 1][i]);
            }

            for (int i = 0; i < s.size(); i++){
                fish_map[row_end - 1][i] = s.elementAt(i);
            }

            for (int i = row_start; i < row_end; i++){
                row_size[i] = 0;
            }
            row_size[row_end - 1] = N;
            row_start = 100;
            row_end = 101;
            col_start = 0;

            int size = s.size();
            for (int j = 0; j < size/2; j++){
                fish_map[row_start - 1][size/2 + j] = s.elementAt(size/2 - j - 1);
            }

            row_start = 99;
            row_size[row_start] = N;
            row_end = 101;
            col_start = size / 2;

            Queue<Integer> v1 = new LinkedList<>();
            for (int i = row_end - 1; i >= row_start; i--)
                for(int j = col_start + col_start/2 - 1; j >= col_start; j--)
                    v1.add(fish_map[i][j]);

            for (int i = row_start - 2; i < row_start; i++) {
                for (int j = col_start + col_start/2; j < N; j++) {
                    int num = v1.poll();
                    fish_map[i][j] = num;
                }
                row_size[i] = N;
            }
            row_start = 97;
            col_start = col_start + col_start / 2;

            // 주변과 열교환
            int diff_map2[][]=new int[101][101];
            for (int i = 0; i < 101; i++)
                for (int j = 0; j < 101; j++)
                    diff_map2[i][j] = 0;

            for (int i = row_start; i < row_end; i++) {
                for (int j = col_start; j < row_size[i]; j++) {
                    for (int k = 0; k < 4; k++) {
                        nrow = i + dr[k];
                        ncol = j + dc[k];
                        if (nrow < row_start || nrow > row_end - 1)
                            continue;
                        if (ncol < col_start || ncol > row_size[i] - 1)
                            continue;
                        if (ncol > row_size[nrow] - 1)
                            continue;
                        if (fish_map[i][j] <= fish_map[nrow][ncol])
                            continue;
                        int diff = 0;
                        diff = (int)(fish_map[i][j] - fish_map[nrow][ncol]) / 5;
                        diff_map2[i][j] -= diff;
                        diff_map2[nrow][ncol] += diff;
                    }
                }
            }

            for (int i = row_start; i < row_end; i++)
                for (int j = col_start; j < row_size[i]; j++)
                    fish_map[i][j] += diff_map2[i][j];

            Queue<Integer> q3=new LinkedList<>();;
            for (int i = col_start; i < N; i++)
                for (int j = row_end - 1; j >= row_start; j--)
                    q3.add(fish_map[j][i]);

            int q3size = q3.size();
            for (int i = 0; i < q3size; i++) {
                int num = q3.peek();
                fish_map[row_end - 1][i] = num;
            }

            for (int i = row_start; i < row_end; i++)
                row_size[i] = 0;

            row_size[row_end - 1] = N;
            row_start = 100;
            row_end = 101;
            col_start = 0;

            min_fish_num = 1000001;
            max_fish_num = -1;
            iter++;

        }

        System.out.println(iter);

    }
}
