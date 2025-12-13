import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
public class Main {
    static int fibo1 = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int N = Integer.parseInt(br.readLine());
        int foldableNum = Integer.numberOfTrailingZeros(N); //2의 몇승인가
        boolean[][] paper = new boolean[N][N];
        boolean[][] isChecked = new boolean[N][N];
        int falseNum = 0;
        int trueNum = 0;

        boolean allSame1 = true; //all false
        boolean allSame2 = true; //all true
        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                boolean input = Integer.parseInt(inputLine[j]) == 1 ? false : true;
                paper[j][i] = input;
                if (input) allSame1 = false;
                else allSame2 = false; 
            }
        }

        if (allSame1) {
            bw.write("0\n1");
            bw.flush();
            bw.close();
            return;
        } 
        if (allSame2) {
            bw.write("1\n0");
            bw.flush();
            bw.close();
            return;
        }

        for (int i = 0; i < foldableNum; i++) {
            int splitUnit = N / (1 << (i + 1));
            boolean[] isIno = new boolean[1 << ((i + 1) * 2)];
            Arrays.fill(isIno, true);
            for (int j = 0; j < (1 << ((i + 1) * 2)); j++) {
                int startPointX = (j % (1 << (i + 1))) * splitUnit;
                int startPointY = (j / (1 << (i + 1))) * splitUnit; 
                if (isChecked[startPointX][startPointY]) continue; //check isCounted paper

                boolean color = paper[startPointX][startPointY];
                for (int k = 0; k < splitUnit * splitUnit; k++) {
                    int crrX = startPointX + k % splitUnit;
                    int crrY = startPointY + k / splitUnit;
                    if (paper[crrX][crrY] != color)  {
                        isIno[j] = false;
                        break;
                    }
                }
                //marking if counted
                if (isIno[j]) {// all paper was same color
                    if (color) trueNum++;
                    else falseNum++;
                    for (int k = 0; k < Math.pow(splitUnit, 2); k++) {
                        int crrX = startPointX + k % splitUnit;
                        int crrY = startPointY + k / splitUnit;
                        isChecked[crrX][crrY] = true;
                    }
                }

            }
        }

        bw.write(Integer.toString(trueNum) + "\n" + Integer.toString(falseNum));

        bw.flush();
        bw.close();
    }


}
