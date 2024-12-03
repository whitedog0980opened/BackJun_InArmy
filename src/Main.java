import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //recieve inputs info
        String[] inputInfo = br.readLine().split(" "); //first input - total line nums
        int cardNum = Integer.parseInt(inputInfo[0]);
        int maxNum = Integer.parseInt(inputInfo[1]);

        //recieve cards
        String[] cardListStr = br.readLine().split(" ");
        int cardListInt[] = new int[cardNum];
        for (int i = 0; i < cardNum; i++) {
            cardListInt[i] = Integer.parseInt(cardListStr[i]);
        }

        int biggest = 0;
        for (int i = 0; i < cardNum - 2; i++) {
            for (int j = i + 1; j < cardNum - 1; j++) {
                for (int k = j + 1; k < cardNum; k++) {
                    int currMax = cardListInt[i] + cardListInt[j] + cardListInt[k];
                    if (currMax <= maxNum && currMax > biggest) {
                        biggest = currMax;
                    }
                }
            }
        }

        bw.write(Integer.toString(biggest));
        bw.close();
    }
}
