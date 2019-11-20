
import Data.Array

-- dimentions of grids
n = 9
s = floor . sqrt . fromIntegral $ n


-- starting function to find solution
main = do
    let solution = solve puzzleBoard   -- function call
    printBoard solution                -- function call to print solution


-- int values for all objects
type Mark = Int

type Location = (Int, Int)          -- row and col

type Board = Array Location Mark

puzzleBoard :: Board
puzzleBoard = array ((0, 0), (n-1, n-1)) $ puzzleAssocs $ examplePuzzle n   -- function call to convert 2D puzzleboard to array


-- initial puzzle box for 4x4
examplePuzzle :: (Eq a, Num a, Num t) => a -> [[t]]
examplePuzzle 4 = [[1, 2, 0,  0],
                   [0, 0, 0,  1],

                   [2, 0, 1,  0],
                   [4, 0, 0,  2]]


-- initial puzzle box for 9x9
examplePuzzle 9 = [[5, 3, 0,  0, 7, 0,  0, 0, 0],
                   [6, 0, 0,  1, 9, 5,  0, 0, 0],
                   [0, 9, 8,  0, 0, 0,  0, 6, 0],

                   [8, 0, 0,  0, 6, 0,  0, 0, 3],
                   [4, 0, 0,  8, 0, 3,  0, 0, 1],
                   [7, 0, 0,  0, 2, 0,  0, 0, 6],

                   [0, 6, 0,  0, 0, 0,  2, 8, 0],
                   [0, 0, 0,  4, 1, 9,  0, 0, 5],
                   [0, 0, 0,  0, 8, 0,  0, 7, 0]]

solve :: Board -> Maybe Board
solve = headOrNothing . solutions

solutions :: Board -> [Board]
solutions b = solutions' (emptyLocations b) b       -- function call to get all empty cell location and try to fill them
  where
    solutions' :: [Location] -> Board -> [Board]
    solutions' []     b = [b]                                         -- when list is empty return b
    solutions' (x:xs) b = concatMap (solutions' xs) candidateBoards    --recursive call to reach end of list and start marking from bottom to top
      where
        candidateMarks  = [m | m <- [1..n], isPossibleMark m x b]   -- try to put all values and check if it's possible
        candidateBoards = map (\m -> copyWithMark m x b) candidateMarks   -- copy the marked puzzle box to original puzzle board

-- Function to get all empty cell locations
emptyLocations :: Board -> [Location]
emptyLocations b = [(row, col) | row <- [0..n-1], col <- [0..n-1], b ! (row, col) == 0]   --check cell with value equal to 0

-- function to check validation of assigned value
isPossibleMark :: Mark -> Location -> Board -> Bool
isPossibleMark m (row, col) b = notInRow && notInColumn && notInBox    -- check if value is already in same row, col or box
  where
    notInRow    = notElem m $ b `marksInRow` row                      -- Move in whole row and check for marked value if present return false
    notInColumn = notElem m $ b `marksInColumn` col                   -- Move in whole col and check for marked value if present return false
    notInBox    = notElem m $ b `marksIn3x3Box` (row, col)            -- Move in whole box and check for marked value if present return false

copyWithMark :: Mark -> Location -> Board -> Board
copyWithMark mark (row, col) b = b // [((row, col), mark)]            -- function to copy marked board to original board

marksInRow :: Board -> Int -> [Mark]
b `marksInRow` row = [b ! loc | loc <- range((row, 0), (row, n-1))]         -- function to check if current mark is present in the same row

marksInColumn ::  Board -> Int -> [Mark]
b `marksInColumn` col = [b ! loc | loc <- range((0, col), (n-1, col))]      -- function to check if current mark is present in the same col

marksIn3x3Box :: Board -> Location -> [Mark]
b `marksIn3x3Box` (row, col) = [b ! loc | loc <- locations]               -- function to check if current mark is present in the same box
  where
    row' = (row `div` s) * s
    col' = (col `div` s) * s
    locations = range((row', col'), (row' + 1, col' + 1))                -- For loop to travel in all rows and cols in current box of size s


-- convert 2D array into 1D array
puzzleAssocs :: [[Mark]] -> [(Location, Mark)]
puzzleAssocs = concatMap rowAssocs . zip [0..n-1]
  where
    rowAssocs :: (Int, [Mark]) -> [((Int, Int), Mark)]
    rowAssocs (row, marks) = colAssocs row $ zip [0..n-1] marks


    colAssocs :: Int -> [(Int, Mark)] -> [((Int, Int), Mark)]
    colAssocs row cols = map (\(col, m) -> ((row, col), m)) cols


headOrNothing :: [a] -> Maybe a
headOrNothing []     = Nothing              -- return nothing if no solution
headOrNothing (x:xs) = Just x               -- return head if solution possible

printBoard :: Maybe Board -> IO ()
printBoard Nothing  = putStrLn "Not solvable"      -- if nothing is return then print no solution
printBoard (Just b) = mapM_ putStrLn [show $ b `marksInRow` row | row <- [0..n-1]]    --else print solution


printBoards :: Maybe Board -> IO ()
printBoards Nothing  = putStrLn "Not solvable"      -- if nothing is return then print no solution
printBoards (Just b) = do mapM_ putStrLn [show $ b `marksInRow` row | row <- [0..n-1]] --else print solution
                          putStrLn ""
-- function to print all solutions
printAll :: [Board] -> IO ()
printAll [] = return ()
printAll (x:xs) = do printBoards (Just x)
                     printAll xs

printcheck :: [Board] -> IO ()
printcheck [] = printBoards Nothing
printcheck (xs) = printAll xs

allSolutions :: IO ()
allSolutions = printcheck $ solutions puzzleBoard
