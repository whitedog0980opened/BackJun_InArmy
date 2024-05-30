import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String result;
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        String[] inputStrs = inputStr.split(" ");
        int[] nums = new int[2];
        nums[0] = Integer.parseInt(inputStrs[0]);
        nums[1] = Integer.parseInt(inputStrs[1]);
        if (nums[0] > nums[1]) {result = ">";}
        else if (nums[0] < nums[1]) {result = "<";}
        else {result = "==";}
        System.out.printf(result);
    }
}
