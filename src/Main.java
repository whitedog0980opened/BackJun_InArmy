import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int row = Integer.parseInt(inputs[1]);
        int col = Integer.parseInt(inputs[2]);

        int startNum = 0;
        int endNum = (1 << (2 * n)) - 1; // (2^n)^2 - 1
        int totalCellNum = endNum + 1;

        int diviedBy = 1 << (n - 1);
        int rDivied = row / diviedBy;
        int rRest = row % diviedBy;
        int cDivied = col / diviedBy;
        int cRest = col % diviedBy;

        while (true) {
            startNum += rDivied * (totalCellNum / 2);
            startNum += cDivied * (totalCellNum / 4);
            totalCellNum /= 4;
            endNum = startNum + totalCellNum - 1;

            diviedBy /= 2;
            if (diviedBy == 0) break;
            rDivied = rRest / diviedBy;
            rRest %= diviedBy;
            cDivied = cRest / diviedBy;
            cRest %= diviedBy;
        }

        bw.write(Integer.toString(endNum));


        bw.flush();
        bw.close();
    }

}

