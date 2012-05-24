#include<stdio.h>
#include<malloc.h>

int div(int one, int two) {
   return one / two;
}

int main() {
   int input;
   int op1;
   int op2;
   int op3;
   int res1;
   int res2;

   scanf("%d", &input);

   while (input != -1) {
      scanf("%d", &op1);
      scanf("%d", &op2);

      if (input == 0) {
         res1 = op1 / op2;
         res2 = div(op1, op2);
      } else { if (input == 1) {
         res1 = op2 / op1;
         res2 = div(op2, op1);
      } else { if (input == 2) {
         scanf("%d", &op3);

         res1 = (op1 + op2) / (op1 - op3);
         res2 = div((op1 + op2), (op1 - op3));
      } else { if (input == 3) {
         scanf("%d", &op3);

         res1 = (op3 - op2) / (op1 + op2);
         res2 = div((op3 - op2), (op1 + op2));
      }}}}

      printf("%d %d\n", res1, res2);
      scanf("%d", &input);
   }

   return 0;
}
