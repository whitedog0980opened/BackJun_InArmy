import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int lineNum = Integer.parseInt(br.readLine()); //first input - total line nums
        Set<Integer> inputTree = new TreeSet<>();
        for(int i = 0; i < lineNum; i++) {
            inputTree.add(Integer.parseInt(br.readLine()));
        }



        for (int i : inputTree) {
            bw.write(Integer.toString(i));
            bw.newLine();
        }
        bw.close();
    }
}
