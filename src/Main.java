import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import org.w3c.dom.Node;
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] minimumDP = new int[k + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (weight > k) continue;

            for (int j = k; j >= weight; j--) {
                minimumDP[j] = Math.max(minimumDP[j - weight] + cost, minimumDP[j]);
            }
        }

        bw.write(Integer.toString(minimumDP[k]));
        bw.flush();
        bw.close();
    }
}