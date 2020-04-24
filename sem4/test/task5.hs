isSatisfy :: (a -> Bool) -> [a] -> Bool
isSatisfy f [] = True
isSatisfy f (x:xs)
    | f x       = isSatisfy f xs
    | otherwise = False