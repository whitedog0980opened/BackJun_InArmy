import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInput = br.readLine().split(" ");
        int treeNum = Integer.parseInt(firstInput[0]);
        long needQuantity = Integer.parseInt(firstInput[1]);
        String[] treesInput = br.readLine().split(" ");
        int[] trees = new int[treeNum];
        int longestTree = 0;
        for (int i = 0; i < treeNum; i++) {
            trees[i] = Integer.parseInt(treesInput[i]);
            if (longestTree < trees[i]) longestTree = trees[i];
        }

        long start = 0;
        long end = longestTree;
        long result= 0;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long collected = 0;
            for (int tree : trees) { //counting tree collected
                if (tree > mid) collected += tree - mid;
            }
            long crrOverCollected = collected - needQuantity;
            if (collected >= needQuantity) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        bw.write(Long.toString(result));
        bw.flush();
        bw.close();
    }
}
