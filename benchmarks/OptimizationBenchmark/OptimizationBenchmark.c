#include<stdio.h>
#include<malloc.h>
int EV_global1;
int EV_global2;
int EV_global3;
int constantFolding()
{
int EV_a;
EV_a = ((((((((((8*9)/4)+2)-(5*8))+9)-12)+6)-9)-18)+(((23*3)/23)*90));
return EV_a;
}
int constantPropagation()
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
int EV_e;
int EV_f;
int EV_g;
int EV_h;
int EV_i;
int EV_j;
int EV_k;
int EV_l;
int EV_m;
int EV_n;
int EV_o;
int EV_p;
int EV_q;
int EV_r;
int EV_s;
int EV_t;
int EV_u;
int EV_v;
int EV_w;
int EV_x;
int EV_y;
int EV_z;
EV_a = 4;
EV_b = 7;
EV_c = 8;
EV_d = 5;
EV_e = 11;
EV_f = 21;
EV_g = (EV_a+EV_b);
EV_h = (EV_c+EV_d);
EV_i = (EV_e+EV_f);
EV_j = (EV_g+EV_h);
EV_k = (EV_i*EV_j);
EV_l = ((EV_e+(EV_h*EV_i))-EV_k);
EV_m = ((EV_h-(EV_i*EV_j))+(EV_k/EV_l));
EV_n = (((((EV_e+EV_f)+EV_g)+EV_h)+EV_i)-EV_j);
EV_o = ((((EV_n-EV_m)+EV_h)-EV_a)-EV_b);
EV_p = (((EV_k+EV_l)-EV_g)-EV_h);
EV_q = (((EV_b-EV_a)*EV_d)-EV_i);
EV_r = (((EV_l*EV_c)*EV_d)+EV_o);
EV_s = ((((EV_b*EV_a)*EV_c)/EV_e)-EV_o);
EV_t = (((EV_i+EV_k)+EV_c)-EV_p);
EV_u = ((EV_n+EV_o)-(EV_f*EV_a));
EV_v = (((EV_a*EV_b)-EV_k)-EV_l);
EV_w = ((EV_v-EV_s)-(EV_r*EV_d));
EV_x = (((EV_o-EV_w)-EV_v)-EV_n);
EV_y = (((EV_p*EV_x)*EV_t)-EV_w);
EV_z = ((EV_w-(EV_x*EV_y))+EV_k);
return EV_z;
}
int deadCodeElimination()
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
int EV_e;
EV_a = 4;
EV_a = 5;
EV_a = 7;
EV_a = 8;
EV_b = 6;
EV_b = 9;
EV_b = 12;
EV_b = 8;
EV_c = 10;
EV_c = 13;
EV_c = 9;
EV_d = 45;
EV_d = 12;
EV_d = 3;
EV_e = 23;
EV_e = 10;
EV_global1 = 11;
EV_global1 = 5;
EV_global1 = 9;
return ((((EV_a+EV_b)+EV_c)+EV_d)+EV_e);
}
int sum(int EV_number)
{
int EV_total;
EV_total = 0;
while ((EV_number>0))
{
EV_total = (EV_total+EV_number);
EV_number = (EV_number-1);
}
return EV_total;
}
int doesntModifyGlobals()
{
int EV_a;
int EV_b;
EV_a = 1;
EV_b = 2;
return (EV_a+EV_b);
}
int interProceduralOptimization()
{
int EV_a;
EV_global1 = 1;
EV_global2 = 0;
EV_global3 = 0;
EV_a = sum(100);
if ((EV_global1==1))
{
EV_a = sum(10000);
}
else
{
if ((EV_global2==2))
{
EV_a = sum(20000);
}
if ((EV_global3==3))
{
EV_a = sum(30000);
}
}
return EV_a;
}
int commonSubexpressionElimination()
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
int EV_e;
int EV_f;
int EV_g;
int EV_h;
int EV_i;
int EV_j;
int EV_k;
int EV_l;
int EV_m;
int EV_n;
int EV_o;
int EV_p;
int EV_q;
int EV_r;
int EV_s;
int EV_t;
int EV_u;
int EV_v;
int EV_w;
int EV_x;
int EV_y;
int EV_z;
EV_a = 11;
EV_b = 22;
EV_c = 33;
EV_d = 44;
EV_e = 55;
EV_f = 66;
EV_g = 77;
EV_h = (EV_a*EV_b);
EV_i = (EV_c/EV_d);
EV_j = (EV_e*EV_f);
EV_k = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_l = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_m = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_n = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_o = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_p = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_q = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_r = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_s = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_t = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_u = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_v = ((((EV_b*EV_a)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_w = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_f*EV_e))+EV_g);
EV_x = (((EV_g+(EV_a*EV_b))+(EV_c/EV_d))-(EV_e*EV_f));
EV_y = ((((EV_a*EV_b)+(EV_c/EV_d))-(EV_e*EV_f))+EV_g);
EV_z = ((((EV_c/EV_d)+(EV_a*EV_b))-(EV_e*EV_f))+EV_g);
return (((((((((((((((((((((((((EV_a+EV_b)+EV_c)+EV_d)+EV_e)+EV_f)+EV_g)+EV_h)+EV_i)+EV_j)+EV_k)+EV_l)+EV_m)+EV_n)+EV_o)+EV_p)+EV_q)+EV_r)+EV_s)+EV_t)+EV_u)+EV_v)+EV_w)+EV_x)+EV_y)+EV_z);
}
int hoisting()
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
int EV_e;
int EV_f;
int EV_g;
int EV_h;
int EV_i;
EV_a = 1;
EV_b = 2;
EV_c = 3;
EV_d = 4;
EV_i = 0;
while ((EV_i<1000000))
{
EV_e = 5;
EV_g = ((EV_a+EV_b)+EV_c);
EV_h = ((EV_c+EV_d)+EV_g);
EV_i = (EV_i+1);
}
return EV_b;
}
int doubleIf()
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
EV_a = 1;
EV_b = 2;
EV_c = 3;
if ((EV_a==1))
{
EV_b = 20;
if ((EV_a==1))
{
EV_b = 200;
EV_c = 300;
}
else
{
EV_a = 1;
EV_b = 2;
EV_c = 3;
}
EV_d = 50;
}
return EV_d;
}
int integerDivide()
{
int EV_a;
EV_a = 3000;
EV_a = (EV_a/2);
EV_a = (EV_a*4);
EV_a = (EV_a/8);
EV_a = (EV_a/16);
EV_a = (EV_a*32);
EV_a = (EV_a/64);
EV_a = (EV_a*128);
EV_a = (EV_a/4);
return EV_a;
}
int association()
{
int EV_a;
EV_a = 10;
EV_a = (EV_a*2);
EV_a = (EV_a/2);
EV_a = (3*EV_a);
EV_a = (EV_a/3);
EV_a = (EV_a*4);
EV_a = (EV_a/4);
EV_a = (EV_a+4);
EV_a = (EV_a-4);
EV_a = (EV_a*50);
EV_a = (EV_a/50);
return EV_a;
}
int tailRecursionHelper(int EV_value,int EV_sum)
{
if ((EV_value==0))
{
return EV_sum;
}
else
{
return tailRecursionHelper((EV_value-1), (EV_sum+EV_value));
}
}
int tailRecursion(int EV_value)
{
return tailRecursionHelper(EV_value, 0);
}
int unswitching()
{
int EV_a;
int EV_b;
EV_a = 1;
EV_b = 2;
while ((EV_a<1000000))
{
if ((EV_b==2))
{
EV_a = (EV_a+1);
}
else
{
EV_a = (EV_a+2);
}
}
return EV_a;
}
int randomCalculation(int EV_number)
{
int EV_a;
int EV_b;
int EV_c;
int EV_d;
int EV_e;
int EV_i;
int EV_sum;
EV_i = 0;
EV_sum = 0;
while ((EV_i<EV_number))
{
EV_a = 4;
EV_b = 7;
EV_c = 8;
EV_d = (EV_a+EV_b);
EV_e = (EV_d+EV_c);
EV_sum = (EV_sum+EV_e);
EV_i = (EV_i*2);
EV_i = (EV_i/2);
EV_i = (3*EV_i);
EV_i = (EV_i/3);
EV_i = (EV_i*4);
EV_i = (EV_i/4);
EV_i = (EV_i+1);
}
return EV_sum;
}
int iterativeFibonacci(int EV_number)
{
int EV_previous;
int EV_result;
int EV_count;
int EV_i;
int EV_sum;
EV_previous = (-1);
EV_result = 1;
EV_i = 0;
while ((EV_i<EV_number))
{
EV_sum = (EV_result+EV_previous);
EV_previous = EV_result;
EV_result = EV_sum;
EV_i = (EV_i+1);
}
return EV_result;
}
int recursiveFibonacci(int EV_number)
{
if (((EV_number<=0)||(EV_number==1)))
{
return EV_number;
}
else
{
return (recursiveFibonacci((EV_number-1))+recursiveFibonacci((EV_number-2)));
}
}
int main()
{
int EV_input;
int EV_result;
int EV_i;
scanf("%d", &EV_input);
EV_i = 1;
while ((EV_i<EV_input))
{
EV_result = constantFolding();
printf("%d\n",EV_result);
EV_result = constantPropagation();
printf("%d\n",EV_result);
EV_result = deadCodeElimination();
printf("%d\n",EV_result);
EV_result = interProceduralOptimization();
printf("%d\n",EV_result);
EV_result = commonSubexpressionElimination();
printf("%d\n",EV_result);
EV_result = hoisting();
printf("%d\n",EV_result);
EV_result = doubleIf();
printf("%d\n",EV_result);
EV_result = integerDivide();
printf("%d\n",EV_result);
EV_result = association();
printf("%d\n",EV_result);
EV_result = tailRecursion((EV_input/1000));
printf("%d\n",EV_result);
EV_result = unswitching();
printf("%d\n",EV_result);
EV_result = randomCalculation(EV_input);
printf("%d\n",EV_result);
EV_result = iterativeFibonacci((EV_input/5));
printf("%d\n",EV_result);
EV_result = recursiveFibonacci((EV_input/1000));
printf("%d\n",EV_result);
EV_i = (EV_i+1);
}
printf("%d\n",9999);
return 0;
}
