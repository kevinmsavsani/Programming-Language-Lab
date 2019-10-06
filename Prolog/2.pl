
% menu(diet,1,0,0)
% menu(hungry,X,Y,Z)
% menu(diet,X,Y,Z) to get all output press tab after each output
% find_items(not_so_hungry,1,1,1)
% find_items(diet,0,1,0)

starter(cornTikki,30).
starter(tomatoSoup, 20).
starter(chilliPaneer, 40).
starter(crispychicken, 40).
starter(papdiChaat, 20).
starter(coldDrink, 20).

mainDish(kadhaiPaneer ,50).
mainDish(vegKorma ,40).
mainDish(murghLababdar  ,50).
mainDish(vegDumBiryani ,50).
mainDish(steamRice ,40).

dessert(iceCream ,20).
dessert(malaiSandwich ,30).
dessert(rasmalai ,10).

menu(hungry,1,1,1).
menu(notSoHungry,1,1,0).
menu(notSoHungry,0,1,1).
menu(diet,0,0,1).
menu(diet,0,1,0).
menu(diet,1,0,0).

find_items(hungry,1,1,1) :- starter(A,X),mainDish(B,Y),dessert(C,Z),write("Items: "),write(A),write(" , "),write(B),write(" , "),write(C).
%find_items(notSoHungry,1,1,0) :- starter(A,X),mainDish(B,Y),X+Z =< 80,write("Items: "),write(A),write(" , "),write(B).
%find_items(notSoHungry,0,1,1) :- mainDish(B,Y),dessert(C,Z),Y+Z =< 80,write("Items: "),write(B),write(" , "),write(C).
%find_items(diet,0,0,1) :- dessert(C,Z),Z=<40,write("Items: "),write(C).
%find_items(diet,0,1,0) :- mainDish(B,Y),Y=<40,write("Items: "),write(B).
%find_items(diet,1,0,0) :- starter(A,X),X=<40,write("Items: "),write(A).


% subset(X, Y) :- X is a subset of Y
subset([], []).
subset([H|T1], [H|T2]) :- subset(T1, T2).
subset(L, [_|T]) :- subset(L, T).

% sum(L, N) :- sum of elements in L is equal to N
sum([], 0).
sum([[A,H]|T], N) :- sum(T, N1), N is N1 + H.

% subset_sum(X, L, N) :- subset X of L sums to N
subset_sum(X, N) :- findall([A,Y],dessert(A,Y),R), subset(X,R), sum(X,M), M =< N, M > 0.


writer([]) :- writeln("").
writer([[A,H]|T]) :- write(A), write(" , "),writer(T).

find_items(diet,0,0,1) :- findall([A,Y],dessert(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).
find_items(diet,0,1,0) :- findall([A,Y],mainDish(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).
find_items(diet,1,0,0) :- findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).

find_items(notSoHungry,1,1,0) :- mainDish(B,C),findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80,M > 0,write("Items: "), write(B),write(" , "),writer(X).
find_items(notSoHungry,0,1,1) :- mainDish(B,C), findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80, M > 0,write("Items: "), write(B),write(" , "), writer(X).

