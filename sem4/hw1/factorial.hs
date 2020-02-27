factorial :: Int -> Int
factorial 0 = 1
factorial 1 = 1
factorial n | n > 0 =  n * factorial (n - 1)
            | otherwise = error "number should be greater than or equal to 0"