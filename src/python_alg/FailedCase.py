import sys
import math
import heapq
import copy

###30804
#-1 -> left, 1 -> right
def progress(dir, fruit_num, arr, index, target, origin):
    counter = 0
    while True:
        if ((dir == -1 and index - 1 < 0)) or (dir == 1 and index + 1 >= fruit_num): #borderCheck
            return counter #endPoint
        if (target == arr[index]):
            index += dir
            counter += 1
        if (origin == arr[index]):#origin?
            index += dir
            counter += 1
        else:
            return counter
def find_diff_ori(dir, fruit_num, arr, index, origin):
    counter = 0;
    while True:
        if ((dir == -1 and index - 1 < 0)) or (dir == 1 and index + 1 >= fruit_num): #borderCheck
            return [10, counter] #endPoint
        counter += 1
        if (origin == arr[index]):#origin?
            index += dir
            counter += 1
        else:
            return [arr[index], counter]
        

fruit_num = int(sys.stdin.readline().strip())
fruit_arr_ori = list(map(float, sys.stdin.readline().strip().split()))
results = []
for i in range(fruit_num):
    origin = fruit_arr_ori[i]
    left_progress = True;
    right_progress = True;
    seiquence = []
    #left one
    l_num, l_start_index = find_diff_ori(-1, fruit_num, fruit_arr_ori, i - 1, origin)
    #right one
    r_num, r_start_index = find_diff_ori(1, fruit_num, fruit_arr_ori, i + 1, origin)
    if (l_num == 10):
        left_progress = False;
    if (r_num == 10):
        right_progress = False;
    
    if (l_num != r_num):
        seiquence.append([-1, r_start_index])
        seiquence.append([l_start_index, -1])
    else:
        [l_start_index, r_start_index]

    
    for starts in seiquence:
        if(starts[0] == -1):
            results.append(1 + r_start_index + progress(1, fruit_num, fruit_arr_ori, r_start_index, r_num, origin))
        elif(starts[1] == -1):
            results.append(1 + l_start_index + progress(-1, fruit_num, fruit_arr_ori, l_start_index, l_num, origin))
        else:
            result = 1 + r_start_index + l_start_index
            if (left_progress):
                result += progress(-1, fruit_num, fruit_arr_ori, l_start_index, l_num, origin)
            if (right_progress):
                result += progress(1, fruit_num, fruit_arr_ori, r_start_index, r_num, origin)
            results.append(result)

print(results)
sys.stdout.flush();
###