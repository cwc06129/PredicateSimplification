(set-logic LIA)

(synth-fun simplify ( (rt_input.number Int) ) Bool
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
		rt_input.number
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

(declare-var rt_input.number Int)

(constraint (= (simplify rt_input.number )
	( and (> 8 rt_input.number) (> rt_input.number 6) ) 
	))

(check-synth)