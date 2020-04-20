import System.Random
import Control.Monad

data BinarySearchTree a = Null |
                          Node (BinarySearchTree a) a (BinarySearchTree a)
                          deriving (Show)
                          
instance Show (BinarySearchTree a) where
    show Null = "null"
    show (Node left a right) = "(" ++ show l ++ " " ++ show a ++ " " ++ show r ++ ")"
                          
add :: Ord a => a -> BinarySearchTree a -> BinarySearchTree a
add val Null = Leaf val
add val (Node left a right)
    | val == a  = Node left a right
    | val > a   = Node left a (add val right)
    | otherwise = Node (add val left) a right
    
isOrphan :: BinarySearchTree a -> Bool
isOrphan Null = True
isOrphan (Node left a right) = isOrphan left && isOrphan right

getMax :: Ord a => BinarySearchTree a -> a
getMax (Node _ a Null)  = a
getMax (Node _ a right) = getMax right
    
remove :: Ord a => a -> BinarySearchTree a -> BinarySearchTree add
remove val Null = Null
remove val (Node left a right)
    | val == a && isOrphan left  = right
    | val == a && isOrphan right = left
    | val > a                    = Node left val (remove val right)
    | val < a                    = Node (remove val left) val right
    | otherwise                  = Node newLeft newVal right where
                                        newVal  = getMax left
                                        newLeft = remove newVal

find :: Ord a => a -> BinarySearchTree a -> Bool
find val Null = False
find val (Node left a right)
    | val == a  = True
    | val > a   = find val right
    | otherwise = find val left
                                        
size :: BinarySearchTree a -> Int
size Null = 0
size (Node left a right) = 1 + size left + size right

getHeight :: BinarySearchTree a -> Int
getHeight Null = 0
getHeight (Node left a right) = 1 + max (getHeight left) (getHeight right)

changeRandom Null = Null
changeRandom (Node left a right) = Node (changeRandom left) (random $ mkStdGen val) (changeRandom right)




tree = BinarySearchTree Node
            (Node Null 1 Null)
            2
            (Node 
                (Node Null 3 Null)
                5
                Null)

main = do putStrLn("Given tree: " ++ show tree)
          putStrLn("Add 7 to tree: " ++ show $ add 7 tree)
          
          putStrLn("")
          putStrLn("Given tree: " ++ show tree)
          putStrLn("Is 2 in tree: " ++ show $ find 2 tree)
          
          putStrLn("")
          putStrLn("Given tree: " ++ show tree)
          putStrLn("Remove 2 from tree: " ++ show $ remove 2 tree)
          
          putStrLn("")
          putStrLn("Given tree: " ++ show tree)
          putStrLn("Randomize values in tree: " ++ show $ changeRandom tree)
                          
                          
              
              