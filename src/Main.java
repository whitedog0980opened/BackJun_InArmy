import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list : 15551
//want to study : hashTable
//need to review = 5430 ! deque

public class Main {
    static class MaxHeap {
        int[] heap;
        int capacity;
        int next = 1;

        MaxHeap(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;

        }

        private void add(int x) {
            // if heap full
            if (next > capacity + 1) {
                return;
            }
            int crr = next;
            next++;
            int parentIndex = crr / 2;
            heap[crr] = x;
            
            while (parentIndex > 0) {
                if (heap[parentIndex] < heap[crr]) {
                    //exchange with parent
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[crr];
                    heap[crr] = temp;
                    //set pr and crr
                    crr = parentIndex;
                    parentIndex = crr / 2;
                } else {
                    break;
                }
            }
        }
        private int pop() {
            // if heap empty
            if (next == 1) {
                return 0;
            }

            int result = heap[1]; // 1 is root
            heap[1] = heap[next - 1]; heap[next - 1] = 0;
            next--;

            int crr = 1;
            int child = 2;
            while (true) {
                if (child > next - 1) {
                    break;
                }
                if (capacity > child && heap[child] < heap[child + 1]) {
                    child++;
                }
                if (heap[crr] < heap[child]) {
                    int temp = heap[crr];
                    heap[crr] = heap[child];
                    heap[child] = temp;

                    crr = child;
                    child = child * 2;
                    continue;
                }
                break;
            }
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int capacity = Integer.parseInt(br.readLine());
        MaxHeap mp = new MaxHeap(capacity);

        for (int i = 0 ; i < capacity; i++) {
            int crrNum = Integer.parseInt(br.readLine());
            if (crrNum == 0) {
                bw.write(Integer.toString(mp.pop()) + "\n");
                continue;
            }
            mp.add(crrNum);
        }
        
        bw.flush();
        bw.close();
    }
}
