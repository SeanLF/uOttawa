;Program made by
;Lyne Ouellette 
;Philippe Bisaillon 
;Modified by Alistair Kennedy

;To run the simulation, call the main method (life alive sizeX sizeY generation char)
;Where
;alive is a rectangle that represents the initial configuration of alive organism.
;ex: '((0 0) (0 1) (2 5))
;In the above example,there are three living organism located at (x,y)=(0,0),(0,1) and (2,5)
;SizeX and SizeY represents the size of the array
;generation represents the number of generation to simulate
;char is the character representing a living organism

;Ex of simulation (life '((2 1) (2 3) (1 3) (3 2) (3 3)) 10 10 4 '*)

;*************************************************************************************
;Return the numbers of neighbors present, a pair will never exists outside the matrix
(define getNN
 (lambda (coor L) 
  (let ((x (car coor)) (y (cadr coor))) ; x,y are the coordinates
   (+ (isAlive? (list (- x 1) (- y 1)) L) 
      (isAlive? (list (- x 1) y) L)
      (isAlive? (list (- x 1) (+ y 1)) L)
      (isAlive? (list x (- y 1)) L)
      (isAlive? (list x (+ y 1)) L)
      (isAlive? (list (+ x 1) (- y 1)) L)
      (isAlive? (list (+ x 1) (+ y 1)) L)
      (isAlive? (list (+ x 1) y) L)
   )
  )
 )
)

; Returns 1 if an element is a number of the list, else returns 0. Used by getNN
(define isAlive?
 (lambda (coor L)
  (if (member coor L) 1 0)
 )
)

;*************************************************************************************
;List of methods controlling the birth of an organism
;x and y: The current coordinate to be tested
;L: list that contains the alive coordinates
;MAXx : max coordinate in x
;MAXy : max coordinate in y
;newBirth : list that contains new organism

;Function that will test each coordinates for a possible birth
;Sweeps in x
(define newBornRow
 (lambda (x y L Xmax Ymax Current)
  (if (< x Xmax)
   (append (Birth x y L Current) (newBornRow (+ x 1) y L Xmax Ymax Current))
   '()
  )
 )
)

;Sweeps in y, call newBornRow
(define newBorn
 (lambda (x y L Xmax Ymax Current)
  (if (< y Ymax)
   (append (newBornRow x y L Xmax Ymax Current) (newBorn x (+ y 1) L Xmax Ymax Current ))
   '()
  )
 )
)


(define Birth
 (lambda (x y L newBirth)
  (if (and (not (member (list x y) L)) (= (getNN (list x y) L) 3)) 
   (list (list x y)) 
   '() 
  )
 )
)

;*************************************************************************************
;Method that controls the death of an organism
;takes in entry the list of organism

(define stillAlive
 (lambda (L current)
   L
 )
)

;*************************************************************************************
;Main method + tests
;Alive = list of pair that represents the coordinates of the organism
;SizeX & SizeY = dimensions of the array
;generation = number of generation to run the simulation

(define life
 (lambda (alive sizeX sizeY generation char)
  (if (valid? alive sizeX sizeY generation char)
   (survival alive sizeX sizeY generation char)
   '(The arguments are not valid)
  )
 )
)

(define survival
 (lambda (alive sizeX sizeY generation char)
  (cond 
   ((= generation 0) (print alive sizeX sizeY char))
   (else (print alive sizeX sizeY char) (survival (append (stillAlive alive alive) (newBorn 0 0 alive sizeX sizeY '())) sizeX sizeY (- generation 1) char))
  )
 )
)


;Returns false if the arguments are not valid
(define valid?
 (lambda (alive sizeX sizeY generation char)
  1
 )
)

;*************************************************************************************
;Method to print the simulation

;Prints the X legend
(define printLegend
 (lambda (x sizeX)
  (display x)
  (display " ")
  (if (< (+ x 1) sizeX) (printLegend (+ x 1) sizeX )) 
 )
)

(define print
 (lambda (alive sizeX sizeY char)
  (display "  ")
  (printLegend 0 sizeX)
  (newline)
  (printY 0 0 alive sizeX sizeY char)
  (newline)
 )
)

;Sweeps in Y
(define printY
 (lambda (x y alive sizeX sizeY char)
  (display y) (display " ")
  (printX x y alive sizeX char)
  (newline)
  (if (< (+ y 1) sizeY) (printY x (+ y 1) alive sizeX sizeY char))
 )
)

;Sweeps in X
(define printX
 (lambda (x y alive sizeX char)
  (if (member (list x y) alive) (display char) (display " "))
  (display " ")
  (if (< (+ x 1) sizeX) (printX (+ x 1) y alive sizeX char))
 )
)

