import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
//need to review = 5430 ! deque
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String str = br.readLine();
        String str2 = str.replace("IO", "1") + "O";

        ArrayList<Integer> foundRepeatPattern = new ArrayList<>();
        boolean[] is1 = {false};
        int[] counter = {0};
        str2.chars().forEach(i -> {
            if (is1[0]) {
                if (i == 'I') { // I
                    foundRepeatPattern.add(counter[0]);
                    counter[0] = 0;
                    is1[0] = false;
                } else if (i == 'O') { // 0
                    foundRepeatPattern.add(counter[0] - 1);
                    counter[0] = 0;
                    is1[0] = false;
                } else {
                    counter[0]++;
                }
                
            } else if(i == '1') { // 1
                counter[0]++;
                is1[0] = true;
            }
        });

        int result = 0;
        for (int i : foundRepeatPattern) {
            int cul = i - N + 1;
            if (cul > 0) result += cul;
        }

        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();
    }
}
