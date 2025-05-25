# AtomicLong Counter Increment Demo

## Overview

This Java project demonstrates the use of the `AtomicLong` class to safely increment a shared counter across multiple threads **without the risk of race conditions**. It showcases how atomic operations allow multiple threads to update a shared variable concurrently while maintaining thread safety.

---

## Key Concepts

- **Atomic Variables (`AtomicLong`)**  
  Provides atomic operations for `long` values without using explicit synchronization.

- **Concurrency**  
  Two threads (`Thread1` and `Thread2`) run in parallel and attempt to increment the same shared counter.

- **Thread Safety Without Locks**  
  `AtomicLong.incrementAndGet()` ensures that each increment operation is performed safely across threads.

---
## How It Works

1. A shared `AtomicLong` counter is initialized to `0`.
2. A `CountIncrementer` task is defined which increments the counter **100,000 times** using `incrementAndGet()`.
3. Two threads are created and assigned the same task.
4. Both threads run concurrently and increment the shared counter.
5. After both threads finish, the main thread prints the final counter value.

---
