(set-logic LIA)

(synth-fun simplify ( (a Int) ) Bool
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
		a
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
	( or ( and ( not ( > rt_input.number 7 ) ) ( and ( > rt_input.number 1 ) ( and ( not ( = 4 rt_input.number ) ) ( and ( >= rt_input.number 6 ) ( not ( > 7 rt_input.number ) ) ) ) ) ) ( and ( not ( > rt_input.number 7 ) ) ( and ( > rt_input.number 1 ) ( and ( not ( = 4 rt_input.number ) ) ( not ( >= rt_input.number 6 ) ) ) ) ) ) 
	))

(check-synth)