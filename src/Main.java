import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list :
//want to study : hashTable
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //instant Stack
    //30503
    //not tested
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int mapSize = Integer.parseInt(st.nextToken());

        int[] map = new int[mapSize + 1]; //1 starting
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < mapSize + 1; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        int quaryNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < quaryNum; i++) {
            String[] inputs = br.readLine().split(" ");
            if (inputs.length == 3) {
                int from = Integer.parseInt(inputs[1]);
                int to = Integer.parseInt(inputs[2]);
                for (int j = from; j <= to; j++) {
                    map[j] = Integer.MAX_VALUE;
                }
            }
            else {
                int from = Integer.parseInt(inputs[1]);
                int to = Integer.parseInt(inputs[2]);
                int findCase = Integer.parseInt(inputs[3]);
                
                int counter = 0;
                for (int j = from; j <= to; j++) {
                    if (map[j] == findCase) counter++;
                }
                bw.write(Integer.toString(counter) + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}