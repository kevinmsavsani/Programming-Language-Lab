
import Data.List

anagrams x y = sort x == sort y && length x == length y

printAnagram t [] = 0
printAnagram t (x:xs) = do if anagrams t x
                              then do let z = printAnagram t xs in z+1
                              else let z = printAnagram t xs in z


printElements [] = 0
printElements (x:xs) = do let y = printAnagram x xs
                          let z = printElements xs in z+y

continuousSubSeqs = filter (not . null) . concatMap inits . tails


check x = do let m = concat x
             let t = continuousSubSeqs m
             printElements t
