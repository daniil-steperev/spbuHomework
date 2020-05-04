import Data.List
import Control.Monad.Writer.Lazy

data Graph = Graph [Vertex] [Edge] -- vertices belong to classes Ord, Eq, Show
                                   -- edges belong to class Show

dijkstra :: Graph -> [Vertex]
dijkstra (Graph st end) = dijkstra' [] st
    where dijkstra' path [] = path
          dijkstra' path notVisited = dijkstra' path' notVisited'
          where
                nearestVert = minimum notVisited
                path' = closest : path
                notVisited' = delete closest (map (updateVertex closest) notVisited)
                updateVertices v1 v2 = min (Vertex (id v2) (addEdge (label v1) (findEdge (id v1) (id v2) es))) v2
                
                addEdge' :: Writer [Edge] Int -> Edge -> Writer [Edge] Int
                addEdge' wr e = do
                    dist <- wr
                    tell (e : [])
                    return (dist + (weight e))
                    
addEdge :: Maybe (Writer [Edge] Int) -> Maybe Edge -> Maybe (Writer [Edge] Int)
addEdge Nothing Nothing    = Nothing
addEdge Nothing (Just wr)  = Nothing
addEdge (Just wr) Nothing  = Nothing
addEdge (Just wr) (Just e) = Just (addEdge' wr e) 

findEdge :: Int -> Int -> [Edge] -> Maybe Edge
findEdge _ _ [] = Nothing
findEdge id1 id2 (e : ess)
    | id1 == (idFrom e) && id2 == (idTo e) = Just e
    | otherwise = findEdge id1 id2 ess                   

data Edge = Edge {
    weight :: Int,
    idTo :: Int,
    idFrom :: Int
}

instance Show Edge where
    show (Edge _ idTo idFrom) = show idFrom ++ " ~» " ++ show idTo


data Vertex = Vertex {
    number :: Int, 
    label :: Maybe (Writer [Edge] Int)
}

instance Eq Vertex where
    (==) fir sec = number fir == number sec

instance Show Vertex where
    show (Vertex number Nothing)   = "(" ++ show number ++ " - END OF PATH)"
    show (Vertex number (Just wr)) = "(" ++ show number ++ " " ++ show (fst (runWriter wr))++ ": " ++ show (snd (runWriter wr)) ++ ")"

instance Ord Vertex where
    compare (Vertex _ Nothing) (Vertex _ Nothing)               = EQ
    compare (Vertex _ Nothing) (Vertex _ (Just wr))             = GT
    compare (Vertex _ (Just writer)) (Vertex _ Nothing)         = LT
    compare (Vertex _ (Just writer1)) (Vertex _ (Just writer2)) = compare (fst (runWriter writer1)) (fst (runWriter writer2))