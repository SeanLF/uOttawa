printList([X|L]):- 
	writeln(X),
	printList(L).
	
secondLast(X,[X,_]).
secondLast(X,[_|L]):-secondLast(X,L).

maxmin([A,B],Max,Min):- Max is max(A,B), Min is min(A,B).
maxmin([X|L],Max,Min):- maxmin(L,Y,Z), Max is max(X,Y), Min is min(X,Z).