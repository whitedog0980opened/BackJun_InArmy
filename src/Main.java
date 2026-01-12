import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque


// Dijkstra <- 이거 이용하라는데 공부 필요할듯.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int wayNum = Integer.parseInt(st.nextToken());
        int partyNode = Integer.parseInt(st.nextToken());

        //int[] -> index 0 = targetNode & index 1 = time to spend
        LinkedList<int[]>[] ways = new LinkedList[nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            ways[i] = new LinkedList<int[]>();
        }

        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int useTime = Integer.parseInt(st.nextToken());

            ways[fromNode].add(new int[]{toNode, useTime});
        }

        int longestTime = 0;

        for (int i = 1; i < nodeNum + 1; i++) {
            int result = bfs_1238(i, partyNode, nodeNum, ways) + bfs_1238(partyNode, i, nodeNum, ways);
            longestTime = Math.max(result, longestTime);
        }
        bw.write(Integer.toString(longestTime));

         
        bw.flush();
        bw.close();
    }
    static int bfs_1238(int start, int targetNode, int nodeNum, LinkedList<int[]>[] ways) {
        boolean[] visited = new boolean[nodeNum + 1];
        int shortestTimeRecord = Integer.MAX_VALUE;

        Queue<int[]> queue = new LinkedList<>();
        int[] startNode = {start, 0}; // 0 = spentTime
        queue.add(startNode);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] crrNode = queue.poll();

            if (crrNode[0] == targetNode) {
                if (crrNode[1] < shortestTimeRecord) shortestTimeRecord = crrNode[1];
                continue;
            }


            for (int[] i : ways[crrNode[0]]) {
                //check visited
                if (visited[i[0]]) continue;
                //time over (already fount shorter way)
                if (shortestTimeRecord < crrNode[1] + i[1]) continue;

                int[] nextNode = {i[0], crrNode[1] + i[1]};
                queue.add(nextNode);
            }
        }
        return shortestTimeRecord;
    }
}
