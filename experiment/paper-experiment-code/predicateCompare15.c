#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((6 >= rt_input.c) && !(6 >= rt_input.b) && !(6 >= rt_input.a) && (8 > rt_input.a)) || ((6 >= rt_input.c) && !(6 >= rt_input.b) && !(6 >= rt_input.a) && !(8 > rt_input.a) && (8 > rt_input.b)) || (!(6 >= rt_input.c) && (8 > rt_input.c) && (6 >= rt_input.b) && !(6 >= rt_input.a)) || (!(6 >= rt_input.c) && (8 > rt_input.c) && !(6 >= rt_input.b) && (8 > rt_input.a)) || (!(6 >= rt_input.c) && (8 > rt_input.c) && !(6 >= rt_input.b) && !(8 > rt_input.a) && (8 > rt_input.b)) || (!(6 >= rt_input.c) && !(8 > rt_input.c) && (8 > rt_input.b) && (6 >= rt_input.b) && !(6 >= rt_input.a) && (8 > rt_input.a)) || (!(6 >= rt_input.c) && !(8 > rt_input.c) && (8 > rt_input.b) && !(6 >= rt_input.b) && (8 > rt_input.a)));
	bool generatedPred = (((7 > rt_input.c) && ((7 == rt_input.a) && (rt_input.b > 6))) || (((7 > rt_input.c) && ((rt_input.a > 7) && (7 == rt_input.b))) || (((7 == rt_input.c) && ((rt_input.a > 6) && (7 > rt_input.b))) || (((7 == rt_input.c) && ((8 > rt_input.a) && (rt_input.b > 6))) || (((7 == rt_input.c) && ((rt_input.a > 7) && (7 == rt_input.b))) || (((rt_input.c > 7) && ((7 == rt_input.a) && (7 > rt_input.b))) || ((rt_input.c > 7) && ((8 > rt_input.a) && (7 == rt_input.b)))))))));

	assert(originPred == generatedPred);
}