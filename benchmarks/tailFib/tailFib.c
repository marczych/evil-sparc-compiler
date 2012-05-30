#include<stdio.h>
#include<malloc.h>
int computeFib(int EV_input, int acc)
{
if ((EV_input==0))
{
return acc;
}
else
{
if ((EV_input<=2))
{
return 1 + acc;
}
else
{
return computeFib(EV_input-2, computeFib(EV_input-1, acc));
}
}
}
int main()
{
int EV_input;
scanf("%d", &EV_input);
printf("%d\n",computeFib(EV_input, 0));
return 0;
}
