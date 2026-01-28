import sys
import heapq
from collections import deque

###2103
node_num = int(sys.stdin.readline().strip())
edges = []
x_max = 0;
y_max = 0;
x_min = 1000;
y_min = 1000;
for i in range(int(node_num / 2)):
    node = list(map(int, sys.stdin.readline().strip().split()))
    # nodes.append(node)
    node2 = list(map(int, sys.stdin.readline().strip().split()))
    
    edge = [abs(node[0] - node2[0]), abs(node[1] - node2[1])]
    edges.append(edge)
    
total = 0;
for i in edges:
    total += i[0] + i[1]

print(total)

sys.stdout.flush()
###