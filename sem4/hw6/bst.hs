module BinarySearchTree where

import Control.Monad
-- import System.Random

data BinarySearchTree a = Null |
                          Node (BinarySearchTree a) a (BinarySearchTree a)
                         
instance Show a => Show (BinarySearchTree a) where
    show Null = "null"
    show (Node left a right) = "(" ++ show left ++ " " ++ show a ++ " " ++ show right ++ ")"
                          
add :: Ord a => a -> BinarySearchTree a -> BinarySearchTree a
add val Null = Node Null val Null
add val (Node left a right)
    | val == a  = Node left a right
    | val > a   = Node left a (add val right)
    | otherwise = Node (add val left) a right
    
isOrphan :: BinarySearchTree a -> Bool
isOrphan Null     = True
isOrphan tree@(Node left val right) = size tree == 0

getMax :: Ord a => BinarySearchTree a -> a
getMax (Node _ a Null)  = a
getMax (Node _ a right) = getMax right
    
remove :: Ord a => a -> BinarySearchTree a -> BinarySearchTree a
remove val Null = Null
remove val (Node left a right)
    | val == a && isOrphan left  = right
    | val == a && isOrphan right = left
    | val > a                    = Node left val (remove val right)
    | val < a                    = Node (remove val left) val right
    | otherwise                  = Node newLeft newVal right where
                                        newVal  = getMax left
                                        newLeft = remove newVal left

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

chgRandomly Null = Null
chgRandomly (Node l v r) = Node (chgRandomly left) (val) (chgRandomly right) where
                            val = newRand

tree = Node
            (Node Null 1 Null)
            2
            (Node 
                (Node Null 3 Null)
                5
                Null)

main = do putStrLn("Given tree: " ++ show tree)
          putStrLn("Add 7 to tree: " ++ show (add 7 tree))
          
          putStrLn("")
          putStrLn("Given tree: " ++ show tree)
          putStrLn("Is 2 in tree: " ++ show (find 2 tree))
          
          putStrLn("")
          putStrLn("Given tree: " ++ show tree)
          putStrLn("Remove 2 from tree: " ++ show (remove 2 tree))

                          
                          
              
              