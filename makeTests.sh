#!/bin/bash
# Builds all benchmarks into a .s file
make
if [ $? == 0 ]; then
   rm `find benchmarks/ -name "*.s"`
   echo "----------------===========Compiling BenchMarkishTopics=============-----------------"
   java Evil benchmarks/BenchMarkishTopics/BenchMarkishTopics.ev
   echo "----------------===========Compiling bert=============-----------------"
   java Evil benchmarks/bert/bert.ev
   echo "----------------===========Compiling biggest=============-----------------"
   java Evil benchmarks/biggest/biggest.ev
   echo "----------------===========Compiling binaryConverter=============-----------------"
   java Evil benchmarks/binaryConverter/binaryConverter.ev
   echo "----------------===========Compiling creativeBenchMarkName=============-----------------"
   java Evil benchmarks/creativeBenchMarkName/creativeBenchMarkName.ev
   echo "----------------===========Compiling fact_sum=============-----------------"
   java Evil benchmarks/fact_sum/fact_sum.ev
   echo "----------------===========Compiling Fibonacci=============-----------------"
   java Evil benchmarks/Fibonacci/Fibonacci.ev
   echo "----------------===========Compiling GeneralFunctAndOptimize=============-----------------"
   java Evil benchmarks/GeneralFunctAndOptimize/GeneralFunctAndOptimize.ev
   echo "----------------===========Compiling hailstone=============-----------------"
   java Evil benchmarks/hailstone/hailstone.ev
   echo "----------------===========Compiling hanoi_benchmark=============-----------------"
   java Evil benchmarks/hanoi_benchmark/hanoi_benchmark.ev
   echo "----------------===========Compiling killerBubbles=============-----------------"
   java Evil benchmarks/killerBubbles/killerBubbles.ev
   echo "----------------===========Compiling mile1=============-----------------"
   java Evil benchmarks/mile1/mile1.ev
   echo "----------------===========Compiling mixed=============-----------------"
   java Evil benchmarks/mixed/mixed.ev
   echo "----------------===========Compiling OptimizationBenchmark=============-----------------"
   java Evil benchmarks/OptimizationBenchmark/OptimizationBenchmark.ev
   echo "----------------===========Compiling primes=============-----------------"
   java Evil benchmarks/primes/primes.ev
   echo "----------------===========Compiling programBreaker=============-----------------"
   java Evil benchmarks/programBreaker/programBreaker.ev
   echo "----------------===========Compiling stats=============-----------------"
   java Evil benchmarks/stats/stats.ev
   echo "----------------===========Compiling TicTac=============-----------------"
   java Evil benchmarks/TicTac/TicTac.ev
   echo "----------------===========Compiling uncreativeBenchmark=============-----------------"
   java Evil benchmarks/uncreativeBenchmark/uncreativeBenchmark.ev
   echo "----------------===========Compiling wasteOfCycles=============-----------------"
   java Evil benchmarks/wasteOfCycles/wasteOfCycles.ev
fi
