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
        //초기 고찰
        // 비트마스크를 이용. 규칙을 정하고 모든 경우의 수를 계산한다 (가능하면 비트계산)
        // 규칙에 부합하는 비트마스크를 dp에 저장한다. 
        // 그다음, 다음줄은 기본규칙(부서진 책상) + 이전줄에 의해 컨닝방지 규칙 + 규칙을 포함한 규칙 (X))
        // + 오류 케이스. 첫줄이 전부 X일 경우 무조건 0 출력됨
        // 함수화 필요. 예외 사항에 대응하기 때문에 특정 기능 코드 재사용 필요.
        
        //중반 고찰
        //첫번째 줄은 단순히 2^m의 가짓수를 전부 시험하고 이 중 조건에 맞는 경우를 고른다
        //DP자료구조가 저장하는 내용은 2진수int(map) 과 지금까지 앉은 학생의 갯수이다.
        //2층부터의 계산은 전 층에서의 모든 저장 내용을 반복한다.
        // 전층의 학생 위치에 따라 설치 불가능한 위치를 갱신한다. 이는 2진법으로 비트마스크 사용
        // 가능한 수를 전부 다음층의 자료구조에 저장한다.
        //  중첩이 많이 일어난다. 이는 학생수가 더 많은것을 저장한다.
        //자료구조는 int[]로 결정. 최대 길이가 1024이므로 그닥 길진않다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int testCase = Integer.parseInt(st.nextToken());
        
        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            boolean[][] map = new boolean[n][m]; //un seatAble
            //take input
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                String[] input = st.nextToken().split("");
                for (int k = 0; k < m; k++) {
                    map[j][k] = input[k].equals(".");
                }
            }

            int totalWays = (int) Math.pow(2, m);
            int[][] bitDp = new int[n][totalWays]; // [현재 층][학생의 배치2진법의 10진수값] = 앉은 학생 수
            for (int i = 0; i < n; i++) {
                Arrays.fill(bitDp[i], -1);
            }
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
            //2~n floors
            for (int i = 1; i < n; i++) { //i == floor
                //pre able cases
                for (int j = 0; j < totalWays; j++) {
                    int preDecimal = j; 
                    int preSeatedNum = bitDp[i - 1][preDecimal]; //이미 앉은 학생
                    if (preSeatedNum == -1) continue; //no pre data 

                    int bitMask = n1014MakeBitMask(totalWays, m, map, i, preDecimal);

                    //crr floor
                    n1014CultCrrFloor(totalWays, bitMask, preSeatedNum, bitDp, i);
                }
            }

            //get Max value
            int result = 0;
            for (int i = 0; i < totalWays; i++) {
                result = Math.max(bitDp[n - 1][i], result);
            }
            bw.write(Integer.toString(result) + "\n");
        }

        bw.flush();
        bw.close();;
    }
    static int n1014MakeBitMask(int totalWays, int m, boolean[][] map, int crrFloor, int preDecimal) {
        int bitMask = totalWays - 1; //2진법 : 전부 1로 채움
        for (int k = 0; k < m; k++) { //초기 입력값 적용
            if (!map[crrFloor][k]) bitMask -= (int) Math.pow(2, k);
        }
        //cult preFloor
        //if you don't want, preDecimal is 0
        int toClear = (preDecimal << 1) | (preDecimal >> 1);
        bitMask &= ~toClear;
        return bitMask;
    }
    //return has exception
    static void n1014CultCrrFloor(int totalWays, int bitMask, int preSeatedNum, int[][] bitDp, int crrFloor) {;
        for (int k = 0; k < totalWays; k++) {
            int crrDecimal = k;
            if ((crrDecimal & (crrDecimal << 1)) != 0) continue;
            if ((crrDecimal & bitMask) != crrDecimal) continue;

            int crrSeatedNum = preSeatedNum + Integer.bitCount(crrDecimal);
            if (bitDp[crrFloor][crrDecimal] < crrSeatedNum) {
                bitDp[crrFloor][crrDecimal] = crrSeatedNum;
            }
        }
    }
}