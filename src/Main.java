import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //member class
        class Member {
            int age;
            String name;
            Member(int age, String name) {
                this.age = age;
                this.name = name;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // get first line input = member num
        int memberNum = Integer.parseInt(br.readLine());

        //unsorted member list
        List<Member> memberList = new ArrayList<>();
        for (int i = 0; i < memberNum; i++) {
            //get memmber info
            String[] memberInput = br.readLine().split(" ");
            int age = Integer.parseInt(memberInput[0]);
            String name = memberInput[1];

            //create member object
            Member member = new Member(age, name);
            memberList.add(member);
        }

        //sort member list by age
        Collections.sort(memberList, (m1, m2) -> {
            if (m1.age == m2.age) return 0;
            else if (m1.age > m2.age) return 1;
            else return -1;
        });

        //output
        for (int i = 0; i < memberNum; i++) {
            Member member = memberList.get(i);
            bw.write(member.age + " " + member.name + "\n");
        }

        bw.flush();
        bw.close();
    }


}
