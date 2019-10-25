
decode(String,Sum) :-
    string_chars(String, List),
    list_sum(List,Sum,_).

isNextLessThanSeven([X|_]) :- atom_number(X,N), N<7.

list_sum([], 1, 0).
list_sum([X|Xs], Sum, Ci) :-
    list_sum(Xs, S0 , C0),
    atom_number(X,N),
    (   N = 0 ->
        Sum is 0,
        Ci is S0
    ;   (N =:= 1 ; N =:= 2, isNextLessThanSeven(Xs) )->
        Sum is S0 +C0,
        Ci is S0
    ;   Sum is S0 ,
        Ci is S0
    ).