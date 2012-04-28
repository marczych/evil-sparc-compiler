#include<stdio.h>
#include<malloc.h>
struct EV_gameBoard
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
};
void cleanBoard(struct EV_gameBoard * EV_board)
{
EV_board->EV_a = 0;
EV_board->EV_b = 0;
EV_board->EV_c = 0;
EV_board->EV_d = 0;
EV_board->EV_e = 0;
EV_board->EV_f = 0;
EV_board->EV_g = 0;
EV_board->EV_h = 0;
EV_board->EV_i = 0;
}
void printBoard(struct EV_gameBoard * EV_board)
{
printf("%d ",EV_board->EV_a);
printf("%d ",EV_board->EV_b);
printf("%d\n",EV_board->EV_c);
printf("%d ",EV_board->EV_d);
printf("%d ",EV_board->EV_e);
printf("%d\n",EV_board->EV_f);
printf("%d ",EV_board->EV_g);
printf("%d ",EV_board->EV_h);
printf("%d\n",EV_board->EV_i);
}
void printMoveBoard()
{
printf("%d\n",123);
printf("%d\n",456);
printf("%d\n",789);
}
void placePiece(struct EV_gameBoard * EV_board,int EV_turn,int EV_placement)
{
if ((EV_placement==1))
{
EV_board->EV_a = EV_turn;
}
else
{
if ((EV_placement==2))
{
EV_board->EV_b = EV_turn;
}
else
{
if ((EV_placement==3))
{
EV_board->EV_c = EV_turn;
}
else
{
if ((EV_placement==4))
{
EV_board->EV_d = EV_turn;
}
else
{
if ((EV_placement==5))
{
EV_board->EV_e = EV_turn;
}
else
{
if ((EV_placement==6))
{
EV_board->EV_f = EV_turn;
}
else
{
if ((EV_placement==7))
{
EV_board->EV_g = EV_turn;
}
else
{
if ((EV_placement==8))
{
EV_board->EV_h = EV_turn;
}
else
{
if ((EV_placement==9))
{
EV_board->EV_i = EV_turn;
}
}
}
}
}
}
}
}
}
}
int checkWinner(struct EV_gameBoard * EV_board)
{
if ((EV_board->EV_a==1))
{
if ((EV_board->EV_b==1))
{
if ((EV_board->EV_c==1))
{
return 0;
}
}
}
if ((EV_board->EV_a==2))
{
if ((EV_board->EV_b==2))
{
if ((EV_board->EV_c==2))
{
return 1;
}
}
}
if ((EV_board->EV_d==1))
{
if ((EV_board->EV_e==1))
{
if ((EV_board->EV_f==1))
{
return 0;
}
}
}
if ((EV_board->EV_d==2))
{
if ((EV_board->EV_e==2))
{
if ((EV_board->EV_f==2))
{
return 1;
}
}
}
if ((EV_board->EV_g==1))
{
if ((EV_board->EV_h==1))
{
if ((EV_board->EV_i==1))
{
return 0;
}
}
}
if ((EV_board->EV_g==2))
{
if ((EV_board->EV_h==2))
{
if ((EV_board->EV_i==2))
{
return 1;
}
}
}
if ((EV_board->EV_a==1))
{
if ((EV_board->EV_d==1))
{
if ((EV_board->EV_g==1))
{
return 0;
}
}
}
if ((EV_board->EV_a==2))
{
if ((EV_board->EV_d==2))
{
if ((EV_board->EV_g==2))
{
return 1;
}
}
}
if ((EV_board->EV_b==1))
{
if ((EV_board->EV_e==1))
{
if ((EV_board->EV_h==1))
{
return 0;
}
}
}
if ((EV_board->EV_b==2))
{
if ((EV_board->EV_e==2))
{
if ((EV_board->EV_h==2))
{
return 1;
}
}
}
if ((EV_board->EV_c==1))
{
if ((EV_board->EV_f==1))
{
if ((EV_board->EV_i==1))
{
return 0;
}
}
}
if ((EV_board->EV_c==2))
{
if ((EV_board->EV_f==2))
{
if ((EV_board->EV_i==2))
{
return 1;
}
}
}
return (-1);
}
int main()
{
int EV_turn;
int EV_space1;
int EV_space2;
int EV_winner;
int EV_i;
struct EV_gameBoard * EV_board;
EV_i = 0;
EV_turn = 0;
EV_space1 = 0;
EV_space2 = 0;
EV_winner = (-1);
EV_board = (struct EV_gameBoard*)malloc(sizeof(struct EV_gameBoard));
cleanBoard(EV_board);
while (((EV_winner<0)&&(EV_i!=8)))
{
printBoard(EV_board);
if ((EV_turn==0))
{
EV_turn = (EV_turn+1);
scanf("%d", &EV_space1);
placePiece(EV_board, 1, EV_space1);
}
else
{
EV_turn = (EV_turn-1);
scanf("%d", &EV_space2);
placePiece(EV_board, 2, EV_space2);
}
EV_winner = checkWinner(EV_board);
EV_i = (EV_i+1);
}
printf("%d\n",(EV_winner+1));
return 0;
}
