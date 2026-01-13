import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라)
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
        ArrayList<int[]>[] ways = new ArrayList[nodeNum + 1];
        ArrayList<int[]>[] reservedWays = new ArrayList[nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            ways[i] = new ArrayList<int[]>();
            reservedWays[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int useTime = Integer.parseInt(st.nextToken());

            ways[fromNode].add(new int[]{toNode, useTime});
            reservedWays[toNode].add(new int[]{fromNode, useTime});
        }

        int longestTime = 0;

        int[] wayToParty = bfs_1238(partyNode, nodeNum, ways);
        int[] wayToHome = bfs_1238(partyNode, nodeNum, reservedWays);
        for (int i = 1; i < nodeNum + 1; i++) {
            int totalTime = wayToParty[i] + wayToHome[i];
            if (totalTime > longestTime) longestTime = totalTime;
        }

        bw.write(Integer.toString(longestTime));

         
        bw.flush();
        bw.close();
    }
    static int[] bfs_1238(int targetNode, int nodeNum, ArrayList<int[]>[] ways) {
        int[] disk = new int[nodeNum + 1];
        Arrays.fill(disk, Integer.MAX_VALUE);
        disk[targetNode] = 0;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2)-> Integer.compare(n1[1], n2[1]));
        int[] startNode = {targetNode, 0};
        queue.add(startNode);
        
        
        while (!queue.isEmpty()) {
            int[] crrNode = queue.poll();
            
            if (crrNode[1] > disk[crrNode[0]]) continue;

            for (int[] i : ways[crrNode[0]]) {
                //check this way is shotter then disk
                int nextTime = i[1] + crrNode[1];
                if (disk[i[0]] <= nextTime) continue;
                int[] nextNode = {i[0], nextTime};
                disk[nextNode[0]] = nextTime;
                queue.add(nextNode);
            }
        }
        return disk;
    }
}
