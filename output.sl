(set-logic LIA)

(synth-fun simplify ( (rt_input.tc Int) (rt_input.tb Int) (rt_input.ta Int) ) Bool
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
		rt_input.tc
		rt_input.tb
		rt_input.ta
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

(declare-var rt_input.tc Int)
(declare-var rt_input.tb Int)
(declare-var rt_input.ta Int)

(constraint (= (simplify rt_input.tc rt_input.tb rt_input.ta )
	( and (> rt_input.tc 7) (and (and (not (= 3 rt_input.ta)) (> rt_input.tc 8)) (> 6 rt_input.tb)) ) 
	))

(check-synth)