package golds;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Gold2 {
    private static void n1197() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
            return a1[2] - a2[2];
        });
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int[] edge = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            pq.add(edge);
        }
        
        int[] union = new int[v + 1];
        for (int i = 1; i < v + 1; i++) {
            union[i] = i;
        }

        int sumOfWeight = 0;
        int edgeCount = 0;
        while (!pq.isEmpty()) {
            int[] crrEdge = pq.poll();
            if (union1197(crrEdge[0], crrEdge[1], union)) {
                sumOfWeight += crrEdge[2];
                edgeCount++;
                if (edgeCount == v - 1) break;
            };
        }

        bw.write(Integer.toString(sumOfWeight));
        bw.flush();
        bw.close();
    }
    static int find1197(int x, int[] union) {
        if (union[x] == x) return x;
        return union[x] = find1197(union[x], union);
    }
    static boolean union1197(int x, int y, int[] union) {
        int rootX = find1197(x, union);
        int rooty = find1197(y, union);

        if (rootX != rooty) {
            union[rooty] = rootX;
            return true;
        }
        return false;
    }
    private static void n12865() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] minimumDP = new int[k + 1];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (weight > k) continue;

            for (int j = k; j >= weight; j--) {
                minimumDP[j] = Math.max(minimumDP[j - weight] + cost, minimumDP[j]);
            }
        }

        bw.write(Integer.toString(minimumDP[k]));
        bw.flush();
        bw.close();
    }
    private static void n11779() throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int cityNum = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());

        final int NULLBUS = 1000001;
        int[][] buses = new int[cityNum + 1][cityNum + 1];
        for (int i = 0; i < cityNum + 1; i++) {
            Arrays.fill(buses[i], NULLBUS);
        }

        StringTokenizer st;
        for (int i = 0; i < busNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (cost < buses[from][to] || buses[from][to] == 0) {
                buses[from][to] = cost;
            }
        }
        st = new StringTokenizer(br.readLine());
        int startCity = Integer.parseInt(st.nextToken());
        int endCity = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
            return a1[1] - a2[1];
        });
        int[] startOj = {startCity, 0, 0};
        pq.add(startOj);
        int[] dist = new int[cityNum + 1];
        int[] parent = new int[cityNum + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startCity] = 0;

        int result = 0;
        while (!pq.isEmpty()) {
            int[] crrA = pq.poll();
            if (crrA[0] == endCity) {
                result = crrA[1];
                break;
            }

            for (int i = 1; i < cityNum + 1; i++) {
                if (i == crrA[0]) continue;
                if (buses[crrA[0]][i] == NULLBUS) continue;
                int nextCost = crrA[1] + buses[crrA[0]][i];
                if (dist[i] > nextCost) {
                    int[] nextOj = {i, nextCost, crrA[2] + 1};
                    dist[i] = nextCost;
                    pq.add(nextOj);
                    parent[i] = crrA[0];
                }
            }
        }
        bw.write(Integer.toString(result) + "\n"); //print minimum cost
        
        Stack<Integer> stack = new Stack<>();
        int cur = endCity;
        while (cur != 0) {
            stack.push(cur);
            cur = parent[cur];
        }
        bw.write(stack.size() + "\n"); //print how many city visited
        while (!stack.isEmpty()) { //print visited city list
            bw.write(stack.pop() + " ");
        }
        bw.flush();
        bw.close();
    }
}
