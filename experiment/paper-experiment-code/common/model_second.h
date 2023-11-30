#ifndef _ENUM_DEFINE
#define _ENUM_DEFINE
#endif

typedef struct {
	int result;
} STATE;

typedef struct {
	int a;
	int b;
	int c;
} INPUT;

typedef struct {
} OUTPUT;

extern STATE rt_state;
extern INPUT rt_input;
extern OUTPUT rt_output;
extern void model_initialize(void);
extern void model_step(void);
