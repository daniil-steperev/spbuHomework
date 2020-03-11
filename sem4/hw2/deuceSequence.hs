deuceSequence :: Int -> [Int]
deuceSequence n = helper n 1 [] where
   helper 0 _ _ = []
   helper n x xs | n > 0 =  x : helper (n-1) (2 * x) xs
                 | otherwise = error "degree should be greater or equall 0"

deuceSequence' :: Int -> [Int]
deuceSequence' n = take n $ iterate (*2) 1