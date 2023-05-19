(set-logic LIA)

(synth-fun simplify ( (a Int) (b Int) (c Int) ) Bool
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
		b
		c
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

(declare-var a Int)
(declare-var b Int)
(declare-var c Int)

(constraint (= (simplify a b c)
	(and (and (> c 2) (> c a)) (and (> c 5) (> 9 c)))
	))

(check-synth)