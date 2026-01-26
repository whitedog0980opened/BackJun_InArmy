import sys
import heapq
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