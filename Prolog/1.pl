isOne(N) :- N=1.
isTwo(N) :- N=2.
isGreaterThanZero(N) :- N>=0.
isLessThanSix(N) :- N>=0,N<7.


decode(S1) :-
    string_chars(S1, L1),
    decode_List(L1).

decode_head(L) :- write(L),atom_number(L,N),isLessThanSix(N).

decode_List([]).
decode_List([H|T]) :- decode_head(H),decode_List(T).

fib(0, 1) :- !.
fib(1, 1) :- !.
fib(N, F) :-
        N > 1,
        N1 is N-1,
        N2 is N-2,
        fib(N1, F1),
        fib(N2, F2),
        F is F1+F2.