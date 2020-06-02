knottingNumbers :: [Integer]
knottingNumbers = 1:7:9:concatMap(\elem -> [elem * 10 + 1, elem * 10 + 7, elem * 10 + 9]) knottingNumbers