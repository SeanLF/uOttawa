% CSI2120
% SEAN FLOYD
% 6778524

% ------------------
% ex 1
% ------------------
     q(a).
     q(b).
     s(a).
     s(e).
     s(f).
     s(g).
     k(e).
     k(f).
     u(e).
     u(g).
     p(X,X,c):- q(X). 
     p(X,Y,Z):- r(X,Y,Z),s(Z).
     r(X,Y,Z):- t(X,Z), u(Y).
     t(X,Z):- k(X),k(Z).
     
     % b) replace t(X,Z) :- k(X),k(Z),!.
     % c) replace p(X,X,c) :- q(X),!.
     
     
% ------------------
% ex 2
% ------------------

combinations(RComb,Number,Result):- 
	factorial(RComb, X),
	factorial(Number, Y),
	Z1 is Number-RComb,
	factorial(Z1,Z),
	Res is X*Z,
	Result is Y/Res.

	
factorial(0,1).
factorial(N,F) :-  
   N>0, 
   N1 is N-1, 
   factorial(N1,F1), 
   F is N * F1.
   
   
% ------------------
% ex 3
% ------------------

% player(Game, Name, Team)
player(p1, 'Stan', t1). player(p2, 'Pierre', t1). player(p3,'Jochen',t2). player(p4,'Robert',t2). player(p5, 'Paul', t3). player(p6, 'Samuel', t3).
% match(Match, HomeTeam, VisitingTeam, Season).
match(g1, t1, t2, winter11).
match(g2, t2, t3, winter11).
match(g3, t1, t3, fall12).
match(g4, t2, t3, fall12).
match(g4, t2, t1, fall12).
% aGoal(Match, Player, GoalInMinute)
aGoal(g1, p1, 55). 
aGoal(g1, p1, 22).
aGoal(g1, p2, 10).
aGoal(g2, p3, 37).
aGoal(g2, p3, 41).
aGoal(g2, p6, 60).
aGoal(g3, p2, 33).
aGoal(g3, p5, 49).

% a) ?- match(Match,_,_, winter11), aGoal(Match,Player,GoalInMinute),GoalInMinute>45.
% b) ?- aGoal(_,PlayerNumber,_), player(PlayerNumber, Name, _).
% c) ?- aGoal(Game1,Player,_), aGoal(Game2, Player, _), not(Game1=Game2), player(Player, Name,_).
% d) ?- match(_,_,AT,fall12), not(match(_,AT, _,fall12)),!.        since we were asked "the" team (singular)

% ------------------
% ex 4
% ------------------

%parent(child,father,mother)
parent(claire,alex,brianne).
parent(dalton,alex,brianne).
parent(eric,alex,brianne).
parent(thor,rick,shannon).
parent(ulric,rick,yazzi).
parent(sofia,bob,bobette).

	
hasSibling(Kid,OtherKid):-
	parent(Kid,Dad, Mom),
	parent(OtherKid, HisDad, HisMom),
	Kid\==OtherKid, Dad=HisDad, Mom=HisMom.
% kid is a single child if his two parents don't have another kid. don't take into account half siblingsconsult('').
singleChild(Kid):- 
	parent(Kid,_,_),
	not(hasSibling(Kid,_)).

%
% ------------------
% ex 5
% ------------------

pgrm(_):- repeat, writeln('2 to the power of X='),read(X), sign(X), X<0,!, fail.
pow(2,1,2).
pow(2,Y,Z):-
	Y>0,
	Y1 is Y-1,
	pow(2,Y1,Z1),
	Z is 2*Z1.
	
sign(X):- X>0, write('2^'),write(X),write(' is '),pow(2,X,Z), writeln(Z),fail.
sign(X):- X<0.