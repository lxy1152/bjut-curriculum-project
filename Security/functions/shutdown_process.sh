#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Shutdown the specfied process

:<<!
The function ip_mac_binding_menu_title is used to print 
the title in the main menu.
!
function shutdown_process_menu_title() {
  echo "Shutdown process"
}

: <<!
The function shutdown_process_menu provides a menu 
that user can choose the functions:
  1. go back
  2. kill the process by PID
!
function shutdown_process_menu() {
  create_menu "Remote_shutdown_of_the_specified_process" 2 "Go_back" "Kill_the_process_by_PID" 1 "kill_process_by_pid"
}

: <<!
The function kill_process_by_pid provides a way to kill the process by pid.

Before running this function, it is recommended to check the status of the process.

@param $1: localhost or remote
!
function kill_process_by_pid() {
  # get the pid
  read -p "Input the pid: " pid

  # confirm
  warn "kill the process[PID=$pid]"
  # if yes
  if [ 0 -eq $? ]; then
    # localhost or remote
    if [ "localhost" = $1 ]; then
      $(kill $pid)
    elif [ "remote" = $2]; then
      $(ssh -p $port $username@$ip_address sudo kill $pid)
    else
      echo "Not supported. Please check your source code."
      return
    fi
  fi

  # print result
  result "Kill process[PID=$pid]"
}
