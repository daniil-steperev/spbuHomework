factorization :: Int -> [[Int]]
factorization 0 = [[]]
factorization n
    | n > 0     = decompose n 1
    | otherwise = error "Inputted number should be positive!"
    
decompose 0 _             = [[]]
decompose lastSum curTerm = [x : res | x <- [curTerm..lastSum], res <- (decompose (lastSum - x) x)]
    