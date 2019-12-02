#include"taddr.h"
int newLabel() {
	static int number=0;
	return number++;
}
int newTemp() {
	static int temp=0;
	return temp++;
}
