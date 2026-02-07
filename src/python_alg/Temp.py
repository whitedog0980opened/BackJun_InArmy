import sys
import heapq
from collections import deque
###30804
#-1 -> left, 1 -> right
fruit_num = int(sys.stdin.readline().strip())
fruit_arr = list(map(int, sys.stdin.readline().split()))


max_num = 0
fruits_count = {} # 과일 종류별 개수를 담을 딕셔너리
left = 0
right = 0


while right < fruit_num:
    current_fruit = fruit_arr[right]
    fruits_count[current_fruit] = fruits_count.get(current_fruit, 0) + 1
    
    while len(fruits_count) > 2:
        left_fruit = fruit_arr[left]
        fruits_count[left_fruit] -= 1
        if fruits_count[left_fruit] == 0:
            del fruits_count[left_fruit] 
        left += 1
    
    max_num = max(max_num, right - left + 1)
    
    right += 1
print(max_num)
###