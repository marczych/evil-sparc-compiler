#!/bin/bash
# Builds all benchmarks into a .s file
make
if [ $? == 0 ]; then
   for benchmark in "BenchMarkishTopics" "bert" "biggest" "binaryConverter"\
      "creativeBenchMarkName" "fact_sum" "Fibonacci" "GeneralFunctAndOptimize"\
      "hailstone" "hanoi_benchmark" "killerBubbles" "mile1" "mixed"\
      "OptimizationBenchmark" "primes" "programBreaker" "stats" "TicTac"\
      "uncreativeBenchmark" "wasteOfCycles"
   do
      if [ -e ./benchmarks/$benchmark/$benchmark.s ]
      then
         rm ./benchmarks/$benchmark/$benchmark.s
      fi

      echo "----------------===========Compiling $benchmark=============-----------------"
      java Evil benchmarks/$benchmark/$benchmark.ev
   done
fi
