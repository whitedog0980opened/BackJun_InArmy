import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551 1916,1446(다익스트라) 1504 (gold)
//want to study : hashTable
//need to review = 5430 ! deque
//https://lmarena.ai/ko

//1918
// split 정규식보다, Matcher 라는 클래스를 이용해보길 권장. (복잡한 수식이기때문)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //분리대상 : 알파벳, +-*/, (). 알파벳과 나머지를 이분법적으로 보고 Lookahead, Lookbehind로 분리
        String regex = "(?<=[+\\-*/%^()])|(?=[+\\-*/%^()])";
        String ori = br.readLine();
        String[] splited = ori.split(regex);

        //배열을 2차원 리스트 * 배열로 바꿈. 배열 = {우선순위, 연산자종류 혹은 숫자}
        int parenStack = 0;
        ArrayList<int[]> pSplited = new ArrayList<>();
        for (int i = 0; i < splited.length; i++) {
            String crr = splited[i];
            if (crr.matches("[a-z|A-Z]+")) {
                int[] temp = {Integer.MIN_VALUE, (int) crr.charAt(0)}; //MinValue means its numberic 
                pSplited.add(temp);
            }
            //우선순위 점수 : 기본점수100 + 괄호 중첩 갯수 * 200 + */추가점수1000 - 인덱스
            else if (crr.equals("+")) {
                int[] temp = {100 + parenStack * 200 - i, 1}; // 1 means +
                pSplited.add(temp);
            }
            else if (crr.equals("-")) {
                int[] temp = {100 + parenStack * 200 - i, 2}; // 2 means -
                pSplited.add(temp);
            }
            else if (crr.equals("*")) {
                int[] temp = {100 + parenStack * 200 + 100 - i, 3}; // 3 means *
                pSplited.add(temp);
            }
            else if (crr.equals("/")) {
                int[] temp = {100 + parenStack * 200 + 100 - i, 4}; // 4 means /
                pSplited.add(temp);
            }
            else if (crr.equals("(")) {
                parenStack++;
            }
            else if (crr.equals(")")) {
                parenStack--;
            }
        }

        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int[] crr : pSplited) {
            //이번것이 숫자면
            if (crr[0] == Integer.MIN_VALUE) {
                sb.append(Character.toString((char) crr[1]));
            }
            //연산자라면
            else {
                //스택이 빌경우, 값 스택에 추가
                if (stack.isEmpty()) {
                    stack.add(crr);
                    continue;
                }
                //이번게 스택꺼보다 작으면, 큰게 나올때까지 스택에 있는거 출력
                int[] prev = stack.pop();
                while (prev[0] > crr[0]) {
                    if (prev[1] == 1) sb.append("+");
                    else if (prev[1] == 2) sb.append("-");
                    else if (prev[1] == 3) sb.append("*");
                    else sb.append("/");

                    // 만약 중간에 텅텅 비었다면, 이번꺼를 스택에 넣고 반복 끝
                    if (stack.isEmpty()) {
                        stack.add(crr);
                        break;
                    }
                    prev = stack.pop();
                }
                //이번게 스택꺼보다 크면 둘다 스택에 넣기
                if (prev[0] < crr[0]) {
                    stack.add(prev);
                    stack.add(crr);
                    continue;
                }
            }
        }
        // 스택 털기
        while (!stack.isEmpty()) {
            int[] crr = stack.pop();
            if (crr[1] == 1) sb.append("+");
            else if (crr[1] == 2) sb.append("-");
            else if (crr[1] == 3) sb.append("*");
            else sb.append("/");
        }

        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
    }

}