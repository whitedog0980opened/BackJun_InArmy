import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold) 25943, 14534 (marathon)
//want to study : hashTable
//need to review = 5430 ! deque


// 순번의 int 배열을 n자릿수의 숫자로 바꾸고 list에 넣기까지 함
// 이제 이걸 정렬하고, int 배열로 바꿔서 매칭해서 출력하면 됨
//14534
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        HashSet<Integer> list = new HashSet<>();
        ArrayList<Integer>[] patterns = new ArrayList[6];

        for (int i = 0; i < tc; i++) {
            char[] chars = br.readLine().toCharArray();

            //check pattern exist
            int maxDepth = chars.length;
            if (patterns[maxDepth] != null) {
                printCase14534(chars, patterns[maxDepth], i, bw);
                continue;
            }

            int[] indexOfChars = new int[chars.length];
            for (int j = 0; j < chars.length; j++) {
                indexOfChars[j] = j;
            }
            
            dfs14534(0, indexOfChars, chars, list);
            ArrayList<Integer> sortedList = new ArrayList<>(list);
            Collections.sort(sortedList);
            patterns[maxDepth] = sortedList;
            printCase14534(chars, patterns[maxDepth], i, bw);
            list = new HashSet<>();
        }

        bw.flush();
        bw.close();
    }
    static void dfs14534(int depth, int[] arr, char[] chars, HashSet<Integer> list) {
        if (depth == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                list.add(arrToInt14534(arr));
            }
            return;
        }

        for (int i = depth; i < arr.length; i++) {
            swap14534(depth, i, arr);
            dfs14534(depth + 1, arr, chars, list);
            swap14534(depth, i, arr);
        }
    }
    static void swap14534(int i, int j, int[] arr) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static int arrToInt14534(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i] * (int) Math.pow(10 ,arr.length - i - 1);
        }
        return result;
    }
    static void printCase14534(char[] chars, ArrayList<Integer> pattern, int testcase, BufferedWriter bw) throws IOException {
        if (testcase == 0) {
            bw.write("Case # " + Integer.toString(testcase + 1) + ":");
        } else {
            bw.write("\nCase # " + Integer.toString(testcase + 1) + ":");
        }

        //testCodes
        // bw.newLine();
        // for (int temp : pattern) {
        //     bw.write(Integer.toString(temp) + ", ");
        // }
        // bw.newLine();
        // for (char temp : chars) {
        //     bw.write(Character.toString(temp) + ", ");
        // }
        

        int charNum = chars.length;
        for (int singlePtn : pattern) {
            bw.newLine();

            int pre = 0;
            for (int i = charNum - 1; i >= 0; i--) {
                int crr = (singlePtn / (int) Math.pow(10, i)) - pre ;
                bw.write(Character.toString(chars[crr]));
                pre = pre * 10 + crr * 10;
            }
        }
    }
}