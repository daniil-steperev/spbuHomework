unitList :: [Int]
unitList = 1 : (-1) : unitList

getTaskList :: [Int]
getTaskList = zipWith (*) unitList [1..]

testUnitList :: [Int]
testUnitList = take 10 unitList

testTaskList :: [Int]
testTaskList = take 10 getTaskList