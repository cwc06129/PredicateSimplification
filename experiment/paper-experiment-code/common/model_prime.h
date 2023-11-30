#ifndef _ENUM_DEFINE
#define _ENUM_DEFINE
#endif
typedef struct {
} STATE;

typedef struct {
    int number;
} INPUT;

typedef struct {
    int result;
} OUTPUT;

extern STATE rt_state;
extern INPUT rt_input;
extern OUTPUT rt_output;
extern void model_initialize(void);
extern void model_step(void);
