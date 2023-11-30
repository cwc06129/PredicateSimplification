#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((4 == rt_input.b) && (4 == rt_input.a)) || ((4 == rt_input.b) && !(4 == rt_input.a) && (4 >= rt_input.c) && (4 > rt_input.a) && !(4 > rt_input.c)) || ((4 == rt_input.b) && !(4 == rt_input.a) && (4 >= rt_input.c) && !(4 > rt_input.a)) || ((4 == rt_input.b) && !(4 == rt_input.a) && !(4 >= rt_input.c) && (4 >= rt_input.a)) || (!(4 == rt_input.b) && (4 == rt_input.a) && (4 >= rt_input.c) && (4 >= rt_input.b) && !(4 > rt_input.c)) || (!(4 == rt_input.b) && (4 == rt_input.a) && (4 >= rt_input.c) && !(4 >= rt_input.b)) || (!(4 == rt_input.b) && (4 == rt_input.a) && !(4 >= rt_input.c) && (4 > rt_input.b)) || (!(4 == rt_input.b) && !(4 == rt_input.a) && (4 >= rt_input.c) && !(4 > rt_input.c) && (4 > rt_input.a) && !(4 > rt_input.b)) || (!(4 == rt_input.b) && !(4 == rt_input.a) && (4 >= rt_input.c) && !(4 > rt_input.c) && !(4 > rt_input.a) && (4 > rt_input.b)));
	bool generatedPred = (((4 == rt_input.a) && (4 == rt_input.b)) || (((4 == rt_input.c) && ((4 > rt_input.a) && (4 == rt_input.b))) || (((5 > rt_input.c) && ((rt_input.a > 4) && (4 == rt_input.b))) || (((rt_input.c > 4) && ((4 > rt_input.a) && (4 == rt_input.b))) || (((4 == rt_input.c) && ((4 == rt_input.a) && (4 > rt_input.b))) || (((5 > rt_input.c) && ((4 == rt_input.a) && (rt_input.b > 4))) || (((rt_input.c > 4) && ((4 == rt_input.a) && (4 > rt_input.b))) || (((4 == rt_input.c) && ((4 > rt_input.a) && (rt_input.b > 4))) || ((4 == rt_input.c) && ((rt_input.a > 4) && (4 > rt_input.b)))))))))));

	assert(originPred == generatedPred);
}