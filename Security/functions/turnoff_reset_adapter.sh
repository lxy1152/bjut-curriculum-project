#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Turnoff or reset the adapter

: <<!
The function turnoff_reset_adapter_menu_title is used to print 
the title in the main menu.
!
function turnoff_reset_adapter_menu_title() {
  echo "Turnoff or reset the adapter"
}

: <<!
The function shutdown_process_menu provides a menu 
that user can choose the functions:
  1. go back
  2. turn off the adapter
  3. reset the adapter
!
function turnoff_reset_adapter_menu() {
  create_menu "Turnoff_or_reset_the_adapter" 3 "Go_back" "Turnoff_the_adapter" "Reset_the_adapter" 2 "turnoff_adapter" "reset_adapter"
}

: <<!
The function turnoff_adapter provides a way to turnoff the adapter.

@param $1: localhost or remote
!
function turnoff_adapter() {
  echo "Developing"
  # $(ssh -p $port $username@$ip_address ifconfig eth0 down)
}

: <<!
The function reset_adapter provides a way to reset the adapter.

@param $1: localhost or remote
!
function reset_adapter() {
  echo "Developing"
}
