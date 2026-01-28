import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list :
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {

    //1014
    public static void main(String[] args) throws IOException {
        // 비트마스크를 이용. 규칙을 정하고 모든 경우의 수를 계산한다 (가능하면 비트계산)
        // 규칙에 부합하는 비트마스크를 dp에 저장한다. 
        // 그다음, 다음줄은 기본규칙(부서진 책상) + 이전줄에 의해 컨닝방지 규칙 + 규칙을 포함한 규칙 (X))
        // 그냥 다음줄도 동일하게 진행한다.
        // 몰?루
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < testCase; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[n][m];

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; i < m; k++) {
                    String input = st.nextToken();
                    map[j][k] = input.equals(".");
                }
            }

            //cult first floor
            int maxAbleTry = (int) Math.pow(2, m);
            for (int j = 0; j < maxAbleTry; j++) {
                //여기에서 j값을 2진수로써 사용
                //아마, boolean 배열을 사용할듯
                //연속된 값하고, X쳐진값 (이건 map[0][A]에서 찾을 수 있을듯.) 을 걸러야함
                //j의 십진수값이 어느 인덱스가 true인지를 알아야 할듯. 이건 함수로 만들자
                // -> 10진수값 인풋, boolean배열 리턴

            }

        }


        bw.flush();
        bw.close();;
    }
}