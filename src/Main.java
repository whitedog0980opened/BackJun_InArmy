import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalInputNum = Integer.parseInt(br.readLine());

        int[] arr = new int[totalInputNum];
        for (int i = 0; i < totalInputNum; i++) {
            int inputNum = Integer.parseInt(br.readLine());
            arr[i] =  inputNum;
        }

        Arrays.sort(arr);
        
        boolean isFirst = true;
        for (int i : arr) {
            if (isFirst) isFirst = false;
            else bw.newLine();
            bw.write(Integer.toString(i));
        }

        bw.flush();
        bw.close();
    }
}