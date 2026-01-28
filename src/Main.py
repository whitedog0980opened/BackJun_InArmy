import sys
import heapq
from collections import deque

###2103
node_num = int(sys.stdin.readline().strip())
# nodes_x = []
# nodes_y = []
nodes = []
perimeter = 0;

for i in range(node_num):
    node = list(map(int, sys.stdin.readline().strip().split()))
    nodes.append(node)
    # nodes_y.append(node[1])
    
nodes.sort()
for i in range(1, node_num, 2):
    nodes[i:i+2] = sorted(nodes[i:i+2], key=lambda p: p[1])

# for node in nodes:
#     nodes_x.append(node[0])
#     nodes_y.append(node[1])

for i in range(node_num):
    x1, y1 = nodes[i]
    x2, y2 = nodes[(i + 1) % node_num]
    perimeter += abs(x1 - x2) + abs(y1 - y2)
        

print(perimeter)

sys.stdout.flush()
###