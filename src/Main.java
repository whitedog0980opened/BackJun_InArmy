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
    //1562
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int numLen = Integer.parseInt(br.readLine());

        long[][][] map = new long[numLen][10][1024];
        for (int i = 1; i < 10; i++) {
            map[0][i][1 << i] = 1;
        }
        for (int i = 1; i < numLen; i++) {
            //if last is 0 exception
            for (int k = 1; k < 1024; k++) {
                int crrBitMask = k | 1;
                map[i][0][crrBitMask] += (map[i - 1][1][k]) % 1000000000;
            }
            //if last is 9 exception
            for (int k = 1; k < 1024; k++) {
                int crrBitMask = k | 512;
                map[i][9][crrBitMask] += (map[i - 1][8][k]) % 1000000000;
            }
            for (int j = 1; j < 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    int crrBitMask = k | (1 << j);
                    map[i][j][crrBitMask] += (map[i - 1][j - 1][k] % 1000000000 +  map[i - 1][j + 1][k] % 1000000000) % 1000000000;
                }
            }
        }
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = (map[numLen - 1][i][1023] + result) % 1000000000;
        }
        bw.write(Long.toString(result));
        bw.flush();
        bw.close();
    }
}