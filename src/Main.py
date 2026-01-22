import sys
import heapq
from collections import deque
#from collections import deque
n, k = map(int, sys.stdin.readline().strip().split(" "))
k -= 1

dq = deque([])
for i in range(n):
    dq.append(i + 1)

dq.rotate(-k)
sys.stdout.write("<" + str(dq.popleft()))
dq.rotate(-k)

for i in range(n - 1):
    sys.stdout.write(", " + str(dq.popleft()))
    dq.rotate(-k)
sys.stdout.write(">")
sys.stdout.flush()
