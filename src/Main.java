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
    //13399
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int nodeNum = Integer.parseInt(br.readLine());
        int wayNum = Integer.parseInt(br.readLine());
        // take inputs
        int[][] nodemap =  new int[nodeNum + 1][nodeNum + 1];
        for (int i = 1; i < nodeNum + 1; i++) {
            Arrays.fill(nodemap[i], 99999999);
            nodemap[i][i] = 0;
        }
        //init map
        for (int i = 0; i < wayNum; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (nodemap[from][to] > cost) nodemap[from][to] = cost;
        }
        //floid
        for (int i = 1; i < nodeNum + 1; i++) { //middle node
            for (int j = 1; j < nodeNum + 1; j++) { //from node
                for (int k = 1; k < nodeNum + 1; k++) {//to node
                    int newWayCost = nodemap[j][i] + nodemap[i][k];
                    if (nodemap[j][k] > newWayCost) nodemap[j][k] = newWayCost;
                }
            }
        }
        //unreachable 99999999 -> 0
        for (int i = 1; i < nodeNum + 1; i++) {
            for (int j = 1; j < nodeNum + 1; j++) {
                if (nodemap[i][j] == 99999999) nodemap[i][j] = 0;
            }
        }
        //output
        for (int i = 1; i < nodeNum + 1; i++) {
            bw.write(Integer.toString(nodemap[i][1]));
            for (int j = 2; j < nodeNum + 1; j++) {
                bw.write(" " + Integer.toString(nodemap[i][j]));
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();;
    }
}