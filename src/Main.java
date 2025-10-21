import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int peopleNum = Integer.parseInt(br.readLine());
        String[] sizesStr = br.readLine().split(" "); 
        int[] sizes = new int[6];
        for (int i = 0; i < 6; i++) {
            sizes[i] = Integer.parseInt(sizesStr[i]);
        } 
        String[] boundsStr = br.readLine().split(" ");
        int tShirtBound = Integer.parseInt(boundsStr[0]);
        int penBound    = Integer.parseInt(boundsStr[1]);

        int toBuyTshirtBount = 0;
        for (int i : sizes) {
            if (i == 0) continue;
            int currentNeedBound = i / tShirtBound;
            if (i % tShirtBound != 0) currentNeedBound++;
            toBuyTshirtBount += currentNeedBound;
        }

        int toBuyPenBound = peopleNum / penBound;
        int toBuyPen = peopleNum % penBound;

        bw.write(String.valueOf(toBuyTshirtBount));
        bw.newLine();
        bw.write(String.valueOf(toBuyPenBound) + " " + String.valueOf(toBuyPen));

        bw.flush();
        bw.close();
    }
}
