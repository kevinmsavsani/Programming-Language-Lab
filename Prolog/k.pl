starter(1,"Corn Tikki",30).
starter(2,"Tomato Soup", 20).
starter(3,"Chilli Paneer", 40).
starter(4,"Crispy chicken", 40).
starter(5,"Papdi Chaat", 20).
starter(6,"Cold Drink", 20).

mainDish(1,"Kadhai Paneer with Butter / Plain Naan" ,50).
mainDish(2,"Veg Korma with Butter / Plain Naan" ,40).
mainDish(3,"Murgh Lababdar with Butter / Plain Naan"  ,50).
mainDish(4,"Veg Dum Biryani with Dal Tadka" ,50).
mainDish(5,"Steam Rice with Dal Tadka" ,40).

dessert(1,"Ice-cream" ,20).
dessert(2,"Malai Sandwich" ,30).
dessert(3,"Rasmalai" ,10).

writer1([]) :- writeln("").
writer1([A|T]) :-  write(" , "),write(A),writer1(T).
writer([A|T]) :- write(A),writer1(T).

k(0,_,_).
k(N,M,S,L) :- dessert(N,A,S1),S2 is S+S1, M=< S2, writer(L), write(" , "),write(A), k(N,M,S2,A/L).
k(N,M,S,L) :- dessert(N,A,S1),S2 is S+S1, M > S2, k(N-1,M,S,L).

find_items(diet,0,0,1) :- k(3,40,0,[]).