(set-logic LIA)

(synth-fun simplify ( (rt_input.obsDistance_3 Int) (rt_state.zone_1 Int) (Fast_speed Int) (rt_state.speed_status Int) (rt_state.zone_0 Int) (rt_input.obsDistance_2 Int) (rt_input.obsDistance_1 Int) ) Bool
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
		rt_state.zone_1
		Fast_speed
		rt_state.speed_status
		rt_state.zone_0
		rt_input.obsDistance_2
		rt_input.obsDistance_1
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
(declare-var rt_state.zone_1 Int)
(declare-var Fast_speed Int)
(declare-var rt_state.speed_status Int)
(declare-var rt_state.zone_0 Int)
(declare-var rt_input.obsDistance_2 Int)
(declare-var rt_input.obsDistance_1 Int)

(constraint (= (simplify rt_input.obsDistance_3 rt_state.zone_1 Fast_speed rt_state.speed_status rt_state.zone_0 rt_input.obsDistance_2 rt_input.obsDistance_1 )
	( and (> rt_input.obsDistance_2 2) (> (- rt_input.obsDistance_1 9) 4) ) 
	))

(check-synth)