import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold) 25943, 14534 (marathon)
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] chars = br.readLine().toCharArray();
        int[] indexOfChars = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            indexOfChars[i] = i;
        }




        


        bw.flush();
        bw.close();
    }
    static void dfs(int depth, int[] arr, char[] chars, BufferedWriter bw) {
        if (depth == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                char a = chars[arr[i]];
                bw.write(Character.toString(a));
            }

            
            
        }


        
    }
}