import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //take first line = userNum, vectorNum
        String[] firstInputLine = br.readLine().split(" ");
        int userNum = Integer.parseInt(firstInputLine[0]);
        int vectorNum = Integer.parseInt(firstInputLine[1]);

        //take vectors
        LinkedList<Integer>[] vectors = new LinkedList[userNum + 1]; //array start for 1
        for (int i = 1; i < userNum + 1; i++) { //init list
            vectors[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < vectorNum; i++) { //fill vectors
            String[] tempInput = br.readLine().split(" ");
            int from = Integer.parseInt(tempInput[0]);
            int to =  Integer.parseInt(tempInput[1]);

            vectors[from].add(to);
            vectors[to].add(from);
        }
        int[] kelvinNums = new int[userNum + 1];

        for (int i = 1; i < userNum + 1; i++) { // main
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int[] deepCount = new int[userNum + 1]; //index = userNum, record deep
            Arrays.fill(deepCount, Integer.MIN_VALUE);
            deepCount[i] = 0;
            //deep copy
            LinkedList<Integer>[] copyedVectors = new LinkedList[userNum + 1];
            for (int j = 1; j < userNum + 1; j++) {
                copyedVectors[j] = new LinkedList<>(vectors[j]);
            }

            while (!queue.isEmpty()) {
                int crrNode = queue.remove();
                int deepCounter = deepCount[crrNode] + 1;
                for (int next : copyedVectors[crrNode]) {
                    if (deepCount[next] == Integer.MIN_VALUE) { //isnt Visited?
                        deepCount[next] = deepCounter;
                        queue.add(next);
                    } else {
                        continue;
                    }
                }
            }
            int result = 1; //++1 because start point is -1
            for (int j : deepCount) {
                if (j < 0) continue;
                result += j;
            }

            kelvinNums[i] = result; 
        }

        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = userNum; i > 0; i--) {
            if (kelvinNums[i] <= min) {
                min = kelvinNums[i];
                result = i;
            }
        }
        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
    }

}

