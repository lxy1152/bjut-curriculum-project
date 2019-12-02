%{
#define YYSTYPE struct TreeNode*
#include<stdio.h>   
#include"tree.h"
#include"lex.h"
#include"analys.tab.h"
struct TreeNode* save;
int complete=1;
%}

%token  IF THEN ELSE WHILE DO 
%token  ID INT8 INT10 INT16
%token  LT GT EQ ASSIGN SEM ADD SUB MUL DIV
%token  LPAREN RPAREN
%token  LBRACK RBRACK
%token  LBRACE RBRACE

%left THEN
%left LT GT EQ 
%left ADD SUB
%left MUL DIV
%left LPAREN RPAREN LBRACE RBRACE
%right ELSE
%right ASSIGN
%%
P:L P
 {
  $$=NewNode("P",$1->line);
  $$->child=$1;
  $1->brother=$2;
  save=$$;
  }

P:L
  {
  $$=NewNode("P",$1->line);
  $$->child=$1;
  save=$$;
  }

L:S SEM
 {
  $$=NewNode("L",$1->line);
  $$->child=$1;
  $2=NewNode("SEM",getLine());
  strcpy($2->code,";");
  $1->brother=$2;
 }

S:ID ASSIGN E 
  {
  $1=NewNode("ID",getLine());
  $2=NewNode("ASSIGN",getLine());
  $$=NewNode("S",$1->line);
  strcpy($2->code,"=");
  strcpy($1->code,getNameID());
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |IF C THEN S	
  {
  $1=NewNode("IF",$2->line);
  $3=NewNode("THEN",getLine());
  $$=NewNode("S",$1->line);
  strcpy($1->code,"if");
  strcpy($3->code,"then");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  $3->brother=$4;
  }
 |IF C THEN S ELSE S
  {
  $1=NewNode("IF",getLine());
  $3=NewNode("THEN",getLine());
  $5=NewNode("ELSE",getLine());
  $$=NewNode("S",$1->line);
  strcpy($1->code,"if");
  strcpy($3->code,"then");
  strcpy($5->code,"else");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  $3->brother=$4;
  $4->brother=$5;
  $5->brother=$6;
  }
 |WHILE C DO S
  {
  $1=NewNode("WHILE",getLine());
  $3=NewNode("DO",getLine());
  $$=NewNode("S",$1->line);
  strcpy($1->code,"while");
  strcpy($3->code,"do");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  $3->brother=$4;
  }
 |LBRACK P RBRACK 
  {
  $1=NewNode("LBRACK",getLine());
  $3=NewNode("RBRACK",getLine());
  $$=NewNode("S",$1->line);
  strcpy($1->code,"{");
  strcpy($3->code,"}");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }

C:E GT E
  {
  $$=NewNode("C",$1->line);
  $2=NewNode("GT",getLine());
  strcpy($2->code,">");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |E LT E
  {
  $$=NewNode("C",$1->line);
  $2=NewNode("LT",getLine());
  strcpy($2->code,"<");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |E ASSIGN E
  {
  $$=NewNode("C",$1->line);
  $2=NewNode("ASSIGN",getLine());
  strcpy($2->code,"=");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }

E:E ADD T
  {
  $$=NewNode("E",$1->line);
  $2=NewNode("ADD",getLine());
  strcpy($2->code,"+");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |E SUB T
  {
  $$=NewNode("E",$1->line);
  $2=NewNode("SUB",getLine());
  strcpy($2->code,"-");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |T
  {
  $$=NewNode("E",$1->line);
  $$->child=$1;
  }

T:F
  {
  $$=NewNode("T",$1->line);
  $$->child=$1;
  }
 |T MUL F
  {
  $$=NewNode("E",$1->line);
  $2=NewNode("MUL",getLine());
  strcpy($2->code,"*");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |T DIV F
  {
  $$=NewNode("E",$1->line);
  $2=NewNode("DIV",getLine());
  strcpy($2->code,"/");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }

F:LPAREN E RPAREN 
  {
  $1=NewNode("LPAREN",getLine());
  $3=NewNode("RPAREN",getLine());
  $$=NewNode("F",$1->line);
  strcpy($1->code,"(");
  strcpy($3->code,")");
  $$->child=$1;
  $1->brother=$2;
  $2->brother=$3;
  }
 |ID
  {
  $1=NewNode("ID",getLine());
  $$=NewNode("F",$1->line);
  strcpy($1->code,getName());
  $$->child=$1;
  }
 |INT8
  {
  $1=NewNode("INT8",getLine());
  $$=NewNode("F",$1->line);
  strcpy($1->code,getName());
  $$->child=$1;
  }
 |INT10
  {
  $1=NewNode("INT10",getLine());
  $$=NewNode("F",$1->line);
  strcpy($1->code,getName());
  $$->child=$1;
  }
 |INT16
  {
  $1=NewNode("INT16",getLine());
  $$=NewNode("F",$1->line);
  strcpy($1->code,getName());
  $$->child=$1;
  }
%%

yyerror(){
printf("Line in %d: %s is imcomplete!\n",getLine(),getName());
complete=0;
return 1;
}
int Complete(){
return complete;
}
struct TreeNode* GetSave(){
return save;
}

