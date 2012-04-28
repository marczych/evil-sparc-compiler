#include<stdio.h>
#include<malloc.h>
struct EV_IntList
{
int EV_head;
struct EV_IntList * EV_tail;
};
struct EV_IntList * getIntList()
{
struct EV_IntList * EV_list;
int EV_next;
EV_list = (struct EV_IntList*)malloc(sizeof(struct EV_IntList));
scanf("%d", &EV_next);
if ((EV_next==(-1)))
{
EV_list->EV_head = EV_next;
EV_list->EV_tail = NULL;
return EV_list;
}
else
{
EV_list->EV_head = EV_next;
EV_list->EV_tail = getIntList();
return EV_list;
}
}
int biggest(int EV_num1,int EV_num2)
{
if ((EV_num1>EV_num2))
{
return EV_num1;
}
else
{
return EV_num2;
}
}
int biggestInList(struct EV_IntList * EV_list)
{
int EV_big;
EV_big = EV_list->EV_head;
while ((EV_list->EV_tail!=NULL))
{
EV_big = biggest(EV_big, EV_list->EV_head);
EV_list = EV_list->EV_tail;
}
return EV_big;
}
int main()
{
struct EV_IntList * EV_list;
EV_list = getIntList();
printf("%d\n",biggestInList(EV_list));
return 0;
}
