package slivers;
import java.util.*;
import java.io.*;

//this class made because Sliver class is too long

public class Sliver2{
    static void n14940() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        boolean[][] mapCanGo = new boolean[N][M];
        int[][] wayToTarget = new int[N][M];
        int[] target = new int[2];

        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                int crr = Integer.parseInt(inputLine[j]);
                if (crr == 1) {
                    mapCanGo[i][j] = true;
                    wayToTarget[i][j] = -1;
                }
                else if (crr == 2) {
                    mapCanGo[i][j] = true;
                    target = new int[]{i, j};
                }
            }
        }
        // index2 = reach to target
        int[] startPoint = {target[0], target[1], 0};
        wayToTarget[startPoint[0]][startPoint[1]] = 0;
        mapCanGo[startPoint[0]][startPoint[1]] = false;

        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};

        Queue<int[]> queue = new LinkedList<>();
        queue.add(startPoint);

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            for (int i = 0; i < 4; i++) {
                int[] next = {crr[0] + dx[i], crr[1] + dy[i], crr[2] + 1};
                boolean borderCheck = next[0] >= 0 && next[0] < N &&
                                    next[1] >= 0 && next[1] < M;
                if (borderCheck && mapCanGo[next[0]][next[1]]) {
                    wayToTarget[next[0]][next[1]] = next[2];
                    mapCanGo[next[0]][next[1]] = false;
                    queue.add(next);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < M; j++) {
                if (j != 0) sb.append(" ");
                sb.append(Integer.toString(wayToTarget[i][j]));
            }
            bw.write(sb.toString());
            bw.newLine();
        }
        
        bw.flush();
        bw.close();
    }
    static void n18111() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int blocks = Integer.parseInt(st.nextToken());

        int maxHeight = 0;
        //init map
        int[] map = new int[n * m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int crr = Integer.parseInt(st.nextToken());
                map[i * m + j] = crr;

                maxHeight = Math.max(maxHeight, crr);
            }
        }
        
        Arrays.sort(map);
        int startHeight = maxHeight; //start from highest y
        int minimumTime = Integer.MAX_VALUE;
        int y = 0;
        for (int crrHeight = startHeight; crrHeight >= 0; crrHeight--) {
            int needBlocks = 0;
            int spentTime = 0;
            //check has enough blocks
            for (int j : map) {
                int diff = Math.abs(j - crrHeight);
                if (j < crrHeight) {
                    needBlocks += diff;
                    spentTime += diff;
                }
                else if (j > crrHeight) {
                    needBlocks -= diff;
                    spentTime += diff * 2;
                }
            }
            boolean hasEnough = needBlocks <= blocks;
            if (hasEnough && minimumTime > spentTime) {
                minimumTime = spentTime;
                y = crrHeight;
            }
        }


        bw.write(Integer.toString(minimumTime) + " " + Integer.toString(y));
        bw.flush();
        bw.close();
    }
    static void n14405() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] pikaSaid = br.readLine().toCharArray();

        int size = pikaSaid.length;
        boolean isCorrect = true;
        for (int i = 0; i < size; i++) { 
            //borderCheck
            if (i + 1 >= size) {
                isCorrect = false;
                break;
            }
            if (pikaSaid[i] == 'p' && pikaSaid[i + 1] == 'i') {
                i++;
                continue;
            }
            if (pikaSaid[i] == 'k' && pikaSaid[i + 1] == 'a') {
                i++;
                continue;
            }
            if (i + 2 >= size) {
                isCorrect = false;
                break;
            }
            if (pikaSaid[i] == 'c' && pikaSaid[i + 1] == 'h' && pikaSaid[i + 2] == 'u') {
                i += 2;
                continue;
            }
            isCorrect = false;
            break;
        }

        if (isCorrect) bw.write("YES");
        else bw.write("NO");
        bw.flush();
        bw.close();
    }
    static void n25594() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String hg = br.readLine();
        boolean isCorrect = true;
        StringBuilder str = new StringBuilder();

        int i = 0;
        while (i < hg.length()) {
            if (hg.startsWith("aespa" , i)) {
                i += 5;
                str.append("a");
            }
            else if (hg.startsWith("baekjoon" , i)) {
                i += 8;
                str.append("b");
            }
            else if (hg.startsWith("cau" , i)) {
                i += 3;
                str.append("c");
            }
            else if (hg.startsWith( "debug", i)) {
                i += 5;
                str.append("d");
            }
            else if (hg.startsWith("edge" , i)){
                i += 4;
                str.append("e");
            }
            else if (hg.startsWith("firefox" , i)){
                i += 7;
                str.append("f");
            }
            else if (hg.startsWith("golang" , i)) {
                i += 6;
                str.append("g");
            }
            else if (hg.startsWith("haegang" , i)) {
                i += 7;
                str.append("h");
            }
            else if (hg.startsWith("iu" , i)){
                i += 2;
                str.append("i");
            }
            else if (hg.startsWith("java" , i)) {
                i += 4;
                str.append("j");
            }
            else if (hg.startsWith("kotlin" , i)) {
                i += 6;
                str.append("k");
            }
            else if (hg.startsWith("lol" , i)) {
                i += 3;
                str.append("l");
            }
            else if (hg.startsWith("mips" , i)){
                i += 4;
                str.append("m");
            }
            else if (hg.startsWith("null" , i)) {
                i += 4;
                str.append("n");
            }
            else if (hg.startsWith("os" , i)) {
                i += 2;
                str.append("o");
            }
            else if (hg.startsWith("python" , i)) {
                i += 6;
                str.append("p");
            }
            else if (hg.startsWith("query" , i)) {
                i += 5;
                str.append("q");
            }
            else if (hg.startsWith("roka" , i)) {
                i += 4;
                str.append("r");
            }
            else if (hg.startsWith("solvedac" , i)) {
                i += 8;
                str.append("s");
            }
            else if (hg.startsWith("tod" , i)) {
                i += 3;
                str.append("t");
            }
            else if (hg.startsWith("unix" , i)) {
                i += 4;
                str.append("u");
            }
            else if (hg.startsWith("virus" , i)){
                i += 5;
                str.append("v");
            }
            else if (hg.startsWith("whale" , i)) {
                i += 5;
                str.append("w");
            }
            else if (hg.startsWith("xcode" , i)) {
                i += 5;
                str.append("x");
            }
            else if (hg.startsWith("yahoo" , i)) {
                i += 5;
                str.append("y");
            }
            else if (hg.startsWith("zebra" , i)) {
                i += 5;
                str.append("z");
            }
            else { 
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            bw.write("It's HG!\n" + str.toString());
        }
        else bw.write("ERROR!");
        bw.flush();
        bw.close();
    }
    static void n14534() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        HashSet<Integer> list = new HashSet<>();
        ArrayList<Integer>[] patterns = new ArrayList[6];

        for (int i = 0; i < tc; i++) {
            char[] chars = br.readLine().toCharArray();

            //check pattern exist
            int maxDepth = chars.length;
            if (patterns[maxDepth] != null) {
                printCase14534(chars, patterns[maxDepth], i, bw);
                continue;
            }

            int[] indexOfChars = new int[chars.length];
            for (int j = 0; j < chars.length; j++) {
                indexOfChars[j] = j;
            }
            
            dfs14534(0, indexOfChars, chars, list);
            ArrayList<Integer> sortedList = new ArrayList<>(list);
            Collections.sort(sortedList);
            patterns[maxDepth] = sortedList;
            printCase14534(chars, patterns[maxDepth], i, bw);
            list = new HashSet<>();
        }

        bw.flush();
        bw.close();
    }
    static void dfs14534(int depth, int[] arr, char[] chars, HashSet<Integer> list) {
        if (depth == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                list.add(arrToInt14534(arr));
            }
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap14534(depth, i, arr);
            dfs14534(depth + 1, arr, chars, list);
            swap14534(depth, i, arr);
        }
    }
    static void swap14534(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int arrToInt14534(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] * (int) Math.pow(10 ,arr.length - i - 1);
        }
        return result;
    }
    static void printCase14534(char[] chars, ArrayList<Integer> pattern, int testcase, BufferedWriter bw) throws IOException {
        if (testcase == 0) {
            bw.write("Case # " + Integer.toString(testcase + 1) + ":");
        } else {
            bw.write("\nCase # " + Integer.toString(testcase + 1) + ":");
        }
        //testCodes
        // bw.newLine();
        // for (int temp : pattern) {
        //     bw.write(Integer.toString(temp) + ", ");
        // }
        // bw.newLine();
        // for (char temp : chars) {
        //     bw.write(Character.toString(temp) + ", ");
        // }
        int charNum = chars.length;
        for (int singlePtn : pattern) {
            bw.newLine();

            int pre = 0;
            for (int i = charNum - 1; i >= 0; i--) {
                int crr = (singlePtn / (int) Math.pow(10, i)) - pre ;
                bw.write(Character.toString(chars[crr]));
                pre = pre * 10 + crr * 10;
            }
        }
    }
    static void n25943() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int graveNum = Integer.parseInt(br.readLine());

        int[] graves = new int[graveNum];
        //init by input
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < graveNum; i++) {
            graves[i] = Integer.parseInt(st.nextToken());
        }
        //first step -> put grave each dish
        int dishL = graves[0];
        int dishR = graves[1];

        //second step -> put each grave on less weight dish

        for (int i = 2; i < graveNum; i++) {
            int crrGrave = graves[i];
            if (dishL > dishR) dishR += crrGrave;
            else dishL += crrGrave; 
        }

        int diff = Math.abs(dishL - dishR);
        int weightNum = 0;


        int able100W = diff / 100;
        if (able100W > 0) {
            diff -= able100W * 100;
            weightNum += able100W;
        }
        boolean isAble50W = diff / 50 > 0; //50weight can able to use only 1
        if (isAble50W) {
            diff -= 50;
            weightNum++;
        }
        int able20W = diff / 20;
        if (able20W > 0) {
            diff -= able20W * 20;
            weightNum += able20W;
        }
        int able10W = diff / 10;
        if (able10W > 0) {
            diff -= able10W * 10;
            weightNum += able10W;
        }
        boolean isAble5W = diff / 5 > 0; //50weight can able to use only 1
        if (isAble5W) {
            diff -= 5;
            weightNum++;
        }
        int able2W = diff / 2;
        if (able2W > 0) {
            diff -= able2W * 2;
            weightNum += able2W;
        }
        int able1W = diff;
        if (able1W > 0) {
            diff -= able1W;
            weightNum += able1W;
        }

        bw.write(Integer.toString(weightNum));

        bw.flush();
        bw.close();
    }
    static void n21736() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] mapInputs = br.readLine().split(" ");
        int n = Integer.parseInt(mapInputs[0]); //row of map
        int m = Integer.parseInt(mapInputs[1]);
        
        boolean[][] map = new boolean[n][m]; //where people
        boolean[][] visited = new boolean[n][m];
        int[] startXY = new int[2];

        for (int i = 0; i < n; i++) {
            String[] lineIP = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                if (lineIP[j].equals("P")) {
                    map[i][j] = true;
                }
                else if (lineIP[j].equals("X")) {
                    visited[i][j] = true; 
                }
                else if (lineIP[j].equals("I")) { //start poinr
                    startXY[0] = i;
                    startXY[1] = j;
                } 
            }
        }

        int[] dx = {0, 0, -1 , 1};
        int[] dy = {-1, 1, 0, 0};

        int meetable = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(startXY);
        visited[startXY[0]][startXY[1]] = true;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();
            if (map[crr[0]][crr[1]]) {
                meetable++;
            }

            for (int i = 0; i < 4; i++) {
                int[] next = {crr[0] + dx[i], crr[1] + dy[i]};
                boolean borderCheck = next[0] >= 0 && next[0] < n && next[1] >= 0 && next[1] < m;
                if (borderCheck && !visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }

        if (meetable == 0) {
            bw.write("TT");
        } else {
            bw.write(Integer.toString(meetable));
        }
        bw.flush();
        bw.close();
    }
    static void n2103() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        int[][] xSortedNode = new int[nodeNum][2];
        int[][] ySortedNode = new int[nodeNum][2];
        //get input and init
        StringTokenizer st = null;
        for (int i = 0; i < nodeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int[] node = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            xSortedNode[i] = node;
            ySortedNode[i] = node;
        }
        //sort array in order (if same, other axis sort)
        Arrays.sort(xSortedNode, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a1[1] - a2[1];
            }
            return a1[0] - a2[0];
        });
        Arrays.sort(ySortedNode, (a1, a2) ->{
            if (a1[1] == a2[1]) {
                return a1[0] - a2[0];
            }
            return a1[1] - a2[1];
        });
        
        int result = 0;
        for (int i = 0; i < (int) nodeNum/2; i++) {
            int crrIndex = i*2; // if i % 2 == 0, index i and i+1 is must same num
            int edgeY = Math.abs(xSortedNode[crrIndex][1] - xSortedNode[crrIndex + 1][1]);
            int edgeX = Math.abs(ySortedNode[crrIndex][0] - ySortedNode[crrIndex + 1][0]);
            result += edgeX + edgeY;
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();;
    }
    static void n13399() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int num = Integer.parseInt(inputs[0]);
        int quesNum = Integer.parseInt(inputs[1]);

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i < num + 1; i++) {
            arr.add(i);
        }

        HashSet<Integer> fixedNums = new HashSet<>();
        HashSet<Integer> calledNums = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < quesNum; i++) {
            int ques = Integer.parseInt(br.readLine());
            st.push(ques);
            fixedNums.add(ques);
        }

        outputs : for (int i = 0; i < num;) {
            int result = 0;
            if (!st.isEmpty()) {
                result = st.pop();
                if (calledNums.contains(result)) continue;
                calledNums.add(result);
                bw.write(Integer.toString(result) + "\n");
                continue;
            }
            while (fixedNums.contains(arr.get(i))) {
                i++;
                if (i >= num) {
                    break outputs;
                }
            }
            bw.write(Integer.toString(arr.get(i)) + "\n");
            i++;
        }
        bw.flush();
        bw.close();;
    }
    private static class IntArrayWrapper15650 {
        private final int[] data;
        public IntArrayWrapper15650(int[] data) { this.data = data; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof IntArrayWrapper15650)) return false;
            return Arrays.equals(this.data, ((IntArrayWrapper15650) o).data);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }
    }
    private static void n15650() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = i + 1;
        }
        HashSet<IntArrayWrapper15650> hs = new HashSet<>();
        
        fLoop : while (true) {
            int crrIndex = m - 1;
            boolean wrongCase = true;
            while (wrongCase) {
                wrongCase = false;
                for (int i = 0; i < m; i++) {
                    if (arr[i] + (m-1 - i) > n) { //이상한거 찾음
                        // wrongCase = true;
                        if (i - 1 < 0) break fLoop;
                        for (int j = (i - 1); j < m; j++) { //정상화
                            arr[j]++;
                            if (j + 1 > m - 1) break;
                            arr[j + 1] = arr[j];
                        }
                        if (arr[i - 1] + (m - 1 - i + 1) > n) wrongCase = true;
                        if (wrongCase && i - 1 == 0) break fLoop;
                    }
                }
            }

            crrIndex = m - 1;
            //중복 체크
            IntArrayWrapper15650 tempWapped = new IntArrayWrapper15650(Arrays.copyOf(arr, m));
            if (hs.contains(tempWapped)) continue;
            hs.add(tempWapped);
            //print
            bw.write(Integer.toString(arr[0]));
            for (int i = 1; i < m; i++) {
                bw.write(" " + Integer.toString(arr[i]));
            }
            bw.newLine();

            arr[crrIndex]++;
        }
        bw.flush();
        bw.close();;
    }
    private static void n34851() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long prev = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n + 1; i++) {
            long crr = Integer.parseInt(st.nextToken());
            if (prev == 1 || crr == 1) {
                prev = crr + prev;
            }
            else  {
                prev %= 1000000007;
                prev *= crr;
            }
        }
        prev %= 1000000007;
        int result = (int) prev;

        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();;
    }
    private static void n31802() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int pr = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] prArr = new long[pr + 1];
        prArr[1] = Integer.parseInt(st.nextToken());
        for (int i = 2; i < pr + 1; i++) {
            prArr[i] = prArr[i - 1] + Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int quotientA = Math.floorDiv(a, pr);
        int quotientB = Math.floorDiv(b, pr);
        int remainderA = Math.floorMod(a, pr);
        int remainderB = Math.floorMod(b, pr);

        long total = prArr[remainderB] - prArr[remainderA] + (quotientB - quotientA) * prArr[pr];
        bw.write(Long.toString(total));
        bw.flush();
        bw.close();
    }
    private static void n1004() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int planetNum = Integer.parseInt(br.readLine());

            int needToPene = 0;
            for (int i = 0; i < planetNum; i++) {
                st = new StringTokenizer(br.readLine());
                int planetX = Integer.parseInt(st.nextToken());
                int planetY = Integer.parseInt(st.nextToken());
                int planetR = Integer.parseInt(st.nextToken());

                double startToPlX = Math.abs(planetX - startX);
                double startToPlY = Math.abs(planetY - startY);
                double endToPlX = Math.abs(planetX - endX);
                double endToPlY = Math.abs(planetY - endY);


                boolean startInnerPlanet = Math.sqrt(Math.pow(startToPlX, 2) + Math.pow(startToPlY, 2)) < planetR;
                boolean endInnerPlanet = Math.sqrt(Math.pow(endToPlX, 2) + Math.pow(endToPlY, 2)) < planetR;
                if (startInnerPlanet != endInnerPlanet) needToPene++;
            }

            bw.write(Integer.toString(needToPene) + "\n");
        }

        bw.flush();
        bw.close();
    }
    private static void n1932() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int h = Integer.parseInt(br.readLine());
        int[] prevLine = {Integer.parseInt(br.readLine())}; //firstLine (root)
        int maxValue = prevLine[0];
        for (int i = 1; i < h; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] crrLine = new int[i + 1];
            for (int j = 0; j < i + 1; j++) {
                int crrNum = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    crrLine[j] = crrNum + prevLine[j];
                } else if (j == i) {
                    crrLine[j] = crrNum + prevLine[j - 1];
                } else {
                    crrLine[j] = Math.max(crrNum + prevLine[j - 1], crrNum + prevLine[j]);
                }
                maxValue = Math.max(crrLine[j], maxValue);
            }
            prevLine = crrLine;
        }

        bw.write(Integer.toString(maxValue));
        bw.flush();
        bw.close();
    }
}