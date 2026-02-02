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

###24860
n1, n2 = map(int, sys.stdin.readline().strip().split(" "))
m1, m2 = map(int, sys.stdin.readline().strip().split(" "))
o1, o2, o3 = map(int, sys.stdin.readline().strip().split(" "))

result = (n1 * n2 + m1 * m2) * (o1 * o2 * o3)
print(result)
###

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

###34635
r_need, g_need, b_need = map(int, sys.stdin.readline().strip().split())
r_have, g_have, b_have = map(int, sys.stdin.readline().strip().split())
rg_able, gb_able = map(int, sys.stdin.readline().strip().split())

r_need -= r_have
g_need -= g_have
b_need -= b_have
used = 0

if (r_need > 0):
    rg_able -= r_need
    if (rg_able < 0):
        print(-1)
        sys.exit(0)
    used += r_need
if (b_need > 0):
    gb_able -= b_need
    if (gb_able < 0):
        print(-1)
        sys.exit(0)
    used += b_need
if (g_need > 0):
    rg_able -= g_need
    if (rg_able < 0):
       gb_able += rg_able
       if (gb_able < 0):
           print(-1)
           sys.exit(0)
    used += g_need
print(used)
###

###28043
days, h_umb, w_umb = map(int, sys.stdin.readline().strip().split())
for i in range(days):
    h_wea, w_wea = sys.stdin.readline().strip().split()
    to_print = ""
    #homePattern
    if ((w_umb == 0) or (h_wea == "Y")) and (h_umb != 0):
        w_umb += 1
        h_umb -= 1
        to_print += "Y "
    else:
        to_print += "N "
    #workPlacePattern
    if ((h_umb == 0) or (w_wea == 'Y')) and (w_umb != 0):
        h_umb += 1
        w_umb -= 1
        to_print += "Y"
    else:
        to_print += "N"
        
    sys.stdout.write(to_print + "\n")
###