import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String ori = br.readLine();
        int repeat = Integer.parseInt(br.readLine());
        int preStart = 0;
        int preEnd = ori.length();
        StringTokenizer st;
        for (int i = 0; i < repeat; i++) {
            st = new StringTokenizer(br.readLine());
            preStart = preStart + Integer.parseInt(st.nextToken());
            preEnd = preStart + Integer.parseInt(st.nextToken());
        }
        bw.write(ori.substring(preStart, preEnd));
        
        bw.flush();
        bw.close();
    }

}