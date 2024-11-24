Write a C program that contains a string (char pointer) with a value \Hello World’.
The program should AND or and XOR each character in this string with 127 and
display the result.
PROGRAM:
#include<stdio.h>
#include<string.h>
#include<stdlib.h>
void main()
{
 char str[]="Hello World";
 char str1[11];
 char str2[11]=str[];
 char str3[20];
 int i,len;
 len = strlen(str);
 for(i=0;i<len;i++)
 {
 str1[i] = str[i]&127;
 printf("%c",str1[i]);
 }
 printf("\n");
 for(i=0;i<len;i++)
 {
 str3[i] = str2[i]^127;
 printf("%c",str3[i]);
 }
 printf("\n");
}
OUTPUT:
Hello World
Hello World
