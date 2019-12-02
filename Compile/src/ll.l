%{
#define YYSTYPE struct TreeNode*
#include<string.h>
#include<stdio.h>
#include"analys.tab.h"
#include"lex.h"
#include"tree.h"
char name[20];
char id[20];
char nameID[2][20];
static int LineNo;
FILE* filein;
%}
letter      [a-zA-Z]
hex         [0-9a-f]
digit_all   [0-9]
digit_ezero [1-9]
digit_oct   [0-7]
%%
"else"  {
return ELSE;
}

"if"    {
return IF;
}

"while" {
return WHILE;
}

"do"    {
return DO;
}

"then"  {
return THEN;
}

"+"     {
return ADD;
}

"-"     {
return SUB;
}

"*"     {
return MUL;
}

"/"     {
return DIV;
}

">"     {
return GT;
}

"<"     {
return LT;
}

"="     {
strcpy(nameID[1],"=");
return ASSIGN;
}

"("     {
return LPAREN;
}

")"     {
return RPAREN;
}

"{"     {
return LBRACE;
}

"}"     {
return RBRACE;
}

"\n"    {
LineNo++;
}

" "     {
}

";"     {
return SEM;
}

{letter}(({letter}|{digit_all})*)(((_|\.)({letter}|{digit_all})+))*       {
if(strcmp(nameID[1],"=")==0){
  strcpy(nameID[1]," ");
  strcpy(id,nameID[0]);
  strcpy(name,yytext);
}
else{
  strcpy(name,yytext);
  strcpy(nameID[0],yytext);
}
return ID;
}

(0[xX]){hex}{hex}*([ \f\n\r\t\v]|(\.{hex}{hex}+))*                        {
strcpy(nameID[1]," ");
strcpy(id,nameID[0]);
strcpy(name,yytext);
return INT16;
}

0(({digit_oct})+([ \f\n\r\t\v]|(\.{digit_oct}{digit_oct}+))*)             {
strcpy(nameID[1]," ");
strcpy(id,nameID[0]);
strcpy(name,yytext);
return INT8;
}

(({digit_ezero}{digit_all}*)|0)(\.{digit_all}{digit_all}*)*               {
strcpy(nameID[1]," ");
strcpy(id,nameID[0]);
strcpy(name,yytext);
return INT10;
}

%%
int yywrap(){
return 1;
}
/*int getToken(){
   static int FirstTime=1;
   int  type;
   if(FirstTime){
      filein=fopen("filepath","r");
      yyin=filein;
      FirstTime=0;
   }
   type=yylex();
   strcpy(name,yytext);
   printf("这是内容：%s  ", yytext);
   return type;
}
int main(int args,char ** argv){
	for(int i=0;i<25;i++)
	printf("这是属性：%s\n",name[getToken()]);
        getchar();
}*/
void setFile(){
  filein=fopen("C://Users//lxy//Desktop//test.txt","r");
  yyin=filein;
}
int getLine(){
  return LineNo;
}
char* getName(){
return name;
}
char* getNameID(){
return id;
}