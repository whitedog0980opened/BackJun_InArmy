import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int StudentNum = Integer.parseInt(br.readLine());
        List<Integer> retester = new ArrayList<>();

        A : for (int i = 0; i < StudentNum; i++) {
            String[] strScores = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                int correctAnswer = (j) % 5 + 1;
                int score = Integer.parseInt(strScores[j]);
                if (score != correctAnswer) {
                    continue A;
                }
            }
            retester.add(i + 1);
            
        }

        for (int i : retester) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }
}
