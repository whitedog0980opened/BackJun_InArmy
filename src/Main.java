import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] inputs = br.readLine().split(" ");
        int rowNum = Integer.parseInt(inputs[0]);
        int colNum = Integer.parseInt(inputs[1]);
        int targerY = rowNum -1;
        int targetX = colNum -1;
        boolean[][] map = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            String[] rowInputs = br.readLine().split("");

            for (int j = 0; j < colNum; j++) {
                boolean isRoad = Integer.parseInt(rowInputs[j]) == 1;
                if (isRoad) map[i][j] = true;
            }
        }

        //BFS
        Queue<int[]> queue = new LinkedList<>();
        int[] startPoint = {0, 0, 0}; //x ,y ,depth
        queue.add(startPoint);
        HashSet<int[]> visited = new HashSet<>();
        visited.add(startPoint);

        int[] dx = {1, -1, 0 ,0};
        int[] dy = {0, 0, 1, -1};
        int maxDepth = 0;

        first: while (!queue.isEmpty()) {
            int[] crrPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int[] nextPoint = {crrPoint[0] + dx[i], crrPoint[1] + dy[i], crrPoint[2] + 1};
                if (nextPoint[1] < 0 || nextPoint[1] >= rowNum ||
                    nextPoint[0] < 0 || nextPoint[0] >= colNum ) {
                    continue;
                }
                boolean isVisited = visited.stream().anyMatch(arr -> arr[0] == nextPoint[0] && arr[1] == nextPoint[1]);
                if (isVisited) continue;

                int[] target = {targetX, targerY};
                int[] nextXY = {nextPoint[0], nextPoint[1]};
                if(Arrays.equals(nextXY, target)) {
                    maxDepth = nextPoint[2];
                    break first;
                }

                if(map[nextPoint[1]][nextPoint[0]]) {
                    queue.add(nextPoint);
                    visited.add(nextPoint);
                }
            }
        }

        bw.write(Integer.toString(maxDepth + 1));
        
        bw.flush();
        bw.close();
    }
}
