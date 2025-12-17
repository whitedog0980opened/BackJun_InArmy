import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] cases = new int[12];
        cases[1] = 1;
        cases[2] = 2;
        cases[3] = 4;
        //get inputs
        int testCase = Integer.parseInt(br.readLine());
        int max = 0;
        int[] testCases = new int[testCase];
        for (int i = 0; i < testCase; i++) {
            int crr = Integer.parseInt(br.readLine());
            testCases[i] = crr;
            if (max < crr) max = crr;
        }

        for (int i = 4; i < max + 1; i++) {
            cases[i] = cases[i - 1] + cases[i - 2] + cases[i - 3];
        }

        bw.write(Integer.toString(cases[testCases[0]]));
        for (int i = 1; i < testCase; i++) {
            bw.newLine();
            bw.write(Integer.toString(cases[testCases[i]]));
        }

        bw.flush();
        bw.close();
    }
}
