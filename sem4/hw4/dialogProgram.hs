module Main
    where
    
import System.IO
import Control.Exception

main = do hSetBuffering stdin LineBuffering
          doLoop []
    
doLoop list = do
    putStrLn "Enter a command 1 to add value to the sorted list, 2 to remove value from the list, 3 to print list, 0 to exit"
    command <- getLine
    case command of
        '0':_ ->    return ()
        '1':_ -> do putStrLn "Enter an element to add to the list"
                    element <- getLine
                    doLoop $ doAddElement list $ read element
        '2':_ -> do putStrLn "Enter an element to remove from the list"
                    element <- getLine
                    doLoop $ doRemoveElement list $ read element
        '3':_ -> do putStr "Current list: "
                    print list
                    doLoop list
        _  :_ -> do putStrLn "Enter a correct command, please (1 - add value, 2 - remove value, 3 - print list, 0 - exit)"
                    doLoop list
    
doAddElement :: [Int] -> Int -> [Int]
doAddElement [] elem = [elem]
doAddElement (x:xs) elem
    | elem <= x      = elem : x : xs
    | otherwise      = x : doAddElement xs elem
    
doRemoveElement :: [Int] -> Int -> [Int]
doRemoveElement [] elem = []
doRemoveElement (x:xs) elem
    | elem == x         = xs
    | elem > x          = x : doRemoveElement xs elem