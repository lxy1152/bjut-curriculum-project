#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Cleanup

:<<!
The function garbage_cleanup_menu_title is used to print 
the title in the main menu.
!
function garbage_cleanup_menu_title() {
  echo "Garbage cleanup"
}

: <<!
The function garbage_cleanup_menu provides a menu 
that user can choose the functions:
  1. go back
  2. cleanup
!
function garbage_cleanup_menu() {
  create_menu "Remote_system_garbage_file_cleanup" 2 "Go_back" "Cleanup" 1 "garbage_cleanup"
}

: <<!
The function garbage_cleanup provides a way to cleanup.

This function will delete all files in /tmp directory.

@param $1: localhost or remote
!
function garbage_cleanup() {
  echo "This operation will delete all files in tmp directory."
  warn "delete"
  if [ 1 -eq $? ]; then
    if [ "localhost" = $1 ]; then
      $(rm -rf /tmp/*)
    elif [ "remote" = $1 ]; then
      $(ssh -p $port $username@$ip_address sudo rm -rf /tmp/*)
    else
      echo "Not supported. Please check your source code."
      return
    fi
  fi
  result "Delete the files in tmp directory"
}
