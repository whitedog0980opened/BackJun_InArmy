import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1, num2;
        String[] numsStr;

        int tries = Integer.parseInt(sc.nextLine());
        int result[] = new int[tries];

        for (int i = 0; i < tries; i++) {
            numsStr = sc.nextLine().split(" ");
            num1 = Integer.parseInt(numsStr[0]);
            num2 = Integer.parseInt(numsStr[1]);
            result[i] = num1 + num2;
        }
        for (int i = 0; i < tries; i++) {
            System.out.println(Integer.toString(result[i]));
        }
    }
}
