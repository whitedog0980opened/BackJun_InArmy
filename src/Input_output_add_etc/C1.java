import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    // 자바 15이하에서 사용 불가하여 주석처리해둠
    // public void  n10171() {
    //     System.out.printf("""
    //         \\    /\\
    //          )  ( ')
    //         (  /  )
    //          \\(__)|""");
    // }

    // public void n10172() {
    //     System.out.printf("""
    //         |\\_/|
    //         |q p|   /}
    //         ( 0 )""\"\\
    //         |"^"`    |
    //         ||_/=\\\\__|""");
    // }

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
        sc.close();
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
        sc.close();
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

    public void n11654() {
        Scanner sc = new Scanner(System.in);
        char input =sc.nextLine().charAt(0);
        System.out.printf(Integer.toString((int) input));
    }

    public void n2798() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //recieve inputs info
        String[] inputInfo = br.readLine().split(" "); //first input - total line nums
        int cardNum = Integer.parseInt(inputInfo[0]);
        int maxNum = Integer.parseInt(inputInfo[1]);

        //recieve cards
        String[] cardListStr = br.readLine().split(" ");
        int cardListInt[] = new int[cardNum];
        for (int i = 0; i < cardNum; i++) {
            cardListInt[i] = Integer.parseInt(cardListStr[i]);
        }

        int biggest = 0;
        for (int i = 0; i < cardNum - 2; i++) {
            for (int j = i + 1; j < cardNum - 1; j++) {
                for (int k = j + 1; k < cardNum; k++) {
                    int currMax = cardListInt[i] + cardListInt[j] + cardListInt[k];
                    if (currMax <= maxNum && currMax > biggest) {
                        biggest = currMax;
                    }
                }
            }
        }

        bw.write(Integer.toString(biggest));
        bw.close();
    }

    public void n4153() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String[] inputNumStr = br.readLine().split(" ");
            int[] inputNum =     {Integer.parseInt(inputNumStr[0]),  // convert to int
                Integer.parseInt(inputNumStr[1]),
                Integer.parseInt(inputNumStr[2])};

            Arrays.sort(inputNum);
            if (inputNum[0] == 0 || inputNum[1] == 0 || inputNum[2] == 0) break;
            if ( Math.pow(inputNum[0], 2) + Math.pow(inputNum[1], 2) == Math.pow(inputNum[2], 2)) {
                bw.write("right");
            } else {
                bw.write("wrong");
            }
            bw.newLine();
        }
        bw.close();
    }
    
    public void n10874() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int StudentNum = Integer.parseInt(br.readLine());
        List<Integer> retester = new ArrayList<>();

        A : for (int i = 0; i < StudentNum; i++) {
            String[] strScores = br.readLine().split(" ");
            for (int j = 0; j < 10; j++) {
                int correctAnswer = (j) % 5 + 1;
                int score = Integer.parseInt(strScores[j]);
                if (score != correctAnswer) {
                    continue A;
                }
            }
            retester.add(i + 1);
            
        }

        for (int i : retester) {
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }

    public void n30802() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int peopleNum = Integer.parseInt(br.readLine());
        String[] sizesStr = br.readLine().split(" "); 
        int[] sizes = new int[6];
        for (int i = 0; i < 6; i++) {
            sizes[i] = Integer.parseInt(sizesStr[i]);
        } 
        String[] boundsStr = br.readLine().split(" ");
        int tShirtBound = Integer.parseInt(boundsStr[0]);
        int penBound    = Integer.parseInt(boundsStr[1]);

        int toBuyTshirtBount = 0;
        for (int i : sizes) {
            if (i == 0) continue;
            int currentNeedBound = i / tShirtBound;
            if (i % tShirtBound != 0) currentNeedBound++;
            toBuyTshirtBount += currentNeedBound;
        }

        int toBuyPenBound = peopleNum / penBound;
        int toBuyPen = peopleNum % penBound;

        bw.write(String.valueOf(toBuyTshirtBount));
        bw.newLine();
        bw.write(String.valueOf(toBuyPenBound) + " " + String.valueOf(toBuyPen));

        bw.flush();
        bw.close();
    }
    public void n2231() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String inputNumStr = br.readLine();
        int inputNum = Integer.parseInt(inputNumStr);
        int minTryNum =  inputNum - (inputNumStr.length() * 9);
        if (minTryNum < 0) minTryNum = 0;


        for (int i = minTryNum ; i < inputNum; i++) {
            int tryNum = i;
            String tryNumStr = String.valueOf(tryNum);
            for (String j : tryNumStr.split("")) { //  9 9 9 5 9 . 99959 99964 99973 99982 99987 99996
                tryNum += Integer.parseInt(j);
            }
            if (tryNum == inputNum) {
                bw.write(tryNumStr);
                break;
            }
            if (i == inputNum - 1) {
                bw.write("0");
            }
        }

        bw.flush();
        bw.close();
    }

    public void n2775() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testCaseNum = Integer.parseInt(br.readLine());

        for (int i = 0; i < testCaseNum; i++) {
            int floor = Integer.parseInt(br.readLine());
            int roomNum = Integer.parseInt(br.readLine());
            int[][] peopleNumDp = new int[floor + 1][roomNum + 1];
            //init 0 floor peopleNum
            for (int j = 1; j <= roomNum; j++) {
                peopleNumDp[0][j] = j;
            }
            //init roomNum 1 = 1
            for (int j = 1; j <= floor; j++) {
                peopleNumDp[j][1] = 1;
            }

            for (int j = 1; j <= floor; j++) {
                for (int k = 2; k <= roomNum; k++) {
                    peopleNumDp[j][k] = peopleNumDp[j - 1][k] + peopleNumDp[j][k - 1];
                }
            }
            bw.write(Integer.toString(peopleNumDp[floor][roomNum]));
            if (i + 1 != testCaseNum) bw.newLine();
        }


        bw.flush();
        bw.close();
    }

    static void n2869() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strInputs = br.readLine().split(" ");
        int climeByDay = Integer.parseInt(strInputs[0]);
        int slipByDay = Integer.parseInt(strInputs[1]);
        int stickLength = Integer.parseInt(strInputs[2]);
        stickLength -= climeByDay;

        int days = stickLength / (climeByDay - slipByDay) + 1;
        if (stickLength % (climeByDay - slipByDay) != 0) days++;

        bw.write(String.valueOf(days));

        bw.flush();
        bw.close();
    }

    static void n10989() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalInputNum = Integer.parseInt(br.readLine());

        int[] arr = new int[totalInputNum];
        for (int i = 0; i < totalInputNum; i++) {
            int inputNum = Integer.parseInt(br.readLine());
            arr[i] =  inputNum;
        }

        Arrays.sort(arr);
        
        boolean isFirst = true;
        for (int i : arr) {
            if (isFirst) isFirst = false;
            else bw.newLine();
            bw.write(Integer.toString(i));
        }

        bw.flush();
        bw.close();
    }
    static void n14626() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String brokenISBN = br.readLine();
        int brokenWeight = 0;
        int sum = 0;
        for (int i = 0; i < 13; i++) {
            char crr = brokenISBN.charAt(i);
            int weight = 3 - ((i + 1)%2) * 2;
            if (crr == '*') {
                brokenWeight = weight;
            }
            else {
                sum += Character.getNumericValue(crr) * weight;
            }
        }

        int keyRest = sum % 10;
        for (int i = 0; i < 10; i++) {
            int total = sum + i * brokenWeight;
            if (total % 10 == 0) {
                bw.write(Integer.toString(i));
                break;
            }
        
        }

        bw.flush();
        bw.close();
    }

    static void n15829() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inpurNum = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int mod = 1234567891;
        int sum = 0;

        for (int i = 0; i < inpurNum; i++) {
            char crrCH = str.charAt(i);
            int hashNum = (int)crrCH - 96;
            long temp = 1;
            for (int j = 0; j < i; j++) {
                temp *= 31;
            }
            temp %= mod;
            sum += hashNum * temp;
        }

        bw.write(Integer.toString(sum));
        

        bw.flush();
        bw.close();
    }

    static void n28702() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input1 = br.readLine();
        String input2 = br.readLine();
        String input3 = br.readLine();
        int answer = 0;

        if (input1.matches(".*[0-9].*")) {
            answer = Integer.parseInt(input1) + 3;
        } else if (input2.matches(".*[0-9].*")) {
            answer = Integer.parseInt(input2) + 2;
        } else {
            answer = Integer.parseInt(input3) + 1;
        }

        if (answer % 3 == 0) {
            if (answer % 5 == 0) {
                bw.write("FizzBuzz");
            } else {
                bw.write("Fizz");
            }
        } else if (answer % 5 == 0) {
            bw.write("Buzz");
        } else {
            bw.write(Integer.toString(answer));
        }

        bw.flush();
        bw.close();
    }
    static int for24416fibo1 = 0;
    static void n24416() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int fiboMax = Integer.parseInt(br.readLine());

        int[] fiboArray = new int[fiboMax];
        int fibo2 = 0;
        fiboArray[0] = 1;
        fiboArray[1] = 1;
        for (int i = 2; i < fiboMax; i++) {
            fiboArray[i] = fiboArray[i - 1] + fiboArray[i - 2];
            fibo2++;
        }

        fibo24416(fiboMax);

        bw.write(Integer.toString(for24416fibo1 + 1) + " " + Integer.toString(fibo2));

        bw.flush();
        bw.close();
    }
    static int fibo24416(int crr) {
        if (crr == 1 || crr == 2) {
            return 1;
        } 
        else {
            for24416fibo1++;
            return (fibo24416(crr - 1) + fibo24416(crr - 2));
        }
    }
}