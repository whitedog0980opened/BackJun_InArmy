import sys
import heapq
from collections import deque

###1655
r_need, g_need, b_need = map(int, sys.stdin.readline().strip().split())
r_have, g_have, b_have = map(int, sys.stdin.readline().strip().split())
rg_able, gb_able = map(int, sys.stdin.readline().strip().split())

r_need -= r_have
g_need -= g_have
b_need -= b_have
used = 0

# while (r_need <= 0 and g_need <= 0 and b_need <= 0):
if (r_need > 0):
    rg_able -= r_need
    if (rg_able < 0):
        used = -1
        sys.exit(0)
    used += r_need
if (b_need)



###