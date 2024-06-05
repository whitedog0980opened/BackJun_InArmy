package Input_output_add_etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
}
