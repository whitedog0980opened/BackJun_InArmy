import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get all input String
        String firstInput = br.readLine();

        //parse numbers and operators
        List<Integer> nums = new ArrayList<>();
        List<String> operators = new ArrayList<>();
        String[] numsStr = firstInput.split("[-]|[+]");
        String[] opsStr = firstInput.split("[0-9]+");
        if (numsStr[0] == "") { //exception for first negative number
            numsStr[0] = "666666"; //dummy number
        }

        for (String s : numsStr) {
            nums.add(Integer.parseInt(s));
        }
        for (String s : opsStr) {
            operators.add(s);
        }

        if (nums.get(0) == 666666) {
            nums.remove(0);
        }
        if (operators.get(0).equals("")) {
            operators.remove(0);
        }

        operators.add("+"); //dummy operator for last number


        //괄호 안에 있는지 여부
        boolean isCovered = false;
        if (operators.size() == nums.size() && operators.get(0) == "-") {
            isCovered = true;
            operators.remove(0);
        }

        int curruntResult = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (isCovered) {
                curruntResult -= nums.get(i);
                if (operators.get(i).equals("-")) isCovered = false;
            } else {
                curruntResult += nums.get(i);
                if (operators.get(i).equals("-")) isCovered = true;
            }
        }

        bw.write(String.valueOf(curruntResult));


   

        bw.flush();
        bw.close();
    }


}
