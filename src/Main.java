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
        int needQu = Integer.parseInt(st.nextToken());
        int cityN = Integer.parseInt(st.nextToken());
        int[] dp = new int[needQu + 101];
        int[][] cityEffectAd = new int[cityN][2];

        for (int i = 0; i < cityN; i++) {
            st = new StringTokenizer(br.readLine());
            cityEffectAd[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            //init dp
            if (dp[cityEffectAd[i][1]] == 0 || dp[cityEffectAd[i][1]] > cityEffectAd[i][0]) {
                dp[cityEffectAd[i][1]] = cityEffectAd[i][0];
            }
        }
        //dp 진행
        for (int i = 0; i < needQu; i++) {
            if (dp[i] == 0) continue;
            for (int j = 0; j < cityN; j++) {
                int[] crrCity = cityEffectAd[j];
                if (dp[i + crrCity[1]] == 0 || dp[i + crrCity[1]] > dp[i] + crrCity[0]) {
                    dp[i + crrCity[1]] = dp[i] + crrCity[0];
                }
            }
        }
        //최소값 찾기
        int minimum = Integer.MAX_VALUE;
        for (int i = needQu; i < needQu + 100; i++) {
            if (dp[i] == 0) continue;
            minimum = Math.min(minimum, dp[i]);
        }
        bw.write(Integer.toString(minimum));
        bw.flush();
        bw.close();
    }
}