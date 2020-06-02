fibonacci :: Integer -> Integer
fibonacci n | n > 0 = helperFibonacci 0 1 (n - 1)
            | n < 0 = (-1) ^ abs(n + 1) * helperFibonacci 0 1 ((-n) - 1)
            | otherwise = 0
            
helperFibonacci first second 0 = second
helperFibonacci first second n = helperFibonacci second (first + second) (n - 1)