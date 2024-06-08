package Input_output_add_etc;

import java.util.*;

public class Sliver {
    public void n1002() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        HashMap<Integer, ArrayList<Double>> datas = new HashMap<Integer, ArrayList<Double>>(tries);

        for (int i = 0; i < tries; i++)  {
            String[] tempStr = sc.nextLine().split(" ");
            ArrayList<Double> tempInt = new ArrayList<Double>(6);
            for (int j = 0; j < 6; j++) {
                tempInt.add(Double.parseDouble(tempStr[j]));
            }
            datas.put(i, tempInt);
        }
        for (int i = 0; i < tries; i++) {
            double x1 = datas.get(i).get(0);
            double y1 = datas.get(i).get(1);
            double r1 = datas.get(i).get(2);
            double x2 = datas.get(i).get(3);
            double y2 = datas.get(i).get(4);
            double r2 = datas.get(i).get(5);

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

            if (distance == 0 && r1 == r2) { // 원의 일치
                System.out.println("-1");
            } else if (distance < Math.abs(r1 - r2)) { // 위치는 일치하나 크기가 다름
                System.out.println("0");
            } else if (distance > (r1 + r2)) { // 원이 서로 멀어서 닿지 않음
                System.out.println("0");
            } else if (distance - (r1 + r2) == 0) { // 외접
                System.out.println("1");
            } else if (distance - Math.abs(r1 - r2) == 0) { //내접
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
    }

    public void n1015() {
        Scanner sc = new Scanner(System.in);
        int inputs = Integer.parseInt(sc.nextLine());
        String AStr[] = sc.nextLine().split(" ");
        //convert to int
        int A[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            A[i] = Integer.parseInt(AStr[i]);
        }
        int[] A2 = A.clone();
        int min = 0;
        int j = 0;
        int Q[] = new int[inputs];
        int B[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            min = 1001;
            for (j = 0; j < inputs; j++) {
                if (min > A[j]) {
                    min = A[j];
                    Q[i] = j;
                }
            }
            B[i] = A[Q[i]];
            A[Q[i]] = 1001;
        }

        int P[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            for (j = 0; j < inputs; j++) {
                if (A2[i] == B[j]) {
                    P[i] = j;
                    B[j] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < inputs; i++) {
            System.out.printf(Integer.toString(P[i]) + " ");
        }
    }

//    public void n1080Fail() {
//        Scanner sc = new Scanner(System.in);
//        String[] input1 = sc.nextLine().split(" ");
//        int n = Integer.parseInt(input1[0]);
//        int m = Integer.parseInt(input1[1]);
//
//        int[] listInputs = new int[n * m];
//        int[] listQED = new int[n * m];
//
//        for (int i = 0; i < n; i++) {
//            String[] temp = sc.nextLine().split("");
//            for (int j = 0; j < temp.length; j++) {
//                listInputs[j] = Integer.parseInt(temp[j]);
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            String[] temp = sc.nextLine().split("");
//            for (int j = 0; j < temp.length; j++) {
//                listQED[j] = Integer.parseInt(temp[j]);
//            }
//        }
//
//        int maxTry = (int)Math.pow(2, (n - 2) * (m - 2));
//        int findCase = maxTry + 1;
//        for(int i = 1; i < maxTry; i++) {
//            String BinaryI = Integer.toBinaryString(i);
//            int changeNum = 0;
//            int[] listCheck = new int[n * m];
//            listCheck = listInputs.clone();
//            for (int j = 0; j < BinaryI.length(); j++) { // j = index of BinaryI
//                int jToFilpableArrayInput = j + (int)(j / 7) * 2;
//                boolean flipableSpot =  Character.getNumericValue(BinaryI.charAt(j)) == 1;
//                if (flipableSpot) {
//                    flip(listCheck, jToFilpableArrayInput, m);
//                    changeNum++;
//                }
//            }
//            if (Arrays.equals(listCheck, listQED)) {
//                if(findCase < changeNum) {findCase = changeNum;}
//            }
//        }
//        if (findCase == maxTry + 1) {System.out.printf("-1");}
//        else {System.out.println(Integer.toString(findCase));}
//
//    }
//    public static void flip(int[] array, int x, int m) {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                array[x + i + j * m] = array[x + i + j * m] == 1 ? 0 : 1;
//            }
//        }
//    }

    public void n1080() {
        Scanner sc = new Scanner(System.in);
        String[] input1 = sc.nextLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        int[][] listInputs = new int[n][m];
        int[][] listQED = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listInputs[i][j] = Integer.parseInt(temp[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listQED[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int changeNum = 0;
        for(int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (listInputs[i][j] != listQED[i][j]) {
                    flip(listInputs, i, j);
                    changeNum++;
                }
            }
        }
        if (!Arrays.deepEquals(listInputs, listQED)) {System.out.printf("-1");}
        else {System.out.println(Integer.toString(changeNum));}
    }
    public static void flip(int[][] array, int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[x + i][y + j] = array[x + i][y + j] == 1 ? 0 : 1;
            }
        }
    }

    public static void n9012() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < tries; i++) {
            String str = sc.nextLine();
            boolean isVPS = false;
            if (isVPS(str)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    public static boolean isVPS(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else {
                return false; // Invalid character
            }
        }
        return stack.isEmpty();
    }
}
