#!/bin/bash

java Evil $1.ev
if [ $? == 0 ]
then
   gcc -mcpu=v9 $1.s -o $1
fi
