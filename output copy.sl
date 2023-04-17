(set-logic LIA)

(synth-fun simplify ( (Midium_speed Int) (rt_input.obsDistance_3 Int) (rt_state.zone_1 Int) (Stop Int) (rt_state.speed_status Int) (rt_state.zone_0 Int) (rt_input.obsDistance_2 Int) (rt_input.obsDistance_1 Int) (rt_input.obsDistance_0 Int) ) Bool
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
		Midium_speed
		rt_input.obsDistance_3
		rt_state.zone_1
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

(declare-var Midium_speed Int)
(declare-var rt_input.obsDistance_3 Int)
(declare-var rt_state.zone_1 Int)
(declare-var Stop Int)
(declare-var rt_state.speed_status Int)
(declare-var rt_state.zone_0 Int)
(declare-var rt_input.obsDistance_2 Int)
(declare-var rt_input.obsDistance_1 Int)
(declare-var rt_input.obsDistance_0 Int)

(constraint (>= rt_state.zone_0 0))
(constraint (< rt_state.zone_0 3))
(constraint (>= rt_state.zone_1 0))
(constraint (< rt_state.zone_1 3))
(constraint (>= rt_input.obsDistance_0 0))
(constraint (< rt_input.obsDistance_0 60))
(constraint (>= rt_input.obsDistance_1 0))
(constraint (< rt_input.obsDistance_1 60))
(constraint (>= rt_input.obsDistance_2 0))
(constraint (< rt_input.obsDistance_2 60))
(constraint (>= rt_input.obsDistance_3 0))
(constraint (< rt_input.obsDistance_3 60))
(constraint (>= rt_state.speed_status 0))
(constraint (< rt_state.speed_status 4))
(constraint (>= Midium_speed 0))
(constraint (< Midium_speed 4))
(constraint (>= Stop 0))
(constraint (< Stop 4))
(constraint (= (simplify Midium_speed rt_input.obsDistance_3 rt_state.zone_1 Stop rt_state.speed_status rt_state.zone_0 rt_input.obsDistance_2 rt_input.obsDistance_1 rt_input.obsDistance_0 )
	( and ( not ( = rt_input.obsDistance_3 rt_state.zone_1 ) )  (and (> rt_input.obsDistance_3 rt_input.obsDistance_2) (> 5 rt_input.obsDistance_3)))
	))

(check-synth)