import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;
        int num = 0;
        for (int i = 0; i < 9; i++) {
            int temp = Integer.parseInt(sc.nextLine());
            if (temp > max) {
                max = temp;
                num = i + 1;
            }
        }
        System.out.println(Integer.toString(max));
        System.out.println(Integer.toString(num));
    }
}
