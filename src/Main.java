import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String result;
        Scanner sc = new Scanner(System.in);
        int score = Integer.parseInt(sc.nextLine());
        if (score > 90) {result = "A";}
        else if (100 >= score && score > 80) {result = "B";}
        else if (score > 70) {result = "C";}
        else if (score > 60) {result = "D";}
        else {result = "F";}
        System.out.printf(result);
    }
}
