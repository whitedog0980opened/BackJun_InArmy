import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tries; i++) {
            System.out.println(Integer.toString(i + 1));
        }
    }
}
