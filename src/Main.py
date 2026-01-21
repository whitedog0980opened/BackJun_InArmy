import sys
import heapq

node_nums, graph_nums = map(int, sys.stdin.readline().strip().split(" "))
start_point = int(sys.stdin.readline().strip())

graphs = [list(map(int, sys.stdin.readline().strip().split(" "))) for i in range(graph_nums)]

pq = []
heapq.heappush(pq, (0, start_point))



sys.stdout.write(str(max_value))
sys.stdout.flush()
