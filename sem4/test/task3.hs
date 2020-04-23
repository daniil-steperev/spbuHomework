plotRhombus :: Int -> IO ()
plotRhombus n = getRhombus 0 (2 * n - 2) where
    getRhombus curLine maxSize
        | curLine < n        = do putStrLn (replicate (n - curLine - 1) ' ' ++ replicate (2 * curLine + 1) 'x')
                                  getRhombus (curLine + 1) maxSize   
        | curLine <= maxSize = do putStrLn (replicate (curLine - n + 1) ' ' ++ replicate (2 * (maxSize - curLine) + 1) 'x')
                                  getRhombus (curLine + 1) maxSize
        | otherwise          = return ()
        
test :: IO ()
test = plotRhombus 4