#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((2 >= rt_input.a) && (2 == rt_input.a) && (2 >= rt_input.c) && (1 >= rt_input.b) && !(1 >= rt_input.c)) || ((2 >= rt_input.a) && (2 == rt_input.a) && (2 >= rt_input.c) && !(1 >= rt_input.b)) || ((2 >= rt_input.a) && (2 == rt_input.a) && !(2 >= rt_input.c) && (2 >= rt_input.b)) || ((2 >= rt_input.a) && !(2 == rt_input.a) && (2 == rt_input.b) && !(1 >= rt_input.c)) || ((2 >= rt_input.a) && !(2 == rt_input.a) && !(2 == rt_input.b) && (2 >= rt_input.c) && !(1 >= rt_input.c) && !(2 >= rt_input.b)) || (!(2 >= rt_input.a) && (2 >= rt_input.b) && (2 >= rt_input.c) && (2 == rt_input.b)) || (!(2 >= rt_input.a) && (2 >= rt_input.b) && (2 >= rt_input.c) && !(2 == rt_input.b) && !(1 >= rt_input.c)));
	bool generatedPred = (((2 == rt_input.c) && ((2 == rt_input.a) && (2 > rt_input.b))) || (((3 > rt_input.c) && ((2 == rt_input.a) && (rt_input.b > 1))) || (((rt_input.c > 2) && ((2 == rt_input.a) && (3 > rt_input.b))) || (((rt_input.c > 1) && ((2 > rt_input.a) && (2 == rt_input.b))) || (((2 == rt_input.c) && ((2 > rt_input.a) && (rt_input.b > 2))) || (((3 > rt_input.c) && ((rt_input.a > 2) && (2 == rt_input.b))) || ((2 == rt_input.c) && ((rt_input.a > 2) && (2 > rt_input.b)))))))));

	assert(originPred == generatedPred);
}