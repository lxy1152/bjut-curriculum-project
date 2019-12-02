#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Test bandwidth

:<<!
The function test_bandwidth_menu_title is used to print 
the title in the main menu.
!
function test_bandwidth_menu_title() {
  echo "Test bandwidth"
}

: <<!
The function test_bandwidth_menu provides a menu 
that user can choose the functions:
  1. go back
  2. test bandwidth
!
function test_bandwidth_menu() {
  create_menu "Test_bandwidth" 2 "Go_back" "Test_bandwidth" 1 "test_bandwidth"
}

: <<!
The function test_bandwidth provides a way to test the bandwidth.
Still under developing.
!
function test_bandwidth() {
  echo "Developing..."
  # echo "Start testing..."
  # local a=$(
  #   ssh -p $port $username@$ip_address \
  #     "cat /sys/class/net/eth0/statistics/rx_packets >>packets.txt &&
  #     cat /sys/class/net/eth0/statistics/tx_packets >>packets.txt &&
  #     sleep 1 &&
  #     cat /sys/class/net/eth0/statistics/rx_packets >>packets.txt &&
  #     cat /sys/class/net/eth0/statistics/tx_packets >>packets.txt &&
  #     cat packets.txt
  #     rm packets.txt"
  # )
  # # echo "tx eth0: $TXPPS pkts/s rx eth0: $RXPPS pkts/s"
  # result "Test success"
}
