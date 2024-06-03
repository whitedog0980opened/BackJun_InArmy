import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String temp;
        Scanner sc = new Scanner(System.in);
        int multy = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < 9; i++) {
            temp = String.format("%d * %d = %d", multy, i + 1, multy * (i + 1));
            System.out.println(temp);
        }
    }
}
