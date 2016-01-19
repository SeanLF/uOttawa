% Assignment 2 - CSI2120 - 6778524 

% QUESTION 1

rest(_,[],[]):-!.
rest(X,[X|T],T):-!.
rest(X,[_|T],R):-
	rest(X,T,R).
combination(N,L,X):-
	length(X,N),
	memberr(X,L).
memberr([],_).
memberr([Head|Tail],Y):-
	member(Head,Y),
	rest(Head,Y,New),
	memberr(Tail,New).

start:- 
	initial(Initial),
	path(Initial,[],Sol),
	write('Found solution='),nl,
	forall(member(X,Sol),
	(write(X),nl)).

% finding a path in a graph from initial node to final node
path(Node,Path,[Node|Path]):- 
	final(Node).
path(Node,Path,Sol):- 
	move(Node,Node1),
	not(member(Node1,Path)),
	path(Node1,[Node|Path],Sol).

% start off with everyone on left bank and time=0
initial([0,leftBank,[thorin,fili,nori,bofur,balin],[]]).

% we want everyone to be on the right bank when we're done
final([_ /* time */,rightBank,[],[thorin,fili,nori,bofur,balin]]).

% opposite bank.
opposite(leftBank,rightBank).  
opposite(rightBank,leftBank).

% time for crossing the bridge 
ttime(thorin,1).
ttime(fili,3).
ttime(nori,6).
ttime(bofur,8).
ttime(balin,12).

move([Time1,FlashLight1,LeftBank1,RightBank1], [Time2,FlashLight2,LeftBank2,RightBank2]):- 
	opposite(FlashLight1,FlashLight2),
	(	(	FlashLight1=leftBank,
			cross(Individual,LeftBank1),
			remove(Individual,LeftBank1,LeftBank2),
			append(Individual,RightBank1,RightBank2),
			findtime(Individual,Time),
			Time2 is Time1+Time
		) ; % OR if flashlight is on the right bank
   	(	FlashLight1=rightBank,
			cross(Individual,RightBank1),
			remove(Individual,RightBank1,RightBank2),
			append(Individual,LeftBank1,LeftBank2),
			findtime(Individual,Time),
			Time2 is Time1+Time
		)
	),
	Time2 < 31	.

remove(S,L,R):- 
	findall(Element,(member(Element,L),not(member(Element,S))),R).

% 1 or 2 ppl cross the bridge
findtime([Individual],Time):- 
	ttime(Individual,Time),!.
findtime([PersonA,PersonB],Time):- 
	ttime(PersonA,TimeA),
	ttime(PersonB,TimeB),
	Time is max(TimeA,TimeB),!.

/* take all the combinations of 1 person, and 2 persons from our group */
cross(Individual,LeftBank):- 
	combination(1,LeftBank,Individual)   ; % OR
	combination(2,LeftBank,Individual).


% QUESTION 2 ... will need to edit the file path 

main:-
	open('/Users/Sean/Dropbox/2ndYear/CSI2120-Paradigms/Prolog/names.txt', read, Str),
	my_read(Str,Lines),
	close(Str),
	nl,write(Lines), nl,
	tree(Tree), 
	construct(Tree,Lines,_Result).

my_read(File, L) :-
	read_line_to_codes(File, T),
	(   T  = end_of_file -> L = [];
	atom_codes(T1, T),
	my_read(File, L1), L = [T1 | L1]).
			  
insert(Key, nil, t(Key, nil, nil)).

insert(Key, t(Root, Left, Right), t(Root, LeftPlus, Right)) :-
   precedes(Key,Root),
   insert(Key, Left, LeftPlus).
	
insert(Key, t(Root, Left, Right), t(Root, Left, RightPlus)) :-
   precedes(Root,Key),
   insert(Key, Right, RightPlus).
	
tree(X):- 
	X = nil.
	
construct(Tree,[Beginning|End], Result):- 
	insert(Beginning, Tree, NewTree),
	construct(NewTree, End, Result).

% fails without empty case 
construct(Tree,[],Tree):-nl,write('Resulting tree: '),write(Tree),nl,nl.
% built-in compare/3 can have the following results = {<,>,=} 
precedes(A,B):-
	compare(<,A,B).