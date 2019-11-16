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

m :: [[Int]] -> Int
m [] = 1
m (x:xs) = sum x * m xs


greatest f xss = snd $ maximum $ [(f xs, xs) | xs <- xss]

printElements [] =  putStrLn ""
printElements (x:xs) = putStrLn x >> printElements xs

toList xs = do
            printList xs
            putStrLn ""

printList [] = do
               putStr " Empty "
               return()
printList [x] = do
                putStr "Cons "
                putStr . show $ x
                putStr " Empty "
printList (x:xs) = do
  putStr " Cons "
  putStr . show $ x
  putStr " ("
  printList xs
  putStr ")"


data List a = Empty | Cons a (List a) deriving Show

toHaskellList :: (List e) -> [e]
toHaskellList Empty = []
toHaskellList (Cons el rem) = el : toHaskellList rem



