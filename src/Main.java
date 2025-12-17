import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInputs = br.readLine().split(" ");
        int M = Integer.parseInt(firstInputs[0]);
        int N = Integer.parseInt(firstInputs[1]);

        int[][] box = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int crr = Integer.parseInt(inputLine[j]);
                if (crr == 1) {
                    visited[i][j] = true;
                    int[] tomato = {i, j, 0};
                    queue.add(tomato);
                }
                box[i][j] = Integer.parseInt(inputLine[j]);
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int result = 0;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextN = crr[0] + dx[i];
                int nextM = crr[1] + dy[i];
                boolean checkBorder = (
                    nextN >= 0 && nextN < N &&
                    nextM >= 0 && nextM < M &&
                    box[nextN][nextM] != -1 &&
                    box[nextN][nextM] != 1 &&
                    visited[nextN][nextM] == false
                );

                if (checkBorder) {
                    int nextDepth = crr[2] + 1;
                    if (nextDepth > result) result = nextDepth;
                    int[] newTomamto = {nextN, nextM, nextDepth};
                    queue.add(newTomamto);
                    visited[nextN][nextM] = true;
                }
            }
        }

        //check left raw tomato
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && box[i][j] == 0) {
                    result = -1;
                }
            }
        } 

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}
