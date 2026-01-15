import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라)
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int blocks = Integer.parseInt(st.nextToken());

        int maxHeight = 0;
        //init map
        int[] map = new int[n * m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int crr = Integer.parseInt(st.nextToken());
                map[i * m + j] = crr;

                maxHeight = Math.max(maxHeight, crr);
            }
        }
        
        Arrays.sort(map);
        int startHeight = maxHeight; //start from highest y
        int minimumTime = Integer.MAX_VALUE;
        int y = 0;
        for (int crrHeight = startHeight; crrHeight >= 0; crrHeight--) {
            int needBlocks = 0;
            int spentTime = 0;
            //check has enough blocks
            for (int j : map) {
                int diff = Math.abs(j - crrHeight);
                if (j < crrHeight) {
                    needBlocks += diff;
                    spentTime += diff;
                }
                else if (j > crrHeight) {
                    needBlocks -= diff;
                    spentTime += diff * 2;
                }
            }
            boolean hasEnough = needBlocks <= blocks;
            if (hasEnough && minimumTime > spentTime) {
                minimumTime = spentTime;
                y = crrHeight;
            }
        }


        bw.write(Integer.toString(minimumTime) + " " + Integer.toString(y));
        bw.flush();
        bw.close();
    }
}
