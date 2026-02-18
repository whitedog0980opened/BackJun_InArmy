import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Pt {
    static void n23291() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInputs = br.readLine().split(" ");
        int fishbowlNum = Integer.parseInt(firstInputs[0]);
        int minumunGap = Integer.parseInt(firstInputs[1]);
        
        String[] fishbowlStrInputs = br.readLine().split(" ");
        int[][] fishbowlMap = new int[fishbowlNum][fishbowlNum]; //[y][x]좌표

        //place first fishbowl
        for (int i = 0; i < fishbowlNum; i++) {
            int fishTemp = Integer.parseInt(fishbowlStrInputs[i]);
            fishbowlMap[0][i] = fishTemp;
        }

        int tryCount = 0;
        while (!checkGap_23291(minumunGap, fishbowlMap, fishbowlNum)) {
            //add 1 for each minimum num
            int minNum = Integer.MAX_VALUE;
            for (int i = 0; i < fishbowlNum; i++) { //find minimum num
                int crrNum = fishbowlMap[0][i];
                if (crrNum < minNum) minNum = crrNum;
            }
            for (int i = 0; i < fishbowlNum; i++) { //add 1 on minimum num
                if (fishbowlMap[0][i] == minNum) fishbowlMap[0][i]++;
            }

            //stack 1st bowl on 2nd bowl
            int[] tempFlatMap = Arrays.copyOf(fishbowlMap[0], fishbowlNum);
            for (int i = 0; i < fishbowlNum - 1; i++) {
                fishbowlMap[0][i] = fishbowlMap[0][i + 1];
            }
            fishbowlMap[0][fishbowlNum - 1] = 0; // set lastbowl is empty
            fishbowlMap[1][0] = tempFlatMap[0];

            fishbowlMap = rollAndStack_23291(fishbowlMap, fishbowlNum);
            moveFishes_23291(fishbowlMap, fishbowlNum);
            fishbowlMap = toFlatForm_23291(fishbowlMap, fishbowlNum);
            foldForm_23291(fishbowlMap, fishbowlNum);
            moveFishes_23291(fishbowlMap, fishbowlNum);
            fishbowlMap = toFlatForm_23291(fishbowlMap, fishbowlNum);
            tryCount++;
        }

        bw.write(Integer.toString(tryCount));
        

        

        bw.flush();
        bw.close();
    }
    static boolean checkGap_23291(int minGap, int[][] fishbowlMap, int bowlNum) {
        int minFishNum = Integer.MAX_VALUE;
        int maxFishNum = Integer.MIN_VALUE;

        for (int i = 0; i < bowlNum; i++) { //check only 1st floor
            int crrFishbowl = fishbowlMap[0][i];
            if (crrFishbowl == 0) continue; //check empty
            if (crrFishbowl > maxFishNum) {
                maxFishNum = crrFishbowl;
            }
            if (crrFishbowl < minFishNum) {
                minFishNum = crrFishbowl;
            }
        }
        boolean result = (maxFishNum - minFishNum) <= minGap;
        return result;
    }

    static int[][] rollAndStack_23291(int[][] fishbowlMap, int bowlNum) {
        boolean rollable = true;
        //floor
        LinkedList<Integer> nonStackedFloor = new LinkedList<>();
        for (int i : fishbowlMap[0]) {
            nonStackedFloor.add(i);
        }
        nonStackedFloor.removeLast();  // last is 0
        nonStackedFloor.removeFirst(); // 1st is rolled
        //roll box
        int[][] rollingBox = new int[bowlNum][bowlNum];
        rollingBox[0][0] = fishbowlMap[0][0];
        rollingBox[1][0] = fishbowlMap[1][0];

        int boxSizeX = 1; // important
        int boxSizeY = 2;

        while (rollable) {
            int[][] newRollingBox = new int[bowlNum][bowlNum];

            for (int i = 0; i < boxSizeX; i++) { // roll and float 1
                for (int j = 0; j < boxSizeY; j++) {
                    newRollingBox[boxSizeX - 1 - i + 1][j] = rollingBox[j][i];
                }
            }
            if (boxSizeX < boxSizeY) boxSizeX++;
            else boxSizeY++;

            for (int i = 0; i < boxSizeX; i++) {
                newRollingBox[0][i] = nonStackedFloor.removeFirst();
            }

            rollingBox = newRollingBox;

            if (boxSizeY > nonStackedFloor.size()) {
                rollable = false;
            }
        }

        int leftFloorSize = nonStackedFloor.size();
        for (int i = 0; i < leftFloorSize; i++) {
            rollingBox[0][boxSizeX + i] = nonStackedFloor.removeFirst();
        }
        return rollingBox;
    } 

    static void moveFishes_23291(int[][] fishbowlMap, int bowlNum) {
        int[][] addFishNumMap = new int[bowlNum][bowlNum];
        int[] dx = {1, 0};
        int[] dy = {0, 1};

        for (int i = 0; i < bowlNum; i++) {
            for (int j = 0; j < bowlNum; j++) {
                if (fishbowlMap[j][i] == 0) continue;

                for (int k = 0; k < 2; k++) {
                    int di = i + dx[k];
                    int dj = j + dy[k];
                    if (di >= 0 && dj >= 0 && di < bowlNum && dj < bowlNum && fishbowlMap[dj][di] != 0) {
                        int diff = Math.abs(fishbowlMap[j][i] - fishbowlMap[dj][di]) / 5;
                        if (diff > 0) {
                            if (fishbowlMap[j][i] > fishbowlMap[dj][di]) {
                                addFishNumMap[j][i] -= diff;
                                addFishNumMap[dj][di] += diff;
                            } else {
                                addFishNumMap[j][i] += diff;
                                addFishNumMap[dj][di] -= diff;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < bowlNum; i++) {
            for (int j = 0; j < bowlNum; j++) {
                if (fishbowlMap[j][i] != 0) fishbowlMap[j][i] += addFishNumMap[j][i];
            }
        }
    }

    static int[][] toFlatForm_23291(int[][] fishbowlMap, int bowlNum) {
        LinkedList<Integer> flatMap = new LinkedList<>();
        int dy = 0;
        for (int i = 0; i < bowlNum; i++) {
            if (fishbowlMap[0][i] == 0) break;
            flatMap.add(fishbowlMap[0][i]);
            int y = 1;
            while (y < bowlNum && fishbowlMap[y][i] != 0) {
                flatMap.add(fishbowlMap[y][i]);
                y++;
            }
            dy = 1;
        }

        int[][] newFishBowlMap = new int[bowlNum][bowlNum];
        for (int i = 0; i < flatMap.size(); i++) { 
            newFishBowlMap[0][i] = flatMap.get(i);
        }

        return newFishBowlMap;
    }

    static void foldForm_23291(int[][] fishbowlMap, int bowlNum) {
        int halfBowlNum = bowlNum/2;
        int quatBowlNum = bowlNum/4;
        for (int i = 0; i < halfBowlNum; i++) {
            fishbowlMap[1][i] = fishbowlMap[0][halfBowlNum - i - 1];
            fishbowlMap[0][halfBowlNum - i - 1] = fishbowlMap[0][bowlNum - i - 1];
            fishbowlMap[0][bowlNum - i - 1] = 0;
        }
        for (int i = 0; i < quatBowlNum; i++) {
            fishbowlMap[2][i] = fishbowlMap[1][quatBowlNum - i - 1];
            fishbowlMap[1][quatBowlNum - i - 1] = fishbowlMap[1][halfBowlNum - i - 1];
            fishbowlMap[1][halfBowlNum - i - 1] = 0;
            fishbowlMap[3][i] = fishbowlMap[0][quatBowlNum - i - 1];
            fishbowlMap[0][quatBowlNum - i - 1] = fishbowlMap[0][halfBowlNum - i - 1];
            fishbowlMap[0][halfBowlNum - i - 1] = 0;
        }
    }
    static void n1014() throws IOException {
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