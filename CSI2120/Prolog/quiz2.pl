solveQuadratic(A,B,C,X) :- discriminant(A,B,C,Discriminant),
              		   root2(A,B,C,Discriminant,X).

discriminant(A,B,C,Discriminant) :- Discriminant is B*B-4*A*C.

root2(A,B,_,0,X):- X is -B/(2*A).

root2(A,B,_,Discriminant,X):- Discriminant>0, Z is sqrt(Discriminant),
X is (-B - Z)/(2*A).

root2(A,B,_,Discriminant,X):- Discriminant>0, Z is sqrt(Discriminant),
X is (-B + Z)/(2*A).
