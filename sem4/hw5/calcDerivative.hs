import System.IO

main = do
        let expr1 = Add (Subt (Mult (Const 5) Term) (Mult (Const 5) (Const 10))) (Pow Term 2)
        putStr "Given expression 1: "
        putStrLn $ show expr1
        
        putStr "Calculated derivative: "
        putStrLn $ show $ calcDer expr1
        
        putStr "Derivative after simplifications: "
        putStrLn $ show $ calcAndSimpDer expr1

        let expr2 = Add (Add (Pow (Const 2) 4) (Pow Term 3)) (Mult (Const 3) (Subt Term (Const 2)))
        putStr "Given expression 2: "
        putStrLn $ show expr2

        putStr "Calculated derivative: "
        putStrLn $ show $ calcDer expr2

        putStr "Derivative after simplifications"
        putStrLn $ show $ calcAndSimpDer expr2

calcAndSimpDer :: Expr -> Expr -- calcAndSimpDer means calculate and simplificate the expression
calcAndSimpDer expr = makeSimp $ calcDer expr        

data Expr = Const Int      | -- Expr means Expression
            Term           | -- Term means 'x'
            Unar Expr      | -- Unar means Unary minus
            Add  Expr Expr | -- Add means Addition
            Subt Expr Expr | -- Subt means Subtraction
            Mult Expr Expr | -- Mult means Multiplication
            Div  Expr Expr | -- Div means Division
            Pow  Expr Int    -- Pow means Power
           
instance Show Expr where
    show (Const x)  = show x
    show (Term)     = "x"
    show (Unar x)   = "(-" ++ (show x) ++ ")"
    show (Add f g)  = "("  ++ (show f) ++ " + " ++ (show g) ++ ")"
    show (Subt f g) = "("  ++ (show f) ++ " - " ++ (show g) ++ ")"
    show (Mult f g) = "("  ++ (show f) ++ " * " ++ (show g) ++ ")"
    show (Div f g)  = "("  ++ (show f) ++ " / " ++ (show g) ++ ")"
    show (Pow f g)  = "("  ++ (show f) ++ "^"   ++ (show g) ++ ")"
    
calcDer :: Expr -> Expr -- calcDer means Calculate the Derivative    
calcDer x = case x of
                Const y  -> Const 0
                Term     -> Const 1
                Unar f   -> Unar (calcDer f)
                Add  f g -> Add  (calcDer f) (calcDer g)
                Subt f g -> Subt (calcDer f) (calcDer g)
                Mult f g -> Add  (Mult g (calcDer f)) (Mult f (calcDer g)) -- (f * g)' = f' * g + f * g'
                Div  f g -> Subt (Div (calcDer f) (g)) (Div (Mult f (calcDer g)) (Mult g g)) -- (f / g)' = f' / g - (f * g') / (g * g)
                Pow  f a -> Mult (Const a) (Mult (Pow f (a - 1)) (calcDer f)) -- (f^a)' = a * f^(a - 1) * f'
                
makeSimp :: Expr -> Expr -- makeSimp means Make a simplification of the expression         
makeSimp expr = case expr of
                    Unar f   -> simplify $ Unar $ makeSimp f
                    Add  f g -> simplify $ Add (makeSimp f) (makeSimp g)
                    Subt f g -> simplify $ Subt (makeSimp f) (makeSimp g)
                    Mult f g -> simplify $ Mult (makeSimp f) (makeSimp g)
                    Div  f g -> simplify $ Div (makeSimp f) (makeSimp g)
                    Pow  f a -> simplify $ Pow (makeSimp f) a
                    _        -> expr
                        
simplify :: Expr -> Expr
simplify (Unar (Unar f))    = simplify f

-- simplifications with Const as second argument 
simplify (Add f (Const 0))  = simplify f
simplify (Subt f (Const 0)) = simplify f
simplify (Mult f (Const 0)) = Const 0
simplify (Mult f (Const 1)) = simplify f
simplify (Div f (Const 1))  = simplify f
simplify (Div f (Const 0))  = error "Division by zero!"
simplify (Pow f 0)  = Const 1
simplify (Pow f 1)  = simplify f

-- simplifications with Const as first argument
simplify (Add (Const 0) f)  = simplify f
simplify (Div (Const 0) f)  = Const 0
simplify (Subt (Const 0) f) = simplify (Unar f)
simplify (Mult (Const 0) f) = Const 0
simplify (Mult (Const 1) f) = f

-- simplifications with Const as both arguments
simplify (Add (Const x) (Const y))  = Const (x + y)
simplify (Subt (Const x) (Const y)) = Const (x - y)
simplify (Mult (Const x) (Const y)) = Const (x * y)
simplify (Div (Const x) (Const y))  = Const (x `div` y)
simplify (Pow (Const x) y)          = Const (x ^ y)
    
-- otherwise    
simplify f = f