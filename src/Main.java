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

        //map[i][j] -> i = depth (숫자의 길이), j = 끝나는 숫자
        //map[i][j] = 숫자의 길이가 i 인 j로 끝나는 경우의 수   
        //시간 복잡도 -> O(10n)
        int[][] map = new int[numLen][10];
        Arrays.fill(map[0], 1);
        for (int i = 1; i < numLen; i++) {
            map[i][0] += map[i - 1][1];
            map[i][9] += map[i - 1][8];
            for (int j = 1; j < 9; j++) {
                map[i][j] += (map[i - 1][j - 1] + map[i - 1][j + 1]) % 1000000000;
            }
        }

        // 이제 map[i][j][k] = 숫자의 길이가 i인 j로 시작해서 k로 끝나는 경우의 수 알고리즘 짜야함
        // 시간 복잡도 -> O(30n) (시작과 끝의 크기차이는 최대 3이다.)


        bw.flush();
        bw.close();
    }
}