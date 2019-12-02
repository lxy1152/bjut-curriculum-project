#ifndef TADDR_H
#define TADDR_H
#define CODE_SIZE 2000
#define PLACE_SIZE 500
#include"tree.h"
struct C{
	int C_False;
	int C_True;
	char code[CODE_SIZE];
};
struct E{
	char code[CODE_SIZE];
	char place[PLACE_SIZE];
};
struct F{
	char code[CODE_SIZE];
	char place[PLACE_SIZE];
};
struct L{
	char code[CODE_SIZE];
};
struct P{
	char code[CODE_SIZE];
};
struct S{
	int begin;
	int next;
	char code[CODE_SIZE];
};
struct T{
	char code[CODE_SIZE];
	char place[PLACE_SIZE];
};
struct T* T(struct TreeNode *node);
struct S* S(struct TreeNode *node);
struct E* E(struct TreeNode *node);
struct P* P(struct TreeNode *node);
struct F* F(struct TreeNode *node);
struct L* L(struct TreeNode *node);
struct C* C(struct TreeNode *node,struct S *s);
struct C* C_New(struct TreeNode *node,struct S *s);
int printAddr(struct TreeNode *node);
int newLabel();
int newTemp();
struct TreeNode *GetSave();
int Complete();
#endif
