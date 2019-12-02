#!/bin/bash
#Author      lxy
#Date        2019-11-13
#Version     1.0
#Description Welcome

: <<!
This function welcome is to print the banner of the program 
and connect to the user's remote or local server.

To generate a new ascii banner, visit the website: 
http://www.desmoulins.fr/index_us.php?pg=scripts!online!asciiart&start

@return the return value of function connect
!
function welcome() {
  # print the banner
  # the color is yellow
  echo -e " \033[33m
 ____                       _ _          __        __    _       _               
/ ___|  ___  ___ _   _ _ __(_) |_ _   _  \ \      / /_ _| |_ ___| |__   ___ _ __ 
\___ \ / _ \/ __| | | | '__| | __| | | |  \ \ /\ / / _\` | __/ __| '_ \ / _ \ '__|
 ___) |  __/ (__| |_| | |  | | |_| |_| |   \ V  V / (_| | || (__| | | |  __/ |   
|____/ \___|\___|\__,_|_|  |_|\__|\__, |    \_/\_/ \__,_|\__\___|_| |_|\___|_|   
                                  |___/                                           
      \033[0m"

  # print welcome
  echo "Welcome to Security Watcher. Your remote security management system."
  echo "To connect to your server, please input the information below."

  # import all files in ./utils
  source_utils

  # connect to the server
  connect
  return $?
}

: <<!
The function connect is let user to connect to the server.
User must provides the server's ip address, the port, the username and the password.

If this is the first time for user to use ssh to connect to the server, 
then the user will see the informatio below: 
  The authenticity of host 'yourip (yourip)' can't be established.
  ECDSA key fingerprint is SHA256:xxxx.
  Are you sure you want to continue connecting (yes/no)?
Just input yes.

After input the configuration, the program will execute
  ssh -p $port $username@$ip_address exit
to test the connection.

In order to run the program better, users who can use 'sudo' are recommended.

You should notice that:
  port,
  username,
  ip_address,
these are global variables.

@return the result. 0 means success
!
function connect() {
  # input the information
  while ((1)); do

    # input ip_address
    read -p "IP address(input localhost to skip)-> " ip_address

    # if the ip_address is localhost and then break the loop
    if [ "localhost" = $ip_address ]; then
      # echo skipped
      echo "Port(default: 22)-> skipped"
      echo "Username-> skipped"
      # echo success
      echo "Successfully login"
      break
    # if the ip_address is not localhost
    else
      # check the ip_address
      check_ip $ip_address

      # if ip is valid
      if [ $? = 0 ]; then
        # input the port
        while ((1)); do
          read -p "Port(default: 22)-> " port
          # if port is null, then set port=22. This is the default value.
          if [ -z $port ]; then
            port=22
            break
          fi
          #if port is not a number
          if [ -n "$(echo $port | sed -n "/^[0-9]\+$/p")" ]; then
            break
          else
            echo "Please input correct port"
          fi
        done

        # input the username
        read -p "Username-> " username
        # connect to to the server and execute 'exit'
        echo "Test connection..."
        $(ssh -p $port $username@$ip_address exit)
        # if success
        if [ $? = 0 ]; then
          echo "Test success!"
          return
        else
          echo "It seems like some of your configuration is wrong"
          echo "Please try again"
          break
        fi
      else
        echo "Please input the correct ip address"
      fi
    fi
  done
}

: <<!
The function source_utils aims to import all file in folder ./utils
by using command 'source'.

So if you want to provide more shell scripts as tools
then you can simply write a new shell script and put it in folder ./utils.
And then you can call it in other file instead of explicitly calling "source".
!
function source_utils() {
  # get all filenames
  # this is a string
  local file_str=$(ls -l ./utils/ | awk '{print $9}')
  # source each file
  for i in $file_str; do
    local exec_source="source ./utils/"$i
    ${exec_source}
  done
}
