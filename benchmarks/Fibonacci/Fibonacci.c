#include<stdio.h>
#include<malloc.h>
int computeFib(int EV_input)
{
if ((EV_input==0))
{
return 0;
}
else
{
if ((EV_input<=2))
{
return 1;
}
else
{
return (computeFib((EV_input-1))+computeFib((EV_input-2)));
}
}
}
int main()
{
int EV_input;
scanf("%d", &EV_input);
printf("%d\n",computeFib(EV_input));
return 0;
}
