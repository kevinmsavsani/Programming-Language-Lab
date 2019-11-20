import Data.Array

-- dimensions of grids
n = 4
s = floor . sqrt . fromIntegral $ n

-- int values for all objects
type Symbols = Int
type Coordinate = (Int, Int)
type Grid = Array Coordinate Symbols

puzzleGrid :: [[Symbols]] -> Array (Int, Int) Symbols
puzzleGrid x = array ((0, 0), (n-1, n-1)) (convertInput x)  -- function call to convert 2D puzzleboard to array


-- convert 2D array into 1D array
convertInput :: [[Symbols]] -> [((Int, Int), Symbols)]
convertInput = concatMap convertRow . zip [0..n-1]
  where
    convertRow :: (Int, [Symbols]) -> [((Int, Int), Symbols)]
    convertRow (x, ms) = convertCol x $ zip [0..n-1] ms

    convertCol :: Int -> [(Int, Symbols)] -> [((Int, Int), Symbols)]
    convertCol x cols = map (\(y, m) -> ((x, y), m)) cols

getSol :: Grid -> Maybe Grid
getSol = finalOut . getAllSols

getAllSols :: Grid -> [Grid]
getAllSols b = getAllSols' (emptyCoordinates b) b       -- function call to get all empty cell location and try to fill them
  where
    getAllSols' :: [Coordinate] -> Grid -> [Grid]
    getAllSols' []     b = [b]                                         -- when list is empty return b
    getAllSols' (x:xs) b = concatMap (getAllSols' xs) candidateBoards    --recursive call to reach end of list and start marking from bottom to top
      where
        candidateMarks  = [m | m <- [1..n], ifPossible m x b]   -- try to put all values and check if it's possible
        candidateBoards = map (\m -> updateGrid m x b) candidateMarks   -- copy the marked puzzle box to original puzzle board

-- Function to get all empty cell locations
emptyCoordinates :: Grid -> [Coordinate]
emptyCoordinates b = [(x, y) | x <- [0..n-1], y <- [0..n-1], b ! (x, y) == 0]   --check cell with value equal to 0

-- function to check validation of assigned value
ifPossible :: Symbols -> Coordinate -> Grid -> Bool
ifPossible m (x, y) b = notInRow && notInColumn && notInBox    -- check if value is already in same row, col or box
  where
    notInRow    = notElem m $ b `elemsInRow` x                      -- Move in whole row and check for marked value if present return false
    notInColumn = notElem m $ b `elemsInCol` y                   -- Move in whole col and check for marked value if present return false
    notInBox    = notElem m $ b `elemsInBox` (x, y)            -- Move in whole box and check for marked value if present return false

updateGrid :: Symbols -> Coordinate -> Grid -> Grid
updateGrid mark (x, y) b = b // [((x, y), mark)]            -- function to copy marked board to original board

elemsInRow :: Grid -> Int -> [Symbols]
b `elemsInRow` x = [b ! loc | loc <- range((x, 0), (x, n-1))]         -- function to check if current mark is present in the same row

elemsInCol ::  Grid -> Int -> [Symbols]
b `elemsInCol` y = [b ! loc | loc <- range((0, y), (n-1, y))]      -- function to check if current mark is present in the same col

elemsInBox :: Grid -> Coordinate -> [Symbols]
b `elemsInBox` (x, y) = [b ! loc | loc <- locations]               -- function to check if current mark is present in the same box
  where
    x' = (x `div` s) * s
    y' = (y `div` s) * s
    locations = range((x', y'), (x' + 1, y' + 1))                -- For loop to travel in all rows and cols in current box of size s


finalOut :: [a] -> Maybe a
finalOut []     = Nothing              -- return nothing if no output
finalOut (x:xs) = Just x               -- return head if output possible

printGrid :: Maybe Grid -> IO ()
printGrid Nothing  = putStrLn "Not solvable"      -- if nothing is return then print no output
printGrid (Just b) = do mapM_ putStrLn [show $ b `elemsInRow` x | x <- [0..n-1]]    --else print output
                        putStrLn ""


-- function to print all getAllSols
printAll :: [Grid] -> IO ()
printAll [] = return ()
printAll (x:xs) = do printGrid (Just x)
                     printAll xs

printcheck :: [Grid] -> IO ()
printcheck [] = printGrid Nothing
printcheck (xs) = printAll xs

allSolutions :: [[Symbols]] -> IO ()
allSolutions x = do
                     if list_lengths x
                         then do printcheck $ getAllSols $ puzzleGrid x
                         else putStrLn "Invalid Input Format"

list_lengths :: Foldable t => [t a] -> Bool
list_lengths x = (map length x) == (replicate n n)


-- starting function to find output
run :: [[Symbols]] -> IO ()
run x = do
    if list_lengths x
        then do let output = getSol $ puzzleGrid x
                printGrid output             -- function call to print output
        else putStrLn "Invalid Input Format"