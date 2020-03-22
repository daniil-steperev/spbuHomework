module Main
    where
    
import System.IO
import Control.Exception

main = do hSetBuffering stdin LineBuffering
          doLoop []
    
doLoop :: [(String, Int)] -> IO ()    
doLoop list = do putStrLn "Enter a command 1 to add new record (name, phone)"
                 putStrLn "2 to find phone by name"
                 putStrLn "3 to find name by phone"
                 putStrLn "4 to save current data to file"
                 putStrLn "5 to load data from file"
                 putStrLn "0 to exit"
                 command <- getLine
                 case command of
                    '0':_ -> return ()
                    '1':_ -> do putStrLn "Enter a name to add: "
                                name <- getLine
                                putStrLn "Enter a phone to add: "
                                phone <- getLine
                                doLoop $ doAddRecord list name $ read phone
                    '2':_ -> do putStrLn "Enter the name whose phone should be found: "
                                name <- getLine
                                putStrLn $ show $ doFindByName list name
                                doLoop list
                    '3':_ -> do putStrLn "Enter the phone which owner should be found: "
                                phone <- getLine
                                putStrLn $ show $ doFindByPhone list $ read phone
                                doLoop list
                    '4':_ -> do putStrLn "Enter a file name to save the data: "
                                fileName <- getLine
                                doSaveToFile list fileName
                                doLoop list
                    '5':_ -> do putStrLn "Enter the file name to load the data"
                                fileName <- getLine
                                phoneBook <- readFile fileName
                                doLoop $ doLoadFromFile phoneBook
                    _  :_ -> do putStrLn "Enter a correct command, please"
                                doLoop list
                    
doAddRecord :: [(String, Int)] -> String -> Int -> [(String, Int)]
doAddRecord [] name phone = [(name, phone)]
doAddRecord (curRec@(curName,curPhone):xs) name phone
    | phone <= curPhone = (name, phone):curRec:xs
    | otherwise         = curRec:doAddRecord xs name phone
    
doFindByName :: [(String, Int)] -> String -> Maybe Int
doFindByName [] name = Nothing
doFindByName ((curName, curPhone):xs) name
    | name == curName = Just curPhone
    | otherwise       = doFindByName xs name
    
doFindByPhone :: [(String, Int)] -> Int -> Maybe String
doFindByPhone [] phone = Nothing
doFindByPhone ((curName, curPhone):xs) phone
    | phone == curPhone = Just curName
    | otherwise         = doFindByPhone xs phone
    
doSaveToFile :: [(String, Int)] -> String -> IO ()
doSaveToFile list fileName = bracket (openFile fileName WriteMode) hClose
                                     (\h -> hPutStrLn h $ concatMap (\(curName, curPhone) -> curName ++ " " ++ show curPhone ++ ['\n']) list)
                                     
doLoadFromFile :: String -> [(String, Int)]
doLoadFromFile fileData = map (\[name, phone] -> (name, read phone :: Int)) (filter (\x -> x /= []) $ map words $ lines fileData)
                                     
                                     