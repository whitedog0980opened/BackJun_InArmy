package etc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class FailedCase {
    static void n5430Wrong() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        firstLoop : for (int i = 0; i < testCase; i++) {
            boolean isFliped = false;
            int[] deletingIndex = {0, 0};
            String commends = br.readLine().replace("RR", "");
            int arrayLength = Integer.parseInt(br.readLine());
            String[] arrayString = br.readLine().replaceAll("[\\[\\]]", "").split(",");

            int[] array = new int[arrayLength];
            for (int j = 0; j < arrayLength; j++) { //get array
                array[j] = Integer.parseInt(arrayString[j]);
            }
            for (int j = 0; j < commends.length(); j++) { //handle commend
                char crrCommend = commends.charAt(j);
                if (crrCommend == 'D') {
                    int aliveNumCounter = 0;
                    for (int k : array) {
                        if (k != -1) aliveNumCounter++;
                    }

                    if (aliveNumCounter >= 1) {
                        if (!isFliped) {
                            array[deletingIndex[0]++] = -1;
                        } else {
                            array[array.length - deletingIndex[1] - 1] = -1;
                            deletingIndex[1]++;
                        }

                        // int[] newArray = new int[array.length - 1];
                        // for (int k = 1; k < array.length; k++) {
                        //     newArray[k - 1] = array[k];
                        // }
                        // array = newArray;
                    }
                    else {
                        bw.write("error\n");
                        continue firstLoop;
                    }
                }
                else {
                    int len = array.length;
                    int[] newArray = new int[len];
    
                    for (int k = 0; k < len; k++) {
                        newArray[k] = array[len - 1 - k];
                    }
    
                    System.arraycopy(newArray, 0, array, 0, len);
                    isFliped = !isFliped;
                }
            }
            arrayLength = array.length;
            int[] temp = new int[array.length];
            int k = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] != 0) {
                    temp[k++] = array[j];
                }
            } 

            if (arrayLength == 1) {
                bw.write("[" + temp[0] + "]\n");
                continue;
            }
            for (int j = 0; j < k; j++) {
                if (j == k - 1) {
                    bw.write(temp[j] + "]\n");
                }
                else if (j == 0) {
                    bw.write("[" + temp[j] + ",");
                }
                else {
                    bw.write(temp[j] + ",");
                }
            }
        }
        bw.flush();
        bw.close();
    }
    //uses in 9019
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
    static void n9019FailedRef() throws IOException {
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
    static void n1167_failedLeckOfTime() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        int maxLenght = 0;

        //from = first index, to = sec index
        int[][] map = new int[nodeNum + 1][nodeNum + 1]; 
        // init map
        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crrNode = Integer.parseInt(inputs[0]);
            int crr = 0;
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                map[crrNode][nextNode] = length;
                //check maxLenght
                maxLenght = Math.max(maxLenght, length);
            }
        }

        // i = crrNode
        boolean isNewDepth = true;
        while (isNewDepth) {
            isNewDepth = false;
            int[][] newConnection = new int[nodeNum + 1][nodeNum + 1];
            for (int i = 1; i < nodeNum + 1; i++) {
            //this loop for find fromNum
                
                for (int j = 1; j < nodeNum + 1; j++) {
                    int length = map[i][j];
                    if (length == 0) continue; //empty 

                    //this loop for find toNum and add new connection
                    for (int k = 1; k < nodeNum + 1; k++) {
                        int connectedNum = map[j][k] + map[i][j];
                        if (map[j][k] == 0 || k == i) continue; //empty or fromNum and toNum endPoint is same 
                        if ((newConnection[i][k] == 0 || (newConnection[i][k] > connectedNum)) && (map[i][k] > connectedNum || map[i][k] == 0)) {
                            newConnection[i][k] = connectedNum;
                        }
                    }
                }
            }   
            //apply newConnection
            for (int i = 1; i <nodeNum + 1; i++ ) {
                for (int j = 1; j < nodeNum + 1; j++) {
                    if (newConnection[i][j] != 0) {
                        isNewDepth = true;
                        map[i][j] = newConnection[i][j];
                        maxLenght = Math.max(maxLenght, newConnection[i][j]);
                    }
                }
            }
        }
        bw.write(Integer.toString(maxLenght));
        bw.flush();
        bw.close();
    }
    static void n16236_wrongAlgorithm() throws IOException {
        //문제점 : 물고기가 도중에 성장하면, 중간부터 최단경로가 바뀐다. 
        //기존 미리 뽑아두는경우, 바뀐내용이 반영되지 않는다
        //+ 더 큰 물고기는 지나갈 수 없다. 이는 맨해튼 거리가 아닌 BFS사용을 유도한다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int boxSize = Integer.parseInt(br.readLine());
        int[][] box = new int[boxSize][boxSize];
        int[] babySharkInfo = {2, 0, 0, 2}; // size, x, y, growCounter
        for (int i = 0; i < boxSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < boxSize; j++) {
                int crr = Integer.parseInt(st.nextToken());
                if (crr == 9) {
                    babySharkInfo[1] = i;
                    babySharkInfo[2] = j;
                    crr = 0;
                }
                box[i][j] = crr;
            }
        }

        int totalTime = 0;
        while (isBoxClear16236(boxSize, box)) {
            ArrayList<int[]> eatables = pollEatable16236(boxSize, box, babySharkInfo);
            if (eatables == null) { //mom calling!
                break;
            }
            totalTime += sharkHuntTIme16236(boxSize, box, babySharkInfo, eatables);
        }
        
        bw.write(Integer.toString(totalTime));
        bw.flush();
        bw.close();;
    }
    private static ArrayList<int[]> pollEatable16236(int boxSize, int[][] box, int[] babySharkInfo) {
        ArrayList<int[]> eatableFishInfo = new ArrayList<>();
        int babySharkSize = babySharkInfo[0];
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (box[i][j] > 0 && box[i][j] < babySharkSize) {
                    eatableFishInfo.add(new int[]{i, j});
                    box[i][j] = 0;
                }
            }
        }
        if (eatableFishInfo.isEmpty()) { //Mom Calling
            return null;
        }
        return eatableFishInfo;
    }
    //poll close fish
    private static int[] findCloseFish16236(int[] babySharkInfo, ArrayList<int[]> eatableFishInfo) {
        int[] closeFish = {Integer.MAX_VALUE, 0, 0};
        int closeFishIndex = -1;
        int eatableFishNum = eatableFishInfo.size();
        for (int i = 0; i < eatableFishNum; i++) {
            int[] crrFish = eatableFishInfo.get(i);
            int time = Math.abs(babySharkInfo[1] - crrFish[0]) + Math.abs(babySharkInfo[2] - crrFish[1]);
            boolean okay = false;
            //logic -> prioty time->x small->y small 
            if (closeFish[0] > time) {
                okay = true;
            }
            else if (closeFish[0] == time)  {
                if (closeFish[1] > crrFish[0]) okay = true;
                else if (closeFish[1] == crrFish[0]) {
                    if (closeFish[2] > crrFish[1]) okay = true;
                }
            }

            if (okay) {
                closeFish[0] = time;
                closeFish[1] = crrFish[0];
                closeFish[2] = crrFish[1];
                closeFishIndex = i;
            }
        }
        eatableFishInfo.remove(closeFishIndex);
        return closeFish;
    }
    private static int sharkHuntTIme16236(int boxSize, int[][] box, int[] babySharkInfo, ArrayList<int[]> eatableFishInfo) {
        int totalTime = 0; 
        while (!eatableFishInfo.isEmpty()) {
            int[] closeFish = findCloseFish16236(babySharkInfo, eatableFishInfo);
            totalTime += closeFish[0];
            //eat enough fishes to grow
            if (--babySharkInfo[3] == 0) {
                babySharkInfo[0]++;
                babySharkInfo[3] = babySharkInfo[0];
            }
            babySharkInfo[1] = closeFish[1];
            babySharkInfo[2] = closeFish[2];
        }
        return totalTime;
    }
    private static boolean isBoxClear16236(int boxSize, int[][] box) {
        for (int i = 0; i < boxSize; i++) {
            for (int j = 0; j < boxSize; j++) {
                if (box[i][j] != 0) return true;
            }
        }
        return false;
    }
}
