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
        int fiboMax = Integer.parseInt(br.readLine());

        int[] fiboArray = new int[fiboMax];
        int fibo2 = 0;
        fiboArray[0] = 1;
        fiboArray[1] = 1;
        for (int i = 2; i < fiboMax; i++) {
            fiboArray[i] = fiboArray[i - 1] + fiboArray[i - 2];
            fibo2++;
        }

        fibo(fiboMax);

        bw.write(Integer.toString(fibo1 + 1) + " " + Integer.toString(fibo2));

        bw.flush();
        bw.close();
    }
    static int fibo(int crr) {
        if (crr == 1 || crr == 2) {
            return 1;
        } 
        else {
            fibo1++;
            return (fibo(crr - 1) + fibo(crr - 2));
        }
    }

}
