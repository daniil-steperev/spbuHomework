reverseList :: [a] -> [a]
reverseList list = helper list [] where
    helper [] reversed = reversed
    helper (x:xs) reversed = helper xs (x:reversed)
