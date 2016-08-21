# concurrent_programming
Concurrent programming experiment

This repository contains some programs which use concurrent processing written in `java`  and `python`.

`ConcurrentPerfectNumberFinder.java` is a simple java program to find list of perfect numbers from 1 to a certain limit. It demonstrate how to use Executor Framework, Callable and Future for multithreading. It also compares the running time with normal method without concurrrency.

To run the program:

1. javac ConcurrentPerfectNumberFinder.java
2. java ConcurrentPerfectNumberFinder 20000

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
