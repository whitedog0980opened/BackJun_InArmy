import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[nodeNum + 1][nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            // map[i][i] = 0;
        }

        for (int i = 0; i < busNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (map[from][to] > cost) map[from][to] = cost;
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
            return a1[1] - a2[1];
        });
        int[] disk = new int[nodeNum + 1];
        st = new StringTokenizer(br.readLine());
        int startNode = Integer.parseInt(st.nextToken());
        int endNode = Integer.parseInt(st.nextToken());
        int[] startArr = {startNode, 0}; //cost

        pq.add(startArr);
        disk[startNode] = 0;
        Arrays.fill(disk, Integer.MAX_VALUE);
        int lessCost = 0;
        while (!pq.isEmpty()) {
            int[] crrArr = pq.poll();

            if (crrArr[0] == endNode) {
                lessCost = crrArr[1];
                break;
            }
            for (int i = 1; i < nodeNum + 1; i++) {
                int nextCost = map[crrArr[0]][i];
                if (nextCost == Integer.MAX_VALUE) continue;
                nextCost += crrArr[1];
                if (disk[i] > nextCost) {
                    disk[i] = nextCost;
                    int[] nextNode = {i, nextCost};
                    pq.add(nextNode);
                }
            }
        }
        bw.write(Integer.toString(lessCost));
        bw.flush();
        bw.close();
    }

}