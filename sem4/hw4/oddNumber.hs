evenNumber1 :: [Int] -> Int
evenNumber1 = foldr (\x s -> if x `mod` 2 == 0 then s + 1 else s) 0

evenNumber2 :: [Int] -> Int
evenNumber2 = length . filter even

evenNumber3 :: [Int] -> Int
evenNumber3 = sum . map (\x -> (1 - x `mod` 2))

test1 :: Bool
test1 = evenNumber1 [-1, 2, 3, 4, -1, 5, 6] == 3

test2 :: Bool
test2 = evenNumber2 [-1, 2, 3, 4, -1, 5, 6] == 3

test3 :: Bool
test3 = evenNumber3 [-1, 2, 3, 4, -1, 5, 6] == 3
