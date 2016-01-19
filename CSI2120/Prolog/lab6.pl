% Lab 6 - 6778524

% exercise 1

:- dynamic fibo/2.
fibo(1,1).
fibo(2,1).
fibo(3,2).

fibo(N,F):- 
	N>=0,
	N1 is N-1, 
	fibo(N1,F1), 
	N2 is N-2, 
	fibo(N2,F2),
	F is F1+F2,
	assert(fibo(N,F)),!.
	
% exercise 2

find_all(X,Goal,Bag) :- 
	post_it(X,Goal),
	gather([],Bag). 

post_it(X,Goal) :- 
	call(Goal), 						% try Goal 
	asserta(data999(X)), 			% assert above others 
	fail.					 				% force backtracking  

post_it(_,_). 							% Done (above goal does not succeed anymore)  

gather(B,Bag) :- 
	data999(X), 						% next recorded solution 
	retract(data999(X)),			 	% clean up
	not(member(X,B)),					% if the retracted element is not part of the list, add it!  <----------------
	gather([X|B],Bag), 				% continue  
	!. 									% cut off tail end 

gather(S,S). 							% Done 

myList([a,1,y,3,4,z,3,6,3]).