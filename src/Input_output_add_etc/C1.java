package Input_output_add_etc;

import java.util.Scanner;

public class C1 {
    public void n18108() { // 불기 연도를 서기 연도로 변환
        Scanner scanner = new Scanner(System.in);
        int yearA = scanner.nextInt();
        System.out.printf(Integer.toString(yearA - 543));
    }

    public void n10430() { // 나머지 구하기
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

    public void n2588() { // 세자릿수 곱셈 문제
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int[] blist = new int[3];
        blist[0] = b % 10;
        blist[1] = ((b % 100) - blist[0]) / 10;
        blist[2] = (b - (b % 100)) / 100;
        System.out.println(Integer.toString(a * blist[0]));
        System.out.println(Integer.toString(a * blist[1]));
        System.out.println(Integer.toString(a * blist[2]));
        System.out.println(Integer.toString(a * b));
    }
}