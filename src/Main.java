import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());

        //from = first index, to = sec index
        int[][] map = new int[nodeNum + 1][nodeNum + 1]; 
        // init map
        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crr = 0;
            int crrNode = Integer.parseInt(inputs[0]);
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                map[crrNode][nextNode] = length;
            }
        }

        // i = crrNode
        for (int i = 1; i < nodeNum + 1; i++) {
            //this loop for find fromNum
            int[] newConnection = new int[nodeNum + 1];
            for (int j = 1; j < nodeNum + 1; j++) {
                if (map[i][j] == 0) continue; //empty 

                //this loop for find toNum and add new connection
                for (int k = 1; k < nodeNum + 1; k++) {
                    int fromNum = map[i][j];
                    int toNum = map[fromNum][k];
                    if (toNum == 0 || k == i) continue; //empty or fromNum and toNum endPoint is same 
                    if (newConnection[k] == 0 || (newConnection[k] > toNum + fromNum)) {
                        newConnection[k] = toNum + fromNum;
                    }
                }
            }
        }


        bw.flush();
        bw.close();
    }
}
