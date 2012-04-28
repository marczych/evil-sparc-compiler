#include<stdio.h>
#include<malloc.h>
int function(int EV_n)
{
int EV_i;
int EV_j;
if ((EV_n<=0))
{
return 0;
}
EV_i = 0;
while ((EV_i<(EV_n*EV_n)))
{
EV_j = (EV_i+EV_n);
printf("%d ",EV_j);
EV_i = (EV_i+1);
}
return function((EV_n-1));
}
int main()
{
int EV_num;
scanf("%d", &EV_num);
function(EV_num);
printf("%d\n",0);
return 0;
}
