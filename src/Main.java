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

        int nodeNum = Integer.parseInt(br.readLine());
        int maxLenght = 0;

        ArrayList<int[]>[] adj = new ArrayList[nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crrNode = Integer.parseInt(inputs[0]);
            int crr = 0;
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                adj[crrNode].add(new int[]{nextNode, length});
            }
        }


        int[] firstPoint = bfs_1167(1, nodeNum, adj);
        int[] secPoint = bfs_1167(firstPoint[0], nodeNum, adj)    ;
        
        bw.write(Integer.toString(secPoint[1]));
        bw.flush();
        bw.close();
    }

    static int[] bfs_1167(int start, int n, ArrayList<int[]>[] adj) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();

        int[] startArr = {start, 0}; // startnode, lenght
        visited[start] = true;
        queue.add(startArr);

        int crrMaxLenght = 0;
        int crrFartherNode = start;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            if (crr[1] > crrMaxLenght) {
                crrMaxLenght = crr[1];
                crrFartherNode = crr[0];
            }

            for (int[] nextNode : adj[crr[0]]) {
                if (visited[nextNode[0]]) {
                    continue;
                }
                visited[nextNode[0]] = true;
                queue.add(new int[]{nextNode[0], crr[1] + nextNode[1]});
            }
        }
        return new int[]{crrFartherNode, crrMaxLenght};
    }
}
