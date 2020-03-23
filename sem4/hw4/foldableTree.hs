data FoldTree a = Leaf a |
                  Node (FoldTree a) a (FoldTree a)
              
instance Foldable FoldTree where
    foldr func initVal (Leaf current) = func current initVal
    foldr func initVal (Node left current right) = foldr func (func current (foldr func initVal right)) left
    
getTreeElements :: FoldTree a -> [a]
getTreeElements = foldr (:) []

testTree = Node (Node (Leaf 2) 7 (Leaf 1)) 0 (Leaf 2)
test :: Bool
test = [2, 7, 1, 0, 2] == (getTreeElements $ testTree)