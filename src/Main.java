import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] numsStr = sc.nextLine().split(" ");
        int dice1 = Integer.parseInt(numsStr[0]);
        int dice2 = Integer.parseInt(numsStr[1]);
        int dice3 = Integer.parseInt(numsStr[2]);
        if (dice1 == dice2 && dice2 == dice3 && dice3 == dice1) {
            System.out.printf(Integer.toString(10000 + 1000 * dice1));
        }
        else if (dice1 == dice2 || dice2 == dice3 || dice3 == dice1) {
            System.out.printf(Integer.toString(1000 + 100 * (((dice1 - dice2 == 0) ? 1 : 0) * dice1 + ((dice2 - dice3 == 0) ? 1 : 0) * dice2 + ((dice3 - dice1 == 0) ? 1 : 0) * dice3)));
        }
        else {
            System.out.printf(Integer.toString((dice1 > dice2) ? ((dice1 > dice3) ? dice1 * 100 : dice3 * 100) : (dice2 > dice3) ? dice2 * 100 : dice3 * 100));
        }
    }
}
