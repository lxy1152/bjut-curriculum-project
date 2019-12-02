#include"tree.h"
#include"taddr.h"
#include<stdio.h>
#include<string.h>
#define ENTRY 20
int entry;
int getEntry() {
	static int entry = 20;
	return entry++;
}

struct T* T(struct TreeNode *node) {
	struct T *t = (struct T*)malloc(sizeof(struct T));
	if (node->child->brother == 0) {
		struct F *f ;
		f=F(node->child);
		strcpy(t->place, f->place);
		strcpy(t->code, f->code);
	}
	else {
		struct F *f1 ;
		struct T *t1 ;
		t1=T(node->child);
		f1=F(node->child->brother->brother);
		sprintf(t->place, "t%d", newTemp());
		sprintf(t->code, "%s%s\n%s:=%s%s%s   ", t1->code, f1->code, t->place, t1->place,node->child->brother->code, f1->place);
		
	}
	return t;
}
struct E* E(struct TreeNode *node) {
	struct E *e = (struct E*)malloc(sizeof(struct E));
	if (node->child->brother == 0) {
		struct T *t;
		t=T(node->child);
		strcpy(e->place, t->place);
		strcpy(e->code, t->code);
	}
	else {
		struct E *e1 ;
		struct T *t1 ;
		e1=E(node->child);
		t1=T(node->child->brother->brother);
		sprintf(e->place, "t%d", newTemp());
		sprintf(e->code, "%s%s\n%s:=%s%s%s", e1->code, t1->code, e->place, e1->place, node->child->brother->code, t1->place);
	}
	return e;
}
struct F* F(struct TreeNode *node) {
	struct F *f = (struct F*)malloc(sizeof(struct F));
	if (strcmp(node->child->name, "LPAREN") == 0) {
		struct E *e ;
		e=E(node->child->brother);
		strcpy(f->place, e->place);
		strcpy(f->code, e->code);
	}
	else {
		strcpy(f->place, node->child->code);
		strcpy(f->code, " ");
	}
	return f;
}

struct C *C_New(struct TreeNode *node, struct S *s) {
	struct C *c = (struct C*)malloc(sizeof(struct C));
	c->C_True = newLabel();
	c->C_False = newLabel();
	if (strcmp(node->child->name, "E") == 0) {
		struct E *e1;
		struct E *e2;
		e1 = E(node->child);
		e2 = E(node->child->brother->brother);
		sprintf(c->code, "%s%s\nif %s%s%sgoto L%d\ngoto L%d\n", e1->code, e2->code, e1->place, node->child->brother->code, e2->place, c->C_True, c->C_False);
	}
	return c;
}
struct C *C(struct TreeNode *node,struct S *s) {
	struct C *c = (struct C*)malloc(sizeof(struct C));
	c->C_True = newLabel();
	c->C_False = s->next;
	if (strcmp(node->child->name, "E") == 0) {
		struct E *e1 ;
		struct E *e2 ;
		e1=E(node->child);
		e2=E(node->child->brother->brother);
		sprintf(c->code, "%s%s\nif %s%s%s goto L%d\ngoto L%d\n", e1->code, e2->code, e1->place, node->child->brother->code, e2->place, c->C_True, c->C_False);
	}
	return c;
}
struct S* S(struct TreeNode *node,int next) {
	struct TreeNode *temp = node->child;
	struct S *s = (struct S*)malloc(sizeof(struct S));
	s->next = next;
	if (strcmp(temp->name, "IF")==0) {
		if (temp->brother != 0 && strcmp(temp->brother->name, "C")==0) {
			struct C *c1 ,*c2;
			struct S *s1;
			c1=C(temp->brother,s);
			c2 = C_New(temp->brother, s);
			temp = temp->brother->brother;
			if (strcmp(temp->name, "THEN") == 0 && strcmp(temp->brother->name, "S") == 0) {
				s1=S(temp->brother,next);
				if (temp->brother->brother != 0) {
					temp = temp->brother->brother;
					struct S *s2;
					s2 = S(temp->brother,next);
					sprintf(s->code, "%sL%d:%s goto L%d\nL%d:%s\ngoto L%d\n", c2->code, c2->C_True, s1->code, s->next, c2->C_False, s2->code,s->next);

				}
				else {
					sprintf(s->code, "%sL%d:%s\n", c1->code, c1->C_True, s1->code);
				}
			}
			
		}
	}
	else if (strcmp(temp->name, "WHILE")==0) {
		s->begin = newLabel();
		struct C *c;
		c = C(temp->brother,s);
		struct S *s1;
		s1 = S(temp->brother->brother->brother,s->begin);
		sprintf(s->code, "L%d:%s\nL%d:%sgoto L%d\n", s->begin, c->code, c->C_True, s1->code, s->begin);
	}
	else if (strcmp(temp->name, "ID") == 0) {
		struct E *e ;
		e=E(temp->brother->brother);
		sprintf(s->code, "%s \n%s=%s\n",e->code, temp->code, e->place);
	}
	return s;
}
struct L* L(struct TreeNode *node) {
	struct L *l = (struct L*)malloc(sizeof(struct L));
	if (node->child != 0 && strcmp(node->child->name, "S") == 0) {
		struct S *s ;
		entry = getEntry();
		s=S(node->child,entry);
		strcpy(l->code, s->code);
		return l;
	}
}
struct P* P(struct TreeNode *node) {
	if (node->child != 0 && strcmp(node->child->name,"L")==0) {
		struct P *p = (struct P*)malloc(sizeof(struct P));
		struct L *l;
		l=L(node->child);
		if (node->child->brother != 0) {
			struct P *p1 = (struct P*)malloc(sizeof(struct P));
			p1 = P(node->child->brother);
			sprintf(p->code, "%sL%d:%s", l->code,entry-1, p1->code);
		}
		else {
			strcpy(p->code, l->code);
		}
		return p;
	}
}
int printAddr(struct TreeNode *node) {
	struct P *p;
	p=P(node);
	printf("%s", p->code);
}
