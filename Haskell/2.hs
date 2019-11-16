
import Data.List
import Test.QuickCheck

anagrams x y = sort x == sort y

type Set a = [a]

powerset :: Set a -> Set (Set a)
powerset [] = [[]]
powerset (x:xs) = [x:ps | ps <- powerset xs] ++ powerset xs