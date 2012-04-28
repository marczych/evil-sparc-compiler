#include<stdio.h>
#include<malloc.h>
struct EV_node
{
int EV_data;
struct EV_node * EV_next;
};
struct EV_tnode
{
int EV_data;
struct EV_tnode * EV_left;
struct EV_tnode * EV_right;
};
struct EV_i
{
int EV_i;
};
struct EV_myCopy
{
int EV_b;
};
int EV_a;
int EV_b;
struct EV_i * EV_i;
struct EV_node * concatLists(struct EV_node * EV_first,struct EV_node * EV_second)
{
struct EV_node * EV_temp;
EV_temp = EV_first;
if ((EV_first==NULL))
{
return EV_second;
}
while ((EV_temp->EV_next!=NULL))
{
EV_temp = EV_temp->EV_next;
}
EV_temp->EV_next = EV_second;
return EV_first;
}
struct EV_node * add(struct EV_node * EV_list,int EV_toAdd)
{
struct EV_node * EV_newNode;
EV_newNode = (struct EV_node*)malloc(sizeof(struct EV_node));
EV_newNode->EV_data = EV_toAdd;
EV_newNode->EV_next = EV_list;
return EV_newNode;
}
int size(struct EV_node * EV_list)
{
if ((EV_list==NULL))
{
return 0;
}
return (1+size(EV_list->EV_next));
}
int get(struct EV_node * EV_list,int EV_index)
{
if ((EV_index==0))
{
return EV_list->EV_data;
}
return get(EV_list->EV_next, (EV_index-1));
}
struct EV_node * pop(struct EV_node * EV_list)
{
EV_list = EV_list->EV_next;
return EV_list;
}
void printList(struct EV_node * EV_list)
{
if ((EV_list!=NULL))
{
printf("%d\n",EV_list->EV_data);
printList(EV_list->EV_next);
}
}
void treeprint(struct EV_tnode * EV_root)
{
if ((EV_root!=NULL))
{
treeprint(EV_root->EV_left);
printf("%d\n",EV_root->EV_data);
treeprint(EV_root->EV_right);
}
}
void freeList(struct EV_node * EV_list)
{
if ((EV_list!=NULL))
{
freeList(EV_list->EV_next);
free(EV_list);
}
}
void freeTree(struct EV_tnode * EV_root)
{
if ((!(EV_root==NULL)))
{
freeTree(EV_root->EV_left);
freeTree(EV_root->EV_right);
free(EV_root);
}
}
struct EV_node * postOrder(struct EV_tnode * EV_root)
{
struct EV_node * EV_temp;
if ((EV_root!=NULL))
{
EV_temp = (struct EV_node*)malloc(sizeof(struct EV_node));
EV_temp->EV_data = EV_root->EV_data;
EV_temp->EV_next = NULL;
return concatLists(concatLists(postOrder(EV_root->EV_left), postOrder(EV_root->EV_right)), EV_temp);
}
return NULL;
}
struct EV_tnode * treeadd(struct EV_tnode * EV_root,int EV_toAdd)
{
struct EV_tnode * EV_temp;
if ((EV_root==NULL))
{
EV_temp = (struct EV_tnode*)malloc(sizeof(struct EV_tnode));
EV_temp->EV_data = EV_toAdd;
EV_temp->EV_left = NULL;
EV_temp->EV_right = NULL;
return EV_temp;
}
if ((EV_toAdd<EV_root->EV_data))
{
EV_root->EV_left = treeadd(EV_root->EV_left, EV_toAdd);
}
else
{
EV_root->EV_right = treeadd(EV_root->EV_right, EV_toAdd);
}
return EV_root;
}
struct EV_node * quickSort(struct EV_node * EV_list)
{
int EV_pivot;
int EV_i;
struct EV_node * EV_less;
struct EV_node * EV_greater;
struct EV_node * EV_temp;
EV_less = NULL;
EV_greater = NULL;
if ((size(EV_list)<=1))
{
return EV_list;
}
EV_pivot = ((get(EV_list, 0)+get(EV_list, (size(EV_list)-1)))/2);
EV_temp = EV_list;
EV_i = 0;
while ((EV_temp!=NULL))
{
if ((get(EV_list, EV_i)>EV_pivot))
{
EV_greater = add(EV_greater, get(EV_list, EV_i));
}
else
{
EV_less = add(EV_less, get(EV_list, EV_i));
}
EV_temp = EV_temp->EV_next;
EV_i = (EV_i+1);
}
freeList(EV_list);
return concatLists(quickSort(EV_less), quickSort(EV_greater));
}
struct EV_node * quickSortMain(struct EV_node * EV_list)
{
printList(EV_list);
printf("%d\n",(-999));
printList(EV_list);
printf("%d\n",(-999));
printList(EV_list);
printf("%d\n",(-999));
return NULL;
}
int treesearch(struct EV_tnode * EV_root,int EV_target)
{
printf("%d\n",(-1));
if ((EV_root!=NULL))
{
if ((EV_root->EV_data==EV_target))
{
return 1;
}
if ((treesearch(EV_root->EV_left, EV_target)==1))
{
return 1;
}
if ((treesearch(EV_root->EV_right, EV_target)==1))
{
return 1;
}
else
{
return 0;
}
}
return 0;
}
struct EV_node * inOrder(struct EV_tnode * EV_root)
{
struct EV_node * EV_temp;
if ((EV_root!=NULL))
{
EV_temp = (struct EV_node*)malloc(sizeof(struct EV_node));
EV_temp->EV_data = EV_root->EV_data;
EV_temp->EV_next = NULL;
return concatLists(inOrder(EV_root->EV_left), concatLists(EV_temp, inOrder(EV_root->EV_right)));
}
else
{
return NULL;
}
}
int bintreesearch(struct EV_tnode * EV_root,int EV_target)
{
printf("%d\n",(-1));
if ((EV_root!=NULL))
{
if ((EV_root->EV_data==EV_target))
{
return 1;
}
if ((EV_target<EV_root->EV_data))
{
return bintreesearch(EV_root->EV_left, EV_target);
}
else
{
return bintreesearch(EV_root->EV_right, EV_target);
}
}
return 0;
}
struct EV_tnode * buildTree(struct EV_node * EV_list)
{
int EV_i;
struct EV_tnode * EV_root;
EV_root = NULL;
EV_i = 0;
while ((EV_i<size(EV_list)))
{
EV_root = treeadd(EV_root, get(EV_list, EV_i));
EV_i = (EV_i+1);
}
return EV_root;
}
void treeMain(struct EV_node * EV_list)
{
struct EV_tnode * EV_root;
struct EV_node * EV_inList;
struct EV_node * EV_postList;
EV_root = buildTree(EV_list);
treeprint(EV_root);
printf("%d\n",(-999));
EV_inList = inOrder(EV_root);
printList(EV_inList);
printf("%d\n",(-999));
freeList(EV_inList);
EV_postList = postOrder(EV_root);
printList(EV_postList);
printf("%d\n",(-999));
freeList(EV_postList);
printf("%d\n",treesearch(EV_root, 0));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, 10));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, (-2)));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, 2));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, 3));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, 9));
printf("%d\n",(-999));
printf("%d\n",treesearch(EV_root, 1));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 0));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 10));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, (-2)));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 2));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 3));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 9));
printf("%d\n",(-999));
printf("%d\n",bintreesearch(EV_root, 1));
printf("%d\n",(-999));
freeTree(EV_root);
}
struct EV_node * myCopy(struct EV_node * EV_src)
{
if ((EV_src==NULL))
{
return NULL;
}
return concatLists(add(NULL, EV_src->EV_data), myCopy(EV_src->EV_next));
}
int main()
{
int EV_i;
int EV_element;
struct EV_node * EV_myList;
struct EV_node * EV_copyList1;
struct EV_node * EV_copyList2;
struct EV_node * EV_sortedList;
EV_myList = NULL;
EV_i = 0;
while ((EV_i<10))
{
scanf("%d", &EV_element);
EV_myList = add(EV_myList, EV_element);
EV_copyList1 = myCopy(EV_myList);
EV_copyList2 = myCopy(EV_myList);
EV_sortedList = quickSortMain(EV_copyList1);
freeList(EV_sortedList);
treeMain(EV_copyList2);
EV_i = (EV_i+1);
}
freeList(EV_myList);
freeList(EV_copyList1);
freeList(EV_copyList2);
return 0;
}
