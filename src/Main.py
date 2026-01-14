import sys

n = int (sys.stdin.readline().strip());

arr = [1, 3, 5, 11]
if n < 5:
    sys.stdout.write(str(arr[n - 1]))
    sys.exit(0)

for i in range(4, n):
    next_val = (arr[i-2] * 2 + arr[i - 1]) % 10007;
    arr.append(next_val)

print(arr[n - 1])
