#!/bin/bash
# Runs all benchmarks sequentially and verifies their correctness.
ulimit -s unlimited
count=0

BENCHMARKS=`ls benchmarks`
for benchmark in $BENCHMARKS
do
   ./fileRun.sh $benchmark
   let "count += $?"
done

echo "$count failed"
