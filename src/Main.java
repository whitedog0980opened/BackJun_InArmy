import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNum; i++) {
            int floor = Integer.parseInt(br.readLine());
            int roomNum = Integer.parseInt(br.readLine());
            int[][] peopleNumDp = new int[floor + 1][roomNum + 1];
            //init 0 floor peopleNum
            for (int j = 1; j <= roomNum; j++) {
                peopleNumDp[0][j] = j;
            }
            //init roomNum 1 = 1
            for (int j = 1; j <= floor; j++) {
                peopleNumDp[j][1] = 1;
            }

            for (int j = 1; j <= floor; j++) {
                for (int k = 2; k <= roomNum; k++) {
                    peopleNumDp[j][k] = peopleNumDp[j - 1][k] + peopleNumDp[j][k - 1];
                }
            }
            bw.write(Integer.toString(peopleNumDp[floor][roomNum]));
            if (i + 1 != testCaseNum) bw.newLine();
        }


        bw.flush();
        bw.close();
    }

}
