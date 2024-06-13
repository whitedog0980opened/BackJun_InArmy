import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Receive Test Case Num
        Scanner sc = new Scanner(System.in);
        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < testCase; i++) {
            String[] nums = sc.nextLine().split(" ");
            buildTimeCalculator(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), sc);
        }
    }

    public static void buildTimeCalculator(int buildsNum, int sequencesNum, Scanner sc) {
        // Receive required times for build
        String[] requireTimeStrs = sc.nextLine().split(" ");
        int[] requireTimes = new int[buildsNum + 1]; // +1 Because Number of Building is start counting from 1
        for (int i = 0; i < buildsNum; i++) {
            requireTimes[i + 1] = Integer.parseInt(requireTimeStrs[i]);
        }

        // Receive sequence of buildings
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= buildsNum; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < sequencesNum; i++) {
            String[] temp = sc.nextLine().split(" ");
            int X = Integer.parseInt(temp[0]);
            int Y = Integer.parseInt(temp[1]);
            adjList.get(Y).add(X); // Storing in reverse order for backtracking
        }

        // Receive target building
        int targetBuild = Integer.parseInt(sc.nextLine());

        // Find the maximum time to build the target building
        boolean[] visited = new boolean[buildsNum + 1];
        int[] dp = new int[buildsNum + 1];
        Arrays.fill(dp, -1);
        int result = findMaxTime(targetBuild, adjList, requireTimes, dp, visited);
        System.out.println(result);
    }

    public static int findMaxTime(int current, ArrayList<ArrayList<Integer>> adjList, int[] requireTimes, int[] dp, boolean[] visited) {
        if (dp[current] != -1) {
            return dp[current];
        }
        if (adjList.get(current).isEmpty()) {
            dp[current] = requireTimes[current];
            return dp[current];
        }
        int maxTime = 0;
        for (int prev : adjList.get(current)) {
            if (!visited[prev]) {
                visited[prev] = true;
                maxTime = Math.max(maxTime, findMaxTime(prev, adjList, requireTimes, dp, visited));
                visited[prev] = false;
            }
        }
        dp[current] = maxTime + requireTimes[current];
        return dp[current];
    }
}