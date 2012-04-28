#include<stdio.h>
#include<malloc.h>
struct EV_intList
{
int EV_data;
struct EV_intList * EV_rest;
};
int EV_intList;
int length(struct EV_intList * EV_list)
{
if ((EV_list==NULL))
{
return 0;
}
return (1+length(EV_list->EV_rest));
}
struct EV_intList * addToFront(struct EV_intList * EV_list,int EV_element)
{
struct EV_intList * EV_front;
if ((EV_list==NULL))
{
EV_list = (struct EV_intList*)malloc(sizeof(struct EV_intList));
EV_list->EV_data = EV_element;
return EV_list;
}
EV_front = (struct EV_intList*)malloc(sizeof(struct EV_intList));
EV_front->EV_data = EV_element;
EV_front->EV_rest = EV_list;
return EV_front;
}
struct EV_intList * deleteFirst(struct EV_intList * EV_list)
{
struct EV_intList * EV_first;
if ((EV_list==NULL))
{
return NULL;
}
EV_first = EV_list;
EV_list = EV_list->EV_rest;
free(EV_first);
return EV_list;
}
int main()
{
struct EV_intList * EV_list;
int EV_sum;
scanf("%d", &EV_intList);
EV_sum = 0;
EV_list = NULL;
while ((EV_intList>0))
{
EV_list = addToFront(EV_list, EV_intList);
printf("%d ",EV_list->EV_data);
EV_intList = (EV_intList-1);
}
printf("%d ",length(EV_list));
while ((length(EV_list)>0))
{
EV_sum = (EV_sum+EV_list->EV_data);
printf("%d ",length(EV_list));
EV_list = deleteFirst(EV_list);
}
printf("%d\n",EV_sum);
return 0;
}
