#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_gcd.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = ((rt_input.n1 == rt_input.n2) && !(6 >= rt_input.n2) && !(rt_input.n1 >= 8));
	bool generatedPred = (((rt_input.n2 > 6) && (rt_input.n1 == rt_input.n2)) && (8 > rt_input.n1));

	assert(originPred == generatedPred);
}