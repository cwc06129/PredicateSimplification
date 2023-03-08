(set-logic LIA)

(synth-fun maxProfit ((a0 Int) (a1 Int) (a2 Int) (a3 Int) (a4 Int) (a5 Int)) Int
    ((Start Int(
        0 1 2 3 4 5
        a0 a1 a2 a3 a4 a5
        (+ Start Start)
        (- Start Start)
        (* Start Start)
        (ite StartBool Start Start)
    ))
    (StartBool Bool(
        (and StartBool StartBool)
        (or StartBool StartBool)
        (not StartBool)
        (< Start Start)
        (> Start Start)
        (<= Start Start)
        (>= Start Start)
        (= Start Start)
    )))
)

(constraint (= (maxProfit 7 4 4 2 5 1) 3))
(constraint (= (maxProfit 4 10 10 6 10 5) 6))
(constraint (= (maxProfit 10 5 4 3 3 2) 0))
(constraint (= (maxProfit 1 2 3 3 1 6) 5))
(constraint (= (maxProfit 3 7 10 6 6 7) 7))

(constraint (= (maxProfit 1 6 8 6 2 4) 7))
(constraint (= (maxProfit 3 2 6 4 5 1) 4))
(constraint (= (maxProfit 10 2 6 3 2 5) 4))
(constraint (= (maxProfit 2 9 3 1 3 8) 7))
(constraint (= (maxProfit 10 5 4 1 2 3) 2))

(constraint (= (maxProfit 1 5 10 1 3 9) 9))
(constraint (= (maxProfit 2 9 1 1 7 1) 7))
(constraint (= (maxProfit 6 2 2 1 4 8) 7))
(constraint (= (maxProfit 10 9 9 8 6 9) 3))
(constraint (= (maxProfit 2 9 1 4 9 2) 8))

(constraint (= (maxProfit 5 8 2 10 5 6) 8))
(constraint (= (maxProfit 9 7 5 3 8 9) 6))
(constraint (= (maxProfit 1 6 10 10 10 4) 9))
(constraint (= (maxProfit 8 1 3 8 3 5) 7))
(constraint (= (maxProfit 4 7 5 10 7 7) 6))

(check-synth)