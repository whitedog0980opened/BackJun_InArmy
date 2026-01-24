import sys
import heapq

###1753
node_nums, graph_nums = map(int, sys.stdin.readline().strip().split(" "))
start_point = int(sys.stdin.readline().strip())

#graphs line
graphs = [[] for _ in range(node_nums + 1)] #init
for i in range(graph_nums):
    crr_input = list(map(int, sys.stdin.readline().strip().split(" ")))
    graphs[crr_input[0]].append([crr_input[1], crr_input[2]]) 
#minimum time recode
disk = [sys.maxsize for i in range(node_nums + 1)]
disk[start_point] = 0;

pq = []
heapq.heappush(pq, (0, start_point)) # index 0 : weight, index 1 : node

while (not len(pq) == 0):
    crr = heapq.heappop(pq)
    
    if (crr[0] > disk[crr[1]]):
        continue
    
    for graph in graphs[crr[1]]:
        next = [crr[0] + graph[1], graph[0]]
        if (disk[next[1]] > next[0]): #find more small cost
            disk[next[1]] = next[0]
            heapq.heappush(pq, next)
        
for i in range(1, len(disk)):
    if (disk[i] == 9223372036854775807):
        print("INF")
    else:
        print(disk[i])
###

###1865
#대충, 목적지로 향하는 최단거리를 구하고, 웜홀값은최대치를 구하거나
#아니면, 그냥 음수까지 포함해서 최단거리 구하고, 음수가 나오면 yes 리턴하면 될듯?
#실패, 플로이드 워셜 알고리즘을 사용하는것을 유도함
#실패2, 플로이드의 경우 시간초과 (최대 500개인데 연산이 너무 많거나 파이썬이라 그런가봄)
#-> 벨만-포드 적용 어디선가 어디서로 이동할 때, 짧은 경우가 갱신될 경우가 생긴다.
#하지만 일반적인 경우, 한 정점에서의 간선 갯수는 (양방향) n - 1개이다. 자신을 제외한 모든 정점
#따라서 n - 1번째 반복에서 발견되는 새로운 간선은, n - 1개의 간선들이 연결된 최장거리 간선이다.
#더 많이 연결된 간선은, 무한하게 줄어들고 있는 경우의 간선에 해당한다.
#이를 이용해 n번째 루프는 n - 1개를 초과하는 간선을 가진 정점으로, 무한루프를 의심한다.
tc = int(sys.stdin.readline().strip())
for i in range(tc):
    node_num, road_num, worm_num = map(int, sys.stdin.readline().strip().split(" "))
    
    ways = []
    dist = [0] * (node_num + 1)
 
    
    for _ in range(road_num):
        s_point, e_point, time = map(int, sys.stdin.readline().strip().split(" "))
        ways.append([s_point, e_point, time])
        ways.append([e_point, s_point, time])
    for _ in range(worm_num):
        s_point, e_point, time = map(int, sys.stdin.readline().strip().split(" "))
        ways.append([s_point, e_point, -time])
            
    is_able = False    
    for i in range(node_num):
        for s, e, t in ways:
            if (dist[e] > dist[s] + t) :
                dist[e] = dist[s] + t
                if (i == node_num - 1):
                    is_able = True
                    break
                
    if is_able:
        print("YES")
    else:
        print("NO")

sys.stdout.flush()
###