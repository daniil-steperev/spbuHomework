sum3 :: Num a => [a] -> [a] -> [a] -> [a]
sum3 xs ys zs = sum2 xs (sum2 ys zs)

sum2 :: Num a => [a] -> [a] -> [a]
sum2 [] [] = []
sum2 (x:xs) [] = x : sum2 xs []
sum2 [] (y:ys) = y : sum2 [] ys
sum2 (x:xs) (y:ys) = (x + y) : sum2 xs ys