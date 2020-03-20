maxIndex :: [Integer] -> Integer
maxIndex [] = 0
maxIndex (x:y:[]) = 1
maxIndex (x:y:xs) = helper xs (y + x) y 2 1 where
    helper [] _ _ _ maxInd  = maxInd
    helper (x:xs) maxSum prev curInd maxInd
        | x + prev > maxSum = helper xs (x + prev) x (curInd + 1) curInd
        | otherwise         = helper xs maxSum x (curInd + 1) maxInd