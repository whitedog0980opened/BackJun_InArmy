import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int peopleNum = Integer.parseInt(br.readLine());

        int[][] bodyData = new int[peopleNum][2];
        for (int i = 0; i < peopleNum; i++) {
            String[] inputStrs = br.readLine().split(" ");
            int weight = Integer.parseInt(inputStrs[0]);
            int height = Integer.parseInt(inputStrs[1]);

            bodyData[i][0] = weight;
            bodyData[i][1] = height;
        }

        int point[] = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            for (int j = 0; j < peopleNum; j++) {
                point[i] = (bodyData[i][0] < bodyData[j][0] 
                            && bodyData[i][1] < bodyData[j][1]
                            ) ? ++point[i] : point[i];
            }
        }

        // //compare score
        // int[] grade = new int[peopleNum];
        // int crrGrade = 0;
        // int sameStack = 0;
        // int max = Integer.MAX_VALUE;
        // for (int i = 0; i < peopleNum; i++) {
        //     int maxI = 0;
        //     int maxCrr = -1;
        //     for (int j = 0; j < peopleNum; j++) {
        //         if (maxCrr < point[j]) {
        //             maxCrr = point[j];
        //             maxI = j;
        //         }
        //     }
        //     if (max > maxCrr) { //if max value down -> grade++
        //         crrGrade += sameStack + 1;
        //         max = maxCrr;
        //         sameStack = 0;
        //     } else {
        //         sameStack++;
        //     }
        //     point[maxI] = -1;
        //     grade[maxI] = crrGrade;
        // }

        boolean isFirst = true;
        for (int i : point) {
            if (isFirst) isFirst = false;
            else bw.write(" ");
            bw.write(Integer.toString(i + 1));
        }
        

        bw.flush();
        bw.close();
    }
}
