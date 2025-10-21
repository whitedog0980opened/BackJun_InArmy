import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        //순열 생성
        List<Integer> permutation = new LinkedList<>();

        List<Integer> answerPmt = new LinkedList<>(); //요세푸스 순열
        for (int i = 0; i < n; i++) {
            permutation.add(i + 1);
        }

        //요세푸스 문제에 맞추어 출력
        Iterator<Integer> pmtIterater = permutation.iterator();
        while (true) {
            if (permutation.isEmpty()) break;
            for (int i = 0; i < k; i++) {
                if (!pmtIterater.hasNext()) { //반복이 끝났을 겨우
                    pmtIterater = permutation.iterator(); //새로운 반복자 적용
                }
                if (i + 1 == k) {
                    answerPmt.add(pmtIterater.next());
                    pmtIterater.remove();
                    continue;
                }
                pmtIterater.next();
            }
        }

        // 출력 인덱스
        bw.write("<");
        for (int i = 0; i < n - 1; i++) {
            bw.write(String.valueOf(answerPmt.get(i)) + ", ");
        }
        bw.write(String.valueOf(answerPmt.get(n - 1)) + ">");


        bw.flush();
        bw.close();
    }
}
