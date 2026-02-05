import sys
import heapq
from collections import deque
# regex_len, posts = map(int, sys.stdin.readline().strip().split())
###34545
len_arr = int(sys.stdin.readline().strip())
arr_def = list(map(bool, map(int, sys.stdin.readline().strip().split())))
arr_tar = list(map(bool, map(int, sys.stdin.readline().strip().split())))

num_diff = 0
tar_on = 0
def_on = 0

for i in range(len_arr):
    if (arr_def[i] != arr_tar[i]):
        num_diff += 1;
    if (arr_tar[i]):
        tar_on += 1;
    if (arr_def[i]):
        def_on += 1;
#경우의 수, 모든것을 토글로 제어하기
all_togle = num_diff
#경우의 수2, 모든것을 on 하고 토글로 제어하기
on_and_togle = 1 + len_arr - tar_on
#경우의 수3, 모든것을 off하고 토글로 제어하기
off_and_togle = 2 + tar_on
if (def_on == len_arr):#모든것이 이미 켜저있다면
    on_and_togle -= 1;
    off_and_togle -= 1;
elif (def_on == 0): #모든것이 꺼져있으면
    off_and_togle -= 2;

result = min(min(all_togle, on_and_togle), off_and_togle)
print(result)

sys.stdout.flush();
###