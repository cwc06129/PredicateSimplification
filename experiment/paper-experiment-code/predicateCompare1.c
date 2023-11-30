#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_prime.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((!(rt_input.number > 7) && (rt_input.number > 1) && !(4 == rt_input.number) && (rt_input.number >= 6) && !(7 > rt_input.number)) || (!(rt_input.number > 7) && (rt_input.number > 1) && !(4 == rt_input.number) && !(rt_input.number >= 6)));
	bool generatedPred = ((7 == rt_input.number) || (((4 > rt_input.number) || (5 == rt_input.number)) && (rt_input.number > 1)));

	assert(originPred == generatedPred);
}