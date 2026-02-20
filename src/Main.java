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
    private static class Node1987 {
        int x;
        int y;
        int depth;
        boolean[] visitedAlph;

        Node1987(int x, int y, int depth, boolean[] visitedAlph) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.visitedAlph = visitedAlph;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] node = new int[r][c];

        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                node[i][j] = ((int) line.charAt(j)) - 65;
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        boolean[] startChecker = new boolean[26];
        startChecker[node[0][0]] = true;
        Node1987 startNode = new Node1987(0, 0, 1, startChecker);
        Stack<Node1987> stack = new Stack<>();
        stack.add(startNode);

        int maxDepth = 0;
        while (!stack.isEmpty()) {
            Node1987 crrNode = stack.pop();
            maxDepth = Math.max(maxDepth, crrNode.depth);
            
            for (int i = 0; i < 4; i++) {
                int nextX = crrNode.x + dx[i];
                int nextY = crrNode.y + dy[i];
                if (nextX < 0 || nextX >= r || nextY < 0 || nextY >= c) continue; // border check
                int nextAlph = node[nextX][nextY];
                if (crrNode.visitedAlph[nextAlph]) continue; //already exist

                boolean[] nextVisited = crrNode.visitedAlph.clone();
                nextVisited[nextAlph] = true;
                int nextDepth = crrNode.depth + 1;
                Node1987 nextNode = new Node1987(nextX, nextY, nextDepth, nextVisited);
                stack.add(nextNode);
            }
        }
        
        bw.write(Integer.toString(maxDepth));
        bw.flush();
        bw.close();
    }

}