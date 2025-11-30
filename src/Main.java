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
            Arrays.fill(i, Integer.MIN_VALUE);
        }
        //place first fishbowl
        for (int i = 0; i < fishbowlNum; i++) {
            int fishTemp = Integer.parseInt(fishbowlStrInputs[i]);
            fishbowlMap[0][i] = fishTemp;
        }

        while (!checkGap(minumunGap, fishbowlMap, fishbowlNum)) {
            //add 1 for each minimum num
            int minNum = Integer.MAX_VALUE
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
            fishbowlMap[0][fishbowlNum] = Integer.MIN_VALUE; // set lastbowl is empty
            fishbowlMap[1][0] = tempFlatMap[0];




        }

        

        

        bw.flush();
        bw.close();
    }

    static boolean checkGap(int minGap, int[][] fishbowlMap, int bowlNum) {
        int minFishNum = Integer.MAX_VALUE;
        int maxFishNum = Integer.MIN_VALUE;

        for (int i = 0; i < bowlNum; i++) { //check only 1st floor
            int crrFishbowl = fishbowlMap[0][i];
            if (crrFishbowl == Integer.MIN_VALUE) continue; //check empty
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

    static void rollAndStack(int[][] fishbowlMap, int bowlNum) {
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
        rollingBox[0][1] = fishbowlMap[0][1];

        while (rollable) {
            int[][] newRollingBox = new int[bowlNum][bowlNum];
            

            //check rollable
            int stacked num
        }
    } 
}

