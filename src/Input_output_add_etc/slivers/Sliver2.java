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
}