import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());

            long[] array = new long[N + 8];
            array[0] = 1; array[1] = 1; array[2] = 1; array[3] = 2;
            array[4] = 2; array[5] = 3; array[6] = 4; array[7] = 5;
            //4 5 6
            for (int i = 8; i < N; i++) {
                array[i] = array[i - 1] + array[i - 5]; 
            }

            bw.write(Long.toString(array[N - 1]) + "\n");

        }

        bw.flush();
        bw.close();
    }
}
