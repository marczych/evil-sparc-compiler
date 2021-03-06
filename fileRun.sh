#!/bin/bash
# Assembles the .s file into an executable and runs it with the benchmark's
# input file and compares it with the benchmarks' output file. Also outputs
# time taken.

ulimit -s unlimited

if [ ! -e benchmarks/$1/$1.ev ]
then
   echo "Benchmark $1 not found!"
   exit
fi

if [ $# -gt 1 ]
then
   make

   if [ $? != 0 ]
   then
      exit
   fi

   java Evil benchmarks/$1/$1.ev

   if [ $? != 0 ]
   then
      exit
   fi
fi

echo ----------============= Running $1 =============-----------
gcc -mcpu=v9 benchmarks/$1/$1.s -o benchmarks/$1/my.$1
if [ $? == 0 ]; then
   if [ -e "./benchmarks/$1/my.out" ]
   then
      rm ./benchmarks/$1/my.out
   fi

   time ./benchmarks/$1/my.$1 < ./benchmarks/$1/input > ./benchmarks/$1/my.out
   diff ./benchmarks/$1/my.out ./benchmarks/$1/output > ./benchmarks/$1/my.diff
   if [ $? == 0 ]; then
      echo -e "[32mSuccess!![m - $1";
      exit 0
   else
      echo -e "[31mFail!! =([m - $1";
      exit 1
   fi
else
   exit 1
fi
