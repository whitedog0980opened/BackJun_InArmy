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

        String[] inputs = br.readLine().split(" ");
        int num = Integer.parseInt(inputs[0]);
        int quesNum = Integer.parseInt(inputs[1]);

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 1; i < num + 1; i++) {
            arr.add(i);
        }

        HashSet<Integer> fixedNums = new HashSet<>();
        HashSet<Integer> calledNums = new HashSet<>();
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < quesNum; i++) {
            int ques = Integer.parseInt(br.readLine());
            st.push(ques);
            fixedNums.add(ques);
        }

        outputs : for (int i = 0; i < num;) {
            int result = 0;
            if (!st.isEmpty()) {
                result = st.pop();
                if (calledNums.contains(result)) continue;
                calledNums.add(result);
                bw.write(Integer.toString(result) + "\n");
                continue;
            }
            while (fixedNums.contains(arr.get(i))) {
                i++;
                if (i >= num) {
                    break outputs;
                }
            }
            bw.write(Integer.toString(arr.get(i)) + "\n");
            i++;
        }
        

        bw.flush();
        bw.close();;
    }
}