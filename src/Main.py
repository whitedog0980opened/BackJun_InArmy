import sys

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
    
