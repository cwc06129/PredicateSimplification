(set-logic LIA)

(synth-fun simplify ((x Int) (y Int) (z Int)) Bool
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
        y
        z
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

(declare-var rt_input.a Int)
(declare-var rt_input.b Int)
(declare-var rt_input.c Int)

(constraint (= (simplify rt_input.a rt_input.b rt_input.c) 
    (or (or 
    (and (and (>= 8 rt_input.a) (not (>= 8 rt_input.b))) (not (>= 8 rt_input.c))) 
    (and (and (not (>= 8 rt_input.a)) (>= 8 rt_input.b)) (not (>= 8 rt_input.c))) 
    )
    (and (not (>= 8 rt_input.a)) (not (>= 8 rt_input.b)))
    )
    ))

(check-synth)