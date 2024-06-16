import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        String[] numsStr = sc.nextLine().split(" ");

        int[] nums = new int[tries];
        for (int i = 0; i < tries; i++) {
            nums[i] = Integer.parseInt(numsStr[i]);
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i: nums) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        System.out.printf("%d %d", min, max);
    }
}