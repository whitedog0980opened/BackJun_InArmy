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
            //init
            String[] inputs = br.readLine().split(" ");
            int num = Integer.parseInt(inputs[0]); 
            int targetNum = Integer.parseInt(inputs[1]);;

            //[0] = D, [1] = S, [2] = L, [3] = R

            Queue<String[]> objectNum = new LinkedList<>();
            // int[] startPoint = {0, 0, 0, 0, num};
            //it seems to able to Optimization to change this type to new Class 
            //new Class has field StringBulider and int
            String[] startPoint = {"", Integer.toString(num)};
            objectNum.add(startPoint);

            HashSet<Integer> visited = new HashSet<>();
            visited.add(num);
            int result = 0;

            //BFS
            while (!objectNum.isEmpty()) {
                String[] crrArr = objectNum.poll();
                int crrNum = Integer.parseInt(crrArr[1]);

                //answer check
                if (crrNum == targetNum) {
                    bw.write(crrArr[0]);
                    bw.newLine();
                    break;
                }

                //add to queue
                //D
                int nextNum = crrNum * 2 % 10000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "D", Integer.toString(nextNum)};
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //S
                nextNum = crrNum - 1;
                if (nextNum < 0) nextNum = 9999;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "S", Integer.toString(nextNum)};
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //L
                int n1 = crrNum % 10;
                int n2 = crrNum % 100 / 10;
                int n3 = crrNum % 1000 / 100;
                int n4 = crrNum / 1000;
                int temp = n4;
                n4 = n3; n3 = n2; n2 = n1; n1 = temp;
                nextNum = n1 + n2 * 10 + n3 * 100 + n4 * 1000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "L", Integer.toString(nextNum)};;
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
                //R
                n1 = crrNum % 10;
                n2 = crrNum % 100 / 10;
                n3 = crrNum % 1000 / 100;
                n4 = crrNum / 1000;
                temp = n4;
                n4 = n1; n1 = n2; n2 = n3; n3 = temp; 
                nextNum = n1 + n2 * 10 + n3 * 100 + n4 * 1000;
                if (!visited.contains(nextNum)) {
                    String[] nextArr = {crrArr[0] + "R", Integer.toString(nextNum)};;
                    visited.add(nextNum);
                    objectNum.add(nextArr);
                }
            }
        }
        
        bw.flush();
        bw.close();
    }
}
