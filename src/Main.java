import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inpurNum = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int mod = 1234567891;
        int sum = 0;

        for (int i = 0; i < inpurNum; i++) {
            char crrCH = str.charAt(i);
            int hashNum = (int)crrCH - 96;
            long temp = 1;
            for (int j = 0; j < i; j++) {
                temp *= 31;
            }
            temp %= mod;
            sum += hashNum * temp;
        }

        bw.write(Integer.toString(sum));
        

        bw.flush();
        bw.close();
    }

}

