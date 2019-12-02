#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Show the menu

: <<!
The function menu aims to provide a general way to create a main menu
by scanning the files in folder ./functions.
That means all file in folder ./functions will be 
regarded as the "function" provided to the user.

To provide a new function to user, you can just write a new shell script 
file and put it in folder ./functions.
The format of filename should be like xx_xx.sh.

To custom the title of function, you need to create a function named 
$filename"_menu_title". 
Because the program will call function $filename"_menu" to start this 
function, so in this file you should create a function $filename"_menu".
And the program provides a function "create_menu" to create a new function's menu,
so in this function you should call function create_menu.
Check file ./functions/function_menu.sh for details of the function create_menu.

For example if I want to provide a new function named "find file", then I
should do this work below:
  1. create a new file named "find_file.sh"
  2. put this file in folder "./functions/"
  3. open this file
  4. create a new function named "find_file_menu_title" and then input:
     'echo "Find file by name"'
  5. create a new function named "find_file_menu"
  6. open file ./functions/function_menu.sh and determine 
     how to call this function
  7. call the function "create_menu" in the 
     function "find_file_menu" you have just created 

Now the problem I found is that it takes a little longer to print the menu.
!
function menu() {
  # if directory is missing
  if [ ! -d "./functions/" -o ! -d "./utils/" ]; then
    echo "Please check if you don't have /functions or /utils/ directory"
    echo "And then try again"
    return
  fi

  # get all filenames
  # this is a string
  local file_str=$(ls -l ./functions/ | awk '{print $9}')
  # this is a file array
  local file_arr=(${file_str// /})
  # this is a file array without suffix(.sh)
  local file_arr_no_suffix
  # the file's name
  local filename
  # init
  for ((i = 0; i < ${#file_arr[@]}; i++)); do
    # get filename
    filename=${file_arr[$i]}
    # exec the command 'source $filename' to import the file
    local exec_source="source ./functions/"$filename
    ${exec_source}

    # delete suffix .sh
    filename=${filename/%.sh/ }
    # append this item
    file_arr_no_suffix[$i]=$filename
  done

  # execute by choice
  local choice
  while ((1)); do
    # print the menu
    # color is green
    echo -e "\033[32m"
    echo -e " " 0 "\t\c"
    echo "Exit"
    for ((i = 0; i < ${#file_arr_no_suffix[@]}; i++)); do
      # get filename
      filename=${file_arr_no_suffix[$i]}
      local exec_title=$filename"_menu_title"
      # actually the value of exec_title is $filename _menu_title
      # so you should to delete the space
      exec_title=$(echo $exec_title | sed 's/ //g')
      # print order number and it's name
      echo -e " " $((i + 1)) "\t\c"
      ${exec_title}
    done
    echo -e "\033[0m"

    # get user's choice
    read -p "Choose the function you want to use: " choice

    # if user want to exit
    if [ 0 -eq $choice ]; then
      break
    fi

    # otherwise exec the command '$filename_menu' to start this function
    local exec_menu=${file_arr_no_suffix[$((choice - 1))]}"_menu"
    # actually the value of exec_menu is $filename _menu
    # so you should to delete the space
    exec_menu=$(echo $exec_menu | sed 's/ //g')
    ${exec_menu}
  done
}
