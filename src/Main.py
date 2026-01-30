import sys
import heapq
from collections import deque

###21867
alph_num = int(sys.stdin.readline().strip())
origin_str = sys.stdin.readline().strip()
to_find = "JAV"

is_found = False

for i in range(alph_num):
    if (origin_str[i] in to_find):
        continue;
    sys.stdout.write(origin_str[i])
    is_found = True

if (not is_found):
    print("nojava")
###