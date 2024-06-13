package Input_output_add_etc;

import java.util.*;

public class Gold {
    static void n14502() {
        Scanner sc = new Scanner(System.in);
        String mapSize[]= sc.nextLine().split(" ");
        int N = Integer.parseInt(mapSize[0]);
        int M = Integer.parseInt(mapSize[1]);

        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) { //inject data to map[][]
            String[] temp = sc.nextLine().split(" ");
            map[i] = ArrayStrToInt(temp, M);
        }

        System.out.printf(Integer.toString(checkAll(map, N, M)));
    }


    static int[] checkAroundVoid(int[][] map, int n, int m) {
        int[] result = new int[4];
        result[0] = map[n - 1][m] == 0 ? 0 : 1;
        result[1] = map[n][m + 1] == 0 ? 0 : 1;
        result[2] = map[n + 1][m] == 0 ? 0 : 1;
        result[3] = map[n][m - 1] == 0 ? 0 : 1;
        return result; // up, right, down, left
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
    static int checkSafeVoid(int[][] map, int n, int m) {
        int[][] infectedMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            infectedMap[i] = map[i].clone();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    infectedMap = infection(infectedMap, n, m, i, j).clone();
                }
            }
        }
        return checkWholeVoid(infectedMap, n, m);
    }
    static int[][] infection(int[][] map, int n, int m, int x, int y) {
        if ((x - 1) > 0 && (x - 1) < n) {
            if (map[x - 1][y] == 0) {
                map[x - 1][y] = 2;
                infection(map, n, m, x-1, y);
            }
        }
        if ((x + 1) > 0 && (x + 1) < n) {
            if (map[x + 1][y] == 0) {
                map[x + 1][y] = 2;
                infection(map, n, m, x+1, y);
            }
        }
        if ((y - 1) > 0 && (y - 1) < n) {
            if (map[x][y - 1] == 0) {
                map[x][y - 1] = 2;
                infection(map, n, m, x, y-1);
            }
        }
        if ((y + 1) > 0 && (y + 1) < n) {
            if (map[x][y + 1] == 0) {
                map[x][y + 1] = 2;
                infection(map, n, m, x, y+1);
            }
        }
        if (map[x][y] == 2) {

        }
        return map;
    }
    static int[] ArrayStrToInt(String[] strs, int m) {
        int[]ints = new int[m];
        for (int i = 0; i < m; i++) {
            ints[i] = Integer.parseInt(strs[i]);
        }
        return ints;
    }
    static int[][] insertWall(int[][] map, int x, int y) {
        map[x][y] = 1;
        return map;
    }
    static int checkAll(int[][] map, int n, int m) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {map[i][j] = 1;}
                else continue;
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < m; l++) {
                        if (map[k][l] == 0) {map[k][l] = 1;}
                        else continue;
                        for (int o = 0; o < n; o++) {
                            for (int p = 0; p < n; p++) {
                                if (map[o][p] == 0) {
                                    map[o][p] = 1;
                                }
                                else continue;
                                int temp = checkSafeVoid(map, n, m);
                                if (max < temp) {
                                    max = temp;
                                }
                                map[o][p] = 0;
                            }
                        }
                        map[k][l] = 0;
                    }
                }
                map[i][j] = 0;
            }
        }
        return max;
    }
    //n14502
    //
    //n1005 (first)
    static void n1005_first() {
        // Receive Test Case Num
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCase; i++) {
            String[] nums = sc.nextLine().split(" ");
            buildTimeCalculator(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), sc);
        }
    }

    public static void buildTimeCalculator(int buildsNum, int sequencesNum, Scanner sc) {
        // Receive required times for build
        String[] requireTimeStrs = sc.nextLine().split(" ");
        int[] requireTimes = new int[buildsNum + 1]; // +1 Because Number of Building is start counting from 1
        for (int i = 0; i < buildsNum; i++) {
            requireTimes[i + 1] = Integer.parseInt(requireTimeStrs[i]);
        }
        // Initialize graph and in-degree array
        List<Integer>[] graph = new ArrayList[buildsNum + 1];
        for (int i = 1; i <= buildsNum; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] inDegree = new int[buildsNum + 1];
        // Receive sequence of buildings
        for (int i = 0; i < sequencesNum; i++) {
            String[] temp = sc.nextLine().split(" ");
            int X = Integer.parseInt(temp[0]);
            int Y = Integer.parseInt(temp[1]);
            graph[X].add(Y);
            inDegree[Y]++;
        }
        // Receive target building
        int targetBuild = Integer.parseInt(sc.nextLine());
        // Perform topological sort
        Queue<Integer> queue = new LinkedList<>();
        int[] buildTimes = new int[buildsNum + 1];
        for (int i = 1; i <= buildsNum; i++) {
            buildTimes[i] = requireTimes[i];
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : graph[current]) {
                buildTimes[next] = Math.max(buildTimes[next], buildTimes[current] + requireTimes[next]);
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        // Output the result for the target building
        System.out.println(buildTimes[targetBuild]);
    }
}
