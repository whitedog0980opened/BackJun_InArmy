import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int last = -1; // 마지막 push된 값 저장용 변수

        for (int i = 0; i < inputNum; i++) {
            String command = br.readLine();

            if (command.startsWith("push")) {
                int x = Integer.parseInt(command.split(" ")[1]);
                queue.offer(x);
                last = x; // 마지막 값 갱신
            }
            else if (command.equals("pop")) {
                int result = queue.isEmpty() ? -1 : queue.poll();
                bw.write(String.valueOf(result));
                bw.newLine();
            }
            else if (command.equals("size")) {
                bw.write(String.valueOf(queue.size()));
                bw.newLine();
            }
            else if (command.equals("empty")) {
                bw.write(queue.isEmpty() ? "1" : "0");
                bw.newLine();
            }
            else if (command.equals("front")) {
                int result = queue.isEmpty() ? -1 : queue.peek();
                bw.write(String.valueOf(result));
                bw.newLine();
            }
            else if (command.equals("back")) {
                int result = queue.isEmpty() ? -1 : last;
                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }
}
