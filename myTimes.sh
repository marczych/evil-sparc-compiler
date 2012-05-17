#!/bin/bash

FILE="myTimes.txt"

echo "" >> $FILE
echo "Benchmark, -nofinline -nodeadcode, -finline -nodeadcode, -nofinline -deadcode, -finline -deadcode" >> $FILE

for benchmark in "BenchMarkishTopics" "bert" "biggest" "binaryConverter"\
   "creativeBenchMarkName" "fact_sum" "Fibonacci" "GeneralFunctAndOptimize"\
   "hailstone" "hanoi_benchmark" "killerBubbles" "mile1" "mixed"\
   "OptimizationBenchmark" "primes" "programBreaker" "stats" "TicTac"\
   "uncreativeBenchmark" "wasteOfCycles"
do
   BENCHMARK_STATS="$benchmark"

   for opt in "-nofinline -nodeadcode" "-finline -nodeadcode"\
              "-nofinline -deadcode"   "-finline -deadcode"
   do
      echo "$opt $benchmark"
      java Evil $opt benchmarks/$benchmark/$benchmark.ev
      gcc -mcpu=v9 benchmarks/$benchmark/$benchmark.s -o myTimeExe
      /usr/bin/time -o time.txt -f '%U' myTimeExe < benchmarks/$benchmark/input > /dev/null
      BENCHMARK_STATS="$BENCHMARK_STATS, `cat time.txt`"
   done
   echo $BENCHMARK_STATS >> $FILE
done

rm time.txt myTimeExe
