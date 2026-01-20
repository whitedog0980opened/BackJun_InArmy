import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold) 25943,(marathon)
//want to study : hashTable
//need to review = 5430 ! deque


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int graveNum = Integer.parseInt(br.readLine());

        int[] graves = new int[graveNum];
        //init by input
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < graveNum; i++) {
            graves[i] = Integer.parseInt(st.nextToken());
        }
        //first step -> put grave each dish
        int dishL = graves[0];
        int dishR = graves[1];

        //second step -> put each grave on less weight dish

        for (int i = 2; i < graveNum; i++) {
            int crrGrave = graves[i];
            if (dishL > dishR) dishR += crrGrave;
            else dishL += crrGrave; 
        }

        int diff = Math.abs(dishL - dishR);
        int weightNum = 0;


        int able100W = diff / 100;
        if (able100W > 0) {
            diff -= able100W * 100;
            weightNum += able100W;
        }
        boolean isAble50W = diff / 50 > 0; //50weight can able to use only 1
        if (isAble50W) {
            diff -= 50;
            weightNum++;
        }
        int able20W = diff / 20;
        if (able20W > 0) {
            diff -= able20W * 20;
            weightNum += able20W;
        }
        int able10W = diff / 10;
        if (able10W > 0) {
            diff -= able10W * 10;
            weightNum += able10W;
        }
        boolean isAble5W = diff / 5 > 0; //50weight can able to use only 1
        if (isAble5W) {
            diff -= 5;
            weightNum++;
        }
        int able2W = diff / 2;
        if (able2W > 0) {
            diff -= able2W * 2;
            weightNum += able2W;
        }
        int able1W = diff;
        if (able1W > 0) {
            diff -= able1W;
            weightNum += able1W;
        }

        bw.write(Integer.toString(weightNum));

        bw.flush();
        bw.close();
    }
}