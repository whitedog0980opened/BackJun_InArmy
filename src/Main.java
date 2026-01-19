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

        int tc = Integer.parseInt(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < tc; i++) {
            char[] chars = br.readLine().toCharArray();
            Arrays.sort(chars);
            int[] indexOfChars = new int[chars.length];
            for (int j = 0; j < chars.length; j++) {
                indexOfChars[j] = j;
            }
            if (i == 0) {
                bw.write("Case # " + Integer.toString(i + 1) + ":");
            } else {
                bw.write("\nCase # " + Integer.toString(i + 1) + ":");
            }
            dfs14534(0, indexOfChars, chars, bw, list);
        }

        bw.flush();
        bw.close();
    }
    static void dfs14534(int depth, int[] arr, char[] chars, BufferedWriter bw, ArrayList<Integer> list) throws IOException {
        if (depth == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                list.add(arrToInt(arr));
            }
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap14534(depth, i, arr);
            dfs14534(depth + 1, arr, chars, bw, list);
            swap14534(depth, i, arr);
        }
    }
    static void swap14534(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int arrToInt(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] * (int) Math.pow(10 ,arr.length - i - 1);
        }
        return result;
    }
}