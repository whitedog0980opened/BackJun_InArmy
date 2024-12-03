import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
           String[] inputNumStr = br.readLine().split(" ");
           int[] inputNum =     {Integer.parseInt(inputNumStr[0]),  // convert to int
                                Integer.parseInt(inputNumStr[1]),
                                Integer.parseInt(inputNumStr[2])};

           Arrays.sort(inputNum);
           if (inputNum[0] == 0 || inputNum[1] == 0 || inputNum[2] == 0) break;
           if ( Math.pow(inputNum[0], 2) + Math.pow(inputNum[1], 2) == Math.pow(inputNum[2], 2)) {
               bw.write("right");
           } else {
               bw.write("wrong");
           }
           bw.newLine();
        }
        bw.close();
    }
}
