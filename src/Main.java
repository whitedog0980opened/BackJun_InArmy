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

        int N = Integer.parseInt(br.readLine());
        long[] depth = new long[N + 3];
        depth[1] = 1;
        depth[2] = 1;
        for (int i = 3; i < N + 1; i++) {
            depth[i] = depth[i - 1] + depth[i - 2];
            depth[i] %= 10007;
        }
        int result = (int) ((depth[N] + depth[N - 1]) % 10007);
    
        bw.write(Integer.toString(result));
        
        bw.flush();
        bw.close();
    }
}
