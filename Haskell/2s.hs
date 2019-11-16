
import Data.List
import Test.QuickCheck

anagrams x y = sort x == sort y && length x == length y

printAnagram t [] = return ()
printAnagram t (x:xs) = do if anagrams t x
                              then do print t
                              else return ()
                           printAnagram t xs

printElements [] = return ()
printElements (x:xs) = do printAnagram x xs
                          printElements xs

continuousSubSeqs = filter (not . null) . concatMap inits . tails


check x = do let m = concat x
             let t = continuousSubSeqs m
             printElements t
