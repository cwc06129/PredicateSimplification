#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((2 >= rt_input.obsDistance[1]) && !(2 >= rt_input.obsDistance[0])) || (!(2 >= rt_input.obsDistance[1]) && (2 >= rt_input.obsDistance[2]) && (2 == rt_state.zone[1]) && (rt_state.speed_status == Slow_speed) && (1 == rt_input.obsDistance[2]) && !(1 >= rt_input.obsDistance[3]) && !(6 > rt_input.obsDistance[0])) || (!(2 >= rt_input.obsDistance[1]) && (2 >= rt_input.obsDistance[2]) && (2 == rt_state.zone[1]) && !(rt_state.speed_status == Slow_speed)));
	bool generatedPred = (((3 > rt_input.obsDistance[1]) && (rt_input.obsDistance[0] > 2)) || (((rt_state.speed_status == Slow_speed) && ((rt_input.obsDistance[3] > 1) && ((2 == rt_state.zone[1]) && ((1 == rt_input.obsDistance[2]) && ((rt_input.obsDistance[1] > 2) && (rt_input.obsDistance[0] > 5)))))) || ((!(rt_state.speed_status == Slow_speed)) && ((2 == rt_state.zone[1]) && ((3 > rt_input.obsDistance[2]) && (rt_input.obsDistance[1] > 2))))));

	assert(originPred == generatedPred);
}