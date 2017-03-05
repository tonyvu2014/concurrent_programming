# concurrent_programming
Concurrent programming experiment

This repository contains some programs which use concurrent processing written in `java`  and `python`.

`ConcurrentPerfectNumberFinder.java` is a simple java program to find list of perfect numbers from 1 to a certain limit. It demonstrate how to use Executor Framework, Callable and Future for multithreading. It also compares the running time with normal method without concurrrency.

To run the program:

**1. javac ConcurrentPerfectNumberFinder.java**

**2. java ConcurrentPerfectNumberFinder 500**

Some results from running the program:

```
$ java ConcurrentPerfectNumberFinder 500
Perfect numbers from 1 to 500:
6
28
496
Running findPerfectNumber() in 3 ms
Perfect numbers from 1 to 500:
6
28
496
Running findPerfectNumberInParallel() in 8 ms
```

```
$ java ConcurrentPerfectNumberFinder 50000
Perfect numbers from 1 to 50000:
6
28
496
8128
Running findPerfectNumber() in 3309 ms
Perfect numbers from 1 to 50000:
6
28
496
8128
Running findPerfectNumberInParallel() in 1808 ms
```

As you can see, multithreading codes ran slower with small number due to the overhead of maintaining additional objects and tasks. Only for big number, multithreading program shows performance enhancement.

`concurrent_perfect_number_finder.py` is the equivalent program in python. In Python, because of GIL, we cannot have multithreading, so to achieve parallelism, we will create multiple processes using multiprocessing module. Running the script with:

**python concurrent_perfect_number_finder.py 500**

Example outputs:

```
$ python concurrent_perfect_number_finder.py 500
Perfect numbers from 1 to 500:
6
28
496
Running find_perfect_number() in 0.00787615776062 seconds
Perfect numbers from 1 to 500:
6
28
496
Running find_perfect_number_in_parallel() in 0.0618340969086 seconds
```

```
$ python concurrent_perfect_number_finder.py 50000
Perfect numbers from 1 to 50000:
6
28
496
8128
Running find_perfect_number() in 79.1169490814 seconds
Perfect numbers from 1 to 50000:
6
28
496
8128
Running find_perfect_number_in_parallel() in 46.4370360374 seconds
```

We observe the same phenomenon: multi-processing is slower for small number and only faster for significant input. 
Some other interesting findings:
- With the same input and same algorithm, java is faster than python
- I tried to play with the number of processes by changing the value of pool_size in `pool_size = multiprocessing.cpu_count()*2`. Using the multiple of 3, 4 or higher does not add any performance advantage. 

`ParallelSort.java` is a program to test out the `Arrays.parallelSort()` from Java 8. You need to compile and run it on Java 1.8 version. Here are the comparision in running time between normal `Arrays.sort()` and `Arrays.parallelSort()`:

```
$ /Library/Java/JavaVirtualMachines/jdk1.8.0_71.jdk/Contents/Home/bin/java ParallelSort 10000
Running Arrays.sort() in 3 ms
Running Arrays.parallelSort() in 4 ms
 ```
 
 ```
 $ /Library/Java/JavaVirtualMachines/jdk1.8.0_71.jdk/Contents/Home/bin/java ParallelSort 100000
 Running Arrays.sort() in 25 ms
 Running Arrays.parallelSort() in 27 ms
 ```
 
 ```
 $ /Library/Java/JavaVirtualMachines/jdk1.8.0_71.jdk/Contents/Home/bin/java ParallelSort 1000000
 Running Arrays.sort() in 63 ms
 Running Arrays.parallelSort() in 83 ms
 ```
 
 ```
 $ /Library/Java/JavaVirtualMachines/jdk1.8.0_71.jdk/Contents/Home/bin/java ParallelSort 10000000
 Running Arrays.sort() in 381 ms
 Running Arrays.parallelSort() in 243 ms
 ```

Again, only with very big input, parallelSort() is faster than normal sort() method. For not so big input, it is even slower.y

`Schedule.java` is a simple program to understand the differences between schedule(), scheduleAtFixedRate() and scheduleWithFixedDelay()