import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        List<Integer[]> nodes = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            nodes.add(new Integer[2]);
        }

        for (int i = 0; i < nodeNum; i++)  {
            String[] inputStrs = br.readLine().split(" ");
            nodes.get(i)[0] = Integer.parseInt(inputStrs[0]);
            nodes.get(i)[1] = Integer.parseInt(inputStrs[1]);
        }

        Collections.sort(nodes, (x, y) -> {
            if (x[1] > y[1]) return 1;
            else if (x[1] < y[1]) return -1;
            else {
                if (x[0] > y[0]) return 1;
                else if (x[0] < y[0]) return -1;
                else return 0;
            }
        });

        //for mat form
        bw.write(Integer.toString(nodes.get(0)[0]));
        bw.write(" " + Integer.toString(nodes.get(0)[1]));
        for (int i = 1; i < nodeNum; i++) {
            bw.newLine();
            bw.write(Integer.toString(nodes.get(i)[0]));
            bw.write(" " + Integer.toString(nodes.get(i)[1]));
        }

        bw.flush();
        bw.close();
    }
}

