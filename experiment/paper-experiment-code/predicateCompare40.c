#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((!(1 >= rt_state.zone[0]) && !(2 >= rt_input.obsDistance[3]) && !(2 >= rt_input.obsDistance[2]) && (14 > rt_input.obsDistance[1]) && !(rt_state.speed_status == Fast_speed) && (2 == rt_state.zone[1]) && (19 > rt_input.obsDistance[2]) && !(2 >= rt_input.obsDistance[1]) && !(14 > rt_input.obsDistance[3])) || (!(1 >= rt_state.zone[0]) && !(2 >= rt_input.obsDistance[3]) && !(2 >= rt_input.obsDistance[2]) && (14 > rt_input.obsDistance[1]) && !(rt_state.speed_status == Fast_speed) && (2 == rt_state.zone[1]) && !(19 > rt_input.obsDistance[2])) || (!(1 >= rt_state.zone[0]) && !(2 >= rt_input.obsDistance[3]) && !(2 >= rt_input.obsDistance[2]) && !(14 > rt_input.obsDistance[1])));
	bool generatedPred = ((((rt_input.obsDistance[3] - 9) > 4) && ((2 == rt_state.zone[1]) && ((!(rt_state.speed_status == Fast_speed)) && ((rt_state.zone[0] > 1) && (((rt_input.obsDistance[2] > 2) && ((9 + 9) >= rt_input.obsDistance[2])) && (((9 + 5) > rt_input.obsDistance[1]) && (rt_input.obsDistance[1] > 2))))))) || (((rt_input.obsDistance[3] > 2) && ((2 == rt_state.zone[1]) && ((!(rt_state.speed_status == Fast_speed)) && ((rt_state.zone[0] > 1) && (((rt_input.obsDistance[2] - 9) > 9) && ((9 + 5) > rt_input.obsDistance[1])))))) || ((rt_input.obsDistance[3] > 2) && ((rt_state.zone[0] > 1) && ((rt_input.obsDistance[2] > 2) && ((rt_input.obsDistance[1] - 9) > 4))))));

	assert(originPred == generatedPred);
}