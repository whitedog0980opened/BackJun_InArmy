import java.io.;
import java.util.;

public class Main {
public static void main(String[] args) throws IOException {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    //handle inputs
    String mapSizeInput[] = br.readLine().split(" ");
    int mapSizeX = Integer.parseInt(mapSizeInput[0]);
    int mapSizeY = Integer.parseInt(mapSizeInput[1]);
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
        {0, -1},
        {1, -1},
        {1, -2}
    };
    int[][] tetraL = {
        {0, -1},
        {0, -2},
        {1, -2}
    };

    int max = -2;
    //repeat for rotate
    for (int i = 0; i < 4; i++) {
        max = Math.max(max, tetraTry(tetraT, map, mapSizeX, mapSizeY, i));
        max = Math.max(max, tetraTry(tetraI, map, mapSizeX, mapSizeY, i));
        max = Math.max(max, tetraTry(tetraO, map, mapSizeX, mapSizeY, i));
        max = Math.max(max, tetraTry(tetraZ, map, mapSizeX, mapSizeY, i));
        max = Math.max(max, tetraTry(tetraL, map, mapSizeX, mapSizeY, i));
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