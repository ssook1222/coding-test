import java.io.*;
import java.util.*;


public class Main
{
    static boolean check;
    static int L, C;
    static char[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        L = Integer.parseInt(st.nextToken()); //알파벳 개수
        C = Integer.parseInt(st.nextToken());

        arr=new char[C];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0;i<C;i++){
            arr[i]=st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        dfs(0,"");
    }

    public static void checkit(String str){
        check = false;
        if(str.contains("a") || str.contains("e") || str.contains("i") || str.contains("o") | str.contains("u")){
            str = str.replace("a", "");
            str = str.replace("e", "");
            str = str.replace("i", "");
            str = str.replace("o", "");
            str = str.replace("u", "");

            if (str.length() >= 2) {
                check=true;
            }
        }

        else{check=false;}
    }

    public static String dfs(int idx, String str){
        checkit(str);
        if(str.length()==L&&check==true){
            System.out.println(str);
            return str;
        }

        if (idx >= C) {
            return str;
        }
        dfs(idx + 1, str + arr[idx]);
        dfs(idx + 1, str);
        return str;
    }
}
