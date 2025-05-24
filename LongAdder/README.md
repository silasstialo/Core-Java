# Concurrent Counter with LongAdder
This project demonstrates the use of Java's LongAdder in a multi-threaded environment to efficiently count operations across several threads using a thread pool.

## 🚀 Overview
***The program:***

- Uses a fixed thread pool of 8 threads.

- Launches 12 tasks (numberOfJobs), each performing 100 increments on a shared LongAdder counter.

- Safely shuts down the executor service.

- Waits for all tasks to complete before printing the final count.

## 📦 Technologies Used
- Java 8+

- java.util.concurrent package

- ExecutorService

- Executors

- LongAdder

- TimeUnit

- IntStream for concise loop operations

## 📄 Code Summary
- A LongAdder is used instead of AtomicLong for better performance under high contention.

- ExecutorService manages a pool of 8 threads.

- Each task increments the counter 100 times.

- The program ensures all threads complete by calling shutdown() and awaitTermination().
