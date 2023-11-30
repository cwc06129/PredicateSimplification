#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_gcd.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (!(9 > rt_input.n2) && !(9 > rt_input.n1));
	bool generatedPred = ((rt_input.n2 > 8) && (rt_input.n1 > 8));

	assert(originPred == generatedPred);
}