#include<stdio.h>
#include<malloc.h>
int EV_GLOBAL;
int EV_count;
int fun2(int EV_x,int EV_y)
{
if ((EV_x==0))
{
return EV_y;
}
else
{
return fun2((EV_x-1), EV_y);
}
}
int fun1(int EV_x,int EV_y,int EV_z)
{
int EV_retVal;
EV_retVal = ((((5+6)-(EV_x*2))+(4/EV_y))+EV_z);
if ((EV_retVal>EV_y))
{
return fun2(EV_retVal, EV_x);
}
else
{
if (((5<6)&&(EV_retVal<=EV_y)))
{
return fun2(EV_retVal, EV_y);
}
}
return EV_retVal;
}
int main()
{
int EV_i;
EV_i = 0;
scanf("%d", &EV_i);
while ((EV_i<10000))
{
printf("%d\n",fun1(3, EV_i, 5));
EV_i = (EV_i+1);
}
return 0;
}
