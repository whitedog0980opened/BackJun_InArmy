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

            //첫번째 줄은 단순히 2^m의 가짓수를 전부 시험하고 이 중 조건에 맞는 경우를 고른다
            //자료구조를 고민중
            //자료구조가 저장하는 내용은 2진수int(map) 과 지금까지 앉은 학생의 갯수이다.
            //2층부터의 계산은 전 층에서의 모든 저장 내용을 반복한다.
            // 전층의 학생 위치에 따라 설치 불가능한 위치를 갱신한다. 이는 2진법으로 비트마스크 사용
            // 가능한 수를 전부 다음층의 자료구조에 저장한다.
            //  중첩이 많이 일어난다. 이는 학생수가 더 많은것을 저장한다.
            //자료구조는 int[]로 결정. 최대 길이가 1024이므로 그닥 길진않다.

            int totalWays = (int) Math.pow(2, m);
            int[][] bitDp = new int[n][totalWays]; // [현재 층][학생의 배치2진법의 10진수값] = 앉은 학생 수
            //1floor
            for (int j = 0; j < totalWays; j++) {
                int crrDecimal = j;
                boolean isAble = true; // 조건에 충족하는가
                // 연속하는 1이 존재하는가
                if ((crrDecimal & (crrDecimal << 1)) != 0) {
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    //해당 k에서 맵에 자리가 앖는데, crr에는 1인경우
                    if (!map[0][k] && ((crrDecimal & (1 << k)) != 0)) {
                        isAble = false;
                        break;
                    }
                }
                
                if (isAble) {
                    //중복 X
                    bitDp[0][crrDecimal] = Integer.bitCount(crrDecimal);
                }
            }
        }


        bw.flush();
        bw.close();;
    }
}