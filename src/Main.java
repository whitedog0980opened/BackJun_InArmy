import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold)
//want to study : hashTable
//need to review = 5430 ! deque

//1918
// split 정규식보다, Matcher 라는 클래스를 이용해보길 권장. (복잡한 수식이기때문)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    
        String ori = br.readLine();
        String[] nums = ori.split("[+-*/]");
        
        
        bw.flush();
        bw.close();
    }

}