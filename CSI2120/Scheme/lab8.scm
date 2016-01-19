; exercise 1

(define (slice L i k)
	(sublist L (- i 1) k)	
)

; exercise 2

(define (range i k)
	(do
		((x i (+ x 1)) (L (list) (append L x)))
		(= x k) L
	)
)

(define (drop L num)
	(do 
		((x 1 (+ x 1))) ; x=1, x++
		()
	)
)