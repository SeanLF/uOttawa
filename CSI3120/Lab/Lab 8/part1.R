# Task 1
Length = c(75, 85, 91.6, 95, NA, 105.5, 106);
Year = c(00, 00, 01, NA, 03, 03, 02);
Month = c(11,07,07, NA, 09, 09, 11);
# Make a matrix
Animal = cbind(Year, Month, Length);
# Check if Animal is a matrix; visually
Animal;
# Length of Animals 3 and 6
Animal[1,3];Animal[6,3];
# All animals except row 4
Animal[-4,]


# Task 2
# import Deer.csv
deer = read.csv("/Users/Sean/dev/uOttawa/CSI3120/Lab/Lab 8/Deer.csv");
colnames(deer);
farms = deer$Farm; tbs = deer$Tb;
deer2 = data.frame(farms, tbs, sqrt(deer$LCT))
DataInfo = c(nrow(deer), sum(deer$Tb, na.rm=TRUE)); DataInfo
