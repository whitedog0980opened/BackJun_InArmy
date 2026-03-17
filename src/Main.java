import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import org.w3c.dom.Node;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    private static class Node1197 {
        int node;
        int sumOfWeight;
        int uniqueCount; // same to V means visited all node
        boolean[] visited;

        Node1197(int node, int weight, int uniqueCount, boolean[] visited) {
            this.node = node;
            this.sumOfWeight = weight;
            this.uniqueCount = uniqueCount;
            this.visited = visited;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ArrayList<int[]>[] edges = new ArrayList[v + 1];
        for (int i = 1; i < v + 1; i++) {
            edges[i] = new ArrayList<>();
        }
        //init edges
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[from].add(new int[]{to, weight});
            edges[to].add(new int[]{from, weight});
        }

        PriorityQueue<Node1197> pq = new PriorityQueue<>((n1, n2) -> {
            return n1.sumOfWeight - n2.sumOfWeight;
        });

        boolean[] startVisited = new boolean[v + 1];
        startVisited[1] = true;
        Node1197 startNode = new Node1197(1, 0, 1, startVisited);
        pq.add(startNode);

        int minimumWeight = 0;
        while (!pq.isEmpty()) {
            Node1197 crrNode = pq.poll();
            if (crrNode.uniqueCount == v) {
                minimumWeight = crrNode.sumOfWeight;
                break;
            }

            for (int[] edge : edges[crrNode.node]) {
                //check makes cycle
                if (cycleChecker1197(edges, crrNode, edge[0])) continue;
                boolean[] nextVisited = Arrays.copyOf(crrNode.visited, v + 1);
                nextVisited[edge[0]] = true;
                Node1197 nextNode = new Node1197(edge[0], crrNode.sumOfWeight + edge[1], crrNode.uniqueCount + 1, nextVisited);
                pq.add(nextNode);
            }
        }

        bw.write(Integer.toString(minimumWeight));
        bw.flush();
        bw.close();
    }
    private static boolean cycleChecker1197(ArrayList<int[]>[] edges, Node1197 crrNode, int nextnode) {
        boolean[] targetVisited = crrNode.visited;
        boolean result = targetVisited[nextnode];
        return result;
    }
    
}