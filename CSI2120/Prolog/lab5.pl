
app(X, [X | _]).
app(X, [_ | L]) :- app(X, L). % membership predicate

napp(_, []).
napp(X, [X | _]) :- !, fail.
napp(X, [_ | L]) :- napp(X, L). % not member predicate

next(I, J, I, JS) :- J < 9, JS is J + 1. % (IS, JS) next box is (I, J)
next(I, 9, IS, 1) :- IS is I + 1.

% from left to right and from top to bottom
nth(1, [L | _ ], L) :- !.
nth(I, [_ | S], L) :- IS is I - 1, nth(IS, S, L).

% The predicate column (J, S, C) is true when C is the column rank J
% of the matrix S.
column(_, [], []).
column(J, [L | S], [E | C]) :- nth(J, L, E), column(J, S, C).

count(1, X, [X]).
count(N, X, [X | L]) :- 1 < N, NM is N - 1, count(NM, X, L).



% The predicate square (I, J, S, M) is true when M is the sub-matrix
% at the location (I, J).
square(I, J, [[A,B,C|_], [D,E,F|_], [G,H,K|_] |_],[A,B,C,D,E,F,G,H,K]) :- I < 4, J < 4.

square(I, J, [[_,_,_,A,B,C|_], [_,_,_,D,E,F|_], [_,_,_,G,H,K|_] |_], [A,B,C,D,E,F,G,H,K]) :- I < 4, 3 < J, J < 7.

square(I, J, [[_,_,_,_,_,_,A,B,C], [_,_,_,_,_,_,D,E,F],
[_,_,_,_,_,_,G,H,K] |_], [A,B,C,D,E,F,G,H,K]) :- I < 4, 6 < J.

square(I, J, [_,_,_, [A,B,C|_], [D,E,F|_], [G,H,K|_] |_], [A,B,C,D,E,F,G,H,K]) :- 3 < I, I < 7, J < 4.

square(I, J, [_,_,_,[_,_,_,A,B,C|_], [_,_,_,D,E,F|_], [_,_,_,G,H,K|_] |_],[A,B,C,D,E,F,G,H,K]) :- 3 < I, I < 7, 3 < J, J < 7.
square(I, J, [_,_,_,[_,_,_,_,_,_,A,B,C], [_,_,_,_,_,_,D,E,F], [_,_,_,_,_,_,G,H,K] |_],[A,B,C,D,E,F,G,H,K]) :- 3 < I, I < 7, 6 < J.

square(I, J, [_,_,_,_,_,_, [A,B,C|_], [D,E,F|_], [G,H,K|_]], [A,B,C,D,E,F,G,H,K]) :- 6 < I, J < 4.

square(I, J, [_,_,_,_,_,_,[_,_,_,A,B,C|_], [_,_,_,D,E,F|_], [_,_,_,G,H,K|_]],[A,B,C,D,E,F,G,H,K]) :- 6 < I, 3 < J, J < 7.

square(I, J, [_,_,_,_,_,_,[_,_,_,_,_,_,A,B,C], [_,_,_,_,_,_,D,E,F], [_,_,_,_,_,_,G,H,K] ],[A,B,C,D,E,F,G,H,K]) :- 6 < I, 6 < J.



place(X, 1, 1, [[_ | L] | S], [[X | L] | S]) :- !.

place(X, 1, J, [[Y | L] | S], [[Y | NL] | S]) :- 1 < J, 
  JM is J - 1, 
  place(X, 1, JM, [L | S], [NL | S]).

place(X, I, J, [L | S], [L | NS]) :- 1 < I, 
  IM is I - 1, 
  place(X, IM, J, S, NS).


showPuzzle([]).
showPuzzle([L | S]) :- write(L), nl, see(S).

construct(10, 1, S, S) :- see(S).
construct(I, J, S, R) :- square(I, J, S, M), 
  nth(I, S, L), column(J, S, C),
  app(X, [1,2,3,4,5,6,7,8,9]), napp(X, L), napp(X, M), napp(X, C),
  place(X, I, J, S, NS), next(I, J, IS, JS), construct(IS, JS, NS, R).

sudoku(R) :- count(9, 0, L), count(9, L, S), construct(1, 1, S, R).

color(red).
color(green).
color(blue).