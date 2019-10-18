
% menu(diet,1,0,0).
% menu(hungry,X,Y,Z).
% menu(diet,X,Y,Z). to get all output press tab after each output
% find_items(not_so_hungry,1,1,1).
% find_items(diet,0,1,0).

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

%% subset(X, Y) :- X is a subset of Y
%subset([], []).
%subset([H|T1], [H|T2]) :- subset(T1, T2).
%subset(L, [_|T]) :- subset(L, T).
%
%
%
%% sum(L, N) :- sum of elements in L is equal to N
%sum([], 0).
%sum([[A,H]|T], N) :- sum(T, N1), N is N1 + H.



writer1([]) :- writeln("").
writer1([[A,H]|T]) :-  write(" , "),write(A),writer1(T).
writer([[A,H]|T]) :- write(A),writer1(T).

%find_items(diet,0,0,1) :- findall([A,Y],dessert(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).
%find_items(diet,0,1,0) :- findall([A,Y],mainDish(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).
%find_items(diet,1,0,0) :- findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M =< 40, M > 0,write("Items: "), writer(X).
%find_items(notSoHungry,1,1,0) :- mainDish(B,C),findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80,M > 0,write("Items: "), write(B),write(" , "),writer(X).
%find_items(notSoHungry,0,1,1) :- mainDish(B,C), findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80, M > 0,write("Items: "), write(B),write(" , "), writer(X).



%find_items(notSoHungry,1,1,0) :- findall([I,J],mainDish(I,J),K), subset(L,K), sum(L,C),findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80,M > 0,write("Items: "), write(B),write(" , "),writer(X).
%find_items(notSoHungry,0,1,1) :- findall([I,J],mainDish(I,J),K), subset(L,K), sum(L,C), findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+C =< 80, M > 0,write("Items: "), write(B),write(" , "), writer(X).
%find_items(diet,0,0,1) :- dessert(I,J),findall([A,Y],dessert(A,Y),R), subset(X,R), sum(X,M), M+J =< 40,write("Items: "), writer(X).
%find_items(diet,0,1,0) :- mainDish(I,J),findall([A,Y],mainDish(A,Y),R), subset(X,R), sum(X,M), M+J =< 40,write("Items: "), writer(X).
%find_items(diet,1,0,0) :- starter(I,J),findall([A,Y],starter(A,Y),R), subset(X,R), sum(X,M), M+J =< 40,write("Items: "), writer(X).


% subset(X, Y) :- X is a subset of Y
subset([], [],0,M).
subset(L, [_|T],S,M) :- subset(L, T , S1,M), S = S1.
subset([[H,A]|T1], [[H,A]|T2],S,M) :- subset(T1, T2, S1,M), S = S1 + A,S=<M.
subset([[]|T1], [[H,A]|T2],S,M) :- subset(T1, T2, S1,M), S = S1 + A,S>M,S =S1.

find_items(diet,0,0,1) :- findall([A,Y],dessert(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(diet,0,1,0) :- findall([A,Y],mainDish(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(diet,1,0,0) :- findall([A,Y],starter(A,Y),R), subset(X,R,M,40), M > 0,write("Items: "), writer(X).
find_items(notSoHungry,1,1,0) :- mainDish(B,C),findall([A,Y],starter(A,Y),R), subset(X,R,M,80-C),M > 0,write("Items: "), write(B),write(" , "),writer(X).
find_items(notSoHungry,0,1,1) :- mainDish(B,C), findall([A,Y],starter(A,Y),R), subset(X,R,M,80-C), M > 0,write("Items: "), write(B),write(" , "), writer(X).
