import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] inputStr = sc.nextLine().split(" ");
        int[] inputs = new int[5];
        for (int i = 0; i < 5; i++) {
            double temp = Math.pow(Integer.parseInt(inputStr[i]), 2);
            inputs[i] = (int) temp;
        }
        int result = (inputs[0] + inputs[1] + inputs[2] + inputs[3] + inputs[4]) % 10;
        System.out.printf(Integer.toString(result));
    }
}