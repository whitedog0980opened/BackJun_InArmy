import sys
import heapq

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

sys.stdout.flush()
