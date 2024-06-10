import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.trim(); //

        int len = str.split(" ").length;
        if (str.equals("")){len = 0;};

        System.out.printf(Integer.toString(len));
    }
}
