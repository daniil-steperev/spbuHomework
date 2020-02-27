fibonacci :: Int -> Int
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n | n > 0 = fibonacci (n - 1) + fibonacci (n - 2)
            | otherwise = error "number should be greater than or equal to 0 "