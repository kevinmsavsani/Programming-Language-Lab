

decode(S1,Sum) :-
    string_chars(S1, L1),
    list_sum(L1,Sum,C).
%    decode_List(L1).

%decode_head(L) :- write(L),atom_number(L,N),isLessThanSix(N).
%
%decode_List([]).
%decode_List([H|T]) :- decode_head(H),decode_List(T).

isNextGreaterThanSix([]) :- !.
isNextLessThanSeven([]) :- !.
isNextGreaterThanSix([X|Xs]) :- atom_number(X,N), N>6.
isNextLessThanSeven([X|Xs]) :- atom_number(X,N), N<7.

list_sum([], 1, 0).
list_sum([X|Xs], Sum, Ci) :-
    atom_number(X,N),
    N=0,
    list_sum(Xs, S0 , C0),
    Sum is 0,
    Ci is S0.
list_sum([X|Xs], Sum, Ci) :-
    atom_number(X,N),
    N=1,
    list_sum(Xs, S0, C0),
    Sum is S0 +C0,
    Ci is S0.
%    writeln(" "),
%    write(X),
%    write(" "),
%    write(Xs),
%    write(" "),
%    write(Sum),
%    write(" "),
%    write(S0),
%    write(" "),
%    write(Ci),
%    write(" "),
%    write(C0).
list_sum([X|Xs], Sum, Ci) :-
    atom_number(X,N),
    N=2,
    isNextLessThanSeven(Xs),
    list_sum(Xs, S0, C0),
    Sum is S0 + C0,
    Ci is S0.
%    writeln(" "),
%    write(X),
%    write(" "),
%    write(Xs),
%    write(" "),
%    write(Sum),
%    write(" "),
%    write(S0),
%    write(" "),
%    write(Ci),
%    write(" "),
%    write(C0).
list_sum([X|Xs], Sum, Ci) :-
    atom_number(X,N),
    N =2,
    isNextGreaterThanSix(Xs),
    list_sum(Xs, S0, C0),
    Sum is S0,
    Ci is S0.
%    writeln(" "),
%    write(X),
%    write(" "),
%    write(Xs),
%    write(" "),
%    write(Sum),
%    write(" "),
%    write(S0),
%    write(" "),
%    write(Ci),
%    write(" "),
%    write(C0).
list_sum([X|Xs], Sum, Ci) :-
    atom_number(X,N),
    N>2,
    list_sum(Xs, S0, C0),
    Sum is S0,
    Ci is S0.
%    writeln(" "),
%    write(X),
%    write(" "),
%    write(Xs),
%    write(" "),
%    write(Sum),
%    write(" "),
%    write(S0),
%    write(" "),
%    write(Ci),
%    write(" "),
%    write(C0).

