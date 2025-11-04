import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNum; i++) {
            String[] strInputs = br.readLine().split(" ");
            int n = Integer.parseInt(strInputs[0]);
            int m = Integer.parseInt(strInputs[1]);
            int numOfCabbage = Integer.parseInt(strInputs[2]);
            int cattapliler = 0;
            boolean[][] cabbageMap = new boolean[m + 1][n + 1];
            //init cabbageMap
            for (int j = 0; j < numOfCabbage; j++) {
                String[] cabbageLocation = br.readLine().split(" ");
                int clM = Integer.parseInt(cabbageLocation[1]);
                int clN = Integer.parseInt(cabbageLocation[0]);

                cabbageMap[clM][clN] = true;
            }

            //counting catta
            for (int k = 0; k < n; k++) {
                if (cabbageMap[0][k] == true) {
                    cattapliler++;
                    deleteAdjacentFirstLine(0, k, cabbageMap);
                }
            }
            for (int j = 1; j < m; j++) { //otherLines
                for (int k = 0; k < n; k++) {
                    if (cabbageMap[j][k] == true) {
                        cattapliler++;
                        deleteAdjacent(j, k, cabbageMap);
                    }
                }
            }
            bw.write(Integer.toString(cattapliler));
            if (i + 1 != testCaseNum) bw.newLine();
        }


        bw.flush();
        bw.close();
    }

    public static void deleteAdjacent(int m,int n, boolean[][] cabbageMap) {
        cabbageMap[m][n] = false;
        if (cabbageMap[m + 1][n] == true) {
            deleteAdjacent(m + 1, n, cabbageMap);
        }
        if (cabbageMap[m][n + 1] == true) {
            deleteAdjacent(m, n + 1, cabbageMap);
        }
        if (cabbageMap[m - 1][n] == true) {
            if (m - 1 == 0) {
                deleteAdjacentFirstLine(0, n, cabbageMap);
            } else {
                deleteAdjacent(m - 1, n, cabbageMap);
            }
        }
    }


    public static void deleteAdjacentFirstLine(int m,int n, boolean[][] cabbageMap) {
        cabbageMap[m][n] = false;
        if (cabbageMap[m + 1][n] == true) {
            deleteAdjacent(m + 1, n, cabbageMap);
        }
        if (cabbageMap[m][n + 1] == true) {
            deleteAdjacentFirstLine(m, n + 1, cabbageMap);
        }
    }

}
