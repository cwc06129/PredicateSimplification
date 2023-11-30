#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((1 == rt_input.obsDistance[3]) && (rt_state.speed_status == Fast_speed)) || ((1 == rt_input.obsDistance[3]) && !(rt_state.speed_status == Fast_speed) && !(8 > rt_input.obsDistance[2]) && !(1 >= rt_state.zone[0]) && (2 == rt_state.zone[1])));
	bool generatedPred = (((1 == rt_input.obsDistance[3]) && (rt_state.speed_status == Fast_speed)) || ((1 == rt_input.obsDistance[3]) && ((2 == rt_state.zone[1]) && ((!(rt_state.speed_status == Fast_speed)) &&((rt_state.zone[0] > 1) && (rt_input.obsDistance[2] > 7))))));

	assert(originPred == generatedPred);
}