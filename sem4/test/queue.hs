data ListElement = ListElement { value :: Int,
                                 priority :: Int
                               } deriving (Eq, Show)
                               
getValue :: ListElement -> Int
getValue (ListElement value priority) = value

getPriority :: ListElement -> Int
getPriority (ListElement value priority) = priority

main = do doLoop []
          
doLoop list = do putStrLn "Enter a command 1 to add value with priority"
                 putStrLn "Enter a command 2 to get value with inputted priority"
                 putStrLn "Enter a command 3 to get value with max priority"
                 putStrLn "Enter a command 4 to print the queue"
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
                    _     -> do putStrLn "Enter a correct command, please"
                                doLoop list
                                
doAddElement :: [ListElement] -> Int -> Int -> [ListElement]
doAddElement [] value priority = [ListElement value priority]
doAddElement (prevElem@(ListElement curValue curPriority):xs) value priority
    | priority >= curPriority = (ListElement value priority) : prevElem : xs
    | otherwise              = prevElem : doAddElement xs value priority
    
doGetPriorityItem :: [ListElement] -> Int -> Maybe Int
doGetPriorityItem [] _ = Nothing
doGetPriorityItem ((ListElement curValue curPriority):xs) priority
    | priority == curPriority = Just curValue
    | otherwise               = doGetPriorityItem xs priority
    
doGetMaxPriorityItem :: [ListElement] -> Maybe Int
doGetMaxPriorityItem []                                      = Nothing
doGetMaxPriorityItem ((ListElement curValue curPriority):xs) = Just curValue

doPrintQueue :: [ListElement] -> IO ()
doPrintQueue ((ListElement curValue curPriority):[]) = do   putStr $ show $ curValue
                                                            putStrLn "."  
doPrintQueue ((ListElement curValue curPriority):xs) = do   putStr $ show $ curValue
                                                            putStr ", "
                                                            doPrintQueue xs