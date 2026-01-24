import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold)
//want to study : hashTable
//need to review = 5430 ! deque


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int cases = Integer.parseInt(br.readLine());
        ArrayList<String> ableNames = new ArrayList<>();

        for (int i = 0; i < cases; i++) {
            String[] inputs = br.readLine().split(" ");
            int maxScore = Integer.parseInt(inputs[3]);
            int crrScore = Integer.parseInt(inputs[4]);
            if (inputs[1].equals("jaehak") && inputs[2].equals("notyet") && (maxScore > 3 || maxScore == -1)) {
                ableNames.add(inputs[0]);
            }
        }

        Collections.sort(ableNames);
        bw.write(Integer.toString(ableNames.size()) + "\n");
        for (String name : ableNames) {
            bw.write(name + "\n");
        }
        
        
        bw.flush();
        bw.close();
    }

}