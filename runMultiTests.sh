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
make

joblist=""

count=0
BENCHMARKS=`ls benchmarks`
for benchmark in $BENCHMARKS
do
   let "count += 1"
   ./fileRun.sh $benchmark asdf &
   lastjob=`jobidfromstring $(jobs %%)`
   joblist="$joblist $lastjob"
done

total=$count

for job in $joblist ; do
   wait %$job
   let "count -= $?"
done
echo "$count/$total passed!"
