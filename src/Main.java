import Input_output_add_etc.C1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input1 = sc.nextLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        int[][] listInputs = new int[n][m];
        int[][] listQED = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listInputs[i][j] = Integer.parseInt(temp[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listQED[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int changeNum = 0;
        for(int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (listInputs[i][j] != listQED[i][j]) {
                    flip(listInputs, i, j);
                    changeNum++;
                }
            }
        }
        if (!Arrays.deepEquals(listInputs, listQED)) {System.out.printf("-1");}
        else {System.out.println(Integer.toString(changeNum));}

    }
    public static void flip(int[][] array, int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[x + i][y + j] = array[x + i][y + j] == 1 ? 0 : 1;
            }
        }
    }
}

