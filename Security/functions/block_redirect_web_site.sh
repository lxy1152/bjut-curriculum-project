#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Block or redirect the website

:<<!
The function block_redirect_web_site_menu_title is used to print 
the title in the main menu.
!
function block_redirect_web_site_menu_title() {
  echo "Block and redirect the website"
}

: <<!
The function block_redirect_web_site_menu provides a menu 
that user can choose the functions:
  1. go back
  2. block the website
  3. redirect the website
!
function block_redirect_web_site_menu() {
  create_menu "Block_and_redirect_the_website" 3 "Go_back" "Block_the_website" "Redirect_the_website" 2 "block_website" "redirect_website"
}

: <<!
The function block_website provides a way to block the website.

@param $1: localhost or remote
!
function block_website() {
  # get domain
  local domain

  # localhost not supported
  if [ "remote" = $1 ]; then
    # redirect the domain to 127.0.0.1
    read -p "Please input the domain: " domain
    local block="127.0.0.1 "$domain
    $(
      ssh -p $port $username@$ip_address \
        sudo echo $block >>/etc/hosts
    )
  else
    echo "Not supported. Please check your source code."
    return
  fi
}

: <<!
The function redirect_website provides a way to redirect the website.

@param $1: localhost or remote
!
function redirect_website() {
  # get ip and domain
  local ip
  local domain
  
  # localhost not supported
  if [ "remote" = $1 ]; then
    # redirect the domain to $ip
    read -p "Please input the IP(redirct to): " ip
    read -p "Please input the domain: " domain
    local redirct=$ip" "$domain
    $(
      ssh -p $port $username@$ip_address \
        sudo echo $redirct >>/etc/hosts
    )
  else
    echo "Not supported. Please check your source code."
    return
  fi
}
