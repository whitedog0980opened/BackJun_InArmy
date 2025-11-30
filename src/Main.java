import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStrs = br.readLine().split(" ");
        int nodeNum = Integer.parseInt(inputStrs[0]);
        int lineNum = Integer.parseInt(inputStrs[1]);
        int startNode = Integer.parseInt(inputStrs[2]);

        //input lines
        int[][] lines = new int[lineNum][2];
        for (int i = 0; i < lineNum; i++) {
            String[] lineInputs = br.readLine().split(" ");
            int[] line = {Integer.parseInt(lineInputs[0]), Integer.parseInt(lineInputs[1])};
            lines[i] = line;
        }
        // create graph
        LinkedList<Integer>[] graphs = new LinkedList[nodeNum + 1];
        for (int i = 0; i < nodeNum + 1; i++) {
            graphs[i] = new LinkedList<>();
        }
        for (int[] line : lines) {
            graphs[line[0]].add(line[1]);
            graphs[line[1]].add(line[0]);
        }
        for (LinkedList<Integer> graph : graphs) {
            Collections.sort(graph);
        }
        

        List<Integer> visited = new ArrayList<>();

        boolean isFirst = true;
        dfs(graphs, startNode, visited, bw, isFirst);
        bw.newLine();

        //init again
        isFirst = true;
        visited = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startNode);

        visited.add(startNode);
        bw.write(Integer.toString(startNode));
        while (!queue.isEmpty()) {
            int crrNode = queue.poll();

            for (int i = 0; i < graphs[crrNode].size(); i++) {
                int newNode = graphs[crrNode].get(i);
                if (visited.contains(newNode)) continue;
                else visited.add(newNode);
                queue.add(newNode);
                bw.write(" " + Integer.toString(newNode));
            }
        }

        bw.flush();
        bw.close();
    }

    static void dfs(LinkedList<Integer>[] graphs, int crrNode, List<Integer> visited, BufferedWriter bw, boolean isFirst) throws IOException {
        if (visited.contains(crrNode)) {
            return;
        } else {
            visited.add(crrNode);
            if (isFirst) {
                bw.write(Integer.toString(crrNode));
                isFirst = false;
            }
            else bw.write(" " + Integer.toString(crrNode));
        }
        for (int i = 0; i < graphs[crrNode].size(); i++) {
            int nextNode = graphs[crrNode].get(i);
            dfs(graphs, nextNode, visited, bw, isFirst);
        }
    }
}