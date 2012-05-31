#!/bin/bash

ulimit -s unlimited
make

FILE="myTimes.txt"

echo "" >> $FILE
echo "Benchmark, -noopt, -finline, -deadcode, -tailcall, -irc, -allopt" >> $FILE

BENCHMARKS=`ls benchmarks`
for benchmark in $BENCHMARKS
do
   BENCHMARK_STATS="$benchmark"

   for opt in "-noopt" "-finline" "-deadcode" "-tailcall" "-irc" "-allopt"
   do
      echo "$opt $benchmark"
# later flags will override the nopt flag
      java Evil -noopt $opt benchmarks/$benchmark/$benchmark.ev
      gcc -mcpu=v9 benchmarks/$benchmark/$benchmark.s -o myTimeExe
      /usr/bin/time -o time.txt -f '%U' myTimeExe < benchmarks/$benchmark/input > /dev/null
      BENCHMARK_STATS="$BENCHMARK_STATS, `cat time.txt`"
   done
   echo $BENCHMARK_STATS >> $FILE
done

rm time.txt myTimeExe
