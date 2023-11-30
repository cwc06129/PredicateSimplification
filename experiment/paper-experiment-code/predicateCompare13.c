#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((4 > rt_input.c) && (2 >= rt_input.c) && !(2 >= rt_input.a) && (4 > rt_input.a) && !(2 >= rt_input.b)) || ((4 > rt_input.c) && (2 >= rt_input.c) && !(2 >= rt_input.a) && !(4 > rt_input.a) && (4 > rt_input.b) && !(2 >= rt_input.b)) || ((4 > rt_input.c) && !(2 >= rt_input.c) && (4 > rt_input.b) && (2 >= rt_input.a) && !(2 >= rt_input.b)) || ((4 > rt_input.c) && !(2 >= rt_input.c) && (4 > rt_input.b) && !(2 >= rt_input.a)) || ((4 > rt_input.c) && !(2 >= rt_input.c) && !(4 > rt_input.b) && (4 > rt_input.a)) || (!(4 > rt_input.c) && (4 > rt_input.b) && (4 > rt_input.a) && (2 >= rt_input.b) && !(2 >= rt_input.a)) || (!(4 > rt_input.c) && (4 > rt_input.b) && (4 > rt_input.a) && !(2 >= rt_input.b)));
	bool generatedPred = (((3 > rt_input.c) && ((3 == rt_input.a) && (rt_input.b > 2))) || (((3 > rt_input.c) && ((rt_input.a > 3) && (3 == rt_input.b))) || (((3 == rt_input.c) && ((3 > rt_input.a) && (3 == rt_input.b))) || (((3 == rt_input.c) && ((rt_input.a > 2) && (4 > rt_input.b))) || (((3 == rt_input.c) && ((4 > rt_input.a) && (rt_input.b > 3))) || (((rt_input.c > 3) && ((3 == rt_input.a) && (3 > rt_input.b))) || ((rt_input.c > 3) && ((4 > rt_input.a) && (3 == rt_input.b)))))))));

	assert(originPred == generatedPred);
}