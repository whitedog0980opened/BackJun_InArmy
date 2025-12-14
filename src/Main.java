import java.io.*;
import java.util.*;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 1043
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInput = br.readLine().split(" ");
        int PeopleNum = Integer.parseInt(firstInput[0]);
        int partyNum = Integer.parseInt(firstInput[1]);

        String[] secendInput = br.readLine().split(" ");
        int knownPeopleNum = Integer.parseInt(secendInput[0]);
        HashSet<Integer> knownPeople = new HashSet<>();
        for (int i = 0; i < knownPeopleNum; i++) {
            knownPeople.add(Integer.parseInt(secendInput[i + 1]));
        }
        //init
        LinkedList<Integer>[] graphs = new LinkedList[PeopleNum + 1];
        for (int i = 0; i < PeopleNum + 1; i++) {
            graphs[i] = new LinkedList<>();
        }
        LinkedList<Integer>[] parties = new LinkedList[partyNum]; 
        for (int i = 0; i < partyNum; i++) {
            parties[i] = new LinkedList<>();
        }
        for (int i = 0; i < partyNum; i++) {
            //inputs
            String[] partyInput = br.readLine().split(" ");
            //fill parties
            for (int j = 0; j < partyInput.length; j++) {
                parties[i].add(Integer.parseInt(partyInput[j]));
            }
            //fill graphs
            int partyPeople = parties[i].get(0);
            if (partyPeople == 0) continue; //if empty party
            int host = Integer.parseInt(partyInput[1]);
            for (int j = 2; j < partyPeople + 1; j++) {
                int to = parties[i].get(j);
                graphs[host].add(to);
                graphs[to].add(host);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        for (int i : knownPeople) {
            queue.add(i);
            visited.add(i);
        }

        while (!queue.isEmpty()) {
            int crrPerson = queue.poll();
            for (int i = 0; i < graphs[crrPerson].size(); i++) {
                int nextPerson = graphs[crrPerson].get(i);
                if (visited.contains(nextPerson)) continue;
                else {
                    visited.add(nextPerson);
                    knownPeople.add(nextPerson);
                    queue.add(nextPerson);
                }
            }
        }

        int ableParty = 0;
        for (int i = 0; i < partyNum; i++) {
            boolean able = true;
            for (int j = 1; j < parties[i].size(); j++) {
                int crrPerson = parties[i].get(j);
                if (knownPeople.contains(crrPerson)) able = false;
            }
            if (able) ableParty++;
        }

        bw.write(Integer.toString(ableParty));

        bw.flush();
        bw.close();
    }
}
