import sys
import heapq
from collections import deque

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