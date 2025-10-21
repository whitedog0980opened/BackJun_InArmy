import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputNumStr = br.readLine();
        int inputNum = Integer.parseInt(inputNumStr);
        int minTryNum =  inputNum - (inputNumStr.length() * 9);
        if (minTryNum < 0) minTryNum = 0;


        for (int i = minTryNum ; i < inputNum; i++) {
            int tryNum = i;
            String tryNumStr = String.valueOf(tryNum);
            for (String j : tryNumStr.split("")) { //  9 9 9 5 9 . 99959 99964 99973 99982 99987 99996
                tryNum += Integer.parseInt(j);
            }
            if (tryNum == inputNum) {
                bw.write(tryNumStr);
                break;
            }
            if (i == inputNum - 1) {
                bw.write("0");
            }
        }

        bw.flush();
        bw.close();
    }
}
