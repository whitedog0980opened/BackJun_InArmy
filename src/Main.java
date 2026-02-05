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
    // 이런 전용 클래스를 하나 만들면 contains(new IntArrayWrapper(arr))로 안전하게 확인 가능
    private static class IntArrayWrapper {
        private final int[] data;
        public IntArrayWrapper(int[] data) { this.data = data; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof IntArrayWrapper)) return false;
            return Arrays.equals(this.data, ((IntArrayWrapper) o).data);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(data);
        }
    }
    //디버깅 필요 15650 -> 예제 2. 출력 이상함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = i + 1;
        }
        HashSet<IntArrayWrapper> hs = new HashSet<>();
        
        fLoop : while (true) {
            int crrIndex = m - 1;
            boolean wrongCase = true;
            while (wrongCase) {
                wrongCase = false;
                for (int i = 0; i < m; i++) {
                    if (arr[i] + (m-1 - i) > n) { //이상한거 찾음
                        // wrongCase = true;
                        if (i - 1 < 0) break fLoop;
                        for (int j = (i - 1); j < m; j++) { //정상화
                            arr[j]++;
                            if (j + 1 > m - 1) break;
                            arr[j + 1] = arr[j];
                        }
                        if (arr[i - 1] + (m - 1 - i + 1) > n) wrongCase = true;
                        if (wrongCase && i - 1 == 0) break fLoop;
                    }
                }
            }

            crrIndex = m - 1;
            //중복 체크
            IntArrayWrapper tempWapped = new IntArrayWrapper(Arrays.copyOf(arr, m));
            if (hs.contains(tempWapped)) continue;
            hs.add(tempWapped);
            //print
            bw.write(Integer.toString(arr[0]));
            for (int i = 1; i < m; i++) {
                bw.write(" " + Integer.toString(arr[i]));
            }
            bw.newLine();

            arr[crrIndex]++;
        }
        bw.flush();
        bw.close();;
    }

}