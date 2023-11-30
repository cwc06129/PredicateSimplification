#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((2 >= rt_input.obsDistance[0]) && (2 >= rt_input.obsDistance[1]) && !(rt_state.speed_status == Slow_speed)) || ((2 >= rt_input.obsDistance[0]) && !(2 >= rt_input.obsDistance[1])) || (!(2 >= rt_input.obsDistance[0]) && (7 > rt_input.obsDistance[3]) && (2 == rt_state.zone[0]) && !(1 >= rt_state.zone[1]) && (7 > rt_input.obsDistance[2]) && (rt_state.speed_status == Fast_speed) && !(2 == rt_input.obsDistance[3])) || (!(2 >= rt_input.obsDistance[0]) && (7 > rt_input.obsDistance[3]) && (2 == rt_state.zone[0]) && !(1 >= rt_state.zone[1]) && !(7 > rt_input.obsDistance[2]) && (2 >= rt_input.obsDistance[3])));
	bool generatedPred = (((!(rt_state.speed_status == Slow_speed)) && ((3 > rt_input.obsDistance[1]) && (3 > rt_input.obsDistance[0]))) || (((rt_input.obsDistance[1] > 2) && (3 > rt_input.obsDistance[0])) || ((((7 > rt_input.obsDistance[3]) && (!(2 == rt_input.obsDistance[3]))) && ((rt_state.zone[1] > 1) && ((rt_state.speed_status == Fast_speed) && ((2 == rt_state.zone[0]) && ((7 > rt_input.obsDistance[2]) && (rt_input.obsDistance[0] > 2)))))) || ((3 > rt_input.obsDistance[3]) && ((rt_state.zone[1] > 1) && ((2 == rt_state.zone[0]) && ((rt_input.obsDistance[2] > 6) && (rt_input.obsDistance[0] > 2))))))));

	assert(originPred == generatedPred);
}