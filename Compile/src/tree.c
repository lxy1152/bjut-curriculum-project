# include<stdio.h>
# include<stdlib.h>
# include<stdarg.h>
#include<string.h>
#include"tree.h"
struct TreeNode* NewNode(char *name, int LineNo) {
	struct TreeNode *Node = (struct TreeNode*)malloc(sizeof(struct TreeNode));
	strcpy(Node->name, name);
	Node->line = LineNo;
	Node->child = NULL;
	Node->brother = NULL;
	return Node;
}
void printTree(struct TreeNode* node,int level)
{
	if (node == NULL) {
		return;
	}
    else 
    {
        for(int i=0; i<level; i++)				
            printf("-");
        printf("%s (%d)",node->name,node->line);
        printf("\n");
        printTree(node->child,level+4);
        printTree(node->brother,level);
    }
}

