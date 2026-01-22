import sys
import heapq
### n31403 
a = sys.stdin.readline().strip()
b = sys.stdin.readline().strip()
c = sys.stdin.readline().strip()

int_result = int(a) + int(b) - int(c);
sys.stdout.write(str(int_result) + "\n");

str_result = str(int((a + b)) - int(c));
sys.stdout.write(str(str_result))
###

### n11727
n = int (sys.stdin.readline().strip());

arr = [1, 3, 5, 11]
if n < 5:
    sys.stdout.write(str(arr[n - 1]))
    sys.exit(0)

for i in range(4, n):
    next_val = (arr[i-2] * 2 + arr[i - 1]) % 10007;
    arr.append(next_val)

print(arr[n - 1])
###

### n30306
n = sys.stdin.readline().strip();
first_dice = list(map(int, sys.stdin.readline().strip().split(" ")));
second_dice = list(map(int, sys.stdin.readline().strip().split(" ")));

win1 = 0
win2 = 0
for fir in first_dice:
    for sec in second_dice:
        if (fir > sec): 
            win1 += 1;
        elif (fir < sec):
            win2 += 1;

result = ""
if (win1 > win2):
    result = "first";
elif (win2 > win1):
    result = "second";
else:
    result = "tie";
print(result);
###

### n23544
n = int(sys.stdin.readline().strip());

my_set = set();
for i in range(n):
    my_set.add(sys.stdin.readline().strip());

result = n - len(my_set)
print(result);
###

### n33225
origin = list(sys.stdin.readline().strip())
new_list = []
deleted = False;
pre_ch = ""
for ch in origin:
    if ch == pre_ch:
        if not deleted:
            deleted = True;
            continue;
        else:
            new_list.append(ch);
    else:
        new_list.append(ch);
        pre_ch = ch;
        deleted = False;

for ch in new_list:
    sys.stdout.write(ch)
###

### 31821
case = int(sys.stdin.readline().strip())
fees = []
result = 0;

for i in range(case):
    fees.append(int(sys.stdin.readline().strip()))

students_num = int(sys.stdin.readline().strip())
for i in range(students_num):
    result += fees[int(sys.stdin.readline().strip()) - 1]

sys.stdout.write(str(result))
sys.stdout.flush()
###