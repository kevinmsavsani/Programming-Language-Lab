import Control.Monad (replicateM)
import Data.Function (on)
import Data.List     (sortBy)
import System.Random (randomRIO)
import Control.Applicative((<$>))

import System.Random
import Data.Array.IO
import Control.Monad

-- | Randomly shuffle a list
--   /O(N)/
shuffle :: [a] -> IO [a]
shuffle xs = do
        ar <- newArray n xs
        forM [1..n] $ \i -> do
            j <- randomRIO (i,n)
            vi <- readArray ar i
            vj <- readArray ar j
            writeArray ar j vi
            return vj
  where
    n = length xs
    newArray :: Int -> [a] -> IO (IOArray Int a)
    newArray n xs =  newListArray (1,n) xs



combine [] _ = []
combine _ [] = []
combine (x:xs) (y:ys) = (x ++ " vs " ++ y) : combine xs ys

combine1 [] _ = []
combine1 _ [] = []
combine1 (x:xs) (y:ys) = (x ++ " " ++ y) : combine1 xs ys

randomize xs = do
  ys <- replicateM (length xs) $ randomRIO (1 :: Int, 100000)
  pure $ map fst $ sortBy (compare `on` snd) (zip xs ys)

list = ["A","B","C","D","E","F","G","H","I","J","K","L"]
ranl = list
(list1,list2) = splitAt 6 ranl
list3 = combine list1 list2
date = ["1/11 9:30 AM","1/11 7:30 PM","2/11 9:30 AM","2/11 7:30 PM","3/11 9:30 AM","3/11 7:30 PM"]
bar = list3
list4 = combine1 bar date