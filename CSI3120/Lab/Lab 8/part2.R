# read from csv
RK = read.csv("/Users/Sean/dev/uOttawa/CSI3120/Lab/Lab 8/RK.csv");

# Plot with title, x and y labels, and solid data points
plot(RK$D.PARK, RK$TOT.N, main="Plot of D.PARK as x and TOT.N as y", xlab="Distance to Park", ylab="Total Number", pch=16)

# plot with color = blue if olive = 0, if olive > 0 use col=red
plot(RK$D.PARK, RK$TOT.N, main="Plot of D.PARK as x and TOT.N as y", xlab="Distance to Park", ylab="Total Number", pch=16, col=ifelse((RK$OLIVE==0), "blue", (ifelse((RK$OLIVE>0),"red", "black"))))