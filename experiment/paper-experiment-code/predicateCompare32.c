#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((!(rt_input.obsDistance[2]*rt_state.zone[0] < 1) && (rt_input.obsDistance[1] >= 5) && ((rt_input.obsDistance[3] - rt_state.zone[0]) <= 1) && (1 == (rt_input.obsDistance[3] - rt_state.zone[1])) && !((rt_input.obsDistance[2] - rt_state.zone[0]) <= 1) && !(1 >= rt_state.zone[1])) || (!(rt_input.obsDistance[2]*rt_state.zone[0] < 1) && (rt_input.obsDistance[1] >= 5) && ((rt_input.obsDistance[3] - rt_state.zone[0]) <= 1) && !(1 == (rt_input.obsDistance[3] - rt_state.zone[1])) && (1 == (rt_input.obsDistance[0] - 19)) && ((rt_input.obsDistance[2] - rt_state.zone[1]) <= 1)) || (!(rt_input.obsDistance[2]*rt_state.zone[0] < 1) && (rt_input.obsDistance[1] >= 5) && !((rt_input.obsDistance[3] - rt_state.zone[0]) <= 1) && (rt_input.obsDistance[2] > rt_state.zone[1]) && (rt_input.obsDistance[0] >= 5)) || (!(rt_input.obsDistance[2]*rt_state.zone[0] < 1) && !(rt_input.obsDistance[1] >= 5) && !(rt_input.obsDistance[2] >= 6) && (rt_state.speed_status == Midium_speed)));
	bool generatedPred = (((((2 + rt_state.zone[0]) > rt_input.obsDistance[3]) && ((1 + rt_state.zone[1]) == rt_input.obsDistance[3])) && ((rt_state.zone[1] > 1) && ((rt_input.obsDistance[2]*rt_state.zone[0] > 0) && (((-1 + rt_input.obsDistance[2]) > rt_state.zone[0]) && (rt_input.obsDistance[1] > 4))))) || (((((2 + rt_state.zone[0]) > rt_input.obsDistance[3]) && (!(1 == (rt_input.obsDistance[3] - rt_state.zone[1])))) && (((2 + rt_state.zone[1]) > rt_input.obsDistance[2]) && ((rt_input.obsDistance[2]*rt_state.zone[0] > 0) && ((rt_input.obsDistance[1] > 4) && (1 == (rt_input.obsDistance[0] - 19)))))) || ((((-1 + rt_input.obsDistance[3]) >  rt_state.zone[0]) && ((rt_input.obsDistance[2] > rt_state.zone[1]) && ((rt_input.obsDistance[2]*rt_state.zone[0] > 0) && ((rt_input.obsDistance[1] > 4) && (rt_input.obsDistance[0] > 4))))) || ((rt_state.speed_status == Midium_speed) && ((rt_input.obsDistance[2]*rt_state.zone[0] > 0) && ((6 > rt_input.obsDistance[2]) && (5 > rt_input.obsDistance[1])))))));

	assert(originPred == generatedPred);
}