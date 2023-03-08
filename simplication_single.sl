(set-logic LIA)

(synth-fun simplify ((x Int) (y Int)) Bool
    ((Start Bool (
        true
        false
        (and Start Start)
        (or Start Start)
        (not Start)
        (= StartInt StartInt)
        (> StartInt StartInt)
        (< StartInt StartInt)
        (>= StartInt StartInt)
        (<= StartInt StartInt)
    ))
    (StartInt Int (
        x
        1
        2
        3
        4
        5
        6
        7
        8
        9
        0
        -1
        (+ StartInt StartInt)
        (- StartInt StartInt)
    ))
    )
)

(declare-var rt_input.n1 Int)
(declare-var rt_input.n2 Int)

(constraint (= (simplify rt_input.n1 rt_input.n2) 
    ( or ( and ( >= 2 rt_input.n1 ) ( and ( not ( = 1 ( - 4 rt_input.n2 ) ) ) ( >= 4 rt_input.n2 ) ) ) ( or ( and ( >= 2 rt_input.n1 ) ( and ( not ( = 1 ( - 4 rt_input.n2 ) ) ) ( and ( not ( >= 4 rt_input.n2 ) ) ( >= rt_input.n2 8 ) ) ) ) ( or ( and ( >= 2 rt_input.n1 ) ( and ( not ( = 1 ( - 4 rt_input.n2 ) ) ) ( and ( not ( >= 4 rt_input.n2 ) ) ( and ( not ( >= rt_input.n2 8 ) ) ( = 6 rt_input.n2 ) ) ) ) ) ( or ( and ( not ( >= 2 rt_input.n1 ) ) ( and ( = 6 rt_input.n1 ) ( and ( not ( = rt_input.n1 rt_input.n2 ) ) ( and ( not ( = 1 ( - 4 rt_input.n2 ) ) ) ( > 9 rt_input.n2 ) ) ) ) ) ( or ( and ( not ( >= 2 rt_input.n1 ) ) ( and ( not ( = 6 rt_input.n1 ) ) ( and ( = 6 rt_input.n2 ) ( and ( not ( > 4 rt_input.n1 ) ) ( not ( = 9 rt_input.n1 ) ) ) ) ) ) ( or ( and ( not ( >= 2 rt_input.n1 ) ) ( and ( not ( = 6 rt_input.n1 ) ) ( and ( not ( = 6 rt_input.n2 ) ) ( and ( >= 2 rt_input.n2 ) ( = 1 ( - 9 rt_input.n1 ) ) ) ) ) ) ( and ( not ( >= 2 rt_input.n1 ) ) ( and ( not ( = 6 rt_input.n1 ) ) ( and ( not ( = 6 rt_input.n2 ) ) ( and ( >= 2 rt_input.n2 ) ( and ( not ( = 1 ( - 9 rt_input.n1 ) ) ) ( = 4 rt_input.n1 ) ) ) ) ) ) ) ) ) ) ) )  
    ))
(check-synth)