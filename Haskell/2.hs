module Anagram where

import Data.List

anagrams x y = sort x == sort y && length x == length y

printAnagram t [] = (0,[])
printAnagram t (x:xs) = do if anagrams t x
                              then do let (z,a) = printAnagram t xs in (z+1,a ++ [(t,x)])
                              else let (z,a) = printAnagram t xs in (z,a)


printElements [] = (0,[])
printElements (x:xs) = do let (y,a) = printAnagram x xs
                          let (z,b) = printElements xs
                          (z+y,a++b)

continuousSubSeqs = filter (not . null) . concatMap inits . tails

check x = do if (length x) /= 2
                 then do print "Please enter string in valid format"
                 else do let m = concat x
                         let t = continuousSubSeqs m
                         let (a,b) = printElements t
                         print a
                         print b

flatten [] = []
flatten ([]:vs) = flatten vs
flatten ((x:xs):vs) = x:flatten (xs:vs)