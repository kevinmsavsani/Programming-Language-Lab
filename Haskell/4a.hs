
import Data.Array

n = 9
s = floor . sqrt . fromIntegral $ n

main = do
    let solution = solve puzzleBoard
    printBoard solution

type Mark = Int

type Location = (Int, Int)

type Board = Array Location Mark

puzzleBoard :: Board
puzzleBoard = array ((0, 0), (n-1, n-1)) $ puzzleAssocs $ examplePuzzle n

examplePuzzle :: (Eq a, Num a, Num t) => a -> [[t]]
examplePuzzle 4 = [[1, 2, 0,  0],
                   [0, 0, 0,  1],

                   [2, 0, 1,  0],
                   [4, 0, 0,  2]]

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
solutions b = solutions' (emptyLocations b) b
  where
    solutions' :: [Location] -> Board -> [Board]
    solutions' []     b = [b]
    solutions' (x:xs) b = concatMap (solutions' xs) candidateBoards
      where
        candidateMarks  = [m | m <- [1..n], isPossibleMark m x b]
        candidateBoards = map (\m -> copyWithMark m x b) candidateMarks

emptyLocations :: Board -> [Location]
emptyLocations b = [(row, col) | row <- [0..n-1], col <- [0..n-1], b ! (row, col) == 0]

isPossibleMark :: Mark -> Location -> Board -> Bool
isPossibleMark m (row, col) b = notInRow && notInColumn && notInBox
  where
    notInRow    = notElem m $ b `marksInRow` row
    notInColumn = notElem m $ b `marksInColumn` col
    notInBox    = notElem m $ b `marksIn3x3Box` (row, col)

copyWithMark :: Mark -> Location -> Board -> Board
copyWithMark mark (row, col) b = b // [((row, col), mark)]

marksInRow :: Board -> Int -> [Mark]
b `marksInRow` row = [b ! loc | loc <- range((row, 0), (row, n-1))]

marksInColumn ::  Board -> Int -> [Mark]
b `marksInColumn` col = [b ! loc | loc <- range((0, col), (n-1, col))]

marksIn3x3Box :: Board -> Location -> [Mark]
b `marksIn3x3Box` (row, col) = [b ! loc | loc <- locations]
  where
    row' = (row `div` s) * s
    col' = (col `div` s) * s
    locations = range((row', col'), (row' + 1, col' + 1))

puzzleAssocs :: [[Mark]] -> [(Location, Mark)]
puzzleAssocs = concatMap rowAssocs . zip [0..n-1]
  where
    rowAssocs :: (Int, [Mark]) -> [((Int, Int), Mark)]
    rowAssocs (row, marks) = colAssocs row $ zip [0..n-1] marks

    colAssocs :: Int -> [(Int, Mark)] -> [((Int, Int), Mark)]
    colAssocs row cols = map (\(col, m) -> ((row, col), m)) cols

headOrNothing :: [a] -> Maybe a
headOrNothing []     = Nothing
headOrNothing (x:xs) = Just x

printBoard :: Maybe Board -> IO ()
printBoard Nothing  = putStrLn "Not solvable"
printBoard (Just b) = mapM_ putStrLn [show $ b `marksInRow` row | row <- [0..n-1]]


printBoards :: Maybe Board -> IO ()
printBoards Nothing  = putStrLn "Not solvable"
printBoards (Just b) = do mapM_ putStrLn [show $ b `marksInRow` row | row <- [0..n-1]]
                          putStrLn ""

printAll :: [Board] -> IO ()
printAll [] = return ()
printAll (x:xs) = do printBoards (Just x)
                     printAll xs

printcheck :: [Board] -> IO ()
printcheck [] = printBoards Nothing
printcheck (xs) = printAll xs

allSolutions :: IO ()
allSolutions = printcheck $ solutions puzzleBoard
