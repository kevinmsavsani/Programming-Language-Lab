Prolog installation in ubuntu terminal :-
sudo apt-get update
sudo add-apt-repository ppa:swi-prolog/stable
sudo apt-get install swi-prolog

Problem statement 1 --
run command -  swipl -s 1.pl
test cases execute  - decode(“7”).
                    - decode(“25”).
 decode print number of possible way to decode the string.
 
 Problem statement 2 --
 run command -  swipl -s 2.pl
 test cases execute -   menu(Status,X,Y,Z).
                        find_items(hungry,1,1,1).
                        find_items(notSoHungry,1,1,0).
                        find_items(notSoHungry,0,1,1).
                        find_items(diet,1,0,0).
                        find_items(diet,0,1,0).
                        find_items(diet,0,0,1).
                        find_items_repetition(diet,1,0,0).
                        find_items_repetition(diet,0,1,0).
                        find_items_repetition(diet,0,0,1).
                        find_items_complex(notSoHungry,0,1,1).   
                        find_items_complex(notSoHungry,1,1,0).  
 
 Problem statement 3 --
  run command -  swipl -s 3.pl
  test cases execute -  all_path.
                        path_undirected(g1,g17,P).
                        path_without_cycle_undirected(g1,g17,P).
                        path_without_cycle_directed(g1,g17,P).
                        valid([g1, g6, g8, g9, g10, g15, g13, g14, g17]).
                        valid_endpoints([g1, g6, g8, g9, g10, g15, g13, g14, g17]).
                        optimal(X).                                    