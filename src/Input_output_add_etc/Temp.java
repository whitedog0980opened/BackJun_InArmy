//This class saves temp resorce or stocks

// public class Main {
//     public static void main(String[] args) throws IOException {

//     }
// }


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Temp {
    public void n1167_inProgress() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());

        //from = first index, to = sec index
        int[][] map = new int[nodeNum + 1][nodeNum + 1]; 
        // init map
        for (int i = 0; i < nodeNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int crrNode = Integer.parseInt(inputs[0]);
            int crr = 0;
            while (true) {
                int nextNode = Integer.parseInt(inputs[++crr]);
                if (nextNode == -1) break;
                int length = Integer.parseInt(inputs[++crr]);
                map[crrNode][nextNode] = length;
            }
        }

        // i = crrNode
        for (int i = 1; i < nodeNum + 1; i++) {
            //this loop for find fromNum
            int[] newConnection = new int[nodeNum + 1];
            for (int j = 1; j < nodeNum + 1; j++) {
                int length = map[i][j];
                if (length == 0) continue; //empty 

                //this loop for find toNum and add new connection
                for (int k = 1; k < nodeNum + 1; k++) {
                    if (map[j][k] == 0 || k == i) continue; //empty or fromNum and toNum endPoint is same 
                    if (newConnection[k] == 0 || (newConnection[k] > map[j][k] + map[i][j])) {
                        newConnection[k] = map[j][k] + map[i][j];
                    }
                }
                boolean hasblank = false;
            }
        }
        bw.write("stopPoint");
        //42 인덱스 범위 초가 주의
        //아마 맵 내부의 숫자는 거리를 뜻하는데, 이게 fromNum으로 착각해서 생김
        //즉 첫번째 루프는 fromNum이 2가 아니라 2가 1.3 인덱스에 있으니까 from은 3이 됨


        bw.flush();
        bw.close();
    }
    static void n11724() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] infos = br.readLine().split(" ");
        int nodeNum = Integer.parseInt(infos[0]);
        int graphNum = Integer.parseInt(infos[1]);

        LinkedList<Integer>[] graphs = new LinkedList[nodeNum + 1];
        //init graphs
        for (int i = 1; i < nodeNum + 1; i++) {
            graphs[i] = new LinkedList<Integer>();
        }
        //take buffer and fill graphs
        for (int i = 0; i < graphNum; i++) {
            String[] inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            //no deraction
            graphs[from].add(to);
            graphs[to].add(from);
        }

        //find connections
        boolean[] visited = new boolean[nodeNum + 1];
        int colony = 0; //means independent group num
        for (int i = 1; i < nodeNum + 1; i++) {
            //check visited
            if (visited[i]) continue;

            visited[i] = true;
            colony++;

            // if node is independent
            if (graphs[i].isEmpty()) continue;

            //start of BFS
            Queue<Integer> queue = new LinkedList<>();

            int graphSize = graphs[i].size();
            for (int j = 0; j < graphSize; j++) {
                int next = graphs[i].poll();
                visited[next] = true;
                queue.add(next);
            }

            while (!queue.isEmpty()) {
                int crr = queue.poll();
                if (graphs[crr].isEmpty()) continue;
                int crrGraphSize = graphs[crr].size();
                for (int j = 0; j < crrGraphSize; j++) {
                    int next2 = graphs[crr].poll();
                    if (visited[next2]) continue;
                    visited[next2] = true;
                    queue.add(next2);
                }
            }
        }

        bw.write(Integer.toString(colony));
        bw.flush();
        bw.close();
    }
}