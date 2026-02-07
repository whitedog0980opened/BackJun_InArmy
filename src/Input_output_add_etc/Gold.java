import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

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
    static void n7576() throws IOException {
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
    static void n5430NeedToReview() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); // 출력을 한 번에 모으기 위함

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String input = br.readLine();

            // Deque에 숫자 담기 (양방향 삭제 가능)
            Deque<Integer> deque = new LinkedList<>();
            // 대괄호 제외하고 숫자만 분리
            StringTokenizer st = new StringTokenizer(input.substring(1, input.length() - 1), ",");
            while (st.hasMoreTokens()) {
                deque.add(Integer.parseInt(st.nextToken()));
            }

            boolean isReversed = false;
            boolean isError = false;

            for (char cmd : command.toCharArray()) {
                if (cmd == 'R') {
                    isReversed = !isReversed; // 방향만 전환
                } else { // 'D' 인 경우
                    if (deque.isEmpty()) {
                        isError = true;
                        break;
                    }
                    // 방향에 따라 앞 혹은 뒤에서 제거
                    if (isReversed) {
                        deque.removeLast();
                    } else {
                        deque.removeFirst();
                    }
                }
            }

            if (isError) {
                sb.append("error\n");
            } else {
                makeResultString(sb, deque, isReversed);
            }
        }
        System.out.print(sb);
    }

    private static void makeResultString(StringBuilder sb, Deque<Integer> deque, boolean isReversed) {
        sb.append("[");
        while (!deque.isEmpty()) {
            sb.append(isReversed ? deque.removeLast() : deque.removeFirst());
            if (!deque.isEmpty()) {
                sb.append(",");
            }
        }
        sb.append("]\n");
    }
    
    static void n7662() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (0 < testCase--) {
            int inputCase = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> doublePreQueue = new TreeMap<>();

            for (int i = 0; i < inputCase; i++) {
                String[] inputStr = br.readLine().split(" ");
                char commend = inputStr[0].charAt(0);
                int value = Integer.parseInt(inputStr[1]);
                boolean smallist = false;

                // add entry
                if (commend == 'I') {
                    doublePreQueue.merge(value, 1, (x1, x2) -> {return x1 + x2;});
                    continue;
                } // delete entry
                else if (value == -1) {
                    smallist = true;
                }
                
                if (doublePreQueue.isEmpty()) continue; // if empty
                int key = smallist ? doublePreQueue.firstKey() : doublePreQueue.lastKey();
                int queueValue = doublePreQueue.get(key);
                if (queueValue > 1) doublePreQueue.put(key, queueValue - 1);
                else doublePreQueue.remove(key);
            }

            if (doublePreQueue.isEmpty()) {
                bw.write("EMPTY\n");
            }
            else {
                String lastNum = Integer.toString(doublePreQueue.lastKey());
                String firstNum = Integer.toString(doublePreQueue.firstKey());
                bw.write(lastNum + " " + firstNum + "\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
    static void n9019() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (0 < testCase--) {
            //init
            String[] inputs = br.readLine().split(" ");
            int num = Integer.parseInt(inputs[0]); 
            int targetNum = Integer.parseInt(inputs[1]);;

            //[0] = D, [1] = S, [2] = L, [3] = R

            Queue<String[]> objectNum = new LinkedList<>();
            // int[] startPoint = {0, 0, 0, 0, num};
            //it seems to able to Optimization to change this type to new Class 
            //new Class has field StringBulider and int
            String[] startPoint = {"", Integer.toString(num)};
            objectNum.add(startPoint);

            HashSet<Integer> visited = new HashSet<>();
            visited.add(num);
            int result = 0;

            //BFS
            while (!objectNum.isEmpty()) {
                String[] crrArr = objectNum.poll();
                int crrNum = Integer.parseInt(crrArr[1]);

                //answer check
                if (crrNum == targetNum) {
                    bw.write(crrArr[0]);
                    bw.newLine();
                    break;
                }

                //add to queue
                //D
                int nextNum = crrNum * 2 % 10000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "D", Integer.toString(nextNum)};
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //S
                nextNum = crrNum - 1;
                if (nextNum < 0) nextNum = 9999;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "S", Integer.toString(nextNum)};
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //L
                int n1 = crrNum % 10;
                int n2 = crrNum % 100 / 10;
                int n3 = crrNum % 1000 / 100;
                int n4 = crrNum / 1000;
                int temp = n4;
                n4 = n3; n3 = n2; n2 = n1; n1 = temp;
                nextNum = n1 + n2 * 10 + n3 * 100 + n4 * 1000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "L", Integer.toString(nextNum)};;
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //R
                n1 = crrNum % 10;
                n2 = crrNum % 100 / 10;
                n3 = crrNum % 1000 / 100;
                n4 = crrNum / 1000;
                temp = n4;
                n4 = n1; n1 = n2; n2 = n3; n3 = temp; 
                nextNum = n1 + n2 * 10 + n3 * 100 + n4 * 1000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "R", Integer.toString(nextNum)};;
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
            }
        }
        
        bw.flush();
        bw.close();
    }
    static void n10026() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        //G = 1, R = 2, B = 5
        int[][] map = new int[N][N];
        int[][] map2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = inputLine[j].equals("G") ? 1 : inputLine[j].equals("R") ? 2 : 3;
                map2[i][j] = inputLine[j].equals("B") ? 1 : 2;
            }
        }

        int RGBresult = 0;
        int GBresult = 0;

        boolean[][] visitedRGB = new boolean[N][N];
        boolean[][] visitedGB = new boolean[N][N];

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        LinkedList<int[]> linkedList = new LinkedList<>();
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] crrTemp = {i, j};
                linkedList.add(crrTemp);
                if (visitedRGB[i][j]) RGBresult--;
                if (visitedGB[i][j]) GBresult--;
                visitedRGB[i][j] = true;
                visitedGB[i][j] = true;
                
                //RGB
                while (!linkedList.isEmpty()) {
                    int[] crrXY = linkedList.poll();
                    for (int k = 0; k < 4; k++) {
                        int[] nextXY = {crrXY[0] + dx[k], crrXY[1] + dy[k]};
                        boolean borderCheck = nextXY[0] >= 0 && nextXY[0] < N && nextXY[1] >= 0 && nextXY[1] < N ? true : false;
                        if (!borderCheck) continue;
                        if (!visitedRGB[nextXY[0]][nextXY[1]] && 
                            map[crrXY[0]][crrXY[1]] == map[nextXY[0]][nextXY[1]]) {
                            linkedList.add(nextXY);
                            visitedRGB[nextXY[0]][nextXY[1]] = true;
                            continue;
                        }
                    }
                }
                RGBresult++;
                //GB
                linkedList.add(crrTemp);
                while (!linkedList.isEmpty()) {
                    int[] crrXY = linkedList.poll();
                    for (int k = 0; k < 4; k++) {
                        int[] nextXY = {crrXY[0] + dx[k], crrXY[1] + dy[k]};
                        boolean borderCheck = nextXY[0] >= 0 && nextXY[0] < N && 
                                              nextXY[1] >= 0 && nextXY[1] < N ? true : false;
                        if (!borderCheck) continue;
                        if (!visitedGB[nextXY[0]][nextXY[1]] && 
                            map2[crrXY[0]][crrXY[1]] == map2[nextXY[0]][nextXY[1]]) {
                            linkedList.add(nextXY);
                            visitedGB[nextXY[0]][nextXY[1]] = true;
                        }
                    }
                }
                GBresult++;
            }
        }

        bw.write(Integer.toString(RGBresult) + " " + Integer.toString(GBresult));
        bw.flush();
        bw.close();
    }
    static void n1167() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        int maxLenght = 0;

        ArrayList<int[]>[] adj = new ArrayList[nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crrNode = Integer.parseInt(inputs[0]);
            int crr = 0;
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                adj[crrNode].add(new int[]{nextNode, length});
            }
        }


        int[] firstPoint = bfs_1167(1, nodeNum, adj);
        int[] secPoint = bfs_1167(firstPoint[0], nodeNum, adj)    ;
        
        bw.write(Integer.toString(secPoint[1]));
        bw.flush();
        bw.close();
    }
    static int[] bfs_1167(int start, int n, ArrayList<int[]>[] adj) {
        boolean[] visited = new boolean[n + 1];
        Queue<int[]> queue = new LinkedList<>();

        int[] startArr = {start, 0}; // startnode, lenght
        visited[start] = true;
        queue.add(startArr);

        int crrMaxLenght = 0;
        int crrFartherNode = start;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            if (crr[1] > crrMaxLenght) {
                crrMaxLenght = crr[1];
                crrFartherNode = crr[0];
            }

            for (int[] nextNode : adj[crr[0]]) {
                if (visited[nextNode[0]]) {
                    continue;
                }
                visited[nextNode[0]] = true;
                queue.add(new int[]{nextNode[0], crr[1] + nextNode[1]});
            }
        }
        return new int[]{crrFartherNode, crrMaxLenght};
    }
    static void n1238() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int wayNum = Integer.parseInt(st.nextToken());
        int partyNode = Integer.parseInt(st.nextToken());

        //int[] -> index 0 = targetNode & index 1 = time to spend
        ArrayList<int[]>[] ways = new ArrayList[nodeNum + 1];
        ArrayList<int[]>[] reservedWays = new ArrayList[nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            ways[i] = new ArrayList<int[]>();
            reservedWays[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine());
            int fromNode = Integer.parseInt(st.nextToken());
            int toNode = Integer.parseInt(st.nextToken());
            int useTime = Integer.parseInt(st.nextToken());

            ways[fromNode].add(new int[]{toNode, useTime});
            reservedWays[toNode].add(new int[]{fromNode, useTime});
        }

        int longestTime = 0;

        int[] wayToParty = bfs_1238(partyNode, nodeNum, ways);
        int[] wayToHome = bfs_1238(partyNode, nodeNum, reservedWays);
        for (int i = 1; i < nodeNum + 1; i++) {
            int totalTime = wayToParty[i] + wayToHome[i];
            if (totalTime > longestTime) longestTime = totalTime;
        }

        bw.write(Integer.toString(longestTime));

         
        bw.flush();
        bw.close();
    }
    static int[] bfs_1238(int targetNode, int nodeNum, ArrayList<int[]>[] ways) {
        int[] disk = new int[nodeNum + 1];
        Arrays.fill(disk, Integer.MAX_VALUE);
        disk[targetNode] = 0;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2)-> Integer.compare(n1[1], n2[1]));
        int[] startNode = {targetNode, 0};
        queue.add(startNode);
        
        
        while (!queue.isEmpty()) {
            int[] crrNode = queue.poll();
            
            if (crrNode[1] > disk[crrNode[0]]) continue;

            for (int[] i : ways[crrNode[0]]) {
                //check this way is shotter then disk
                int nextTime = i[1] + crrNode[1];
                if (disk[i[0]] <= nextTime) continue;
                int[] nextNode = {i[0], nextTime};
                disk[nextNode[0]] = nextTime;
                queue.add(nextNode);
            }
        }
        return disk;
    }
    static void n1504() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());
        int graphNum = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] graphs = new ArrayList[nodeNum + 1];
        //init graphs
        for (int i = 1; i < nodeNum + 1; i++) {
            graphs[i] = new ArrayList<int[]>();
        }
        //get inputs from Buffer
        for (int i = 0; i < graphNum; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            graphs[from].add(new int[]{to, weight});
            graphs[to].add(new int[]{from, weight});
        }

        st = new StringTokenizer(br.readLine());
        int target1 = Integer.parseInt(st.nextToken());
        int target2 = Integer.parseInt(st.nextToken());
        int targetFinal = nodeNum;
        st = null;

        int toTarget1 = bfs1504(1, target1, nodeNum, graphNum, graphs);
        int toTarget2 = bfs1504(1, target2, nodeNum, graphNum, graphs);
        int betweenTgs = bfs1504(target1, target2, nodeNum, graphNum, graphs);
        int toFinal1 = bfs1504(target1, targetFinal, nodeNum, graphNum, graphs);
        int toFinal2 = bfs1504(target2, targetFinal, nodeNum, graphNum, graphs);

        boolean[] wayNot = {true, true};
        int way[] = {toTarget1 + betweenTgs + toFinal2, toTarget2 + betweenTgs + toFinal1};
        int result = Integer.MAX_VALUE;
        if (toTarget1 >= 0 && toFinal2 >= 0 && betweenTgs >= 0) {
            result = Math.min(result, way[0]);
        }
        if (toTarget2 >= 0 && toFinal1 >= 0 && betweenTgs >= 0) {
            result = Math.min(result, way[1]);
        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }


        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
    static int bfs1504(int start, int target, int nodeNum, int graphNum, ArrayList<int[]>[] graphs) {
        PriorityQueue<int[]> greedyQueue = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[1], n2[1])); 
        int[] startOj = new int[]{start, 0}; // index 1 : weight for priority
        greedyQueue.add(startOj);

        int disk[] = new int[nodeNum + 1];
        Arrays.fill(disk, Integer.MAX_VALUE);
        disk[start] = 0;
 
        while (!greedyQueue.isEmpty()) {
            int[] crr = greedyQueue.poll();

            if (crr[0] == target) {
                // shortestWay = crr[2];
            }

            for (int[] nextPoint : graphs[crr[0]]) {
                // shoter then disk
                if (disk[nextPoint[0]] > disk[crr[0]] + nextPoint[1]) {
                    disk[nextPoint[0]] = disk[crr[0]] + nextPoint[1];
                    int[] next = new int[]{nextPoint[0], crr[1] + nextPoint[1]};
                    greedyQueue.add(next);
                }
            }
        }

        if (disk[target] == Integer.MAX_VALUE) {
            return -1;
        }
        else {
            return disk[target];
        }
    }
    static void n1967() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodeNum = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] edges = new ArrayList[nodeNum + 1]; // edges num is nodeNum - 1
        //init
        for (int i = 1; i < nodeNum + 1; i++) {
            edges[i] = new ArrayList<int[]>();
        }
        //take input
        for (int i = 0; i < nodeNum - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            //방향 없는 간선
            edges[from].add(new int[]{to, weight});
            edges[to].add(new int[]{from, weight});
        }

        int firstPoint = bfs1967(nodeNum, 1, edges)[0];
        int[] secPoint = bfs1967(nodeNum, firstPoint, edges);
        
        bw.write(Integer.toString(secPoint[1]));

        bw.flush();
        bw.close();
    }
    static int[] bfs1967(int nodeNum, int startPoint, ArrayList<int[]>[] edges) {
        Queue<int[]> queue = new LinkedList<>();
        int[] startNode = {startPoint, 0}; // {crrNode, total weight}
        queue.add(startNode);

        boolean visited[] = new boolean[nodeNum + 1];
        visited[startPoint] = true;

        int longestWay = Integer.MIN_VALUE;
        int targetNode = 0;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();
            
            if (longestWay < crr[1]) {
                longestWay = crr[1];
                targetNode = crr[0];
            }
            for (int[] edge : edges[crr[0]]) {
                int to = edge[0];
                int weight = edge[1];

                if (visited[to]) continue;
                visited[to] = true;
                int[] next = {to, weight + crr[1]};
                queue.add(next);
            }

        }
        int[] result = {targetNode, longestWay};
        return result;
    } 
    static void n15551() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int strNum = Integer.parseInt(st.nextToken());

        String a =  "Aa" + "a".repeat(strNum - 2);;
        String b =  "BB" + "a".repeat(strNum - 2);;

        // bw.write(Integer.toString(a.hashCode()));
        bw.write(a + "\n");
        bw.write(b + "\n");

        bw.flush();
        bw.close();
    }
    private static class MaxHeap1655 {
        int[] heap;
        int capacity;
        int next = 1;
        MaxHeap1655(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
        }

        private void add(int value) {
            if (next > capacity + 1) return;
            int crr = next;
            next += 1;

            heap[crr] = value;
            int parentIndex = crr/2;
            while (parentIndex > 0) {
                if (heap[parentIndex] < heap[crr]) {
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = parentIndex;
                    parentIndex /= 2;
                } else break;
            }
        }
        private int pop() {
            if (next == 1) return Integer.MIN_VALUE;

            int result = heap[1];
            heap[1] = heap[next - 1];
            next--;
            int crr = 1;
            int childIndex = crr * 2;
            while (crr * 2 < next) {
                childIndex = crr * 2;
                if (childIndex > next - 1) {
                    break;
                }
                if (childIndex + 1 < next && heap[childIndex] < heap[childIndex + 1]) {
                    childIndex++;
                }
                if (heap[childIndex] > heap[crr]) {
                    int temp = heap[childIndex];
                    heap[childIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = childIndex;
                    childIndex *= 2;
                } else break;
            }
            return result;
        }
        private int peek() {
            if (next == 1) return -99999; //error code
            return heap[1];
        }
    }
    private static class MinHeap1655 {
        int[] heap;
        int capacity;
        int next = 1;
        MinHeap1655(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
        }

        private void add(int value) {
            if (next > capacity + 1) return;
            int crr = next;
            next += 1;

            heap[crr] = value;
            int parentIndex = crr/2;
            while (parentIndex > 0) {
                if (heap[parentIndex] > heap[crr]) {
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = parentIndex;
                    parentIndex /= 2;
                } else break;
            }
        }
        private int pop() {
            if (next == 1) return Integer.MIN_VALUE;

            int result = heap[1];
            heap[1] = heap[next - 1];
            next--;
            int crr = 1;
            int childIndex = crr * 2;
            while (crr * 2 < next) {
                childIndex = crr * 2;
                if (childIndex > next - 1) {
                    break;
                }
                if (childIndex + 1 < next && heap[childIndex] > heap[childIndex + 1]) {
                    childIndex++;
                }
                if (heap[childIndex] < heap[crr]) {
                    int temp = heap[childIndex];
                    heap[childIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = childIndex;
                    childIndex *= 2;
                } else break;
            }
            return result;
        }
        private int peek() {
            if (next == 1) return -99999; //error code
            return heap[1];
        }
    }
    static void n1655() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        MaxHeap1655 maxH = new MaxHeap1655(num);
        MinHeap1655 minH = new MinHeap1655(num);
        for (int i = 0; i < num; i++) {
            int crr = Integer.parseInt(br.readLine());

            if (maxH.next == minH.next) {
                maxH.add(crr);
            } else {
                minH.add(crr);
            }

            if (maxH.next > 1 && minH.next > 1 && maxH.peek() > minH.peek()) {
                int maxVal = maxH.pop();
                int minVal = minH.pop();
                maxH.add(minVal);
                minH.add(maxVal);
            }
        
            bw.write(Integer.toString(maxH.peek()) + "\n");
        }

        bw.flush();
        bw.close();;
    }
    static void n11404() throws IOException {
        //플로이드 관련 문제임
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int nodeNum = Integer.parseInt(br.readLine());
        int wayNum = Integer.parseInt(br.readLine());
        // take inputs
        int[][] nodemap =  new int[nodeNum + 1][nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            Arrays.fill(nodemap[i], 99999999);
            nodemap[i][i] = 0;
        }
        //init map
        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (nodemap[from][to] > cost) nodemap[from][to] = cost;
        }
        //floid
        for (int i = 1; i < nodeNum + 1; i++) { //middle node
            for (int j = 1; j < nodeNum + 1; j++) { //from node
                for (int k = 1; k < nodeNum + 1; k++) {//to node
                    int newWayCost = nodemap[j][i] + nodemap[i][k];
                    if (nodemap[j][k] > newWayCost) nodemap[j][k] = newWayCost;
                }
            }
        }
        //unreachable 99999999 -> 0
        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum + 1; j++) {
                if (nodemap[i][j] == 99999999) nodemap[i][j] = 0;
            }
        }
        //output
        for (int i = 1; i < nodeNum + 1; i++) {
            bw.write(Integer.toString(nodemap[i][1]));
            for (int j = 2; j < nodeNum + 1; j++) {
                bw.write(" " + Integer.toString(nodemap[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();;
    }
    static void n16236() throws IOException {
        //16236 -> BFS와 구현 문제
        //문제점 : 물고기가 도중에 성장하면, 중간부터 최단경로가 바뀐다. 
        //기존 미리 뽑아두는경우, 바뀐내용이 반영되지 않는다
        //+ 더 큰 물고기는 지나갈 수 없다. 이는 맨해튼 거리가 아닌 BFS사용을 유도한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int boxSize = Integer.parseInt(br.readLine());
        int totalFishes = 0; //need for stop program
        int[][] box = new int[boxSize][boxSize];
        int[] babySharkInfo = {2, 0, 0, 2}; // size, x, y, growCounter
        for (int i = 0; i < boxSize; i++) {
            //initalizing
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < boxSize; j++) {
                int crr = Integer.parseInt(st.nextToken());
                if (crr == 9) {
                    babySharkInfo[1] = i;
                    babySharkInfo[2] = j;
                    crr = 0;
                }
                if (crr != 0) totalFishes++;

                box[i][j] = crr;
            }
        }

        int totalTime = 0;
        while (totalFishes-- != 0) {
            int[] closestFish = findCloseFish16236(boxSize, babySharkInfo, box);
            int eatingTime = eatFish(babySharkInfo, closestFish, box);
            if (eatingTime == Integer.MAX_VALUE) { //mom calling!
                break;
            }

            totalTime += eatingTime;
        }
        
        bw.write(Integer.toString(totalTime));
        bw.flush();
        bw.close();;
    }
    private static int[] findCloseFish16236(int boxSize ,int[] babySharkInfo, int[][] box) {
        int[] firstShark = {babySharkInfo[1], babySharkInfo[2], 0}; //x, y, distance
        int sharkSize = babySharkInfo[0];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(firstShark);
        boolean[][] visited = new boolean[boxSize][boxSize];
        visited[firstShark[0]][firstShark[1]] = true;

        int[] closhFishInfo = {0, 0, Integer.MAX_VALUE}; 

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] crrLocate = queue.poll();
            int crrLocateFishSize = box[crrLocate[0]][crrLocate[1]];
            //find close fish sequence
            if (crrLocateFishSize != 0 && crrLocateFishSize != sharkSize) { //if fish exist (same size pretend 'noFish')
                if (crrLocateFishSize > sharkSize) continue; // bigger fish unable to through
                else {
                    if (closhFishInfo[2] < crrLocate[2]) continue; //isn't closest fish
                    else if (closhFishInfo[2] > crrLocate[2]) { //closest fish!
                        closhFishInfo = crrLocate;
                        continue;
                    }
                    else { // same distance 
                        boolean isPrioritized = false; 
                        if (closhFishInfo[0] > crrLocate[0]) isPrioritized = true;
                        else if (closhFishInfo[0] == crrLocate[0]) {
                            if (closhFishInfo[1] > crrLocate[1]) isPrioritized = true;
                        }

                        if (isPrioritized) closhFishInfo = crrLocate;
                        continue;
                    }
                }
            }

            //bfs Logic
            for (int i = 0; i < 4; i++) {
                int nextX = crrLocate[0] + dx[i];
                int nextY = crrLocate[1] + dy[i];
                //borderCheck && visited?
                if (nextX < 0 || nextX > boxSize - 1 || nextY < 0 || nextY > boxSize - 1) continue;
                if (visited[nextX][nextY]) continue; 
                visited[nextX][nextY] = true;
                //next object
                int[] nextLocate = {nextX, nextY, crrLocate[2] + 1};
                queue.add(nextLocate);
            }
        }

        return closhFishInfo;
    }
    //return taken time
    private static int eatFish(int[] babySharkInfo, int[] targetFish, int[][] box) {
        babySharkInfo[1] = targetFish[0];
        babySharkInfo[2] = targetFish[1];
        if (--babySharkInfo[3] == 0) { //shark grow!
            babySharkInfo[0]++;
            babySharkInfo[3] = babySharkInfo[0];
        }

        box[targetFish[0]][targetFish[1]] = 0; //eaten
        return targetFish[2];
    }
    private static void n2206() throws IOException {
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
    private static void n12851() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] subin = {n, 0}; //locate, moved num

        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        visited[n] = 0;
        queue.add(subin);
        int resultCase = 0;

        boolean pin = false;
        int pinnedShortestMove = 0;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            if (crr[0] == m) {
                if (!pin) {
                    pinnedShortestMove = crr[1];
                    pin = true;
                }
                if (pinnedShortestMove == crr[1]) {
                    resultCase++;
                }
            }

            int[] nextp1 = {crr[0] + 1, crr[1] + 1};
            if ((nextp1[0] >= 0 && nextp1[0] < 100001 && visited[nextp1[0]] >= nextp1[1])) {
                visited[nextp1[0]] = nextp1[1]; 
                queue.add(nextp1);
            }
            int[] nextm1 = {crr[0] - 1, crr[1] + 1};
            if ((nextm1[0] >= 0 && nextm1[0] < 100001 && visited[nextm1[0]] >= nextm1[1])) {
                visited[nextm1[0]] = nextm1[1];
                queue.add(nextm1);
            }
            int[] nextTp = {crr[0] * 2, crr[1] + 1};
            if ((nextTp[0] >= 0 && nextTp[0] < 100001 && visited[nextTp[0]] >= nextTp[1])) {
                visited[nextTp[0]] = nextTp[1];
                queue.add(nextTp);
            }
        }
        int result = visited[m];

        bw.write(Integer.toString(result) + "\n");
        bw.write(Integer.toString(resultCase));

        bw.flush();
        bw.close();;
    } 
    //1918
    static void n1918() throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //분리대상 : 알파벳, +-*/, (). 알파벳과 나머지를 이분법적으로 보고 Lookahead, Lookbehind로 분리
        String regex = "(?<=[+\\-*/%^()])|(?=[+\\-*/%^()])";
        String ori = br.readLine();
        String[] splited = ori.split(regex);

        //배열을 2차원 리스트 * 배열로 바꿈. 배열 = {우선순위, 연산자종류 혹은 숫자}
        int parenStack = 0;
        ArrayList<int[]> pSplited = new ArrayList<>();
        for (int i = 0; i < splited.length; i++) {
            String crr = splited[i];
            if (crr.matches("[a-z|A-Z]+")) {
                int[] temp = {Integer.MIN_VALUE, (int) crr.charAt(0)}; //MinValue means its numberic 
                pSplited.add(temp);
            }
            //우선순위 점수 : 기본점수100 + 괄호 중첩 갯수 * 200 + */추가점수1000 - 인덱스
            else if (crr.equals("+")) {
                int[] temp = {100 + parenStack * 200 - i, 1}; // 1 means +
                pSplited.add(temp);
            }
            else if (crr.equals("-")) {
                int[] temp = {100 + parenStack * 200 - i, 2}; // 2 means -
                pSplited.add(temp);
            }
            else if (crr.equals("*")) {
                int[] temp = {100 + parenStack * 200 + 100 - i, 3}; // 3 means *
                pSplited.add(temp);
            }
            else if (crr.equals("/")) {
                int[] temp = {100 + parenStack * 200 + 100 - i, 4}; // 4 means /
                pSplited.add(temp);
            }
            else if (crr.equals("(")) {
                parenStack++;
            }
            else if (crr.equals(")")) {
                parenStack--;
            }
        }

        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int[] crr : pSplited) {
            //이번것이 숫자면
            if (crr[0] == Integer.MIN_VALUE) {
                sb.append(Character.toString((char) crr[1]));
            }
            //연산자라면
            else {
                //스택이 빌경우, 값 스택에 추가
                if (stack.isEmpty()) {
                    stack.add(crr);
                    continue;
                }
                //이번게 스택꺼보다 작으면, 큰게 나올때까지 스택에 있는거 출력
                int[] prev = stack.pop();
                while (prev[0] > crr[0]) {
                    if (prev[1] == 1) sb.append("+");
                    else if (prev[1] == 2) sb.append("-");
                    else if (prev[1] == 3) sb.append("*");
                    else sb.append("/");

                    // 만약 중간에 텅텅 비었다면, 이번꺼를 스택에 넣고 반복 끝
                    if (stack.isEmpty()) {
                        stack.add(crr);
                        break;
                    }
                    prev = stack.pop();
                }
                //이번게 스택꺼보다 크면 둘다 스택에 넣기
                if (prev[0] < crr[0]) {
                    stack.add(prev);
                    stack.add(crr);
                    continue;
                }
            }
        }
        // 스택 털기
        while (!stack.isEmpty()) {
            int[] crr = stack.pop();
            if (crr[1] == 1) sb.append("+");
            else if (crr[1] == 2) sb.append("-");
            else if (crr[1] == 3) sb.append("*");
            else sb.append("/");
        }

        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
    }
}
