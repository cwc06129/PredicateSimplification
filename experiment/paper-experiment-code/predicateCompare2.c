#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "common/model_gcd.h"

INPUT rt_input;
STATE rt_state;
OUTPUT rt_output;

int main() {
	bool originPred = (((2 >= rt_input.n1) && !(1 == (4 - rt_input.n2)) && (4 >= rt_input.n2)) || ((2 >= rt_input.n1) && !(1 == (4 - rt_input.n2)) && !(4 >= rt_input.n2) && (rt_input.n2 >= 8)) || ((2 >= rt_input.n1) && !(1 == (4 - rt_input.n2)) && !(4 >= rt_input.n2) && !(rt_input.n2 >= 8) && (6 == rt_input.n2)) || (!(2 >= rt_input.n1) && (6 == rt_input.n1) && !(rt_input.n1 == rt_input.n2) && !(1 == (4 - rt_input.n2)) && (9 > rt_input.n2)) || (!(2 >= rt_input.n1) && !(6 == rt_input.n1) && (6 == rt_input.n2) && !(4 > rt_input.n1) && !(9 == rt_input.n1)) || (!(2 >= rt_input.n1) && !(6 == rt_input.n1) && !(6 == rt_input.n2) && (2 >= rt_input.n2) && (1 == (9 - rt_input.n1))) || (!(2 >= rt_input.n1) && !(6 == rt_input.n1) && !(6 == rt_input.n2) && (2 >= rt_input.n2) && !(1 == (9 - rt_input.n1)) && (4 == rt_input.n1)));
	bool generatedPred = ((((3 > rt_input.n2) || (4 == rt_input.n2)) && (3 > rt_input.n1)) || (((rt_input.n2 > 7) && (3 > rt_input.n1)) || (((6 == rt_input.n2) && (3 > rt_input.n1)) || ((((9 > rt_input.n2) && (!((3 == rt_input.n2) || (rt_input.n1 == rt_input.n2)))) && (6 == rt_input.n1)) || (((6 == rt_input.n2) && (((!(6 == rt_input.n1)) && (rt_input.n1 > 2)) && ((!(9 == rt_input.n1)) && (rt_input.n1 > 3)))) || (((3 > rt_input.n2) && (8 == rt_input.n1)) || ((3 > rt_input.n2) && (4 == rt_input.n1))))))));

	assert(originPred == generatedPred);
}