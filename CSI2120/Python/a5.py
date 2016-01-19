# 6778524
import os

def mult(n=11,path=os.getcwd()):
		fid = open (path+'/multiples'+str(n)+'.txt','w')
		print ("Working on multiples of "+str(n)+".")
		a = n
		b = n
		for line in range(1,1000001):
			b = line*a
			fid.write(str(b))
			fid.write("\n")
		fid.close()
        
def getM(a=11,b=18, path=os.getcwd()):
    for x in range(a,b+1):
        mult(x)
    print("\nDone!\n")