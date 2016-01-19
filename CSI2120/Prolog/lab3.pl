countDown(N):- writeln(N), Z is N-1, N>0,!,countDown(Z). 

% ex2

element(chlorine,'cl').
element(helium,'he').
element(hydrogen,'h').
element(nitrogen,'n').
element(oxygen,'o').

find(_):- writeln('Elements in the Periodic Table'), write('Symbol to look-up: ') ,read(Z), write(Z),element(E,Z), write('Element '),write(Z),write(' is '),write(E).

%ex 3 
animal(hamster).
animal(elephant).
animal(snake).
small(snake).
small(hamster).
poisonous(snake).
color(elephant,grey).
color(snake,grey).

likes(jane,X) :- animal(X),cute(X).

cute(X) :- poisonous(X),!,fail.
cute(X) :- small(X).
cute(X) :- color(X,grey).
