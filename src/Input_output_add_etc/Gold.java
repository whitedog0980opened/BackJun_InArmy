import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

        // Receive sequence of buildings
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= buildsNum; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < sequencesNum; i++) {
            String[] temp = sc.nextLine().split(" ");
            int X = Integer.parseInt(temp[0]);
            int Y = Integer.parseInt(temp[1]);
            adjList.get(Y).add(X); // Storing in reverse order for backtracking
        }

        // Receive target building
        int targetBuild = Integer.parseInt(sc.nextLine());

        // Find the maximum time to build the target building
        boolean[] visited = new boolean[buildsNum + 1];
        int[] dp = new int[buildsNum + 1];
        Arrays.fill(dp, -1);
        int result = findMaxTime(targetBuild, adjList, requireTimes, dp, visited);
        System.out.println(result);
    }

    public static int findMaxTime(int current, ArrayList<ArrayList<Integer>> adjList, int[] requireTimes, int[] dp, boolean[] visited) {
        if (dp[current] != -1) { //check empty
            return dp[current];
        }
        if (adjList.get(current).isEmpty()) {
            dp[current] = requireTimes[current];
            return dp[current];
        }
        int maxTime = 0;
        for (int prev : adjList.get(current)) {
            if (!visited[prev]) {
                visited[prev] = true;
                maxTime = Math.max(maxTime, findMaxTime(prev, adjList, requireTimes, dp, visited));
                visited[prev] = false;
            }
        }
        dp[current] = maxTime + requireTimes[current];
        return dp[current];
    }

    static void n14500() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //handle inputs
        String mapSizeInput[] = br.readLine().split(" ");
        int mapSizeX = Integer.parseInt(mapSizeInput[1]);
        int mapSizeY = Integer.parseInt(mapSizeInput[0]);
        //index 0 and n + 1 is blank value for border
        int[][] map = new int[mapSizeY + 2][mapSizeX + 2]; //include border
        //map index is start from 1
        for (int i = 1; i < mapSizeY + 1; i++) {
            String xInputLine[] = br.readLine().split(" ");
            for (int j = 1; j < mapSizeX + 1; j++) {
                int cellInput = Integer.parseInt(xInputLine[j - 1]);
                map[i][j] = cellInput;
            }
        }
        //init map's border
        for (int i = 0; i < mapSizeX + 2; i++) {
            map[0][i] = -1;
            map[mapSizeY + 1][i] = -1;
        }
        for (int i = 1; i < mapSizeY + 1; i++) {
            map[i][0] = -1;
            map[i][mapSizeX + 1] = -1;
        }
        // movement each tetra
        int[][] tetraT = {
            {1, 0},
            {1, 1},
            {2, 0}
        };
        int[][] tetraI = {
            {1, 0},
            {2, 0},
            {3, 0}
        };
        int[][] tetraO = {
            {1, 0},
            {1, -1},
            {0, -1}
        };
        int[][] tetraZ = {
            {0, 1},
            {1, 1},
            {1, 2}
        };
        int[][] tetraZ2 = {
            {0, 1},
            {-1, 1},
            {-1, 2}
        };
        int[][] tetraL = {
            {0, 1},
            {0, 2},
            {1, 2}
        };
        int[][] tetraL2 = {
            {0, 1},
            {0, 2},
            {-1, 2}
        };

        int max = -2;
        //repeat for rotate
        for (int i = 0; i < 4; i++) {
            max = Math.max(max, tetraTry(tetraT, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraI, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraO, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraZ, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraZ2, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraL, map, mapSizeX, mapSizeY, i));
            max = Math.max(max, tetraTry(tetraL2, map, mapSizeX, mapSizeY, i));
        }

        bw.write(Integer.toString(max));
    

        bw.flush();
        bw.close();
    }
    public static int tetraTry(int[][] tetraShape, int[][] map, int mapSizeX, int mapSizeY, int deract) {
        //deep copy tetraShape
        int[][] rotatedTetraShape = new int[3][2];
        for (int i = 0; i < 3; i++) {
            rotatedTetraShape[i] = tetraShape[i].clone();
        }
        //rotate tetraShape
        for (int i = 0; i < deract; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = rotatedTetraShape[j][0];
                rotatedTetraShape[j][0] = rotatedTetraShape[j][1];
                rotatedTetraShape[j][1] = -temp;
            }
        }

        //get sum
        int max = -2;
        for (int i = 0; i < mapSizeY + 1; i++) {
            for (int j = 0; j < mapSizeX + 1; j++) {
                int sum = map[i][j];
                if (map[i][j] == -1)  {
                    sum = -1;
                    continue;
                }
                for (int[] indexs : rotatedTetraShape) {
                    int curI = i + indexs[1];
                    int curJ = j + indexs[0];
                    if (curI < 0 || curI >= map.length || curJ < 0 || curJ >= map[0].length || map[curI][curJ] == -1)  {
                        sum = -1;
                        continue;
                    };
                    sum += map[curI][curJ];
                }
                if (max < sum) max = sum;
            }
        }
        return max; //-1 means every try is blocked by border
    }
    public static void n1074() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int row = Integer.parseInt(inputs[1]);
        int col = Integer.parseInt(inputs[2]);

        int startNum = 0;
        int endNum = (1 << (2 * n)) - 1; // (2^n)^2 - 1
        int totalCellNum = endNum + 1;

        int diviedBy = 1 << (n - 1);
        int rDivied = row / diviedBy;
        int rRest = row % diviedBy;
        int cDivied = col / diviedBy;
        int cRest = col % diviedBy;

        while (true) {
            startNum += rDivied * (totalCellNum / 2);
            startNum += cDivied * (totalCellNum / 4);
            totalCellNum /= 4;
            endNum = startNum + totalCellNum - 1;

            diviedBy /= 2;
            if (diviedBy == 0) break;
            rDivied = rRest / diviedBy;
            rRest %= diviedBy;
            cDivied = cRest / diviedBy;
            cRest %= diviedBy;
        }

        bw.write(Integer.toString(endNum));


        bw.flush();
        bw.close();
    }

    public static void n1931() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int meetingNum = Integer.parseInt(br.readLine());

        int[][] meetings = new int[meetingNum][2];
        for (int i = 0; i < meetingNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int startTime = Integer.parseInt(inputs[0]);
            int endTime = Integer.parseInt(inputs[1]);

            meetings[i][0] = startTime;
            meetings[i][1] = endTime;
        }

        Arrays.sort(meetings, (n1, n2) -> {
            if (n1[1] == n2[1]) {
                return Integer.compare(n1[0], n2[0]);
            }
            return Integer.compare(n1[1], n2[1]);
        });



        int leastStartTime = 0;
        int ableMeetingNum = 0;
        for (int i = 0; i < meetingNum; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            // 이전에 선택한 회의와 겹치지 않으면 선택
            if (start >= leastStartTime) {
                ableMeetingNum++;
                leastStartTime = end;
            }
        }

        bw.write(Integer.toString(ableMeetingNum));
        
        bw.flush();
        bw.close();
    }

    public static void n7569() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] inputs = br.readLine().split(" ");
        int xNum = Integer.parseInt(inputs[0]);
        int yNum = Integer.parseInt(inputs[1]);
        int zNum = Integer.parseInt(inputs[2]);

        int[][][] tomatoMap = new int[xNum][yNum][zNum];
        Queue<int[]> queue = new LinkedList<>();
        HashSet<int[]> visitedNode = new HashSet<>();

        for (int i = 0; i < yNum * zNum; i++) {
            String[] inputRow = br.readLine().split(" ");
            for (int j = 0; j < xNum; j++) {
                int tempY = i % yNum;
                int tempZ = i / yNum;
                int crrTomato = Integer.parseInt(inputRow[j]);
                tomatoMap[j][tempY][tempZ] = crrTomato; // init tomato map
                if (crrTomato == 1) { //add ripe tomato (1)
                    int[] tomatoCoord = {j, tempY, tempZ, 0};
                    queue.add(tomatoCoord);
                    visitedNode.add(tomatoCoord);
                }
            }
        }

        int depth = 0;
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        

        while (!queue.isEmpty()) {
            int[] crrRipeTomato = queue.poll();

            for (int i = 0; i < 6; i++) {
                int[] nextXYZ = {crrRipeTomato[0] + dx[i], crrRipeTomato[1] + dy[i], crrRipeTomato[2] + dz[i]};
                boolean borderCheck = nextXYZ[0] > -1 && nextXYZ[1] > -1 && nextXYZ[2] > -1
                                    && nextXYZ[0] < xNum && nextXYZ[1] < yNum && nextXYZ[2] < zNum;
                if (borderCheck) {
                    boolean visitCheck = visitedNode.stream().allMatch((arr -> Arrays.equals(arr, nextXYZ)));
                    boolean isUnriped = tomatoMap[nextXYZ[0]][nextXYZ[1]][nextXYZ[2]] == 0;
                    if (!visitCheck && isUnriped) {
                        tomatoMap[nextXYZ[0]][nextXYZ[1]][nextXYZ[2]] = 1;
                        depth = crrRipeTomato[3] + 1;
                        int[] temp = {nextXYZ[0], nextXYZ[1], nextXYZ[2], depth};
                        queue.add(temp);
                    }
                }
            }
        }

        boolean hasZero = Arrays.stream(tomatoMap)
                                .flatMap(Arrays::stream)
                                .flatMapToInt(Arrays::stream)
                                .anyMatch(x -> x == 0);

        if (hasZero) {
            bw.write(Integer.toString(-1));
            bw.flush();
            bw.close();
            return;
        }

        bw.write(Integer.toString(depth));
        
        bw.flush();
        bw.close();
    }
    static void n1043() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInput = br.readLine().split(" ");
        int PeopleNum = Integer.parseInt(firstInput[0]);
        int partyNum = Integer.parseInt(firstInput[1]);

        String[] secendInput = br.readLine().split(" ");
        int knownPeopleNum = Integer.parseInt(secendInput[0]);
        HashSet<Integer> knownPeople = new HashSet<>();
        for (int i = 0; i < knownPeopleNum; i++) {
            knownPeople.add(Integer.parseInt(secendInput[i + 1]));
        }
        //init
        LinkedList<Integer>[] graphs = new LinkedList[PeopleNum + 1];
        for (int i = 0; i < PeopleNum + 1; i++) {
            graphs[i] = new LinkedList<>();
        }
        LinkedList<Integer>[] parties = new LinkedList[partyNum]; 
        for (int i = 0; i < partyNum; i++) {
            parties[i] = new LinkedList<>();
        }
        for (int i = 0; i < partyNum; i++) {
            //inputs
            String[] partyInput = br.readLine().split(" ");
            //fill parties
            for (int j = 0; j < partyInput.length; j++) {
                parties[i].add(Integer.parseInt(partyInput[j]));
            }
            //fill graphs
            int partyPeople = parties[i].get(0);
            if (partyPeople == 0) continue; //if empty party
            int host = Integer.parseInt(partyInput[1]);
            for (int j = 2; j < partyPeople + 1; j++) {
                int to = parties[i].get(j);
                graphs[host].add(to);
                graphs[to].add(host);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i : knownPeople) {
            queue.add(i);
            visited.add(i);
        }

        while (!queue.isEmpty()) {
            int crrPerson = queue.poll();
            for (int i = 0; i < graphs[crrPerson].size(); i++) {
                int nextPerson = graphs[crrPerson].get(i);
                if (visited.contains(nextPerson)) continue;
                else {
                    visited.add(nextPerson);
                    knownPeople.add(nextPerson);
                    queue.add(nextPerson);
                }
            }
        }

        int ableParty = 0;
        for (int i = 0; i < partyNum; i++) {
            boolean able = true;
            for (int j = 1; j < parties[i].size(); j++) {
                int crrPerson = parties[i].get(j);
                if (knownPeople.contains(crrPerson)) able = false;
            }
            if (able) ableParty++;
        }

        bw.write(Integer.toString(ableParty));

        bw.flush();
        bw.close();
    }
}
