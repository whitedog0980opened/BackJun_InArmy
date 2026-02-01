import sys
import heapq
from collections import deque

###1655
def binary_seek1655(question, nums):
    high = len(nums) - 1
    low = 0;
    res = -1;
    while high >= low:
        mid = (high + low) //2
        if (question == nums[mid]):
            res = mid
            high = mid - 1
        elif (question > nums[mid]):
            low = mid + 1
        else:
            high = mid - 1;
    return res;
num, q_num = map(int, sys.stdin.readline().strip().split())
nums = []
for i in range(num):
    nums.append(int(sys.stdin.readline().strip()))
nums.sort()
for i in range(q_num):
    question = int(sys.stdin.readline().strip())
    sys.stdout.write(str(binary_seek1655(question, nums)) + "\n")
###