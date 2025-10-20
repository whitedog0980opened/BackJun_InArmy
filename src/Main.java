import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }
        line = line.trim();

        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        StringBuilder cur = new StringBuilder();

        // Parse the input line into nums and ops
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '+' || c == '-') {
                nums.add(Integer.parseInt(cur.toString()));
                cur.setLength(0);
                ops.add(c);
            } else {
                cur.append(c);
            }
        }
        // Add the last number
        if (cur.length() > 0) nums.add(Integer.parseInt(cur.toString()));

        int result = nums.isEmpty() ? 0 : nums.get(0);
        boolean minusSeen = false;
        for (int i = 0; i < ops.size() && i + 1 < nums.size(); i++) {
            char op = ops.get(i);
            int next = nums.get(i + 1);
            if (!minusSeen) {
                if (op == '+') result += next;
                else { minusSeen = true; result -= next; }
            } else {
                result -= next;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}