#include<stdio.h>
#include<malloc.h>
struct EV_Node
{
int EV_val;
struct EV_Node * EV_prev;
struct EV_Node * EV_next;
};
int EV_swapped;
int compare(struct EV_Node * EV_a,struct EV_Node * EV_b)
{
return (EV_a->EV_val-EV_b->EV_val);
}
void deathSort(struct EV_Node * EV_head)
{
int EV_swapped;
int EV_swap;
struct EV_Node * EV_currNode;
EV_swapped = 1;
while ((EV_swapped==1))
{
EV_swapped = 0;
EV_currNode = EV_head;
while ((EV_currNode->EV_next!=EV_head))
{
if ((compare(EV_currNode, EV_currNode->EV_next)>0))
{
EV_swap = EV_currNode->EV_val;
EV_currNode->EV_val = EV_currNode->EV_next->EV_val;
EV_currNode->EV_next->EV_val = EV_swap;
EV_swapped = 1;
}
EV_currNode = EV_currNode->EV_next;
}
}
}
void printEVILList(struct EV_Node * EV_head)
{
struct EV_Node * EV_currNode;
struct EV_Node * EV_toFree;
EV_currNode = EV_head->EV_next;
printf("%d\n",EV_head->EV_val);
free(EV_head);
while ((EV_currNode!=EV_head))
{
EV_toFree = EV_currNode;
printf("%d\n",EV_currNode->EV_val);
EV_currNode = EV_currNode->EV_next;
free(EV_toFree);
}
}
int main()
{
int EV_numNodes;
int EV_counter;
struct EV_Node * EV_currNode;
struct EV_Node * EV_head;
struct EV_Node * EV_previous;
EV_swapped = 666;
scanf("%d", &EV_numNodes);
if ((EV_numNodes<=0))
{
printf("%d\n",(-1));
return (-1);
}
EV_numNodes = (EV_numNodes*1000);
EV_counter = EV_numNodes;
EV_head = (struct EV_Node*)malloc(sizeof(struct EV_Node));
EV_head->EV_val = EV_counter;
EV_head->EV_prev = EV_head;
EV_head->EV_next = EV_head;
EV_counter = (EV_counter-1);
EV_previous = EV_head;
while ((EV_counter>0))
{
EV_currNode = (struct EV_Node*)malloc(sizeof(struct EV_Node));
EV_currNode->EV_val = EV_counter;
EV_currNode->EV_prev = EV_previous;
EV_currNode->EV_next = EV_head;
EV_previous->EV_next = EV_currNode;
EV_previous = EV_currNode;
EV_counter = (EV_counter-1);
}
deathSort(EV_head);
printEVILList(EV_head);
return 0;
}
