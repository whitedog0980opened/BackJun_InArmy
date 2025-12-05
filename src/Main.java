import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input1 = br.readLine();
        String input2 = br.readLine();
        String input3 = br.readLine();
        int answer = 0;

        if (input1.matches(".*[0-9].*")) {
            answer = Integer.parseInt(input1) + 3;
        } else if (input2.matches(".*[0-9].*")) {
            answer = Integer.parseInt(input2) + 2;
        } else {
            answer = Integer.parseInt(input3) + 1;
        }

        if (answer % 3 == 0) {
            if (answer % 5 == 0) {
                bw.write("FizzBuzz");
            } else {
                bw.write("Fizz");
            }
        } else if (answer % 5 == 0) {
            bw.write("Buzz");
        } else {
            bw.write(Integer.toString(answer));
        }

        bw.flush();
        bw.close();
    }

}

