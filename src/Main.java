import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 34635 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //1655
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        MaxHeap1655 maxH = new MaxHeap1655(num);
        MinHeap1655 minH = new MinHeap1655(num);
        for (int i = 0; i < num; i++) {
            int crr = Integer.parseInt(br.readLine());

            if (maxH.next == minH.next) {
                maxH.add(crr);
            } else {
                minH.add(crr);
            }

            if (maxH.next > 1 && minH.next > 1 && maxH.peek() > minH.peek()) {
                int maxVal = maxH.pop();
                int minVal = minH.pop();
                maxH.add(minVal);
                minH.add(maxVal);
            }
        
            bw.write(Integer.toString(maxH.peek()) + "\n");
        }

        bw.flush();
        bw.close();;
    }
}