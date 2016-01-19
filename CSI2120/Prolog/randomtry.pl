exampleGraph([v(a, 1, [b, c]), v(b, 3, [d]), v(c, 2, [d, e]), v(d, 5, [f]), v(e, 3, [f]), v(f, 3, [])]).
     pathValue(Path, Value) :-
       exampleGraph(G),
       pathValue(Path, Value, G).

pathValue([N], Value, Graph) :- member(v(N, Value, _), Graph).
pathValue([N1, N2 | Ns], Value, Graph) :- member(v(N1, V, Successors), Graph), member(N2, Successors),
pathValue([N2 | Ns], Value2, Graph), Value is V + Value2.