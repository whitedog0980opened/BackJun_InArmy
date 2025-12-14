import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        int houseNum = 0;
        ArrayList<Integer> houseSizes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(inputLine[j]) == 1 ? true : false; // map[y][x] 
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int result = findHouse(j, i, map, N, visited);
                if (result >= 1) {
                    houseNum++;
                    houseSizes.add(result);
                }

            }
        }
        Collections.sort(houseSizes);
        bw.write(Integer.toString(houseNum));
        for (int i = 0; i < houseSizes.size(); i++) {
            bw.newLine();
            bw.write(Integer.toString(houseSizes.get(i)));
        }


        bw.flush();
        bw.close();
    }
    static int findHouse(int crrX, int crrY, boolean[][] map, int N, boolean[][] visited) {
        if (!map[crrY][crrX] || visited[crrY][crrX]) {
            return 0;
        }
        int result = 1;
        visited[crrY][crrX] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nextX = crrX + dx[i];
            int nextY = crrY + dy[i];
            boolean borderCheck = nextX >= 0 && nextX < N && nextY >= 0 && nextY < N;
            if (borderCheck && map[nextY][nextX] && !visited[nextY][nextX]) {
                result += findHouse(nextX, nextY, map, N, visited);
            } else {
                continue;
            }
        }
        return result;
    }


}
