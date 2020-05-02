digitSum :: Int -> Int
digitSum 0 = 0
digitSum x = mod x 10 + digitSum (div x 10)