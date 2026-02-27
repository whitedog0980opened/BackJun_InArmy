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

        int h = Integer.parseInt(br.readLine());

        int[][] maxMap = new int[3][h + 1];
        int[][] minMap = new int[3][h + 1];
        for (int i = 1; i < h + 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] cells = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            for (int j = 0; j < 3; j++) {
                int prevMaxNum = maxMap[1][i - 1];
                int prevMinNum = minMap[1][i - 1];
                if (j == 0 || j == 1) {
                    prevMaxNum = Math.max(maxMap[0][i - 1], prevMaxNum);
                    prevMinNum = Math.min(minMap[0][i - 1], prevMinNum);
                }
                if (j == 2 || j == 1) {
                    prevMaxNum = Math.max(maxMap[2][i - 1], prevMaxNum);
                    prevMinNum = Math.min(minMap[2][i - 1], prevMinNum);
                }
                maxMap[j][i] = cells[j] + prevMaxNum;
                minMap[j][i] = cells[j] + prevMinNum;
            }
        }
        int maxNum = Math.max(Math.max(maxMap[0][h], maxMap[1][h]), maxMap[2][h]);
        int minNum = Math.min(Math.min(minMap[0][h], minMap[1][h]), minMap[2][h]);

        bw.write(Integer.toString(maxNum) + " " + Integer.toString(minNum));
        bw.flush();
        bw.close();
    }
}