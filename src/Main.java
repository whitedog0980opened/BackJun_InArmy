import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int totalPeople = Integer.parseInt(br.readLine());
        String[] timeInput = br.readLine().split(" ");
        int[] times = new int[totalPeople]; 
        for (int i = 0; i < totalPeople; i++) {
            times[i] = Integer.parseInt(timeInput[i]);
        }

        Arrays.sort(times);
        int sum = 0;
        int sum2 = 0;
        for (int i = 0; i < totalPeople; i++) {
            sum += times[i];
            sum2 += sum;
        }
        bw.write(Integer.toString(sum2));
        
        bw.flush();
        bw.close();
    }
}
