#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description The operation of string
: <<!
The function replace allows you replace '_' to ' '.
For example:
  The result of `replace "Hello_World"` will be "Hello World"

@param $1: the string you want to replace

@return the replaced string
!
function replace() {
  echo ${1//_/ }
}