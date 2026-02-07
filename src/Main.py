import sys
import math
import heapq
from collections import deque
# regex_len, posts = map(int, sys.stdin.readline().strip().split())
###31534
a, b, h = map(float, sys.stdin.readline().strip().split())
if (a == b):
    result = -1; 
else:
    h = math.sqrt(abs(b - a) ** 2 + h ** 2)

    l = h / abs(a - b)
    al = a * l
    bl = b * l
    ar = (al ** 2) * math.pi
    br = (bl ** 2) * math.pi
    result = round(abs(ar - br), 6)
print(result)
sys.stdout.flush();
###