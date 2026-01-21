import sys

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
