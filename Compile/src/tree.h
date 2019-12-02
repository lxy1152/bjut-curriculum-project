#ifndef TREE_H
#define TREE_H
#include"tree.h"

struct TreeNode{
	char name[20];
	int line;
	char code[20];
	struct TreeNode *child;
	struct TreeNode *brother;
};
void printTree(struct TreeNode*node ,int level);
struct TreeNode* NewNode(char *name, int LineNo);
#endif
