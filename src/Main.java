import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라)
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] pikaSaid = br.readLine().toCharArray();

        int size = pikaSaid.length;
        boolean isCorrect = true;
        for (int i = 0; i < size; i++) { 
            //borderCheck
            if (i + 1 >= size) {
                isCorrect = false;
                break;
            }
            if (pikaSaid[i] == 'p' && pikaSaid[i + 1] == 'i') {
                i++;
                continue;
            }
            if (pikaSaid[i] == 'k' && pikaSaid[i + 1] == 'a') {
                i++;
                continue;
            }
            if (i + 2 >= size) {
                isCorrect = false;
                break;
            }
            if (pikaSaid[i] == 'c' && pikaSaid[i + 1] == 'h' && pikaSaid[i + 2] == 'u') {
                i += 2;
                continue;
            }
            isCorrect = false;
            break;
        }

        if (isCorrect) bw.write("YES");
        else bw.write("NO");
        bw.flush();
        bw.close();
    }
}
