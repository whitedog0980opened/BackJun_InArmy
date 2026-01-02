import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
//need to review = 5430 ! deque
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (0 < testCase--) {
            int inputCase = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> doublePreQueue = new TreeMap<>();

            for (int i = 0; i < inputCase; i++) {
                String[] inputStr = br.readLine().split(" ");
                char commend = inputStr[0].charAt(0);
                int value = Integer.parseInt(inputStr[1]);
                boolean smallist = false;

                // add entry
                if (commend == 'I') {
                    doublePreQueue.merge(value, 1, (x1, x2) -> {return x1 + x2;});
                    continue;
                } // delete entry
                else if (value == -1) {
                    smallist = true;
                }
                
                if (doublePreQueue.isEmpty()) continue; // if empty
                int key = smallist ? doublePreQueue.firstKey() : doublePreQueue.lastKey();
                int queueValue = doublePreQueue.get(key);
                if (queueValue > 1) doublePreQueue.put(key, queueValue - 1);
                else doublePreQueue.remove(key);
            }

            if (doublePreQueue.isEmpty()) {
                bw.write("EMPTY\n");
            }
            else {
                String lastNum = Integer.toString(doublePreQueue.lastKey());
                String firstNum = Integer.toString(doublePreQueue.firstKey());
                bw.write(lastNum + " " + firstNum + "\n");
            }
        }
        
        bw.flush();
        bw.close();
    }
}
