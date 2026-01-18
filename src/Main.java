import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라)
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String hg = br.readLine();
        boolean isCorrect = true;
        StringBuilder str = new StringBuilder();

        int i = 0;
        while (i < hg.length()) {
            if (hg.startsWith("aespa" , i)) {
                i += 5;
                str.append("a");
            }
            else if (hg.startsWith("baekjoon" , i)) {
                i += 8;
                str.append("b");
            }
            else if (hg.startsWith("cau" , i)) {
                i += 3;
                str.append("c");
            }
            else if (hg.startsWith( "debug", i)) {
                i += 5;
                str.append("d");
            }
            else if (hg.startsWith("edge" , i)){
                i += 4;
                str.append("e");
            }
            else if (hg.startsWith("firefox" , i)){
                i += 7;
                str.append("f");
            }
            else if (hg.startsWith("golang" , i)) {
                i += 6;
                str.append("g");
            }
            else if (hg.startsWith("haegang" , i)) {
                i += 7;
                str.append("h");
            }
            else if (hg.startsWith("iu" , i)){
                i += 2;
                str.append("i");
            }
            else if (hg.startsWith("java" , i)) {
                i += 4;
                str.append("j");
            }
            else if (hg.startsWith("kotlin" , i)) {
                i += 6;
                str.append("k");
            }
            else if (hg.startsWith("lol" , i)) {
                i += 3;
                str.append("l");
            }
            else if (hg.startsWith("mips" , i)){
                i += 4;
                str.append("m");
            }
            else if (hg.startsWith("null" , i)) {
                i += 4;
                str.append("n");
            }
            else if (hg.startsWith("os" , i)) {
                i += 2;
                str.append("o");
            }
            else if (hg.startsWith("python" , i)) {
                i += 6;
                str.append("p");
            }
            else if (hg.startsWith("query" , i)) {
                i += 5;
                str.append("q");
            }
            else if (hg.startsWith("roka" , i)) {
                i += 4;
                str.append("r");
            }
            else if (hg.startsWith("solvedac" , i)) {
                i += 8;
                str.append("s");
            }
            else if (hg.startsWith("tod" , i)) {
                i += 3;
                str.append("t");
            }
            else if (hg.startsWith("unix" , i)) {
                i += 4;
                str.append("u");
            }
            else if (hg.startsWith("virus" , i)){
                i += 5;
                str.append("v");
            }
            else if (hg.startsWith("whale" , i)) {
                i += 5;
                str.append("w");
            }
            else if (hg.startsWith("xcode" , i)) {
                i += 5;
                str.append("x");
            }
            else if (hg.startsWith("yahoo" , i)) {
                i += 5;
                str.append("y");
            }
            else if (hg.startsWith("zebra" , i)) {
                i += 5;
                str.append("z");
            }
            else { 
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            bw.write("It's HG!\n" + str.toString());
        }
        else bw.write("ERROR!");
        bw.flush();
        bw.close();
    }
}
