import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 13399 28043 (marahton)
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //16236
    //문제점 : 물고기가 도중에 성장하면, 중간부터 최단경로가 바뀐다. 
    //기존 미리 뽑아두는경우, 바뀐내용이 반영되지 않는다
    //+ 더 큰 물고기는 지나갈 수 없다. 이는 맨해튼 거리가 아닌 BFS사용을 유도한다.
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        int boxSize = Integer.parseInt(br.readLine());

        bw.flush();
        bw.close();;
    }

}