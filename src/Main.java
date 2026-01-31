import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
//https://testcase.ac/problems/1764
//https://www.acmicpc.net/step/16
//want to solve list :
//want to study : hashTable, 이분매칭 ,비트마스크 + dp, 그리디
//need to review = 5430 ! deque
//https://lmarena.ai/ko

public class Main {
    //2103
    private static class MaxHeap1655 {
        int[] heap;
        int capacity;
        int next = 1;
        MaxHeap1655(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
        }

        private void add(int value) {
            if (next > capacity + 1) return;
            int crr = next;
            next += 1;

            heap[crr] = value;
            int parentIndex = crr/2;
            while (parentIndex > 0) {
                if (heap[parentIndex] < heap[crr]) {
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = parentIndex;
                    parentIndex /= 2;
                } else break;
            }
        }
        private int pop() {
            if (next == 1) return Integer.MIN_VALUE;

            int result = heap[1];
            heap[1] = heap[next - 1];
            next--;
            int crr = 1;
            int childIndex = crr * 2;
            while (childIndex < next - 1) {
                if (childIndex > next - 1) {
                    break;
                }
                if (capacity > childIndex && heap[childIndex] < heap[childIndex + 1]) {
                    childIndex++;
                }
                if (heap[childIndex] > heap[crr]) {
                    int temp = heap[childIndex];
                    heap[childIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = childIndex;
                    childIndex *= 2;
                } else break;
            }
            return result;
        }
        private int peek() {
            if (next == 1) return -99999; //error code
            return heap[1];
        }
    }
    private static class MinHeap1655 {
        int[] heap;
        int capacity;
        int next = 1;
        MinHeap1655(int capacity) {
            heap = new int[capacity + 1];
            this.capacity = capacity;
        }

        private void add(int value) {
            if (next > capacity + 1) return;
            int crr = next;
            next += 1;

            heap[crr] = value;
            int parentIndex = crr/2;
            while (parentIndex > 0) {
                if (heap[parentIndex] > heap[crr]) {
                    int temp = heap[parentIndex];
                    heap[parentIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = parentIndex;
                    parentIndex /= 2;
                } else break;
            }
        }
        private int pop() {
            if (next == 1) return Integer.MIN_VALUE;

            int result = heap[1];
            heap[1] = heap[next - 1];
            next--;
            int crr = 1;
            int childIndex = crr * 2;
            while (childIndex < next - 1) {
                if (childIndex > next - 1) {
                    break;
                }
                if (capacity > childIndex && heap[childIndex] > heap[childIndex + 1]) {
                    childIndex++;
                }
                if (heap[childIndex] < heap[crr]) {
                    int temp = heap[childIndex];
                    heap[childIndex] = heap[crr];
                    heap[crr] = temp;

                    crr = childIndex;
                    childIndex *= 2;
                } else break;
            }
            return result;
        }
        private int peek() {
            if (next == 1) return -99999; //error code
            return heap[1];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        MaxHeap1655 maxH = new MaxHeap1655(num);
        MinHeap1655 minH = new MinHeap1655(num);
        for (int i = 0; i < num; i++) {
            int crr = Integer.parseInt(br.readLine());

            if (maxH.capacity == minH.capacity) {
                maxH.add(crr);
            } else {
                minH.add(crr);
            }

            if (maxH.next > 1 && minH.next > 1 && maxH.peek() > minH.peek()) {
                int maxVal = maxH.pop();
                int minVal = minH.pop();
                maxH.add(minVal);
                minH.add(maxVal);
            }
        
            bw.write(Integer.toString(maxH.peek()) + "\n");
        }

        bw.flush();
        bw.close();;
    }
}