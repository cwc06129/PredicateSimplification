#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((8 >= rt_input.a) && !(8 >= rt_input.b) && !(8 >= rt_input.c)) || (!(8 >= rt_input.a) && (8 >= rt_input.b) && !(8 >= rt_input.c)) || (!(8 >= rt_input.a) && !(8 >= rt_input.b)));
	bool generatedPred = (((rt_input.c > 8) && ((9 > rt_input.a) && (rt_input.b > 8))) || (((rt_input.c > 8) && ((rt_input.a > 8) && (9 > rt_input.b))) || ((rt_input.a > 8) && (rt_input.b > 8))));

	assert(originPred == generatedPred);
}