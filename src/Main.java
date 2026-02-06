import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] subin = {n, 0}; //locate, moved num

        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<int[]> queue = new LinkedList<>();
        visited[n] = 0;
        queue.add(subin);
        int resultCase = 0;

        boolean pin = false;
        int pinnedShortestMove = 0;

        while (!queue.isEmpty()) {
            int[] crr = queue.poll();

            if (crr[0] == m) {
                if (!pin) {
                    pinnedShortestMove = crr[1];
                    pin = true;
                }
                if (pinnedShortestMove == crr[1]) {
                    resultCase++;
                }
            }

            int[] nextp1 = {crr[0] + 1, crr[1] + 1};
            if ((nextp1[0] >= 0 && nextp1[0] < 100001 && visited[nextp1[0]] >= nextp1[1])) {
                visited[nextp1[0]] = nextp1[1]; 
                queue.add(nextp1);
            }
            int[] nextm1 = {crr[0] - 1, crr[1] + 1};
            if ((nextm1[0] >= 0 && nextm1[0] < 100001 && visited[nextm1[0]] >= nextm1[1])) {
                visited[nextm1[0]] = nextm1[1];
                queue.add(nextm1);
            }
            int[] nextTp = {crr[0] * 2, crr[1] + 1};
            if ((nextTp[0] >= 0 && nextTp[0] < 100001 && visited[nextTp[0]] >= nextTp[1])) {
                visited[nextTp[0]] = nextTp[1];
                queue.add(nextTp);
            }
        }
        int result = visited[m];


        bw.write(Integer.toString(result) + "\n");
        bw.write(Integer.toString(resultCase));

        bw.flush();
        bw.close();;
    }

}