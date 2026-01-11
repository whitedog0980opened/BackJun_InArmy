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
}
