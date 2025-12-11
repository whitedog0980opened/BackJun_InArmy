import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
public class Main {
    public static void main(String[] args) throws IOException {
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
}
