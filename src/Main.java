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


        bw.flush();
        bw.close();
    }

}