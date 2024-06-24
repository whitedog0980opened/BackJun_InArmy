import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tries = Integer.parseInt(bf.readLine());

        // Precompute the counts of 0s and 1s for all Fibonacci numbers up to 40
        int maxN = 40;
        int[] zeroCount = new int[maxN + 1];
        int[] oneCount = new int[maxN + 1];

        // Initial conditions
        zeroCount[0] = 1; // Fibonacci(0) has one 0
        oneCount[0] = 0;  // Fibonacci(0) has zero 1s
        zeroCount[1] = 0; // Fibonacci(1) has zero 0s
        oneCount[1] = 1;  // Fibonacci(1) has one 1

        // Fill in the counts using the known relationships
        for (int i = 2; i <= maxN; i++) {
            zeroCount[i] = zeroCount[i - 1] + zeroCount[i - 2];
            oneCount[i] = oneCount[i - 1] + oneCount[i - 2];
        }

        for (int i = 0; i < tries; i++) {
            int attempt = Integer.parseInt(bf.readLine());
            bw.write(zeroCount[attempt] + " " + oneCount[attempt] + "\n");
        }

        bw.flush();
        bw.close();
        bf.close();

    }
}
