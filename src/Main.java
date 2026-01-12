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

        int nums = Integer.parseInt(br.readLine());
        //this array is key of Hashmap
        int[] inputNums = new int[nums]; // original input data

        TreeSet<Integer> treeSet = new TreeSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums; i++) {
            int x = Integer.parseInt(st.nextToken());
            treeSet.add(x);
            inputNums[i] = x;
        }

        int treeSizeSave = treeSet.size();
        for (int i = 0; i < treeSizeSave; i++) {
            map.put(treeSet.pollFirst(), i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums; i++) {
            int index = map.get(inputNums[i]);
            sb.append(index + " ");
        }
        String result = sb.toString().trim();
        bw.write(result);
         
        bw.flush();
        bw.close();
    }
}
