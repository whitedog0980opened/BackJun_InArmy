import sys
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