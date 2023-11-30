#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((2 == rt_state.zone[0]) && !(2 >= rt_input.obsDistance[3]) && !(2 >= rt_input.obsDistance[2]) && (2 == rt_state.zone[1]) && !(9 > rt_input.obsDistance[0]) && !(rt_state.speed_status == Fast_speed));
	bool generatedPred = ((rt_input.obsDistance[3] > 2) && ((2 == rt_state.zone[1]) && ((!(rt_state.speed_status == Fast_speed)) && ((2 == rt_state.zone[0]) && ((rt_input.obsDistance[2] > 2) && (rt_input.obsDistance[0] > 8))))));

	assert(originPred == generatedPred);
}