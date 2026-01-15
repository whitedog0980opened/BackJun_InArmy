import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라)
//want to study : hashTable
//need to review = 5430 ! deque


// Dijkstra <- 이거 이용하라는데 공부 필요할듯.
1 3 6
0 256 0
512 0
512 87
1 3 18
0 256 0
512 0
512 91
// 리펙토링 필요
//n = 1. m = 1 일때 51에서 인덱스 -1 오류 발생
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int blocks = Integer.parseInt(st.nextToken());

        int[] map = new int[n * m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i * m + j] = Integer.parseInt(st.nextToken());
            }
        } 

        int targetIndex = (int) Math.round((n * m - 1) * 2.0 / 3.0);
        if (targetIndex > 256) targetIndex = 256;
        int needBlocks = 0;
        int spentTime = 0;

        Arrays.sort(map);

        //cut over targerIndex
        for (int i = 0; i < n * m; i++) {
            if (map[i] > map[targetIndex]) {
                int toBrakeBlocks = map[i] - map[targetIndex];
                blocks += toBrakeBlocks;
                spentTime += toBrakeBlocks * 2;
                map[i] -= toBrakeBlocks;
            }
            else if (map[i] < map[targetIndex]) {
                int toFillBlocks = map[targetIndex] - map[i];
                needBlocks += toFillBlocks;
            }
        }

        int prevTargetValue = map[targetIndex - 1];
        int crrTarget = map[targetIndex];
        int toBrakeBlocks = 0;
        for (int i = 0; i < n * m; i++) {
            if (map[i] == map[targetIndex]) {
                toBrakeBlocks++;
            }
        } 
        int toFillBlocks = n * m - toBrakeBlocks;
        //if don't have enough blocks
        while (crrTarget >= 0) {
            long currentInventory = blocks;
            long currentNeedBlocks = 0;
            long currentSpentTime = 0;

            for (int i = 0; i < n * m; i++) {
                if (map[i] > crrTarget) {
                    int toBrake = map[i] - crrTarget;
                    currentInventory += toBrake;
                    currentSpentTime += toBrake * 2L;
                } else if (map[i] < crrTarget) {
                    int toFill = crrTarget - map[i];
                    currentNeedBlocks += toFill;
                    currentSpentTime += toFill * 1L;
                }
            }

            if (currentInventory >= currentNeedBlocks) {
                break;
            }

            crrTarget--;
        }

        //fill
        for (int i = 0; i < n * m; i++) {
            if (crrTarget > map[i]) {
                int toFill = crrTarget - map[i];
                spentTime += toFill;
            }
            else if (crrTarget < map[i]) {
                int toBrack = map[i] - crrTarget;
                spentTime += toBrack * 2;
            }
        }

        bw.write(Integer.toString(spentTime) + " " + Integer.toString(crrTarget));
        bw.flush();
        bw.close();
    }
}
