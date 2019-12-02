#!/bin/bash
#Author      lxy
#Date        2019-11-16
#Version     1.0
#Description Provide some useful tools in function using

: <<!
The function warn will ask user if he/she want to continue
before some important opeartion.

Such as kill the process or shutdown the computer...

@param $1: the tip you want to show to the user

@return do this? 0 means yes and 1 means no 
!
function warn() {
  # show the tip
  local tip=$1
  read -p "Are you sure you want to $tip? (Y/N): " sure

  # misspelling is also ok
  case $sure in
  [yY][eE][sS] | [yY])
    return 0
    ;;
  [nN][oO] | nN)
    return 1
    ;;
  *)
    return 1
    ;;
  esac
}

: <<!
The function result will show the result after running a function
by using it's return value.

@param $1: the message you want to show when exec is success 
@param $2: the message you want to show when exec is fail. can be null
!
function result() {
  # if success
  if [ 0 -eq $? ]; then
    # print $1
    echo $1
  # if fail
  else
    # if null, print default message
    # otherwise print $2
    if [ -z $2 ]; then
      echo "There is something wrong. Please try again"
    else
      echo $2
    fi
  fi
}
