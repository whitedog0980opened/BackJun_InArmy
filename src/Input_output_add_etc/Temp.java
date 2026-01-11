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
import java.util.LinkedList;
import java.util.Queue;

public class Temp {
    public void n1167_inProgress() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());

        //from = first index, to = sec index
        int[][] map = new int[nodeNum + 1][nodeNum + 1]; 
        // init map
        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crrNode = Integer.parseInt(inputs[0]);
            int crr = 0;
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                map[crrNode][nextNode] = length;
            }
        }

        // i = crrNode
        for (int i = 1; i < nodeNum + 1; i++) {
            //this loop for find fromNum
            int[] newConnection = new int[nodeNum + 1];
            for (int j = 1; j < nodeNum + 1; j++) {
                int length = map[i][j];
                if (length == 0) continue; //empty 

                //this loop for find toNum and add new connection
                for (int k = 1; k < nodeNum + 1; k++) {
                    if (map[j][k] == 0 || k == i) continue; //empty or fromNum and toNum endPoint is same 
                    if (newConnection[k] == 0 || (newConnection[k] > map[j][k] + map[i][j])) {
                        newConnection[k] = map[j][k] + map[i][j];
                    }
                }
                boolean hasblank = false;
            }
        }
        bw.write("stopPoint");
        //42 인덱스 범위 초가 주의
        //아마 맵 내부의 숫자는 거리를 뜻하는데, 이게 fromNum으로 착각해서 생김
        //즉 첫번째 루프는 fromNum이 2가 아니라 2가 1.3 인덱스에 있으니까 from은 3이 됨


        bw.flush();
        bw.close();
    }
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
}