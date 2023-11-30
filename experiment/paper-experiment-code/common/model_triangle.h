#ifndef _ENUM_DEFINE
typedef enum _Triangle_type {Indecisive, Equilateral, Isoscele, Non_triangle, Scalene} Triangle_type;
#define _ENUM_DEFINE
#endif

typedef struct {
	Triangle_type result;
} STATE;

typedef struct {
	int ta;
	int tb;
	int tc;
} INPUT;

typedef struct {
} OUTPUT;

extern STATE rt_state;
extern INPUT rt_input;
extern OUTPUT rt_output;
extern void model_initialize(void);
extern void model_step(void);
