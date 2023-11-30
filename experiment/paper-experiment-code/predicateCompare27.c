#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_CA.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (!(1 >= rt_state.zone[0]) && !(1 >= rt_state.zone[1]) && !(2 >= rt_input.obsDistance[2]) && !(2 >= rt_input.obsDistance[3]) && !(2 >= rt_input.obsDistance[1]));
	bool generatedPred = ((rt_input.obsDistance[3] > 2) && ((rt_state.zone[1] > 1) && ((rt_state.zone[0] > 1) && ((rt_input.obsDistance[2] > 2) && (rt_input.obsDistance[1] > 2)))));

	assert(originPred == generatedPred);
}