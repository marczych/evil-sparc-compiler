#!/bin/bash
# Runs all benchmarks sequentially and verifies their correctness.
ulimit -s unlimited
count=0

for benchmark in "BenchMarkishTopics" "bert" "biggest" "binaryConverter"\
   "creativeBenchMarkName" "fact_sum" "Fibonacci" "GeneralFunctAndOptimize"\
   "hailstone" "hanoi_benchmark" "killerBubbles" "mile1" "mixed"\
   "OptimizationBenchmark" "primes" "programBreaker" "stats" "TicTac"\
   "uncreativeBenchmark" "wasteOfCycles"
do
   ./fileRun.sh $benchmark
   let "count += $?"
done

echo "$count failed"
