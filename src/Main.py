import sys
n = int(sys.stdin.readline().strip());

my_set = set();
for i in range(n):
    my_set.add(sys.stdin.readline().strip());

result = n - len(my_set)
print(result);
    
