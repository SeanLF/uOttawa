# variables
names <- c("setosa","virginica","versicolor")
pch111 <- c(1,1,1)
pch1111 <- c(1,1,1,1)
bgr <- c("blue", "green", "red")
bgro <- c("blue", "green", "red", "orange")

# Species and their characteristics
SetosaSepalLength <- subset(iris$Sepal.Length, iris$Species==names[1]);SetosaSepalWidth <- subset(iris$Sepal.Width, iris$Species==names[1]);SetosaPetalLength <- subset(iris$Petal.Length, iris$Species==names[1]);SetosaPetalWidth <- subset(iris$Petal.Width, iris$Species==names[1])
VersicolorSepalLength <- subset(iris$Sepal.Length, iris$Species==names[2]);VersicolorSepalWidth <- subset(iris$Sepal.Width, iris$Species==names[2]);VersicolorPetalLength <- subset(iris$Petal.Length, iris$Species==names[2]);VersicolorPetalWidth <- subset(iris$Petal.Width, iris$Species==names[2])
VirginicaSepalLength <- subset(iris$Sepal.Length, iris$Species==names[3]);VirginicaSepalWidth <- subset(iris$Sepal.Width, iris$Species==names[3]);VirginicaPetalLength <- subset(iris$Petal.Length, iris$Species==names[3]);VirginicaPetalWidth <- subset(iris$Petal.Width, iris$Species==names[3])

# Mean characteristics per species
meanSetosaSepalLength <- mean(SetosaSepalLength);meanSetosaSepalWidth <- mean(SetosaSepalWidth);meanSetosaPetalLength <- mean(SetosaPetalLength);meanSetosaPetalWidth <- mean(SetosaPetalWidth)
meanVersicolorSepalLength <- mean(VersicolorSepalLength);meanVersicolorSepalWidth <- mean(VersicolorSepalWidth);meanVersicolorPetalLength <- mean(VersicolorPetalLength);meanVersicolorPetalWidth <- mean(VersicolorPetalWidth)
meanVirginicaSepalLength <- mean(VirginicaSepalLength);meanVirginicaSepalWidth <- mean(VirginicaSepalWidth);meanVirginicaPetalLength <- mean(VirginicaPetalLength);meanVirginicaPetalWidth <- mean(VirginicaPetalWidth)

# Mean characteristics
meanSepalLength <- c(meanSetosaSepalLength, meanVersicolorSepalLength, meanVirginicaSepalLength);meanSepalWidth <- c(meanSetosaSepalWidth,meanVersicolorSepalWidth,meanVirginicaSepalWidth);meanPetalLength <-c(meanSetosaPetalLength,meanVersicolorPetalLength,meanVirginicaPetalLength);meanPetalWidth <-c(meanSetosaPetalWidth,meanVersicolorPetalWidth,meanVirginicaPetalWidth)

# create a pie chart that shows distribution of species
pie(as.data.frame(table(iris$Species))$Freq, main="Iris Distribution", labels=names)

par(mfrow=c(2,3)) 

# creates a scatter plot for sepal length per species
attach(iris)
plot(Sepal.Length, Sepal.Width, col=bgr[Species], main="Sepal Length vs Sepal Width")
legend("bottomright", names, pch=pch111, col=bgr)
detach(iris)

# creates a scatter plot for sepal length per species
attach(iris)
plot(Sepal.Length, Petal.Length, col=bgr[Species], main="Sepal Length vs Petal Length")
legend("bottomright", names, pch=pch111, col=bgr)
detach(iris)

# creates a scatter plot for sepal length per species
attach(iris)
plot(Sepal.Length, Petal.Width, col=bgr[Species], main="Sepal Length vs Petal Width")
legend("bottomright", names, pch=pch111, col=bgr)
detach(iris)

# creates a scatter plot for sepal length per species
attach(iris)
plot(Sepal.Width, Petal.Length, col=bgr[Species], main="Sepal Width vs Petal Length")
legend("topright", names, pch=pch111, col=bgr)
detach(iris)

# creates a scatter plot for sepal length per species
attach(iris)
plot(Sepal.Width, Petal.Width, col=bgr[Species], main="Sepal Width vs Petal Width")
legend("topright", names, pch=pch111, col=bgr)
detach(iris)

# creates a scatter plot for sepal length per species
attach(iris)
plot(Petal.Length, Petal.Width, col=bgr[Species], main="Petal Length vs Petal Width")
legend("bottomright", names, pch=pch111, col=bgr)
detach(iris)

par(mfrow=c(2,2)) 

# creates a barplot for the average value of each characteristic of setosa species
barplot(c(meanSetosaSepalLength,meanSetosaSepalWidth,meanSetosaPetalLength,meanSetosaPetalWidth), main="Average characteristic for Setosa", xlab="Average characteristic", col=bgro)
legend("topright", c("Sepal L", "Sepal W","Petal L", "Petal W"), pch=pch1111, col=bgro)
lines(c(meanSetosaSepalLength,meanSetosaSepalWidth,meanSetosaPetalLength,meanSetosaPetalWidth))

# creates a barplot for the average value of each characteristic of versicolor species
barplot(c(meanVersicolorSepalLength,meanVersicolorSepalWidth,meanVersicolorPetalLength,meanVersicolorPetalWidth), main="Average characteristic for Versicolor", xlab="Average characteristic",col=bgro)
legend("topright", c("Sepal L", "Sepal W","Petal L", "Petal W"), pch=pch1111, col=bgro)
lines(c(meanVersicolorSepalLength,meanVersicolorSepalWidth,meanVersicolorPetalLength,meanVersicolorPetalWidth))

# creates a barplot for the average value of each characteristic of virginica species
barplot(c(meanVirginicaSepalLength,meanVirginicaSepalWidth,meanVirginicaPetalLength,meanVirginicaPetalWidth), main="Average characteristic for Virginica", xlab="Average characteristic",col=bgro)
legend("topright", c("Sepal L", "Sepal W","Petal L", "Petal W"), pch=pch1111, col=bgro)
lines(c(meanVirginicaSepalLength,meanVirginicaSepalWidth,meanVirginicaPetalLength,meanVirginicaPetalWidth))

# creates a grouped bar plot for the average value of each characteristic for each
barplot(c(meanSepalLength, meanSepalWidth, meanPetalLength, meanPetalWidth), main="Average Characteristic per Species", beside=true, xlab="[Sepal Length, Sepal Width, Petal Length, Petal Width]/Species", col=bgr)
legend("topright", names, pch=pch111, col=bgr)
lines(c(meanSepalLength, meanSepalWidth, meanPetalLength, meanPetalWidth))
 
par(mfrow=c(2,2)) 

# creates a boxplot for sepal length
boxplot(SetosaSepalLength, VersicolorSepalLength, VirginicaSepalLength, main="Boxplot for Sepal Length", xlab="Species", ylab="Sepal Length",col=bgr)
legend("bottomright", names, pch=pch111, col=bgr)
 

# creates a boxplot for sepal width
boxplot(SetosaSepalWidth, VersicolorSepalWidth, VirginicaSepalWidth, main="Boxplot for Sepal Width", xlab="Species", ylab="Sepal Width",col=bgr)
legend("topright", names, pch=pch111, col=bgr)
 

# creates a boxplot for petal length
boxplot(SetosaPetalLength, VersicolorPetalLength, VirginicaPetalLength, main="Boxplot for Petal Length", xlab="Species", ylab="Petal Length",col=bgr)
legend("bottomright", names, pch=pch111, col=bgr)
 

# creates a boxplot for petal width
boxplot(SetosaPetalWidth, VersicolorPetalWidth, VirginicaPetalWidth, main="Boxplot for Petal Width", xlab="Species", ylab="Petal Width",col=bgr)
legend("bottomright", names, pch=pch111, col=bgr)
 

# The box plots are the most useful in determining whether an iris belongs to a species. The petal length in perticular is the best characteristic to detemine an iris's species.