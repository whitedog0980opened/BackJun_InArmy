### n31403 
a = sys.stdin.readline().strip()
b = sys.stdin.readline().strip()
c = sys.stdin.readline().strip()

int_result = int(a) + int(b) - int(c);
sys.stdout.write(str(int_result) + "\n");

str_result = str(int((a + b)) - int(c));
sys.stdout.write(str(str_result))
###

###