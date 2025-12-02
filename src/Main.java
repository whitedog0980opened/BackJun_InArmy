import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String brokenISBN = br.readLine();
        int brokenWeight = 0;
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            char crr = brokenISBN.charAt(i);
            int weight = 3 - ((i + 1)%2) * 2;
            if (crr == '*') {
                brokenWeight = weight;
            }
            else {
                sum += Character.getNumericValue(crr) * weight;
            }
        }

        int keyRest = sum % 10;
        for (int i = 0; i < 10; i++) {
            int total = sum + i * brokenWeight;
            if (total % 10 == 0) {
                bw.write(Integer.toString(i));
                break;
            }
        
        }

        bw.flush();
        bw.close();
    }

}

