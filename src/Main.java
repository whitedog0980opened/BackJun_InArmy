import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //inputs
        int N = Integer.parseInt(br.readLine());
        String[] cardNumStr = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] checkNumStr = br.readLine().split(" ");

        HashMap<Integer, Integer> cardMap = new HashMap<>(); 
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int cardNum = Integer.parseInt(cardNumStr[i]);
            boolean isNew = !cardMap.containsKey(cardNum);
            if (isNew) {
                cardMap.put(cardNum, 1);
            } else { // 중복시 value 1 증가
                cardMap.put(cardNum, cardMap.get(cardNum) + 1);
            }
        }

        for (int i = 0; i < M; i++) {
            int checkNum = Integer.parseInt(checkNumStr[i]);
            if (cardMap.containsKey(checkNum)) resultList.add(cardMap.get(checkNum));
            else resultList.add(0); 
        }

        for (int i : resultList) {
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }
}