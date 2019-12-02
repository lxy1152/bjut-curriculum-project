#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Provide the function of creating a new menu

: <<!
The function create_menu provides a general way to create function's menu.
To use this function, you just need to provide
  1. menu title
  2. number of menu items
  3. name of each menu items
  4. number of functions you provided
  5. name of each functions you provided
Of course, you have to implement these functions.

For example:
  create_menu 'title' 2 'item1' 'item2' 1 'function1'

If you want to pass in a string with spaces, please replace all spaces with _.
And then before echo, use function replace in string.sh file 
to replace all _ to space.
If you pass a parameter "Hello World" and echo it, you can only see "Hello"
not "Hello World". 
In fact, you can change the space into any character you don't use very often.
If you want to do this, remember to modify the function replace.
Maybe there is some easy way to handle this problem. But I didn't find it.

@param menu title
@param number of menu items
@param name of each menu items
@param number of functions you provided
@param name of each functions you provided
!
function create_menu() {
  local choice
  local item_number=$2
  local first_item_number=3
  local function_number_start=$((first_item_number + item_number))
  while ((1)); do
    # print menu title
    show_menu_title $1

    # print menu item
    for ((i = $first_item_number; i < $function_number_start; i++)); do
      show_menu_item $i ${!i}
    done

    # get user's choice
    get_choice $function_number_start
    choice=$?
    # if $choice is invalid
    if [ -1 -eq $choice ]; then
      continue
    fi
    # if user want to go back
    if [ 0 -eq $choice ]; then
      break
    fi
    # if user want to execuate the function
    # get func name
    local where=$((function_number_start + choice))
    local func=${!where}

    # use "localhost" or "remote" to distinguish
    if [ "localhost" = $ip_address ]; then
      func=$func" localhost"
    else
      func=$func" remote"
    fi

    # for testing you can use this code below
    # func=$func" localhost"

    # exec func
    ${func}
  done
}

: <<!
The function show_menu_title can print the title of the function.
Before using command echo, remember to replace it.

@param $1: the title
!
function show_menu_title() {
  # replace the title
  local s=$(replace $1)
  # print tip
  echo -e "\nYou are now in function: $s"
  echo -e "Choose the function you want to use: \n"
}

: <<!
The function show_menu_item can print the items of the menu.
Before using command echo, remember to replace it.

@param $1: order number
@param $2: item you want to print
!
function show_menu_item() {
  # replace it
  local s=$(replace $2)
  # the order number should sub $first_item_number
  echo -e " " $(($1 - 3)) "\t" $s
}

: <<!
The function get_choice can get the choice the user input.
Before using command echo, remember to replace it.

@return choice
!
function get_choice() {
  # read choice
  local choice
  read -p "Your choice is: " choice

  # if user input the invalid number or string
  # need to add non-number condition
  if [ $1 -lt $choice ]; then
    echo "Please input correct number"
    return -1
  fi
  # if user want to go back
  if [ 0 -eq $choice ]; then
    echo "Go back to the previous level"
    return 0
  fi
  # if user want to exec the function, then return his choice
  return $choice
}
