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

        String[] inputs = br.readLine().split(" ");
        int coinNum = Integer.parseInt(inputs[0]);
        int target = Integer.parseInt(inputs[1]);
        int[] coins = new int[coinNum];

        for (int i = 0; i < coinNum; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int current = 0;
        int needCoin = 0;
        for (int i = coinNum - 1; i >= 0; i--) {
            while (coins[i] <= target - current) {
                current += coins[i];
                needCoin++;
            }
        }

        bw.write(Integer.toString(needCoin));

        bw.flush();
        bw.close();
    }
}
