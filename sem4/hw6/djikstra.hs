import Data.List
import Control.Monad.Writer.Lazy

test = dijkstra graph

data Graph = Graph [Vertex] [Edge] -- vertices belong to classes Ord, Eq, Show
                                   -- edges belong to class Show

dijkstra :: Graph -> [Vertex]
dijkstra (Graph vertices edges) = dijkstraIteration [] vertices edges

dijkstraIteration :: [Vertex] -> [Vertex] -> [Edge] -> [Vertex]
dijkstraIteration path [] edges = path
dijkstraIteration path notVisited edges =
    let nearestVert = minimum notVisited
        path' = nearestVert : path
        notVisited' = delete nearestVert (map (updateWeights nearestVert edges) notVisited)
    in dijkstraIteration path' notVisited' edges

updateWeights :: Vertex -> [Edge] -> Vertex -> Vertex
updateWeights v1 edges v2 = min newVert v2
    where newVert = (Vertex (number v2) (addEdge (label v1) (findEdge (number v1) (number v2) edges)))


addEdge :: Maybe (Writer [Edge] Int) -> Maybe Edge -> Maybe (Writer [Edge] Int)
addEdge Nothing Nothing    = Nothing
addEdge Nothing (Just wr)  = Nothing
addEdge (Just wr) Nothing  = Nothing
addEdge (Just wr) (Just e) = Just (writeEdge wr e) 

writeEdge :: Writer [Edge] Int -> Edge -> Writer [Edge] Int
writeEdge writer edge = do dist <- writer
                           tell (edge : [])
                           return (dist + (weight edge))

findEdge :: Int -> Int -> [Edge] -> Maybe Edge
findEdge _ _ [] = Nothing
findEdge id1 id2 (e : ess)
    | id1 == (idFrom e) && id2 == (idTo e) = Just e
    | otherwise = findEdge id1 id2 ess                   

data Edge = Edge {
    idFrom :: Int,
    idTo :: Int,
    weight :: Int
}

instance Show Edge where
    show (Edge idFrom idTo _) = show idFrom ++ " ~> " ++ show idTo
    
data Vertex = Vertex {
    number :: Int, 
    label :: Maybe (Writer [Edge] Int)
}

instance Eq Vertex where
    (==) fir sec = number fir == number sec

instance Show Vertex where
    show (Vertex number Nothing)   = "(" ++ show number ++ " - no path is found)"
    show (Vertex number (Just wr)) = "(To vertex " ++ show number ++ " distance: " ++ show (fst (runWriter wr)) ++ ", path: " ++ show (snd (runWriter wr)) ++ ")"

instance Ord Vertex where
    compare (Vertex _ Nothing) (Vertex _ Nothing)               = EQ
    compare (Vertex _ Nothing) (Vertex _ (Just wr))             = GT
    compare (Vertex _ (Just writer)) (Vertex _ Nothing)         = LT
    compare (Vertex _ (Just writer1)) (Vertex _ (Just writer2)) = compare (fst (runWriter writer1)) (fst (runWriter writer2))
    
graph = Graph [(Vertex 1 (Just (writer (0, [])))), (Vertex 2 Nothing), (Vertex 3 Nothing), (Vertex 4 Nothing), (Vertex 5 Nothing)] [(Edge 1 5 10), (Edge 1 2 1), (Edge 1 3 4), (Edge 1 4 7), (Edge 2 3 3), (Edge 3 4 1), (Edge 4 5 5), (Edge 3 2 2), (Edge 3 5 3), (Edge 2 5 7)] 