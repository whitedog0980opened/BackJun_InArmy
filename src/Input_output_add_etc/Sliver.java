
import java.util.*;
import java.io.*;

public class Sliver {
    public void n1002() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());
        HashMap<Integer, ArrayList<Double>> datas = new HashMap<Integer, ArrayList<Double>>(tries);

        for (int i = 0; i < tries; i++)  {
            String[] tempStr = sc.nextLine().split(" ");
            ArrayList<Double> tempInt = new ArrayList<Double>(6);
            for (int j = 0; j < 6; j++) {
                tempInt.add(Double.parseDouble(tempStr[j]));
            }
            datas.put(i, tempInt);
        }
        for (int i = 0; i < tries; i++) {
            double x1 = datas.get(i).get(0);
            double y1 = datas.get(i).get(1);
            double r1 = datas.get(i).get(2);
            double x2 = datas.get(i).get(3);
            double y2 = datas.get(i).get(4);
            double r2 = datas.get(i).get(5);

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

            if (distance == 0 && r1 == r2) { // 원의 일치
                System.out.println("-1");
            } else if (distance < Math.abs(r1 - r2)) { // 위치는 일치하나 크기가 다름
                System.out.println("0");
            } else if (distance > (r1 + r2)) { // 원이 서로 멀어서 닿지 않음
                System.out.println("0");
            } else if (distance - (r1 + r2) == 0) { // 외접
                System.out.println("1");
            } else if (distance - Math.abs(r1 - r2) == 0) { //내접
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
    }

    public void n1015() {
        Scanner sc = new Scanner(System.in);
        int inputs = Integer.parseInt(sc.nextLine());
        String AStr[] = sc.nextLine().split(" ");
        //convert to int
        int A[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            A[i] = Integer.parseInt(AStr[i]);
        }
        int[] A2 = A.clone();
        int min = 0;
        int j = 0;
        int Q[] = new int[inputs];
        int B[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            min = 1001;
            for (j = 0; j < inputs; j++) {
                if (min > A[j]) {
                    min = A[j];
                    Q[i] = j;
                }
            }
            B[i] = A[Q[i]];
            A[Q[i]] = 1001;
        }

        int P[] = new int[inputs];
        for (int i = 0; i < inputs; i++) {
            for (j = 0; j < inputs; j++) {
                if (A2[i] == B[j]) {
                    P[i] = j;
                    B[j] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < inputs; i++) {
            System.out.printf(Integer.toString(P[i]) + " ");
        }
    }

//    public void n1080Fail() {
//        Scanner sc = new Scanner(System.in);
//        String[] input1 = sc.nextLine().split(" ");
//        int n = Integer.parseInt(input1[0]);
//        int m = Integer.parseInt(input1[1]);
//
//        int[] listInputs = new int[n * m];
//        int[] listQED = new int[n * m];
//
//        for (int i = 0; i < n; i++) {
//            String[] temp = sc.nextLine().split("");
//            for (int j = 0; j < temp.length; j++) {
//                listInputs[j] = Integer.parseInt(temp[j]);
//            }
//        }
//        for (int i = 0; i < n; i++) {
//            String[] temp = sc.nextLine().split("");
//            for (int j = 0; j < temp.length; j++) {
//                listQED[j] = Integer.parseInt(temp[j]);
//            }
//        }
//
//        int maxTry = (int)Math.pow(2, (n - 2) * (m - 2));
//        int findCase = maxTry + 1;
//        for(int i = 1; i < maxTry; i++) {
//            String BinaryI = Integer.toBinaryString(i);
//            int changeNum = 0;
//            int[] listCheck = new int[n * m];
//            listCheck = listInputs.clone();
//            for (int j = 0; j < BinaryI.length(); j++) { // j = index of BinaryI
//                int jToFilpableArrayInput = j + (int)(j / 7) * 2;
//                boolean flipableSpot =  Character.getNumericValue(BinaryI.charAt(j)) == 1;
//                if (flipableSpot) {
//                    flip(listCheck, jToFilpableArrayInput, m);
//                    changeNum++;
//                }
//            }
//            if (Arrays.equals(listCheck, listQED)) {
//                if(findCase < changeNum) {findCase = changeNum;}
//            }
//        }
//        if (findCase == maxTry + 1) {System.out.printf("-1");}
//        else {System.out.println(Integer.toString(findCase));}
//
//    }
//    public static void flip(int[] array, int x, int m) {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                array[x + i + j * m] = array[x + i + j * m] == 1 ? 0 : 1;
//            }
//        }
//    }

    public void n1080() {
        Scanner sc = new Scanner(System.in);
        String[] input1 = sc.nextLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);

        int[][] listInputs = new int[n][m];
        int[][] listQED = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listInputs[i][j] = Integer.parseInt(temp[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            String[] temp = sc.nextLine().split("");
            for (int j = 0; j < m; j++) {
                listQED[i][j] = Integer.parseInt(temp[j]);
            }
        }
        int changeNum = 0;
        for(int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                if (listInputs[i][j] != listQED[i][j]) {
                    flip(listInputs, i, j);
                    changeNum++;
                }
            }
        }
        if (!Arrays.deepEquals(listInputs, listQED)) {System.out.printf("-1");}
        else {System.out.println(Integer.toString(changeNum));}
    }
    public static void flip(int[][] array, int x, int y) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                array[x + i][y + j] = array[x + i][y + j] == 1 ? 0 : 1;
            }
        }
    }

    public static void n9012() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < tries; i++) {
            String str = sc.nextLine();
            boolean isVPS = false;
            if (isVPS(str)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
    public static boolean isVPS(String str) {
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else {
                return false; // Invalid character
            }
        }
        return stack.isEmpty();
    }

    public static void n10828() {
        Scanner sc = new Scanner(System.in);
        int tries = Integer.parseInt(sc.nextLine());

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tries; i++) {
            String commend = sc.nextLine();
            if (commend.contains("push")) {
                int x = Integer.parseInt(commend.split(" ")[1]);
                stack.push(x);
            } else if (commend.contains("pop")) {
                System.out.printf(Integer.toString(stack.pop()));
            } else if (commend.contains("size")) {
                System.out.printf(Integer.toString(stack.size()));
            } else if (commend.contains("empty")) {
                int isEmpty = stack.isEmpty() ? 1 : 0;
                System.out.printf(Integer.toString(isEmpty));
            } else if (commend.contains("top")) {
                try {
                    System.out.printf(Integer.toString(stack.peek()));
                }catch (EmptyStackException e) {
                    System.out.printf("-1");
                }
            }
        }
    }

    public static void n1003() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tries = Integer.parseInt(bf.readLine());

        // Precompute the counts of 0s and 1s for all Fibonacci numbers up to 40
        int maxN = 40;
        int[] zeroCount = new int[maxN + 1];
        int[] oneCount = new int[maxN + 1];

        // Initial conditions
        zeroCount[0] = 1; // Fibonacci(0) has one 0
        oneCount[0] = 0;  // Fibonacci(0) has zero 1s
        zeroCount[1] = 0; // Fibonacci(1) has zero 0s
        oneCount[1] = 1;  // Fibonacci(1) has one 1

        // Fill in the counts using the known relationships
        for (int i = 2; i <= maxN; i++) {
            zeroCount[i] = zeroCount[i - 1] + zeroCount[i - 2];
            oneCount[i] = oneCount[i - 1] + oneCount[i - 2];
        }

        for (int i = 0; i < tries; i++) {
            int attempt = Integer.parseInt(bf.readLine());
            bw.write(zeroCount[attempt] + " " + oneCount[attempt] + "\n");
        }

        bw.flush();
        bw.close();
        bf.close();
    }

    public static void n2751() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int lineNum = Integer.parseInt(br.readLine()); //first input - total line nums
        Set<Integer> inputTree = new TreeSet<>();
        for(int i = 0; i < lineNum; i++) {
            inputTree.add(Integer.parseInt(br.readLine()));
        }

        for (int i : inputTree) {
            bw.write(Integer.toString(i));
            bw.newLine();
        }
        bw.close();
    }

    public static void n10814() throws IOException {
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

    public static void n1541() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        if (line == null || line.trim().isEmpty()) {
            bw.write("0");
            bw.flush();
            bw.close();
            return;
        }
        line = line.trim();

        List<Integer> nums = new ArrayList<>();
        List<Character> ops = new ArrayList<>();
        StringBuilder cur = new StringBuilder();

        // Parse the input line into nums and ops
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '+' || c == '-') {
                nums.add(Integer.parseInt(cur.toString()));
                cur.setLength(0);
                ops.add(c);
            } else {
                cur.append(c);
            }
        }
        // Add the last number
        if (cur.length() > 0) nums.add(Integer.parseInt(cur.toString()));

        int result = nums.isEmpty() ? 0 : nums.get(0);
        boolean minusSeen = false;
        for (int i = 0; i < ops.size() && i + 1 < nums.size(); i++) {
            char op = ops.get(i);
            int next = nums.get(i + 1);
            if (!minusSeen) {
                if (op == '+') result += next;
                else { minusSeen = true; result -= next; }
            } else {
                result -= next;
            }
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    public static void n10816() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //inputs
        int N = Integer.parseInt(br.readLine());
        String[] cardNumStr = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        String[] checkNumStr = br.readLine().split(" ");

        HashMap<Integer, Integer> cardMap = new HashMap<>(); 
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int cardNum = Integer.parseInt(cardNumStr[i]);
            boolean isNew = !cardMap.containsKey(cardNum);
            if (isNew) {
                cardMap.put(cardNum, 1);
            } else { // 중복시 value 1 증가
                cardMap.put(cardNum, cardMap.get(cardNum) + 1);
            }
        }

        for (int i = 0; i < M; i++) {
            int checkNum = Integer.parseInt(checkNumStr[i]);
            if (cardMap.containsKey(checkNum)) resultList.add(cardMap.get(checkNum));
            else resultList.add(0); 
        }

        for (int i : resultList) {
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
    }

    public static void n10845() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int last = -1; // 마지막 push된 값 저장용 변수

        for (int i = 0; i < inputNum; i++) {
            String command = br.readLine();

            if (command.startsWith("push")) {
                int x = Integer.parseInt(command.split(" ")[1]);
                queue.offer(x);
                last = x; // 마지막 값 갱신
            }
            else if (command.equals("pop")) {
                int result = queue.isEmpty() ? -1 : queue.poll();
                bw.write(String.valueOf(result));
                bw.newLine();
            }
            else if (command.equals("size")) {
                bw.write(String.valueOf(queue.size()));
                bw.newLine();
            }
            else if (command.equals("empty")) {
                bw.write(queue.isEmpty() ? "1" : "0");
                bw.newLine();
            }
            else if (command.equals("front")) {
                int result = queue.isEmpty() ? -1 : queue.peek();
                bw.write(String.valueOf(result));
                bw.newLine();
            }
            else if (command.equals("back")) {
                int result = queue.isEmpty() ? -1 : last;
                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    public static void n11650() throws IOException {
        class Factor {
            public int x;
            public int y;
            Factor (int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int inputNum = Integer.parseInt(br.readLine());

        List<Factor> factors = new ArrayList<>();
        for (int i = 0; i < inputNum; i++) {
            String[] inputs = br.readLine().split(" ");
            Factor factor = new Factor(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
            factors.add(factor);
        }

        Collections.sort(factors, (x, y) -> {
            if (x.x < y.x) return -1;
            else if (x.x == y.x) {
                if (x.y < y.y) return -1;
                else if (x.y == y.y) return 0;
                else return 1;
            } else {
                return 1;
            }
        });

        for (Factor factor : factors) {
            bw.write(factor.x + " " + factor.y + "\n");
        }

        bw.flush();
        bw.close();
    }

    public static void n11866() throws IOException {
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
    public static void n1463() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int x = Integer.parseInt(br.readLine());

        int[] dp = new int[x + 1]; // dp = minumun steps to reach i
        dp[1] = 0;
        for (int i = 2; i <= x; i++) {
            // dp[i - 1]가 minimumTry로부터 1을 뺀 결과라면
            int minimumTry = dp[i - 1] + 1; 
            // dp[i / 3]가 minimumTry로부터 3을 나눈 결과라면
            if (i % 3 == 0) {
                minimumTry = Math.min(minimumTry, dp[i / 3] + 1);
            }
            // dp[i / 2]가 minimumTry로부터 2를 나눈 결과라면
            if (i % 2 == 0) {
                minimumTry = Math.min(minimumTry, dp[i / 2] + 1);
            }
            dp[i] = minimumTry;
        }

        bw.write(String.valueOf(dp[x]));
        bw.flush();
        bw.close();
    }

    public static void n1012() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // 네 방향 이동
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (T-- > 0) {
            String[] s = br.readLine().split(" ");
            int M = Integer.parseInt(s[0]); // 가로 길이
            int N = Integer.parseInt(s[1]); // 세로 길이
            int K = Integer.parseInt(s[2]); // 배추 개수

            boolean[][] map = new boolean[N][M];

            // 배추 위치 입력
            for (int i = 0; i < K; i++) {
                String[] loc = br.readLine().split(" ");
                int x = Integer.parseInt(loc[0]);
                int y = Integer.parseInt(loc[1]);
                map[y][x] = true;
            }

            int count = 0;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[y][x]) {
                        count++;
                        dfs1012(y, x, map, dx, dy, N, M);
                    }
                }
            }

            System.out.println(count);
        }
    }
    static void dfs1012(int y, int x, boolean[][] map, int[] dx, int[] dy, int N, int M) {
        map[y][x] = false;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= 0 && ny < N && nx >= 0 && nx < M && map[ny][nx]) {
                dfs1012(ny, nx, map, dx, dy, N, M);
            }
        }
    }

    static void n1260() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] strInputs = br.readLine().split(" ");
        int V = Integer.parseInt(strInputs[0]); //정점의 갯수
        int lineNum = Integer.parseInt(strInputs[1]);
        int startPoint = Integer.parseInt(strInputs[2]);


        //init graph line list
        LinkedList<Integer>[] graph = new LinkedList[V + 1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new LinkedList<>();
        }

        //get graph lines
        for (int i = 0; i < lineNum; i++) {
            String[] edgeInputs = br.readLine().split(" ");
            int from = Integer.parseInt(edgeInputs[0]);
            int to = Integer.parseInt(edgeInputs[1]);
            graph[from].add(to);
            graph[to].add(from);
        }

        //sort graph lines
        for (int i = 1; i <= V; i++) {
            Collections.sort(graph[i]);
        }

        //DFS
        boolean[] visited = new boolean[V + 1];
        LinkedList<Integer> bfsList = new LinkedList<>();
        bfsList.push(startPoint);
        visited[startPoint] = true;

        bw.write(Integer.toString(startPoint));
        dfs1260(graph, visited, startPoint, bw);
        bw.newLine();
        visited = new boolean[V + 1];
        visited[startPoint] = true;
        bw.write(Integer.toString(startPoint));

        while (!bfsList.isEmpty()) {
            int current = bfsList.pop();
            
            if (!graph[current].isEmpty()) {
                Iterator<Integer> i = graph[current].listIterator();
                while (i.hasNext()) {
                    int temp = i.next();
                    if (!visited[temp]) {
                        visited[temp] = true;
                        bfsList.add(temp);
                        bw.write(" " + Integer.toString(temp));
                    }
                }
            }
        }


        bw.flush();
        bw.close();
    }

    public static void dfs1260(LinkedList<Integer>[] graph, boolean[] visited, int current, BufferedWriter bw) throws IOException{
        if (!graph[current].isEmpty()) {
            for (int i = 0; i < graph[current].size(); i++) {
                int nextCurrent = graph[current].get(i);
                if (!visited[nextCurrent]) {
                    visited[nextCurrent] = true;
                    bw.write(" " + Integer.toString(nextCurrent));
                    dfs1260(graph, visited, nextCurrent, bw);
                }
            }
        }
    }

    public static void n4949() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
        Stack<Integer> stack = new Stack<>();
        boolean checker = true;
        boolean isFirst = true;
        while (true) {
            String str = br.readLine();
            if (str.equals(".")) break;
            checker = true;
            stack.clear();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '(') stack.add(1); // 1 means "("
                else if (ch == ')') {
                    if (stack.isEmpty()) {
                        checker = false;
                        break;
                    }
                    if (stack.pop() != 1) {
                        checker = false;
                        break;
                    }
                }
                else if (ch == '[') stack.add(2); // 2 means "["
                else if (ch == ']') {
                    if (stack.isEmpty()) {
                        checker = false;
                        break;
                    }
                    if (stack.pop() != 2) {
                        checker = false;
                        break;
                    }
                }
            }
            if (!stack.empty()) {
                checker = false;
                stack.clear();
            }

            if (isFirst) {
                isFirst = false;
            } else {
                bw.newLine();
            }
            bw.write(checker ? "yes" : "no");
            
        }
        

        bw.flush();
        bw.close();
    }

    static void n7568() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int peopleNum = Integer.parseInt(br.readLine());

        int[][] bodyData = new int[peopleNum][2];
        for (int i = 0; i < peopleNum; i++) {
            String[] inputStrs = br.readLine().split(" ");
            int weight = Integer.parseInt(inputStrs[0]);
            int height = Integer.parseInt(inputStrs[1]);

            bodyData[i][0] = weight;
            bodyData[i][1] = height;
        }

        int point[] = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            for (int j = 0; j < peopleNum; j++) {
                point[i] = (bodyData[i][0] < bodyData[j][0] 
                            && bodyData[i][1] < bodyData[j][1]
                            ) ? ++point[i] : point[i];
            }
        }

        // //compare score
        // int[] grade = new int[peopleNum];
        // int crrGrade = 0;
        // int sameStack = 0;
        // int max = Integer.MAX_VALUE;
        // for (int i = 0; i < peopleNum; i++) {
        //     int maxI = 0;
        //     int maxCrr = -1;
        //     for (int j = 0; j < peopleNum; j++) {
        //         if (maxCrr < point[j]) {
        //             maxCrr = point[j];
        //             maxI = j;
        //         }
        //     }
        //     if (max > maxCrr) { //if max value down -> grade++
        //         crrGrade += sameStack + 1;
        //         max = maxCrr;
        //         sameStack = 0;
        //     } else {
        //         sameStack++;
        //     }
        //     point[maxI] = -1;
        //     grade[maxI] = crrGrade;
        // }

        boolean isFirst = true;
        for (int i : point) {
            if (isFirst) isFirst = false;
            else bw.write(" ");
            bw.write(Integer.toString(i + 1));
        }
        

        bw.flush();
        bw.close();
    }

    public static void n10773() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int totalInputNum = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < totalInputNum; i++) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                stack.pop();
            } else {
                stack.push(input);
            }
        }

        int sum = 0;
        while (!stack.empty()) {
            sum += stack.pop();
        }
        bw.write(Integer.toString(sum));
        

        bw.flush();
        bw.close();
    }

    public static void n1260SecTry() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStrs = br.readLine().split(" ");
        int nodeNum = Integer.parseInt(inputStrs[0]);
        int lineNum = Integer.parseInt(inputStrs[1]);
        int startNode = Integer.parseInt(inputStrs[2]);

        //input lines
        int[][] lines = new int[lineNum][2];
        for (int i = 0; i < lineNum; i++) {
            String[] lineInputs = br.readLine().split(" ");
            int[] line = {Integer.parseInt(lineInputs[0]), Integer.parseInt(lineInputs[1])};
            lines[i] = line;
        }
        // create graph
        LinkedList<Integer>[] graphs = new LinkedList[nodeNum + 1];
        for (int i = 0; i < nodeNum + 1; i++) {
            graphs[i] = new LinkedList<>();
        }
        for (int[] line : lines) {
            graphs[line[0]].add(line[1]);
            graphs[line[1]].add(line[0]);
        }
        for (LinkedList<Integer> graph : graphs) {
            Collections.sort(graph);
        }
        

        List<Integer> visited = new ArrayList<>();

        boolean isFirst = true;
        dfs_n1260SecTry(graphs, startNode, visited, bw, isFirst);
        bw.newLine();

        //init again
        isFirst = true;
        visited = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(startNode);

        visited.add(startNode);
        bw.write(Integer.toString(startNode));
        while (!queue.isEmpty()) {
            int crrNode = queue.poll();

            for (int i = 0; i < graphs[crrNode].size(); i++) {
                int newNode = graphs[crrNode].get(i);
                if (visited.contains(newNode)) continue;
                else visited.add(newNode);
                queue.add(newNode);
                bw.write(" " + Integer.toString(newNode));
            }
        }

        bw.flush();
        bw.close();
    }
    static void dfs_n1260SecTry(LinkedList<Integer>[] graphs, int crrNode, List<Integer> visited, BufferedWriter bw, boolean isFirst) throws IOException {
        if (visited.contains(crrNode)) {
            return;
        } else {
            visited.add(crrNode);
            if (isFirst) {
                bw.write(Integer.toString(crrNode));
                isFirst = false;
            }
            else bw.write(" " + Integer.toString(crrNode));
        }
        for (int i = 0; i < graphs[crrNode].size(); i++) {
            int nextNode = graphs[crrNode].get(i);
            dfs_n1260SecTry(graphs, nextNode, visited, bw, isFirst);
        }
    }

    static void n11651() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int nodeNum = Integer.parseInt(br.readLine());
        List<Integer[]> nodes = new ArrayList<>();
        for (int i = 0; i < nodeNum; i++) {
            nodes.add(new Integer[2]);
        }

        for (int i = 0; i < nodeNum; i++)  {
            String[] inputStrs = br.readLine().split(" ");
            nodes.get(i)[0] = Integer.parseInt(inputStrs[0]);
            nodes.get(i)[1] = Integer.parseInt(inputStrs[1]);
        }

        Collections.sort(nodes, (x, y) -> {
            if (x[1] > y[1]) return 1;
            else if (x[1] < y[1]) return -1;
            else {
                if (x[0] > y[0]) return 1;
                else if (x[0] < y[0]) return -1;
                else return 0;
            }
        });

        //for mat form
        bw.write(Integer.toString(nodes.get(0)[0]));
        bw.write(" " + Integer.toString(nodes.get(0)[1]));
        for (int i = 1; i < nodeNum; i++) {
            bw.newLine();
            bw.write(Integer.toString(nodes.get(i)[0]));
            bw.write(" " + Integer.toString(nodes.get(i)[1]));
        }

        bw.flush();
        bw.close();
    }
    public static void n1620() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int pokeNum = Integer.parseInt(inputs[0]);
        int queNum = Integer.parseInt(inputs[1]);

        LinkedHashMap<Integer, String> collects = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> collects2 = new LinkedHashMap<>();
        for (int i = 1; i < pokeNum + 1; i++) {
            String name = br.readLine();
            collects.put(i, name);
            collects2.put(name, i);
        }

        boolean isFirst = true;
        for (int i = 0; i < queNum; i++) {
            String question = br.readLine();
            if (question.matches(".*[0-9].*")) {
                int temp = Integer.parseInt(question);
                if (isFirst) {
                    bw.write(collects.get(temp));
                    isFirst = false;
                } else {
                    bw.newLine();
                    bw.write(collects.get(temp));
                }
            } else {
                if (isFirst) {
                    bw.write(Integer.toString(collects2.get(question)));
                    isFirst = false;
                } else {
                    bw.newLine();
                    bw.write(Integer.toString(collects2.get(question)));
                }
            }
        }
    
        bw.flush();
        bw.close();
    }

    static void n1389() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //take first line = userNum, vectorNum
        String[] firstInputLine = br.readLine().split(" ");
        int userNum = Integer.parseInt(firstInputLine[0]);
        int vectorNum = Integer.parseInt(firstInputLine[1]);

        //take vectors
        LinkedList<Integer>[] vectors = new LinkedList[userNum + 1]; //array start for 1
        for (int i = 1; i < userNum + 1; i++) { //init list
            vectors[i] = new LinkedList<Integer>();
        }
        for (int i = 0; i < vectorNum; i++) { //fill vectors
            String[] tempInput = br.readLine().split(" ");
            int from = Integer.parseInt(tempInput[0]);
            int to =  Integer.parseInt(tempInput[1]);

            vectors[from].add(to);
            vectors[to].add(from);
        }
        int[] kelvinNums = new int[userNum + 1];

        for (int i = 1; i < userNum + 1; i++) { // main
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            int[] deepCount = new int[userNum + 1]; //index = userNum, record deep
            Arrays.fill(deepCount, Integer.MIN_VALUE);
            deepCount[i] = 0;
            //deep copy
            LinkedList<Integer>[] copyedVectors = new LinkedList[userNum + 1];
            for (int j = 1; j < userNum + 1; j++) {
                copyedVectors[j] = new LinkedList<>(vectors[j]);
            }

            while (!queue.isEmpty()) {
                int crrNode = queue.remove();
                int deepCounter = deepCount[crrNode] + 1;
                for (int next : copyedVectors[crrNode]) {
                    if (deepCount[next] == Integer.MIN_VALUE) { //isnt Visited?
                        deepCount[next] = deepCounter;
                        queue.add(next);
                    } else {
                        continue;
                    }
                }
            }
            int result = 1; //++1 because start point is -1
            for (int j : deepCount) {
                if (j < 0) continue;
                result += j;
            }

            kelvinNums[i] = result; 
        }

        int min = Integer.MAX_VALUE;
        int result = 0;
        for (int i = userNum; i > 0; i--) {
            if (kelvinNums[i] <= min) {
                min = kelvinNums[i];
                result = i;
            }
        }
        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
    }
    static void n1697() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);

        if (K < N) {
            bw.write(Integer.toString(N - K));
            bw.flush();
            bw.close();
            return;
        }

        Set<Integer> visited = new HashSet<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        visited.add(N);

        while (!queue.isEmpty()) {
            int crr[] = queue.poll();
            int crrN = crr[0];
            int crrDepth = crr[1];

            if (crrN == K) {
                bw.write(Integer.toString(crrDepth));
                bw.flush();
                bw.close();
                return;
            }

            int[] next = {crrN + 1, crrN - 1, crrN * 2};
            for (int nextN : next) {
                if (nextN >= 0 && nextN <= 100000 && !visited.contains(nextN)){
                    visited.add(nextN);
                    queue.add(new int[]{nextN, crrDepth + 1});
                }
            }
        }
        bw.flush();
        bw.close();
    }
    static void n1927() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int trys = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean isFirst = true;

        for (int i = 0; i < trys; i++) {
            int commend = Integer.parseInt(br.readLine());
            if (commend == 0) {
                int output = pq.isEmpty() ? 0 : pq.remove();
                if (isFirst) {
                    bw.write(Integer.toString(output));
                    isFirst = false;
                }
                else {
                    bw.newLine();
                    bw.write(Integer.toString(output));
                }
            }
            else {
                pq.add(commend);
            }

        }

        
        bw.flush();
        bw.close();
    }
    static void n1764() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        HashSet<String> unHeard = new HashSet<>();
        TreeSet<String> unHeardLooked = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            unHeard.add(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            String unLooked = br.readLine();
            if (unHeard.contains(unLooked)) {
                unHeardLooked.add(unLooked);
            }

        }
        bw.write(Integer.toString(unHeardLooked.size()));
        for (String i : unHeardLooked) {
            bw.newLine();
            bw.write(i);
        }

        
        bw.flush();
        bw.close();
    }
    static void n2178() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] inputs = br.readLine().split(" ");
        int rowNum = Integer.parseInt(inputs[0]);
        int colNum = Integer.parseInt(inputs[1]);
        int targerY = rowNum -1;
        int targetX = colNum -1;
        boolean[][] map = new boolean[rowNum][colNum];

        for (int i = 0; i < rowNum; i++) {
            String[] rowInputs = br.readLine().split("");

            for (int j = 0; j < colNum; j++) {
                boolean isRoad = Integer.parseInt(rowInputs[j]) == 1;
                if (isRoad) map[i][j] = true;
            }
        }

        //BFS
        Queue<int[]> queue = new LinkedList<>();
        int[] startPoint = {0, 0, 0}; //x ,y ,depth
        queue.add(startPoint);
        HashSet<int[]> visited = new HashSet<>();
        visited.add(startPoint);

        int[] dx = {1, -1, 0 ,0};
        int[] dy = {0, 0, 1, -1};
        int maxDepth = 0;

        first: while (!queue.isEmpty()) {
            int[] crrPoint = queue.poll();

            for (int i = 0; i < 4; i++) {
                int[] nextPoint = {crrPoint[0] + dx[i], crrPoint[1] + dy[i], crrPoint[2] + 1};
                if (nextPoint[1] < 0 || nextPoint[1] >= rowNum ||
                    nextPoint[0] < 0 || nextPoint[0] >= colNum ) {
                    continue;
                }
                boolean isVisited = visited.stream().anyMatch(arr -> arr[0] == nextPoint[0] && arr[1] == nextPoint[1]);
                if (isVisited) continue;

                int[] target = {targetX, targerY};
                int[] nextXY = {nextPoint[0], nextPoint[1]};
                if(Arrays.equals(nextXY, target)) {
                    maxDepth = nextPoint[2];
                    break first;
                }

                if(map[nextPoint[1]][nextPoint[0]]) {
                    queue.add(nextPoint);
                    visited.add(nextPoint);
                }
            }
        }

        bw.write(Integer.toString(maxDepth + 1));
        
        bw.flush();
        bw.close();
    }
    static void n2579() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int stairNum = Integer.parseInt(br.readLine());

        int[] stairs = new int[stairNum];
        int[][] stairsMax = new int[stairNum][2];
        for (int i = 0; i < stairNum; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if (stairNum == 1) {
            bw.write(Integer.toString(stairs[0]));
            bw.flush();
            bw.close();
            return;
        }


        stairsMax[0][0] = stairs[0];
        stairsMax[1][0] = stairs[1];
        stairsMax[1][1] = stairs[0] + stairs[1];

        for (int i = 2; i < stairNum; i++)  {
            stairsMax[i][1] = stairsMax[i - 1][0] + stairs[i];
            stairsMax[i][0] =Math.max(stairsMax[i - 2][0], stairsMax[i - 2][1]) + stairs[i];
        }

        //int result = dfs(stairs, stairNum, 0, 0, -1);
        int result = Math.max(stairsMax[stairNum - 1][0], stairsMax[stairNum - 1][1]);
        bw.write(Integer.toString(result));

        bw.flush();
        bw.close();
    }
    static void n2606() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int computerNum = Integer.parseInt(br.readLine());
        int graphNum = Integer.parseInt(br.readLine());
        LinkedList<Integer>[] graphs = new LinkedList[computerNum + 1];
        for (int i = 1; i < computerNum + 1; i++) { //init LinkedList Array
            graphs[i] = new LinkedList<Integer>();
        }

        for (int i = 1; i < graphNum + 1; i++) {
            String[] inputs = br.readLine().split(" ");
            int from = Integer.parseInt(inputs[0]);
            int to = Integer.parseInt(inputs[1]);
            graphs[from].add(to);
            graphs[to].add(from);
        }

        boolean[] visited = new boolean[computerNum + 1];
        visited[1] = true;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        int infected = 0;
        while (!queue.isEmpty()) {
            int crrPC = queue.poll();
            for (int to : graphs[crrPC]) {
                if (visited[to]) continue;
                else {
                    queue.add(to);
                    visited[to] = true;
                    infected++;
                }
            }
        }

        bw.write(Integer.toString(infected));


        
        bw.flush();
        bw.close();
    }
    static void n2630() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int N = Integer.parseInt(br.readLine());
        int foldableNum = Integer.numberOfTrailingZeros(N); //2의 몇승인가
        boolean[][] paper = new boolean[N][N];
        boolean[][] isChecked = new boolean[N][N];
        int falseNum = 0;
        int trueNum = 0;

        boolean allSame1 = true; //all false
        boolean allSame2 = true; //all true
        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                boolean input = Integer.parseInt(inputLine[j]) == 1 ? false : true;
                paper[j][i] = input;
                if (input) allSame1 = false;
                else allSame2 = false; 
            }
        }

        if (allSame1) {
            bw.write("0\n1");
            bw.flush();
            bw.close();
            return;
        } 
        if (allSame2) {
            bw.write("1\n0");
            bw.flush();
            bw.close();
            return;
        }

        for (int i = 0; i < foldableNum; i++) {
            int splitUnit = N / (1 << (i + 1));
            boolean[] isIno = new boolean[1 << ((i + 1) * 2)];
            Arrays.fill(isIno, true);
            for (int j = 0; j < (1 << ((i + 1) * 2)); j++) {
                int startPointX = (j % (1 << (i + 1))) * splitUnit;
                int startPointY = (j / (1 << (i + 1))) * splitUnit; 
                if (isChecked[startPointX][startPointY]) continue; //check isCounted paper

                boolean color = paper[startPointX][startPointY];
                for (int k = 0; k < splitUnit * splitUnit; k++) {
                    int crrX = startPointX + k % splitUnit;
                    int crrY = startPointY + k / splitUnit;
                    if (paper[crrX][crrY] != color)  {
                        isIno[j] = false;
                        break;
                    }
                }
                //marking if counted
                if (isIno[j]) {// all paper was same color
                    if (color) trueNum++;
                    else falseNum++;
                    for (int k = 0; k < Math.pow(splitUnit, 2); k++) {
                        int crrX = startPointX + k % splitUnit;
                        int crrY = startPointY + k / splitUnit;
                        isChecked[crrX][crrY] = true;
                    }
                }

            }
        }

        bw.write(Integer.toString(trueNum) + "\n" + Integer.toString(falseNum));

        bw.flush();
        bw.close();
    }
    static void n2667() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        int N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        boolean[][] visited = new boolean[N][N];
        int houseNum = 0;
        ArrayList<Integer> houseSizes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] inputLine = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(inputLine[j]) == 1 ? true : false; // map[y][x] 
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int result = findHouse2667(j, i, map, N, visited);
                if (result >= 1) {
                    houseNum++;
                    houseSizes.add(result);
                }

            }
        }
        Collections.sort(houseSizes);
        bw.write(Integer.toString(houseNum));
        for (int i = 0; i < houseSizes.size(); i++) {
            bw.newLine();
            bw.write(Integer.toString(houseSizes.get(i)));
        }


        bw.flush();
        bw.close();
    }
    static int findHouse2667(int crrX, int crrY, boolean[][] map, int N, boolean[][] visited) {
        if (!map[crrY][crrX] || visited[crrY][crrX]) {
            return 0;
        }
        int result = 1;
        visited[crrY][crrX] = true;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nextX = crrX + dx[i];
            int nextY = crrY + dy[i];
            boolean borderCheck = nextX >= 0 && nextX < N && nextY >= 0 && nextY < N;
            if (borderCheck && map[nextY][nextX] && !visited[nextY][nextX]) {
                result += findHouse2667(nextX, nextY, map, N, visited);
            } else {
                continue;
            }
        }
        return result;
    }
    static void n2805() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //get inputs
        String[] firstInput = br.readLine().split(" ");
        int treeNum = Integer.parseInt(firstInput[0]);
        long needQuantity = Integer.parseInt(firstInput[1]);
        String[] treesInput = br.readLine().split(" ");
        int[] trees = new int[treeNum];
        int longestTree = 0;
        for (int i = 0; i < treeNum; i++) {
            trees[i] = Integer.parseInt(treesInput[i]);
            if (longestTree < trees[i]) longestTree = trees[i];
        }

        long start = 0;
        long end = longestTree;
        long result= 0;
        while (start <= end) {
            long mid = start + (end - start) / 2;
            long collected = 0;
            for (int tree : trees) { //counting tree collected
                if (tree > mid) collected += tree - mid;
            }
            long crrOverCollected = collected - needQuantity;
            if (collected >= needQuantity) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        bw.write(Long.toString(result));
        bw.flush();
        bw.close();
    }
}


