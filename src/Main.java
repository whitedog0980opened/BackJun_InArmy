import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int hour = Integer.parseInt(strs[0]);
        int minate = Integer.parseInt(strs[1]);
        minate -= 45;
        if (minate < 0) {
            minate += 60;
            hour -= 1;
        }
        if (hour < 0) {
            hour += 24;
        }
        System.out.printf(Integer.toString(hour) + " " + Integer.toString(minate));
    }
}
