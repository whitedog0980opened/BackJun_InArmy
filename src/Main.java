import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
public class Main {
    static int fibo1 = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int computerNum = Integer.parseInt(br.readLine());
        int graphNum = Integer.parseInt(br.readLine());
        LinkedList<Integer>[] graphs = new LinkedList[computerNum + 1];
        for (int i = 1; i < computerNum + 1; i++) { //init LinkedList Array
            graphs[i] = new LinkedList<Integer>();
        }

        for (int i = 1; i < graphNum + 1; i++) {
            String[] inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            graphs[from].add(to);
            graphs[to].add(from);
        }

        boolean[] visited = new boolean[computerNum + 1];
        visited[1] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int infected = 0;
        while (!queue.isEmpty()) {
            int crrPC = queue.poll();
            for (int to : graphs[crrPC]) {
                if (visited[to]) continue;
                else {
                    queue.add(to);
                    visited[to] = true;
                    infected++;
                }
            }
        }

        bw.write(Integer.toString(infected));


        
        bw.flush();
        bw.close();
    }


}
