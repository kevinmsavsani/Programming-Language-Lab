module Anagram where

import Data.List

anagrams x y = sort x == sort y   -- if both strings are equal after sort than they are anagram else not

printAnagram t [] = (0,[])                                 -- Break condition when remaining string is empty return 0
printAnagram t (x:xs) = do if anagrams t x                 -- check if both strings t and x  are anagram
                              then do let (z,a) = printAnagram t xs in (z+1,a ++ [(t,x)])   --check if t is anagram with any remaining substrings, increment z and add both x and t to ans list
                              else let (z,a) = printAnagram t xs in (z,a)     -- if t and x are not anagrma check for t and remaing substrings


printElements [] = (0,[])        -- break condition when list is empty return 0 number of anagrams and empty list of anagrams
printElements (x:xs) = do let (y,a) = printAnagram x xs    -- function call for printAnagram
                          let (z,b) = printElements xs     -- recursion call to check for anagrams in remaining substrings
                          (z+y,a++b)                      --  add results of both , current substring's and remaining substrings

nonEmptySubstrings :: [a] -> [[a]]
nonEmptySubstrings x = concatMap  (tail . inits) ( tails x)    -- get all nonempty substring

check x = do if (length x) /= 2                         -- Check if there are exactly two lists
                 then do print "Please enter string in valid format"      -- If no error message
                 else do let m = concat x              -- concatenate both lists
                         let t = nonEmptySubstrings m   -- Function call for nonEmptySubstrings function and t will contain all subsets
                         let (a,b) = printElements t   -- Function all for printElements fuction and a will be containing number of anagrams
                         print a                        -- b will be containing list of all anagrams
                         print b
