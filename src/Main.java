import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] blist = new int[3];
        blist[0] = b % 10;
        blist[1] = ((b % 100) - blist[0]) / 10;
        blist[2] = (b - (b % 100)) / 100;
        System.out.println(Integer.toString(a * blist[0]));
        System.out.println(Integer.toString(a * blist[1]));
        System.out.println(Integer.toString(a * blist[2]));
        System.out.println(Integer.toString(a * b));
    }
}