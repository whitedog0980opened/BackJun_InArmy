import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int y = Integer.parseInt(sc.nextLine());
        if ((!(y % 100 == 0) || (y % 400 == 0)) && (y % 4 == 0)) {
            System.out.printf("1");
        }
        else System.out.printf("0");
    }
}
