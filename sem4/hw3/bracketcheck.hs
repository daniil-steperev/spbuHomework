brtCheck :: [Char] -> Bool
brtCheck x = helper x [] where
    helper [] []        = True
    
    helper [] (y:ys) 
        | isOpenBrt  y  = False
        | isCloseBrt y  = False 
        | otherwise     = helper [] ys
	
    helper (x:xs) s@(y:ys)
        | isOpenBrt  x  = helper xs (x:s)
        | isCloseBrt x  = if isCorrectBrt y x then helper xs ys else False
        | otherwise     = helper xs s
	
    helper (x:xs) [] 
        | isOpenBrt  x  = helper xs [x]
        | isCloseBrt x  = False
        | otherwise     = helper xs []
                         
isOpenBrt :: Char -> Bool                         
isOpenBrt x  = if (x == '(' || x == '[' || x == '{') then True else False

isCloseBrt :: Char -> Bool
isCloseBrt x = if (x == ')' || x == ']' || x == '}') then True else False

isCorrectBrt :: Char ->  Char -> Bool
isCorrectBrt openBrt closeBrt 
    | openBrt == '(' = closeBrt == ')'
    | openBrt == '[' = closeBrt == ']'
	| openBrt == '{' = closeBrt == '}'
    

test :: Bool
test = (brtCheck "[]")    && (brtCheck "({[]})")       &&
       (brtCheck "12313") && (brtCheck "(1{2[3]4}5)6") &&
       not (brtCheck "{") && not (brtCheck "({])")