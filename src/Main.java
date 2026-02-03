import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //16236
    //문제점 : 물고기가 도중에 성장하면, 중간부터 최단경로가 바뀐다. 
    //기존 미리 뽑아두는경우, 바뀐내용이 반영되지 않는다
    //+ 더 큰 물고기는 지나갈 수 없다. 이는 맨해튼 거리가 아닌 BFS사용을 유도한다.
    
    public static void main(String[] args) throws IOException {
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