firstInList xs x = helper xs x 0 where
   helper [] _ _ = error "element is not in the list"
   helper (x:xs) element n | x == element = n
                           | otherwise = helper xs element (n + 1)