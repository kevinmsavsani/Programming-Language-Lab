module Main where

import System.IO
import Control.Monad (replicateM)
import Data.Function (on)
import Data.List     (sortBy)
import System.Random (randomRIO,randomRs,mkStdGen)
import Control.Applicative((<$>))

list = ["BS","CM","CH","CV","CS","DS","EE","HU","MA","ME","PH","ST"]      -- initial team list

--Function to combine two team list
combine [] _ = []
combine _ [] = []
combine (x:xs) (y:ys) = (x ++ " vs " ++ y) : combine xs ys

--function to combine team list and time list
combine1 [] _ = []
combine1 _ [] = []
combine1 (x:xs) (y:ys) = (x ++ " " ++ y) : combine1 xs ys

--randomize :: [a] -> IO [a]
randomize xs = do
  ys <- replicateM (length xs)  (randomRIO (1 :: Int, 100000))  --generate list of random numbers map them with list of teams sort according to random numbers
  pure $ map fst ( sortBy (compare `on` snd) (zip xs ys))       -- this will shuffle the team list in random order every time


saveArr xs = do
          writeFile "test.txt" (unlines xs)

--main :: IO ()
main = do
  ranl <- randomize list   -- Randomly change order of elements of the list
  let (list1,list2) = splitAt 6 ranl          --split list into two equal half
  let list3 = combine list1 list2             -- combine both half to make pair of corresponding indexes
  let date = ["1-11 9:30 AM","1-11 7:30 PM","2-11 9:30 AM","2-11 7:30 PM","3-11 9:30 AM","3-11 7:30 PM"]  --list of available time slots
  bar <- randomize list3                      --Randomly change order of elements of the list
  let list4 = combine1 bar date               -- combine team pair and time slots lists
  saveArr list4                               --function call to save the final schedule



-- function to print elements of the list
printElements [] = return ()
printElements (x:xs) = do putStrLn x
                          printElements xs

-- function to split line on space
split x y = func x y [[]]
    where
        func x [] z = reverse $ map (reverse) z
        func x (y:ys) (z:zs) = if y==x then
            func x ys ([]:(z:zs))
        else
            func x ys ((y:z):zs)

checkString x team = do let a = split ' ' x    -- split line on space and get first element i.e. team name
                        if team == a!!0        -- if team in query equals first team in team list pair
                             then do putStrLn x  -- print the fixture of that team
                             else if team == a!!2  --if team in query equals second team in team list pair
                                       then do putStrLn x  -- print the fixture of that team
                                       else return ()

checkElements [] team = return ()
checkElements (x:xs) team = do checkString x team
                               checkElements xs team

--function to get fixture of all teams
allMatch = do
   content <- readFile "test.txt"     --read the stored schedule line by line
   let linesOfFiles = lines content
   printElements linesOfFiles          -- print line on console

-- function to get fixture of requested team
match team = do
   content <- readFile "test.txt"
   let linesOfFiles = lines content
   checkElements linesOfFiles team            -- function call for checkElements


fixture team = do          --function to get fixture
   if team == "all"        -- if request is to get all team's schedule call allMatch function
     then do allMatch
     else if team `elem` list   --find team in list
             then do match team
             else putStrLn "Please enter correct team"


-- function to get next match
checkmatchString x date time = do
                                if time <= 9.5                     -- if time is less than 9.5 than next match will be at 9:30
                                     then do let m = "9:30"
                                             checkdatematchString x date m  -- function call
                                     else if time <= 19.5
                                          then do let m = "7:30"    -- if time is less than 19.5 than next match will be at 7:30
                                                  checkdatematchString x date m
                                          else if time < 24
                                              then do let m = "9:30"     -- if time is between 19.5 and 24 than next match on next date at 9:30
                                                      if date == "1-11"
                                                          then do checkdatematchString x "2-11" m
                                                          else if date == "2-11"
                                                              then do checkdatematchString x "3-11" m
                                                              else return ()
                                              else return ()


-- function call to get match with time and time of argument

checkdatematchString x date time = do let a = split ' ' x  -- split line by space
                                      if date == a!!3       -- check for date match
                                           then do if time == a!!4   -- check for time match
                                                        then putStrLn x
                                                        else return ()
                                           else return ()

checkmatch [] date time = return ()    -- if list is empty return
checkmatch (x:xs) date time = do checkmatchString x date time     -- function call
                                 checkmatch xs date time


-- function to get next match after current time
nextmatch date time = do
   if date < 1 || date > 31 || time < 0 || time >= 24   -- check for correct format of time
         then putStrLn "Please Enter date time in correct format"
         else if date > 3 || (date == 3 && time > 19.50)     --check if any match left
                  then putStrLn "All Matches are Over"
                  else do content <- readFile "test.txt"
                          let linesOfFiles = lines content
                          let s = show date
                          let b = s ++ "-11"              -- concatenate month with data
                          checkmatch linesOfFiles b time  --function call to check the match
