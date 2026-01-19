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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
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
}