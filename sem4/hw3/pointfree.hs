{-
source function:
func x l = map (\y -> y*x) l

1. Apply η-reduction for "l":
func x = map (\y -> y*x)

2. Reduction lambda-function:
func x = map (*x)

3. Apply η-reduction for "x":
func = map . (*)
-}

func :: Integer -> [Integer] -> [Integer]
func = map . (*)
