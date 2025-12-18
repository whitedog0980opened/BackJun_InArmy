import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tries = Integer.parseInt(br.readLine());
        HashSet set = new HashSet<>();
        for (int i = 0; i < tries; i++) {
            String[] command = br.readLine().split(" ");
            if (command.length == 1) {
                if (command[0].equals("all")) {
                    for (int j = 1; j <= 20; j++) {
                        set.add(j);
                    }
                    continue;
                } else {
                    set.removeAll(set);
                    continue;
                }
            }
            int num = Integer.parseInt(command[1]);
            if (command[0].equals("add")) {
                set.add(num);
                continue;
            }
            else if (command[0].equals("remove")) {
                set.remove(num);
                continue;
            }
            else if (command[0].equals("toggle")) {
                boolean toggle = set.contains(num);
                if (toggle) set.remove(num);
                else set.add(num);
                continue;
            }
            else if (command[0].equals("check")) {
                boolean isContain = set.contains(num);
                if (isContain) bw.write("1\n");
                else bw.write("0\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
