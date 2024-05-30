import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numsStr = scanner.nextLine();
        Long[] nums = new Long[4];
        String[] numsStrArray = numsStr.split(" ");
        for (int i = 0; i < 3; i++) {
            nums[i] = Long.parseLong(numsStrArray[i]);
        }
        System.out.printf(Long.toString(nums[0] + nums[1] + nums[2]));
    }
}