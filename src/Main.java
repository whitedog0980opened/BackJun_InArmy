import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mapSize[] = sc.nextLine().split(" ");
        int N = Integer.parseInt(mapSize[0]);
        int M = Integer.parseInt(mapSize[1]);

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) { //inject data to map[][]
            String[] temp = sc.nextLine().split(" ");
            map[i] = ArrayStrToInt(temp, M);
        }

        System.out.printf(Integer.toString(checkAll(map, N, M)));
    }

    static int[] ArrayStrToInt(String[] strs, int m) {
        int[] ints = new int[m];
        for (int i = 0; i < m; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }
        return ints;
    }

    static int checkAll(int[][] map, int n, int m) {
        int maxSafeArea = 0;

        for (int i = 0; i < n * m; i++) {
            for (int j = i + 1; j < n * m; j++) {
                for (int k = j + 1; k < n * m; k++) {
                    int x1 = i / m, y1 = i % m;
                    int x2 = j / m, y2 = j % m;
                    int x3 = k / m, y3 = k % m;

                    if (map[x1][y1] == 0 && map[x2][y2] == 0 && map[x3][y3] == 0) {
                        map[x1][y1] = 1;
                        map[x2][y2] = 1;
                        map[x3][y3] = 1;

                        int safeArea = checkSafeVoid(map, n, m);
                        maxSafeArea = Math.max(maxSafeArea, safeArea);

                        map[x1][y1] = 0;
                        map[x2][y2] = 0;
                        map[x3][y3] = 0;
                    }
                }
            }
        }
        return maxSafeArea;
    }

    static int checkSafeVoid(int[][] map, int n, int m) {
        int[][] infectedMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            infectedMap[i] = map[i].clone();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    infection(infectedMap, n, m, i, j);
                }
            }
        }

        return checkWholeVoid(infectedMap, n, m);
    }

    static void infection(int[][] map, int n, int m, int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int curX = pos[0], curY = pos[1];

            for (int d = 0; d < 4; d++) {
                int newX = curX + dx[d];
                int newY = curY + dy[d];

                if (newX >= 0 && newX < n && newY >= 0 && newY < m && map[newX][newY] == 0) {
                    map[newX][newY] = 2;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }

    static int checkWholeVoid(int[][] map, int n, int m) {
        int voidNum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    voidNum++;
                }
            }
        }
        return voidNum;
    }
}
