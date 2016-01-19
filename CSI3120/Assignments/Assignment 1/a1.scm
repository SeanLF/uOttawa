(define random	(let ((a 69069) (c 1) (m (expt 2 32)) (seed 19380110))		(lambda new-seed (if (pair? new-seed)			(set! seed (car new-seed))			(set! seed (modulo (+ (* seed a) c) m))) (/ seed m)		)	))
(define (randint . args) (cond ((= (length args) 1)(floor (* (random) (car args)))) ((= (length args) 2)	(+ (car args) (floor (* (random) (- (cadr args) (car args)))))) (else (error 'randint "usage: (randint [lo] hi)"))))

(define (tree) (getValues '() '()))
(define PathValues '())

; creates the decision tree
; dTree: the decision tree
; PathValues: a helping list to keep track of the encountered values between the root and current subtree
(define (getValues dTree PathValues)
	(let((rand (randint -5 10)))
		(cond 
			((and(<= rand -2)(not(null? PathValues))) 'no) ; random integer is in range [-5 , -2] => no
			((and(<= rand 0)(not(null? PathValues))) 'yes) ; random integer is in range [-1 ,  0] => yes
			; random integer > 0 from here on
                        ((member rand PathValues) (getValues dTree PathValues)) ; encountered random integer above , try again
                        (else (begin
                                (set! PathValues (append PathValues (list rand))) ; add the new integer to the list of encountered #'s
                                (set! dTree (append(list (getValues dTree PathValues) (getValues dTree PathValues)) dTree)) ; recursively create subtrees
                                (set! PathValues (removeLastElement PathValues)) ; subtrees are finished, remove integer from encountered #'s
                                dTree ; return decision tree
                                )))))
                               
; my little helpers :)						
(define (removeLastElement List)(cond	((null? List) '())(else (reverse(cdr(reverse List))))))
(define (lastItem List) (cond ((null? List) '())(else (reverse(car(reverse List))))))
(define (sizeOfList List)(cond((null? List) 0)((not(list? List)) 1)(else (+ (sizeOfList (car List)) (sizeOfList (cdr List))))))
(define (numberOfImmediateInnerLists List)(cond((null? List) 0)((list? List) (+ 1 (numberOfImmediateInnerLists (cdr List))))))

; this gives a 0 or 1 score 
; testList is a list of instructions (left/right) to go through the tree and an expected answer
; tree is a binary tree containing either yes or no
(define (binaryScore testValues tree)
	(cond
		((or(null? tree)(null? testValues)) 0) ; either list is empty
		
		((and(not(list? tree)) (eqv? tree (car(lastItem testValues)))) 1) ; tree value is not list AND tree value = last element of test
                ((and(not(list? tree)) (not(eqv? tree (car(lastItem testValues))))) 0) ; tree value is not list AND tree value ≠ last element of test
		((and(null?(cdr testValues))(eqv? tree(car testValues))) 1 ) ; reached result in testList AND tree value = expected result 
                ((and(null?(cdr testValues))(not(eqv? tree(car testValues)))) 0 ) ; reached result in testList AND tree value ≠ expected result 
		
		((eqv?(car testValues) #f) (binaryScore (cdr testValues) (car tree))) ; encounter #f in test list, go left subtree
		((eqv?(car testValues) #t) (binaryScore (cdr testValues) (cadr tree))) ; encounter #t in test list, go right subtree
		(else 0) ; no condition => fails the test
		))

; recursively calculates total score of tree from database of tests
(define (totalScore testDB tree score)
  (cond
    ((or(null? testDB)(null? tree)) 0 ) ; empty lists means a failure
    (else(begin
           (set! score (+ score (+ (binaryScore (car testDB) tree) (totalScore (cdr testDB) tree 0)))) ; recurse!
           score ; return score
           ))))

							
; EXECUTE ME!
(define (run numberOfLoops)
  (if (= 0 numberOfLoops) (display "stopped")
  (let ((t (tree))(db '((#f #t #f #f #t #t #t #f #t #f 'yes) (#f #f #f #t #t #t #f #f #t #f 'yes) (#t #t #t #f #t #t #f #t #f #f 'no) (#t #f #f #t #t #t #f #f 'no) (#f #f #f #t #f #t 'yes) (#f #f #t #t #t #t #f #t 'yes) (#t #t #f #f #t #f #t 'no) )))
    ;( if(= x 0) (display "stopped"))
    (begin
      (let ((score (totalScore db t 0))(size (numberOfImmediateInnerLists db)))
        (if (= size score) ; if tree obtains max score
            (begin(display t)(display "\n")) ; display it (new line)
            ;(begin(display score)(display "\n") ) ; otherwise, display the score
            ))
      (run (- numberOfLoops 1))
      ))))