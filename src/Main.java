import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int stairNum = Integer.parseInt(br.readLine());

        int[] stairs = new int[stairNum];
        int[][] stairsMax = new int[stairNum][2];
        for (int i = 0; i < stairNum; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (stairNum == 1) {
            bw.write(Integer.toString(stairs[0]));
            bw.flush();
            bw.close();
            return;
        }


        stairsMax[0][0] = stairs[0];
        stairsMax[1][0] = stairs[1];
        stairsMax[1][1] = stairs[0] + stairs[1];

        for (int i = 2; i < stairNum; i++)  {
            stairsMax[i][1] = stairsMax[i - 1][0] + stairs[i];
            stairsMax[i][0] =Math.max(stairsMax[i - 2][0], stairsMax[i - 2][1]) + stairs[i];
        }

        //int result = dfs(stairs, stairNum, 0, 0, -1);
        int result = Math.max(stairsMax[stairNum - 1][0], stairsMax[stairNum - 1][1]);
        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
    }

}
