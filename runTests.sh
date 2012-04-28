#!/bin/bash
ulimit -s unlimited
count=0
./fileRun.sh BenchMarkishTopics
let "count += $?"
./fileRun.sh bert
let "count += $?"
./fileRun.sh biggest
let "count += $?"
./fileRun.sh binaryConverter
let "count += $?"
./fileRun.sh creativeBenchMarkName
let "count += $?"
./fileRun.sh fact_sum
let "count += $?"
./fileRun.sh Fibonacci
let "count += $?"
./fileRun.sh GeneralFunctAndOptimize
let "count += $?"
./fileRun.sh hailstone
let "count += $?"
./fileRun.sh hanoi_benchmark
let "count += $?"
./fileRun.sh killerBubbles
let "count += $?"
./fileRun.sh mile1
let "count += $?"
./fileRun.sh mixed
let "count += $?"
./fileRun.sh OptimizationBenchmark
let "count += $?"
./fileRun.sh primes
let "count += $?"
./fileRun.sh programBreaker
let "count += $?"
./fileRun.sh stats
let "count += $?"
./fileRun.sh TicTac
let "count += $?"
./fileRun.sh uncreativeBenchmark
let "count += $?"
./fileRun.sh wasteOfCycles
let "count += $?"
echo "$count failed"
