import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//need to review = 5430 ! deque

public class Main {

    public static void main(String[] args) throws IOException {
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
}
