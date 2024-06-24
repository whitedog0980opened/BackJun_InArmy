package Input_output_add_etc;

import java.util.NoSuchElementException;
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

    public void n2884() {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int hour = Integer.parseInt(strs[0]);
        int minate = Integer.parseInt(strs[1]);
        minate -= 45;
        if (minate < 0) {
            minate += 60;
            hour -= 1;
        }
        if (hour < 1) {
            hour += 24;
        }
        System.out.printf(Integer.toString(hour) + " " + Integer.toString(minate));
    }


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
    public void n2525() {
        Scanner sc = new Scanner(System.in);
        String[] strs = sc.nextLine().split(" ");
        int hour = Integer.parseInt(strs[0]);
        int minate = Integer.parseInt(strs[1]);
        int cookTimer = Integer.parseInt(sc.nextLine());
        minate -= cookTimer;
        if (minate < 0) {
            minate += 60;
            hour -= 1;
        }
        if (hour < 1) {
            hour += 24;
        }
        System.out.printf(Integer.toString(hour) + " " + Integer.toString(minate));
    }

     public void n2480() {
         Scanner sc = new Scanner(System.in);
         String[] numsStr = sc.nextLine().split(" ");
         int dice1 = Integer.parseInt(numsStr[0]);
         int dice2 = Integer.parseInt(numsStr[1]);
         int dice3 = Integer.parseInt(numsStr[2]);
         if (dice1 == dice2 && dice2 == dice3 && dice3 == dice1) {
             System.out.printf(Integer.toString(10000 + 1000 * dice1));
         }
         else if (dice1 == dice2 || dice2 == dice3 || dice3 == dice1) {
             System.out.printf(Integer.toString(1000 + 100 * (((dice1 - dice2 == 0) ? 1 : 0) * dice1 + ((dice2 - dice3 == 0) ? 1 : 0) * dice2 + ((dice3 - dice1 == 0) ? 1 : 0) * dice3)));
         }
         else {
             System.out.printf(Integer.toString((dice1 > dice2) ? ((dice1 > dice3) ? dice1 * 100 : dice3 * 100) : (dice2 > dice3) ? dice2 * 100 : dice3 * 100));
         }
     }

     public void n2739() {
         String temp;
         Scanner sc = new Scanner(System.in);
         int multy = Integer.parseInt(sc.nextLine());
         for (int i = 0; i < 9; i++) {
             temp = String.format("%d * %d = %d", multy, i + 1, multy * (i + 1));
             System.out.println(temp);
         }
     }

     public void n10950() {
         Scanner sc = new Scanner(System.in);
         int num1, num2;
         String[] numsStr;

         int tries = Integer.parseInt(sc.nextLine());
         int result[] = new int[tries];

         for (int i = 0; i < tries; i++) {
             numsStr = sc.nextLine().split(" ");
             num1 = Integer.parseInt(numsStr[0]);
             num2 = Integer.parseInt(numsStr[1]);
             result[i] = num1 + num2;
         }
         for (int i = 0; i < tries; i++) {
             System.out.println(Integer.toString(result[i]));
         }
     }

     public void n1152() {
         Scanner sc = new Scanner(System.in);
         String str = sc.nextLine();
         str = str.trim(); //

         int len = str.split(" ").length;
         if (str.equals("")){len = 0;};

         System.out.printf(Integer.toString(len));
     }

     public void n2438() {
         Scanner sc = new Scanner(System.in);
         int tries = Integer.parseInt(sc.nextLine());
         for (int i = 0; i < tries; i++) {
             for (int j = 0; j <= i; j++) {
                 System.out.printf("*");
             }
             System.out.println();
         }
     }

     public void n2562() {
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

    public void n2675() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tries; i++) {
            String[] inputs = sc.nextLine().split(" ", 2);
            int repeats = Integer.parseInt(inputs[0]);
            String str = inputs[1];
            for (int j = 0; j < str.length(); j++){
                for(int k = 0; k < repeats; k++) {
                    System.out.print(str.charAt(j));
                }
            }
            System.out.println();
        }
    }

    public void n1009() {
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

    public void n10818() {
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

    public void n2439() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < tries; i++) {
            for (int j = 0; j < tries - i - 1; j++) {
                System.out.printf(" ");
            }
            for (int j = 0; j < i + 1; j++) {
                System.out.printf("*");
            }
            System.out.println("");
        }
    }

    public void n2577() {
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        long num = a * b * c;
        String numStr = Long.toString(num);
        int[] count = new int[10];
        for (String i : numStr.split("")) {
            if (i.equals("0")) {
                count[0]++;
            }
            if (i.equals("1")) {
                count[1]++;
            }
            if (i.equals("2")) {
                count[2]++;
            }
            if (i.equals("3")) {
                count[3]++;
            }
            if (i.equals("4")) {
                count[4]++;
            }
            if (i.equals("5")) {
                count[5]++;
            }
            if (i.equals("6")) {
                count[6]++;
            }
            if (i.equals("7")) {
                count[7]++;
            }
            if (i.equals("8")) {
                count[8]++;
            }
            if (i.equals("9")) {
                count[9]++;
            }
        }
        System.out.println(Integer.toString(count[0]));
        System.out.println(Integer.toString(count[1]));
        System.out.println(Integer.toString(count[2]));
        System.out.println(Integer.toString(count[3]));
        System.out.println(Integer.toString(count[4]));
        System.out.println(Integer.toString(count[5]));
        System.out.println(Integer.toString(count[6]));
        System.out.println(Integer.toString(count[7]));
        System.out.println(Integer.toString(count[8]));
        System.out.println(Integer.toString(count[9]));
    }

    public void n2741() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < tries; i++) {
            System.out.println(Integer.toString(i + 1));
        }
    }

    public void n2920() {
        Scanner sc = new Scanner(System.in);
        String inputs = sc.nextLine().replace(" ", "");
        int num = Integer.parseInt(inputs);
        if (num == 12345678) {
            System.out.printf("ascending");
        } else if (num == 87654321) {
            System.out.printf("descending");
        } else  {
            System.out.printf("mixed");
        }
    }

    public void n10951() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input;
            try {
                input = sc.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }
            String[] i = input.split(" ");
            int x = Integer.parseInt(i[0]);
            int y = Integer.parseInt(i[1]);
            System.out.println(Integer.toString(x + y));
        }
        sc.close();
    }

    public void n10952() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] i = input.split(" ");
            int x = Integer.parseInt(i[0]);
            int y = Integer.parseInt(i[1]);

            if (x == 0 && y == 0) {break;}

            System.out.println(Integer.toString(x + y));
        }
        sc.close();
    }
}