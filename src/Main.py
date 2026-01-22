import sys
import heapq

n1, n2 = map(int, sys.stdin.readline().strip().split(" "))
m1, m2 = map(int, sys.stdin.readline().strip().split(" "))
o1, o2, o3 = map(int, sys.stdin.readline().strip().split(" "))

result = (n1 * n2 + m1 * m2) * (o1 * o2 * o3)
print(result)
