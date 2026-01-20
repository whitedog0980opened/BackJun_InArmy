import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold)
//want to study : hashTable
//need to review = 5430 ! deque


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int graphNum = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] graphs = new ArrayList[nodeNum + 1];
        //init graphs
        for (int i = 1; i < nodeNum + 1; i++) {
            graphs[i] = new ArrayList<int[]>();
        }
        //get inputs from Buffer
        for (int i = 0; i < graphNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            graphs[from].add(new int[]{to, weight});
            graphs[to].add(new int[]{from, weight});
        }

        st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken());
        int target2 = Integer.parseInt(st.nextToken());
        st = null;


        bw.flush();
        bw.close();
    }
    static int bfs1504(int start, int target, int nodeNum, int graphNum, ArrayList<int[]>[] graphs) {
        PriorityQueue<int[]> greedyQueue = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[1], n2[1])); 
        int[] startOj = new int[]{start, 0}; // index 1 : weight for priority
        greedyQueue.add(startOj);

        int shortestWay = Integer.MAX_VALUE;

        int disk[] = new int[nodeNum + 1];
        Arrays.fill(disk, Integer.MAX_VALUE);
        disk[start] = 0;
 
        while (!greedyQueue.isEmpty()) {
            int[] crr = greedyQueue.poll();

            if (crr[0] == target) {
                // shortestWay = crr[2];
            }

            for (int[] nextPoint : graphs[crr[0]]) {
                // shoter then disk
                if (disk[nextPoint[0]] > disk[crr[0]] + nextPoint[1]) {
                    disk[nextPoint[0]] = disk[crr[0]] + nextPoint[1];
                    int[] next = new int[]{nextPoint[0], crr[1] + nextPoint[1]};
                    greedyQueue.add(next);
                }
            }
        }

        if (disk[target] == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return disk[target];
        }
    }
}