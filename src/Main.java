import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int meetingNum = Integer.parseInt(br.readLine());

        int[][] meetings = new int[meetingNum][2];
        for (int i = 0; i < meetingNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int startTime = Integer.parseInt(inputs[0]);
            int endTime = Integer.parseInt(inputs[1]);

            meetings[i][0] = startTime;
            meetings[i][1] = endTime;
        }

        Arrays.sort(meetings, (n1, n2) -> {
            if (n1[1] == n2[1]) {
                return Integer.compare(n1[0], n2[0]);
            }
            return Integer.compare(n1[1], n2[1]);
        });



        int leastStartTime = 0;
        int ableMeetingNum = 0;
        for (int i = 0; i < meetingNum; i++) {
            int start = meetings[i][0];
            int end = meetings[i][1];

            // 이전에 선택한 회의와 겹치지 않으면 선택
            if (start >= leastStartTime) {
                ableMeetingNum++;
                leastStartTime = end;
            }
        }

        bw.write(Integer.toString(ableMeetingNum));
        
        bw.flush();
        bw.close();
    }
}
