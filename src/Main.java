import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
//need to review = 5430 ! deque
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        firstLoop : for (int i = 0; i < testCase; i++) {
            boolean isFliped = false;
            int[] deletingIndex = {0, 0};
            String commends = br.readLine().replace("RR", "");
            int arrayLength = Integer.parseInt(br.readLine());
            String[] arrayString = br.readLine().replaceAll("[\\[\\]]", "").split(",");

            int[] array = new int[arrayLength];
            for (int j = 0; j < arrayLength; j++) { //get array
                array[j] = Integer.parseInt(arrayString[j]);
            }
            for (int j = 0; j < commends.length(); j++) { //handle commend
                char crrCommend = commends.charAt(j);
                if (crrCommend == 'D') {
                    int aliveNumCounter = 0;
                    for (int k : array) {
                        if (k != -1) aliveNumCounter++;
                    }

                    if (aliveNumCounter >= 1) {
                        if (!isFliped) {
                            array[deletingIndex[0]++] = -1;
                        } else {
                            array[array.length - deletingIndex[1] - 1] = -1;
                            deletingIndex[1]++;
                        }

                        // int[] newArray = new int[array.length - 1];
                        // for (int k = 1; k < array.length; k++) {
                        //     newArray[k - 1] = array[k];
                        // }
                        // array = newArray;
                    }
                    else {
                        bw.write("error\n");
                        continue firstLoop;
                    }
                }
                else {
                    int len = array.length;
                    int[] newArray = new int[len];
    
                    for (int k = 0; k < len; k++) {
                        newArray[k] = array[len - 1 - k];
                    }
    
                    System.arraycopy(newArray, 0, array, 0, len);
                    isFliped = !isFliped;
                }
            }
            arrayLength = array.length;
            int[] temp = new int[array.length];
            int k = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[j] != 0) {
                    temp[k++] = array[j];
                }
            } 

            if (arrayLength == 1) {
                bw.write("[" + temp[0] + "]\n");
                continue;
            }
            for (int j = 0; j < k; j++) {
                if (j == k - 1) {
                    bw.write(temp[j] + "]\n");
                }
                else if (j == 0) {
                    bw.write("[" + temp[j] + ",");
                }
                else {
                    bw.write(temp[j] + ",");
                }
            }


        }
        

        bw.flush();
        bw.close();
    }
}
