#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description IP-MAC binding

:<<!
The function ip_mac_binding_menu_title is used to print 
the title in the main menu.
!
function ip_mac_binding_menu_title() {
  echo "Bind IP and MAC address"
}

: <<!
The function ip_mac_binding_menu provides a menu 
that user can choose the functions:
  1. go back
  2. bind
!
function network_status_menu() {
  create_menu 'IP_MAC_Binding' 2 'Go_back' 'Bind' 1 'bind'
}

: <<!
The function bind provides a way to bind ip and mac.

@param $1: localhost or remote
!
function bind() {
  local ip
  local mac

  # get ip
  read -p "Input IP address: " ip
  # ip address validity check
  check_ip $ip
  result "Your IP address is valid" "Your IP address is invalid"
  if [ ! 0 -eq $?]; then
    return
  fi

  #get mac
  read -p "Input MAC address: " mac
  check_mac $mac
  result "Your IP address is valid" "Your IP address is invalid"
  if [ ! 0 -eq $?]; then
    return
  fi

  # mac address validity check
  check_mac $mac
  if [ ! 0 -eq $? ]; then
    return
  fi

  # localhost or remote
  if [ "localhost" = $1 ]; then
    # arp
    $(arp s $ip $mac)
  elif [ "remote" = $1 ]; then
    # arp
    $(
      ssh -p $port $username@$ip_address \
        arp s $ip $mac
    )
  else
    echo "Not supported. Please check your source code."
    return
  fi

  # print the result
  result "Bind success"
}
