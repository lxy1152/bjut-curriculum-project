#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Shutdown or start service

:<<!
The function start_shutdown_service_menu_title is used to print 
the title in the main menu.
!
function start_shutdown_service_menu_title() {
  echo "Start or shutdown service"
}

: <<!
The function network_status_menu provides a menu 
that user can choose the functions:
  1. go back
  2. start service
  3. stop service
!
function start_shutdown_service_menu() {
  create_menu 'Remotely_start_or_shutdown_a_specified_service' 2 'Go_back' 'Start_service' 'Stop_service' 2 'start_service' 'stop_service'
}

: <<!
The function start_service provides a way to start the specified service.

@param $1: localhost or remote
!
function start_service() {
  # get service name
  local service_name
  read -p "Input the service name: " service_name

  # localhost or remote
  if [ "remote" = $1 ]; then
    $(
      ssh -p $port $username@$ip_address \
        sudo service $service_name start
    )
  else 
    echo "Not supported. Please check your source code."
    return 
  fi

  # print the result
  result "Service start"
}

: <<!
The function stop_service provides a way to stop the specified service.

@param $1: localhost or remote
!
function stop_service() {
  # get service name
  local service_name
  read -p "Input the service name: " service_name

  # localhost or remote
  if [ "remote" = $1 ]; then
    $(
      ssh -p $port $username@$ip_address \
        sudo service $service_name stop
    )
  else 
    echo "Not supported. Please check your source code."
    return 
  fi

  # print the result
  result "Service stop"
}