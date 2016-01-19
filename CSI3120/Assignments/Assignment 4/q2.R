perceptron = function(dataSet, speciesA, speciesB, N) {
	# the weights for Theta and iris characteristics are set to random
	w=runif(5, -1, 1)
	print(w)
	# the learning constant
	n = runif(1, 0.1, 0.2)
	# randomize rows
 dataSet <- dataSet[sample(nrow(dataSet)), ]
	for (epoch in 1: N) {
		count = 0
		for(i in 1 : nrow(dataSet)){
			# inputs to the perceptron where x[1] = sepal L, x[2] = sepal W, x[3] = petal L, x[4] = petal W, x[5]=theta
			x = c(1, dataSet$Sepal.Length[i], dataSet$Sepal.Width[i], dataSet$Petal.Length[i], dataSet$Petal.Width[i])
			s = x[1]*w[1] + x[2]*w[2] + x[3]*w[3] + x[4]*w[4] + x[5]*w[5]
			y = sgn(s)
			d = desiredOutput(dataSet$Species[i], speciesA)
			if(y != d){
				for (index in 1:5) 
				{
					w[index] = w[index] + n * d * x[index]
				}
			}
			if(y == d)
			{
				count = count +1
			}
		}
		print(paste(c("Epoch", epoch, "completed with", count, "irises properly classified."), collapse = " "))
	}
   paste(c(speciesA, speciesB), collapse = " ");w
}


sgn = function(s)
{
	ifelse(s>0, 1, -1)
}

desiredOutput = function(actualSpecies, speciesA){
	ifelse(actualSpecies == speciesA, 1, -1)
}

# the number of epochs
N = runif(1, 10,15)
dataSet = subset(iris, iris$Species %in% c("setosa", "versicolor"))
perceptron(dataSet, "setosa", "versicolor", N)

dataSet = subset(iris, iris$Species %in% c("setosa", "virginica"))
perceptron(dataSet, "setosa", "virginica", N)

# the number of epochs -> max reached 96% as non linearly-separable domains
N = runif(1, 10,10000)
dataSet = subset(iris, iris$Species %in% c("versicolor", "virginica"))
perceptron(dataSet, "versicolor", "virginica", N)