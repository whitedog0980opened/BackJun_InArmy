import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tries; i++) {
            String[] inputs = sc.nextLine().split(" ");
            int a = Integer.parseInt(inputs[0]);
            a = a % 10;
            int b = Integer.parseInt(inputs[1]);
            b = b % 4;
            if (b == 0) {b = 4;}
            int result = (int)Math.pow(a, b) % 10;
            if (result == 0) {result = 10;}
            System.out.println(Integer.toString(result));
        }
    }
}
