module Main where

import System.IO
import Control.Monad (replicateM)
import Data.Function (on)
import Data.List     (sortBy)
import System.Random (randomRIO)
import Control.Applicative((<$>))

combine [] _ = []
combine _ [] = []
combine (x:xs) (y:ys) = (x ++ " vs " ++ y) : combine xs ys

combine1 [] _ = []
combine1 _ [] = []
combine1 (x:xs) (y:ys) = (x ++ " " ++ y) : combine1 xs ys

--randomize :: [a] -> IO [a]
randomize xs = do
  ys <- replicateM (length xs) $ randomRIO (1 :: Int, 100000)
  pure $ map fst $ sortBy (compare `on` snd) (zip xs ys)


saveArr xs = do
          writeFile "test.txt" (unlines xs)

--main :: IO ()
main = do
  putStrLn "not randomized"
  let list = ["A","B","C","D","E","F","G","H","I","J","K","L"]
  ranl <- randomize list
  let (list1,list2) = splitAt 6 ranl
  print list1
  print list2
  let list3 = combine list1 list2
  print list3
  let date = ["1-11 9:30 AM","1-11 7:30 PM","2-11 9:30 AM","2-11 7:30 PM","3-11 9:30 AM","3-11 7:30 PM"]
  print date
  putStrLn "randomized"
  bar <- randomize list3
  randate <- randomize date
  let list4 = combine1 bar randate
  print list4
  saveArr list4



printElements [] = return ()
printElements (x:xs) = do putStrLn x
                          printElements xs

split x y = func x y [[]]
    where
        func x [] z = reverse $ map (reverse) z
        func x (y:ys) (z:zs) = if y==x then
            func x ys ([]:(z:zs))
        else
            func x ys ((y:z):zs)

checkString x team = do let a = split ' ' x
                        if team == a!!0
                             then do putStrLn x
                             else if team == a!!2
                                       then do putStrLn x
                                       else return ()

checkElements [] team = return ()
checkElements (x:xs) team = do checkString x team
                               checkElements xs team

allMatch = do
   content <- readFile "test.txt"
   let linesOfFiles = lines content
   printElements linesOfFiles

match team = do
   content <- readFile "test.txt"
   let linesOfFiles = lines content
   checkElements linesOfFiles team

fixture team = do
   if team == "all"
     then do allMatch
     else match team

checkmatchString x date time = do
                                if time < 9.5
                                     then do let m = "9:30"
                                             checkdatematchString x date m
                                     else if time < 19.5
                                          then do let m = "7:30"
                                                  checkdatematchString x date m
                                          else if time < 24
                                              then do let m = "9:30"
                                                      if date == "1-11"
                                                          then do checkdatematchString x "2-11" m
                                                          else if date == "2-11"
                                                              then do checkdatematchString x "3-11" m
                                                              else return ()
                                              else return ()


checkdatematchString x date time = do let a = split ' ' x
                                      if date == a!!3
                                           then do if time == a!!4
                                                        then putStrLn x
                                                        else return ()
                                           else return ()

checkmatch [] date time = return ()
checkmatch (x:xs) date time = do checkmatchString x date time
                                 checkmatch xs date time

nextmatch date time = do
   content <- readFile "test.txt"
   let linesOfFiles = lines content
   let s = show date
   let b = s ++ "-11"
   checkmatch linesOfFiles b time

