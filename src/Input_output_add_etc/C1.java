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

    public void n11382() {
        Scanner scanner = new Scanner(System.in);
        String numsStr = scanner.nextLine();
        int[] nums = new int[3];
        String[] numsStrArray = numsStr.split(" ");
        for (int i = 0; i < 3; i++) {
            nums[i] = Integer.parseInt(numsStrArray[i]);
        }
        System.out.printf(Integer.toString(nums[1] + nums[2] + nums[3]));
    }

    public void  n10171() {
        System.out.printf("""
            \\    /\\
             )  ( ')
            (  /  )
             \\(__)|""");
    }

    public void n10172() {
        System.out.printf("""
            |\\_/|
            |q p|   /}
            ( 0 )""\"\\
            |"^"`    |
            ||_/=\\\\__|""");
    }

    public void n1330() {
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

    public void n9498() {
        String result;
        Scanner sc = new Scanner(System.in);
        int score = Integer.parseInt(sc.nextLine());
        if (score > 90) {result = "A";}
        else if (score > 80) {result = "B";}
        else if (score > 70) {result = "C";}
        else if (score > 60) {result = "D";}
        else {result = "F";}
        System.out.printf(result);
    }

    public void n2753() {
        Scanner sc = new Scanner(System.in);
        int y = Integer.parseInt(sc.nextLine());
        if ((!(y % 100 == 0) || (y % 400 == 0)) && (y % 4 == 0)) {
            System.out.printf("1");
        }
        else System.out.printf("0");
    }

    public void n14681() {
        Scanner sc = new Scanner(System.in);
        int x = Integer.parseInt(sc.nextLine());
        int y = Integer.parseInt(sc.nextLine());
        if (x > 0 && y > 0) {System.out.printf("1");}
        if (x < 0 && y > 0) {System.out.printf("2");}
        if (x < 0 && y < 0) {System.out.printf("3");}
        if (x > 0 && y < 0) {System.out.printf("4");}
    }
}