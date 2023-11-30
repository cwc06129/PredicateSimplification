(set-logic LIA)

(synth-fun simplify ( (rt_input.obsDistance_3 Int) (Stop Int) (rt_state.speed_status Int) (rt_state.zone_0 Int) (rt_input.obsDistance_2 Int) (rt_input.obsDistance_1 Int) (rt_input.obsDistance_0 Int) ) Bool
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
		rt_input.obsDistance_3
		Stop
		rt_state.speed_status
		rt_state.zone_0
		rt_input.obsDistance_2
		rt_input.obsDistance_1
		rt_input.obsDistance_0
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

(declare-var rt_input.obsDistance_3 Int)
(declare-var Stop Int)
(declare-var rt_state.speed_status Int)
(declare-var rt_state.zone_0 Int)
(declare-var rt_input.obsDistance_2 Int)
(declare-var rt_input.obsDistance_1 Int)
(declare-var rt_input.obsDistance_0 Int)

(constraint (= (simplify rt_input.obsDistance_3 Stop rt_state.speed_status rt_state.zone_0 rt_input.obsDistance_2 rt_input.obsDistance_1 rt_input.obsDistance_0 )
	(> 3 rt_input.obsDistance_0)
	))

(check-synth)