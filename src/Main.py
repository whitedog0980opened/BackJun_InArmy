import sys

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
