package etc;
//This class saves temp resorce or stocks

// public class Main {
//     public static void main(String[] args) throws IOException {

//     }
// }


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Temp {
    static void n18870() throws IOException {
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