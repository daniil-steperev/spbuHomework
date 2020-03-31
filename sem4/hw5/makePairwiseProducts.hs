makePairwiseProducts :: Int -> [Int]
makePairwiseProducts 0 = []
makePairwiseProducts n
    | n > 0 = [1..n] >>= \first -> [1..n] >>= \second -> return $ first * second
    | otherwise = error "Inputted number should be positive"