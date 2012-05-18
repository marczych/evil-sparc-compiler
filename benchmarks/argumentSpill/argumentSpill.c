#include<stdio.h>
#include<malloc.h>

int bigFunction(int one, int two, int three, int four, int five, int six,
 int seven, int eight) {
   printf("%d\n", one);
   printf("%d\n", two);
   printf("%d\n", three);
   printf("%d\n", four);
   printf("%d\n", five);
   printf("%d\n", six);
   printf("%d\n", seven);
   printf("%d\n", eight);

   return one + two - three * four + five / six + seven - eight;
}

int main() {
   int one, two, three, four, five, six, seven, eight;

   scanf("%d", &one);
   scanf("%d", &two);
   scanf("%d", &three);
   scanf("%d", &four);
   scanf("%d", &five);
   scanf("%d", &six);
   scanf("%d", &seven);
   scanf("%d", &eight);

   printf("%d\n", bigFunction(one, two, three, four, five, six, seven, eight));

   return 0;
}
