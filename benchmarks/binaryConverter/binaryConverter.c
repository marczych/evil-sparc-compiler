#include<stdio.h>
#include<malloc.h>
int wait(int EV_waitTime)
{
while ((EV_waitTime>0))
{
EV_waitTime = (EV_waitTime-1);
}
return 0;
}
int power(int EV_base,int EV_exponent)
{
int EV_product;
EV_product = 1;
while ((EV_exponent>0))
{
EV_product = (EV_product*EV_base);
EV_exponent = (EV_exponent-1);
}
return EV_product;
}
int recursiveDecimalSum(int EV_binaryNum,int EV_decimalSum,int EV_recursiveDepth)
{
int EV_tempNum;
int EV_base;
int EV_remainder;
if ((EV_binaryNum>0))
{
EV_base = 2;
EV_tempNum = (EV_binaryNum/10);
EV_tempNum = (EV_tempNum*10);
EV_tempNum = (EV_binaryNum-EV_tempNum);
if ((EV_tempNum==1))
{
EV_decimalSum = (EV_decimalSum+power(EV_base, EV_recursiveDepth));
}
return recursiveDecimalSum((EV_binaryNum/10), EV_decimalSum, (EV_recursiveDepth+1));
}
return EV_decimalSum;
}
int convertToDecimal(int EV_binaryNum)
{
int EV_recursiveDepth;
int EV_decimalSum;
EV_recursiveDepth = 0;
EV_decimalSum = 0;
return recursiveDecimalSum(EV_binaryNum, EV_decimalSum, EV_recursiveDepth);
}
int main()
{
int EV_number;
int EV_waitTime;
scanf("%d", &EV_number);
EV_number = convertToDecimal(EV_number);
EV_waitTime = (EV_number*EV_number);
while ((EV_waitTime>0))
{
wait(EV_waitTime);
EV_waitTime = (EV_waitTime-1);
}
printf("%d\n",EV_number);
return 0;
}
