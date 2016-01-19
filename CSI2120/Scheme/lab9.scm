(define fibonacci
  (lambda (n)
    (if (= n 0)
        0
        (let fib ((i n) (a1 1) (a2 0))
          (if (= i 1)
              a1
	      (begin (display (- i 1)) (display (+ a1 a2)) (display a1) (newline)
              (fib (- i 1) (+ a1 a2) a1)))))))

; 	Fibonacci Option 2
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(define fibonacci
  (lambda (n)
    (let fib ((i n))
      (cond
        ((= i 0) 0)
        ((= i 1) 1)
        (else (begin (display (- i 1)) (display(- i 2)) (newline)  (+ (fib (- i 1)) (fib (- i 2)))))))) )

(define (insert x lst op?)
	(if (null? lst)
	(list x)
	(let ((y (car lst)) (ys (cdr lst)))
	(if (op? x y)
		(cons x lst)
		(cons y (insert x ys op?))))))
		
(define (insertion-sor lst op?)
	(if (null? lst)
	 '()
         (insert (car lst) (insertion-sor (cdr lst) op?) op?)
	 )
  )
	 
(define (insertion-sort v op?) (insertion-sor (vector->list v) op?))