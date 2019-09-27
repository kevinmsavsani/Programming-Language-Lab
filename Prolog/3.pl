
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

edge(g15,g16,5).
start(g1).
start(g2).
start(g3).
start(g4).
end(g16).
end(g17).


% if he specify from which gate to which gate
routing(FromCity, ToCity, [FromCity, ToCity]) :-
  edge(FromCity, ToCity, _).

routing(FromCity, ToCity, [FromCity|Connections]) :-
  edge(FromCity, ToConnection, _),
  routing(ToConnection, ToCity, Connections).

% find all edge from all gate
all_path(L) :-
    all_path(_,_,L).
all_path(Start,End,L) :-
    start(Start),
    end(End),
    routing(Start, End, L).

%path(X, Y, N, [X-Y]) :- edge(X, Y, N).
%path(X, Z, N, [X-Y|T]) :-
%    edge(X, Y, N0),
%    path(Y, Z, N1, T),
%    N is N0 + N1.

path(X, Y, N, Path) :- path(X, Y, N, [], Path).

path(X, Y, N, Seen, [X]) :-
    \+ memberchk(X, Seen),
    edge(X, Y, N).
path(X, Z, N, Seen, [X|T]) :-
    \+ memberchk(X, Seen),
    edge(X, Y, N0),
    path(Y, Z, N1, [X|Seen], T),
    \+ memberchk(X, T),
    N is N0 + N1.

memberchk(X, L) :- once(member(X, L)).
member(X, [X|_]).
member(X, [_|Xs]) :- member(X, Xs).

uniq_shortest_path(X, Y, MinCost, Path) :-
    path(X, Y, MinCost, Path),
    \+ (path(X, Y, LowerCost, OtherPath),
        OtherPath \= Path,
        LowerCost =< MinCost).

% Prolog Conventions


% to get min weight path
%path(S,D,TDist):-
%    edge(S,D,TDist).
%path(S,D,TDist):-
%    edge(S,X,TD1), path(X,D,TD2), TDist=TD1+TD2.
%
%aggregate(min(D), path(g1,g17,D), D).


% findminpath(g1,g17,W,P).
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