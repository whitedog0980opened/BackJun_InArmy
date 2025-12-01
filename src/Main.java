import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInputs = br.readLine().split(" ");
        int fishbowlNum = Integer.parseInt(firstInputs[0]);
        int minumunGap = Integer.parseInt(firstInputs[1]);
        
        String[] fishbowlStrInputs = br.readLine().split(" ");
        int[][] fishbowlMap = new int[fishbowlNum][fishbowlNum]; //[y][x]좌표
        //Empty place is MIN_VALUE
        for (int[] i : fishbowlMap) {
            Arrays.fill(i, 0);
        }
        //place first fishbowl
        for (int i = 0; i < fishbowlNum; i++) {
            int fishTemp = Integer.parseInt(fishbowlStrInputs[i]);
            fishbowlMap[0][i] = fishTemp;
        }

        int tryCount = 0;
        while (!checkGap(minumunGap, fishbowlMap, fishbowlNum)) {
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

            fishbowlMap = rollAndStack(fishbowlMap, fishbowlNum);
            moveFishes(fishbowlMap, fishbowlNum);
            fishbowlMap = toFlatForm(fishbowlMap, fishbowlNum);
            foldForm(fishbowlMap, fishbowlNum);
            moveFishes(fishbowlMap, fishbowlNum);
            foldForm(fishbowlMap, fishbowlNum);
            tryCount++;
        }

        bw.write(Integer.toString(tryCount));
        

        

        bw.flush();
        bw.close();
    }

    static boolean checkGap(int minGap, int[][] fishbowlMap, int bowlNum) {
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

    static int[][] rollAndStack(int[][] fishbowlMap, int bowlNum) {
        boolean rollable = true;
        //floor
        LinkedList<Integer> nonStackedFloor = new LinkedList<>();
        for (int i : fishbowlMap[0]) {
            nonStackedFloor.add(i);
        }
        nonStackedFloor.remove(); // first one is rolled
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

        for (int i = 0; i < nonStackedFloor.size(); i++) {
            rollingBox[0][boxSizeX + i] = nonStackedFloor.removeFirst();
        }
        return rollingBox;
    } 

    static void moveFishes(int[][] fishbowlMap, int bowlNum) {
        int[][] addFishNumMap = new int[bowlNum][bowlNum];
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        for (int i = 0; i < bowlNum; i++) {
            for (int j = 0; j < bowlNum; j++) {
                if (fishbowlMap[j][i] == 0) continue;

                for (int k = 0; k < 4; k++) {
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

    static int[][] toFlatForm(int[][] fishbowlMap, int bowlNum) {
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

    static void foldForm(int[][] fishbowlMap, int bowlNum) {
        int halfBowlNum = bowlNum/2;
        int quatBowlNum = bowlNum/4;
        for (int i = 0; i < halfBowlNum; i++) {
            fishbowlMap[1][i] = fishbowlMap[0][halfBowlNum - i];
            fishbowlMap[0][halfBowlNum - i] = fishbowlMap[0][bowlNum - i - 1];
            fishbowlMap[0][bowlNum - i - 1] = 0;
        }
        for (int i = 0; i < quatBowlNum; i++) {
            fishbowlMap[2][i] = fishbowlMap[1][quatBowlNum - i];
            fishbowlMap[1][quatBowlNum - i] = fishbowlMap[1][halfBowlNum - i];
            fishbowlMap[1][halfBowlNum - i] = 0;
            fishbowlMap[3][i] = fishbowlMap[0][quatBowlNum - i];
            fishbowlMap[0][quatBowlNum - i] = fishbowlMap[0][halfBowlNum - i];
            fishbowlMap[0][halfBowlNum - i] = 0;
        }
    }
}

