#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Validity check

: <<!
The function directory_check can check if the specified directory exists.
If not exist, it will recursively create the directory.

@param $1: the directory you want to check
!
function directory_check() {
  if [ ! -d $1 ]; then
    $(mkdir -p $1)
  fi
}

: <<!
The funcion check_ip is to check the validity of the ip address.
The input IP address should be dotted decimal.

@param $1: the ip address you want to check

@return is valid? 0 means valid and 1 means invalid
!
function check_ip() {
  # get the ip address
  local IP=$1

  # if this address satisfies this regular expression
  if [[ $IP =~ ^[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}$ ]]; then
    # cut the ip address and get each part of it
    local FIELD1=$(echo $IP | cut -d. -f1)
    local FIELD2=$(echo $IP | cut -d. -f2)
    local FIELD3=$(echo $IP | cut -d. -f3)
    local FIELD4=$(echo $IP | cut -d. -f4)

    # if the number is less than or equal to 255, return 1 (valid)
    # otherwise return 0 (invalid)
    if [ $FIELD1 -le 255 -a $FIELD2 -le 255 -a $FIELD3 -le 255 -a $FIELD4 -le 255 ]; then
      return 0
    else
      return 1
    fi
  else
    return 1
  fi
}

: <<!
The funcion check_mac is to check the validity of the mac address.
The format of mac address should be like:
  xx:xx:xx:xx:xx:xx

@param $1: the mac address you want to check

@return is valid? 0 means valid and 1 means invalid
!
function check_mac() {
  local mac=$1
  local s=$(echo $mac | grep -o -E '([[:xdigit:]]{1,2}:){5}[[:xdigit:]]{1,2}')
  if [[ $mac == $s ]]; then
    return 0
  fi
  return 1
}

: <<!
The funcion check_number is to check if the param is a number.

@param $1: the param you want to check

@return is valid? 0 means valid and 1 means invalid
!
function check_number() {
  local s=$(echo $1 | grep -o -E '[[:alpha:]]+')
  if [ ! -z $s ]; then
    return 1
  fi
  return 0
}
