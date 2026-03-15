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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] cheeseMap = new boolean[n][m];
        boolean[][] isAir = new boolean[n][m];
        int[][] cheeseExCounter = new int[n][m];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        //cheeseMap init
        //모서리에는 치즈가 존재하지 않으므로, 제외
        boolean isNothing = true;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m - 1; j++) {
                boolean crrCheese = Integer.parseInt(st.nextToken()) == 0 ? false : true;
                cheeseMap[i][j] = crrCheese;
                if (crrCheese) isNothing = false;
            }
        }
        markExternalAir2638(n, m, cheeseMap, isAir);
        //cheeseExCounter init
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (!cheeseMap[i][j]) continue;
                boolean[] neighbors = new boolean[4];
                for (int k = 0; k < 4; k++) {
                    neighbors[k] = isAir[i + dx[k]][j + dy[k]];
                    if (neighbors[k]) cheeseExCounter[i][j]++;
                }
            }
        }
        int time = 0;
        while (true) {
            isAir = new boolean[n][m];
            markExternalAir2638(n, m, cheeseMap, isAir);

            List<int[]> toMelt = new ArrayList<>();
            boolean hasCheese = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cheeseMap[i][j]) {
                        hasCheese = true;
                        int airCount = 0;
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k], ny = j + dy[k];
                            if (isAir[nx][ny]) airCount++;
                        }
                        if (airCount >= 2) toMelt.add(new int[]{i, j});
                    }
                }
            }

            if (!hasCheese) break; 
            if (toMelt.isEmpty()) break; 

            for (int[] pos : toMelt) {
                cheeseMap[pos[0]][pos[1]] = false;
            }
            time++;
        }
        bw.write(Integer.toString(time));
        bw.flush();
        bw.close();
    }
    static void markExternalAir2638(int n, int m, boolean[][] cheeseMap, boolean[][] isAir) {
        Queue<int[]> q = new LinkedList<>();
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        q.add(new int[]{0, 0});
        isAir[0][0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!isAir[nx][ny] && !cheeseMap[nx][ny]) {
                        isAir[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}