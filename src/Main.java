import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        class Factor {
            public int x;
            public int y;
            Factor (int x, int y) {
                this.x = x;
                this.y = y;
            }
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(br.readLine());

        List<Factor> factors = new ArrayList<>();
        for (int i = 0; i < inputNum; i++) {
            String[] inputs = br.readLine().split(" ");
            Factor factor = new Factor(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            factors.add(factor);
        }

        Collections.sort(factors, (x, y) -> {
            if (x.x < y.x) return -1;
            else if (x.x == y.x) {
                if (x.y < y.y) return -1;
                else if (x.y == y.y) return 0;
                else return 1;
            } else {
                return 1;
            }
        });

        for (Factor factor : factors) {
            bw.write(factor.x + " " + factor.y + "\n");
        }

        bw.flush();
        bw.close();
    }
}
