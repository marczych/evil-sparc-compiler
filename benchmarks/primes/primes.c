#include<stdio.h>
#include<malloc.h>
int isqrt(int EV_a)
{
int EV_square;
int EV_delta;
EV_square = 1;
EV_delta = 3;
while ((EV_square<=EV_a))
{
EV_square = (EV_square+EV_delta);
EV_delta = (EV_delta+2);
}
return ((EV_delta/2)-1);
}
int prime(int EV_a)
{
int EV_max;
int EV_divisor;
int EV_remainder;
if ((EV_a<2))
{
return 0;
}
else
{
EV_max = isqrt(EV_a);
EV_divisor = 2;
while ((EV_divisor<=EV_max))
{
EV_remainder = (EV_a-((EV_a/EV_divisor)*EV_divisor));
if ((EV_remainder==0))
{
return 0;
}
EV_divisor = (EV_divisor+1);
}
return 1;
}
}
int main()
{
int EV_limit;
int EV_a;
scanf("%d", &EV_limit);
EV_a = 0;
while ((EV_a<=EV_limit))
{
if (prime(EV_a))
{
printf("%d\n",EV_a);
}
EV_a = (EV_a+1);
}
return 0;
}
