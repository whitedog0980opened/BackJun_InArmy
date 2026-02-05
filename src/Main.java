import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        boolean[][] map = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String inputLine = br.readLine();
            for (int j = 0; j < m; j++) {
                char ch = inputLine.charAt(j);
                if (ch == '1') map[i][j] = true;
            }
        }

        int[] startPoint = {0, 0, 1, 1}; //n, m, distance, breakable count
        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPoint);
        boolean[][] visited = new boolean[n][m];
        boolean[][] bVisited = new boolean[n][m];
        visited[0][0] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int result = 0;
        while (!queue.isEmpty()) {
            int[] crrPoint = queue.poll();

            if (crrPoint[0] == n - 1 && crrPoint[1] == m - 1) { //found
                result = crrPoint[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextN = crrPoint[0] + dx[i];
                int nextM = crrPoint[1] + dy[i];
                int[] nextPoint = {nextN, nextM, crrPoint[2] + 1, crrPoint[3]};
                if (nextN < 0 || nextN >= n || nextM < 0 || nextM >= m) continue;
                if (crrPoint[3] == 1) {
                    if (visited[nextN][nextM]) continue;
                    if (map[nextN][nextM]) {
                        nextPoint[3] = 0;
                    } else {
                        visited[nextN][nextM] = true;
                    }
                    bVisited[nextN][nextM] = true;
                } else {
                    if (bVisited[nextN][nextM]) continue;
                    if (map[nextN][nextM]) continue;
                    bVisited[nextN][nextM] = true;
                }

                queue.add(nextPoint);
            }
        }
        if (result == 0) result = -1;
        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();;
    }

}