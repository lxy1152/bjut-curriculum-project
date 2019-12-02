#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Query network status and output to file

:<<!
The function network_status_menu_title is used to print 
the title in the main menu.
!
function network_status_menu_title() {
  echo "View network status"
}

: <<!
The function network_status_menu provides a menu 
that user can choose the functions:
  1. go back
  2. query network status and output to file(txt and csv)
!
function network_status_menu() {
  create_menu 'View_network_status_output_as_TXT_and_CSV_files' 2 'Go_back' 'Query_network_status' 1 'query_network_status'
}

: <<!
The function query_network_status provides a way to query the network status.
And then output it to file.

If you use remote server, then default path is "./output/network/remote/".
If you use loacalhost server, then default path is "./output/network/localhost/"
And default filename is network_status.

@param $1: localhost or remote
!
function query_network_status() {
  # set output filename
  local filename="network_status"

  # localhost or remote
  if [ "localhost" = $1 ]; then
    # set output directory
    local output_dir="./output/network/localhost/"
    # check whether the directory exist
    directory_check $output_dir
    # ipconfig and output
    $(ipconfig | tee $output_dir$filename.txt >$output_dir$filename.csv)
  elif [ "remote" = $1 ]; then
    # set output directory
    local output_dir="./output/network/remote/"
    # check whether the directory exist
    directory_check $output_dir
    # ip and output
    $(
      ssh -p $port $username@$ip_address \
        ip a sh | tee $output_dir$filename.txt >$output_dir$filename.csv
    )
  else 
    echo "Not supported. Please check your source code."
    return 
  fi

  # print the result
  result "Now you can open "$output_dir$filename".txt(csv) to view the network status."
}
