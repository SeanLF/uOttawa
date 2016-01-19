;---------------------------------------------- 6778524 CSI2120 a3 ---------------------------------------- 
;-------------------------------------------------- exercise 1 --------------------------------------------

(define (encode-rr L)
	(let encode( (L1 L) (count 1) (temp '()) (lists '()))
		(if (null? L1) lists
			(if (null? (cdr L1) ) (append lists (list temp))
				(if (eqv? (car L1) (cadr L1)) 
					(encode (cdr L1) (+ 1 count) (list (+ 1 count) (car L1)) lists) ; else do next line
					(encode (cdr L1) 1 (cadr L1) (append lists (list temp)))
			))))) 

;-------------------------------------------------- exercise 2 --------------------------------------------

(define (roots-2 L)
	(let* (
		(a (car L) )	(b(cadr L))		(c(caddr L))	(d(-(* b b)(* 4 a c))))
		(cond
			( (< d 0) (list 0 '()) ) ; 0 solution
			( (= d 0) (list 1 (list (/ (- 0 b) (* 2 a) ) ) )) ; 1 solution 
			( (> d 0) (list 2 (list (/ (- 0 b (sqrt d)) (* 2 a) ) (/ ( + (- 0 b) (sqrt d)) (* 2 a))))))))

;-------------------------------------------------- exercise 3 --------------------------------------------

(define (apply-list list test?) (filter test? list)	)
			
;-------------------------------------------------- exercise 4 --------------------------------------------

(define (split L i)	(list (sublist L 0 i) (sublist L i (length L))))