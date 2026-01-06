import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int houseNum = Integer.parseInt(br.readLine());
        //r = 0, g = 1, b = 2
        int[][] houseColorCost = new int[houseNum][3];
        //sums of pattern
        
        for (int i = 0; i < houseNum; i++) {
            String[] costs = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                houseColorCost[i][j] = Integer.parseInt(costs[j]);
            }
        }

        for (int i = 1; i < houseNum; i++) {
            for (int j = 0; j < 3; j++) {
                houseColorCost[i][j] += Math.min(houseColorCost[i - 1][(j + 1) % 3], houseColorCost[i - 1][(j + 2) % 3]);
            }
        }

        int result = Math.min(Math.min(houseColorCost[houseNum - 1][0], houseColorCost[houseNum - 1][1]), houseColorCost[houseNum - 1][2]);

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}
