import sys
import heapq
from collections import deque
#대충, 목적지로 향하는 최단거리를 구하고, 웜홀값은최대치를 구하거나
#아니면, 그냥 음수까지 포함해서 최단거리 구하고, 음수가 나오면 yes 리턴하면 될듯?
#실패, 플로이드 워셜 알고리즘을 사용하는것을 유도함
tc = int(sys.stdin.readline().strip())
for i in range(tc):
    node_num, road_num, worm_num = map(int, sys.stdin.readline().strip().split(" "))
    
    ways = [[] for i in range(node_num + 1)]
    for _ in range(road_num):
        s_point, e_point, time = map(int, sys.stdin.readline().strip().split(" "))
        ways[s_point] = [time, e_point] # index 0 : time for heapq 
        ways[e_point] = [time, s_point]
    for _ in range(worm_num):
        s_point, e_point, time = map(int, sys.stdin.readline().strip().split(" "))
        ways[s_point] = [-time, e_point]
        
    for i in range(node_num):
        for j in range(node_num):
            for k in range(node_num):
                


sys.stdout.write(">")
sys.stdout.flush()
