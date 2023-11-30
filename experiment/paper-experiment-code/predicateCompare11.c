#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_second.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((4 >= rt_input.a) && !(4 >= rt_input.c) && !(4 >= rt_input.b) && (6 > rt_input.b)) || ((4 >= rt_input.a) && !(4 >= rt_input.c) && !(4 >= rt_input.b) && !(6 > rt_input.b) && (6 > rt_input.c)) || (!(4 >= rt_input.a) && (6 > rt_input.a) && (4 > rt_input.b) && !(4 >= rt_input.c)) || (!(4 >= rt_input.a) && (6 > rt_input.a) && !(4 > rt_input.b) && (6 > rt_input.c) && (4 >= rt_input.b) && !(4 >= rt_input.c)) || (!(4 >= rt_input.a) && (6 > rt_input.a) && !(4 > rt_input.b) && (6 > rt_input.c) && !(4 >= rt_input.b)) || (!(4 >= rt_input.a) && (6 > rt_input.a) && !(4 > rt_input.b) && !(6 > rt_input.c) && (6 > rt_input.b)) || (!(4 >= rt_input.a) && !(6 > rt_input.a) && (6 > rt_input.b) && (6 > rt_input.c) && (4 >= rt_input.c) && !(4 >= rt_input.b)) || (!(4 >= rt_input.a) && !(6 > rt_input.a) && (6 > rt_input.b) && (6 > rt_input.c) && !(4 >= rt_input.c)));
	bool generatedPred = (((rt_input.c > 4) && ((5 > rt_input.a) && (5 == rt_input.b))) || (((5 == rt_input.c) && ((5 > rt_input.a) && (rt_input.b > 5))) || (((rt_input.c > 4) && ((5 == rt_input.a) && (4 > rt_input.b))) || (((5 == rt_input.c) && ((5 == rt_input.a) && (4 == rt_input.b))) || (((6 > rt_input.c) && ((5 == rt_input.a) && (rt_input.b > 4))) || (((rt_input.c > 5) && ((5 == rt_input.a) && ((rt_input.b > 3) && (6 > rt_input.b)))) || (((5 > rt_input.c) && ((rt_input.a > 5) && (5 == rt_input.b))) || ((5 == rt_input.c) && ((rt_input.a > 5) && (6 > rt_input.b))))))))));

	assert(originPred == generatedPred);
}