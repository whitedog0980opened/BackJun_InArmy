import sys
import heapq
from collections import deque

###2526
base, divieder = map(int, sys.stdin.readline().strip().split())
recode = []
crr_base = base
while True:
    if (crr_base in recode):
        last_repeat = recode.index(crr_base)
        print(len(recode) - last_repeat)
        break
        
    recode.append(crr_base)
    multed = crr_base * base
    crr_base = multed % divieder

sys.stdout.flush()
###