#!/bin/bash

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
./fileRun.sh BenchMarkishTopics &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh bert &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh biggest &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh binaryConverter &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh creativeBenchMarkName &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh fact_sum &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh Fibonacci &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh GeneralFunctAndOptimize &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh hailstone &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh hanoi_benchmark &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh killerBubbles &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh mile1 &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh mixed &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh OptimizationBenchmark &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh primes &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh programBreaker &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh stats &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh TicTac &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"
./fileRun.sh wasteOfCycles &
lastjob=`jobidfromstring $(jobs %%)`
joblist="$joblist $lastjob"

count=20
for job in $joblist ; do
   wait %$job
   let "count -= $?"
done
echo "$count/20 passed!"
