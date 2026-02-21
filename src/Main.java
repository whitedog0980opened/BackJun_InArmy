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
        int[] prevLine = {Integer.parseInt(br.readLine())}; //firstLine (root)
        int maxValue = prevLine[0];
        for (int i = 1; i < h; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] crrLine = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                int crrNum = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    crrLine[j] = crrNum + prevLine[j];
                } else if (j == i) {
                    crrLine[j] = crrNum + prevLine[j - 1];
                } else {
                    crrLine[j] = Math.max(crrNum + prevLine[j - 1], crrNum + prevLine[j]);
                }
                maxValue = Math.max(crrLine[j], maxValue);
            }
            prevLine = crrLine;
        }

        bw.write(Integer.toString(maxValue));
        bw.flush();
        bw.close();
    }
}