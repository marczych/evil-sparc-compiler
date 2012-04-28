#include<stdio.h>
#include<malloc.h>
int sum(int EV_a,int EV_b)
{
return (EV_a+EV_b);
}
int fact(int EV_n)
{
int EV_t;
if (((EV_n==1)||(EV_n==0)))
{
return 1;
}
if ((EV_n<=1))
{
return fact(((-1)*EV_n));
}
EV_t = (EV_n*fact((EV_n-1)));
return EV_t;
}
int main()
{
int EV_num1;
int EV_num2;
int EV_flag;
EV_flag = 0;
while ((EV_flag!=(-1)))
{
scanf("%d", &EV_num1);
scanf("%d", &EV_num2);
EV_num1 = fact(EV_num1);
EV_num2 = fact(EV_num2);
printf("%d\n",sum(EV_num1, EV_num2));
scanf("%d", &EV_flag);
}
return 0;
}
