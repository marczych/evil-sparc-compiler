#!/bin/bash
# Builds all benchmarks into a .s file
make
if [ $? == 0 ]; then
   BENCHMARKS=`ls benchmarks`
   for benchmark in $BENCHMARKS
   do
      if [ -e ./benchmarks/$benchmark/$benchmark.s ]
      then
         rm ./benchmarks/$benchmark/$benchmark.s
      fi

      echo "----------------===========Compiling $benchmark=============-----------------"
      java Evil benchmarks/$benchmark/$benchmark.ev
   done
fi
