% fact containing all edges with start,end ,weight of edge
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

% start cantaing gate name for entry and end cantaing gate name for exit
start(g1).
start(g2).
start(g3).
start(g4).
end(g17).

% print list of path by taking head out recursively and printing it
writer1([]) :- writeln("").
writer1([H|T]) :-  write(" , "),write(H),writer1(T).
writer([H|T]) :- write("Path: "),write(H),writer1(T).

% it give path without cycle considering given edge are directed
path_without_cycle_directed(X,Y,_,[X,Y]) :- edge(X,Y,_).
path_without_cycle_directed(X,Y,L,[X|R]) :- edge(X,Z,_), \+member(Z,L),
                          path_without_cycle_directed(Z,Y,[Z|L],R), \+member(X,R).
path_without_cycle_directed(X,Y,R):-path_without_cycle_directed(X,Y,[],R),writer(R).


% it give path without cycle considering given edge are undirected
path_without_cycle_undirected(X,Y,_,[X,Y]) :- (edge(X,Y,_);edge(Y,X,_)).
path_without_cycle_undirected(X,Y,L,[X|R]) :- (edge(X,Z,_);edge(Z,X,_)), \+member(Z,L),
                          path_without_cycle_undirected(Z,Y,[Z|L],R), \+member(X,R).
path_without_cycle_undirected(X,Y,R):-path_without_cycle_undirected(X,Y,[],R),writer(R).


% it give path with cycle considering given edge are undirected
path_undirected(Start, End, [Start, End]) :- edge(Start, End, _).
path_undirected(Start, End, [Start|Connections]) :- (edge(Start, ToConnection, _);edge(ToConnection, Start, _)),
                                                             path_undirected(ToConnection, End, Connections).

% it give path with cycle considering given edge are directed
path_directed(Start, End, [Start, End]) :- edge(Start, End, _).
path_directed(Start, End, [Start|Connections]) :- edge(Start, ToConnection, _),
                                                           path_directed(ToConnection, End, Connections).

% print all path from jail to exit
all_path :-
    findall(_,all_path(_,_,_),_).
all_path(Start,End,L) :-
    start(Start),
    end(End),
    path_directed(Start, End, L),writer(L).


% check if edge exist
check(_,[]).
check(X,[Xt|_]) :- edge(X,Xt,_).
check(X,[Xt|_]) :- edge(Xt,X,_).

% recursively go to end of list then check each pair that edge exist or not
valid([]).
valid([X|Xs]) :-
    valid(Xs),
    check(X,Xs).


% check if edge exist and check last gate is exit
check_endpoints(X,[]) :- end(X).
check_endpoints(X,[Xt|_]) :- edge(X,Xt,_).
check_endpoints(X,[Xt|_]) :- edge(Xt,X,_).

% check if starting vertex is jail
check_start([X|_]) :- start(X).

% check if starting vertex is jail and then validate all other pair to have an edge
valid_endpoints([]).
valid_endpoints(Xs) :-
    check_start(Xs),valid_check_endpoints(Xs).

% recursively go to end of list and check end to be exit and then check between each pair of path that edge exist or not
valid_check_endpoints([]).
valid_check_endpoints([X|Xs]) :-
    valid_check_endpoints(Xs),
    check_endpoints(X,Xs).

% find a path between X and Y and return weight with path
findapath(X, Y, W, [X,Y], _) :- edge(X, Y, W).
findapath(X, Y, W, [X|P], V) :- \+ member(X, V),
                                 edge(X, Z, W1),
                                 findapath(Z, Y, W2, P, [X|V]),
                                 W is W1 + W2.

find_shortest_path([], _, MinPath, MinPath).

find_shortest_path([(C, Path)|Paths], MinLen, _, Output) :-
    C < MinLen,
    find_shortest_path(Paths, C, Path, Output).

find_shortest_path([(C, _)|Paths], MinLen, MinPath, Output) :-
    C >= MinLen,
    find_shortest_path(Paths, MinLen, MinPath, Output).

optimal(X) :-
    findall((W,P),(start(X),end(Y),findapath(X, Y, W, P, [])), R),
    findall(W, edge(_, _, W), L),
    sumlist(L, Sum),
    find_shortest_path(R, Sum, [], X).
