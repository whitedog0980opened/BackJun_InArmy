import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);

        if (K < N) {
            bw.write(Integer.toString(N - K));
            bw.flush();
            bw.close();
            return;
        }

        Set<Integer> visited = new HashSet<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        visited.add(N);

        while (!queue.isEmpty()) {
            int crr[] = queue.poll();
            int crrN = crr[0];
            int crrDepth = crr[1];

            if (crrN == K) {
                bw.write(Integer.toString(crrDepth));
                bw.flush();
                bw.close();
                return;
            }

            int[] next = {crrN + 1, crrN - 1, crrN * 2};
            for (int nextN : next) {
                if (nextN >= 0 && nextN <= 100000 && !visited.contains(nextN)){
                    visited.add(nextN);
                    queue.add(new int[]{nextN, crrDepth + 1});
                }
            }
        }
        bw.flush();
        bw.close();
    }
}

