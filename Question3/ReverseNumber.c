#include <stdio.h>
 
int main()
{
   int numberToReverse=0;
   int reversedNumber = 0;
   //Display message on screen
   
   printf("Please type in a number to reverse : ");
   //Read input from command line 
   scanf("%d", &numberToReverse);
 
   while(numberToReverse != 0)
   {
       //Multiply the number by 10
      reversedNumber = reversedNumber*10;
      //Add resultant to it's remainder
      reversedNumber = reversedNumber + numberToReverse%10;
      //Divide the resultant by 10 to switch number one digit at a time
      numberToReverse= numberToReverse/10;
   }
    printf("\n\nThe computed reverse of the number is : %d\n", reversedNumber);
 
 return 0;
}