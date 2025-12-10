import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int trys = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean isFirst = true;

        for (int i = 0; i < trys; i++) {
            int commend = Integer.parseInt(br.readLine());
            if (commend == 0) {
                int output = pq.isEmpty() ? 0 : pq.remove();
                if (isFirst) {
                    bw.write(Integer.toString(output));
                    isFirst = false;
                }
                else {
                    bw.newLine();
                    bw.write(Integer.toString(output));
                }
            }
            else {
                pq.add(commend);
            }

        }

        
        bw.flush();
        bw.close();
    }
}
