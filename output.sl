(set-logic LIA)

(synth-fun simplify ( (rt_input.n2 Int) (rt_input.n1 Int) ) Bool
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
		rt_input.n2
		rt_input.n1
		-1
		0
		1
		2
		3
		4
		5
		6
		7
		8
		9
		(+ StartInt StartInt)
		(- StartInt StartInt)
	))
	)
)

(declare-var rt_input.n2 Int)
(declare-var rt_input.n1 Int)

(constraint (= (simplify rt_input.n2 rt_input.n1 )
	( and (= 6 rt_input.n1) ( and ( not (= rt_input.n1 rt_input.n2) ) ( and ( not (= 3 rt_input.n2) ) (> 9 rt_input.n2) ) ) ) 
	))

(check-synth)