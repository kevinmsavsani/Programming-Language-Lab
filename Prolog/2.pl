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

subset([], [],0,_).
subset(L, [_|T],S,M) :- subset(L, T , S1,M), S = S1.
subset([[H,A]|T1], [[H,A]|T2],S,M) :- subset(T1, T2, S1,M), S = S1 + A,S=<M.
subset([[]|T1], [[_,A]|T2],S,M) :- subset(T1, T2, S1,M), S = S1 + A,S>M,S =S1.


find_item(hungry,1,1,1) :- starter(A,_),mainDish(B,_),dessert(C,_),write("Items: "),write(A),write(" , "),write(B),write(" , "),writeln(C).
find_item(notSoHungry,1,1,0) :- mainDish(B,C), starter(A,M), M+C =< 80,write("Items: "), write(A),write(" , "),writeln(B).
find_item(notSoHungry,0,1,1) :- mainDish(B,C), dessert(A,M), M+C =< 80,write("Items: "), write(A),write(" , "), writeln(B).
find_item(diet,0,0,1,R) :- subset(X,R,M,40), M > 0,writer(X).
find_item(diet,0,1,0,R) :- subset(X,R,M,40), M > 0,writer(X).
find_item(diet,1,0,0,R) :- subset(X,R,M,40), M > 0,writer(X).

find_items(hungry,1,1,1) :- findall(_,find_item(hungry,1,1,1),_).
find_items(notSoHungry,1,1,0) :- findall(_,find_item(notSoHungry,1,1,0),_).
find_items(notSoHungry,0,1,1) :- findall(_,find_item(notSoHungry,0,1,1),_).
find_items(diet,0,0,1) :- findall([A,Y],(dessert(A,Y),Y=<40),R), findall(_,find_item(diet,0,0,1,R),_).
find_items(diet,0,1,0) :- findall([A,Y],(mainDish(A,Y),Y=<40),R), findall(_,find_item(diet,0,1,0,R),_).
find_items(diet,1,0,0) :- findall([A,Y],(starter(A,Y),Y=<40),R), findall(_,find_item(diet,1,0,0,R),_).

writer1([]) :- writeln("").
writer1([[A,_]|T]) :-  write(" , "),write(A),writer1(T).
writer([[A,_]|T]) :- write("Items: "), write(A),writer1(T).

adds([],0).
adds([_,A],A).
fill([], _, 0,_,S) :- S=0.
fill([], _, N,M,S) :- N < M,N>0,S=0.
fill([X|Xs], X, N,M,S) :- succ(N0, N), fill(Xs, X, N0,M,S1),adds(X,A),S is S1 +A.

append([],L,L).
append([H|T],L2,[H|L3])  :-  append(T,L2,L3).

subset_repe([], [],0,_).
subset_repe(L, [_|T],S,M) :- subset_repe(L, T , S1,M), S = S1.
subset_repe(R, [[H,A]|T2],S,M) :- subset_repe(T1, T2, S1,M-A),  N is div((M-S1),A), N>0, fill(R2,[H,A],N,N,S4),S is S4+S1,append(R2,T1,R).

find_item_repe(diet,0,0,1,R) :- subset_repe(X,R,M,40), M > 0,writer(X).
find_item_repe(diet,0,1,0,R) :- subset_repe(X,R,M,40), M > 0,writer(X).
find_item_repe(diet,1,0,0,R) :- subset_repe(X,R,M,40), M > 0,writer(X).

find_items_repe(diet,0,0,1) :- findall([A,Y],(dessert(A,Y),Y=<40),R), findall(_,find_item_repe(diet,0,0,1,R),_).
find_items_repe(diet,0,1,0) :- findall([A,Y],(mainDish(A,Y),Y=<40),R), findall(_,find_item_repe(diet,0,1,0,R),_).
find_items_repe(diet,1,0,0) :- findall([A,Y],(starter(A,Y),Y=<40),R), findall(_,find_item_repe(diet,1,0,0,R),_).

writer3([]) :- write("").
writer3([[A,_]|T]) :-  write(" , "),write(A),writer3(T).
writer2([[A,_]|T]) :- write(A),writer3(T).
find_item_complex(notSoHungry,1,1,0,K,R) :- subset(L,K,C,80),C>0, subset(X,R,M,80-C), M>0, write("Items: "), writer2(L),write(" , "),writer2(X),writeln("").
find_item_complex(notSoHungry,0,1,1,K,R) :- subset(L,K,C,80),C>0, subset(X,R,M,80-C), M>0 , write("Items: "), writer2(L), write(" , "),writer2(X),writeln("").

find_items_complex(notSoHungry,1,1,0) :- findall([I,J],mainDish(I,J),K), findall([A,Y],starter(A,Y),R),findall(_,find_item_complex(notSoHungry,0,1,1,K,R),_).
find_items_complex(notSoHungry,0,1,1) :- findall([I,J],mainDish(I,J),K), findall([A,Y],dessert(A,Y),R),findall(_,find_item_complex(notSoHungry,0,1,1,K,R),_).