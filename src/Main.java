import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold)
//want to study : hashTable
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] edges = new ArrayList[nodeNum + 1]; // edges num is nodeNum - 1
        //init
        for (int i = 1; i < nodeNum + 1; i++) {
            edges[i] = new ArrayList<int[]>();
        }
        //take input
        for (int i = 0; i < nodeNum - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            //방향 없는 간선
            edges[from].add(new int[]{to, weight});
            edges[to].add(new int[]{from, weight});
        }

        int firstPoint = bfs1967(nodeNum, 1, edges)[0];
        int[] secPoint = bfs1967(nodeNum, firstPoint, edges);
        
        bw.write(Integer.toString(secPoint[1]));

        bw.flush();
        bw.close();
    }
    
    

}