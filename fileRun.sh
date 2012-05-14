#!/bin/bash
echo ----------============= Running $1 =============-----------
gcc -mcpu=v9 benchmarks/$1/$1.s -o benchmarks/$1/my.$1
if [ $? == 0 ]; then
   rm ./benchmarks/$1/my.out
   time ./benchmarks/$1/$1 < ./benchmarks/$1/input > ./benchmarks/$1/my.out
   diff ./benchmarks/$1/my.out ./benchmarks/$1/output > ./benchmarks/$1/my.diff
   if [ $? == 0 ]; then
      echo "Success!! - $1";
      exit 0
   else
      echo "Fail!! =( - $1";
      exit 1
   fi
else
   exit 1
fi
