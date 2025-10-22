import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int x = Integer.parseInt(br.readLine());

        int[] dp = new int[x + 1]; // dp = minumun steps to reach i
        dp[1] = 0;
        for (int i = 2; i <= x; i++) {
            // dp[i - 1]가 minimumTry로부터 1을 뺀 결과라면
            int minimumTry = dp[i - 1] + 1; 
            // dp[i / 3]가 minimumTry로부터 3을 나눈 결과라면
            if (i % 3 == 0) {
                minimumTry = Math.min(minimumTry, dp[i / 3] + 1);
            }
            // dp[i / 2]가 minimumTry로부터 2를 나눈 결과라면
            if (i % 2 == 0) {
                minimumTry = Math.min(minimumTry, dp[i / 2] + 1);
            }
            dp[i] = minimumTry;
        }

        bw.write(String.valueOf(dp[x]));
        bw.flush();
        bw.close();
    }

}
