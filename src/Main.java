import Input_output_add_etc.C1;

import java.util.Scanner;

public class Main {
    public static void minateHandle(int minate, int hour) {
        if (minate > 59) {
            minate -= 60;
            hour += 1;
        }
        if (hour > 23) {
            hour -= 24;
        }
        if (minate < 0) {minateHandle(minate, hour);}
        else System.out.printf(Integer.toString(hour) + " " + Integer.toString(minate));;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int hour = Integer.parseInt(strs[0]);
        int minate = Integer.parseInt(strs[1]);
        int cookTimer = Integer.parseInt(sc.nextLine());
        minate += cookTimer;
        minateHandle(minate, hour);
    }
}
