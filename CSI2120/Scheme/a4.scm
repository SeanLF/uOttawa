;; ------------------------------------------ exercise 1

(define (cos-t x t)
  (cond 
    ((<= t 0) "usage: (cost-t x t) and t>0")
    ((= t 1) 1)
    ((> t 1) 
     (+
      (/
       (* 
        (expt x (* 2 (- t 1))) 
        (expt (- 0 1) (- t 1)))
       (factorial(* 2 (- t 1)))) 
      (cos-t x (- t 1)))
)))
    
    
(define (factorial n)
     (if (zero? n) 1
         (* n (factorial (- n 1)))))    

(cos-t 3.14 100)
;; evaluates to -0.9999987317275388

;; ------------------------------------------ exercise 2
(define delete (lambda (x t)
    (cond
      ((null? t) '())
      ((and (equal? x (car t)) (null? (cadr t))) (caddr t)) 
      ((and (equal? x (car t)) (null? (caddr t))) (cadr t)) 
      ((equal? x (car t))
       (let ((r (removemax-BST (cadr t)))) (list (cdr r) (car r) (caddr t))))
      ((precedes? x (car t)) (list (car t)
                                   (delete x (cadr t)) (caddr t))) 
      ((precedes? (car t) x) (list (car t) (cadr t)
                                   (delete x (caddr t))))
      (else t) )))

(define removemax-BST
  (lambda (t)
    (cond
      ((null? (caddr t)) (cons (cadr t) (car t))) 
      (else
       (let ((r (removemax-BST (caddr t))))
         (cons (list (car t) (cadr t) (car r)) (cdr r))
         )) )))

(define precedes? (lambda (x y) (< x y)))

(define (deleteNodes BST start end)
  (if(<= start end) (deleteNodes(delete start BST) (+ start 1) end) (begin (display "Resulting tree: ") BST)))

(define t9 '(14 (7 () (12 () ()))(26 (20(17 () ()) ()) (31(30 () ())(35 () ())))))

(deleteNodes t9 15 25)
;; ------------------------------------------ exercise 3
(define (abs-vec vec)
  (define i 0)                      
  (do ()
    ((= i (vector-length vec))) ; run until the end of the vector
    (vector-set! vec i (abs (vector-ref vec i)))
  (set! i (+ i 1)))(begin (display "Resulting vector: ") vec))

(abs-vec (list->vector '(-1 -2 -3 -4 -10)))