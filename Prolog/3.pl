
edge(g1,g5,4).
edge(g2,g5,6).
edge(g3,g5,8).
edge(g4,g5,9).
edge(g1,g6,10).
edge(g2,g6,9).
edge(g3,g6,3).
edge(g4,g6,5).
edge(g5,g7,3).
edge(g5,g10,4).
edge(g5,g11,6).
edge(g5,g12,7).
edge(g5,g6,7).
edge(g5,g8,9).
edge(g6,g8,2).
edge(g6,g12,3).
edge(g6,g11,5).
edge(g6,g10,9).
edge(g6,g7,10).
edge(g7,g10,2).
edge(g7,g11,5).
edge(g7,g12,7).
edge(g7,g8,10).
edge(g8,g9,3).
edge(g8,g12,3).
edge(g8,g11,4).
edge(g8,g10,8).
edge(g10,g15,5).
edge(g10,g11,2).
edge(g10,g12,5).
edge(g11,g15,4).
edge(g11,g13,5).
edge(g11,g12,4).
edge(g12,g13,7).
edge(g12,g14,8).
edge(g15,g13,3).
edge(g13,g14,4).
edge(g14,g17,5).
edge(g14,g18,4).
edge(g17,g18,8).

start(g1).
start(g2).
start(g3).
start(g4).
end(g17).

writer1([]) :- writeln("").
writer1([H|T]) :-  write(" , "),write(H),writer1(T).
writer([H|T]) :- write("Path: "),write(H),writer1(T).


show_path_directed(X,Y,R):-show_path_directed(X,Y,[],R),writer(R).

show_path_directed(X,Y,_,[X,Y]) :- edge(X,Y,_).
show_path_directed(X,Y,L,[X|R]) :- edge(X,Z,_), \+member(Z,L),
                          show_path_directed(Z,Y,[Z|L],R), \+member(X,R).

show_path_undirected(X,Y,R):-show_path_undirected(X,Y,[],R),writer(R).

show_path_undirected(X,Y,_,[X,Y]) :- (edge(X,Y,_);edge(Y,X,_)).
show_path_undirected(X,Y,L,[X|R]) :- (edge(X,Z,_);edge(Z,X,_)), \+member(Z,L),
                          show_path_undirected(Z,Y,[Z|L],R), \+member(X,R).


routing_undirected(FromCity, ToCity, [FromCity, ToCity]) :-
  edge(FromCity, ToCity, _).

routing_undirected(FromCity, ToCity, [FromCity|Connections]) :-
  (edge(FromCity, ToConnection, _);edge(ToConnection, FromCity, _)),
  routing_undirected(ToConnection, ToCity, Connections).

routing_directed(FromCity, ToCity, [FromCity, ToCity]) :-
  edge(FromCity, ToCity, _).

routing_directed(FromCity, ToCity, [FromCity|Connections]) :-
  edge(FromCity, ToConnection, _),
  routing_directed(ToConnection, ToCity, Connections).

all_path :-
    findall(_,all_path(_,_,_),_).
all_path(Start,End,L) :-
    start(Start),
    end(End),
    routing_directed(Start, End, L),writer(L).


check_endpoints(X,[]) :- end(X).
check_endpoints(X,[Xt|_]) :- edge(X,Xt,_).
check_endpoints(X,[Xt|_]) :- edge(Xt,X,_).

check_start([X|_]) :- start(X).

valid_endpoints([]).
valid_endpoints(Xs) :-
    check_start(Xs),valid_check_endpoints(Xs).

valid_check_endpoints([]).
valid_check_endpoints([X|Xs]) :-
    valid_check_endpoints(Xs),
    check_endpoints(X,Xs).

check(_,[]).
check(X,[Xt|_]) :- edge(X,Xt,_).
check(X,[Xt|_]) :- edge(Xt,X,_).


valid([]).
valid(Xs) :-
    valid_check(Xs).

valid_check([]).
valid_check([X|Xs]) :-
    valid_check(Xs),
    check(X,Xs).


findapath(X, Y, W, [X,Y], _) :- edge(X, Y, W).
findapath(X, Y, W, [X|P], V) :- \+ member(X, V),
                                 edge(X, Z, W1),
                                 findapath(Z, Y, W2, P, [X|V]),
                                 W is W1 + W2.

:-dynamic(solution/2).
findminpath(X, Y, W, P) :- \+ solution(_, _),
                           findapath(X, Y, W1, P1, []),
                           assertz(solution(W1, P1)),
                           !,
                           findminpath(X,Y,W,P).

findminpath(X, Y, _, _) :- findapath(X, Y, W1, P1, []),
                           solution(W2, P2),
                           W1 < W2,
                           retract(solution(W2, P2)),
                           asserta(solution(W1, P1)),
                           fail.

findminpath(_, _, W, P) :- solution(W,P), retract(solution(W,P)).

all_shortest_path(Weight,Path) :-
    all_shortest_path(_,_, Weight,Path).
all_shortest_path(Start,End,Weight,Path) :-
    start(Start),
    end(End),
    findminpath(Start, End, Weight, Path).


find_shortest_path([], _, MinPath, MinPath).

find_shortest_path([(C, Path)|Paths], MinLen, _, Output) :-
    C < MinLen,
    find_shortest_path(Paths, C, Path, Output).

find_shortest_path([(C, _)|Paths], MinLen, MinPath, Output) :-
    C >= MinLen,
    find_shortest_path(Paths, MinLen, MinPath, Output).

optimal(X) :-
    findall((D,P),all_shortest_path(D,P), R),
    findall(W, edge(_, _, W), L),
    sumlist(L, Sum),
    find_shortest_path(R, Sum, [], X).
