#include<stdio.h>
#include<malloc.h>
int mod(int EV_a,int EV_b)
{
return (EV_a-((EV_a/EV_b)*EV_b));
}
void hailstone(int EV_n)
{
while (1)
{
printf("%d ",EV_n);
if ((mod(EV_n, 2)==1))
{
EV_n = ((3*EV_n)+1);
}
else
{
EV_n = (EV_n/2);
}
if ((EV_n<=1))
{
printf("%d\n",EV_n);
return ;
}
}
}
int main()
{
int EV_num;
scanf("%d", &EV_num);
hailstone(EV_num);
return 0;
}
