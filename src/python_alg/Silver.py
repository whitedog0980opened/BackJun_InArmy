import sys
import heapq
import math
### 1793
while True:
    n = sys.stdin.readline().strip();
    if not n:
        break;
    else:
        n = int(n)
    
    arr = [1, 1, 3, 5, 11]
    if n < 5:
        sys.stdout.write(str(arr[n]) + "\n")
        sys.stdout.flush()
        continue;
    for i in range(5, n + 1):
        next_val = (arr[i-2] * 2 + arr[i - 1]);
        arr.append(next_val)
    sys.stdout.write(str(arr[n]) + "\n")
    sys.stdout.flush()
###

### 34803
colume, row = map(int, sys.stdin.readline().strip().split(" "))
strs = [sys.stdin.readline().strip() for i in range(row)]
length = int(sys.stdin.readline().strip())

dict = {}
for s in strs:
    for i in range(colume - length + 1):
        crr = s[i:i + length]
        dict[crr] = dict.get(crr, 0) + 1;

max_value = max(dict.values())

sys.stdout.write(str(max_value))
sys.stdout.flush()
###

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

###1158
#from collections import deque
n, k = map(int, sys.stdin.readline().strip().split(" "))
k -= 1

dq = deque([])
for i in range(n):
    dq.append(i + 1)

dq.rotate(-k)
sys.stdout.write("<" + str(dq.popleft()))
dq.rotate(-k)

for i in range(n - 1):
    sys.stdout.write(", " + str(dq.popleft()))
    dq.rotate(-k)
sys.stdout.write(">")
sys.stdout.flush()
###1158

###9656
turn = int(sys.stdin.readline().strip())
if not ((turn % 2) == 0):
    sys.stdout.write("CY")
else:
    sys.stdout.write("SK")

sys.stdout.flush()
###

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