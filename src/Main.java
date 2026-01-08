import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] infos = br.readLine().split(" ");
        int siteNum = Integer.parseInt(infos[0]);
        int toFindNum = Integer.parseInt(infos[1]);

        HashMap<String, String> siteList = new HashMap<>();

        for (int i = 0; i < siteNum; i++) {
            String[] siteNpw = br.readLine().split(" ");
            siteList.put(siteNpw[0], siteNpw[1]);
        }

        for (int i = 0; i < toFindNum; i++) {
            String site = br.readLine();
            bw.write(siteList.get(site) + "\n");
        }

        bw.flush();
        bw.close();
    }
}
