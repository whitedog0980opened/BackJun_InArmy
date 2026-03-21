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
    public static void main(String[] args) throws IOException {
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