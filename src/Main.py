import sys

push_times = int(sys.stdin.readline().strip())

ab_list = [[1, 0], [0, 1], [1, 1]]
if push_times < 4:
    print(ab_list[push_times - 1][0], ab_list[push_times - 1][1])

for i in range(3, push_times + 1):
    new_a = ab_list[i - 1][1]
    new_b = ab_list[i - 1][0] + ab_list[i - 1][1]
    ab_list.append([new_a, new_b]);
        
print(ab_list[push_times][0], ab_list[push_times][1])
    
