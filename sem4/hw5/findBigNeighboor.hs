import Control.Monad

findBigNeighboor :: Ord x => [x] -> Maybe x
findBigNeighboor (x:last@(y:z:end)) = mplus (if (y > x && y > z) then (Just y) else Nothing) (findBigNeighboor last)
findBigNeighboor _                     = Nothing

test :: Bool
test = (findBigNeighboor [1, 3, 5, 3, 5, 2]) == Just 5