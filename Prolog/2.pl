
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
find_items(notSoHungry,1,1,0) :- starter(A,X),mainDish(B,Y),write("Items: "),write(A),write(" , "),write(B).
find_items(notSoHungry,0,1,1) :- mainDish(B,Y),dessert(C,Z),write("Items: "),write(B),write(" , "),write(C).
find_items(diet,0,0,1) :- dessert(C,Z),Z<41,write("Items: "),write(C).
find_items(diet,0,1,0) :- mainDish(B,Y),Y<41,write("Items: "),write(B).
find_items(diet,1,0,0) :- starter(A,X),X<41,write("Items: "),write(A).




