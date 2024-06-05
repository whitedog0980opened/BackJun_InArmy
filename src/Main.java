import Input_output_add_etc.C1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

