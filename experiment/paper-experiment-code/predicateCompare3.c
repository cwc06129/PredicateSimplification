#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_gcd.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((4 == rt_input.n1) && (4 == rt_input.n2)) || ((4 == rt_input.n1) && !(4 == rt_input.n2) && (rt_input.n2 >= 8)) || (!(4 == rt_input.n1) && (4 == rt_input.n2) && (rt_input.n1 >= 8)));
	bool generatedPred = (((4 == rt_input.n2) && (4 == rt_input.n1)) || (((rt_input.n2 > 7) && (4 == rt_input.n1)) || ((4 == rt_input.n2) && (rt_input.n1 > 7))));

	assert(originPred == generatedPred);
}