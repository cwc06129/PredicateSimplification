#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((1 >= rt_input.obsDistance[1]) && (2 >= rt_input.obsDistance[0]) && !(13 >= rt_input.obsDistance[2]) && (13 > rt_input.obsDistance[3])) || ((1 >= rt_input.obsDistance[1]) && !(2 >= rt_input.obsDistance[0])) || (!(1 >= rt_input.obsDistance[1]) && (2 >= rt_input.obsDistance[2]) && (2 == rt_state.zone[0]) && !(7 > rt_input.obsDistance[3]) && !(1 >= rt_state.zone[1])));
	bool generatedPred = ((((9 + 4) >  rt_input.obsDistance[3]) && (((rt_input.obsDistance[2] - 9) > 4) && ((2 > rt_input.obsDistance[1]) && (3 > rt_input.obsDistance[0])))) || (((2 > rt_input.obsDistance[1]) && (rt_input.obsDistance[0] > 2)) || ((rt_input.obsDistance[3] > 6) && ((rt_state.zone[1] > 1) && ((2 == rt_state.zone[0]) && ((3 > rt_input.obsDistance[2]) && (rt_input.obsDistance[1] > 1)))))));

	assert(originPred == generatedPred);
}