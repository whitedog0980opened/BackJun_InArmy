import sys
import heapq
from collections import deque

###15786
regex_len, posts = map(int, sys.stdin.readline().strip().split())
regex = sys.stdin.readline().strip()

for i in range(posts):
    is_able = True;
    postit = sys.stdin.readline().strip()
    sliced_postit = postit
    for ch in regex:
        if (ch in sliced_postit):
            slice_point = sliced_postit.index(ch) + 1
            sliced_postit = sliced_postit[slice_point:]
        else:
            is_able = False;
            break;
        
    if (is_able):
        sys.stdout.write("true\n")
    else:
        sys.stdout.write("false\n")
    
sys.stdout.flush();
###