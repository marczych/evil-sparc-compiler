#include<stdio.h>
#include<malloc.h>
struct EV_linkedNums
{
int EV_num;
struct EV_linkedNums * EV_next;
};
struct EV_linkedNums * getRands(int EV_seed,int EV_num)
{
int EV_cur;
int EV_prev;
struct EV_linkedNums * EV_curNode;
struct EV_linkedNums * EV_prevNode;
EV_cur = (EV_seed*EV_seed);
EV_prevNode = (struct EV_linkedNums*)malloc(sizeof(struct EV_linkedNums));
EV_prevNode->EV_num = EV_cur;
EV_prevNode->EV_next = NULL;
EV_num = (EV_num-1);
EV_prev = EV_cur;
while ((EV_num>0))
{
EV_cur = ((((EV_prev*EV_prev)/EV_seed)*(EV_seed/2))+1);
EV_curNode = (struct EV_linkedNums*)malloc(sizeof(struct EV_linkedNums));
EV_curNode->EV_num = EV_cur;
EV_curNode->EV_next = EV_prevNode;
EV_prevNode = EV_curNode;
EV_num = (EV_num-1);
EV_prev = EV_cur;
}
return EV_curNode;
}
int calcMean(struct EV_linkedNums * EV_nums)
{
int EV_sum;
int EV_num;
int EV_mean;
EV_sum = 0;
EV_num = 0;
EV_mean = 0;
while ((EV_nums!=NULL))
{
EV_num = (EV_num+1);
EV_sum = (EV_sum+EV_nums->EV_num);
EV_nums = EV_nums->EV_next;
}
if ((EV_num!=0))
{
EV_mean = (EV_sum/EV_num);
}
return EV_mean;
}
int approxSqrt(int EV_num)
{
int EV_guess;
int EV_result;
int EV_prev;
EV_guess = 1;
EV_prev = EV_guess;
EV_result = 0;
while ((EV_result<EV_num))
{
EV_result = (EV_guess*EV_guess);
EV_prev = EV_guess;
EV_guess = (EV_guess+1);
}
return EV_prev;
}
void approxSqrtAll(struct EV_linkedNums * EV_nums)
{
while ((EV_nums!=NULL))
{
printf("%d\n",approxSqrt(EV_nums->EV_num));
EV_nums = EV_nums->EV_next;
}
}
void range(struct EV_linkedNums * EV_nums)
{
int EV_min;
int EV_max;
int EV_first;
EV_min = 0;
EV_max = 0;
EV_first = 1;
while ((EV_nums!=NULL))
{
if (EV_first)
{
EV_min = EV_nums->EV_num;
EV_max = EV_nums->EV_num;
EV_first = 0;
}
else
{
if ((EV_nums->EV_num<EV_min))
{
EV_min = EV_nums->EV_num;
}
else
{
if ((EV_nums->EV_num>EV_max))
{
EV_max = EV_nums->EV_num;
}
}
}
EV_nums = EV_nums->EV_next;
}
printf("%d\n",EV_min);
printf("%d\n",EV_max);
}
int main()
{
int EV_seed;
int EV_num;
int EV_mean;
struct EV_linkedNums * EV_nums;
scanf("%d", &EV_seed);
scanf("%d", &EV_num);
EV_nums = getRands(EV_seed, EV_num);
EV_mean = calcMean(EV_nums);
printf("%d\n",EV_mean);
range(EV_nums);
approxSqrtAll(EV_nums);
return 0;
}
