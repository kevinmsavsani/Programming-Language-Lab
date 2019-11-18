{-
    run command - ghci -W 1.hs
                  m [[1,2],[3,5]]
                  :quit
                      or
                  ghci
                  :l 1        -- for loading 1.hs
                  m [[1,2],[3,5]]       -- m is function name and other is argument
                  :r                for reload
                  :t m              give m :: [[Int]] -> Int
-}

import Data.List
import Data.Ord

-- Function m for task a
m :: [[Int]] -> Int
m [] = 1                -- if list is empty return 1
m (x:xs) = sum x * m xs -- sums elements in list x and calls m for next list recursively and multiply all of them



greatest f xss = snd $ maximum $ [(f xs, xs) | xs <- xss]


printElements [] =  putStrLn ""
printElements (x:xs) = putStrLn x >> printElements xs


-- Function toList for task b
toList xs = do
            printList xs
            putStrLn ""

printList [] = do                    -- If list is empty
               putStr " Empty "
               return()
printList [x] = do                    -- If list have only one list
                putStr "Cons "
                putStr . show $ x
                putStr " Empty "
printList (x:xs) = do                -- If list have one than one list
  putStr " Cons "
  putStr . show $ x
  putStr " ("
  printList xs                       -- call same function for remaining lists
  putStr ")"



data List a = Empty | Cons a (List a) deriving Show  --Haskell list declaration


-- Function for task c

toHaskellList :: (List e) -> [e]            -- add e in list
toHaskellList Empty = []                    -- if input is empty create empty Haskell list
toHaskellList (Cons el rem) = el : toHaskellList rem     --get input in given format store first element after "Cons" in el
                                                        -- add el into list






