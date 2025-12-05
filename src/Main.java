import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int pokeNum = Integer.parseInt(inputs[0]);
        int queNum = Integer.parseInt(inputs[1]);

        LinkedHashMap<Integer, String> collects = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> collects2 = new LinkedHashMap<>();
        for (int i = 1; i < pokeNum + 1; i++) {
            String name = br.readLine();
            collects.put(i, name);
            collects2.put(name, i);
        }

        boolean isFirst = true;
        for (int i = 0; i < queNum; i++) {
            String question = br.readLine();
            if (question.matches(".*[0-9].*")) {
                int temp = Integer.parseInt(question);
                if (isFirst) {
                    bw.write(collects.get(temp));
                    isFirst = false;
                } else {
                    bw.newLine();
                    bw.write(collects.get(temp));
                }
            } else {
                if (isFirst) {
                    bw.write(Integer.toString(collects2.get(question)));
                    isFirst = false;
                } else {
                    bw.newLine();
                    bw.write(Integer.toString(collects2.get(question)));
                }
            }
        }


        

        bw.flush();
        bw.close();
    }

}

