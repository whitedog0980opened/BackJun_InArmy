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

    //2103
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        int[][] xSortedNode = new int[nodeNum][2];
        int[][] ySortedNode = new int[nodeNum][2];
        //get input and init
        StringTokenizer st = null;
        for (int i = 0; i < nodeNum; i++) {
            st = new StringTokenizer(br.readLine());
            int[] node = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            xSortedNode[i] = node;
            ySortedNode[i] = node;
        }
        //sort array in order (if same, other axis sort)
        Arrays.sort(xSortedNode, (a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a1[1] - a2[1];
            }
            return a1[0] - a2[0];
        });
        Arrays.sort(ySortedNode, (a1, a2) ->{
            if (a1[1] == a2[1]) {
                return a1[0] - a2[0];
            }
            return a1[1] - a2[1];
        });
        
        int result = 0;
        for (int i = 0; i < (int) nodeNum/2; i++) {
            int crrIndex = i*2; // if i % 2 == 0, index i and i+1 is must same num
            int edgeY = Math.abs(xSortedNode[crrIndex][1] - xSortedNode[crrIndex + 1][1]);
            int edgeX = Math.abs(ySortedNode[crrIndex][0] - ySortedNode[crrIndex + 1][0]);
            result += edgeX + edgeY;
        }
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();;
    }
}