import sys
import heapq
from collections import deque
###25246
origin_str = sys.stdin.readline().strip()
to_find = "aeiou"

target_index = max(origin_str.rfind(c) for c in to_find)
result_str = origin_str[:target_index + 1] + "ntry"
print(result_str)
###