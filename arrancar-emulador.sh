#!/bin/bash

PATH=$PATH:/home/alvaro/Android/Sdk/emulator

AVD=$(emulator -list-avds)
AVD=$(echo $AVD | awk '{print $1}') #si hay más de una línea, elijo la primera
echo Es necesario apagar VirtualBox!
emulator -avd $AVD
