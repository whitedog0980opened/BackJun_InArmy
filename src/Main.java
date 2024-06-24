import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inputs = sc.nextLine().replace(" ", "");
        int num = Integer.parseInt(inputs);
        if (num == 12345678) {
            System.out.printf("ascending");
        } else if (num == 87654321) {
            System.out.printf("descending");
        } else  {
            System.out.printf("mixed");
        }
    }
}
