#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_triangle.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((rt_input.ta == rt_input.tb) && (rt_input.ta >= rt_input.tc) && !(rt_input.ta > rt_input.tc));
	bool generatedPred = ((rt_input.ta == rt_input.tc) && (rt_input.ta == rt_input.tb));

	assert(originPred == generatedPred);
}