#!/bin/bash
# Runs all benchmarks in parallel and verifies their correctness.

function jobidfromstring()
{
   local STRING;
   local RET;

   STRING=$1;
   RET="$(echo $STRING | sed 's/^[^0-9]*//' | sed 's/[^0-9].*$//')"

   echo $RET;
}

ulimit -s unlimited

joblist=""

for benchmark in "BenchMarkishTopics" "bert" "biggest" "binaryConverter"\
   "creativeBenchMarkName" "fact_sum" "Fibonacci" "GeneralFunctAndOptimize"\
   "hailstone" "hanoi_benchmark" "killerBubbles" "mile1" "mixed"\
   "OptimizationBenchmark" "primes" "programBreaker" "stats" "TicTac"\
   "uncreativeBenchmark" "wasteOfCycles"
do
   ./fileRun.sh $benchmark &
   lastjob=`jobidfromstring $(jobs %%)`
   joblist="$joblist $lastjob"
done

count=20
for job in $joblist ; do
   wait %$job
   let "count -= $?"
done
echo "$count/20 passed!"
