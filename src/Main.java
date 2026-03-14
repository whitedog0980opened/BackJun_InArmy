import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import org.w3c.dom.Node;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long n = Long.parseLong(br.readLine());

        if (n == 0) {
            bw.write("0");
            bw.flush();
            bw.close();
            System.exit(0);
        }
        long[] matrix = {1, 1, 1, 0};
        long[][] dp = new long[65][4];
        dp[0] = new long[]{1, 1, 1, 0};

        long[] res = {1, 0, 0, 1};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = matrixMult(res, matrix);
            }
            matrix = matrixMult(matrix, matrix);
            n >>= 1;
        }
        
        bw.write(Long.toString(res[2]));
        bw.flush();
        bw.close();
    }
    private static long[] matrixMult(long[] mat1, long[] mat2) {
        long divideAgent = 1000000007;
        long[] result = {
            ((mat1[0] * mat2[0]) % divideAgent + (mat1[1] * mat2[2]) % divideAgent) % divideAgent,
            ((mat1[0] * mat2[1]) % divideAgent + (mat1[1] * mat2[3]) % divideAgent) % divideAgent,
            ((mat1[2] * mat2[0]) % divideAgent + (mat1[3] * mat2[2]) % divideAgent) % divideAgent,
            ((mat1[2] * mat2[1]) % divideAgent + (mat1[3] * mat2[3]) % divideAgent) % divideAgent
        };
        return result;
    }
}