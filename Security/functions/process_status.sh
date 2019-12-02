#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Query process status and output to file

:<<!
The function process_status_menu_title is used to print 
the title in the main menu.
!
function process_status_menu_title() {
  echo "View process status"
}

: <<!
The function process_status_menu provides a menu 
that user can choose the functions:
  1. go back
  2. query process status(by name) and output to file(txt and csv)
!
function process_status_menu() {
  create_menu "Process_status_output_to_TXT_and_CSV_files" 2 "Go_back" "Query_the_status_of_a_process_by_name" 1 "query_status_by_name"
}

: <<!
The function query_process_status provides a way to query the process status.
And then output it to file.

If you use remote server, then default path is "./output/process/remote/".
If you use loacalhost server, then default path is "./output/process/localhost/"
And default filename is process_status.

@param $1: localhost or remote
!
function query_status_by_name() {
  #set output filename
  local filename="process_status"

  # get process name
  # default all
  local process_name
  read -p "Input the process name(default: all): " process_name

  # localhost or remote
  if [ "localhost" = $1 ]; then
    # set output directory
    local output_dir="./output/process/localhost/"
    # check whether the directory exist
    directory_check $output_dir

    # if the process_name is empty, then do not use command grep
    # otherwise use command grep
    if [ -z $process_name ]; then
      ps_out=$(ps -ef | awk {'print $1, $2, $8'} | tee >$output_dir$filename.txt )
    else
      ps_out=$(ps -ef | grep $process_name | awk {'print $1, $2, $8'} | tee >$output_dir$filename.txt )
    fi
  elif [ "remote" = $1 ]; then
    # set output directory
    local output_dir="./output/process/remote/"
    # check whether the directory exist
    directory_check $output_dir

    # if the process_name is empty, then do not use command grep
    # otherwise use command grep
    if [ -z $process_name ]; then
      $(
        ssh -p $port $username@$ip_address \
          sudo ps -ef | awk {'print $1, $2, $8'} | tee >$output_dir$filename.txt
      )
    else
      $(
        ssh -p $port $username@$ip_address \
          ps -ef | grep $process_name | awk {'print $1, $2, $8'} | tee >$output_dir$filename.txt
      )
    fi
  else
    echo "Not supported. Please check your source code."
    return
  fi

  # although use command tee, it could not output to both txt and csv file
  # so use cat to output to csv file
  $(cat $output_dir$filename.txt >$output_dir$filename.csv)

  # print the result
  result "Now you can open "$output_dir$filename".txt(csv) to view the process status."
}
