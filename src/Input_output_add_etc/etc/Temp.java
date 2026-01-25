package etc;
//This class saves temp resorce or stocks

// public class Main {
//     public static void main(String[] args) throws IOException {

//     }
// }


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Temp {

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
    ///////////////////////////////////
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
    ///////////////////////
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