# Concurrent Maximum Sales Tracker using `LongAccumulator` in Java

---

## Overview

This Java program simulates the concurrent recording of sales data from multiple stores using threads. It utilizes Java's `LongAccumulator` to safely and efficiently track the maximum sales value observed across all threads.

The key components of this application include:

- **Thread pool**: Manages concurrent execution of tasks.
- **`LongAccumulator`**: Accumulates the maximum sales value observed across all threads.
- **Random sales generation**: Each thread simulates daily sales numbers.

## Key Concepts

- **Concurrency**: Multiple threads update a shared resource concurrently.
- **Atomic Operations**: `LongAccumulator` ensures updates are atomic and thread-safe without explicit synchronization.
- **Functional Programming**: Uses `Math::max` as the accumulator function.

## How It Works

1. A `LongAccumulator` is initialized with:
   - `Math::max` as the accumulator function.
   - `Long.MIN_VALUE` as the identity (suitable for finding max).

2. A fixed thread pool of 8 threads is created using `Executors.newFixedThreadPool(8)`.

3. A number of jobs (`numberOfJobs = 12`) are submitted to the thread pool.

4. Each job simulates 10 random sales entries (values between 0 and 1000) and attempts to update the global maximum using `maxSales.accumulate(sales)`.

5. The main thread waits for all tasks to complete using `awaitTermination`.

6. Once all threads complete, the final maximum sales value is retrieved using `maxSales.get()` and printed.

## Requirements

- Java 17 or later (for `Random.nextLong(long bound)` support)
- IDE or command-line environment that supports Java
