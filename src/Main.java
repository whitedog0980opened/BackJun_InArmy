import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstInput = br.readLine().split(" ");
        int numslength = Integer.parseInt(firstInput[0]);
        int tries = Integer.parseInt(firstInput[1]);

        String[] numsInput = br.readLine().split(" ");
        int[] nums = new int[numslength + 1];
        int[] numsDP = new int[numslength + 1];
        for (int i = 1; i < numslength + 1; i++) {
            nums[i] = Integer.parseInt(numsInput[i - 1]);
            numsDP[i] = numsDP[i - 1] + nums[i];
        }

        for (int i = 0; i < tries; i++) {
            String[] inputArea = br.readLine().split(" ");
            int from = Integer.parseInt(inputArea[0]);
            int to = Integer.parseInt(inputArea[1]);
            int sum1 = numsDP[to];
            int sum2 = numsDP[from - 1];

            bw.write(Integer.toString(sum1 - sum2));
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }
}
