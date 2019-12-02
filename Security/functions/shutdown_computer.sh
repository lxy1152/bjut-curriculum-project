#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Shutdown the computer

:<<!
The function shutdown_computer_menu_title is used to print 
the title in the main menu.
!
function shutdown_computer_menu_title() {
  echo "Shutdown the computer"
}

: <<!
The function shutdown_process_menu provides a menu 
that user can choose the functions:
  1. go back
  2. shutdown the computer
!
function shutdown_computer_menu() {
  create_menu "Shutdown_the_computer_remotely" 2 "Go_back" "Shutdown_the_computer" 1 "shutdown_computer"
}

: <<!
The function shutdown_computer provides a way to shutdown the computer.

@param $1: localhost or remote
!
function shutdown_computer() {
  # confirm
  warn "shutdown the computer"
  # if yes
  if [ 1 -eq $? ]; then
    # localhost or remote
    if [ "localhost" = $1 ]; then
      $(shutdown -h now)
    elif [ "remote" = $1 ]; then
      $(ssh -p $port $username@$ip_address sudo shutdown -h now)
    else
      echo "Not supported. Please check your source code."
      return
    fi
  fi
  result "Shutdown success"
}
