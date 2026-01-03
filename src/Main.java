import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
//need to review = 5430 ! deque
public class Main {
    static class Num {
        //field
        int n1; int n2; int n3; int n4;
        int depth;
        String commendList;
        //constructor
        Num(int n1, int n2, int n3, int n4, int depth, String commendList)  {
            this.n1 = n1;
            this.n2 = n2;
            this.n3 = n3;
            this.n4 = n4;
            this.commendList = commendList;
        }

        //method
        public void right() {
            commendList += "R";
            int temp = n4;
            n4 = n1; n1 = n2; n2 = n3; n3 = temp; 
        }
        public void left() {
            commendList += "L";
            int temp = n4;
            n4 = n3; n3 = n2; n2 = n1; n1 = temp;
        }
        //double value
        public void mult2() {
            commendList += "D";
            int temp = 0;
            temp = n1 * 2;
            n1 = temp % 10;
            temp = n2 * 2 + temp / 10;
            n2 = temp % 10;
            temp = n3 * 2 + temp / 10;
            n3 = temp % 10;
            n4 = (n4 * 2) % 10 + temp / 10;
        }
        // just -1, if value is 0000 -> 9999
        public void minus() {
            commendList += "S";
            if (n1 == 0) {
                n1 = 9;
                if (n2 == 0) {
                    n2 = 9;
                    if (n3 == 0) {
                        n3 = 9;
                        if (n4 == 0) {
                            n4 = 9;
                            return;
                        }
                        n4 -= 1;
                        return;
                    }
                    n3 -= 1;
                    return;
                }
                n2 -= 1;
                return;
            }
            n1 -= 1;
        }
        public boolean compare(int t1, int t2, int t3, int t4) {
            if (n1 == t1 && n2 == t2 && n3 == t3 && n4 == t4) return true;
            return false;
        }
        
        @Override
        public int hashCode() {
            return n1 + n2 * 10 + n3 * 100 + n4 * 1000;
        }
        @Override
        public boolean equals(Object obj) {
            Num num = (Num) obj;
            boolean result = this.n1 == num.n1 && this.n2 == num.n2 && this.n3 == num.n3 && this.n4 == num.n4 ? true : false;
            return result;
        } 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());

        while (0 < testCase--) {
            //init
            String[] inputs = br.readLine().split(" ");
            int num = Integer.parseInt(inputs[0]); 
            int targetNum = Integer.parseInt(inputs[1]);
            
            int n1 = num % 10;
            int n2 = num % 100 / 10;
            int n3 = num % 1000 / 100;
            int n4 = num / 1000;

            int t1 = targetNum % 10;
            int t2 = targetNum % 100 /10;
            int t3 = targetNum % 1000 / 100;
            int t4 = targetNum / 1000;

            Queue<Num> objectNum = new LinkedList<>();
            Num startObject = new Num(n1, n2, n3, n4, 0, "");
            objectNum.add(startObject);

            HashMap<Num, Integer> visited = new HashMap<>();
            int result = 0;

            //BFS
            while (!objectNum.isEmpty()) {
                Num crrOj = objectNum.poll();

                //answer check
                if (crrOj.compare(t1, t2, t3, t4)) {
                    result = crrOj.depth;
                    bw.write(crrOj.commendList);
                    bw.newLine();
                    break;
                }

                //init temps
                Num[] tempNums = new Num[4];
                for(int i = 0; i < 4; i++) {
                    tempNums[i] = new Num(crrOj.n1, crrOj.n2, crrOj.n3, crrOj.n4, crrOj.depth + 1, crrOj.commendList); 
                }
                tempNums[0].minus();
                tempNums[1].mult2();
                tempNums[2].left();
                tempNums[3].right();

                //add to queue
                for (int i = 0; i < 4; i++) {
                    Num crrTempNums = tempNums[i];
                    //check isVisited
                    if (visited.containsKey(crrTempNums)) {
                        continue;
                    }
                    visited.put(crrTempNums, crrTempNums.depth);

                    objectNum.add(crrTempNums);
                }
            }
        }
        
        bw.flush();
        bw.close();
    }
}
