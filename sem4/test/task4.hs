supermap :: [t] -> (t -> [a]) -> [a]
supermap [] f     = []
supermap (x:xs) f = f x ++ supermap xs f

test :: [Double]
test = supermap [1,2,3] (\x -> [x * x, cos (x * 30)])