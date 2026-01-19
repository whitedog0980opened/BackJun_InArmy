import sys

tk = int(sys.stdin.readline().strip())


def dfs(nums, str, lists) :
    crr_str = ""
    for i in nums :
        crr_str += str[i]
        crr_str += dfs(nums - 1, str[1:nums + 1])
        list.extend(str)
    
    
    
print(ab_list[push_times][0], ab_list[push_times][1])


