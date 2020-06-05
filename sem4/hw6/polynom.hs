data Polynom = Polynom [Int]

foldPolynoms :: Polynom -> Polynom -> Polynom
foldPolynoms (Polynom a) (Polynom b) = Polynom (foldCoefs a b)

foldCoefs :: [Int] -> [Int] -> [Int]
foldCoefs (x:xs) (y:ys) = (x + y) : (foldCoefs xs ys)
foldCoefs [] ys = ys
foldCoefs xs [] = xs

multPolynoms :: Polynom -> Polynom -> Polynom
multPolynoms (Polynom a) (Polynom b) = Polynom (multCoefs a b)

multCoefs :: [Int] -> [Int] -> [Int]
multCoefs [] ys = []
multCoefs xs [] = []
multCoefs (x:xs) ys = foldCoefs (0 : (multCoefs xs ys)) (map (*x) ys)

instance Show Polynom where
    show (Polynom xs) = showPolynom xs True (length xs)
    
showCoefs :: Int -> Bool -> String
showCoefs elem isFst
    | isFst = case elem of
                   1 -> " 1"
                   _ -> show elem
    | elem == 1    = " + "
    | elem == (-1) = " - "
    | elem < 0     = " " ++ show elem
    | otherwise    = " + " ++ show elem

showDegrees :: Bool -> Int -> Int -> String
showDegrees isUnitCoef elemLeft maxDeg =
    let curDeg = maxDeg - elemLeft - 1 in
        case curDeg of
            1 -> "x"
            0 -> ""
            _ -> (if isUnitCoef then "" else "*") ++ "x^" ++ show curDeg
        
showMonom :: Int -> Bool -> Int -> Int -> String
showMonom x isFst elemLeft maxDeg = (showCoefs x isFst) ++ case x of
                                                               1    -> showDegrees True elemLeft maxDeg
                                                               (-1) -> showDegrees True elemLeft maxDeg
                                                               _    -> showDegrees False elemLeft maxDeg
                                                               
        
showPolynom :: [Int] -> Bool -> Int -> String
showPolynom [] _ _ = ""
showPolynom (x:xs) isFst maxDeg
    | x == 0    = showPolynom xs isFst maxDeg
    | otherwise =  (showMonom x isFst (length xs) maxDeg) ++ (showPolynom xs False maxDeg)
    
    
polynom1 = Polynom [1, -2, 1, 4, 2]
polynom2 = Polynom [0, 0, 5, 1, 2, 3, 1]  

polynom3 = Polynom [1, 5]
polynom4 = Polynom [0, (-2), 3, 1]

main = do putStrLn("Adding check: ")
          putStr(show polynom1 ++ " <+> " ++ show polynom2)
          putStrLn(" = " ++ show (foldPolynoms polynom1 polynom2))
          
          putStrLn("")
          putStrLn("Multiplying check: ")
          putStr(show polynom3 ++ " <*> " ++ show polynom4)
          putStrLn(" = " ++ show (multPolynoms polynom3 polynom4))