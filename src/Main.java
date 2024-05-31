import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = Integer.parseInt(sc.nextLine());
        int y = Integer.parseInt(sc.nextLine());
        if (x > 0 && y > 0) {System.out.printf("1");}
        if (x < 0 && y > 0) {System.out.printf("2");}
        if (x < 0 && y < 0) {System.out.printf("3");}
        if (x > 0 && y < 0) {System.out.printf("4");}
    }
}
