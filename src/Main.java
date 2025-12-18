import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCase = Integer.parseInt(br.readLine());
        firstLoop : for (int i = 0; i < testCase; i++) {
            boolean isFliped = false;
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
                    if (array.length >= 1) {
                        int[] newArray = new int[array.length - 1];
                        for (int k = 1; k < array.length; k++) {
                            newArray[k - 1] = array[k];
                        }
                        array = newArray;
                    }
                    else {
                        bw.write("error\n");
                        continue firstLoop;
                    }
                }
                else {
                    int[] newArray = IntStream.range(0, array.length)
                          .map(k -> array[array.length - 1 - k])
                          .toArray();

                    System.arraycopy(newArray, 0, array, 0, array.length);
                }
            }
            arrayLength = array.length;
            if (arrayLength == 1) {
                bw.write("[" + array[0] + "]\n");
                continue;
            }
            for (int j = 0; j < arrayLength; j++) {
                if (j == arrayLength -1) {
                    bw.write(array[j] + "]\n");
                }
                else if (j == 0) {
                    bw.write("[" + array[j] + ",");
                }
                else {
                    bw.write(array[j] + ",");
                }
            }


        }
        

        bw.flush();
        bw.close();
    }
}
