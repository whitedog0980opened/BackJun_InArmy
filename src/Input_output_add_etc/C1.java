package Input_output_add_etc;

import java.util.Scanner;

public class C1 {
    public void n18108() {
        Scanner scanner = new Scanner(System.in);
        int yearA = scanner.nextInt();
        System.out.printf(Integer.toString(yearA - 543));
    }

    public void n10430() {
        Scanner scanner = new Scanner(System.in);
        String inputs = scanner.nextLine();
        int[] nums = new int[3];
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(inputs.split(" ")[i]);
        }
        System.out.println(Integer.toString((nums[0]+nums[1]) % nums[2]));
        System.out.println(Integer.toString((nums[0]+nums[1]) % nums[2]));
        System.out.println(Integer.toString((nums[0]*nums[1]) % nums[2]));
        System.out.println(Integer.toString((nums[0]*nums[1]) % nums[2]));
    }
}