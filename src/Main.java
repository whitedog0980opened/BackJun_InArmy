import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strInputs = br.readLine().split(" ");
        int V = Integer.parseInt(strInputs[0]); //정점의 갯수
        int lineNum = Integer.parseInt(strInputs[1]);
        int startPoint = Integer.parseInt(strInputs[2]);


        //init graph line list
        LinkedList<Integer>[] graph = new LinkedList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new LinkedList<>();
        }

        //get graph lines
        for (int i = 0; i < lineNum; i++) {
            String[] edgeInputs = br.readLine().split(" ");
            int from = Integer.parseInt(edgeInputs[0]);
            int to = Integer.parseInt(edgeInputs[1]);
            graph[from].add(to);
            graph[to].add(from);
        }

        //sort graph lines
        for (int i = 1; i <= V; i++) {
            Collections.sort(graph[i]);
        }

        //DFS
        boolean[] visited = new boolean[V + 1];
        LinkedList<Integer> bfsStack = new LinkedList<>();
        bfsStack.push(startPoint);
        visited[startPoint] = true;

        bw.write(Integer.toString(startPoint));
        dfs(graph, visited, startPoint, bw);
        bw.newLine();
        visited = new boolean[V + 1];
        visited[startPoint] = true;
        bw.write(Integer.toString(startPoint));

        while (!bfsStack.isEmpty()) {
            int current = bfsStack.pop();
            
            if (!graph[current].isEmpty()) {
                Iterator<Integer> i = graph[current].listIterator();
                while (i.hasNext()) {
                    int temp = i.next();
                    if (!visited[temp]) {
                        visited[temp] = true;
                        bfsStack.add(temp);
                        bw.write(" " + Integer.toString(temp));
                    }
                }
            }
        }


        bw.flush();
        bw.close();
    }

    public static void dfs(LinkedList<Integer>[] graph, boolean[] visited, int current, BufferedWriter bw) throws IOException{
        if (!graph[current].isEmpty()) {
            for (int i = 0; i < graph[current].size(); i++) {
                int nextCurrent = graph[current].get(i);
                if (!visited[nextCurrent]) {
                    visited[nextCurrent] = true;
                    bw.write(" " + Integer.toString(nextCurrent));
                    dfs(graph, visited, nextCurrent, bw);
                }
            }
        }
    }

}
