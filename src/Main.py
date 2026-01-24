import sys
import heapq
from collections import deque

###31925
cases = int(sys.stdin.readline().strip())
ables = []
for i in range(cases):
    name, on_school, is_winner, max_price, crr_price = input().strip().split()
    if (on_school == "jaehak" and
        is_winner == "notyet" and
        (int(max_price) > 3 or int(max_price) == -1)):
        ables.append([int(crr_price), name])

ables.sort(key=lambda x: (x[0]))
ables = ables[:10]

able = len(ables)
sys.stdout.write(str(able) + "\n")
ables.sort(key=lambda x: (x[1]))
for able in ables:
    sys.stdout.write(able[1] + "\n")

sys.stdout.flush()
###