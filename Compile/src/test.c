#include<stdio.h>
#include"lex.h"
#include"taddr.h"
int yyparse();
int main() {
	setFile();
	printf(">\n");
	yyparse();
	if (!Complete()) {
		getchar();
		exit();
	}
	printf("print tree:\n");
	printTree(GetSave(), 0);
	printf("done\n");
	printAddr(GetSave());
	getchar();
	return 1;
}
