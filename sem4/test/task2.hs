matrix :: Int -> [[Int]]
matrix 0 = [[]]
matrix n = [createLists x | x <- [1..n]] where
                createLists :: Int -> [Int]
                createLists y = [getBigger x y | x <- [1..n]] where
                    getBigger :: Int -> Int -> Int
                    getBigger x y = if x > y then x else y
                    
test :: [[Int]]
test = matrix 4                    