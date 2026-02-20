import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import org.w3c.dom.Node;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());

            int planetNum = Integer.parseInt(br.readLine());

            int needToPene = 0;
            for (int i = 0; i < planetNum; i++) {
                st = new StringTokenizer(br.readLine());
                int planetX = Integer.parseInt(st.nextToken());
                int planetY = Integer.parseInt(st.nextToken());
                int planetR = Integer.parseInt(st.nextToken());

                double startToPlX = Math.abs(planetX - startX);
                double startToPlY = Math.abs(planetY - startY);
                double endToPlX = Math.abs(planetX - endX);
                double endToPlY = Math.abs(planetX - endY);


                boolean startInnerPlanet = Math.sqrt(Math.pow(startToPlX, 2) + Math.pow(startToPlY, 2)) < planetR;
                boolean endInnerPlanet = Math.sqrt(Math.pow(endToPlX, 2) + Math.pow(endToPlY, 2)) < planetR;
                if (startInnerPlanet != endInnerPlanet) needToPene++;
            }

            bw.write(Integer.toString(needToPene) + "\n");
        }

        bw.flush();
        bw.close();
    }

}