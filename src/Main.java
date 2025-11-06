import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
        Stack<Integer> stack = new Stack<>();
        boolean checker = true;
        boolean isFirst = true;
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;
            checker = true;
            stack.clear();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '(') stack.add(1); // 1 means "("
                else if (ch == ')') {
                    if (stack.isEmpty()) {
                        checker = false;
                        break;
                    }
                    if (stack.pop() != 1) {
                        checker = false;
                        break;
                    }
                }
                else if (ch == '[') stack.add(2); // 2 means "["
                else if (ch == ']') {
                    if (stack.isEmpty()) {
                        checker = false;
                        break;
                    }
                    if (stack.pop() != 2) {
                        checker = false;
                        break;
                    }
                }
            }
            if (!stack.empty()) {
                checker = false;
                stack.clear();
            }

            if (isFirst) {
                isFirst = false;
            } else {
                bw.newLine();
            }
            bw.write(checker ? "yes" : "no");
            
        }
        

        bw.flush();
        bw.close();
    }
}
