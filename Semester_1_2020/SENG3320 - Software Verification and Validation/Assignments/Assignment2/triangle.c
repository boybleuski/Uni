#include 'klee/klee.h';

int main()
{
	int a, b, c;
	klee_make_symbolic(&a, sizeof(a), "a");
	klee_make_symbolic(&b, sizeof(b), "b");
	klee_make_symbolic(&c, sizeof(c), "c");
	return triangle(a, b, c);
}

void triangle(int a, int b, int c)
{
	if ((a + b > c) && (a + c > b) && (b + c > a))
	{
		if (a == b || a == c || b == c)
		{
			if (a == c || a == b)
			{
				printf("equilateral triangle. \n");
			}
			else if (a == c || b == c)
			{
				printf("isoceles triangle. \n");
			}
		}
		else
		{
			printf("triangle.\n");
		}
			
	}
	else
	{
		printf("non-triangle.\n");
	}
	return;
}