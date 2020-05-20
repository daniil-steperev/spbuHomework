data QueueElement = QueueElement { value :: Int,
                                 priority :: Int
                               } deriving (Eq, Show)
                               
getValue :: QueueElement -> Int
getValue (QueueElement value priority) = value

getPriority :: QueueElement -> Int
getPriority (QueueElement value priority) = priority

main = do doLoop []
          
doLoop list = do putStrLn "Enter a command 1 to add value with priority"
                 putStrLn "Enter a command 2 to get first value with inputted priority"
                 putStrLn "Enter a command 3 to get first value with max priority"
                 putStrLn "Enter a command 4 to print the queue"
                 putStrLn "Enter a command 5 to get all values with inputted priority"
                 putStrLn "Enter a command 6 to get all values with max priority"
                 putStrLn "Enter a command 0 to quit"
                 
                 command <- getLine
                 case command of
                    '0':_ -> return ()
                    '1':_ -> do putStrLn "Enter an element to add to the list"
                                value <- getLine
                                putStrLn "Enter a priority of the element"
                                priority <- getLine
                                doLoop $ doAddElement list (read value) (read priority)
                    '2':_ -> do putStrLn "Enter the priority of an element"
                                priority <- getLine
                                putStr "Value with inputted priority: "
                                putStrLn $ show $ doGetPriorityItem list $ read priority
                                doLoop list
                    '3':_ -> do putStr "Value with max priority: "
                                putStrLn $ show $ doGetMaxPriorityItem list
                                doLoop list
                    '4':_ -> do putStr $ "Queue: "
                                doPrintQueue list
                                doLoop list
                    '5':_ -> do putStrLn "Enter the priority of an element"
                                priority <- getLine
                                putStr "Values with inputted priority: "
                                doPrintPriorityItems list (read priority) []
                                doLoop list
                    '6':_ -> do putStr "Values with max priority: "
                                doPrintMaxPriorityItems list []
                                doLoop list            
                    _     -> do putStrLn "Enter a correct command, please"
                                doLoop list
                                
doAddElement :: [QueueElement] -> Int -> Int -> [QueueElement]
doAddElement [] value priority = [QueueElement value priority]
doAddElement (prevElem@(QueueElement curValue curPriority):xs) value priority
    | priority >= curPriority = (QueueElement value priority) : prevElem : xs
    | otherwise              = prevElem : doAddElement xs value priority
    
doGetPriorityItem :: [QueueElement] -> Int -> Maybe Int
doGetPriorityItem [] _ = Nothing
doGetPriorityItem ((QueueElement curValue curPriority):xs) priority
    | priority == curPriority = Just curValue
    | curPriority > priority  = doGetPriorityItem xs priority
    | otherwise               = Nothing                                            -- current priority is less than the found one
    
doPrintPriorityItems :: [QueueElement] -> Int -> [QueueElement] -> IO ()
doPrintPriorityItems [] _ items = doPrintQueue $ reverse items
doPrintPriorityItems (elem@(QueueElement curValue curPriority):xs) priority items
    | priority == curPriority = doPrintPriorityItems xs priority (elem : items)
    | curPriority > priority  = doPrintPriorityItems xs priority items
    | otherwise               = doPrintQueue $ reverse items                       -- current priority is less than the found one
    
doPrintMaxPriorityItems :: [QueueElement] -> [QueueElement] -> IO ()
doPrintMaxPriorityItems [] items = doPrintQueue $ reverse items
doPrintMaxPriorityItems (x:xs) [] = doPrintMaxPriorityItems xs [x]
doPrintMaxPriorityItems (elem@(QueueElement curValue curPriority):xs) (maxElem@(QueueElement _ maxPriority):ys)
    | curPriority == maxPriority = doPrintMaxPriorityItems xs (elem : maxElem : ys)
    | otherwise = doPrintQueue $ reverse $ maxElem:ys
    
doGetMaxPriorityItem :: [QueueElement] -> Maybe Int
doGetMaxPriorityItem []                                       = Nothing
doGetMaxPriorityItem ((QueueElement curValue curPriority):xs) = Just curValue

doPrintQueue :: [QueueElement] -> IO ()
doPrintQueue [] = putStrLn "is empty."
doPrintQueue ((QueueElement curValue curPriority):[]) = do  putStr $ show $ curValue
                                                            putStrLn "."  
doPrintQueue ((QueueElement curValue curPriority):xs) = do  putStr $ show $ curValue
                                                            putStr ", "
                                                            doPrintQueue xs