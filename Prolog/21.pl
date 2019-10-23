

starter("Corn Tikki",30).
starter("Tomato Soup", 20).
starter("Chilli Paneer", 40).
starter("Crispy chicken", 40).
starter("Papdi Chaat", 20).
starter("Cold Drink", 20).

mainDish("Kadhai Paneer with Butter / Plain Naan" ,50).
mainDish("Veg Korma with Butter / Plain Naan" ,40).
mainDish("Murgh Lababdar with Butter / Plain Naan"  ,50).
mainDish("Veg Dum Biryani with Dal Tadka" ,50).
mainDish("Steam Rice with Dal Tadka" ,40).

dessert("Ice-cream" ,20).
dessert("Malai Sandwich" ,30).
dessert("Rasmalai" ,10).

menu(hungry,1,1,1).
menu(notSoHungry,1,1,0).
menu(notSoHungry,0,1,1).
menu(diet,0,0,1).
menu(diet,0,1,0).
menu(diet,1,0,0).

find_items(hungry,1,1,1) :- starter(A,X),mainDish(B,Y),dessert(C,Z),write("Items: "),write(A),write(" , "),write(B),write(" , "),write(C).

sum([], 0).
sum([[]], 0).
sum([[]|T], N) :- sum(T, N1), N is N1.
sum([[A,H]|T], N) :- sum(T, N1), N is N1 + H.



writer1([]) :- writeln("").
writer1([[A,H]|T]) :-  write(" , "),write(A),writer1(T).
writer([[A,H]|T]) :- write(A),writer1(T).

subseq([H,A],S1,M,S,[H,A],N,A) :- P is A*N, S = S1 + P,S=<M.

adds([A],A).
fill([], _, N,M) :- N < M.
fill([X|Xs], X, N,M) :- succ(N0, N), fill(Xs, X, N0,M).

len([], LenResult):-
    LenResult is 0.

len([X|Y], LenResult):-
    len(Y, L),
    LenResult is L + 1.

combine(R1,T1,[R1|T1]).
% subset(X, Y) :- X is a subset of Y
subset([], [],0,M).
subset(L, [_|T],S,M) :- subset(L, T , S1,M), S = S1.
subset(R, [H|T2],S,M) :- subset(T1, T2, S1,M),  subseq(H,S1,M,S,R1,1,A), N is div((M-S1),A), write(N),write(M),write(S1),write(A), fill(R2,R1,N,N),write("a"),len(R2,S5), write("b"),S4 is S5*A,S is S4+S1,append(R2,T1,R).

append([],L,L).
append([H|T],L2,[H|L3])  :-  append(T,L2,L3).

find_items(diet,0,0,1) :- findall([A,Y],dessert(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(diet,0,1,0) :- findall([A,Y],mainDish(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(diet,1,0,0) :- findall([A,Y],starter(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(notSoHungry,1,1,0) :- mainDish(B,C), starter(A,M), M+C =< 80, M > 0,write("Items: "), write(B),write(" , "),writer(X).
find_items(notSoHungry,0,1,1) :- mainDish(B,C), dessert(A,M), M+C =< 80, M > 0,write("Items: "), write(B),write(" , "), writer(X).
