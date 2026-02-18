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

        int pr = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] prArr = new long[pr + 1];
        prArr[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i < pr + 1; i++) {
            prArr[i] = prArr[i - 1] + Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int quotientA = Math.floorDiv(a, pr);
        int quotientB = Math.floorDiv(b, pr);
        int remainderA = Math.floorMod(a, pr);
        int remainderB = Math.floorMod(b, pr);

        long total = prArr[remainderB] - prArr[remainderA] + (quotientB - quotientA) * prArr[pr];
        bw.write(Long.toString(total));
        bw.flush();
        bw.close();
    }

}