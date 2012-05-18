#!/bin/bash

ulimit -s unlimited

FILE="gccTimes.txt"

# Truncate the file and start with the column headers
echo "Benchmark, O0, O1, O2, O3" > $FILE

BENCHMARKS=`ls benchmarks`
for benchmark in $BENCHMARKS
do
   BENCHMARK_STATS="$benchmark"

   for opt in "O0" "O1" "O2" "O3"
   do
      echo "$opt $benchmark"
      gcc -$opt benchmarks/$benchmark/$benchmark.c -o gccTimeExe
      /usr/bin/time -o time.txt -f '%U' gccTimeExe < benchmarks/$benchmark/input > /dev/null
      BENCHMARK_STATS="$BENCHMARK_STATS, `cat time.txt`"
   done
   echo $BENCHMARK_STATS >> $FILE
done

rm time.txt gccTimeExe
