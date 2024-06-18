import java.util.*;

public class Main {
    public static void main(String[] args) {
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
}