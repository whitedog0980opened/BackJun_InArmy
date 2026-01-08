//This class saves temp resorce or stocks

// public class Main {
//     public static void main(String[] args) throws IOException {

//     }
// }


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Temp {
    public void n1167_inProgress() throws IOException {
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

                int fromNum = map[i][j];
                //this loop for find toNum and add new connection
                for (int k = 1; k < nodeNum + 1; k++) {
                    int toNum = map[fromNum][k];
                    if (toNum == 0 || k == i) continue; //empty or fromNum and toNum endPoint is same 
                    if (newConnection[k] == 0 || (newConnection[k] > toNum + fromNum)) {
                        newConnection[k] = toNum + fromNum;
                    }
                }
            }
        }
        bw.write("stopPoint");
        //42 인덱스 범위 초가 주의
        //아마 맵 내부의 숫자는 거리를 뜻하는데, 이게 fromNum으로 착각해서 생김
        //즉 첫번째 루프는 fromNum이 2가 아니라 2가 1.3 인덱스에 있으니까 from은 3이 됨


        bw.flush();
        bw.close();
    }
    static void n27866() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        int index = Integer.parseInt(br.readLine());

        bw.write(Character.toString(text.charAt(index - 1)));
        bw.flush();
        bw.close();
    }
}