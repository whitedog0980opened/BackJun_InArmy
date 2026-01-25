import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list :
//want to study : hashTable
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //instant Stack
    //30503
    //not tested
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapM = Integer.parseInt(st.nextToken());
        int mapH = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int numInM = mapM / (2 * m) + ((mapM % (2 * m) >= m) ? 1 : 0);
        int numInH = mapH / (2 * n) + ((mapH % (2 * n) >= n) ? 1 : 0);
        int result = numInH * numInM;

        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
    }
}